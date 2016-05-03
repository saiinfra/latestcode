package com.util.packaging;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.sforce.soap.metadata.AsyncResult;
import com.sforce.soap.metadata.CodeCoverageWarning;
import com.sforce.soap.metadata.DeployDetails;
import com.sforce.soap.metadata.DeployMessage;
import com.sforce.soap.metadata.DeployOptions;
import com.sforce.soap.metadata.DeployResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.RunTestFailure;
import com.sforce.soap.metadata.RunTestsResult;
import com.sforce.ws.ConnectionException;
import com.util.Constants;
import com.util.SFoAuthHandle;

/**
 * Sample that logs in and shows a menu of retrieve and deploy metadata options.
 */
public class FileBasedDeployService {

	// one second in milliseconds
	private static final long ONE_SECOND = 1000;

	// maximum number of attempts to deploy the zip file
	private static final int MAX_NUM_POLL_REQUESTS = 50;

	private BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));

	public FileBasedDeployService() {
		super();
	}

	public void deploy(SFoAuthHandle sfHandle, String packageName) throws SFException {
		byte zipBytes[] = readZipFile();
		DeployOptions deployOptions = new DeployOptions();
		deployOptions.setPerformRetrieve(false);
		deployOptions.setRollbackOnError(true);
		MetadataConnection conn = null;
		AsyncResult asyncResult = null;
		try {
			conn = sfHandle.getMetadataConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.toString());
			throw new SFException("Error related to "+packageName+" Details: "+e.toString(),
					SFErrorCodes.Metadata_Conn_Error);
		}
		try {
			conn = sfHandle.getMetadataConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.toString());
			throw new SFException("Error related to "+packageName+" Details: "+e.toString(),
					SFErrorCodes.Metadata_Conn_Error);
		}
		try {
			asyncResult = conn.deploy(zipBytes, deployOptions);
		} catch (ConnectionException e) {
			// e.printStackTrace();
			System.out.println(e.toString());
			throw new SFException("Error related to "+packageName+" Details: "+e.toString(), SFErrorCodes.SF_Conn_Error);
		}

		DeployResult result = waitForDeployCompletion(sfHandle,asyncResult.getId());
		if (!result.isSuccess()) {
			String errors = printErrors(result, "Final list of failures:\n");
			throw new SFException("Error related to "+packageName+" Details: "+errors, SFErrorCodes.FileDeploy_Error);
			// throw new Exception("The files were not successfully deployed");
		}
		System.out.println("The file " + Constants.Component_Zip_FileName
				+ " was successfully deployed\n");
	}

	/*
	 * Read the zip file contents into a byte array.
	 */private byte[] readZipFile() throws SFException {
		byte[] result = null;
		// We assume here that you have a deploy.zip file. // See the retrieve
		// sample for how to retrieve a zip file.
		File zipFile = new File(Constants.Component_Zip_FileName);
		if (!zipFile.exists() || !zipFile.isFile()) {
			throw new SFException(
					"Cannot find the zip file for deploy() on path:"
							+ zipFile.getAbsolutePath(),
					SFErrorCodes.File_Error);
		}
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(zipFile);
		} catch (Exception e) {
			throw new SFException(e.toString(), SFErrorCodes.File_Error);
		}
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = 0;
			while (-1 != (bytesRead = fileInputStream.read(buffer))) {
				bos.write(buffer, 0, bytesRead);
			}

			result = bos.toByteArray();
		} catch (Exception e) {
			throw new SFException(e.toString(), SFErrorCodes.File_Error);
		} finally {
			try {
				fileInputStream.close();
			} catch (Exception e) {
				throw new SFException(e.toString(), SFErrorCodes.File_Error);
			}
		}
		return result;
	}

	/*
	 * Print out any errors, if any, related to the deploy.
	 * 
	 * @param result - DeployResult
	 */
	private String printErrors(DeployResult result, String messageHeader) {
		DeployDetails details = result.getDetails();
		StringBuilder stringBuilder = new StringBuilder();
		if (details != null) {
			DeployMessage[] componentFailures = details.getComponentFailures();
			for (DeployMessage failure : componentFailures) {
				String loc = "(" + failure.getLineNumber() + ", "
						+ failure.getColumnNumber();
				if (loc.length() == 0
						&& !failure.getFileName().equals(failure.getFullName())) {
					loc = "(" + failure.getFullName() + ")";
				}
				stringBuilder.append(
						failure.getFileName() + loc + ":"
								+ failure.getProblem()).append('\n');
			}
			RunTestsResult rtr = details.getRunTestResult();
			if (rtr.getFailures() != null) {
				for (RunTestFailure failure : rtr.getFailures()) {
					String n = (failure.getNamespace() == null ? "" : (failure
							.getNamespace() + ".")) + failure.getName();
					stringBuilder.append("Test failure, method: " + n + "."
							+ failure.getMethodName() + " -- "
							+ failure.getMessage() + " stack "
							+ failure.getStackTrace() + "\n\n");
				}
			}
			if (rtr.getCodeCoverageWarnings() != null) {
				for (CodeCoverageWarning ccw : rtr.getCodeCoverageWarnings()) {
					stringBuilder.append("Code coverage issue");
					if (ccw.getName() != null) {
						String n = (ccw.getNamespace() == null ? "" : (ccw
								.getNamespace() + ".")) + ccw.getName();
						stringBuilder.append(", class: " + n);
					}
					stringBuilder.append(" -- " + ccw.getMessage() + "\n");
				}
			}
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.insert(0, messageHeader);
			System.out.println(stringBuilder.toString());
		}
		return stringBuilder.toString();
	}

	private DeployResult waitForDeployCompletion(SFoAuthHandle sfHandle, String asyncResultId)
			throws SFException {
		int poll = 0;
		long waitTimeMilliSecs = ONE_SECOND;
		DeployResult deployResult;
		boolean fetchDetails;
		MetadataConnection conn = null;
		try {
			conn = sfHandle.getMetadataConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.toString());
			throw new SFException(e.toString(),
					SFErrorCodes.Metadata_Conn_Error);
		}
		try {
			do {
				Thread.sleep(waitTimeMilliSecs);
				// double the wait time for the next iteration

				waitTimeMilliSecs *= 2;
				if (poll++ > MAX_NUM_POLL_REQUESTS) {
					throw new SFException(
							"Request timed out. If this is a large set of metadata components, "
									+ "ensure that MAX_NUM_POLL_REQUESTS is sufficient.",
							SFErrorCodes.FileDeploy_Request_timed_out_Error);
				}
				// Fetch in-progress details once for every 3 polls
				fetchDetails = (poll % 3 == 0);

				deployResult = conn.checkDeployStatus(
						asyncResultId, fetchDetails);
				System.out.println("Status is: " + deployResult.getStatus());
				if (!deployResult.isDone() && fetchDetails) {
					printErrors(deployResult,
							"Failures for deployment in progress:\n");
				}
			} while (!deployResult.isDone());
		} catch (Exception e) {
			throw new SFException(
					"Request timed out. If this is a large set of metadata components, "
							+ "ensure that MAX_NUM_POLL_REQUESTS is sufficient.",
					SFErrorCodes.FileDeploy_Request_timed_out_Error);
		}
		try {
			if (!deployResult.isSuccess()
					&& deployResult.getErrorStatusCode() != null) {
				throw new SFException(deployResult.getErrorStatusCode()
						+ " msg: " + deployResult.getErrorMessage(),SFErrorCodes.FileDeploy_Error );
			}
		} catch (Exception e) {
			throw new SFException(
					"Request timed out. If this is a large set of metadata components, "
							+ "ensure that MAX_NUM_POLL_REQUESTS is sufficient.",
					SFErrorCodes.FileDeploy_Request_timed_out_Error);
		}
		if (!fetchDetails) {
			// Get the final result with details if we didn't do it in the last
			// attempt.
			try {
				deployResult = conn.checkDeployStatus(asyncResultId,
						true);
			} catch (ConnectionException e) {
				// e.printStackTrace();
				System.out.println(e.toString());
				throw new SFException(e.toString(), SFErrorCodes.SF_Conn_Error);
			}
		}
		return deployResult;
	}
}