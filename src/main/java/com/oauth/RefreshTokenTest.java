package com.oauth;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class RefreshTokenTest {

	@Test
	public void test() throws HttpException, IOException {
		//fail("Not yet implemented");
		String environment="https://emc--EMCPackage.cs45.my.salesforce.com";
		String tokenUrl = environment + "/services/oauth2/token";
		String refreshToken="5Aep861X_GB51979zBhSe9UI5CSwW59V3M652G90ORbBXbNI6nV6j2Qw743rUn0cwU2xXCEtU8zFgiBZqPkOppT";
		String clientId="3MVG9uudbyLbNPZNJeDuIFgdXlLpnv6RKDcjyihBIlGZOi3VQ8uVxIZFOlC.SbZ7JRNJKK88xZrtNwS874njr";
		String clientSecret="8443527437279379269";
		//String redirectUri="https://localhost:8443/SFSOAPWS/OAuthServlet/callback";

		/*String environment="https://emc--SFDC01.cs19.my.salesforce.com";
		String tokenUrl = environment + "/services/oauth2/token";
		String refreshToken="5Aep861Ov3oA7NvELo8PPgyybE8wF3Nkx_uUApUldcklY21dnlbnOfypJshWp.pXk6y1xPjHqHXvQRSnTmOX5cg";
		String clientId="3MVG9uudbyLbNPZNJeDuIFgdXlLe.4i_saKSNrVhGTjrvEPE0TarGJiG5U7yh9xqIrFICJpwpi4EX08BnhGeD";
		String clientSecret="192416265676135474";*/

		
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(tokenUrl);
		//post.addParameter("code", code);
		post.addParameter("grant_type", "refresh_token");
		post.addParameter("client_id", clientId);
		post.addParameter("client_secret", clientSecret);
		post.addParameter("refresh_token", refreshToken);
		HttpConnectionManager conManager = httpclient
				.getHttpConnectionManager();
		httpclient.getHostConfiguration().setProxy(
				"us-east-1-static-hopper.quotaguard.com", 9293);
		HttpState state = new HttpState();
		state.setProxyCredentials(null, null,
				new UsernamePasswordCredentials("quotaguard5120", "f9daf5b7d721"));
		httpclient.setState(state);
		
		httpclient.executeMethod(post);
		try
		{
			JSONObject authResponse = new JSONObject(new JSONTokener(
					new InputStreamReader(post.getResponseBodyAsStream())));
			String accessToken = authResponse.getString("access_token");
			System.out.println(authResponse.toString());
			System.out.println(accessToken);

		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
				
	}
	@Test
	public void getIdDetails()
			throws ServletException, IOException {
		String accessToken="00DS0000003Km6L!AQsAQOmsrJeFb856fPi9VPZix5Mmz4zXco3lSsZAfyQoUkqbORsuv6stkYbUqrPlvkVrJmhxH9hBwo_UIUHIaUPaTj68zNMu";
		String instanceURL="https://emc--OppVis.cs1.my.salesforce.com"; 
		HttpClient httpclient = new HttpClient();
		GetMethod get = new GetMethod("https://test.salesforce.com/id/00DS0000003Km6LMAS/00570000001eJC9AAM");
		// set the token in the header
		get.setRequestHeader("Authorization", "OAuth " + accessToken);

		// set the SOQL as a query param
		NameValuePair[] params = new NameValuePair[1];

		params[0] = new NameValuePair("oauth_token", accessToken);
		get.setQueryString(params);
		// System.out.println("Accessing ID URL---" + get.getURI().toString());
		
			httpclient.executeMethod(get);
			System.out.println(" status - " + get.getStatusCode());
			if (get.getStatusCode() == HttpStatus.SC_OK) {
				// Now lets use the standard java json classes to work with the
				// results
				
					JSONObject jsonResponse = new JSONObject(
							new JSONTokener(new InputStreamReader(
									get.getResponseBodyAsStream())));
					System.out.println("Auth Response: {} "
							+ jsonResponse.toString(2));

					String userName = new String(
							(String) jsonResponse.get("username"));
					String orgId = new String(
							(String) jsonResponse.get("organization_id"));
					String userId = new String(
							(String) jsonResponse.get("user_id"));


			}
	}
}
