package com.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.domain.MultiPleDeploymentDO;
import com.services.ForceDepService;
import com.sforce.soap._2005._09.outbound.OFSServerMetadataLogCNotification;
import com.sforce.soap._2005._09.outbound.NotificationPort;
import com.sforce.soap.local.deploy.sobject.OFSServerMetadataLogC;

/**
 * 
 * @author SFSoapDeployImpl is Used For Getting Request For Deploy From
 *         SalesForce
 *
 */
@WebService(endpointInterface = "com.sforce.soap._2005._09.outbound.NotificationPort")
public class SFSoapDeployImpl implements NotificationPort {

	public boolean notifications(String organizationId, String actionId, String sessionId, String enterpriseUrl,
			String partnerUrl, List<OFSServerMetadataLogCNotification> notification) {

		String metadataLogId = "";

		System.out.println("Hello deploy");
		System.out.println("organizationId : " + organizationId);
		System.out.println("actionId : " + actionId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("enterpriseUrl : " + enterpriseUrl);
		System.out.println("partnerUrl : " + partnerUrl);
		OFSServerMetadataLogC sobject = null;

		String bOrgToken = null;
		String bOrgId = null;
		String bOrgURL = null;
		String tOrgId = null;
		String validationId = null;

		String asversionControl = null;
		String gitServerURL = "";

		String bOrgRefreshToken = null;
		int arrSize = notification.size();
		String action = null;
		List<MultiPleDeploymentDO> multiPleDeploymentDOs = new ArrayList<MultiPleDeploymentDO>();
		System.out.println("Arrasize : " + arrSize);
		for (int i = 0; i < arrSize; i++) {
			MultiPleDeploymentDO multiPleDeploymentDO;
			sobject = (OFSServerMetadataLogC) notification.get(i).getSObject();
			metadataLogId = sobject.getId();
			System.out.println("metadataLogId: " + metadataLogId);
			bOrgId = sobject.getOFSServerBaseOrgIdC().getValue();
			bOrgURL = sobject.getOFSServerBaseOrgUrlC().getValue();
			bOrgToken = sobject.getOFSServerBaseOrgTokenC().getValue();
			tOrgId = sobject.getOFSServerOrganizationIdC().getValue();
			bOrgRefreshToken = sobject.getOFSServerBaseOrgRefreshTokenC().getValue();
			action = sobject.getOFSServerActionC().getValue();
			if (action.equals("QuickDeploy")) {
				validationId = sobject.getOFSServerRecordIdC().getValue();
				System.out.println("RecentValidation ID" + validationId);

				multiPleDeploymentDO = new MultiPleDeploymentDO(metadataLogId, bOrgId, bOrgToken, bOrgRefreshToken,
						bOrgURL, validationId);
				multiPleDeploymentDOs.add(multiPleDeploymentDO);
			} else {
				multiPleDeploymentDO = new MultiPleDeploymentDO(metadataLogId, bOrgId, bOrgToken, bOrgRefreshToken,
						bOrgURL);
				multiPleDeploymentDOs.add(multiPleDeploymentDO);
			}

		}

		System.out.println("Size of Multiple Environment--------: " + multiPleDeploymentDOs.size());
		System.out.println("bOrgId : " + bOrgId + "~" + "bOrgURL : " + bOrgURL + "~" + "bOrgToken : " + bOrgToken + "~"
				+ "AS Version Control ?" + asversionControl + "Git Server URL" + gitServerURL);

		System.out.println("metadata Log Id: " + metadataLogId + "  -Org Id: " + tOrgId + "~" + action);

		try {
			ForceDepService deploymentService = new ForceDepService();
			if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& (action != null && !action.isEmpty() && action.equals("Validate")
							|| action.equals("ValidateAll"))
					&& (tOrgId != null && !tOrgId.isEmpty())) {
				System.out.println("Validate");
				deploymentService.deploy(bOrgId, bOrgToken, bOrgURL, bOrgRefreshToken, multiPleDeploymentDOs, true);
				return true;

			} else if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& (action != null && !action.isEmpty() && action.equals("Deploy") || action.equals("DeployAll"))
					&& (tOrgId != null && !tOrgId.isEmpty())) {
				System.out.println("deploy");
				deploymentService.deploy(bOrgId, bOrgToken, bOrgURL, bOrgRefreshToken, multiPleDeploymentDOs, false);
				return true;

			} else if ((metadataLogId != null && !metadataLogId.isEmpty())
					&& (action != null && !action.isEmpty() && action.equals("QuickDeploy"))
					&& (tOrgId != null && !tOrgId.isEmpty())) {
				System.out.println("QuickDeploy");
				deploymentService.quickDeploy(bOrgId, bOrgToken, bOrgURL, bOrgRefreshToken,
						multiPleDeploymentDOs, false);
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
}
