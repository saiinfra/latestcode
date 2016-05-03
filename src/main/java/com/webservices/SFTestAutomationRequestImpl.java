package com.webservices;

import java.util.List;

import javax.jws.WebService;

import com.services.ForceDepService;
import com.util.Constants;

import net.sforce.soap._2005._09.outbound.MetadataLogCNotification;
import net.sforce.soap._2005._09.outbound.NotificationPort;
import net.sforce.soap.local.sobject.MetadataLogC;

/**
 * @author root6
 *
 */
@WebService(endpointInterface = "net.sforce.soap._2005._09.outbound.NotificationPort")
public class SFTestAutomationRequestImpl implements NotificationPort {

	public boolean notifications(String organizationId, String actionId,
			String sessionId, String enterpriseUrl, String partnerUrl,
			List<MetadataLogCNotification> notification) {

		String metadataLogId = "";
		String metadataLogAction = "";
		String testcasename = "";
		String testinformation= "";
		String executionresults= "";

		// TODO Auto-generated method stub
		System.out.println("Hello TestAutomation");
		System.out.println("organizationId : " + organizationId);
		System.out.println("actionId : " + actionId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("enterpriseUrl : " + enterpriseUrl);
		MetadataLogC sobject = null;
		List<MetadataLogCNotification> notifications = notification;
		System.out.println("arrSize: " + notification.size());

		int arrSize = notification.size();
		for (int i = 0; i < arrSize; i++) {
			sobject = (MetadataLogC) notification.get(i).getSObject();
			metadataLogId = sobject.getId();
			metadataLogAction = sobject.getActionC().getValue();
			testcasename = sobject.getNameC().getValue();
			testinformation=sobject.getTestInformationC().getValue();
			//executionresults=sobject.getExecutionResultsC().getValue();
			
			System.out.println("Id: " + sobject.getId());
			System.out.println("MetadataLog ID :" + metadataLogId);
			System.out.println("metadataLogAction :" + metadataLogAction);
			System.out.println("testcasename :" + testcasename);
			System.out.println("TestInformation :" + testinformation);
			//System.out.println("ExecutionResults :" + executionresults);

		}

		try {
			ForceDepService deploymentService = new ForceDepService();

			if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& metadataLogAction
							.equals(Constants.TEST_AUTOMATION_ACTION)) {
				deploymentService.executeScript(Constants.userId,
						Constants.passwd, Constants.serverURL, metadataLogId,testcasename);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
}
