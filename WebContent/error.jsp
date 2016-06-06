<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error!</title>
</head>
<body>
<%
	String errorType = request.getParameter("errorType");
	if(errorType!=null)
		if(errorType.equals("invalidUser"))
			out.println("Invalid user.");
		else if(errorType.equals("db"))
			response.sendError(500, "query error.");
		
	else{
		out.println("Unknown error occurred.");
	}
%>

<a href="index.jsp">Back to Index.</a>
</body>
</html>