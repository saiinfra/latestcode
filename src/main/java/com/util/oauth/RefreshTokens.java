package com.util.oauth;

import java.util.Iterator;
import java.util.List;

import com.domain.DeploymentSettingsDO;
import com.domain.EnvironmentDO;
import com.domain.EnvironmentInformationDO;
import com.ds.salesforce.dao.comp.DeployDetailsDAO;
import com.ds.salesforce.dao.comp.DeploySettingsDAO;
import com.ds.salesforce.dao.comp.EnvironmentDAO;
import com.ds.salesforce.dao.comp.EnvironmentInformationDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;

public class RefreshTokens {
	private  String oAuthToken;
	private  String baseAuthToken;
	private  String customAuthToken;
	private  String customBaseAuthToken;

	public RefreshTokens() {
		super();
	}

	public  void refreshBaseSFHandle(String bOrgId, String bOrgToken,
			String bOrgURL, String refreshtoken) throws SFException {
		DeploySettingsDAO dsDAO = new DeploySettingsDAO();
		DeploymentSettingsDO dsDO = null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			boolean isBase = true;
			baseAuthToken = fdGetSFoAuthHandleService.getSFAuthToken(bOrgId,
					bOrgToken, bOrgURL, refreshtoken, Constants.BaseOrgID);
			System.out.println(baseAuthToken);

			List dsList = dsDAO.findById(bOrgId, fdGetSFoAuthHandleService
					.getSFoAuthHandle(bOrgId, baseAuthToken, bOrgURL,
							refreshtoken, Constants.BaseOrgID));

			for (Iterator iterator = dsList.iterator(); iterator.hasNext();) {
				dsDO = (DeploymentSettingsDO) iterator.next();
			}
			System.out.println("Old Token: " + bOrgToken);
			System.out.println("new Token: " + baseAuthToken);
			dsDO.setToken(baseAuthToken);
			dsDAO.update(dsDO, fdGetSFoAuthHandleService.getSFoAuthHandle(
					bOrgId, baseAuthToken, bOrgURL, refreshtoken,
					Constants.BaseOrgID));
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
	}

