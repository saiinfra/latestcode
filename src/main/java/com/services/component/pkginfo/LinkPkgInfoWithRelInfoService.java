package com.services.component.pkginfo;

import java.util.Iterator;
import java.util.List;

import com.domain.ReleasePackageDO;
import com.ds.salesforce.dao.comp.ReleasePackageDAO;
import com.request.pkginfo.PackageInfoRequest;
import com.response.pkginfo.PackageInfoResponse;
import com.services.BaseService;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;
import com.util.Org;

public class LinkPkgInfoWithRelInfoService extends BaseService {

	PackageInfoRequest request;
	PackageInfoResponse response;

	public LinkPkgInfoWithRelInfoService(PackageInfoRequest request,
			PackageInfoResponse response) {
		this.request = request;
		this.response = response;
	}

	public PackageInfoRequest getRequest() {
		return request;
	}

	public void setRequest(PackageInfoRequest request) {
		this.request = request;
	}

	public PackageInfoResponse getResponse() {
		return response;
	}

	public void setResponse(PackageInfoResponse response) {
		this.response = response;
	}

	public void processLinkRelWithPkgInfo() {
		// Associate package with release
		List<String> pidList = getRequest().getPidList();
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		if (pidList != null) {
			for (Iterator<String> iterator = pidList.iterator(); iterator
					.hasNext();) {
				String pid = (String) iterator.next();

				// Associate package with release
				ReleasePackageDAO relPkgDAO = new ReleasePackageDAO();

				Org org = getRequest().getOrg();
				List<Object> relePkgList = relPkgDAO.findByPkgIDAndRID(pid,
						getRequest().getReleaseId(),
						fdGetSFoAuthHandleService.getSFoAuthHandle(getRequest().getOrg()));
				if (!relePkgList.isEmpty()) {
					ReleasePackageDO relPkgDO = new ReleasePackageDO("1", pid,
							getRequest().getReleaseId(),null);

					String pkgId = relPkgDAO.insertAndGetId(relPkgDO,
							fdGetSFoAuthHandleService
									.getSFoAuthHandle(getRequest().getOrg()));
				}
			}
		}
	}

}
