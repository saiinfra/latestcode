global class SyncDeploymentOperations{
    
    webservice static String deployReleaseActivate(Id strReleaseId){
       
        Id releaseIds = strReleaseId;
        Releases__c objRelease ;
        
        Id rtDeployedRelease =Schema.Sobjecttype.Releases__c.getRecordTypeInfosByName().get('Active').getRecordTypeId();
       
        if(releaseIds !=null || releaseIds!=''){
            objRelease =[Select Id, Name, Status__c from Releases__c where Id=:releaseIds];
            objRelease.Status__c ='Active';
            objRelease.RecordTypeId =rtDeployedRelease ;
            update objRelease;
        }
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults(); 
        
        
        MetadataLog__c objMTlog = new MetadataLog__c();
        objMTlog.Status__c ='Active';
        objMTlog.Action__c ='Active';
        objMTlog.ID__c = releaseIds;
        objMTlog.Name__c = objRelease.Name;     
        
        if (objDS != null) {
            objMTlog.OrganizationId__c = objDS.BaseOrganizationId__c;
            objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
            objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
            objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
            objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
        }
        insert objMTlog;
        
        return 'success';
    }
    webservice static String deployReleaseDeActivate(Id strReleaseId){
       
        Id releaseIds = strReleaseId;
        Releases__c objRelease ;
        Id rtDeployedRelease =Schema.Sobjecttype.Releases__c.getRecordTypeInfosByName().get('OpenRelease').getRecordTypeId();
        if(releaseIds !=null || releaseIds!=''){
            objRelease =[Select Id, Name, Status__c from Releases__c where Id=:releaseIds];
            objRelease.Status__c ='InActive';
            objRelease.RecordTypeId = rtDeployedRelease ;
            update objRelease;
        }
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults(); 
        
        
        MetadataLog__c objMTlog = new MetadataLog__c();
        objMTlog.Status__c ='InActive';
        objMTlog.Action__c ='InActive';
        objMTlog.ID__c = releaseIds;
        objMTlog.Name__c = objRelease.Name;     
        
        if (objDS != null) {
            objMTlog.OrganizationId__c = objDS.BaseOrganizationId__c;
            objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
            objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
            objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
            objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
        }
        insert objMTlog;
        
        return 'success';
     } 
     
    webservice static String getPackagesFromEnvironments(Id strReleaseId){
       
        Id releaseIds = strReleaseId;
        Releases__c objRelease ;
       
        if(releaseIds !=null || releaseIds!=''){
            objRelease =[Select Id, Name, Status__c from Releases__c where Id=:releaseIds];
            objRelease.Status__c ='Package retrieve in Progress';
            update objRelease;
        }
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults(); 
        
        
        MetadataLog__c objMTlog = new MetadataLog__c();
        objMTlog.Status__c ='Open';
        objMTlog.Action__c ='GetPackages';
        objMTlog.ID__c = releaseIds;
        objMTlog.Name__c = objRelease.Name;
        objMTlog.Releases__c = releaseIds ;      
        
        if (objDS != null) {
            objMTlog.OrganizationId__c = objDS.BaseOrganizationId__c;
            objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
            objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
            objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
            objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
        }
        insert objMTlog;
        
        return 'success';
    }  

    public static void updatePackageStatus(List<ReleasePackage__c> lstReleasePackage){
       
          
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults(); 
        MetadataLog__c objMTlog;
        List<MetadataLog__c> lstML =new List<MetadataLog__c>();
        for(ReleasePackage__c objRP: lstReleasePackage){
            System.debug('objRP.PackageParentId__c---->'+objRP.PackageParentId__c);
            if(objRP.PackageParentId__c!= ''){
                objMTlog = new MetadataLog__c();
                objMTlog.Status__c ='False';
                objMTlog.Action__c ='UpdatePackages';
                objMTlog.ID__c = objRP.PackageParentId__c;
                objMTlog.Releases__c = objRP.Release__c;
                //objMTlog.Name__c = 'Test';
                if (objDS != null) {
                    objMTlog.OrganizationId__c = objDS.BaseOrganizationId__c;
                    objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
                    objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
                    objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
                    objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
                }
                lstML.add(objMTlog);
            }
        }
        insert lstML;
    } 
}