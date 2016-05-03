package com.services.application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.EnvironmentInformationDO;
import com.domain.ErrorLogBean;
import com.domain.MetadataLogDO;
import com.domain.MetadataLogInformationDO;
import com.domain.TestMetadataLogDO;
import com.ds.salesforce.dao.comp.DeployDetailsDAO;
import com.ds.salesforce.dao.comp.DeployDetailsInformationDAO;
import com.ds.salesforce.dao.comp.EnvironmentDAO;
import com.ds.salesforce.dao.comp.EnvironmentInformationDAO;
import com.ds.salesforce.dao.comp.MetadataLogDAO;
import com.ds.salesforce.dao.comp.MetadataLogInformationDAO;
import com.ds.salesforce.dao.comp.TestMetadataLogDAO;
import com.exception.SFException;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;
import com.util.Org;
import com.util.SFoAuthHandle;

public class RDAppService {

	// make the constructor private so that this class cannot be
	// instantiated
	public RDAppService() {
		super();
	}

	// create an object of SingleObject
	private static RDAppService instance = null;

	// Get the only object available
	public static RDAppService getInstance() {
		if (instance == null) {
			instance = new RDAppService();
		}
		return instance;
	}

	public static MetadataLogDO findMetadataLog(String metadataLogId,
			SFoAuthHandle sfHandle) {
		MetadataLogDO metadataLogDO = null;
		MetadataLogDAO metadataLogDAO = new MetadataLogDAO();
		try {
			// System.out.println("AOuth Token : "+
			// sfHandle.getEnterpriseConnection().getUserInfo().getProfileId());
			List metadataLogList = metadataLogDAO.findById(metadataLogId,
					sfHandle);

			for (Iterator iterator = metadataLogList.iterator(); iterator
					.hasNext();) {
				metadataLogDO = (MetadataLogDO) iterator.next();
			}
		} catch (SFException e) {
			throw e;
		} /*
		 * catch (ConnectionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); try { throw e; } catch (ConnectionException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); } }
		 */
		return metadataLogDO;
	}

	public static MetadataLogInformationDO findMetadataLogInformation(
			String metadataLogId, SFoAuthHandle sfHandle) {
		MetadataLogInformationDO metadataLogInformationDO = null;
		MetadataLogInformationDAO metadataLogDAO = new MetadataLogInformationDAO();
		try {
			// System.out.println("AOuth Token : "+
			// sfHandle.getEnterpriseConnection().getUserInfo().getProfileId());
			List metadataLogList = metadataLogDAO.findById(metadataLogId,
					sfHandle);

			for (Iterator iterator = metadataLogList.iterator(); iterator
					.hasNext();) {
				metadataLogInformationDO = (MetadataLogInformationDO) iterator
						.next();
			}
		} catch (SFException e) {
			throw e;
		} /*
		 * catch (ConnectionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); try { throw e; } catch (ConnectionException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); } }
		 */
		return metadataLogInformationDO;
	}

	public static TestMetadataLogDO findTestMetadataLog(String metadataLogId,
			SFoAuthHandle sfHandle) {
		TestMetadataLogDO testMetadataLogDO = null;
		TestMetadataLogDAO metadataLogDAO = new TestMetadataLogDAO();
		try {
			// System.out.println("AOuth Token : "+
			// sfHandle.getEnterpriseConnection().getUserInfo().getProfileId());
			List metadataLogList = metadataLogDAO.findById(metadataLogId,
					sfHandle);

			for (Iterator iterator = metadataLogList.iterator(); iterator
					.hasNext();) {
				testMetadataLogDO = (TestMetadataLogDO) iterator.next();
			}
		} catch (SFException e) {
			throw e;
		} /*
		 * catch (ConnectionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); try { throw e; } catch (ConnectionException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); } }
		 */
		return testMetadataLogDO;
	}

	public  EnvironmentDO getEnv(String orgId, SFoAuthHandle sfHandle) {
		EnvironmentDO envDO = null;
		EnvironmentDAO envDAO = new EnvironmentDAO();
		try {
			List envList = envDAO.findById(orgId, sfHandle);
			for (Iterator iterator = envList.iterator(); iterator.hasNext();) {
				envDO = (EnvironmentDO) iterator.next();
			}
		} catch (SFException e) {
			throw e;
		}
		return envDO;
	}

	public static EnvironmentInformationDO getEnv1(String orgId,
			SFoAuthHandle sfHandle) {
		EnvironmentInformationDO envDO = null;
		EnvironmentInformationDAO envDAO = new EnvironmentInformationDAO();
		try {
			List envList = envDAO.findById(orgId, sfHandle);
			for (Iterator iterator = envList.iterator(); iterator.hasNext();) {
				envDO = (EnvironmentInformationDO) iterator.next();
			}
		} catch (SFException e) {
			throw e;
		}
		return envDO;
	}

