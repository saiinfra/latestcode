trigger onAfterDeleteReleasePackage on ReleasePackage__c (after delete) {
    SyncDeploymentOperations.updatePackageStatus(trigger.old);
}