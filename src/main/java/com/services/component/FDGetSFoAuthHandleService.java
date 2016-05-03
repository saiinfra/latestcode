package com.services.component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.domain.EnvironmentDO;
import com.domain.EnvironmentInformationDO;
import com.domain.SFoAuthHandleDO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.future.GetSFoAuthHandleFutureTask;
import com.services.application.RDAppService;
import com.util.Org;
import com.util.SFoAuthHandle;
import com.util.oauth.RefreshTokens;

public class FDGetSFoAuthHandleService {

	private SFoAuthHandle sfHandle = null;

	// make the constructor private so that this class cannot be
	// instantiated
	public FDGetSFoAuthHandleService() {
		super();
	}

	public String getSFAuthToken(String orgId, String token,
			String instanceURL, String refreshtoken, String orgType) {
		SFoAuthHandle sfHandle = getSFoAuthHandle(orgId, token, instanceURL,
				refreshtoken, orgType);
		return sfHandle.getoAuthToken();
	}

	public SFoAuthHandle getSFoAuthHandle(String orgId, String token,
			String instanceURL, String refreshtoken, String orgType)
			throws SFException {
		SFoAuthHandleDO authDO = new SFoAuthHandleDO(orgId, token, instanceURL,
				refreshtoken, orgType);
		sfHandle = getSFoAuthHandleFFT(authDO);
		try {
			sfHandle = sfHandle.getValidConnection();
		} catch (SFException e) {
			throw e;
		}
		return sfHandle;
	}

	public SFoAuthHandle getSFoAuthHandle(Org org) throws SFException {

		SFoAuthHandleDO authDO = new SFoAuthHandleDO(org.getOrgId(),
				org.getOrgToken(), org.getOrgURL(), org.getRefreshToken(),
				org.getOrgType());
		sfHandle = getSFoAuthHandleFFT(authDO);
		try {
			sfHandle = sfHandle.getValidConnection();
		} catch (SFException e) {
			throw e;
		}
		return sfHandle;
	}

	public SFoAuthHandle getSFoAuthHandle(String sourceOrgId, String orgId,
			String token, String instanceURL, String refreshtoken,
			String orgType) {
		// get source environment details

		RDAppService rdAppService = new RDAppService();
		EnvironmentDO envSoureDO = rdAppService.getEnv(sourceOrgId,
				getSFoAuthHandle(orgId, token, instanceURL, refreshtoken, "0"));
		RefreshTokens refreshTokens = new RefreshTokens();
		// refresh access tokens if the existing tokens have expired
		String newSToken = refreshTokens.refreshCustomSFHandle(envSoureDO,
				orgId, token, instanceURL, refreshtoken);
		envSoureDO.setToken(newSToken);
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();
		// get source salesforce handle
		sfHandle = fdGetSFoAuthHandleService.getSFoAuthHandle(envSoureDO,
				orgType);

		return sfHandle;
	}

	public SFoAuthHandle getSFoAuthHandle(EnvironmentDO envDO, String orgType)
			throws SFException {

		SFoAuthHandleDO authDO = new SFoAuthHandleDO(envDO.getOrgId(),
				envDO.getToken(), envDO.getServerURL(),
				envDO.getRefreshtoken(), orgType);
		sfHandle = getSFoAuthHandleFFT(authDO);
		try {
			sfHandle = sfHandle.getValidConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sfHandle;
	}

	public SFoAuthHandle getSFoAuthHandle(EnvironmentInformationDO envDO,
			String orgType) throws SFException {

		SFoAuthHandleDO authDO = new SFoAuthHandleDO(envDO.getOrgId(),
				envDO.getToken(), envDO.getServerURL(),
				envDO.getRefreshtoken(), orgType);
		sfHandle = getSFoAuthHandleFFT(authDO);
		try {
			sfHandle = sfHandle.getValidConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sfHandle;
	}

	private SFoAuthHandle getSFoAuthHandleFFT(SFoAuthHandleDO authDO)
			throws SFException {

		SFoAuthHandle sFoAuthHandle = null;

		GetSFoAuthHandleFutureTask callable1 = new GetSFoAuthHandleFutureTask(
				authDO);
		FutureTask<SFoAuthHandle> getSFoAuthHandleFutureTask = new FutureTask<SFoAuthHandle>(
				callable1);
		try {
			getSFoAuthHandleFutureTask.run();
			// ExecutorService executor = Executors.newFixedThreadPool(1);
			// executor.execute(getSFoAuthHandleFutureTask);
			while (true) {
				try {
					if (getSFoAuthHandleFutureTask.isDone()) {
						System.out
								.println("getSFoAuthHandleFutureTask is Done");
						// shut down executor service
						// executor.shutdown();
						sFoAuthHandle = getSFoAuthHandleFutureTask.get();
						return sFoAuthHandle;
					} else if (!getSFoAuthHandleFutureTask.isDone()) {
						// wait indefinitely for future task to complete
						System.out.println("Waiting on SFoAuthHandle.....");
						sFoAuthHandle = getSFoAuthHandleFutureTask.get();
					}
					System.out
							.println("Waiting for getSFoAuthHandleFutureTask to complete");
					sFoAuthHandle = getSFoAuthHandleFutureTask.get(200L,
							TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					// do nothing
				}
			}
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SF_ListObject_Error);
		}
	}

	public void setSfHandleToNUll() {
		if (sfHandle != null) {
			sfHandle.nullify();
		}
		sfHandle = null;
	}

	public SFoAuthHandle getSfHandle() {
		return sfHandle;
	}

	public void setSfHandle(SFoAuthHandle sfHandle) {
		this.sfHandle = sfHandle;
	}

}