	public static void updateMetadataLogStatus(MetadataLogDO metadataLogDO,
			String status, SFoAuthHandle sfHandle) {
		MetadataLogDAO metadataLogDAO = new MetadataLogDAO();
		metadataLogDO.setStatus(status);
		metadataLogDAO.update(metadataLogDO, sfHandle);
	}

	public static void updateMetadataLogValidationID(
			MetadataLogDO metadataLogDO, SFoAuthHandle sfHandle) {
		MetadataLogDAO metadataLogDAO = new MetadataLogDAO();
		System.out.println(metadataLogDO.getValidationSuccessId());
		metadataLogDAO.updateValidationId(metadataLogDO, sfHandle);
	}

	public static void updateMetadataLogInformationStatus(
			MetadataLogInformationDO metadataLogInformationDO, String status,
			SFoAuthHandle sfHandle) {
		MetadataLogInformationDAO metadataLogDAO = new MetadataLogInformationDAO();
		metadataLogInformationDO.setStatus(status);
		metadataLogDAO.update(metadataLogInformationDO, sfHandle);
	}

	public static void updateTestMetadataLogStatus(
			TestMetadataLogDO testMetadataLogDO, String status, String message,
			SFoAuthHandle sfHandle) {
		TestMetadataLogDAO metadataLogDAO = new TestMetadataLogDAO();
		testMetadataLogDO.setStatus(status);
		testMetadataLogDO.setMessage(message);
		metadataLogDAO.update(testMetadataLogDO, sfHandle);
	}

	public static void updateDeploymentDetails(String metadataLogId,
			String errorMessage, String orgId, SFoAuthHandle sfHandle) {
		DeployDetailsDAO deployDetailsDAO = new DeployDetailsDAO();
		ErrorLogBean errorBean = new ErrorLogBean(metadataLogId, errorMessage,
				orgId, "");
		deployDetailsDAO.insert(errorBean, sfHandle);
	}

	public static void updateDeploymentDetailsInformation(String metadataLogId,
			String errorMessage, String orgId, SFoAuthHandle sfHandle) {
		DeployDetailsInformationDAO deployDetailsInformationDAO = new DeployDetailsInformationDAO();
		ErrorLogBean errorBean = new ErrorLogBean(metadataLogId, errorMessage,
				orgId, "");
		deployDetailsInformationDAO.insert(errorBean, sfHandle);
	}

	public static void updateToComplete(String metadataLogId, String status,
			Org org) {
		MetadataLogDO metadataLogDO = null;
		try {
			FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();
			metadataLogDO = RDAppService.findMetadataLog(metadataLogId,
					fdGetSFoAuthHandleService.getSFoAuthHandle(org));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();

			// updating metadataLog status
			RDAppService.updateMetadataLogStatus(metadataLogDO, status,
					fdGetSFoAuthHandleService.getSFoAuthHandle(org));

			// updating DeploymentDetail status
			RDAppService.updateDeploymentDetails(metadataLogId,
					Constants.SUCCESFULLY_STATUS, org.getOrgId(),
					fdGetSFoAuthHandleService.getSFoAuthHandle(org));

		} catch (SFException e) {
			if (metadataLogDO != null) {
				FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating metadataLog
				RDAppService.updateMetadataLogStatus(metadataLogDO,
						Constants.ERROR_STATUS,
						fdGetSFoAuthHandleService.getSFoAuthHandle(org));

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating Deploy Details
				RDAppService.updateDeploymentDetails(metadataLogId,
						e.getMessage(), metadataLogDO.getSourceOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(org));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			} else {
				System.out.println("Salesforce Exception " + e.getMessage());
			}
		}

	}

	public static void updateToComplete1(String metadataLogId, String status,
			Org org, LinkedList<String> list) {
		MetadataLogDO metadataLogDO = null;
		try {
			FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

			metadataLogDO = RDAppService.findMetadataLog(metadataLogId,
					fdGetSFoAuthHandleService.getSFoAuthHandle(org));
			// nullify connection
			fdGetSFoAuthHandleService.setSfHandleToNUll();

			// updating metadataLog status
			RDAppService.updateMetadataLogStatus(metadataLogDO, status,
					fdGetSFoAuthHandleService.getSFoAuthHandle(org));

			// updating DeploymentDetail status
			Iterator<String> itr = list.iterator();
			while (itr.hasNext()) {
				RDAppService.updateDeploymentDetails(metadataLogId, itr.next(),
						org.getOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(org));

			}

		} catch (SFException e) {
			if (metadataLogDO != null) {
				FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating metadataLog
				RDAppService.updateMetadataLogStatus(metadataLogDO,
						Constants.ERROR_STATUS,
						fdGetSFoAuthHandleService.getSFoAuthHandle(org));

				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
				// updating Deploy Details
				RDAppService.updateDeploymentDetails(metadataLogId,
						e.getMessage(), metadataLogDO.getSourceOrgId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(org));
				// refresh connection
				fdGetSFoAuthHandleService.setSfHandleToNUll();
			} else {
				System.out.println("Salesforce Exception " + e.getMessage());
			}
		}

	}
}
