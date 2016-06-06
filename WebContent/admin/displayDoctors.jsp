<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="hmsModels.*"  %>
     <%@ page import="hmsControllers.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	.modal-content{
		width: 400px;
	}
</style>
</head>
<body>
<div id="employees" class="tab-pane fade in">
				<h3>Doctors' Record</h3>
				<table class="table table-hover table-bordered" id="displayDoctors">
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
								Specialization
							</th>
							<th>
								Date of Birth
							</th>
							<th>
								Gender
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
					<tbody id="docBody"></tbody>
				</table>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addDocModal">Add Doctor</button>
				<div class="docMsg"></div>
				
				
				<!-- Modal -->
				<div id="addDocModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="docForm" action="../Process?action=addDoc">
					
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Enter Doctor's Detail</h4>
				      </div>
				      <div class="modal-body">
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
						    	<label>Specialization</label>
						    	<select class = "form-control" name="catid" id="specialization"></select>
						    </div>
						     <div class="col-lg-6">
						     <label>Date of Birth</label><br>
						     	<input type="text" name="dob" placeholder="Date of Birth" readonly class="form-control dob" required />
						     </div>
						     
						   <br />
						   <div class="col-lg-12">
						   		<label for="radio">Select Gender</label><br>
						    <div class="radio">
						    	<label><input type="radio" name="gender" value="male" required/> Male</label>
						    	<label><input type="radio" name="gender" value="female"/> Female</label>
						    </div>
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
			
			<!--Update Doctor Modal-->
			<div id="editDocModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="docUpdateForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Update Doctor's Detail</h4>
				      </div>
				      <div class="modal-body">
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
						    	<label>Specialization</label>
						    	<select class = "form-control" name="catid" id="specialization"></select>
						    </div>
						     <div class="col-lg-6">
						     <label>Date of Birth</label><br>
						     	<input type="text" name="dob" placeholder="Date of Birth" readonly class="form-control dob" required />
						     </div>
						     
						   <br />
						   <div class="col-lg-12">
						   		<label for="radio">Select Gender</label><br>
						    <div class="radio">
						    	<label><input type="radio" name="gender" value="male" required/> Male</label>
						    	<label><input type="radio" name="gender" value="female"/> Female</label>
						    </div>
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