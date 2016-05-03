package com.services.component;

import com.domain.TestMetadataLogDO;
import com.services.application.RDAppService;
import com.util.Constants;
import com.util.SFoAuthHandle;

public class FDExecuteScriptCompService {

	public FDExecuteScriptCompService() {
		super();
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * executeScript(Constants.userId, Constants.passwd, Constants.serverURL,
	 * "a0161000002rayc"); }
	 */

	public void executeScript(String userId, String passwd, String serverURL,
			String metadataLogId) {

		TestMetadataLogDO testMetadataLogDO = null;

		SFoAuthHandle sfHandle = new SFoAuthHandle(userId, passwd, serverURL,
				"");

		try {

			// Get Meta data Log details
			testMetadataLogDO = RDAppService.findTestMetadataLog(metadataLogId,
					sfHandle);

			String message = "successfully testcase Processed";
			// updating metadataLog to Completing state
			RDAppService.updateTestMetadataLogStatus(testMetadataLogDO,
					Constants.COMPLETED_STATUS, message, sfHandle);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
