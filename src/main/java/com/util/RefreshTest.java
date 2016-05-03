package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RefreshTest {

	public static void main(String[] args) throws IOException {
		InputStream input;
		try {
			input = new FileInputStream(AppUtil.getCurrentPath()
					+ "/WebContent/WEB-INF/properties/config.properties");
			Properties p = new Properties();

			p.load(input);
			System.out.println(p.getProperty("baseclientclientId"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(AppUtil.getCurrentPath()
				+ "/WebContent/WEB-INF/properties/config.properties");

	}

}
