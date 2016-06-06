$(function(){
	
	var uid = $("body").attr("id");
	
	$.ajax({
		url: "../services/room/nurse/"+uid,
		type: "GET",
		success: function(data){
			if(data.length!=0) $("#roomBody .dataTables_empty").remove();
			data.forEach(function(room){
				var index = $("#tblRooms").dataTable().fnAddData([
				                                                  room.rid,
				                                                  room.totalbeds,
				                                                  (parseInt(room.totalbeds) - parseInt(room.availablebeds)),
				                                                  room.availablebeds
				                                                  ]);
				var row = $("#tblRooms").dataTable().fnGetNodes(index);
				$(row).attr("id",room.rid);
				
			});
		},
		error: function(data){
			$(".roomMsg").removeClass("alert-success")
			$(".roomMsg").addClass("alert-danger").html("<strong>Error: </strong> "+data.responseText);
		}
	});
	
	
	$.ajax({
		url: "../services/patient/nurse/"+uid,
		type: "GET",
		success: function(data){
			if(data.length!=0) $("#patientBody .dataTables_empty").remove();
			data.forEach(function(indoor){
				
				var index = $("#tblPatients").dataTable().fnAddData([
				                                                     indoor.patient.name,
				                                                     indoor.patient.dob,
				                                                     indoor.patient.gender,
				                                                     indoor.disease,
				                                                     indoor.patient.doctor.employee.firstname+" "+indoor.patient.doctor.employee.lastname
				                                                     ]);
				
				var row = $("#tblPatients").dataTable().fnGetNodes(index);
				$(row).attr("id",indoor.pid);
				
			});
		},
		error: function(data){
			$(".roomMsg").removeClass("alert-success")
			$(".roomMsg").addClass("alert-danger").html("<strong>Error: </strong> "+data.responseText);
		}
	});
	
	
	$("table").dataTable();
})