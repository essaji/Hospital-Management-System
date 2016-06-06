$(function(){
	
	$.ajax({
		url: "../services/medicine/all",
		type: "GET",
		success: function(data){
			data.forEach(function(medicine){
				
				var index = $("#tblMedicine").dataTable().fnAddData([
				                                        medicine.name,
				                                        medicine.price,
				                                        "<a href='#' onclick='updateMed("+medicine.mid+")'>Update</a> / <a href='#' onclick='removeMed("+medicine.mid+")'>Delete</a> "
				                                        ]);
				var row = $("#tblMedicine").dataTable().fnGetNodes(index);
				$(row).attr("id",medicine.mid);
				
			});
		},
		error: function(data){
			$(".medMsg").removeClass("alert-success")
			$(".medMsg").addClass("alert-danger").html("<strong>Error: </strong> "+data.responseText);
		}
	});
	
	$.ajax({
		url: "../services/prescription/all",
		type: "GET",
		success: function(data){
			data.forEach(function(pres){
				
				var index = $("#tblPrescriptions").dataTable().fnAddData([
				                                              pres.medicine.name,
				                                              pres.dosage,
				                                              pres.patient.name,
				                                              "Dr. "+pres.doctor.employee.firstname+" "+pres.doctor.employee.lastname
				                                              ]);
				var row = $("#tblPrescriptions").dataTable().fnGetNodes(index);
				$(row).attr("id",pres.prid);
			});
		},
		error: function(data){
			
		}
	});
	
	
	$("table").dataTable();
	
	
	//Add Medicine Form
	$("#addMedForm").submit(function(e){
		e.preventDefault();
		
		$.ajax({
			url: $(this).attr("action"),
			type: $(this).attr("method"),
			data: $(this).serialize(),
			success: function(med){
				BootstrapDialog.show({
					title: "Success!",
					message: "Medicine added successfully!"
				});
				
				addMedToTable(med);
			},
			error: function(data){
				BootstrapDialog.show({
					type: BootstrapDialog.TYPE_DANGER,
					title: "Error!",
					message: data.responseText
				});
			}
		});
		
		$("#addMedModal").modal("toggle");
	});
	
	
	//Update Medicine Form
	$("#updateMedForm").submit(function(e){
		e.preventDefault();
		
		$.ajax({
			url: $(this).attr("action"),
			type: $(this).attr("method"),
			data: $(this).serialize(),
			success: function(med){
				BootstrapDialog.show({
					title: "Success!",
					message: "Medicine updated successfully!"
				});
				
				$("#tblMedicine").DataTable().row($("#medBody #"+med.mid)).remove().draw(); //removes row
				addMedToTable(med);
			},
			error: function(data){
				BootstrapDialog.show({
					type: BootstrapDialog.TYPE_DANGER,
					title: "Error!!",
					message: data.responseText
				});
			}
		});
		
		$("#updateMedModal").modal("toggle");
	});
	
})


function addMedToTable(medicine){
	var index = $("#tblMedicine").dataTable().fnAddData([
		                                        medicine.name,
		                                        medicine.price,
		                                        "<a href='#' onclick='updateMed("+medicine.mid+")'>Update</a> / <a href='#' onclick='removeMed("+medicine.mid+")'>Delete</a> "
		                                        ]);
	var row = $("#tblMedicine").dataTable().fnGetNodes(index);
	$(row).attr("id",medicine.mid);
}


function updateMed(mid){
	$("#updateMedForm").attr("action","../services/medicine/"+mid);
	
	
	$.ajax({
		url: "../services/medicine/"+mid,
		type: "GET",
		success: function(med){
			$("#updateMedForm input[name=medName]").val(med.name);
			$("#updateMedForm input[name=price]").val(med.price);
		},
		error : function(data){
			console.log(data.responseText);
		}
	});
	
	$("#updateMedModal").modal("toggle");
}


function removeMed(mid){
	
	bootbox.confirm("Are you sure?", function(sure){
		if(sure){
			
			$.ajax({
				url: "../services/medicine/"+mid,
				type: "DELETE",
				success: function(data){
					BootstrapDialog.show({
						title: "Success!",
						message: "Medicine deleted successfully!"
					});
					
					$("#tblMedicine").DataTable().row($("#medBody #"+mid)).remove().draw();
				},
				error: function(data){
					BootstrapDialog.show({
						type: BootstrapDialog.TYPE_DANGER,
						title: "Error!",
						message: "Error while deleting medicine !"+data.responseText
					});
				}
			})
			
		}
	}).find(".modal-body").css("height","50px");
	
}