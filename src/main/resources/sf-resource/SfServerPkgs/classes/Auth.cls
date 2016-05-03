public class Auth {
    Id EnviromentId ;
    Enviroment__c objEnv;
    public Auth(ApexPages.StandardController controller) {
        EnviromentId = controller.getRecord().Id;
        objEnv =(Enviroment__c) controller.getRecord();
    }
    public Auth(){
        
    }
     public PageReference validateOAuthBase(){
        String urlAuth;
        String ClientId;
        String ClientSecret;
        String redirect_uri;
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults();
        String strMyState = EnviromentId ;
        
            //ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsbYJXSOdJrdCVqxaLEyjnLDaPGLekViuBqlQJcWVyZodXI42r34WH9F5wexo';
           // ClientSecret = '1673914982220759042';
           // redirect_uri='https://sfinfraws.herokuapp.com/customoauthservlet/callback';
            
            ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsbYJXSOdJrdCVqxaLEyjnLDaPGLekViuBqlQJcWVyZodXI42r34WH9F5wexo';
            ClientSecret = '1673914982220759042';
            redirect_uri='https://sfinfraws.herokuapp.com/baseoauthservlet/callback';
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
    
    public PageReference validateOAuth2(){
        String urlAuth;
        String ClientId;
        String ClientSecret;
        String redirect_uri;
        
        DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults();
        String strMyState = EnviromentId + '|'+ objDS.BaseOrganizationId__c  +'|' + objDS.Server_URL__c +'|'+ objDS.TokenCode__c+ '|'+ objDS.RefreshTokenCode__c;
        
       // ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsXpvuj0tgLP6bJJYaFcdwyUC5dmSJ.Oi2e6vkHAZMrNBY6k8y9Qf.QWFahCK';
        
       // ClientSecret = '7048969593420516911';
        //redirect_uri='https://sfinfraws.herokuapp.com/OAuthServlet/callback';
          
        ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsXpvuj0tgLP6bJJYaFcdwyUC5dmSJ.Oi2e6vkHAZMrNBY6k8y9Qf.QWFahCK';
        
        ClientSecret = '7048969593420516911';
        redirect_uri='https://sfinfraws.herokuapp.com/OAuthServlet/callback';
        if(objEnv.Type__c=='Sandbox'){  
            urlAuth = 'https://test.salesforce.com/services/oauth2/authorize?prompt=login&response_type=code' +
                        '&client_id=' + ClientId +                     
                        '&client_secret=' + ClientSecret +
                        '&redirect_uri='+redirect_uri+
                        '&state='+'T|'+strMyState ;
        }else{
            urlAuth = 'https://login.salesforce.com/services/oauth2/authorize?prompt=login&response_type=code' +
                        '&client_id=' + ClientId +                     
                        '&client_secret=' + ClientSecret +
                        '&redirect_uri='+redirect_uri+
                        '&state='+'L|'+strMyState ;
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