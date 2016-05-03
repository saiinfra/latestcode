global class DeploymentOperations{
    webservice static String deployRelease(Id strReleaseId){
        List<DeployMetadata__c> lstDeployMetadataInsert =new List<DeployMetadata__c> ();
        List<DeployMetadata__c> lstDeployMetadataUpdate =new List<DeployMetadata__c> ();
        Set<Id> setDeplyRecordIds = new Set<Id>();
        Set<Id> setEnvRecordIds = new Set<Id>();
        Map<String,ReleasePackage__c> mapReleasePackage = new Map<String, ReleasePackage__c>();
        Id releaseIds = strReleaseId;
        Releases__c objRelease ;
        Id rtDeployedRelease =Schema.Sobjecttype.Releases__c.getRecordTypeInfosByName().get('DeployedRelease').getRecordTypeId();
        List<ReleaseEnvironment__c> lstRelEnv ;
        List<ReleasePackage__c> lstRelPackage;
        
        if(releaseIds !=null || releaseIds!=''){
            lstRelEnv =[Select Id, Environment__c,Environment__r.OrganizationId__c, Releases__c from ReleaseEnvironment__c where Releases__c=:releaseIds];
            System.Debug('lstRelEnv-->'+ lstRelEnv);
            System.Debug('lstRelEnv.size-->'+ lstRelEnv.size());
            if(lstRelEnv != null && lstRelEnv.size() ==0){
                return 'Please select environment for deployment'; 
            }
            
            lstRelPackage =[Select Id, Package__c, Release__c, Order__c from ReleasePackage__c where Release__c=:releaseIds];
            if(lstRelPackage != null && lstRelPackage.size() ==0){
                return 'Please select package for deployment'; 
            }
            objRelease =[Select Id, Status__c from Releases__c where Id=:releaseIds];
            //objRelease.Status__c ='In-Progress';
            objRelease.RecordTypeId =rtDeployedRelease;
            update objRelease;
        } 
        lstRelPackage =[Select Id, Package__c, Release__c, Order__c from ReleasePackage__c where Release__c=:releaseIds];
        if(lstRelPackage != null){
            for(ReleasePackage__c objRelPack:lstRelPackage){
                if(objRelPack.Package__c!= null){
                   setDeplyRecordIds.add(objRelPack.Package__c); 
                    mapReleasePackage.put(releaseIds+''+objRelPack.Package__c,objRelPack );
                }                
            }
        }        
        
        Map<String, ReleaseEnvironment__c> mapReleaseOrgWithRelease = new Map<String, ReleaseEnvironment__c>();
        if(lstRelEnv != null){
            for(ReleaseEnvironment__c objRelEnv:lstRelEnv){
                if(objRelEnv.Environment__c!= null && objRelEnv.Environment__r.OrganizationId__c != null){
                   setEnvRecordIds.add(objRelEnv.Environment__r.OrganizationId__c); 
                   mapReleaseOrgWithRelease.put(objRelEnv.Releases__c +'|'+objRelEnv.Environment__r.OrganizationId__c, objRelEnv);
                }                
            }
        }
        
        List<Enviroment__c> lstEnvironment = [Select Id, OrganizationId__c, TokenCode__c, TokenCodeNonEncrypted__c,RefreshTokenCode__c, Type__c, User_Name__c 
                                            from Enviroment__c
                                            where OrganizationId__c in:setEnvRecordIds];
        
                
        Map<String, Enviroment__c> mapOrgEnviroment = new Map<String, Enviroment__c>();
        for(Enviroment__c objEnv: lstEnvironment){
            if(objEnv != null && objEnv.OrganizationId__c != null){
                mapOrgEnviroment.put(objEnv.OrganizationId__c,objEnv);
            }
        }
        
        List<PackageComponent__c> lstDeployMetadata =[Select Id, DeployedDateTime__c, Name__c,Order__c,SourceOrganizationId__c,Package__c,RequestedDateTime__c,Type__c
                                                            from PackageComponent__c where Package__c=:setDeplyRecordIds];
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults(); 
        List<MetadataLog__c> lstMTlog = new List<MetadataLog__c>();
        for(String strEnv: setEnvRecordIds){
        
            MetadataLog__c objMTlog = new MetadataLog__c();
            objMTlog.OrganizationId__c = strEnv.trim();
            objMTlog.Status__c ='Open';
            objMTlog.Action__c ='Deploy';
            objMTlog.Releases__c = releaseIds;
            if(mapReleaseOrgWithRelease != null && mapReleaseOrgWithRelease.containsKey(releaseIds +'|'+ objMTlog.OrganizationId__c)){
                objMTLog.ReleaseEnvironment__c = mapReleaseOrgWithRelease.get(releaseIds +'|'+ objMTlog.OrganizationId__c).Id;
            }
            
            if (objDS != null) {
                objMTLog.BaseOrgToken__c = objDS.TokenCode__c;
                objMTlog.BaseOrgId__c = objDS.BaseOrganizationId__c;
                objMTlog.BaseOrgUrl__c = objDS.Server_URL__c;
                objMTlog.BaseOrgRefreshToken__c = objDS.RefreshTokenCode__c;
            }  
            
            //objMTLog.SourceOrgToken__c = mapOrgEnviroment.get(UserInfo.getOrganizationId()).TokenCode__c;
            if(mapOrgEnviroment.containsKey(strEnv.trim())){
                objMTLog.TargetOrgTokenNonEncrypted__c = mapOrgEnviroment.get(strEnv.trim()).TokenCodeNonEncrypted__c;
                objMTLog.TargetOrgToken__c = mapOrgEnviroment.get(strEnv.trim()).TokenCode__c;
                objMTlog.TargetOrgRefreshToken__c = mapOrgEnviroment.get(strEnv.trim()).RefreshTokenCode__c;
                
            }
            
            lstMTlog.add(objMTlog);
        }
        if(lstMTlog.size()>0){
            insert lstMTlog;
        }

        for(PackageComponent__c objDM: lstDeployMetadata){
            //for(MetadataLog__c objMTlog: lstMTlog){
            for(Integer i=0; i< lstMTlog.size() ; i++){
                                
                DeployMetadata__c objDMNew = new DeployMetadata__c();
                objDMNew.OrganizationId__c = lstMTlog.get(i).OrganizationId__c;
                objDMNew.MetadataLog__c = lstMTlog.get(i).Id;
                objDMNew.Release__c = releaseIds;
                objDMNew.SourceOrganizationId__c = objDM.SourceOrganizationId__c;
                objDMNew.DeployedDateTime__c = System.Now();
                objDMNew.Name__c = objDM.Name__c;
                objDMNew.Order__c = Integer.valueOf(mapReleasePackage.get(releaseIds +''+ objDM.Package__c).Order__c);
                objDMNew.Package__c = objDM.Package__c;
                objDMNew.RequestedDateTime__c = objDM.RequestedDateTime__c;
                objDMNew.Type__c = objDM.Type__c;                   
                lstDeployMetadataInsert.add(objDMNew);
                
            }           
        }
        
        if( lstDeployMetadataInsert.size() >0){
            insert lstDeployMetadataInsert;
        }
        
        return 'success';
    }
    
    public static void updateReleaseDeployStatus(List<MetadataLog__c> lstMetaDataLog){
        if(lstMetaDataLog != null && lstMetaDataLog.size() >0){
            Set<Id> setReleaseEnvIds= new Set<Id>();
            Set<Id> setReleaseIds= new Set<Id>();
            Map<Id,MetadataLog__c> mapReleaseEnvMetadataLogs= new Map<Id,MetadataLog__c>();
            Map<Id, Set<String>> mapReleasesStatus = new Map<Id, Set<String>>();
            Map<Id, Id> mapReleasesQuickDeploy = new Map<Id, Id>();
            Releases__c objRelease ;
            Quick_Deploy__c objQuickDeploy ;
            List<Releases__c> lstRelease = new List<Releases__c>();
            List<Quick_Deploy__c> lstQuickDeploy = new List<Quick_Deploy__c>();
            List<ReleaseEnvironment__c> lstReleasesEnvironments = new List<ReleaseEnvironment__c>();
            List<ReleasePackage__c> lstReleasesPackages = new List<ReleasePackage__c>();
            for(MetadataLog__c objMDL: lstMetaDataLog){
                setReleaseEnvIds.add(objMDL.ReleaseEnvironment__c);
                setReleaseIds.add(objMDL.Releases__c);
                mapReleaseEnvMetadataLogs.put(objMDL.ReleaseEnvironment__c, objMDL);
            }
            if(setReleaseEnvIds.size() >0){
                List<ReleaseEnvironment__c> lstRelEnv =[Select Id,Releases__c, Environment__c,Status__c from ReleaseEnvironment__c where Id=:setReleaseEnvIds];
                
                if(lstRelEnv != null){
                    for(ReleaseEnvironment__c objRelEnv:lstRelEnv){
                        if(mapReleaseEnvMetadataLogs.containsKey(objRelEnv.Id)){
                            objRelEnv.Status__c =mapReleaseEnvMetadataLogs.get(objRelEnv.Id).Status__c;     
                        }                        
                    }
                    update lstRelEnv;
                }
            }
            
            lstReleasesEnvironments =[Select Id,Releases__c, Environment__c,Status__c from ReleaseEnvironment__c where Releases__c in:setReleaseIds];
            
            lstReleasesPackages =[Select Id,Release__c, Package__c, Package__r.QuickDeploy__c from ReleasePackage__c where Release__c in:setReleaseIds];
            
            if(lstReleasesPackages.size() >0){
                for(ReleasePackage__c objTempRelease: lstReleasesPackages){
                    if(objTempRelease.Package__c != null && objTempRelease.Package__r.QuickDeploy__c != null){
                        mapReleasesQuickDeploy.put(objTempRelease.Release__c,objTempRelease.Package__r.QuickDeploy__c);
                    }
                }
            }
            
            if(lstReleasesEnvironments.size() >0){
                for(ReleaseEnvironment__c objTempRelease: lstReleasesEnvironments){
                    if(mapReleasesStatus.containsKey(objTempRelease.Releases__c)){
                        mapReleasesStatus.get(objTempRelease.Releases__c).add(objTempRelease.Status__c);
                    }else{                      
                        mapReleasesStatus.put(objTempRelease.Releases__c, new Set<String>());
                        mapReleasesStatus.get(objTempRelease.Releases__c).add(objTempRelease.Status__c);
                    }
                }
                Id rtOpenRelease =Schema.Sobjecttype.Releases__c.getRecordTypeInfosByName().get('OpenRelease').getRecordTypeId();
                for(Id releaseId:mapReleasesStatus.keySet()){
                    if(mapReleasesStatus.get(releaseId).contains('Error')){
                        objRelease = new Releases__c(Id=releaseId, Status__c='Failure', recordtypeid=rtOpenRelease);
                        lstRelease.add(objRelease);
                        if(mapReleasesQuickDeploy.containsKey(releaseId)){
                            objQuickDeploy = new Quick_Deploy__c(Id=mapReleasesQuickDeploy.get(releaseId), Status__c='Failure');
                            lstQuickDeploy.add(objQuickDeploy) ;
                        }
                        
                        continue;
                    }else if(mapReleasesStatus.get(releaseId).contains('In-Progress')){
                        objRelease = new Releases__c(Id=releaseId, Status__c='In-Progress');
                        lstRelease.add(objRelease);
                        if(mapReleasesQuickDeploy.containsKey(releaseId)){
                            objQuickDeploy = new Quick_Deploy__c(Id=mapReleasesQuickDeploy.get(releaseId), Status__c='In-Progress');
                            lstQuickDeploy.add(objQuickDeploy) ;
                        }
                        continue;
                    }else if(mapReleasesStatus.get(releaseId).contains('Open')){
                        objRelease = new Releases__c(Id=releaseId, Status__c='In-Progress');
                        lstRelease.add(objRelease);
                        if(mapReleasesQuickDeploy.containsKey(releaseId)){
                            objQuickDeploy = new Quick_Deploy__c(Id=mapReleasesQuickDeploy.get(releaseId), Status__c='In-Progress');
                            lstQuickDeploy.add(objQuickDeploy) ;
                        }
                        continue;
                    }else{
                        objRelease = new Releases__c(Id=releaseId, Status__c='Completed');
                        lstRelease.add(objRelease);
                        if(mapReleasesQuickDeploy.containsKey(releaseId)){
                            objQuickDeploy = new Quick_Deploy__c(Id=mapReleasesQuickDeploy.get(releaseId), Status__c='Completed');
                            lstQuickDeploy.add(objQuickDeploy) ;
                        }
                        continue;   
                    }
                }
                update lstRelease; 
                if(lstQuickDeploy.size()>0){
                    update lstQuickDeploy;
                }
            }
        
        } 
    }
    
    webservice static String cloneRelease(Id strReleaseId){

        List<ReleasePackage__c> lstRelasePackage = new List<ReleasePackage__c>();
        List<ReleaseEnvironment__c> lstReleaseEnv = new List<ReleaseEnvironment__c>();
        
        Id releaseIds = strReleaseId;
        Releases__c objExistingRelease ;
        Releases__c objClonedRelease ;
        Id rtOpenRelease =Schema.Sobjecttype.Releases__c.getRecordTypeInfosByName().get('OpenRelease').getRecordTypeId();
        if(releaseIds !=null || releaseIds!=''){
            objExistingRelease =[Select Id, Name, Status__c from Releases__c where Id=:releaseIds];
            //objRelease.Status__c ='In-Progress';
            objClonedRelease=   new Releases__c();
            objClonedRelease.RecordTypeId =rtOpenRelease;
            objClonedRelease.Status__c = 'Open';
            objClonedRelease.Name = objExistingRelease.Name;
            insert objClonedRelease;
        } 
        List<ReleasePackage__c> lstRelPackage =[Select Id, Package__c, Release__c, Order__c from ReleasePackage__c where Release__c=:releaseIds];
        if(lstRelPackage != null){
            ReleasePackage__c objRP;
            for(ReleasePackage__c objRelPack:lstRelPackage){
                if(objRelPack.Package__c!= null){
                    objRP = new ReleasePackage__c();
                    objRP.Package__c = objRelPack.Package__c;
                    objRP.Release__c = objClonedRelease.Id;
                    objRP.Order__c = objRelPack.Order__c;
                    lstRelasePackage.add(objRP);
                }                
            }
            if(lstRelasePackage.size() >0){
                insert lstRelasePackage;
            }
        }
        
        List<ReleaseEnvironment__c> lstRelEnv =[Select Id, Environment__c,Environment__r.OrganizationId__c, Releases__c from ReleaseEnvironment__c where Releases__c=:releaseIds];
        if(lstRelEnv != null){
            ReleaseEnvironment__c objRE;
            for(ReleaseEnvironment__c objRelEnv:lstRelEnv){
                if(objRelEnv.Environment__c!= null){
                    objRE = new ReleaseEnvironment__c();
                    objRE.Environment__c = objRelEnv.Environment__c;
                    objRE.Releases__c = objClonedRelease.Id;
                    lstReleaseEnv.add(objRE);
                }                
            }
            if(lstReleaseEnv.size() >0){
                insert lstReleaseEnv;
            }
        }
        
        return objClonedRelease.Id;
    }

    
}