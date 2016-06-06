$(document).ready(function(){
	
	$(".dob").datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true,
				yearRange: "1960:2015"
			});
	
	//adding categories
	$.ajax({
		url: "services/category/all",
		type: "GET",
		success: function(categories){
			categories.forEach(function(category){
				$("#signupform select[name=catid]").append("<option value="+category.catid+">"+category.name+"</option>");
			});
		}
	});
	
	$("#signupform").submit(function(e){
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(data){
				BootstrapDialog.show({
					title: "Success",
					message: "Patient has been registered successfully."
				});
			},
			error: function(xml,status,errorThrown){
				BootstrapDialog.show({
					title: "Error",
					message: xml.responseText
				});
			}
			
		});
		
		$("#signup-modal").modal("toggle"); //this line is awesome!!
		
		e.preventDefault();
	});
	
	 
	//Add new Doctor
	$("#docForm").submit(function(e){


		if($("#docForm .dob").val() == ''){
			alert('Date of birth cannot be empty');
			return;
		}
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(doctor){
				
				addDocToTable(doctor);
				$(".docMsg").addClass("alert-success").html("<strong>Success</strong>: Record Added!");
			},
			error: function(xml,status,errorThrown){
				$(".docMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
			}
			
		});
		
		
		
		$("#addDocModal").modal("toggle"); //this line is awesome!!
		
		return false;
		
	});
	
	$("#nurseForm").submit(function(e){
		e.preventDefault();


		if($("#nurseForm .dob").val() == ''){
			alert('Date of birth cannot be empty');
			return;
		}
		
		
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(nurse){
				console.log(nurse);
				addNurseToTable(nurse);
				$(".nurseMsg").addClass("alert-success").html("<strong>Success</strong>: Record Added!");
			},
			error: function(xml,status,errorThrown){
				$(".nurseMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
			}
			
		});
		
		
		$("#addNurseModal").modal("toggle"); //this line is awesome!!
		
	});
	
	
	
	$("#nurseUpdateForm").submit(function(e){
		e.preventDefault();


		if($("#nurseUpdateForm .dob").val() == ''){
			alert('Date of birth cannot be empty');
			return;
		}
		
		
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(nurse){
				console.log(nurse);
				$(".nurseMsg").removeClass("alert-danger").addClass("alert-success").html("<strong>Success</strong>: Record Added!");
				$("#displayNurses").DataTable().row($("#nurseBody #"+nurse.employee.user.uid)).remove().draw();
				addNurseToTable(nurse);
			},
			error: function(xml,status,errorThrown){
				$(".nurseMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
			}
			
		});
		
		
		$("#editNurseModal").modal("toggle"); //this line is awesome!!
		
	});
	
	
	
	$(".dob").datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true,
				yearRange: "1960:2015"
			});
	
	
	$("#roomForm").submit(function(e){
		e.preventDefault();
		
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(data){
				addRoomToTable(data);
				$(".roomMsg").removeClass("alert-danger");
				$(".roomMsg").addClass("alert-success").html("<strong>Success</strong>: Record Added!");
			},
			error: function(xml,status,errorThrown){
				$(".roomMsg").removeClass("alert-success");
				$(".roomMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
			}
			
		});
		
		
		$("#addRoomModal").modal("toggle"); //this line is awesome!!
		
	});
	
	
	$("#updateRoomForm").submit(function(e){
		e.preventDefault();
		
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(room){
				$(".roomMsg").removeClass("alert-danger");
				$(".roomMsg").addClass("alert-success").html("<strong>Success</strong>: Record Updation Success!");

				$("#displayRooms").DataTable().row($("#roomBody #"+room.rid)).remove().draw();
				addRoomToTable(room);
			},
			error: function(xml,status,errorThrown){
				$(".roomMsg").removeClass("alert-success");
				$(".roomMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
			}
			
		});
		
		
		$("#editRoomModal").modal("toggle"); //this line is awesome!!
		
	});
	
	
	$("#docUpdateForm").submit(function(e){
		//e.preventDefault();


		if($("#docUpdateForm .dob").val() == ''){
			alert('Date of birth cannot be empty');
			return;
		}
		
		
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(doctor){
				console.log(doctor);
				$(".docMsg").removeClass("alert-danger");
				$(".docMsg").addClass("alert-success").html("<strong>Success</strong>: Record Updation Success!");
				
				//$("#docBody #"+doctor.employee.user.uid).remove();
				$("#displayDoctors").DataTable().row($("#docBody #"+doctor.employee.user.uid)).remove().draw();
				addDocToTable(doctor);
			},
			error: function(xml,status,errorThrown){
				$(".docMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
			}
			
		});
		
		
		
		$("#editDocModal").modal("toggle"); //this line is awesome!!
		
		return false;
		
	});
	
	
});







function addNurseToTable(nurse){
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
	//$(".deleteMe").remove();
}


function addDocToTable(doctor){
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
	
}




function empDelete(id,type){
	bootbox.confirm("Are you sure?",function(sure){
		if(sure)
			empDeleteConfirm(id,type);
	}).find(".modal-body").css({"height": "50px"})
}

