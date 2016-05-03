package com.services.component.release;

import java.util.Iterator;
import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.PackageInformationDO;
import com.domain.ReleaseInformationDO;
import com.ds.salesforce.dao.comp.PackageCompInfoDAO;
import com.ds.salesforce.dao.comp.PackageInformationDAO;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Constants;

public class GetPkgCompList {
	List<Object> relInfoList;
	EnvironmentDO envDO;
	String pkgid;
	
	public GetPkgCompList(){
		super();
	}
	
	public GetPkgCompList(List<Object> relInfoList, EnvironmentDO envDO,String pkgid){
		this.relInfoList = relInfoList;
		this.envDO = envDO;
		this.pkgid=pkgid;
	}

	public List<Object> getList(){
		// Iterate thru the release information list in the target
		// env
		List<Object> packageCompList=null;
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

			

				PackageCompInfoDAO pkgCompInfoDAO = new PackageCompInfoDAO();

				// find the PackageComponents associated with
				// PackageInformation
				packageCompList = pkgCompInfoDAO
						.findByPackageId(pkgid,
								fdGetSFoAuthHandleService
										.getSFoAuthHandle(envDO,
												Constants.CustomOrgID));

				fdGetSFoAuthHandleService.setSfHandleToNUll();

				
				return packageCompList;
			
		
		
	}
	public GetPkgCompList(EnvironmentDO envDO,String pkgid){
		this.envDO = envDO;
		this.pkgid=pkgid;
	}

	public List<Object> getListClient(){
		// Iterate thru the release information list in the target
		// env
		List<Object> packageCompList=null;
		
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();


				PackageCompInfoDAO pkgCompInfoDAO = new PackageCompInfoDAO();

				// find the PackageComponents associated with
				// PackageInformation
				packageCompList = pkgCompInfoDAO
						.findByPackageId(pkgid,
								fdGetSFoAuthHandleService
										.getSFoAuthHandle(envDO,
												Constants.CustomBaseOrgID));
				fdGetSFoAuthHandleService.setSfHandleToNUll();

				return packageCompList;
			
		
		
	}

	private List<Object> getRelInfoList() {
		return relInfoList;
	}

	private void setRelInfoList(List<Object> relInfoList) {
		this.relInfoList = relInfoList;
	}

	private EnvironmentDO getEnvDO() {
		return envDO;
	}

	private void setEnvDO(EnvironmentDO envDO) {
		this.envDO = envDO;
	}
	
	
}
