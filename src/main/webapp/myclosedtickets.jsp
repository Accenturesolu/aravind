<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
<title>Status Tracking System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-datepicker3.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://code.jquery.com/jquery-1.12.3.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="css/style.css"></link>
<script type="text/javascript">
	history.forward();
	$(document).ready(function() {
		$("#tickets").DataTable({

			responsive : {
				"columns" : [ {
					"data" : "Ticketid",
					className : "all"
				}, {
					"data" : "Ticket type",
					className : "all"
				}, {
					"data" : "Description",
					className : "min-phone-l"
				}, {
					"data" : "Application",
					className : "min-tablet"
				}, /* {
					"data" : "Priority",
					className : "all"
				}, {
					"data" : "Activity",
					className : "desktop"
				}, */ {
					"data" : "Status",
					className : "desktop"
				},/*  {
					"data" : "Comments",
					className : "desktop"
				}, */ {
					"data" : "Start Date",
					className : "all"
				}, {
					"data" : "End Date",
					className : "all"
				}, {
					"data" : "Remedy",
					className : "desktop"
				}, {
					"data" : "Documentation",
					className : "desktop"
				}
 ],
				details : {
					type : 'column',
					target : -1
				}
			},
			columnDefs : [ {
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
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<b> My Closed Tickets </b>
					</h3>
				</div>
				<div class="panel-body">
					<table id="tickets" class="display responsive  nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Ticketid</th>
								<th>Ticket type</th>
								<th>Description</th>
								<th>Application</th>
								<!-- <th>Priority</th>
								<th>Activity</th> -->
								<th>Status</th>
								<!-- <th>Comments</th> -->
								<th>Start date</th>
								<th>End date</th>
								<th>Remedy</th>
								<th>Documentation</th>

								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${closedtickets}" var="ticket">
								<tr>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="ticketid" value="${ticket[0]}">
										<c:out value="${ticket[0]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="tickettype" value="${ticket[1]}">
										<c:out value="${ticket[1]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="desc" value="${ticket[2]}">
										<c:out value="${ticket[2]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="app" value="${ticket[3]}">
										<c:out value="${ticket[3].application_name}"></c:out>
									</td>
									<%-- <td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="pri" value="${ticket[4]}">
										<c:out value="${ticket[4]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="act" value="${ticket[5]}">
										<c:out value="${ticket[5]}"></c:out>
									</td> --%>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="status" value="${ticket[4]}">
										<c:out value="${ticket[4]}"></c:out>
									</td>
								<%-- 	<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="comments" value="${ticket[7]}">
										<c:out value="${ticket[7]}"></c:out>
									</td> --%>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="startdate" value="${ticket[5]}">
										<c:out value="${ticket[5]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="enddate" value="${ticket[6]}">
										<c:out value="${ticket[6]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="remedy" value="${ticket[7]}">
										<c:out value="${ticket[7]}"></c:out>
									</td>
									<td align="left">
										<div class="form-group"></div> <input type="hidden"
										class="form-control" name="doc" value="${ticket[8]}">
										<c:out value="${ticket[8]}"></c:out>
									</td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>