<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	#updatePresModal .modal-body{
		height: 100px;
	}
</style>
</head>
<body>
<div id="myPrescriptions" class="tab-pane fade in">
				<h3>My Prescriptions</h3>
				<table class="table table-hover table-bordered" id="tblPrescriptions">
					<thead>
						<tr>
							<th>
								Patient Name
							</th>
							<th>
								Gender
							</th>
							<th>
								Medicine Name
							</th>
							<th>
								Dosage
							</th>
							<th>
								Action
							</th>
						</tr>
					</thead>
					<tbody id="presBody"></tbody>
				</table>
			</div>
			
			<!-- Prescription Update Modal-->
			<div id="updatePresModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form id="presUpdateForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Prescription View</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						  		<select class="form-control" name="mid"></select>
						  		<input type="text" class="form-control" placeholder="Dosage" name="dosage" />
						  </div>
				      </div>
				      <div class="modal-footer">
				      <button type="submit" class="btn btn-success">Update Prescription</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
</body>
</html>