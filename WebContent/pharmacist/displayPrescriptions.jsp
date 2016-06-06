<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="prescriptions" class="tab-pane fade in">
			<h3>Prescription Record</h3>
				<table class="table table-hover table-bordered" id="tblPrescriptions">
					<thead>
						<tr>
							<th>
								Medicine Name
							</th>
							<th>
								Dosage
							</th>
							<th>
								Patient Name
							</th>
							<th>
								Doctor's Name
							</th>
						</tr>
					</thead>
					<tbody id="presBody"></tbody>
				</table>
		</div>
</body>
</html>