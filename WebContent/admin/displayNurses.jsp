<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="hmsModels.*"  %>
     <%@ page import="hmsControllers.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style type="text/css">
	#nurse-modal-body{
		height: 200px;
	}
</style>
</head>
<body>
<div id="nurses" class="tab-pane fade in">
				<h3>Nurses' Record</h3>
				<table class="table table-hover table-bordered" id="displayNurses">
					<thead>
						<tr>
							<th>
								First Name
							</th>
							<th>
								Last Name
							</th>
							<th>
								Username
							</th>
							<th>
								Password
							</th>
							<th>
								Date of Birth
							</th>
							<th>
								Experience
							</th>
							<th>
								Salary
							</th>
							<th>
								Phone No
							</th>
							<th>
								Action
							</th>
						</tr>
					</thead>
					<tbody id="nurseBody"></tbody>
				</table>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addNurseModal">Add Nurse</button>
				<div class="nurseMsg"></div>
				
				
				
				<div id="addNurseModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="nurseForm" action="../Process?action=addNurse">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Enter Nurse's Detail</h4>
				      </div>
				      <div id="nurse-modal-body" class="modal-body">
						  <div class="form-group">
						  	<div class="col-lg-6">
						  		<input type="text" class="form-control" name="firstname" placeholder="First Name"  required/>
						  	</div>
						   <div class="col-lg-6">
						  		<input type="text" class="form-control" name="lastname" placeholder="Last Name"  required/>
						  	</div>
						  	<div class="col-lg-6">
						  		<input type="text" class="form-control" name="username" placeholder="Username"  required/>
						  	</div>
						   <div class="col-lg-6">
						  		<input type="password" class="form-control" name="password" placeholder="Password" required />
						  	</div>
						  	
						     <div class="col-lg-6">
						     	<input type="text" name="dob" placeholder="Date of Birth" readonly class="form-control dob" required />
						     </div>
						     
						    <div class="col-lg-6">
						    	<input id="experience" type="text" class="form-control" name="experience" placeholder="Experience" required/>
						    </div>
						    
						    <div class="col-lg-6">
						  		<input type="number" class="form-control" name="salary" placeholder="Salary" required />
						  	</div>
						   <div class="col-lg-6">
						  		<input type="text" class="form-control" name="phone" placeholder="Phone No." required />
						  	</div>
						    
						    
						  </div>
						  
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Save Record</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
			
			
			
			<div id="editNurseModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="nurseUpdateForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Enter Nurse's Detail</h4>
				      </div>
				      <div id="nurse-modal-body" class="modal-body">
						  <div class="form-group">
						  	<div class="col-lg-6">
						  		<input type="text" class="form-control" name="firstname" placeholder="First Name"  required/>
						  	</div>
						   <div class="col-lg-6">
						  		<input type="text" class="form-control" name="lastname" placeholder="Last Name"  required/>
						  	</div>
						  	<div class="col-lg-6">
						  		<input type="text" class="form-control" name="username" placeholder="Username"  required/>
						  	</div>
						   <div class="col-lg-6">
						  		<input type="password" class="form-control" name="password" placeholder="Password" required />
						  	</div>
						  	
						     <div class="col-lg-6">
						     	<input type="text" name="dob" placeholder="Date of Birth" readonly class="form-control dob" required />
						     </div>
						     
						    <div class="col-lg-6">
						    	<input id="experience" type="text" class="form-control" name="experience" placeholder="Experience" required/>
						    </div>
						    
						    <div class="col-lg-6">
						  		<input type="number" class="form-control" name="salary" placeholder="Salary" required />
						  	</div>
						   <div class="col-lg-6">
						  		<input type="text" class="form-control" name="phone" placeholder="Phone No." required />
						  	</div>
						    
						    
						  </div>
						  
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Update Record</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
		</div>
</body>
</html>