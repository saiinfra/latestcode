public class AuthClient {
    public PageReference validateOAuthBase(){
        String urlAuth;
        String ClientId;
        String ClientSecret;
        String redirect_uri;
        
        DeploymentSettingClient__c objDS = DeploymentSettingClient__c.getOrgDefaults();
        
         
            ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsZqXGXSs976uKfivATtaFl6rhaqwmMvzgd26NEEvc3wpiPBjxaMR2s3ITjsa';
            ClientSecret = '4904334507055360250';
            redirect_uri='https://sfinfraws.herokuapp.com/baseoauthclientservlet/callback';
            if(runningInASandbox()){  
                urlAuth = 'https://test.salesforce.com/services/oauth2/authorize?prompt=login&response_type=code' +
                        '&client_id=' + ClientId +                     
                        '&client_secret=' + ClientSecret +
                        '&redirect_uri='+redirect_uri+
                        '&state='+'T';
                                   
            }else{
                urlAuth = 'https://login.salesforce.com/services/oauth2/authorize?prompt=login&response_type=code' +
                        '&client_id=' + ClientId +                     
                        '&client_secret=' + ClientSecret +
                        '&redirect_uri='+redirect_uri+
                        '&state='+'L';                  
                        
            }
        PageReference objDetailPage; 
        objDetailPage = new PageReference(urlAuth); 
        objDetailPage.setRedirect(true);
        return objDetailPage;   
        
    }
    
       
    public static Boolean runningInASandbox() {
        return [SELECT Id, IsSandbox FROM Organization where Id =: userinfo.getOrganizationId() LIMIT 1].IsSandbox;
    }
    
}