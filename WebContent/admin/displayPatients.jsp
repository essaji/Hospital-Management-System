<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div id="patients" class="tab-pane fade in active" >
				<h3>Patients' Record</h3>
				<table class="table table-hover table-bordered" id="displayPatients">
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
								Doctor's Name
							</th>
							<th>
								Remove
							</th>
						</tr>
					</thead>
					<tbody id="patientBody"> </tbody>
				</table>
				<div class="patientMsg"></div>
			</div>
</body>
</html>