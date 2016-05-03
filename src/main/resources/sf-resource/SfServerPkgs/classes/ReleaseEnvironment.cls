public class ReleaseEnvironment{
    public ReleaseEnvironment__c objReleaseEnv {get; set;}
    Id idReleaseId {get; set;}
    public ReleaseEnvironment(ApexPages.StandardController controller) {
        idReleaseId =ApexPages.currentPage().getParameters().get('RId');
        objReleaseEnv = new ReleaseEnvironment__c();
        objReleaseEnv.Releases__c = idReleaseId;  
    }
    public ReleaseEnvironment(){
        objReleaseEnv = new ReleaseEnvironment__c();
        idReleaseId =ApexPages.currentPage().getParameters().get('RId'); 
        objReleaseEnv.Releases__c = idReleaseId;        
    }
    
    
    public PageReference saveRelease(){
        insert objReleaseEnv;
        return new PageReference('/'+ idReleaseId); 
    }   
    
    public PageReference cancelRelease(){
        return new PageReference('/'+ idReleaseId);
    }
}