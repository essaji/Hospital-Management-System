$(function(){
	
	var uid = $("body").attr("id");
	
	//For My Patients
	$.ajax({
		url: "../services/patient/doctor/"+uid,
		type: "GET",
		success: function(data){
			data.forEach(function(patient){
				
				addPatientToTable(patient);
				
			});
		},
		error: function(data){
			$(".roomMsg").removeClass("alert-success")
			$(".roomMsg").addClass("alert-danger").html("<strong>Error: </strong> "+data.responseText);
		}
	});
	
	
	//For Displaying My Prescriptions
	$.ajax({
		url: "../services/prescription/doctor/"+uid,
		type: "GET",
		success: function(data){
			data.forEach(function(prescription){
				
				addPresToTable(prescription);

			});
		},
		error: function(data){
			$(".roomMsg").removeClass("alert-success")
			$(".roomMsg").addClass("alert-danger").html("<strong>Error: </strong> "+data.responseText);
		}
	});
	
	
	//For Medicines
	$.ajax({
		url: "../services/medicine/all",
		type: "GET",
		success: function(meds){
			meds.forEach(function(med){
				$("#addPresForm .form-group div select.med").append("<option value="+med.mid+" >"+med.name+"</option>");
			})
		},
		error: function(er){
			console.log(er);
		}
	});
	
	
	$("table").dataTable();
	
	
	//Submit Prescription Form
	$("#presSubmitForm").submit(function(e){
		
		e.preventDefault();
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(pres){
				BootstrapDialog.show({
					title: "Success",
					message: "Prescription Added Success!."
				});
				
				
				//$("#patientBody #"+pres[0].pid).addClass("deleteMe");
				$("#tblPatients").DataTable().row($("#patientBody #"+pres[0].pid)).remove().draw();
				
				addPatientToTable(pres[0].patient);
				
				addPresToTable(pres);
				
			},
			error: function(xml,status,errorThrown){
				BootstrapDialog.show({
					title: "Error",
					message: xml.responseText
				});
			}
			
		});
		
		$("#submitPresModal").modal("toggle");
		
	})
	
	
	
	//Add Prescription Form
	$("#addPresForm").submit(function(e){
		
		e.preventDefault();
		
		$.ajax({
			type: $(this).attr("method"),
			url: $(this).attr("action"),
			data: $(this).serialize(),
			success: function(pres){
				BootstrapDialog.show({
					title: "Success",
					message: "Prescription Added Success!."
				});
				
				
				addPresToTable(pres);
				
			},
			error: function(xml,status,errorThrown){
				BootstrapDialog.show({
					title: "Error",
					message: xml.responseText
				});
			}
			
		});
		
		$("#addPresModal").modal("toggle");
		
	})
	
	
	
	//Update Prescription Form
	$("#presUpdateForm").submit(function(e){
		e.preventDefault();
		
		$.ajax({
			url: $(this).attr("action"),
			type: "PUT",
			data: $(this).serialize(),
			success: function(pres){
				BootstrapDialog.show({
					title: "Success",
					message: "Prescription Upated Success!."
				});
				
				//$("#presBody #"+pres.prid).addClass("deleteMe");
				$("#tblPrescriptions").DataTable().row($("#presBody #"+pres.prid)).remove().draw();
				addPresToTable(pres);
				
			},
			error: function(error){
				BootstrapDialog.show({
					title: "Failed",
					message: error.responseText
				});
			}
		});
		$("#updatePresModal").modal("toggle");
		
	})
	
	
	
});


function viewPres(pid){
	
	$("#presViewForm .form-group div").remove();
	
	//For Getting All Meds
	$.ajax({
		url: "../services/medicine/all",
		type: "GET",
		success: function(meds){
			

			//For Getting presz of patient
			$.ajax({
				url: "../services/prescription/patient/"+pid,
				type: "GET",
				success: function(data){
					data.forEach(function(pres){
						
						
						var select = document.createElement("select");
						$(select).addClass("form-control");
						$(select).attr("name","mid");
						meds.forEach(function(medicine){
							if(pres.mid==medicine.mid) $(select).append("<option value="+medicine.mid+" selected>"+medicine.name+"</option>");
							else $(select).append("<option value="+medicine.mid+">"+medicine.name+"</option>");
						});
						
						var div = document.createElement("div");
						$(div).addClass("col-lg-6");
						$(div).append(select);
						$(div).clone().appendTo("#presViewForm .form-group");
						
						$("#presViewForm .form-group").append("<div class='col-lg-6'><input type='text' class='form-control' name='dosage' placeholder='Dosage' value='"+pres.dosage+"'  required/></div>");

					});
				},
				error: function(data){
					
				}
			})
		},
		error: function(data){
			
		}
	});
	
	$("#viewPresModal").modal("toggle");
}

