public class clsLandingPage {
	Releases__c objRelease;
	public String strStatus {get; set;} 
	public clsLandingPage(ApexPages.StandardController controller) {
		objRelease = (Releases__c) controller.getRecord();
		strStatus = 'Success';
	}
	public PageReference fetchMetadata(){
		if(DeploymentOperations.deployRelease(objRelease.Id) =='success'){
			return new PageReference('/'+ objRelease.Id); 
		}else{
            strStatus = DeploymentOperations.deployRelease(objRelease.Id);
            ApexPages.Message errorMessage = new ApexPages.Message(ApexPages.Severity.INFO, strStatus);
            ApexPages.addMessage(errorMessage);
			
			return null;
		}
	}
}