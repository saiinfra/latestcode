package com.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.util.Constants;

public class LoadSFConfigurations {

	String serverUsername;
	String serverPassword;
	String serverURL;
	String clientUsername;
	String clientPassword;
	String clientURL;
	String ikhanUsername;
	String ikhanPassword;
	String ikhanURL;

	public LoadSFConfigurations() {
		super();
		loadSFConfigs();
	}

	public void loadSFConfigs() {

		Properties prop = new Properties();
		OutputStream output = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();

			File file = new File(classLoader.getResource(
					"sf-resource/" + Constants.SF_Config_File).getFile());

			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				
				if (key.equals("server_username")) {
					setServerUsername(value);
				}
				if (key.equals("server_password")) {
					setServerPassword(value);	
				}
				if (key.equals("server_URL")) {
					setServerURL(value);
				}
				if (key.equals("client_username")) {
					setClientUsername(value);
				}
				if (key.equals("client_password")) {
					setClientPassword(value);
				}
				if (key.equals("client_URL")) {
					setClientURL(value);
				}
				if (key.equals("ikhan_username")) {
					setIkhanUsername(value);
				}
				if (key.equals("ikhan_password")) {
					setIkhanPassword(value);
				}
				if (key.equals("ikhan_URL")) {
					setIkhanURL(value);
				}
			}
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public String getServerUsername() {
		return serverUsername;
	}

	public void setServerUsername(String serverUsername) {
		this.serverUsername = serverUsername;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientURL() {
		return clientURL;
	}

	public void setClientURL(String clientURL) {
		this.clientURL = clientURL;
	}

	public String getIkhanUsername() {
		return ikhanUsername;
	}

	public void setIkhanUsername(String ikhanUsername) {
		this.ikhanUsername = ikhanUsername;
	}

	public String getIkhanPassword() {
		return ikhanPassword;
	}

	public void setIkhanPassword(String ikhanPassword) {
		this.ikhanPassword = ikhanPassword;
	}

	public String getIkhanURL() {
		return ikhanURL;
	}

	public void setIkhanURL(String ikhanURL) {
		this.ikhanURL = ikhanURL;
	}

	
}