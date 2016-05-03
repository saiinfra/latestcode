trigger afterInsertOnQuickDeploy on Quick_Deploy__c (after insert) {
    clsMetadata.createPackages(trigger.New);
}