function empDeleteConfirm(id,type){
	$.ajax({
		type: "GET",
		url: "../Process?action=deleteEmp&id="+id,
		success: function(){
			if(type==1) $(".docMsg").addClass("alert-success").html("<strong>Success</strong>: Record Deleted Success!");
			else if(type==2) $(".nurseMsg").addClass("alert-success").html("<strong>Success</strong>: Record Deleted Success!");
			
			//$("#docBody #"+id).remove();
			$("#displayDoctors").DataTable().row($("#docBody #"+id)).remove().draw();
			//$("#nurseBody #"+id).remove();
			$("#displayNurses").DataTable().row($("#nurseBody #"+id)).remove().draw();
		},
		error: function(xml){
			$(".docMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
		}
	});
}

function  roomDelete(rid){
	bootbox.confirm("Are you sure?",function(sure){
		if(sure)
			roomDeleteConfirm(rid);
	}).find(".modal-body").css({"height": "50px"})
}

function roomDeleteConfirm(rid){
	
	//alert("gonna delete.");
	
	
	$.ajax({
		type: "GET",
		url: "../Process?action=deleteRoom&id="+rid,
		success: function(){
			$(".roomMsg").addClass("alert-success").html("<strong>Success</strong>: Record Deleted Success!");
			//$("#roomBody #"+rid).remove();
			$("#displayRooms").DataTable().row($("#roomBody #"+rid)).remove().draw();
		},
		error: function(xml){
			$(".roomMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
		}
	});
}

function  patientDelete(pid){
	bootbox.confirm("Are you sure?",function(sure){
		if(sure)
			patientDeleteConfirm(pid);
	}).find(".modal-body").css({"height": "50px"})
}


function patientDeleteConfirm(pid){
	$.ajax({
		type: "GET",
		url: "../Process?action=deletePatient&id="+pid,
		success: function(){
			$(".patientMsg").addClass("alert-success").html("<strong>Success</strong>: Record Deleted Success!");
			//$("#patientBody #"+pid).remove();
			$("#displayPatients").DataTable().row($("#patientBody #"+pid)).remove().draw();
			//$("#indoorBody #"+pid).remove();
			$("#displayIndoors").DataTable().row($("#indoorBody #"+pid)).remove().draw();
		},
		error: function(xml){
			$(".patientMsg").addClass("alert-danger").html("<strong>Error</strong>: "+xml.responseText);
		}
	});
}

function editDoc(did){
	$.ajax({
		type: "GET",
		url: "../Process?action=getDoc&id="+did,
		success: function(data){
			//var docObj = JSON.parse(data.responseText);
			editDocForm(did,data);
		},
		error: function(data){
			console.log("Error");
		}
	});
}

function editDocForm(did,docObj){
	$("#editDocModal form").attr("action","../Process?action=editDoc&id="+did);
	
	$("#editDocModal form input[name=firstname]").val(docObj.employee.firstname);
	$("#editDocModal form input[name=lastname]").val(docObj.employee.lastname);
	$("#editDocModal form input[name=username]").val(docObj.employee.user.username);
	$("#editDocModal form input[name=password]").val(docObj.employee.user.password);
	$("#editDocModal form select[name=catid]").val(docObj.category.catid);
	$("#editDocModal form input[name=dob]").val(docObj.employee.dob);
	$("#editDocModal form input[name=salary]").val(docObj.employee.salary);
	$("#editDocModal form input[name=phone]").val(docObj.employee.phone);
	if(docObj.employee.gender=="male")
		$("#editDocModal form input[value=male]").prop("checked", true);
	else
		$("#editDocModal form input[value=female]").prop("checked",true);
	
	
	$("#editDocModal").modal("toggle");
}


function editNurse(nid){
	$.ajax({
		type: "GET",
		url: "../Process?action=getNurse&id="+nid,
		success: function(nurse){
			editNurseForm(nid,nurse);
		},
		error: function(data){
			console.log("Error");
		}
	});
}

function editNurseForm(nid,nurse){
	$("#editNurseModal form").attr("action","../Process?action=editNurse&id="+nid);
	
	$("#editNurseModal form input[name=firstname]").val(nurse.employee.firstname);
	$("#editNurseModal form input[name=lastname]").val(nurse.employee.lastname);
	$("#editNurseModal form input[name=username]").val(nurse.employee.user.username);
	$("#editNurseModal form input[name=password]").val(nurse.employee.user.password);
	$("#editNurseModal form input[name=experience]").val(nurse.experience);
	$("#editNurseModal form input[name=dob]").val(nurse.employee.dob);
	$("#editNurseModal form input[name=salary]").val(nurse.employee.salary);
	$("#editNurseModal form input[name=phone]").val(nurse.employee.phone);
	
	
	$("#editNurseModal").modal("toggle");
}


function editRoom(rid){
	$.ajax({
		type: "GET",
		url: "../Process?action=getRoom&id="+rid,
		success: function(room){
			editRoomForm(rid,room);
		},
		error: function(data){
			console.log("Error");
		}
	});
}

function editRoomForm(rid,room){
	
	$("#editRoomModal form").attr("action","../Process?action=editRoom&id="+rid);
	
	$("#editRoomModal form input[name=totalbeds]").val(room.totalbeds);
	$("#editRoomModal form select[name=nurseId]").val(room.nid);
	
	$("#editRoomModal").modal("toggle");
}


function addRoomToTable(room){
	var index = $("#displayRooms").dataTable().fnAddData([
				                                          room.rid,
				                                          room.totalbeds,
				                                          room.nurse.employee.firstname+" "+room.nurse.employee.lastname,
				                                          room.nurse.employee.phone,
				                                          "<a onClick='roomDelete("+room.rid+")' href='#'  >Delete</a> / <a href='#' onclick='editRoom("+room.rid+")'>Edit</a>"
				                                          ]);
	
	var row = $("#displayRooms").dataTable().fnGetNodes(index);
	$(row).attr("id",room.rid);
	//$(".deleteMe").remove();
}