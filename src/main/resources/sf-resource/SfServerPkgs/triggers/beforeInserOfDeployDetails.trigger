trigger beforeInserOfDeployDetails on DeploymentDetails__c (before insert) {
    Set<Id> setMetadatalogIds= new Set<Id>();
    Set<Id> setOrgIds= new Set<Id>();
    Map<Id,MetadataLog__c> mapMetadataLog;
    Map<String,Enviroment__c> mapOrgIdEnv;
    for(DeploymentDetails__c objDD: trigger.new){        
        if(objDD.MetadataLog__c != null && objDD.Releases__c == null){
            setMetadatalogIds.add(objDD.MetadataLog__c );
        }
        if(objDD.Name__c != null && objDD.Name__c !=''  && objDD.Name__c.length() >17 ){
            setOrgIds.add(objDD.Name__c.right(18));
        }
    }
    if(setMetadatalogIds.size()>0){
        mapMetadataLog = new Map<Id, MetadataLog__c>([ Select Id , Releases__c from MetadataLog__c where id in:setMetadatalogIds]);
        
    }
    if(setOrgIds.size() >0){    
        List<Enviroment__c> lstEnv = [Select Id, OrganizationId__c  from Enviroment__c where OrganizationId__c in:setOrgIds];
        if(lstEnv.size()>0){
            mapOrgIdEnv = new Map<String,Enviroment__c>();
            for(Enviroment__c objEnv : lstEnv){
                if(objEnv.OrganizationId__c != null && objEnv.OrganizationId__c !=''){
                    mapOrgIdEnv.put(objEnv.OrganizationId__c, objEnv);
                }
            }
        }
    }
    for(DeploymentDetails__c objDD: trigger.new){
        if(objDD.MetadataLog__c != null && objDD.Releases__c == null){
            if(mapMetadataLog.containsKey(objDD.MetadataLog__c)){
                objDD.Releases__c = mapMetadataLog.get(objDD.MetadataLog__c).Releases__c;
            }
        }
        if(objDD.Error_Message__c != null && objDD.Error_Message__c !=''){
            if(objDD.Error_Message__c.contains('sucessfully')){
                objDD.Status__c ='Success';
            }else if(objDD.Error_Message__c.contains('com.exception')){
                objDD.Status__c ='Failure';
            }
            
        }
        try{
        if(objDD.Name__c != null && objDD.Name__c !='' && objDD.Name__c.length() >17){
            if(mapOrgIdEnv.containsKey(objDD.Name__c.right(18))){
                objDD.Environment__c = mapOrgIdEnv.get(objDD.Name__c.right(18)).Id;
            }                
        }
        }catch (Exception ex){
        }
    }
}