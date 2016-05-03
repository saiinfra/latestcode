global class PostInstallClass implements InstallHandler {
    global void onInstall(InstallContext context) {
        //if(context.previousVersion() == null) {
        
            DeploymentSetting__c objDS = DeploymentSetting__c.getOrgDefaults();
            if (objDS == null) objDS = new DeploymentSetting__c();
            objDS.BaseOrganizationId__c = System.userinfo.getOrganizationId();
            objDS.Server_URL__c = String.valueOf(System.URL.getSalesforceBaseURL());
            upsert objDS;
            /*
            String urlAuth;
            String ClientId;
            String ClientSecret;
            String redirect_uri;
            
            if(objEnv.Type__c =='Sandbox'){
                ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsbYJXSOdJrdCVqxaLEyjnLDaPGLekViuBqlQJcWVyZodXI42r34WH9F5wexo';
                ClientSecret = '1673914982220759042';
                redirect_uri='https://sfinfraws.herokuapp.com/customoauthservlet/callback';
                
                urlAuth = 'https://emc--oppvis.cs1.my.salesforce.com/services/oauth2/authorize?prompt=login&response_type=code' +
                    '&client_id=' + ClientId +                     
                    '&client_secret=' + ClientSecret +
                    '&redirect_uri='+redirect_uri;
                    
            }else{
                ClientId = '3MVG9fMtCkV6eLhckipcGtsdEsXpvuj0tgLP6bJJYaFcdwyUC5dmSJ.Oi2e6vkHAZMrNBY6k8y9Qf.QWFahCK';
                ClientSecret = '7048969593420516911';
                redirect_uri='https://sfinfraws.herokuapp.com/OAuthServlet/callback';
            
                urlAuth = 'https://login.salesforce.com/services/oauth2/authorize?prompt=login&response_type=code' +
                    '&client_id=' + ClientId +                     
                    '&client_secret=' + ClientSecret +
                    '&redirect_uri='+redirect_uri;

            }
                        
            PageReference objDetailPage;
            objDetailPage = new PageReference(urlAuth); 
            objDetailPage.setRedirect(true);
            */
        //}    
    }
    
    
 }