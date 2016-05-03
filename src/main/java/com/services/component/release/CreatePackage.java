package com.services.component.release;

import java.util.Iterator;
import java.util.List;

import com.domain.PackageDO;
import com.domain.PackageInformationDO;
import com.ds.salesforce.dao.comp.PackageDAO;
import com.services.component.FDGetSFoAuthHandleService;
import com.util.Org;
import com.util.SFoAuthHandle;

public class CreatePackage {

	PackageInformationDO pkgInfoDO;
	Org org;
	
	public CreatePackage(Org org, PackageInformationDO pkgInfoDO){
		super();
		this.org = org;
		this.pkgInfoDO = pkgInfoDO;
	}

	public CreatePackage(Org org){
		super();
		this.org = org;
	}

	public String create(PackageInformationDO pkgInfoDO,SFoAuthHandle sfHandle ){
		PackageDO pkgDO = new PackageDO(pkgInfoDO.getName(), pkgInfoDO.getDescription(),pkgInfoDO.getId());
		PackageDAO PkgDAO = new PackageDAO();
		String pid="";
		/*List<Object> list=PkgDAO.findByParentId(pkgInfoDO.getId(), sfHandle);
		if(list.size()>0)
		{
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				PackageDO PkgDo = (PackageDO) iterator.next();
				pid=PkgDo.getId();
				
			}
			
		}
		else
		{*/
		try{
			 pid = PkgDAO.insertAndGetId(pkgDO,sfHandle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		/*}*/
		
		return pid;
		
		
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
}