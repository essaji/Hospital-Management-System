<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="hmsModels.*"  %>
     <%@ page import="hmsControllers.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="indoors" class="tab-pane fade in">
				<h3>Indoor Patients' Record</h3>
				<table class="table table-hover table-bordered" id="displayIndoors">
					<thead>
						<tr>
							<th>
								Patient Name
							</th>
							<th>
								Gender
							</th>
							<th>
								Disease
							</th>
							<th>
								Status
							</th>
							<th>
								Room No
							</th>
							<th>
								Nurse Name
							</th>
							<th>
								Action
							</th>
						</tr>
					</thead>
					<tbody id="indoorBody"></tbody>
				</table>
				<div class="indoorMsg"></div>
		</div>
		
		
		<div id="assignRoomModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="assignRoomForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Select Room No</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						    
						    <select name="rid" class="form-control"></select>
						    
						  </div>
						  
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Assign Room</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
</body>
</html>