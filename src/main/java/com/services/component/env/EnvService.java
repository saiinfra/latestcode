package com.services.component.env;

import java.util.List;

import com.ds.salesforce.dao.comp.EnvironmentDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.processflow.ProcessStatus;
import com.processflow.ProcessTrack;
import com.request.env.EnvRequest;
import com.request.packages.DeletePackageRequest;
import com.response.env.EnvResponse;
import com.response.packages.DeletePackageResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;
import com.util.Org;

public class EnvService extends BaseService {
	EnvRequest request;
	EnvResponse response;

	public EnvService() {
		super();
	}

	public EnvService(EnvRequest request, EnvResponse response) {
		super(request, response);
		this.request = request;
		this.response = response;
	}

	public List<Object> ListAllEnv(Org org) {
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		EnvironmentDAO dao = new EnvironmentDAO();
		List<Object> envList = dao.listAll(fdGetSFoAuthHandleService
				.getSFoAuthHandle(org));
		/*List<Object> envList = dao.listAll(FDGetSFoAuthHandleService
				.getSFoAuthHandle(getRequest().getOrg()));*/

		return envList;
	}

	public void processToGetAllEnv() {
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			EnvironmentDAO dao = new EnvironmentDAO();
			Org org = getRequest().getOrg();
			System.out.println(org.getOrgType());
			List<Object> envList = dao.listAll(fdGetSFoAuthHandleService
					.getSFoAuthHandle(getRequest().getOrg()));
			getResponse().setEnvList(envList);
			ProcessStatus processStatus = new ProcessStatus(
					"Getting Environments", Constants.SUCCES_STATUS, "get envs");
			ProcessTrack processTrack = new ProcessTrack(processStatus);
			getResponse().getProcessTrack().add(processTrack);
		} catch (Exception e) {
			ProcessStatus processStatus = new ProcessStatus(
					"Deleting Packages", Constants.FAILURE_STATUS, e.toString());
			ProcessTrack processTrack = new ProcessTrack(processStatus);
			processTrack.setCurrentStatus(Constants.FAILURE_STATUS);
			getResponse().getProcessTrack().add(processTrack);
			throw new SFException(e.toString(), SFErrorCodes.SF_Conn_Error);
		}
	}

	public EnvRequest getRequest() {
		return request;
	}

	public void setRequest(EnvRequest request) {
		this.request = request;
	}

	public EnvResponse getResponse() {
		return response;
	}

	public void setResponse(EnvResponse response) {
		this.response = response;
	}

}
