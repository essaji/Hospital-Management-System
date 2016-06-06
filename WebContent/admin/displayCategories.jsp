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
		height: 80px;
	}
	#addCategoryModal .modal-content, #updateCategoryModal .modal-content{
		width: 250px;
	}
</style>
</head>
<body>
<div id="categories" class="tab-pane fade in">
				<h3>Rooms' Record</h3>
				<table class="table table-hover table-bordered" id="tblCategories">
					<thead>
						<tr>
							<th>
								Category No
							</th>
							<th>
								Category Name
							</th>
							<th>
								Action
							</th>
						</tr>
					</thead>
					<tbody id="catBody"></tbody>
				</table>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addCategoryModal">Add New Category</button>
				
				
				<!-- Add Category Modal-->
				<div id="addCategoryModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="POST" id="addCategoryForm" action="../services/category">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Add Category</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
							   <div class="col-lg-12">
							  		<input type="text" class="form-control" name="catName" placeholder="Enter Category"  required/>
							  	</div>
						  </div>
						  
				      </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-success">Add Record</button>
				      </div>
				    </div>
					</form>
				  </div>
				
			</div>
			
			
			
			<!-- Update Category Modal -->
			<div id="updateCategoryModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
					<form method="PUT" id="updateCategoryForm">
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title">Update Category</h4>
				      </div>
				      <div class="modal-body">
						  <div class="form-group">
						   <div class="col-lg-12">
						  		<input type="text" class="form-control" name="catName" placeholder="Enter Category"  required/>
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