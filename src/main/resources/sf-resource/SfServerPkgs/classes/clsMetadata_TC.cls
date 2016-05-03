@isTest
private class clsMetadata_TC {
    static testMethod void myUnitTest() {
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults();
        objDS.BaseOrganizationId__c = UserInfo.getOrganizationId();
        upsert objDS;
        
        // TO DO: implement unit test
        Enviroment__c objEnv = new Enviroment__c();
        objEnv.Name ='Test';
        objEnv.Type__c='Sandbox';
        objEnv.Org_ID__c = userinfo.getOrganizationId() +'_'+userinfo.getUserId() ;
        objEnv.User_Name__c = userinfo.getUserName();
        insert objEnv;
        
        MetadataLog__c objML = new MetadataLog__c();
        objML.Action__c ='Retrieve';
        objML.OrganizationId__c = userinfo.getOrganizationId();
        objML.Status__c ='Open';        
        insert objML;
        
        objML.Status__c ='Completed';        
        update objML;
        
        MetadataDescription__c objMD= new MetadataDescription__c();
        objMD.MetadataLog__c = objML.Id;
        objMD.Name__c ='Test';
        objMD.Type__c ='ApexClasses';
        objMD.OrganizationId__c =userinfo.getOrganizationId();
        insert objMD;
        
        Package__c objP = new Package__c();
        objP.Name = 'Package1';
        insert objP;
        
        PackageComponent__c objPC = new PackageComponent__c();
        objPC.Name__c = 'Test';
        objPC.Type__c ='ApexClasses';
        objPC.Package__c = objP.Id;
        objPC.SourceOrganizationId__c = userinfo.getOrganizationId();
        objPC.Order__c =1;
        insert objPC;
        
        Releases__c objR = new Releases__c();
        objR.Name = 'TestRelease';
        insert objR;
        
        ReleasePackage__c objRP = new ReleasePackage__c(); 
        objRP.Package__c = objP.Id;
        objRP.Release__c =objR.Id;
        objRP.Order__c ='1';
        insert objRP;
        
        ReleaseEnvironment__c objRE = new ReleaseEnvironment__c();
        objRE.Releases__c = objR.Id;
        objRE.Environment__c = objEnv.Id;
        insert objRE;
        
        DeploymentDetails__c objDD = new DeploymentDetails__c();
        objDD.MetadataLog__c = objML.Id;
        objDD.Releases__c =objR.Id; 
        objDD.Name__c ='Test';
        objDD.Type__c ='ApexClassess';
        insert objDD;
        
        clsMetadata objCLS = new clsMetadata();
        objCLS.objPackage =objP;
        objCLS.fetchOrganizations();
        objCLS.strSelectedOrgId =userinfo.getOrganizationId();
        objCLS.fetchMetadata();
        objCLS.openSearch();
        
        objCLS.retriveMetadata();
        objCLS.getLatestRetrivedMetadataLogId(userinfo.getOrganizationId());
        objCLS.fetchRetrivedMetadata();
        objCLS.retrievePackageMetadata();
        objCLS.openEnvironments();
        
        objCLS.saveMetadata();
        objCLS.RemoveMetadataComponents();
        //objCLS.DeployMetadataDetails(); 
        //objCLS.validateOAuth12();
        
        ApexPages.StandardController controller01 = new ApexPages.StandardController(objEnv); 
        ApexPages.currentPage().getParameters().put('id',objEnv.Id);
        Auth objAuth = new Auth(controller01);
        objAuth.validateOAuthBase();
        objAuth.validateOAuth2();
        
        
        objEnv.Type__c='Production/Developer';        
        update objEnv;
        
        controller01 = new ApexPages.StandardController(objEnv); 
        ApexPages.currentPage().getParameters().put('id',objEnv.Id);
        objAuth = new Auth(controller01);
        objAuth.validateOAuthBase();
        objAuth.validateOAuth2();
        
        
        List<MetadataLog__c> lstMDL = new List<MetadataLog__c>();
        lstMDL.add(objML);
        DeploymentOperations.deployRelease(objR.Id);
        DeploymentOperations.updateReleaseDeployStatus(lstMDL);
        DeploymentOperations.cloneRelease(objR.Id);
        objCLS.objRelEnv1.Environment__c = objEnv.Id;
        
        Quick_Deploy__c objQD = new Quick_Deploy__c();
        objQD.Description__c ='Test';
        objQD.Status__c ='Open';
        insert objQD;
        objCLS.objQuickDeploy = objQD;
        objCLS.addPackageWithRelease();
        objCLS.retrieveReleaseStatus();
        objCLS.retrieveDeploymentDetails();
        objCLS.getSelectedMetadataDetails();
        
        controller01 = new ApexPages.StandardController(objR); 
        ApexPages.currentPage().getParameters().put('RId',objR.Id);
        ReleaseEnvironment objRECont = new ReleaseEnvironment(controller01);
        objRECont.objReleaseEnv.Environment__c =objEnv.Id;
        objRECont.saveRelease();
        objRECont.cancelRelease();
        objRECont = new ReleaseEnvironment();
        
        controller01 = new ApexPages.StandardController(objP); 
        objCLS = new clsMetadata(controller01);
        
        PostInstallClass postinstall = new PostInstallClass();
        //Test.testInstall(postinstall, null);
        
        SyncDeploymentOperations.deployReleaseActivate(objR.Id);
        SyncDeploymentOperations.deployReleaseDeActivate(objR.Id);
        SyncDeploymentOperations.getPackagesFromEnvironments(objR.Id);       
        
        delete objRP;
    }
    
    @isTest
    static void testInstallScript() {
        PostInstallClass postinstall = new PostInstallClass();
        
    
    }
}