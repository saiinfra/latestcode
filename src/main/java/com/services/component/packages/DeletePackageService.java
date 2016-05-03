package com.services.component.packages;

import java.util.Iterator;
import java.util.List;

import com.domain.ReleasePackageDO;
import com.ds.salesforce.dao.comp.PackageDAO;
import com.ds.salesforce.dao.comp.ReleasePackageDAO;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.processflow.ProcessStatus;
import com.processflow.ProcessTrack;
import com.request.packages.DeletePackageRequest;
import com.response.packages.DeletePackageResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;
import com.util.Org;
import com.util.SFoAuthHandle;

public class DeletePackageService extends BaseService {

	DeletePackageRequest request;
	DeletePackageResponse response;

	public DeletePackageService(DeletePackageRequest request,
			DeletePackageResponse response) {
		super(request, response);
		this.request = request;
		this.response = response;
	}

	public DeletePackageRequest getRequest() {
		return request;
	}

	public void setRequest(DeletePackageRequest request) {
		this.request = request;
	}

	public DeletePackageResponse getResponse() {
		return response;
	}

	public void setResponse(DeletePackageResponse response) {
		this.response = response;
	}

	public void processDeletePackage() {
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		try {
			ReleasePackageDAO rpkgDAO = new ReleasePackageDAO();
			Org org = getRequest().getOrg();
			
			System.out.println(org.getOrgId());
			SFoAuthHandle sfhandle = fdGetSFoAuthHandleService
					.getSFoAuthHandle(getRequest().getOrg());
			System.out.println("deleting Packages start getting connection");

			List<Object> rpkgDOList = rpkgDAO.findByReleaseId(getRequest()
					.getReleaseId(), sfhandle);
			for (Iterator<Object> iteratord = rpkgDOList.iterator(); iteratord
					.hasNext();) {
				ReleasePackageDO rpkgDO = (ReleasePackageDO) iteratord.next();
				System.out.println(rpkgDO.getPackageC());
				String[] ids = new String[1];
				ids[0] = rpkgDO.getPackageC();
				PackageDAO pkgDAO = new PackageDAO();
				List<Object> pkgList = pkgDAO.findById(ids[0], sfhandle);
				if (pkgList.size() > 0) {
					// delete Packages
					pkgDAO.deleteRecords(ids, sfhandle);
				}
			}
			ProcessStatus processStatus = new ProcessStatus(
					"Deleting Packages", Constants.SUCCES_STATUS,
					"Getting SF Connection");
			getResponse().getProcessTrack().add(
					new ProcessTrack(processStatus));
		} catch (Exception e) {
			ProcessStatus processStatus = new ProcessStatus(
					"Deleting Packages", Constants.FAILURE_STATUS,
					e.toString());
			ProcessTrack processTrack = new ProcessTrack(processStatus);
			processTrack.setCurrentStatus(Constants.FAILURE_STATUS);
			getResponse().getProcessTrack().add(processTrack);
			throw new SFException(e.toString(),
					SFErrorCodes.SF_Conn_Error);
		}
	}
}
