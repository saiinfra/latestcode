package com.services.component.release;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.EnvironmentDO;
import com.domain.ReleaseInformationDO;
import com.ds.salesforce.dao.comp.PackageInformationDAO;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;

public class GetPkgInfoList {
	List<Object> relInfoList;
	EnvironmentDO envDO;
	private static final Logger LOG = LoggerFactory
			.getLogger(GetPkgInfoList.class);

	public GetPkgInfoList() {
		super();
	}

	public GetPkgInfoList(List<Object> relInfoList, EnvironmentDO envDO) {
		this.relInfoList = relInfoList;
		this.envDO = envDO;
	}

	public List<Object> getList() {
		// Iterate thru the release information list in the target
		// env
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		List<Object> packageInfoList = null;
		for (Iterator<Object> iterator2 = getRelInfoList().iterator(); iterator2
				.hasNext();) {
			ReleaseInformationDO rInfoDO = (ReleaseInformationDO) iterator2
					.next();
			System.out.println("ReleaseId  :" + rInfoDO.getId());
			PackageInformationDAO pkginfoDAO = new PackageInformationDAO();

			// find all the packageInformationInfo list associated
			// with ReleaseInformation
			long startTime = System.currentTimeMillis();
			LOG.info("start connection for Get PackageInformation List");
		
			packageInfoList = pkginfoDAO.findByReleaseId(rInfoDO.getId(),
					fdGetSFoAuthHandleService.getSFoAuthHandle(getEnvDO(),
							Constants.CustomOrgID));
			long endTime = System.currentTimeMillis();
			long total = endTime - startTime;
			LOG.info("Total Time Taken to process GetPackageInformation List"
					+ total / 1000 +" seconds");
		

			return packageInfoList;
		}
		return packageInfoList;
	}

	public List<Object> getRelInfoList() {
		return relInfoList;
	}

	public void setRelInfoList(List<Object> relInfoList) {
		this.relInfoList = relInfoList;
	}

	public EnvironmentDO getEnvDO() {
		return envDO;
	}

	public void setEnvDO(EnvironmentDO envDO) {
		this.envDO = envDO;
	}

}
