<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
<title>Status Tracking System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap-datepicker3.min.css">
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
		$("#inprogress_table").DataTable({
			dom : 'Bfrtip',
			buttons : [ 'copyHtml5', 'excelHtml5', 'colvis' ],
			responsive : {
				"columns" : [ {
					"data" : "Employee Name",
					className : "all"
				}, {
					"data" : "TicketId",
					className : "all"
				}, {
					"data" : "Ticket Type",
					className : "all"
				}, {
					"data" : "Ticket Description",
					className : "all"
				}, {
					"data" : "Application Name",
					className : "all"
				}, {
					"data" : "Priority",
					className : "all"
				}, {
					"data" : "Activity",
					className : "all"
				}, {
					"data" : "Status",
					className : "all"
				}, {
					"data" : "Comments",
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
					<h3 class="panel-title text-center">
						<b> List of tickets Inprogress for last 5 days </b>
					</h3>
				</div>
				<div class="panel-body">
					<form action="nochangeinstatus.htm" method="post">
						<table id="inprogress_table" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Employee Name</th>
									<th>TicketId</th>
									<th>Ticket Type</th>
									<th>Ticket Description</th>
									<th>Application Name</th>
									<th>Priority</th>
									<th>Activity</th>
									<th>Status</th>
									<th>Updated On</th>
									<th>Comments</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${inProgress}" var="list">

									<tr>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control"
											name="amployee_name" value="${list[0].employee_name}">
											<c:out value="${list[0].employee_name}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="ticket_id"
											value="${list[1]}"> <c:out value="${list[1]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="ticket_type"
											value="${list[2]}"> <c:out value="${list[2]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="ticket_desc"
											value="${list[3]}"> <c:out value="${list[3]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control"
											name="application_name" value="${list[4].application_name}">
											<c:out value="${list[4].application_name}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="priority"
											value="${list[5]}"> <c:out value="${list[5]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="activity"
											value="${list[6]}"> <c:out value="${list[6]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="status"
											value="${list[7]}"> <c:out value="${list[7]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="updated_on"
											value="${list[8]}"> <c:out value="${list[8]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="comments"
											value="${list[9]}"> <c:out value="${list[9]}"></c:out>
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