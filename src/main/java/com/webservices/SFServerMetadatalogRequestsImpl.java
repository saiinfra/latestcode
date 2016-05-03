package com.webservices;

import java.util.List;

import javax.jws.WebService;

import com.services.ForceDepService;
import com.sforce.soap._2005._09.outbound.NotificationPort;
import com.sforce.soap._2005._09.outbound.OFSServerMetadataLogCNotification;
import com.sforce.soap.local.deploy.sobject.OFSServerMetadataLogC;
import com.util.Constants;

@WebService(endpointInterface = "com.sforce.soap._2005._09.outbound.NotificationPort")
public class SFServerMetadatalogRequestsImpl implements NotificationPort {

	public boolean notifications(String organizationId, String actionId,
			String sessionId, String enterpriseUrl, String partnerUrl,
			List<OFSServerMetadataLogCNotification> notification) {

	
		String metadataLogId = "";

		// TODO Auto-generated method stub
		System.out.println("Hello Release");
		System.out.println("organizationId : " + organizationId);
		System.out.println("actionId : " + actionId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("enterpriseUrl : " + enterpriseUrl);
		OFSServerMetadataLogC sobject = null;
		List<OFSServerMetadataLogCNotification> notifications = notification;
		System.out.println("arrSize: " + notification.size());
		String bOrgToken = null;
		String bOrgId = null;
		String bOrgURL = null;
		String bOrgRefreshToken = null;
		
		String releaseName = "";
		String releaseId = "";
		String releaseStatus = "";
		String packageAction = "";
		String status = "";
		String packageParentId = "";

		int arrSize = notification.size();

		for (int i = 0; i < arrSize; i++) {
			sobject = (OFSServerMetadataLogC) notification.get(i).getSObject();
			metadataLogId = sobject.getId();
			System.out.println("sObject Id: " + sobject.getId());
			bOrgId = sobject.getOFSServerBaseOrgIdC().getValue();
			bOrgURL = sobject.getOFSServerBaseOrgUrlC().getValue();
			bOrgToken = sobject.getOFSServerBaseOrgTokenC().getValue();
			bOrgRefreshToken = sobject.getOFSServerBaseOrgRefreshTokenC().getValue();
			packageAction = sobject.getOFSServerActionC().getValue();
			System.out.println("bOrgId: " + bOrgId + "~bOrgURL: " + bOrgURL
					+ "~bOrgToken: " + bOrgToken + "~bOrgRefreshToken: "
					+ bOrgRefreshToken + "~Action: " + packageAction);
		}

		try {
			ForceDepService deploymentService = new ForceDepService();

			// Activate Release in target environments
			if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& packageAction.equals(Constants.RELEASE_STATUS_ACTIVE)) {
				System.out.println("Initiate/Create Release in target Env");
				releaseName = sobject.getOFSServerNameC().getValue();
				releaseId = sobject.getOFSServerIDC().getValue();
				releaseStatus = sobject.getOFSServerStatusC().getValue();

				System.out.println("releaseName: " + releaseName
						+ "~releaseId: " + releaseId + "~status: "
						+ releaseStatus);
				deploymentService.release(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, releaseId, releaseName,
						releaseStatus, metadataLogId);
				return true;
			}

			// releaseSync - with getPackages
			else if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& packageAction.equals(Constants.ACTION_GETPACKAGES)) {
				System.out.println("GetPackages");
				releaseName = sobject.getOFSServerNameC().getValue();
				releaseId = sobject.getOFSServerIDC().getValue();
				releaseStatus = sobject.getOFSServerStatusC().getValue();

				System.out.println("releaseName: " + releaseName
						+ "~releaseId: " + releaseId + "~status: "
						+ releaseStatus);
				deploymentService.getPackages(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, releaseId, releaseName,
						releaseStatus, metadataLogId);
				return true;
			}
			// releseSync - with updatePackages
			else if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& packageAction.equals(Constants.ACTION_UPDATEPACKAGES)) {
				System.out.println("UpdatePackages");
				status = sobject.getOFSServerStatusC().getValue();
				packageParentId = sobject.getOFSServerIDC().getValue();
				System.out.println("status: " + status + " ~"
						+ "packageParentId: " + packageParentId);
				deploymentService.updatePackages(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, metadataLogId, status,
						packageParentId);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
