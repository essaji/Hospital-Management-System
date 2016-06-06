<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checking Session</title>
</head>
<body>
<%
	String username = (String)session.getAttribute("user");
	String userType = (String)session.getAttribute("type");
	if(username!=null){
		if(!userType.equals("admin"))
			response.sendRedirect("index.jsp");
		else
			response.sendRedirect("error.jsp?erorType=invalidUser"); //for just now let suppose.
	}
	else{
		response.sendRedirect("error.jsp?erorType=invalidUser");
	}
%>
<br>
<a href="logout.jsp" >Logout</a>
</body>
</html>