function submitPres(pid){
	
	$("#presSubmitForm").attr("action","../services/prescription/patient/"+pid);
	$("#presSubmitForm").append("<input type='hidden' name='uid' value='"+$('body').attr('id')+"' /> ");
	
	//For Getting All Meds
	$.ajax({
		url: "../services/medicine/all",
		type: "GET",
		success: function(meds){
			meds.forEach(function(med){
				$("#presSubmitForm .form-group div select.med").append("<option value="+med.mid+" >"+med.name+"</option>");
			})
		},
		error: function(er){
			console.log(er);
		}
	});
	$("#submitPresModal").modal("toggle");
}

function addPres(pid){
	$("#addPresForm").attr("action","../services/prescription/patient/"+pid);
	$("#addPresForm").append("<input type='hidden' name='uid' value='"+$('body').attr('id')+"' /> ");
	
	$("#addPresModal").modal("toggle");
}

function editPres(prid){
	
	$("#presUpdateForm").attr("action","../services/prescription/"+prid);
	$("#presUpdateForm").append("<input type='hidden' name='uid' value='"+$('body').attr('id')+"' /> ");
	
	
	$.ajax({
		url: "../services/prescription/"+prid,
		type: "GET",
		success: function(pres){
			
			$.ajax({
				url: "../services/medicine/all",
				type: "GET",
				success: function(meds){
					
					meds.forEach(function(med){
						
						if(pres.mid == med.mid) $("#presUpdateForm .form-group select").append("<option value="+med.mid+" selected>"+med.name+"</option>");
						else $("#presUpdateForm .form-group select").append("<option value="+med.mid+">"+med.name+"</option>");
					});
					
				},
				error: function(error){
					
				}
			});
			
			$("#presUpdateForm input[name=dosage]").val(pres.dosage);
			
		},
		error: function(data){
			
		}
	});
	
	$("#updatePresModal").modal("toggle");
}

function addPresToTable(data){
	
	if(Array.isArray(data)){
		data.forEach(function(pres){
			var index = $("#tblPrescriptions").dataTable().fnAddData([
			  			                                            pres.patient.name,
						                                            pres.patient.gender,
						                                            pres.medicine.name,
						                                            pres.dosage,
						                                            "<a href='#' onclick='editPres("+pres.prid+")'>Update</a> / <a href='#' onclick='deletePres("+pres.prid+")'>Delete</a>"
						                                            ]);
			var row =  $("#tblPrescriptions").dataTable().fnGetNodes(index);
			$(row).attr("id",pres.prid);
			
		});
	}
	else{
		var index = $("#tblPrescriptions").dataTable().fnAddData([
			  			                                            data.patient.name,
			  			                                            data.patient.gender,
			  			                                            data.medicine.name,
			  			                                            data.dosage,
						                                            "<a href='#' onclick='editPres("+data.prid+")'>Update</a> / <a href='#' onclick='deletePres("+data.prid+")'>Delete</a>"
						                                            ]);
			var row =  $("#tblPrescriptions").dataTable().fnGetNodes(index);
			$(row).attr("id",data.prid);
			//$(".deleteMe").remove();
	}
	
}


function deletePres(prid){
	
	bootbox.confirm("Are you sure?",function(sure){
		if(sure){
			$.ajax({
				url: "../services/prescription/"+prid,
				type: "DELETE",
				success: function(result){
					BootstrapDialog.show({
						title: "Status",
						message: "Message: "+result
					});
					
					
					$("#presBody #"+prid).remove();
				},
				error: function(data){
					BootstrapDialog.show({
						title: "Failed",
						message: "Deletion Failed!"
					});
				}
			});
		}
	}).find(".modal-body").css({"height": "50px"})
	
}

function addPatientToTable(patient){
	var type, link;
	
	if(patient.type){
		type = patient.type;
		link = "<a href='#' onclick='viewPres("+patient.pid+")' >View</a> / <a href='#' onclick='addPres("+patient.pid+")' >Add</a>";
	}else{
		type = "-";
		link = "<a href='#' onclick='submitPres("+patient.pid+")' >Submit</a>";
	}
	
	var index = $("#tblPatients").dataTable().fnAddData([
	           	                                      patient.name,
	        	                                      patient.dob,
	        	                                      patient.gender,
	        	                                      type,
	        	                                      link
	        	                                      ]);
	
	var row = $("#tblPatients").dataTable().fnGetNodes(index);
	$(row).attr("id",patient.pid);
	//$(".deleteMe").remove();
}
