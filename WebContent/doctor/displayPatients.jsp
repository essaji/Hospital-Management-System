<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#viewPresModal div.modal-body{
	height: 200px;
}

#viewPresModal div.modal-content{
	width: 400px;
}
#viewPresModal .modal-dialog, #viewPresModal .modal-content{
		width: 200px;
	}
	
	#presAddForm .modal-body{
		height: 200px;
	}
</style>
</head>
<body>
<div id="myPatients" class="tab-pane fade in active">
				<h3>Patients' Record</h3>
				<table class="table table-hover table-bordered" id="tblPatients">
					<thead>
						<tr>
							<th>
								Full Name
							</th>
							<th>
								Date of Birth
							</th>
							<th>
								Gender
							</th>
							<th>
								Patient Type
							</th>
							<th>
								Prescription
							</th>
						</tr>
					</thead>
					<tbody id="patientBody"></tbody>
				</table>
				<div class="patientMsg"></div>
			</div>
			
			
			<!-- Prescription View Modal-->
			<div id="viewPresModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="presViewForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Prescription View</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						  		
						  </div>
				      </div>
				      <div class="modal-footer">
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
			
			<!-- Prescription Submit Modal-->
			<div id="submitPresModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="presSubmitForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Add Prescription</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						  		<div class="col-lg-6">
						  		<select name="patType" class="form-control">
							 	 		<option value="indoor">Indoor</option>
							 	 		<option value="outdoor">Outdoor</option>
						 	 		</select>
						 	 	</div>
						 	 	<div class="col-lg-6"><input type="text" class="form-control" name="disease" placeholder="Disease" required/></div>
						 	 	
						  		<div class="col-lg-6"><select name="mid1"  class="form-control med"></select></div>
						  		<div class="col-lg-6">
						  			<input type="text" name="dosage1" class="form-control" placeholder="Dosage" required/>
						 	 	</div>
						 	 	
						 	 	<div class="col-lg-6"><select name="mid2" class="form-control med"></select></div>
						  		<div class="col-lg-6">
						  			<input type="text" name="dosage2" class="form-control" placeholder="Dosage"/>
						 	 	</div>
						 	 	
						 	 	<div class="col-lg-6"><select name="mid3" class="form-control med"></select></div>
						  		<div class="col-lg-6">
						  			<input type="text" name="dosage3" class="form-control" placeholder="Dosage"/>
						 	 	</div>
						 	 	
						  </div>
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Add Prescription</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
			
			
			
			<!-- Prescription Add Modal-->
			<div id="addPresModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="addPresForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Add Prescription</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						  		
						  		<div class="col-lg-6"><select name="mid1"  class="form-control med"></select></div>
						  		<div class="col-lg-6">
						  			<input type="text" name="dosage1" class="form-control" placeholder="Dosage" required/>
						 	 	</div>
						 	 
						  </div>
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Add Prescription</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
</body>
</html>