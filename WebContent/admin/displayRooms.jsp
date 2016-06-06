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
	.form-control{
		margin: 5px;
	}
	.modal-body{
		height: 150px;
	}
</style>
</head>
<body>
<div id="rooms" class="tab-pane fade in">
				<h3>Rooms' Record</h3>
				<table class="table table-hover table-bordered" id="displayRooms">
					<thead>
						<tr>
							<th>
								Room No
							</th>
							<th>
								Total No of Beds
							</th>
							<th>
								Governed By
							</th>
							<th>
								Phone Number
							</th>
							<th>
								Action
							</th>
						</tr>
					</thead>
					<tbody id="roomBody"></tbody>
				</table>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addRoomModal">Add Room</button>
				<div class="roomMsg"></div>
				
				
				<!-- Add Room Modal-->
				<div id="addRoomModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="roomForm" action="../Process?action=addRoom">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Enter Room Detail</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						   <div class="col-lg-12">
						  		<input type="number" class="form-control" name="totalbeds" placeholder="Enter No of Beds"  required/>
						  	</div>
						    <div class="col-lg-12">
						    	<label>Governed By</label>
						    	<select class = "form-control" name="nurseId"></select>
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
			
			
			
			<!-- Update Room Modal -->
			<div id="editRoomModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="updateRoomForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Enter Room Detail</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						   <div class="col-lg-12">
						  		<input type="number" class="form-control" name="totalbeds" placeholder="Enter No of Beds"  required/>
						  	</div>
						    <div class="col-lg-12">
						    	<label>Governed By</label>
						    	<select class = "form-control" name="nurseId"></select>
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
		</div>
</body>
</html>