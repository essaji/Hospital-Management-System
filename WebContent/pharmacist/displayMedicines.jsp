<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	#addMedForm .modal-body, #updateMedForm .modal-body{
		height: 100px;
	}
	#addMedForm .form-control, #updateMedForm .form-control{
		margin: 1px;
	}
</style>
</head>
<body>
<div id="medicines" class="tab-pane fade in active">
				<h3>Medicine Record</h3>
				<table class="table table-hover table-bordered" id="tblMedicine">
					<thead>
						<tr>
							<th>
								Medicine Name
							</th>
							<th>
								Price
							</th>
							<th>
								Action
							</th>
						</tr>
					</thead>
					<tbody id="medBody"></tbody>
				</table>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addMedModal">Add Medicine</button>
		</div>
		
		
		<!-- Add Medicine Modal-->
				<div id="addMedModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="addMedForm" action="../services/medicine">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Enter Medicine Detail</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						  	<div class="col-lg-12">
						  		<input type="text" class="form-control" name="medName" placeholder="Medicine Name"  required/>
						  	</div>
						   <div class="col-lg-12">
						  		<input type="number" class="form-control" name="price" placeholder="Medicine Price"  required/>
						  	</div>
						  	
						  </div>
						  
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Add Medicine</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
			
			
			
			
			<!-- Update Medicine Modal-->
				<div id="updateMedModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="PUT" id="updateMedForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Update Medicine Detail</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						  	<div class="col-lg-12">
						  		<input type="text" class="form-control" name="medName" placeholder="Medicine Name"  required/>
						  	</div>
						   <div class="col-lg-12">
						  		<input type="number" class="form-control" name="price" placeholder="Medicine Price"  required/>
						  	</div>
						  	
						  </div>
						  
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Update Medicine</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
		
</body>
</html>