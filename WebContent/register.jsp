<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="connection.jsp" %>
<%
	String fullName = request.getParameter("fullname");
	String gender = request.getParameter("gender");
	String dob = request.getParameter("dob");
	String specialization = request.getParameter("specialization");
	
	System.out.println(fullName+" "+gender+" "+dob+" "+specialization);
	try{
		ResultSet set = con.createStatement().executeQuery("SELECT did from doctor WHERE specialization='"+specialization+"'");
		int did=0;
		if(set.next()) did = set.getInt(1);
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO patient(name,gender,dob,did) VALUES(?,?,?,?)");
		stmt.setString(1,fullName);
		stmt.setString(2,gender);
		stmt.setString(3,dob);
		stmt.setInt(4,did);
		stmt.execute();
		System.out.println(did);
	}
	catch(SQLException e){
		System.out.println(e.getMessage());
		response.setStatus(500);
		response.setContentType("text/plain");
		response.getWriter().write(e.getMessage());
	}
	
%>

