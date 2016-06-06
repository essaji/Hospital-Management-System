<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="connection.jsp" %>
<%
//security check
String userType = (String)session.getAttribute("type");
if(userType==null || !userType.equals("admin")) response.sendRedirect("index.jsp");

try{
	String firstname = request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String type = request.getParameter("empType");
	String salary = request.getParameter("salary");
	String phone = request.getParameter("phone");
	String gender = request.getParameter("gender");
	

	PreparedStatement stmt = con.prepareStatement("INSERT INTO users(username,password,type) VALUES(?,?,?) ", Statement.RETURN_GENERATED_KEYS);
	stmt.setString(1, username);
	stmt.setString(2, password);
	stmt.setString(3, type);
	int uid=0;
	stmt.executeUpdate();
	
	ResultSet keys = stmt.getGeneratedKeys();
	if(keys!=null && keys.next()) uid = keys.getInt(1);
	
	stmt = con.prepareStatement("INSERT INTO employee(firstname,lastname,gender,salary,phone,uid) VALUES(?,?,?,?,?,?) ");
	stmt.setString(1,firstname);
	stmt.setString(2,lastname);
	stmt.setString(3,gender);
	stmt.setString(4,salary);
	stmt.setString(5,phone);
	stmt.setInt(6,uid);
	stmt.execute();
	
}catch(SQLException e){
	System.out.print(e.getMessage());
	response.setStatus(e.getErrorCode());
	response.setContentType("text/plain");
	response.getWriter().write(e.getMessage());
	return;
}

%>


<p>success.</p>