	public  String refreshSFHandle(String bOrgId, String bOrgToken,
			String bOrgURL, String orgType, String refreshtoken)
			throws SFException {
		DeploySettingsDAO dsDAO = new DeploySettingsDAO();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		DeploymentSettingsDO dsDO = null;
		try {

			oAuthToken = fdGetSFoAuthHandleService.getSFAuthToken(bOrgId,
					bOrgToken, bOrgURL, refreshtoken, orgType);
			System.out.println(oAuthToken);

			/*
			 * List dsList = dsDAO.findById(bOrgId, FDGetSFoAuthHandleService
			 * .getSFoAuthHandle(bOrgId, baseAuthToken, bOrgURL, refreshtoken,
			 * Constants.BaseOrgID));
			 * 
			 * for (Iterator iterator = dsList.iterator(); iterator.hasNext();)
			 * { dsDO = (DeploymentSettingsDO) iterator.next(); }
			 * System.out.println("Old Token: " + bOrgToken);
			 * System.out.println("new Token: " + baseAuthToken);
			 * dsDO.setToken(baseAuthToken); dsDAO.update(dsDO,
			 * FDGetSFoAuthHandleService.getSFoAuthHandle( bOrgId,
			 * baseAuthToken, bOrgURL, refreshtoken, Constants.BaseOrgID));
			 */
		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return oAuthToken;
	}

	public  void refreshCustomSFHandle(String bOrgId, String bOrgToken,
			String bOrgURL, String refreshtoken) throws SFException {
	}

	public  String refreshCustomSFHandle(EnvironmentDO envDO,
			String bOrgId, String bOrgToken, String bOrgURL, String refreshToken)
			throws SFException {
		EnvironmentDAO envDAO = new EnvironmentDAO();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {

			customAuthToken = fdGetSFoAuthHandleService.getSFAuthToken(
					envDO.getOrgId(), envDO.getToken(), envDO.getServerURL(),
					envDO.getRefreshtoken(), Constants.CustomOrgID);
			System.out.println(customAuthToken);

			List envLogList = envDAO.findById(envDO.getOrgId(),
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.BaseOrgID));

			for (Iterator iterator = envLogList.iterator(); iterator.hasNext();) {
				envDO = (EnvironmentDO) iterator.next();
			}
			envDO.setTokenCodeNonEncrypted(customAuthToken);

			System.out.println("customAuthToken new Token: "
					+ envDO.getTokenCodeNonEncrypted());
			envDAO.update(envDO, fdGetSFoAuthHandleService.getSFoAuthHandle(
					bOrgId, bOrgToken, bOrgURL, refreshToken,
					Constants.BaseOrgID));

			fdGetSFoAuthHandleService.setSfHandleToNUll();

		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return customAuthToken;
	}

	public  String refreshCustomBaseSFHandle(EnvironmentDO envDO,
			String bOrgId, String bOrgToken, String bOrgURL, String refreshToken)
			throws SFException {
		EnvironmentDAO envDAO = new EnvironmentDAO();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {

			String customBaseAuthToken = fdGetSFoAuthHandleService
					.getSFAuthToken(envDO.getOrgId(), envDO.getToken(),
							envDO.getServerURL(), envDO.getRefreshtoken(),
							Constants.CustomBaseOrgID);
			System.out.println(customBaseAuthToken);

			List envLogList = envDAO.findById(envDO.getOrgId(),
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.CustomBaseOrgID));

			for (Iterator iterator = envLogList.iterator(); iterator.hasNext();) {
				envDO = (EnvironmentDO) iterator.next();
			}
			envDO.setTokenCodeNonEncrypted(customBaseAuthToken);

			System.out.println("customAuthToken new Token: "
					+ envDO.getTokenCodeNonEncrypted());
			envDAO.update(envDO, fdGetSFoAuthHandleService.getSFoAuthHandle(
					bOrgId, bOrgToken, bOrgURL, refreshToken,
					Constants.CustomBaseOrgID));

			fdGetSFoAuthHandleService.setSfHandleToNUll();

		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return customBaseAuthToken;
	}

	public  String refreshClientCustomSFHandle(
			EnvironmentInformationDO envDO, String bOrgId, String bOrgToken,
			String bOrgURL, String refreshToken) throws SFException {
		EnvironmentInformationDAO envDAO = new EnvironmentInformationDAO();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {

			customAuthToken = fdGetSFoAuthHandleService.getSFAuthToken(
					envDO.getOrgId(), envDO.getToken(), envDO.getServerURL(),
					envDO.getRefreshtoken(), Constants.CustomBaseOrgID);
			System.out.println(customAuthToken);

			List envLogList = envDAO.findById(envDO.getOrgId(),
					fdGetSFoAuthHandleService.getSFoAuthHandle(bOrgId,
							bOrgToken, bOrgURL, refreshToken,
							Constants.CustomBaseOrgID));

			for (Iterator iterator = envLogList.iterator(); iterator.hasNext();) {
				envDO = (EnvironmentInformationDO) iterator.next();
			}
			envDO.setTokenCodeNonEncrypted(customAuthToken);

			System.out.println("customAuthToken new Token: "
					+ envDO.getTokenCodeNonEncrypted());
			envDAO.update(envDO, fdGetSFoAuthHandleService.getSFoAuthHandle(
					bOrgId, bOrgToken, bOrgURL, refreshToken,
					Constants.CustomBaseOrgID));

			fdGetSFoAuthHandleService.setSfHandleToNUll();

		} catch (SFException e) {
			throw new SFException(e.toString(),
					SFErrorCodes.SFEnvironment_Update_Error);
		}
		return customAuthToken;
	}

	public  String getBaseAuthToken() {
		return baseAuthToken;
	}

	private  void setBaseAuthToken(String baseAuthToken) {
		this.baseAuthToken = baseAuthToken;
	}

	public  String getCustomAuthToken() {
		return customAuthToken;
	}

	private  void setCustomAuthToken(String customAuthToken) {
		this.customAuthToken = customAuthToken;
	}

	public  String getoAuthToken() {
		return oAuthToken;
	}

	public  void setoAuthToken(String oAuthToken) {
		this.oAuthToken = oAuthToken;
	}

}
