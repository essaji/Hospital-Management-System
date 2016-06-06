<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="myPatients" class="tab-pane fade in">
				<h3>My Patients' Record</h3>
				<table class="table table-hover table-bordered" id="tblPatients">
					<thead>
						<tr>
							<th>
								Patient's Name
							</th>
							<th>
								Date of Birth
							</th>
							<th>
								Gender
							</th>
							<th>
								Disease
							</th>
							<th>
								Doctor's Name
							</th>
						</tr>
					</thead>
					<tbody id="patientBody">
					</tbody>
				</table>
				<div class="patientMsg"></div>
			</div>
</body>
</html>