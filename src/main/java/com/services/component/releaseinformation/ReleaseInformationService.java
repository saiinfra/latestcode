package com.services.component.releaseinformation;

import java.util.List;

import com.ds.salesforce.dao.comp.ReleaseInformationDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.processflow.ProcessStatus;
import com.processflow.ProcessTrack;
import com.request.releaseinformation.ReleaseInformationRequest;
import com.response.releaseinformation.ReleaseInformationResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;

public class ReleaseInformationService extends BaseService {

	ReleaseInformationRequest request;
	ReleaseInformationResponse response;

	public ReleaseInformationService() {
		super();
	}

	public ReleaseInformationService(ReleaseInformationRequest request,
			ReleaseInformationResponse response) {
		super(request, response);
		this.request = request;
		this.response = response;
	}

	public ReleaseInformationRequest getRequest() {
		return request;
	}

	public void setRequest(ReleaseInformationRequest request) {
		this.request = request;
	}

	public ReleaseInformationResponse getResponse() {
		return response;
	}

	public void setResponse(ReleaseInformationResponse response) {
		this.response = response;
	}

	public void processToGetRelInfoList() {
		try {
			FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

			ReleaseInformationDAO riDAO = new ReleaseInformationDAO();
			// Get the ReleaseInformation in the each of target
			// environments.
			List<Object> relInfoList = riDAO.findByParentId(getRequest()
					.getReleaseId(), fdGetSFoAuthHandleService
					.getSFoAuthHandle(getRequest().getEnvDO(),
							Constants.CustomOrgID));
			getResponse().setRelInfoList(relInfoList);
			ProcessStatus processStatus = new ProcessStatus(
					"Getting ReleaseInformation", Constants.SUCCES_STATUS,
					"get relinfo");
			ProcessTrack processTrack = new ProcessTrack(processStatus);
			getResponse().getProcessTrack().add(processTrack);
		} catch (Exception e) {
			ProcessStatus processStatus = new ProcessStatus(
					"Getting ReleaseInformation", Constants.FAILURE_STATUS,
					e.toString());
			ProcessTrack processTrack = new ProcessTrack(processStatus);
			processTrack.setCurrentStatus(Constants.FAILURE_STATUS);
			getResponse().getProcessTrack().add(processTrack);
		}
	}

}
