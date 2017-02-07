<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
<title>Status Tracking System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap-datepicker3.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://code.jquery.com/jquery-1.12.3.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.1.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.print.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.colVis.min.js"></script>
<link rel="stylesheet" href="css/style.css"></link>
<script type="text/javascript">
	history.forward();

	$(document).ready(function() {
		$("#notreported_table").DataTable({
			dom : 'Bfrtip',
			buttons : [ 'copyHtml5', 'excelHtml5', 'colvis' ],

			responsive : {
				"columns" : [ {
					"data" : "Employee Name",
					className : "all"
				}, {
					"data" : "Employee Id",
					className : "all"
				}, {
					"data" : "Email",
					className : "all"
				}, {
					"data" : "Designation",
					className : "all"
				} ],

				"details" : {
					type : 'column',
					target : -1
				}
			},
			"columnDefs" : [ {
				className : 'control',
				orderable : false,
				targets : -1
			} ]
		});
	});
</script>
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<br />
	<br />
	<br />
	<div class="container">
		<div class="rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
				<h4 class="panel-title text-right">
						Ticket dump on <b><c:out value="${showDate}" ></c:out></b> 
						by <b><c:out value="${showCreate}" ></c:out></b>
					</h4>
					<h3 class="panel-title text-center">
						<b> Not Reported Employee's</b>
					</h3>
				</div>
				<div class="panel-body">
					<form action="notreportedemployee.htm" method="post">
						<table id="notreported_table" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Employee Name</th>
									<th>Employee Id</th>
									<th>Email</th>
									<th>Designation</th>
									<th></th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${notReported}" var="list">

									<tr>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="employeename"
											value="${list[0].employee_name}"> <c:out
												value="${list[0].employee_name}"></c:out>
										</td>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="employee_id" value="${list[1]}">
											<c:out value="${list[1]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="email" value="${list[2]}">
											<c:out value="${list[2]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="designation" value="${list[3]}">
											<c:out value="${list[3]}"></c:out>
										</td>
										<td></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>