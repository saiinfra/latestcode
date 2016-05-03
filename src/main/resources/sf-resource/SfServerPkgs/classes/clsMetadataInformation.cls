public class clsMetadataInformation {
    
    public List<SelectOption> optionOrg {get; set;}
    public Id strSelectedOrgId{get; set;}
    public String strDeploySelectedOrgId{get; set;}
    public List<Metadatawrap> lstMetadata {get; set;}
    public List<Metadatawrap> lstDisplayMetadata {get; set;}
    public List<DeployMetadatawrap> lstSelectedMetadata {get; set;}
    public List<DeployMetadatawrap> lstDisplaySelectedMetadata {get; set;}  
    public List<DeploymentDetailsInformation__c> lstDeployDetails {get; set;}
    public Boolean blnDisplaySearch {get;set;}
    public Boolean blnDisplayEnvironments {get;set;}
    
    public String strDeployStatus {get;set;}

    public PackageInformation__c objPackage {get;set;}
    public MetadataLogInformation__c objMetadataLogDetails {get;set;}
    public Set<String> setSelectComponentForPackage = new Set<String>();

    // paging
    public PageManagerClient objPageManager {get;set;}
    public PageManagerClient objSelectedPageManager {get;set;}
    
    public clsMetadataInformation(ApexPages.StandardController controller) {
        optionOrg = new List<SelectOption>();
        lstMetadata = new List<Metadatawrap>();
        lstDisplayMetadata = new List<Metadatawrap>();
        lstSelectedMetadata = new List<DeployMetadatawrap>();
        lstDisplaySelectedMetadata = new List<DeployMetadatawrap>();
        lstDeployDetails = new List<DeploymentDetailsInformation__c>() ;
        objMetadataLogDetails = new MetadataLogInformation__c();

        blnDisplaySearch = false;
        blnDisplayEnvironments = false;
        
        objPageManager = new PageManagerClient(15); 
        objPageManager.page = 0;
        
        objSelectedPageManager = new PageManagerClient(15); 
        objSelectedPageManager.page = 0;
        
        if(controller.getRecord().getsObjectType()==PackageInformation__c.sObjectType){
            objPackage=(PackageInformation__c) controller.getRecord();            
        }
        strSelectedOrgId = UserInfo.getOrganizationId();
        retrievePackageMetadata();
        retriveMetadata();
    }
    
   public void fetchOrganizations(){
        optionOrg.add(new SelectOption('', 'None'));
        optionOrg.add(new SelectOption(userinfo.getOrganizationId(), userinfo.getOrganizationName()));
    }
    
    public PageReference fetchMetadata(){
        lstMetadata.clear();
        DeploymentSettingClient__c objDS = DeploymentSettingClient__c.getOrgDefaults();     
        
        MetadataLogInformation__c objMTlog = new MetadataLogInformation__c();
        objMTlog.OrganizationId__c = strSelectedOrgId;
        objMTlog.Status__c ='Open';
        objMTlog.Action__c ='Retrieve';
        if (objDS != null) {
            objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
            objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
            objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
            objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
        
            objMTLog.SourceOrgToken__c = objDS.TokenCode__c;
            objMTLog.SourceOrganizationURL__c = objDS.Server_URL__c; 
            objMTLog.SourceOrgRefreshToken__c = objDS.RefreshTokenCode__c; 
        }
        insert objMTlog;
        objMetadataLogDetails = getLatestRetrivedMetadataLogId(strSelectedOrgId);
        return null;
    }
    
    public PageReference openSearch(){
        blnDisplaySearch = true;
        return null;
    }
    public PageReference openEnvironments(){
        blnDisplaySearch = false;
        blnDisplayEnvironments = true;
        return null;
    }
    public String MetadataList { get; set; }
    
    //Subclass : Wrapper Class 
    public class Metadatawrap {
        //Static Variables 
        //public string id;
        public Boolean selected{get;set;}
        public MetadataDescriptionInformation__c objMTBDName{get;set;}
        
        //Wrapper  Class Controller
        Metadatawrap() {
            
        }
        
    }
    //Subclass : Wrapper Class 
    public class DeployMetadatawrap {
        //Static Variables 
        //public string id;
        public Boolean selected{get;set;}
        public PackageComponentInformation__c objDMTBDName{get;set;}
        
        //Wrapper  Class Controller
        DeployMetadatawrap() {
            
        }
        
    }
    
    public PageReference saveMetadata(){
        List <PackageComponentInformation__c > lstDeployMD = new List < PackageComponentInformation__c > ();
        PackageComponentInformation__c objDeploy ;
        for (Metadatawrap objMTDT: lstMetadata) {
            if(objMTDT.selected){
                objDeploy = new PackageComponentInformation__c();
                //objDeploy.DeployedDateTime__c= Now;
                //objDeploy.MetadataLog__c = null;
                objDeploy.Name__c=objMTDT.objMTBDName.Name__c;
                objDeploy.SourceOrganizationId__c=objMTDT.objMTBDName.OrganizationId__c;
                //objDeploy.OrganizationId__c=objMTDT.objMTBDName.OrganizationId__c;
                objDeploy.Package__c=objPackage.Id;
                objDeploy.Type__c=objMTDT.objMTBDName.Type__c;              
                //objDeploy.Order__c=
                objDeploy.RequestedDateTime__c = System.Now();              
                lstDeployMD.add(objDeploy);
            }           
        }
        if(lstDeployMD.size() >0){
            insert lstDeployMD;
        }
        lstMetadata.clear();
        lstMetadata = fetchRetrivedMetadata();
        objPageManager.numberOfRows = lstMetadata.size();
        objSelectedPageManager.numberOfRows = lstDeployMD.size();        
        return new PageReference('/'+ objPackage.Id);  
            
    }
    
    public PageReference retriveMetadata(){
        lstMetadata.clear();
        objMetadataLogDetails = getLatestRetrivedMetadataLogId(strSelectedOrgId);
        System.Debug('objMetadataLogDetails--'+ objMetadataLogDetails);
        if(objMetadataLogDetails != null && objMetadataLogDetails.Status__c =='Completed'){
            lstMetadata = fetchRetrivedMetadata();
            objPageManager.numberOfRows = lstMetadata.size();            
        }           
        return null;
    }
    
    public MetadataLogInformation__c getLatestRetrivedMetadataLogId(Id selectedOrgId){
        MetadataLogInformation__c objRetrivedML;
        System.Debug('selectedOrgId---'+selectedOrgId);
        List<MetadataLogInformation__c> lstMetadataLogs =[Select Id, OrganizationId__c, Status__c, LastModifiedDate, Action__c, Name from MetadataLogInformation__c     
                                                            where OrganizationId__c=:selectedOrgId and Action__c='Retrieve' 
                                                            order by lastmodifieddate Desc limit 1];
        if(lstMetadataLogs != null){
            if(lstMetadataLogs.size() >0){
                objRetrivedML = lstMetadataLogs.get(0);
            }
        }   
        return objRetrivedML;
    }
    
    public void retrievePackageMetadata(){
        lstSelectedMetadata.clear();
        List<PackageComponentInformation__c> lstMTDT  = [SELECT Id, DeployedDateTime__c, Name__c, SourceOrganizationId__c,Package__c, Type__c,LastModifiedDate 
                                   FROM PackageComponentInformation__c 
                                                 where Package__c =:objPackage.Id order by LastmodifiedDate desc limit 50000];
        for (PackageComponentInformation__c objMTDT: lstMTDT) {
            DeployMetadatawrap awrap = new DeployMetadatawrap();
            awrap.objDMTBDName = objMTDT;
            awrap.selected = false;          
            lstSelectedMetadata.add(awrap);
            setSelectComponentForPackage.add(objMTDT.Name__c +'|'+ objMTDT.Type__c +'|'+ objMTDT.SourceOrganizationId__c);
        }
        
        objSelectedPageManager.numberOfRows = lstSelectedMetadata.size();
    }
    
    
    //Method to bring the list of MetadataDescription 
    public  List<Metadatawrap>  fetchRetrivedMetadata() {
        String fn ='';
        fn=ApexPages.currentPage().getParameters().get('Name');        
        String ln=''; 
        ln=ApexPages.currentPage().getParameters().get('Type');
        Id metadatalogid = objMetadataLogDetails.Id;
        String strQueryInitial='SELECT Id, Name__c, OrganizationId__c, RefreshDateTime__c, Type__c, LastModifiedDate  FROM MetadataDescriptionInformation__c where OrganizationId__c =: strSelectedOrgId and MetadataLog__c=:metadatalogid ' ; 
        String strQuery='';
        List < Metadatawrap > lstwrap = new List < Metadatawrap > ();
        List < MetadataDescriptionInformation__c > lstMTDT ;
                /*= [SELECT Id, Name__c, OrganizationId__c, RefreshDateTime__c, Type__c
                                   FROM MetadataDescriptionInformation__c 
                                                 where OrganizationId__c=: strSelectedOrgId  order by LastmodifiedDate desc limit 100
                                  ];*/
                                  
        if(fn != null && !fn.equals(''))
                strQuery+=' Name__c LIKE \''+fn+'%\' AND';
        if(ln !=null && !ln.equals(''))        
                strQuery+=' Type__c LIKE \''+ln+'%\' AND';
        
        
            if(!strQuery.equals('')){
                if(strQuery.endsWith('AND')){                                
                    strQuery = strQuery.substring(0,strQuery.length() -3);                    
                }
            }   
            if(!strQuery.equals('')){
                strQuery = strQueryInitial + ' AND '+strQuery;
            }else{
                strQuery = strQueryInitial ;
            }
            
            strQuery = strQuery + ' limit 1000';
            lstMTDT =  Database.query(strQuery);
            System.Debug('lstMTDT----->'+ lstMTDT);                       
        for (MetadataDescriptionInformation__c objMTDT: lstMTDT) {
            Metadatawrap awrap = new Metadatawrap();
            awrap.objMTBDName = objMTDT;
            awrap.selected = false; 
            if(!setSelectComponentForPackage.contains(objMTDT.Name__c+'|'+objMTDT.Type__c+'|'+objMTDT.OrganizationId__c)){
                lstwrap.add(awrap);
            }
        }
        return lstwrap;
     }
    
    public List<Metadatawrap> getMetadataDetails(){
        lstDisplayMetadata.clear();
        for(integer i=objPageManager.startIndex;i<objPageManager.endIndex&&i<lstMetadata.size();i++){
            lstDisplayMetadata.add(lstMetadata.get(i));
        }     
        System.debug('Final lstDisplayMetadata ------>'+ lstDisplayMetadata);        
        System.debug('Final objPageManager.startIndex ------>'+ objPageManager.startIndex);
        System.debug('Final objPageManager.endIndex ------>'+ objPageManager.endIndex);
        System.debug('Final lstMetadata-->'+lstMetadata.size());  
        return lstDisplayMetadata; 
    }
    
    public List<DeployMetadatawrap> getSelectedMetadataDetails(){
        lstDisplaySelectedMetadata.clear();
        for(integer i=objSelectedPageManager.startIndex;i<objSelectedPageManager.endIndex&&i<lstSelectedMetadata.size();i++){
            lstDisplaySelectedMetadata.add(lstSelectedMetadata.get(i));
        }
        return lstDisplaySelectedMetadata; 
    }
    
    public PageReference RemoveMetadataComponents(){
        
        List<PackageComponentInformation__c> lstDeployMetadataFinal = new List<PackageComponentInformation__c>();
        for (DeployMetadatawrap objDMwrap: lstSelectedMetadata) {
            if(objDMwrap.selected){
                lstDeployMetadataFinal.add(objDMwrap.objDMTBDName);
            }
        }
        System.debug('lstDeployMetadata---'+ lstDeployMetadataFinal);
        if(lstDeployMetadataFinal.size() >0){
            delete lstDeployMetadataFinal;
        }  
        return new PageReference('/'+ objPackage.Id); 
    }
    
    
    
}