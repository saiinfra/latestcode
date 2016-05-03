package com.services.component.packages;

import com.domain.PackageDO;
import com.ds.salesforce.dao.comp.PackageDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.processflow.ProcessStatus;
import com.processflow.ProcessTrack;
import com.request.packages.CreatePackageRequest;
import com.response.packages.CreatePackageResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;

public class CreatePackageService extends BaseService {

	CreatePackageRequest request;
	CreatePackageResponse response;

	public CreatePackageService(CreatePackageRequest request,
			CreatePackageResponse response) {
		super(request, response);
		this.request = request;
		this.response = response;
	}

	public void createPackageInformation() {
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			
			CreatePackageRequest request =getRequest(); 
			String name = getRequest().getPkgInfoDO().getName();
			String description = getRequest().getPkgInfoDO().getDescription();
			String parentPackageId = getRequest().getPkgInfoDO().getId();

			PackageDO pkgDO = new PackageDO(name, description, parentPackageId);
			PackageDAO PkgDAO = new PackageDAO();
			String packageId = PkgDAO.insertAndGetId(pkgDO,
					fdGetSFoAuthHandleService.getSFoAuthHandle(getRequest()
							.getOrg()));
			getResponse().setPackageId(packageId);
			ProcessStatus processStatus = new ProcessStatus(
					"Creating Packages", Constants.SUCCES_STATUS,
					"Creating pkgs");
			getResponse().getProcessTrack()
					.add(new ProcessTrack(processStatus));

		} catch (Exception e) {
			ProcessStatus processStatus = new ProcessStatus(
					"Creating Packages", Constants.FAILURE_STATUS, e.toString());
			ProcessTrack processTrack = new ProcessTrack(processStatus);
			processTrack.setCurrentStatus(Constants.FAILURE_STATUS);
			getResponse().getProcessTrack().add(processTrack);
			// throw new SFException(e.toString(), SFErrorCodes.SF_Conn_Error);
		}
	}

	public CreatePackageRequest getRequest() {
		return request;
	}

	public void setRequest(CreatePackageRequest request) {
		this.request = request;
	}

	public CreatePackageResponse getResponse() {
		return response;
	}

	public void setResponse(CreatePackageResponse response) {
		this.response = response;
	}

}
