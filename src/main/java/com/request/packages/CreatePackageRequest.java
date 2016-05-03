package com.request.packages;

import com.domain.PackageInformationDO;
import com.request.BaseRequest;
import com.util.Org;
import com.util.SFoAuthHandle;

public class CreatePackageRequest extends BaseRequest {
	
	PackageInformationDO pkgInfoDO;
	SFoAuthHandle sfHandle;
	Org org;
	
	public CreatePackageRequest(Org org){
		super(org);
		this.org = org;
	}
	
	public CreatePackageRequest(Org org, PackageInformationDO pkgInfoDO, SFoAuthHandle sfHandle){
		super(org);
		this.org = org;
		this.pkgInfoDO = pkgInfoDO;
		this.sfHandle = sfHandle;
	}
	

	public PackageInformationDO getPkgInfoDO() {
		return pkgInfoDO;
	}

	public void setPkgInfoDO(PackageInformationDO pkgInfoDO) {
		this.pkgInfoDO = pkgInfoDO;
	}

	public SFoAuthHandle getSfHandle() {
		return sfHandle;
	}

	public void setSfHandle(SFoAuthHandle sfHandle) {
		this.sfHandle = sfHandle;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
	
}
