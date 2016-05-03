package com.webservices;

import java.util.List;

import javax.jws.WebService;

import com.services.ForceDepService;
import com.sforce.soap._2005._09.outbound.NotificationPort;
import com.sforce.soap._2005._09.outbound.OFSServerMetadataLogCNotification;
import com.sforce.soap.local.deploy.sobject.OFSServerMetadataLogC;
import com.util.Constants;

@WebService(endpointInterface = "com.sforce.soap._2005._09.outbound.NotificationPort")
public class SFSoapUpdatePackagesImpl implements NotificationPort {

	public boolean notifications(String organizationId, String actionId,
			String sessionId, String enterpriseUrl, String partnerUrl,
			List<OFSServerMetadataLogCNotification> notification) {

		// TODO Auto-generated method stub
		System.out.println("Hello UpdatePackages");
		System.out.println("organizationId : " + organizationId);
		System.out.println("actionId : " + actionId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("enterpriseUrl : " + enterpriseUrl);
		OFSServerMetadataLogC sobject = null;
		List<OFSServerMetadataLogCNotification> notifications = notification;
		System.out.println("arrSize: " + notification.size());
		String metadataLogId = "";
		String bOrgToken = null;
		String bOrgId = null;
		String bOrgURL = null;
		String sOrgId = null;
		String bOrgRefreshToken = null;

		String action = "";
		String status = "";
		String packageParentId = "";

		int arrSize = notification.size();
		for (int i = 0; i < arrSize; i++) {
			sobject = (OFSServerMetadataLogC) notification.get(i).getSObject();
			metadataLogId = sobject.getId();
			System.out.println("Id: " + sobject.getId());
			sOrgId = organizationId;
			bOrgId = sobject.getOFSServerBaseOrgIdC().getValue();
			bOrgURL = sobject.getOFSServerBaseOrgUrlC().getValue();
			bOrgToken = sobject.getOFSServerBaseOrgTokenC().getValue();
			bOrgRefreshToken = sobject.getOFSServerBaseOrgRefreshTokenC().getValue();
			action = sobject.getOFSServerActionC().getValue();
			status = sobject.getOFSServerStatusC().getValue();
			packageParentId = sobject.getOFSServerIDC().getValue();

			System.out.println("bOrgId : " + bOrgId + "~" + "bOrgURL : "
					+ bOrgURL + "~" + "bOrgToken : " + bOrgToken + "~"
					+ "bOrgRefreshToken :" + bOrgRefreshToken + " ~"
					+ "action: " + action + "~" + "status: " + status + " ~"
					+ "packageParentId: " + packageParentId);
		}
		try {
			ForceDepService deploymentService = new ForceDepService();
			if ((action != null && action.equals(Constants.ACTION_UPDATEPACKAGES))
					&& (metadataLogId != null && !metadataLogId.isEmpty())) {
				deploymentService.updatePackages(bOrgId, bOrgToken, bOrgURL,
						bOrgRefreshToken, metadataLogId, status,
						packageParentId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
