package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Test {

	public static void main(String[] args) {

		try {
			ZipFile zf = new ZipFile("components.zip");
			Enumeration entries = zf.entries();

			while (entries.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) entries.nextElement();
				String extension = ze.getName().substring(
						ze.getName().lastIndexOf(".") + 1,
						ze.getName().length());
				if (extension.equals("cls")
						|| !ze.getName().equals("unpackaged/package.xml")) {
					System.out.println("Read " + ze.getName());

					File file = new File(ze.getName());

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					System.out.println("Done");

					long size = ze.getSize();
					if (size > 0) {
						System.out.println("Length is " + size);
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						BufferedReader br = new BufferedReader(
								new InputStreamReader(zf.getInputStream(ze)));
						String line;
						while ((line = br.readLine()) != null) {
							bw.write(line);
							System.out.println(line);
						}
						br.close();
						bw.close();

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
