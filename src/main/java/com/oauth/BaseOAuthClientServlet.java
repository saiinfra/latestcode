package com.oauth;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.DeploymentSettingClientDO;
import com.ds.salesforce.dao.comp.DeploySettingsClientDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;
import com.util.SFoAuthHandle;

/**
 * Servlet parameters
 */

@WebServlet(name = "baseoauthclientservlet", urlPatterns = {
		"/baseoauthclientservlet/*", "/baseoauthclientservlet" })
public class BaseOAuthClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(BaseOAuthClientServlet.class);

	private String tokenFilePath = null;
	private String clientId = null;
	private String clientSecret = null;
	private String redirectUri = null;
	private String environment = null;
	private String authUrl = null;
	private String tokenUrl = null;
	private String authorizationCode = null;
	private String idURL = null;

	public void init() throws ServletException {
		/*
		 * clientId = this.getInitParameter("clientId"); clientSecret =
		 * this.getInitParameter("clientSecret"); redirectUri =
		 * this.getInitParameter("redirectUri"); environment =
		 * this.getInitParameter("environment");
		 */

		Properties p = new Properties();

		try {
			p.load(getServletContext().getResourceAsStream(
					"/WEB-INF/properties/config.properties"));

		} catch (Exception e) {
			// TODO: handle exception
		}

		clientId = p.getProperty("baseclientclientId");
		clientSecret = p.getProperty("baseclientclientSecret");
		redirectUri = p.getProperty("baseclientredirectUri");
		environment = p.getProperty("baseclientenvironment");

		System.out.println("clientId -- " + clientId + " --clientSecret--"
				+ clientSecret + "--redirectUri--" + redirectUri
				+ "--environment--" + environment);
		try {

			authUrl = environment
					+ "/services/oauth2/authorize?response_type=code&client_id="
					+ clientId + "&redirect_uri="
					+ URLEncoder.encode(redirectUri, "UTF-8");
			System.out.println("Auth URL---" + authUrl);
		} catch (Exception e) {
			LOG.error("Error while creating AuthURL: {} ", e.getMessage());
			throw new ServletException("Error while creating AuthURL:", e);
		}
		tokenUrl = environment + "/services/oauth2/token";
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String refreshToken = null;

		String accessToken = (String) request.getSession().getAttribute(
				Constants.ACCESS_TOKEN);
		processStateParam(request.getParameter("state"));

		System.out.println("main : " + request.getRequestURI());
		System.out.println("tokenurl : " + tokenUrl);
		System.out.println("authUrl : " + authUrl);
		System.out.println("test: " + request.getRequestURI());
		String instanceUrl = null;
		tokenUrl = getEnvironment() + "/services/oauth2/token";
		String code = request.getParameter("code");
		String BaseOrganizationId = request.getParameter("BaseOrganizationId");
		String BaseOrg = request.getParameter("BaseOrg");
		System.out.println("BaseOrganizationId--------:" + BaseOrganizationId);
		System.out.println("BaseOrg--------:" + BaseOrg);

		System.out.println("Authorization code:" + code);
		authorizationCode = code;
		LOG.info("Auth successful, got Authorization code: {} ", code);
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(tokenUrl);
		post.addParameter("code", code);
		post.addParameter("grant_type", "authorization_code");
		post.addParameter("client_id", clientId);
		post.addParameter("client_secret", clientSecret);
		post.addParameter("redirect_uri", redirectUri);
		try {
			httpclient.executeMethod(post);
			JSONObject authResponse = new JSONObject(new JSONTokener(
					new InputStreamReader(post.getResponseBodyAsStream())));
			accessToken = authResponse.getString("access_token");
			instanceUrl = authResponse.getString("instance_url");
			refreshToken = authResponse.getString("refresh_token");
			System.out.println("refreshToken....." + refreshToken);
			// System.out.println("********: "+accessToken + "~"+instanceUrl);
			idURL = authResponse.getString("id");
			System.out.println("idURL---------" + idURL);
			LOG.info("Auth Response: {} ", authResponse.toString(2));
		} catch (JSONException e) {
			LOG.error("Error while getting JSONObject from AuthResponse: {} ",
					e.getMessage());
			throw new ServletException(
					"Error while getting JSONObject from AuthResponse: {} ", e);
		} catch (Exception e) {
			LOG.error("Error while Oauth with Salesforce: {} ", e.getMessage());
			throw new ServletException("Error while Oauth with Salesforce:  ",
					e);
		} finally {
			post.releaseConnection();
		}
		// }
		// Set a session attribute so that other servlets can get the access
		// token
		request.getSession().setAttribute(Constants.ACCESS_TOKEN, accessToken);
		request.getSession().setAttribute(Constants.INSTANCE_URL, instanceUrl);
		request.getSession()
				.setAttribute(Constants.REFRESH_TOKEN, refreshToken);

		System.out.println("RL --"
				+ (String) request.getSession().getAttribute(
						Constants.INSTANCE_URL));

		// We also get the instance URL from the OAuth response, so set it
		// in the session too

		request.getSession().setAttribute("AuthorizationCode",
				authorizationCode);
		request.getSession().setAttribute("idURL", idURL);

		// }
		getIdDetails(request);
		response.sendRedirect(request.getContextPath() + "/sfcustomhome");
	}

	public void getIdDetails(HttpServletRequest request)
			throws ServletException, IOException, SFException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String accessToken = (String) session.getAttribute("ACCESS_TOKEN");
		String instanceURL = (String) request.getSession().getAttribute(
				Constants.INSTANCE_URL);
		String refreshToken = (String) request.getSession().getAttribute(
				Constants.REFRESH_TOKEN);

		HttpClient httpclient = new HttpClient();
		GetMethod get = new GetMethod(idURL);
		// set the token in the header
		get.setRequestHeader("Authorization", "OAuth " + accessToken);

		// set the SOQL as a query param
		NameValuePair[] params = new NameValuePair[1];

		params[0] = new NameValuePair("oauth_token", accessToken);
		get.setQueryString(params);
		// System.out.println("Accessing ID URL---" + get.getURI().toString());
		try {
			httpclient.executeMethod(get);
			System.out.println(" status - " + get.getStatusCode());
		} catch (Exception e) {
			throw new SFException(e.getMessage(), SFErrorCodes.SF_HTTP_Error);
		}
		if (get.getStatusCode() == HttpStatus.SC_OK) {
			// Now lets use the standard java json classes to work with the
			// results
			try {
				JSONObject jsonResponse = new JSONObject(new JSONTokener(
						new InputStreamReader(get.getResponseBodyAsStream())));
				System.out.println("Auth Response: {} "
						+ jsonResponse.toString(2));
				String userName = new String(
						(String) jsonResponse.get("username"));
				String orgId = new String(
						(String) jsonResponse.get("organization_id"));

				System.out.println("Creating DeploymentSettingClientDO : "
						+ orgId + "~" + accessToken + "~" + instanceURL + "~"
						+ refreshToken);
				DeploymentSettingClientDO deploymentSettingsClientDO = new DeploymentSettingClientDO(
						orgId, accessToken, instanceURL, refreshToken);
				saveTokens(deploymentSettingsClientDO);
			} catch (JSONException e) {
				e.printStackTrace();
				LOG.error(
						"Error while getting JSONObject from the records {} ",
						e.getMessage());
				throw new ServletException(
						"Error while getting JSONObject from the records: ", e);
			} catch (SFException e) {
				throw (e);
			}
		}
	}

	public void saveTokens(DeploymentSettingClientDO deploymentSettingsClientDO)
			throws SFException {
		DeploySettingsClientDAO deploymentSettingsClientDAO = new DeploySettingsClientDAO();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			fdGetSFoAuthHandleService.setSfHandleToNUll();
			SFoAuthHandle sfBaseHandle = fdGetSFoAuthHandleService
					.getSFoAuthHandle(deploymentSettingsClientDO.getOrgId(),
							deploymentSettingsClientDO.getToken(),
							deploymentSettingsClientDO.getServerUrl(),
							deploymentSettingsClientDO.getRefreshToken(),
							Constants.BaseOrgID);

			List deploymentSettingsClientList = deploymentSettingsClientDAO
					.findById(deploymentSettingsClientDO.getOrgId(),
							sfBaseHandle);
			// If should never occur
			if (deploymentSettingsClientList.size() == 0) {
				deploymentSettingsClientDAO.insert(deploymentSettingsClientDO,
						sfBaseHandle);

			} else {
				DeploymentSettingClientDO dsDO = null;
				for (Iterator iterator = deploymentSettingsClientList
						.iterator(); iterator.hasNext();) {
					dsDO = (DeploymentSettingClientDO) iterator.next();
					dsDO.setOrgId(deploymentSettingsClientDO.getOrgId());
					dsDO.setServerUrl(deploymentSettingsClientDO.getServerUrl());
					dsDO.setToken(deploymentSettingsClientDO.getToken());
					dsDO.setRefreshToken(deploymentSettingsClientDO
							.getRefreshToken());
					deploymentSettingsClientDAO.update(dsDO, sfBaseHandle);
				}
			}
			fdGetSFoAuthHandleService.setSfHandleToNUll();
			if (sfBaseHandle != null) {
				sfBaseHandle.nullify();
			}
			sfBaseHandle = null;
		} catch (SFException e) {
			throw (e);
		}
	}

	public void processStateParam(String stateParam) {
		SFoAuthHandle sfBaseHandle = null;
		if (stateParam.equals("L")) {
			setEnvironment(Constants.LoginEnv);
		} else if (stateParam.equals("T")) {
			setEnvironment(Constants.TestEnv);
		}
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}