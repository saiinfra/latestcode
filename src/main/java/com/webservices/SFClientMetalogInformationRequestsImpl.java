package com.webservices;

import java.util.List;

import javax.jws.WebService;

import org.sforce.soap._2005._09.outbound.NotificationPort;
import org.sforce.soap._2005._09.outbound.OFSClientMetadataLogInformationCNotification;
import org.sforce.soap.local.sobject.OFSClientMetadataLogInformationC;

import com.services.ForceDepService;
import com.util.Constants;

/**
 * @author root6
 *
 */
@WebService(endpointInterface = "org.sforce.soap._2005._09.outbound.NotificationPort")
public class SFClientMetalogInformationRequestsImpl implements NotificationPort {

	public boolean notifications(String organizationId, String actionId,
			String sessionId, String enterpriseUrl, String partnerUrl,
			List<OFSClientMetadataLogInformationCNotification> notification) {

		String orgId = organizationId;
		String actId = actionId;
		String sId = sessionId;
		String eUrl = enterpriseUrl;
		String pUrl = partnerUrl;
		String metadataLogId = "";

		// TODO Auto-generated method stub
		System.out.println("Hello Retrieve");
		System.out.println("organizationId : " + organizationId);
		System.out.println("actionId : " + actionId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("enterpriseUrl : " + enterpriseUrl);
		OFSClientMetadataLogInformationC sobject = null;
		List<OFSClientMetadataLogInformationCNotification> notifications = notification;
		System.out.println("arrSize: " + notification.size());
		String bOrgToken = null;
		String bOrgId = null;
		String bOrgURL = null;
		String bOrgRefreshToken = null;
		String sOrgId = null;
		String sOrgURL = null;
		String sOrgToken = null;
		String sOrgRefreshToken = null;
		String tOrgId = null;
		String tOrgURL = null;
		String tOrgToken = null;
		String tOrgRefreshToken = null;
		String status = "";
		String pkgId = "";
		String metadataLogAction = "";

		int arrSize = notification.size();
		for (int i = 0; i < arrSize; i++) {
			sobject = (OFSClientMetadataLogInformationC) notification.get(i)
					.getSObject();
			metadataLogId = sobject.getId();
			System.out.println("Id: " + sobject.getId());
			bOrgId = sobject.getOFSClientBaseOrgIdC().getValue();
			bOrgURL = sobject.getOFSClientBaseOrgUrlC().getValue();
			bOrgToken = sobject.getOFSClientBaseOrgTokenC().getValue();
			bOrgRefreshToken = sobject.getOFSClientBaseOrgRefreshTokenC()
					.getValue();
			metadataLogAction = sobject.getOFSClientActionC().getValue();
			System.out.println("bOrgId: " + bOrgId + "~bOrgURL: " + bOrgURL
					+ "~bOrgToken: " + bOrgToken + "~bOrgRefreshToken: "
					+ bOrgRefreshToken + "~Action: " + metadataLogAction);

		}
		try {
			ForceDepService deploymentService = new ForceDepService();

			if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& metadataLogAction.equals(Constants.ACTION_RETRIEVE)) {
				deploymentService.retrieveClient(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, metadataLogId);
				return true;
			} else if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& metadataLogAction.equals(Constants.SUBMIT_FOR_APPROVAL)) {

				System.out.println("Commit");
				sOrgId = sobject.getOFSClientOrganizationIdC().getValue();
				System.out.println("Commit1");
				sOrgURL = sobject.getOFSClientSourceOrganizationURLC()
						.getValue();
				System.out.println("Commit2");
				sOrgToken = sobject.getOFSClientSourceOrgTokenC().getValue();
				System.out.println("Commit3");
				sOrgRefreshToken = sobject.getOFSClientSourceOrgRefreshTokenC()
						.getValue();
				System.out.println("Commit4");
				System.out.println("sOrgId: " + sOrgId + " ~" + "sOrgURL: "
						+ sOrgURL + " ~" + "sOrgToken: " + sOrgToken + " ~"
						+ "sOrgRefreshToken: " + sOrgRefreshToken);

				tOrgId = "";
				tOrgURL = "";
				tOrgToken = sobject.getOFSClientTargetOrgTokenC().getValue();
				tOrgRefreshToken = sobject.getOFSClientTargetOrgRefreshTokenC()
						.getValue();

				status = sobject.getOFSClientStatusC().getValue();
				pkgId = sobject.getOFSClientIDC().getValue();
				System.out.println("status: " + status + " ~" + "packageId: "
						+ pkgId);

				deploymentService.submitForApproval(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, sOrgId, sOrgToken, sOrgURL,
						sOrgRefreshToken, status, pkgId, metadataLogId);

				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
