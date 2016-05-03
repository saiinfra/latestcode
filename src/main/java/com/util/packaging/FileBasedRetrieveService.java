package com.util.packaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.metadata.AsyncResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.PackageTypeMembers;
import com.sforce.soap.metadata.RetrieveMessage;
import com.sforce.soap.metadata.RetrieveRequest;
import com.sforce.soap.metadata.RetrieveResult;
import com.sforce.soap.metadata.RetrieveStatus;
import com.util.Constants;
import com.util.SFoAuthHandle;

/**
 * Sample that logs in and shows a menu of retrieve and deploy metadata options.
 */
public class FileBasedRetrieveService {

	// one second in milliseconds
	private static final long ONE_SECOND = 1000;

	// maximum number of attempts to deploy the zip file
	private static final int MAX_NUM_POLL_REQUESTS = 50;

	private BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));

	public FileBasedRetrieveService() {
		super();
	}

	public void retrieve(SFoAuthHandle sfHandle, String packageName) throws SFException {
		RetrieveRequest retrieveRequest = new RetrieveRequest();
		// The version in package.xml overrides the version in RetrieveRequest
		retrieveRequest.setApiVersion(new Double(Constants.API_VERSION)
				.doubleValue());
		setUnpackaged(retrieveRequest);
		AsyncResult asyncResult = null;
		MetadataConnection conn = null;
		try {
			conn = sfHandle.getMetadataConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.toString());
			throw new SFException("Error related to "+packageName+" Details: "+e.toString(), SFErrorCodes.Metadata_Conn_Error);
		}
		
		if (conn != null) {
			System.out.println("session Id: "
					+ conn.getSessionHeader().getSessionId());
		}
		try {
			asyncResult = conn.retrieve(retrieveRequest);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.toString());
			throw new SFException("Error related to "+packageName+" Details: "+e.toString(), SFErrorCodes.FileRetrieve_Error);
		}
		RetrieveResult result = waitForRetrieveCompletion(asyncResult, sfHandle);

		if (result.getStatus() == RetrieveStatus.Failed) {
			System.out.println(result.getErrorStatusCode() + " msg: "
					+ result.getErrorMessage());
			throw new SFException("Error related to "+packageName+" Details: "+result.getErrorStatusCode() + " msg: "
					+ result.getErrorMessage(), SFErrorCodes.FileRetrieve_Error);
		} else if (result.getStatus() == RetrieveStatus.Succeeded) {
			// Print out any warning messages
			StringBuilder stringBuilder = new StringBuilder();
			if (result.getMessages() != null) {
				for (RetrieveMessage rm : result.getMessages()) {
					stringBuilder.append(rm.getFileName() + " - "
							+ rm.getProblem() + "\n");
				}
			}
			if (stringBuilder.length() > 0) {
				System.out.println("Retrieve warnings:\n" + stringBuilder);
			}
			System.out.println("Writing results to zip file");
			FileOutputStream os = null;
			try {
				File resultsFile = new File(Constants.Component_Zip_FileName);
				os = new FileOutputStream(resultsFile);
				os.write(result.getZipFile());
			} catch(Exception e){
				throw new SFException("Error related to "+packageName+" Details: "+e.toString(), SFErrorCodes.File_Error);
			}
			finally {
				try {
					os.close();
				} catch(Exception e){
					throw new SFException("Error related to "+packageName+" Details: "+e.toString(), SFErrorCodes.File_Error);
				}
				
			}
		}
	}

	private RetrieveResult waitForRetrieveCompletion(AsyncResult asyncResult,
			SFoAuthHandle sfHandle) throws SFException {
		// Wait for the retrieve to complete
		int poll = 0;
		long waitTimeMilliSecs = ONE_SECOND;
		String asyncResultId = asyncResult.getId();
		RetrieveResult result = null;
		try {
			do {
				Thread.sleep(waitTimeMilliSecs);
				// Double the wait time for the next iteration
				waitTimeMilliSecs *= 2;
				if (poll++ > MAX_NUM_POLL_REQUESTS) {
					throw new SFException(
							"Request timed out.  If this is a large set "
									+ "of metadata components, check that the time allowed "
									+ "by MAX_NUM_POLL_REQUESTS is sufficient.",
							SFErrorCodes.FileRetrieve_Request_timed_out_Error);
					/*
					 * throw new Exception(
					 * "Request timed out.  If this is a large set " +
					 * "of metadata components, check that the time allowed " +
					 * "by MAX_NUM_POLL_REQUESTS is sufficient.");
					 */
				}
				result = sfHandle.getMetadataConnection().checkRetrieveStatus(
						asyncResultId,true);
				System.out.println("Retrieve Status: " + result.getStatus());
			} while (!result.isDone());
		} catch (Exception e) {
			throw new SFException("Request timed out.  If this is a large set "
					+ "of metadata components, check that the time allowed "
					+ "by MAX_NUM_POLL_REQUESTS is sufficient.",
					SFErrorCodes.FileRetrieve_Request_timed_out_Error);
		}
		return result;
	}

	private void setUnpackaged(RetrieveRequest request) throws SFException {
		// Edit the path, if necessary, if your package.xml file is located
		// elsewhere
		File unpackedManifest = new File(Constants.Package_FileName);
		try {
			System.out.println("Manifest file: "
					+ unpackedManifest.getAbsolutePath());

			if (!unpackedManifest.exists() || !unpackedManifest.isFile()) {
				throw new SFException(
						"Should provide a valid retrieve manifest "
								+ "for unpackaged content. Looking for "
								+ unpackedManifest.getAbsolutePath(),
						SFErrorCodes.FileRetrieve_Package_Error);

			}

			// Note that we use the fully quualified class name because // of a
			// collision with the java.lang.Package class
			com.sforce.soap.metadata.Package p = parsePackageManifest(unpackedManifest);
			request.setUnpackaged(p);
		} catch (Exception e) {
			throw new SFException("Should provide a valid retrieve manifest "
					+ "for unpackaged content. Looking for "
					+ unpackedManifest.getAbsolutePath(),
					SFErrorCodes.FileRetrieve_Package_Error);
		}
	}

	private com.sforce.soap.metadata.Package parsePackageManifest(File file)
			throws ParserConfigurationException, IOException, SAXException {
		com.sforce.soap.metadata.Package packageManifest = null;
		List<PackageTypeMembers> listPackageTypes = new ArrayList<PackageTypeMembers>();
		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputStream inputStream = new FileInputStream(file);
		Element d = db.parse(inputStream).getDocumentElement();
		for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
			if (c instanceof Element) {
				Element ce = (Element) c;
				NodeList nodeList = ce.getElementsByTagName("name");
				if (nodeList.getLength() == 0) {
					continue;
				}
				String name = nodeList.item(0).getTextContent();
				NodeList m = ce.getElementsByTagName("members");
				List<String> members = new ArrayList<String>();
				for (int i = 0; i < m.getLength(); i++) {
					Node mm = m.item(i);
					members.add(mm.getTextContent());
				}
				PackageTypeMembers packageTypes = new PackageTypeMembers();
				packageTypes.setName(name);
				packageTypes.setMembers(members.toArray(new String[members
						.size()]));
				listPackageTypes.add(packageTypes);
			}
		}
		packageManifest = new com.sforce.soap.metadata.Package();
		PackageTypeMembers[] packageTypesArray = new PackageTypeMembers[listPackageTypes
				.size()];
		packageManifest.setTypes(listPackageTypes.toArray(packageTypesArray));
		packageManifest.setVersion(Constants.API_VERSION + "");
		return packageManifest;
	}
}