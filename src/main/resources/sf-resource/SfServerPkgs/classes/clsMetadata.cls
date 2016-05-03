public class clsMetadata {
    
    public List<SelectOption> optionOrg {get; set;}
    public Id strSelectedOrgId{get; set;}
    public String strDeploySelectedOrgId{get; set;}
    public List<Metadatawrap> lstMetadata {get; set;}
    public List<Metadatawrap> lstDisplayMetadata {get; set;}
    public List<DeployMetadatawrap> lstSelectedMetadata {get; set;}
    public List<DeployMetadatawrap> lstDisplaySelectedMetadata {get; set;}  
    public List<DeploymentDetails__c> lstDeployDetails {get; set;}
    public Boolean blnDisplaySearch {get;set;}
    public Boolean blnDisplayEnvironments {get;set;}
    
    public String strDeployStatus {get;set;}

    public Package__c objPackage {get;set;}
    public Quick_Deploy__c objQuickDeploy{get; set;}
    public MetadataLog__c objMetadataLogDetails {get;set;}
    public Set<String> setSelectComponentForPackage = new Set<String>();
    public ReleaseEnvironment__c objRelEnv1 {get;set;}
    public ReleaseEnvironment__c objRelEnv2 {get;set;}
    public ReleaseEnvironment__c objRelEnv3 {get;set;}
    public ReleaseEnvironment__c objRelEnv4 {get;set;}
    public ReleaseEnvironment__c objRelEnv5 {get;set;}
    public Map<String, Enviroment__c> mapOrgEnviroment {get; set;}
    public Boolean blnQuickDeploy{get; set;}
    // paging
    public PageManager objPageManager {get;set;}
    public PageManager objSelectedPageManager {get;set;}
    
    public clsMetadata(ApexPages.StandardController controller) {
        optionOrg = new List<SelectOption>();
        lstMetadata = new List<Metadatawrap>();
        lstDisplayMetadata = new List<Metadatawrap>();
        lstSelectedMetadata = new List<DeployMetadatawrap>();
        lstDisplaySelectedMetadata = new List<DeployMetadatawrap>();
        lstDeployDetails = new List<DeploymentDetails__c>() ;
        objMetadataLogDetails = new MetadataLog__c();
        objRelEnv1 = new ReleaseEnvironment__c();
        objRelEnv2 = new ReleaseEnvironment__c();
        objRelEnv3 = new ReleaseEnvironment__c();
        objRelEnv4 = new ReleaseEnvironment__c();
        objRelEnv5 = new ReleaseEnvironment__c();
        blnDisplaySearch = false;
        blnDisplayEnvironments = false;
        
        objPageManager = new PageManager(15); 
        objPageManager.page = 0;
        
        objSelectedPageManager = new PageManager(15); 
        objSelectedPageManager.page = 0;
        
        if(controller.getRecord().getsObjectType()==Package__c.sObjectType){
            objPackage=(Package__c) controller.getRecord();
            blnQuickDeploy = false;
        }else{
            blnQuickDeploy = true;
            objQuickDeploy=(Quick_Deploy__c) controller.getRecord();
            objPackage =[Select Id, Description__c, Status__c, Name, LastModifiedDate ,Send_Deployment_Complete_email__c from Package__c where QuickDeploy__c =: objQuickDeploy.Id];
        }
        retrievePackageMetadata();
        retrieveReleaseStatus();
        retrieveDeploymentDetails();
    }
    public clsMetadata(){        
        optionOrg = new List<SelectOption>();
        lstMetadata = new List<Metadatawrap>();
        lstDisplayMetadata = new List<Metadatawrap>();
        lstSelectedMetadata = new List<DeployMetadatawrap>();
        lstDisplaySelectedMetadata = new List<DeployMetadatawrap>();
        lstDeployDetails = new List<DeploymentDetails__c>() ;
        objMetadataLogDetails = new MetadataLog__c();
        objRelEnv1 = new ReleaseEnvironment__c();
        objRelEnv2 = new ReleaseEnvironment__c();
        objRelEnv3 = new ReleaseEnvironment__c();
        objRelEnv4 = new ReleaseEnvironment__c();
        objRelEnv5 = new ReleaseEnvironment__c();
        blnDisplaySearch = false;
        blnDisplayEnvironments = false;
        blnQuickDeploy =false;
        objPageManager = new PageManager(15);
        objPageManager.page = 0;
        
        objSelectedPageManager = new PageManager(15);
        objSelectedPageManager.page = 0;
        
        if(blnQuickDeploy){
            retrievePackageMetadata();
            retrieveReleaseStatus();
            retrieveDeploymentDetails();
        }
    }    
   public void fetchOrganizations(){
        List<Enviroment__c> lstEnv =[Select Id, Name, OrganizationId__c, Type__c from Enviroment__c];
        if(lstEnv != null){
            optionOrg.add(new SelectOption('', 'None'));
            for(Enviroment__c onjEnv:lstEnv){
                if(onjEnv.OrganizationId__c != null){
                    optionOrg.add(new SelectOption(onjEnv.OrganizationId__c,onjEnv.Name));
                }    
            }
            //strSelectedOrgId ='None';
            /*
            if(lstEnv != null && lstEnv.size()>0 && lstEnv.get(0).OrganizationId__c != null){
                strSelectedOrgId = lstEnv.get(0).OrganizationId__c;
                retriveMetadata();
            }
            */
        }
    }
    
    public PageReference fetchMetadata(){
        
        List<Enviroment__c> lstEnvironment = [Select Id, OrganizationId__c, TokenCode__c, TokenCodeNonEncrypted__c, RefreshTokenCode__c, Type__c, User_Name__c, Server_URL__c 
                                            from Enviroment__c
                                            where OrganizationId__c =:strSelectedOrgId];
        
        mapOrgEnviroment = new Map<String, Enviroment__c>();
        for(Enviroment__c objEnv: lstEnvironment){
            if(objEnv != null && objEnv.OrganizationId__c != null){ 
                mapOrgEnviroment.put(objEnv.OrganizationId__c,objEnv);
            }
        }
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults();     
        
        MetadataLog__c objMTlog = new MetadataLog__c();
        objMTlog.OrganizationId__c = strSelectedOrgId;
        objMTlog.Status__c ='Open';
        objMTlog.Action__c ='Retrieve';
        if (objDS != null) {
            objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
            objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
            objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
            objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
        }        
        //objMTLog.SourceOrgToken__c = mapOrgEnviroment.get(UserInfo.getOrganizationId()).TokenCode__c;
        if(mapOrgEnviroment.containsKey(strSelectedOrgId)){
            objMTLog.SourceOrgToken__c = mapOrgEnviroment.get(strSelectedOrgId).TokenCodeNonEncrypted__c; 
            objMTLog.SourceOrganizationURL__c = mapOrgEnviroment.get(strSelectedOrgId).Server_URL__c; 
            objMTLog.SourceOrgRefreshToken__c = mapOrgEnviroment.get(strSelectedOrgId).RefreshTokenCode__c; 
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
        public MetadataDescription__c objMTBDName{get;set;}
        
        //Wrapper  Class Controller
        Metadatawrap() {
            
        }
        
    }
    //Subclass : Wrapper Class 
    public class DeployMetadatawrap {
        //Static Variables 
        //public string id;
        public Boolean selected{get;set;}
        public PackageComponent__c objDMTBDName{get;set;}
        
        //Wrapper  Class Controller
        DeployMetadatawrap() {
            
        }
        
    }
    
    public PageReference saveMetadata(){
        List <PackageComponent__c > lstDeployMD = new List < PackageComponent__c > ();
        PackageComponent__c objDeploy ;
        List<Enviroment__c> lstEnvironment = [Select Id, OrganizationId__c, TokenCode__c, TokenCodeNonEncrypted__c, RefreshTokenCode__c, Type__c, User_Name__c, Server_URL__c 
                                            from Enviroment__c ];
        
        mapOrgEnviroment = new Map<String, Enviroment__c>();
        for(Enviroment__c objEnv: lstEnvironment){
            if(objEnv != null && objEnv.OrganizationId__c != null){ 
                mapOrgEnviroment.put(objEnv.OrganizationId__c,objEnv);
            }
        }
        for (Metadatawrap objMTDT: lstMetadata) {
            if(objMTDT.selected){
                objDeploy = new PackageComponent__c();
                //objDeploy.DeployedDateTime__c= Now;
                //objDeploy.MetadataLog__c = null;
                objDeploy.Name__c=objMTDT.objMTBDName.Name__c;
                objDeploy.SourceOrganizationId__c=objMTDT.objMTBDName.OrganizationId__c;
                //objDeploy.OrganizationId__c=objMTDT.objMTBDName.OrganizationId__c;
                objDeploy.Package__c=objPackage.Id;
                objDeploy.Type__c=objMTDT.objMTBDName.Type__c;              
                //objDeploy.Order__c=
                objDeploy.RequestedDateTime__c = System.Now(); 
                if(mapOrgEnviroment.containsKey(objDeploy.SourceOrganizationId__c)){
                    objDeploy.Environment__c = mapOrgEnviroment.get(objDeploy.SourceOrganizationId__c).Id;
                }
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
        if(blnQuickDeploy){
            PageReference page=New PageReference('/apex/QuickDeploy?id='+ objQuickDeploy.Id);
            page.setRedirect(true);
            return page; 
        }
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
    
    public MetadataLog__c getLatestRetrivedMetadataLogId(Id selectedOrgId){
        MetadataLog__c objRetrivedML;
        System.Debug('selectedOrgId---'+selectedOrgId);
        List<MetadataLog__c> lstMetadataLogs =[Select Id, OrganizationId__c, Status__c, LastModifiedDate, Action__c, Name from MetadataLog__c     
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
        List<PackageComponent__c> lstMTDT  = [SELECT Id, DeployedDateTime__c, Name__c, SourceOrganizationId__c,Package__c, Environment__c , Type__c,LastModifiedDate 
                                   FROM PackageComponent__c 
                                                 where Package__c =:objPackage.Id order by LastmodifiedDate desc limit 50000];
        for (PackageComponent__c objMTDT: lstMTDT) {
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
        String strQueryInitial='SELECT Id, Name__c, OrganizationId__c, RefreshDateTime__c, Type__c, LastModifiedDate  FROM MetadataDescription__c where OrganizationId__c =: strSelectedOrgId and MetadataLog__c=:metadatalogid ' ; 
        String strQuery='';
        List < Metadatawrap > lstwrap = new List < Metadatawrap > ();
        List < MetadataDescription__c > lstMTDT ;
                /*= [SELECT Id, Name__c, OrganizationId__c, RefreshDateTime__c, Type__c
                                   FROM MetadataDescription__c 
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
                                  
        for (MetadataDescription__c objMTDT: lstMTDT) {
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
        
        List<PackageComponent__c> lstDeployMetadataFinal = new List<PackageComponent__c>();
        for (DeployMetadatawrap objDMwrap: lstSelectedMetadata) {
            if(objDMwrap.selected){
                lstDeployMetadataFinal.add(objDMwrap.objDMTBDName);
            }
        }
        System.debug('lstDeployMetadata---'+ lstDeployMetadataFinal);
        if(lstDeployMetadataFinal.size() >0){
            delete lstDeployMetadataFinal;
        }        
        if(blnQuickDeploy){
            PageReference page=New PageReference('/apex/QuickDeploy?id='+ objQuickDeploy.Id);
            page.setRedirect(true);
            return page; 
        }
        return new PageReference('/'+ objPackage.Id); 
    }
    
    public PageReference addPackageWithRelease(){
        List<ReleaseEnvironment__c> lstReleaseEnvironments = new List<ReleaseEnvironment__c>();
        ReleaseEnvironment__c objRelEnv = new ReleaseEnvironment__c();
        Releases__c objRelease = new Releases__c();
        objRelease.Name = 'Quick Deploy ' + objPackage.Name ;
        objRelease.Status__c ='In-Progress';
        objRelease.Package__c = objPackage.Id;
        insert objRelease;
        
        if(blnQuickDeploy){
            objQuickDeploy.Status__c='In-Progress';
            update objQuickDeploy;
        }
        Set<Id> setEnvironments = new Set<Id>();
        
        List<Enviroment__c> lstEnvironment = [Select Id, OrganizationId__c, TokenCode__c, TokenCodeNonEncrypted__c, RefreshTokenCode__c, Type__c, User_Name__c, Server_URL__c 
                                            from Enviroment__c
                                            where OrganizationId__c =:strSelectedOrgId];
                                            
        if(lstEnvironment != null && lstEnvironment.size() >0){
            setEnvironments.add(lstEnvironment.get(0).Id);
        }
        
        /*
        setEnvironments.add(objRelEnv1.Environment__c);
        setEnvironments.add(objRelEnv1.Environment__c);
        setEnvironments.add(objRelEnv1.Environment__c);
        setEnvironments.add(objRelEnv1.Environment__c);
        setEnvironments.add(objRelEnv1.Environment__c);
        */
        for(Id objEnv: setEnvironments){
            objRelEnv = new ReleaseEnvironment__c();
            objRelEnv.Releases__c = objRelease.Id;
            objRelEnv.Environment__c = objEnv;
            lstReleaseEnvironments.add(objRelEnv);
        }                   
        insert lstReleaseEnvironments;
        
        ReleasePackage__c objRelPack = new ReleasePackage__c();
        objRelPack.Package__c =  objPackage.Id;
        objRelPack.Release__c =  objRelease.Id;
        insert  objRelPack;
        
        
        DeploymentOperations.deployRelease(objRelease.Id);  
        
        PageReference page=New PageReference('/apex/QuickDeploy?id='+ objQuickDeploy.Id);
        page.setRedirect(true);
        return page;
    }
    
    public void retrieveReleaseStatus(){  
        strDeployStatus= 'Open';    
        List<ReleasePackage__c> lstRelPackage =[Select Id, Package__c, Release__c,Release__r.Status__c, Order__c from ReleasePackage__c where Package__c =:objPackage.Id and Release__r.Name like 'Quick Deploy%' order by CreatedDate desc limit 1 ];
        if(lstRelPackage != null && lstRelPackage.size() >0){
            strDeployStatus = lstRelPackage.get(0).Release__r.Status__c;
        }
    }
    
    public static void createPackages(List<Quick_Deploy__c > lstQuickDeploy){
        if(lstQuickDeploy != null && lstQuickDeploy.size() >0){
            List<Package__c> lstPackage= new List<Package__c>();
            Package__c objPack = new Package__c();
            for(Quick_Deploy__c objQD: lstQuickDeploy){
                objPack = new Package__c();
                objPack.Name = objQD.Name;
                objPack.Description__c = objQD.Description__c;
                objPack.Status__c = objQD.Status__c;
                objPack.QuickDeploy__c = objQD.Id;
                lstPackage.add(objPack);
            }
            insert lstPackage;
        }
    }
    
    public void retrieveDeploymentDetails(){      
        List<ReleasePackage__c> lstRelPackage =[Select Id, Package__c, Release__c,Release__r.Status__c, Order__c from ReleasePackage__c where Package__c =:objPackage.Id and Release__r.Name like '%Auto' order by CreatedDate desc ];
        if(lstRelPackage != null && lstRelPackage.size() >0){
            Id idReleaseDeploy = lstRelPackage.get(0).Release__c;
            
            if(idReleaseDeploy != null){
                lstDeployDetails = [Select Id, Name,Name__c, Error_Message__c,Environment__c,Status__c, MetadataLog__c,Releases__c, Type__c,LastModifiedDate FROM DeploymentDetails__c 
                WHERE Releases__r.Package__c =: objPackage.Id 
                order by lastmodifieddate desc];
            }
        }
    }
    
}