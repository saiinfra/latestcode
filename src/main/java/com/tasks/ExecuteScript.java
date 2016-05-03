package com.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.component.FDExecuteScriptCompService;

public class ExecuteScript implements Runnable {

	String orgId;
	String token;
	String serverURL;
	String refreshToken;
	String metadataLogId;
	String orgType;
	String userId;
	String passwd;
	String testcasename;

	public ExecuteScript(String orgId, String token, String serverURL,
			String refreshToken, String orgType, String metadataLogId,String testcasename) {
		this.metadataLogId = metadataLogId;
		this.orgId = orgId;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serverURL = serverURL;
		this.orgType = orgType;
		this.testcasename=testcasename;
	}

	public ExecuteScript(String userId, String passwd, String serverURL,
			String metadataLogId,String testcasename) {

		this.userId = userId;
		this.passwd = passwd;
		this.metadataLogId = metadataLogId;
		this.serverURL = serverURL;
		this.testcasename=testcasename;

	}

	@Override
	public void run() {
		String errors = null;
		boolean errorFlag = false;
		try {
			FDExecuteScriptCompService executeScriptCompService = new FDExecuteScriptCompService();
			
			//String idURL = "http://52.24.127.217:8080/job/TestFramework/buildWithParameters?token=testcasename&testparam=TestJunit";
			
			String idURL = "http://52.34.213.161:8080/job/TestFramework/buildWithParameters";

			System.out.println(getMetadataLogId());
			HttpClient httpclient = new HttpClient();
			String testparam = getTestcasename()+"~"+getMetadataLogId()+"~"+getUserId()+"~"+getPasswd()+"~"+getServerURL();

			PostMethod get = new PostMethod(idURL);
			// set the token in the header
			//get.setRequestHeader("Authorization", "OAuth " + accessToken);

			// set the SOQL as a query param
			NameValuePair[] params = new NameValuePair[2];

			params[0] = new NameValuePair("token", "testcasename");
			params[1] = new NameValuePair("testparam", testparam);
			get.setQueryString(params);
			System.out.println("Accessing ID URL---" + get.getURI().toString());
			try {
				httpclient.executeMethod(get);
				System.out.println(" status - " + get.getStatusCode());
			} catch (Exception e) {
				throw new SFException(e.getMessage(), SFErrorCodes.SF_HTTP_Error);
			}
		/*	executeScriptCompService.executeScript(getUserId(), getPasswd(),
					getServerURL(), getMetadataLogId());*/
		} catch (Exception e) {
			errorFlag = true;
			StringWriter lerrors = new StringWriter();
			e.printStackTrace(new PrintWriter(lerrors));
			errors = lerrors.toString();
		} finally {
			if (errorFlag) {
				System.out.println("Execute Operation Complete for requestId: "
						+ getMetadataLogId() + "\nWith Errors: " + errors);
			} else {
				System.out.println("Execute Operation Complete for requestId: "
						+ getMetadataLogId());
			}
		}
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getMetadataLogId() {
		return metadataLogId;
	}

	public void setMetadataLogId(String metadataLogId) {
		this.metadataLogId = metadataLogId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTestcasename() {
		return testcasename;
	}

	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}
	
	
}