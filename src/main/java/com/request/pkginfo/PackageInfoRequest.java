package com.request.pkginfo;

import java.util.List;

import com.domain.EnvironmentDO;
import com.domain.PackageInformationDO;
import com.domain.ReleaseInformationDO;
import com.request.BaseRequest;
import com.util.Org;

public class PackageInfoRequest extends BaseRequest {
	EnvironmentDO envDO;
	ReleaseInformationDO relInfoDO;
	PackageInformationDO pkgInfoDO;
	List<Object> pkgInfoList;
	List<String> pidList;
	String releaseId;
	Org org;
	
	public PackageInfoRequest(ReleaseInformationDO relInfoDO,
			EnvironmentDO envDO) {
		super();
		this.relInfoDO = relInfoDO;
		this.envDO = envDO;
	}

	public PackageInfoRequest(PackageInformationDO pkgInfoDO,
			EnvironmentDO envDO) {
		super();
		this.pkgInfoDO = pkgInfoDO;
		this.envDO = envDO;
	}

	public PackageInfoRequest(Org org, List<Object> pkgInfoList, EnvironmentDO envDO) {
		super(org);
		this.pkgInfoList = pkgInfoList;
		this.envDO = envDO;
		this.org = org;
	}
	
	public PackageInfoRequest(Org org, List<String> pidList, EnvironmentDO envDO, String releaseId) {
		super();
		this.org = org;
		this.pidList = pidList;
		this.envDO = envDO;
		this.releaseId = releaseId;
	}
	
	public PackageInfoRequest(ReleaseInformationDO relInfoDO,
			PackageInformationDO pkgInfoDO, EnvironmentDO envDO) {
		super();
		this.relInfoDO = relInfoDO;
		this.pkgInfoDO = pkgInfoDO;
		this.envDO = envDO;
	}

	public EnvironmentDO getEnvDO() {
		return envDO;
	}

	public void setEnvDO(EnvironmentDO envDO) {
		this.envDO = envDO;
	}

	public ReleaseInformationDO getRelInfoDO() {
		return relInfoDO;
	}

	public void setRelInfoDO(ReleaseInformationDO relInfoDO) {
		this.relInfoDO = relInfoDO;
	}

	public PackageInformationDO getPkgInfoDO() {
		return pkgInfoDO;
	}

	public void setPkgInfoDO(PackageInformationDO pkgInfoDO) {
		this.pkgInfoDO = pkgInfoDO;
	}

	public List<Object> getPkgInfoList() {
		return pkgInfoList;
	}

	public void setPkgInfoList(List<Object> pkgInfoList) {
		this.pkgInfoList = pkgInfoList;
	}

	public List<String> getPidList() {
		return pidList;
	}

	public void setPidList(List<String> pidList) {
		this.pidList = pidList;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	
}
