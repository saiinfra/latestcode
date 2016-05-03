<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>First OAuth Application</title>
</head>

<script type="text/javascript" language="javascript">
    if (location.protocol != "https:") {
        document.write("OAuth will not work correctly from plain http. "+
                "Please use an https URL.");
        document.write("</p>");
        document.write("<a href=\"login.jsp\">register to get oAuth</a>");
    } else {
    	document.write("</p>");
        document.write("<a href=\"baseoauthservlet\">Click here to retrieve token from Salesforce via OAuth.</a>");
    }
</script>
</body>
</html>