package com.services.component.release;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.domain.DeploymentSettingsDO;
import com.domain.PackageCompInfoDO;
import com.domain.PackageComponentDO;
import com.domain.PackageDO;
import com.domain.PackageInformationDO;
import com.ds.salesforce.dao.comp.PackageComponentDAO;
import com.ds.salesforce.dao.comp.PackageDAO;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Org;
import com.util.SFoAuthHandle;

public class CreatePackageComp {

	Org org;
	List<Object> pkgCompList;

	public CreatePackageComp(Org org, List<Object> pkgCompList) {
		super();
		this.org = org;
		this.pkgCompList = pkgCompList;
	}

	public CreatePackageComp(Org org) {
		super();
		this.org = org;
	}

	public void create(String pid, SFoAuthHandle sfHandle,
			LinkedList<GetPackageProcess> lst) {
		// Create PackageComponent objects in Base Org
		// set the earlier created Package id to these
		// PackageComponents
		FDGetSFoAuthHandleService fdGetSFoAuthHandleService = new FDGetSFoAuthHandleService();

		for (Iterator iterator = getPackageCompList().iterator(); iterator
				.hasNext();) {

			PackageCompInfoDO pkgCompInfoDO = (PackageCompInfoDO) iterator
					.next();

			PackageComponentDO pkgCompDO = pkgCompInfoDO.getPkgCompDO();

			pkgCompDO.setPkgParentId(pid);
			pkgCompDO.setParentPackageCompID(pkgCompInfoDO.getId());
			PackageComponentDAO pkgCompDAO = new PackageComponentDAO();
			pkgCompDAO.insert(pkgCompDO,
					fdGetSFoAuthHandleService.getSFoAuthHandle(getOrg()));
			try {
				String description = "Package Component "
						+ pkgCompDO.getObjName()
						+ "is created for  package ID "
						+ pkgCompDO.getPkgParentId()
						+ "in  Base Org  :"
						+ fdGetSFoAuthHandleService.getSFoAuthHandle(getOrg())
								.getEnterpriseConnection().getUserInfo()
								.getOrganizationId() + "";
				lst.add(new GetPackageProcess(description));
			} catch (Exception e) {

			}
		}
	}

	public void create(String pid, SFoAuthHandle sfHandle) {
		// Create PackageComponent objects in Base Org
		// set the earlier created Package id to these
		// PackageComponents
		for (Iterator iterator = getPackageCompList().iterator(); iterator
				.hasNext();) {

			PackageCompInfoDO pkgCompInfoDO = (PackageCompInfoDO) iterator
					.next();

			PackageComponentDO pkgCompDO = pkgCompInfoDO.getPkgCompDO();

			pkgCompDO.setPkgParentId(pid);
			pkgCompDO.setParentPackageCompID(pkgCompInfoDO.getId());
			PackageComponentDAO pkgCompDAO = new PackageComponentDAO();
			pkgCompDAO.insert(pkgCompDO, sfHandle);
			try {
				String description = "Package Component "
						+ pkgCompDO.getObjName()
						+ "is created for  package ID "
						+ pkgCompDO.getPkgParentId()
						+ "in  Base Org  :"
						+ sfHandle.getEnterpriseConnection().getUserInfo()
								.getOrganizationId() + "";
			} catch (Exception e) {

			}
		}
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	private List<Object> getPackageCompList() {
		return pkgCompList;
	}

	private void setPackageCompList(List<Object> packageCompList) {
		pkgCompList = packageCompList;
	}

}