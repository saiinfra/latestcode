package com.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.domain.MetaBean;
import com.exception.SFErrorCodes;
import com.exception.SFException;

public class XMLUtil {

	public XMLUtil() {
		super();
	}

	private static String getCurrentPath() {
		try {
			File currentDirectory = new File(new File("").getAbsolutePath());
			return currentDirectory.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean deleteDir(File dir) {
	      if (dir.isDirectory()) {
	         String[] children = dir.list();
	         for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir
	            (new File(dir, children[i]));
	            if (!success) {
	               return false;
	            }
	         }
	      }
	      return dir.delete();
	  }
	public static void doPreProcessing(String metadataLogId) {
		try {
			File zipFile = new File(new File(metadataLogId + "/"
					+ Constants.Component_Zip_FileName).getAbsolutePath());
			
			File folder=new File(metadataLogId);
			
			if(folder.exists())
			{
		   	
			}
			
			if (zipFile.exists()) {
				System.out.println("Path: " + zipFile.getAbsolutePath());
				if (zipFile.delete()) {
					System.out.println(zipFile.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			}
			File packeFile = new File(new File(metadataLogId + "/"
					+ Constants.Package_FileName).getAbsolutePath());
			if (packeFile.exists()) {
				System.out.println("Path: " + packeFile.getAbsolutePath());
				if (packeFile.delete()) {
					System.out.println(packeFile.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			}
		      deleteDir(new File(metadataLogId));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createBulkInsertJobXMLFile(String sObject)
			throws SFException {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc
					.createElementNS(
							"http://www.force.com/2009/06/asyncapi/dataload",
							"jobInfo");
			doc.appendChild(rootElement);

			Element operationEle = doc.createElement("operation");
			operationEle.appendChild(doc.createTextNode("insert"));
			rootElement.appendChild(operationEle);

			Element sObjectEle = doc.createElement("object");
			sObjectEle.appendChild(doc.createTextNode(sObject));
			rootElement.appendChild(sObjectEle);

			Element contentEle = doc.createElement("contentType");
			contentEle.appendChild(doc.createTextNode("XML"));
			rootElement.appendChild(contentEle);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File("objects.xml"));
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (ParserConfigurationException pce) {
			throw new SFException(pce.toString(), SFErrorCodes.XML_Build_Error);
		} catch (TransformerException tfe) {
			throw new SFException(tfe.toString(), SFErrorCodes.XML_Build_Error);
		} catch (Exception tfe) {
			throw new SFException(tfe.toString(), SFErrorCodes.XML_Build_Error);
		}
	}

	public static void createBulkInsertXMLFile(MetaBean[] objects)
			throws SFException {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElementNS(
					"http://www.force.com/2009/06/asyncapi/dataload",
					"sObjects");
			doc.appendChild(rootElement);

			for (int i = 0; i < objects.length; i++) {
				// staff elements
				String objectName = (((MetaBean) objects[i]).getName()).trim();
				String orgId = (((MetaBean) objects[i]).getSourceOrg()).trim();

				Element sObject = doc.createElement("sObject");
				rootElement.appendChild(sObject);

				Element nameEle = doc.createElement("Name");
				nameEle.appendChild(doc.createTextNode(objectName));
				sObject.appendChild(nameEle);

				Element orgIdEle = doc.createElement("Organization_Id");
				orgIdEle.appendChild(doc.createTextNode(orgId));
				sObject.appendChild(orgIdEle);

			}
			Element version = doc.createElement("version");
			version.appendChild(doc.createTextNode(Constants.API_VERSION));
			rootElement.appendChild(version);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File("objects.xml"));
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (ParserConfigurationException pce) {
			throw new SFException(pce.toString(), SFErrorCodes.XML_Build_Error);
		} catch (TransformerException tfe) {
			throw new SFException(tfe.toString(), SFErrorCodes.XML_Build_Error);
		} catch (Exception tfe) {
			throw new SFException(tfe.toString(), SFErrorCodes.XML_Build_Error);
		}
	}

	public static void createDeployXMLFile(List metaBeanList,String metadatataLogId)
			throws SFException {
		try {
			
			 File file = new File(metadatataLogId);
		        if (!file.exists()) {
		            if (file.mkdir()) {
		                System.out.println("Directory is created!");
		            } else {
		                System.out.println("Failed to create directory!");
		            }
		        }
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElementNS(
					"http://soap.sforce.com/2006/04/metadata", "Package");
			doc.appendChild(rootElement);

			for (Iterator iterator = metaBeanList.iterator(); iterator
					.hasNext();) {
				MetaBean metaBean = (MetaBean) iterator.next();

				String beanName = metaBean.getName();


					Element types = doc.createElement("types");
					rootElement.appendChild(types);
					Element members = doc.createElement("members");

					members.appendChild(doc.createTextNode(beanName));
					types.appendChild(members);

					Element name = doc.createElement("name");
					String objType = metaBean.getType();
					name.appendChild(doc.createTextNode(objType));
					types.appendChild(name);
				}
			
			Element version = doc.createElement("version");
			version.appendChild(doc.createTextNode(Constants.API_VERSION));
			rootElement.appendChild(version);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(metadatataLogId+"/"+"package.xml"));
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (ParserConfigurationException pce) {
			throw new SFException(pce.toString(), SFErrorCodes.XML_Build_Error);
		} catch (TransformerException tfe) {
			throw new SFException(tfe.toString(), SFErrorCodes.XML_Build_Error);
		} catch (Exception tfe) {
			throw new SFException(tfe.toString(), SFErrorCodes.XML_Build_Error);
		}
	}

	private static void prepareDir() {
		try {
			boolean f = new File("package/objects").mkdirs();
		} catch (Exception tfe) {
			tfe.printStackTrace();
		}
	}

	private static String getPackageFilePath() {
		try {
			prepareDir();
			String filePath = (new File("")).getCanonicalPath() + "/"
					+ "package";
			return filePath;
		} catch (Exception tfe) {
			tfe.printStackTrace();
		}
		return "";
	}
}
