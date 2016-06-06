<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	    String username = (String)session.getAttribute("user");
		String userType = (String)session.getAttribute("type");
		if(!(username!=null && userType.equals("doctor")))
			response.sendRedirect("../index.jsp");	
			
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Doctor Panel</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
	<link href="../css/bootstrap-dialog.css" rel="stylesheet" />
    <link href="../css/simple-sidebar.css" rel="stylesheet">
    
    <!-- Javascript Includes -->
   <script src="../js/jquery-1.11.3.min.js"></script>
   <script src="../js/bootstrap.min.js"></script>
	<script src="../js/bootstrap-dialog.js"></script>
</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Doctor Menu
                    </a>
                </li>
                <li>
                    <a href="doctorpanel.jsp?page=dashboard">Dashboard</a>
                </li>
                <li>
                    <a href="doctorpanel.jsp?page=appointments">Appointments</a>
                </li>
                <li>
                    <a href="../logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <%
        	if(request.getParameter("page")==null || request.getParameter("page").equals("dashboard")){
        	
        %>
        	<div id="page-content-wrapper">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-lg-12">
	                        <h1>Welcome Dr. <%= username %>.</h1>
	                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><<</a>
	                    </div>
	                </div>
	            </div>
	        </div>
        <% }
        else if(request.getParameter("page").equals("appointments")){
        	
        %>
        	<div id="page-content-wrapper">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-lg-12">
	                        <h1>List of Patients</h1>
	                        <%@ include file="displayData.jsp" %>
	                    </div>
	                </div>
	            </div>
	        </div>
        <% } %>
        <!-- /#page-content-wrapper -->

    </div>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>