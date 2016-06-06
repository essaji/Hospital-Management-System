$(function(){
	
	
	$.ajax({
		url: "../services/category/all",
		type: "GET",
		success: function(categories){
			categories.forEach(function(cat){
				$("#docForm select[name=catid]").append("<option value='"+cat.catid+"' >"+cat.name+"</option>");
				$("#docUpdateForm select[name=catid]").append("<option value='"+cat.catid+"' >"+cat.name+"</option>");
			});
		}
	});
	
	//Display Doctors
	$.ajax({
		url: "../services/doctor/all",
		type: "GET",
		success: function(data){
			data.forEach(function(doctor){
				var index = $("#displayDoctors").dataTable().fnAddData([
				                                          doctor.employee.firstname,
				                                          doctor.employee.lastname,
				                                          doctor.employee.user.username,
				                                          doctor.employee.user.password,
				                                          doctor.category.name,
				                                          doctor.employee.dob,
				                                          doctor.employee.gender,
				                                          doctor.employee.salary,
				                                          doctor.employee.phone,
				                                          "<a onClick='empDelete("+doctor.employee.user.uid+",1)' href='#'  >Delete</a> / <a href='#' onclick='editDoc("+doctor.did+")'>Edit</a>"
				                                          ]);
				
				var row = $("#displayDoctors").dataTable().fnGetNodes(index);
				$(row).attr("id",doctor.employee.uid);
				
			});
		},
		error: function(data){
			
		}
	});
	
	//For Nurses
	$.ajax({
		url: "../services/nurse/all",
		type: "GET",
		success: function(data){
			data.forEach(function(nurse){
				var index = $("#displayNurses").dataTable().fnAddData([
								                                          nurse.employee.firstname,
								                                          nurse.employee.lastname,
								                                          nurse.employee.user.username,
								                                          nurse.employee.user.password,
								                                          nurse.employee.dob,
								                                          nurse.experience,
								                                          nurse.employee.salary,
								                                          nurse.employee.phone,
								                                          "<a onClick='empDelete("+nurse.employee.user.uid+",1)' href='#'  >Delete</a> / <a href='#' onclick='editNurse("+nurse.nid+")'>Edit</a>"
								                                          ]);
				
				var row = $("#displayNurses").dataTable().fnGetNodes(index);
				$(row).attr("id",nurse.employee.user.uid);
				
			});
		},
		error: function(data){
			
		}
	});
	
	
	
	//For Room
	$.ajax({
		url: "../services/room/all",
		type: "GET",
		success: function(data){
			data.forEach(function(room){
				var index = $("#displayRooms").dataTable().fnAddData([
							                                          room.rid,
							                                          room.totalbeds,
							                                          room.nurse.employee.firstname+" "+room.nurse.employee.lastname,
							                                          room.nurse.employee.phone,
							                                          "<a onClick='roomDelete("+room.rid+")' href='#'  >Delete</a> / <a href='#' onclick='editRoom("+room.rid+")'>Edit</a>"
							                                          ]);
				
				var row = $("#displayRooms").dataTable().fnGetNodes(index);
				$(row).attr("id",room.rid);
				
				$("#assignRoomForm select[name=rid]").append("<option value="+room.rid+">"+room.rid+"</option>");
			
			});
		},
		error: function(data){
			
		}
	});
	
	
	//For Indoors
	$.ajax({
		url: "../services/indoor/all",
		type: "GET",
		success: function(data){
			data.forEach(function(indoor){
				
				addIndoorToTable(indoor);
			});
		},
		error: function(data){
			
		}
	});
	
	
	//For Patients
	$.ajax({
		url: "../services/patient/all",
		type: "GET",
		success: function(data){
			
			data.forEach(function(patient){
				var type,docName;
				if(patient.type) type = patient.type;
				else type="-";
				
				if(patient.doctor) docName = patient.doctor.employee.firstname;
				else docName = "-";
				
				
				var index = $("#displayPatients").dataTable().fnAddData([
								                                          patient.name,
								                                          patient.dob,
								                                          patient.gender,
									                                      type,
									                                      docName,
								                                          "<td><a onClick='patientDelete("+patient.pid+")' href='#'>Remove</a></td>"
								                                          ]);
				
				var row = $("#displayPatients").dataTable().fnGetNodes(index);
				$(row).attr("id",patient.pid);
				
				
			});
		},
		error: function(data){
			
		}
	});
	
	
	//For displaying Categories
	$.ajax({
		url: "../services/category/all",
		type: "GET",
		success: function(categories){
			categories.forEach(function(category){
				addCategoryToTable(category);
			});
		},
		error: function(error){
			console.log(error.responseText);
		}
	});
	
	//Add Category Form Submission
	$("#addCategoryForm").submit(function(e){
		e.preventDefault();
		
		$.ajax({
			url: $(this).attr("action"),
			type: $(this).attr("method"),
			data: $(this).serialize(),
			success: function(category){
				BootstrapDialog.show({
					title: "Success!",
					message: "category added successfully!"
				});
				
				addCategoryToTable(category);
			},
			error: function(err){
				BootstrapDialog.show({
					type: BootstrapDialog.TYPE_DANGER,
					title: "Error!",
					message: err.responseText
				});
			}
		});
		
		$("#addCategoryModal").modal("toggle");
	})
	
	
	//Update Category Form Submission
	$("#updateCategoryForm").submit(function(e){
		e.preventDefault();
		
		$.ajax({
			url: $(this).attr("action"),
			type: $(this).attr("method"),
			data: $(this).serialize(),
			success: function(category){
				BootstrapDialog.show({
					title: "Success!",
					message: "category updated successfully!"
				});
				
				$("#tblCategories").DataTable().row($("#catBody #"+category.catid)).remove().draw();
				addCategoryToTable(category);
			},
			error: function(err){
				BootstrapDialog.show({
					type: BootstrapDialog.TYPE_DANGER,
					title: "Error!",
					message: err.responseText
				});
			}
		});
		
		$("#updateCategoryModal").modal("toggle");
	})
	
	
	$("table").dataTable();
	
	
	//Room Assignment
	$("#assignRoomForm").submit(function(e){
		e.preventDefault();
		
		$.ajax({
			url: $(this).attr("action"),
			data: $(this).serialize(),
			type: "PUT",
			success: function(indoor){
				BootstrapDialog.show({
					title: "Success!",
					message: "room assigned successfully!"
				});
				
				//$("#indoorBody #"+indoor.pid).addClass("deleteMe");
				$("#displayIndoors").DataTable().row($("#indoorBody #"+indoor.pid)).remove().draw();
				
				
				console.log(indoor);
				addIndoorToTable(indoor);
			},
			error: function(data){
				BootstrapDialog.show({
					type: BootstrapDialog.TYPE_DANGER,
					title: "Error!",
					message: data.responseText
				});
			}
				
		});
		
		$("#assignRoomModal").modal("toggle");
	});
	
	
	$.ajax({
		url: "../services/nurse/all",
		type: "GET",
		success: function(nurses){
			nurses.forEach(function(nurse){
				$("#roomForm select[name=nurseId]").append("<option value='"+nurse.nid+"' >"+nurse.employee.firstname+" "+nurse.employee.lastname+"</option>");
				$("#updateRoomForm select[name=nurseId]").append("<option value='"+nurse.nid+"' >"+nurse.employee.firstname+" "+nurse.employee.lastname+"</option>");
			});
		},
		error: function(data){
			
		}
	});
	
})


