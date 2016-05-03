trigger afterUpdateOnMetadatalog on MetadataLog__c (after update) {
    List<MetadataLog__c> lstMetadatalog = new List<MetadataLog__c>();
    for(MetadataLog__c  objMDL: trigger.new){
        if(trigger.oldMap.get(objMDL.Id).Status__c!= objMDL.Status__c){
            lstMetadatalog .add(objMDL);
        }
    }
    if(lstMetadatalog .size() >0){
        DeploymentOperations.updateReleaseDeployStatus(trigger.New);
    }
}