package com.services.packaging;

import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.util.SFoAuthHandle;
import com.util.packaging.FileBasedDeployService;

public class PackageUtilService {

	public PackageUtilService() {
		super();
	}

	public void deployObjToTargetOrg(SFoAuthHandle sfSourceHandle)
			throws SFException {
		try {
			(new FileBasedDeployService()).deploy(sfSourceHandle, "");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e.toString());
			throw new SFException(e.toString(), SFErrorCodes.FileDeploy_Error);
		}
	}
}
