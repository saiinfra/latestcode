@isTest
private class clsMetadataInformation_TC {

    static testMethod void myUnitTest() {
        DeploymentSettingClient__c objDS = DeploymentSettingClient__c.getOrgDefaults();
        objDS.BaseOrganizationId__c = UserInfo.getOrganizationId();
        upsert objDS;        
             
        MetadataLogInformation__c objML = new MetadataLogInformation__c();
        objML.Action__c ='Retrieve';
        objML.OrganizationId__c = userinfo.getOrganizationId();
        objML.Status__c ='Open';        
        insert objML;
        
        objML.Status__c ='Completed';        
        update objML;
        
        MetadataDescriptionInformation__c objMD= new MetadataDescriptionInformation__c();
        objMD.MetadataLog__c = objML.Id;
        objMD.Name__c ='Test';
        objMD.Type__c ='ApexClasses';
        objMD.OrganizationId__c =userinfo.getOrganizationId();
        insert objMD;
        
        ReleaseInformation__c objR = new ReleaseInformation__c();
        objR.Status__c ='Active';
        objR.Name = 'TestRelease';
        insert objR;
        
        PackageInformation__c objP = new PackageInformation__c();
        objP.Release__c = objR.Id;
        objP.Name = 'Package1';
        insert objP;
        
        PackageComponentInformation__c objPC = new PackageComponentInformation__c();
        objPC.Name__c = 'Test';
        objPC.Type__c ='ApexClasses';
        objPC.Package__c = objP.Id;
        objPC.SourceOrganizationId__c = userinfo.getOrganizationId();
        objPC.Order__c ='1';
        insert objPC;
        
        DeploymentDetailsInformation__c objDD = new DeploymentDetailsInformation__c();
        objDD.MetadataLog__c = objML.Id;
        objDD.Name__c ='Test';
        objDD.Type__c ='ApexClassess';
        insert objDD;
        
        
        
        ApexPages.StandardController controller01 = new ApexPages.StandardController(objP); 
        clsMetadataInformation objCLS = new clsMetadataInformation(controller01);
        
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
        objCLS.getMetadataDetails();
        
        AuthClient objAuth = new AuthClient();
        objAuth.validateOAuthBase();
    }
}