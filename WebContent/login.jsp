<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ include file="connection.jsp" %>


<%

//the following piece of code checks
//if the user is already logged
//i.e if the session exists!
//if so redirect them to the appropriate panel.
String username = (String)session.getAttribute("user");
String userType = (String)session.getAttribute("type");
if(userType!=null){
	if(userType.equals("admin"))
		response.sendRedirect("admin/adminpanel.jsp");
	else if(userType.equals("doctor"))
		response.sendRedirect("doctor/doctorpanel.jsp");
	else if(userType.equals("pharmacist"))
		response.sendRedirect("pharmacist/pharmacistpanel.jsp");
	else if(userType.equals("nurse"))
		response.sendRedirect("nurse/nursepanel.jsp");
}



String user = request.getParameter("user");
String pass = request.getParameter("pass");

PreparedStatement stmt = con.prepareStatement("select * from users where username=? and password=?");
stmt.setString(1,user);
stmt.setString(2, pass);

ResultSet rs = stmt.executeQuery();
if(rs.next()){
	String type = rs.getString("type");
	int uid = rs.getInt("uid");
	
	//System.out.println(user+" "+pass+" "+type);
	
	session.setAttribute("user", user);
	session.setAttribute("type",type);
	session.setAttribute("uid",uid);
	
	if(type.equals("admin")){
		response.sendRedirect("admin/adminpanel.jsp");
	}
	else if(type.equals("doctor")){
		response.sendRedirect("doctor/doctorpanel.jsp");
	}
	else if(type.equals("nurse")){
		response.sendRedirect("nurse/nursepanel.jsp");
	}
	else if(type.equals("pharmacist")){
		response.sendRedirect("pharmacist/pharmacistpanel.jsp");
	}
}
else
{
	response.sendRedirect("error.jsp?errorType=invalidUser");
}
%>