function assignRoom(ipid){
	$("#assignRoomModal").modal("toggle");
	$("#assignRoomForm").attr("action","../services/indoor/"+ipid+"/room");
	
	
	//size setting
	$("#assignRoomModal .modal-body").css("height","100px");
	$("#assignRoomModal .modal-content").css("width","300px");
	
}

function addIndoorToTable(indoor){
	var rid="-",nurseName="-",link="<a href='#' onclick='assignRoom("+indoor.ipid+")'>Assign Room</a>";
	if(indoor.rid) {
		rid = indoor.rid
		link = "";
	}
	
	if(indoor.room) nurseName  = indoor.room.nurse.employee.firstname;
	
	
	
	var index = $("#displayIndoors").dataTable().fnAddData([
					                                          indoor.patient.name,
					                                          indoor.patient.gender,
					                                          indoor.disease,
					                                          indoor.status,
						                                      rid,
						                                      nurseName,
						                                      link
					                                          ]);
	
	var row = $("#displayIndoors").dataTable().fnGetNodes(index);
	$(row).attr("id",indoor.pid);
	//$(".deleteMe").remove();
}

function addCategoryToTable(category){
	var index = $("#tblCategories").dataTable().fnAddData([
	                                                       category.catid,
	                                                       category.name,
	                                                       "<a href='#' onclick='updateCategory("+category.catid+")'>Update</a> / <a href='#' onclick='deleteCategory("+category.catid+")'>Delete</a> "
	                                                       ]);
	var row = $("#tblCategories").dataTable().fnGetNodes(index);
	$(row).attr("id",category.catid);
}

function updateCategory(catid){
	
	$("#updateCategoryForm").attr("action","../services/category/"+catid);
	
	$.ajax({
		url: "../services/category/"+catid,
		type: "GET",
		success: function(category){
			$("#updateCategoryForm input[name=catName]").val(category.name);
		},
		error: function(err){
			console.log(err.responseText);
		}
	})
	
	$("#updateCategoryModal").modal("toggle");
}


function deleteCategory(catid){
	bootbox.confirm("Are you sure?",function(sure){
		if(sure){
			$.ajax({
				url: "../services/category/"+catid,
				type: "DELETE",
				success: function(result){
					BootstrapDialog.show({
						title: "Success!",
						message: "Category deleted successfully!"
					});
					
					$("#tblCategories").DataTable().row($("#catBody #"+catid)).remove().draw();
				},
				error: function(data){
					BootstrapDialog.show({
						type: BootstrapDialog.TYPE_DANGER,
						title: "Error!",
						message: data.responseText,
					});
				}
			});
		}
	}).find(".modal-body").css("height","50px");
}