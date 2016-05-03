package com.services.component;

import java.util.List;

import com.util.XMLUtil;  

public class FDSFXMLPackageCompService {

	public FDSFXMLPackageCompService(){  
		super();
	}
	
	public void createPackageXML(List metadataObjList,String metadataLogId) {
		XMLUtil.doPreProcessing(metadataLogId);
		XMLUtil.createDeployXMLFile(metadataObjList,metadataLogId);
	}
	
}
