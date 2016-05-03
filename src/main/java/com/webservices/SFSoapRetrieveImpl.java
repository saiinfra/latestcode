package com.webservices;

import java.util.List;

import javax.jws.WebService;

import com.services.ForceDepService;
import com.sforce.soap._2005._09.outbound.NotificationPort;
import com.sforce.soap._2005._09.outbound.OFSServerMetadataLogCNotification;
import com.sforce.soap.local.deploy.sobject.OFSServerMetadataLogC;

/**
 * 
 * @author SFSoapRetrieveImpl is Used For Getting Request For Retrieval From
 *         Salesforce
 *
 */
@WebService(endpointInterface = "com.sforce.soap._2005._09.outbound.NotificationPort")
public class SFSoapRetrieveImpl implements NotificationPort {

	public boolean notifications(String organizationId, String actionId,
			String sessionId, String enterpriseUrl, String partnerUrl,
			List<OFSServerMetadataLogCNotification> notification) {

		String metadataLogId = null;
		String bOrgToken = null;
		String bOrgId = null;
		String bOrgURL = null;
		String sOrgId = null;
		String bOrgRefreshToken = null;
		OFSServerMetadataLogC sobject = null;

		System.out.println("Hello Retrieve");
		System.out.println("organizationId : " + organizationId);
		System.out.println("actionId : " + actionId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("enterpriseUrl : " + enterpriseUrl);
		System.out.println("arrSize: " + notification.size());

		int arrSize = notification.size();
		for (int i = 0; i < arrSize; i++) {
			sobject = (OFSServerMetadataLogC) notification.get(i).getSObject();
			metadataLogId = sobject.getId();
			System.out.println("Id: " + sobject.getId());
			sOrgId = organizationId;
			bOrgId = sobject.getOFSServerBaseOrgIdC().getValue();
			bOrgURL = sobject.getOFSServerBaseOrgUrlC().getValue();
			bOrgToken = sobject.getOFSServerBaseOrgTokenC().getValue();
			bOrgRefreshToken = sobject.getOFSServerBaseOrgRefreshTokenC()
					.getValue();

			System.out.println("OrganizationId__c: "
					+ sobject.getOFSServerOrganizationIdC().getValue());
			System.out.println("RecordId__c: "
					+ sobject.getOFSServerRecordIdC().getValue());
			System.out.println("bOrgId : " + bOrgId + "~" + "bOrgURL : "
					+ bOrgURL + "~" + "bOrgToken : " + bOrgToken + "~"
					+ "bOrgRefreshToken :" + bOrgRefreshToken);
		}
		try {
			ForceDepService deploymentService = new ForceDepService();
			if ((sOrgId != null && !sOrgId.isEmpty())
					&& (metadataLogId != null && !metadataLogId.isEmpty())) {
				deploymentService.retrieve(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, metadataLogId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
