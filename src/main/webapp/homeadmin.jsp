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
<link rel="stylesheet" href="css/style.css"></link>
<script>
	history.forward();
	function fun(formName, ticketId, fieldName) {
		$("#" + fieldName).val(ticketId);
		$("#" + formName).submit();
	}
	function val(formName, ticketid, tickettype, desc, application, priority,
			activity, status, tester, comments, Ticketid, Tickettype, Desc,
			Appl, Priority, Activity, Status, Tester, Comments) {
		$("#" + Ticketid).val(ticketid);
		$("#" + Tickettype).val(tickettype);
		$("#" + Desc).val(desc);
		$("#" + Appl).val(application);
		$("#" + Priority).val(priority);
		$("#" + Activity).val(activity);
		$("#" + Status).val(status);
		$("#" + Tester).val(tester);
		$("#" + Comments).val(comments);
		$("#" + formName).submit();
	}
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
					"data" : "Employee name",
					className : "all"
				}, {
					"data" : "Updated on",
					className : "all"
				}, {
					"data" : "Status",
					className : "desktop"
				}, {
					"data" : "Comments",
					className : "desktop"
				} ],
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
	$(document).ready(function() {
		$('#doc_table').DataTable({

			responsive : {

				"columns" : [ {
					"data" : "Ticket ID",
					className : "all"
				}, {
					"data" : "Ticket Type",
					className : "desktop"
				}, {
					"data" : "Description",
					className : "min-phone"
				}, {
					"data" : "Application",
					className : "min-tablet"
				}, {
					"data" : "Status",
					className : "all"
				}, {
					"data" : "Comments",
					className : "desktop"
				}, {
					"data" : "Remedy",
					className : "desktop"
				}, {
					"data" : "Documentation",
					className : "desktop"
				}, {
					"data" : "Update",
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
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
				<h4 class="panel-title text-right">
						Ticket dump on <b><c:out value="${showDate}" ></c:out></b> 
						by <b><c:out value="${showCreate}" ></c:out></b>
					</h4>
					<h3 class="panel-title text-center">
					
						<b> Ticket information </b>
					</h3>
				</div>
				<div class="panel-body">
					<form action="updateTicket.jsp" id="update" method="post">
						<input type="hidden" name="ticketid" id="ticketid" value="" /> <input
							type="hidden" name="tickettype" id="tickettype" value="" /> <input
							type="hidden" name="desc" id="desc" value="" /> <input
							type="hidden" name="application" id="application" value="" /> <input
							type="hidden" name="priority" id="priority" value="" /> <input
							type="hidden" name="activity" id="activity" value="" /> <input
							type="hidden" name="status" id="status" value="" /> <input
							type="hidden" name="tester" id="tester" value="" /> <input
							type="hidden" name="comments" id="comments" value="" />
						<table id="tickets" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Ticket Id</th>
									<th>Ticket type</th>
									<th>Description</th>
									<th>Application</th>
									<th>Priority</th>
									<th>Activity</th>
									<th>Status</th>
									<th>Comments</th>
									<th>Tester</th>
									<th>Start date</th>
									<th></th>
									<th></th>

								</tr>
							</thead>
							<tbody>

								<c:forEach var="ticket" items="${allTickets}">


									<tr>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="ticketid" value="${ticket[0]}">
											<c:out value="${ticket[0]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="tickettype" value="${ticket[1]}">
											<c:out value="${ticket[1]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="desc" value="${ticket[2]}">
											<c:out value="${ticket[2]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="application" value="${ticket[3]}">
											<c:out value="${ticket[3]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" id="priority"
											value="${ticket[4]}"> <c:out value="${ticket[4]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="activity" value="${ticket[5]}">
											<c:out value="${ticket[5]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="status" value="${ticket[6]}">
											<c:out value="${ticket[6]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="comments" value="${ticket[7]}">
											<c:out value="${ticket[7]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="tester" value="${ticket[8]}">
											<c:out value="${ticket[8]}">
											</c:out>
										</td>

										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" id="sdate" value="${ticket[9]}">
											<c:out value="${ticket[9]}">
											</c:out>
										</td>

										<td align="left"><input type="submit"
											class="btn btn-success" value="Update"
											onclick="val('update', '${ticket[0]}','${ticket[1]}','${ticket[2]}','${ticket[3]}','${ticket[4]}','${ticket[5]}','${ticket[6]}','${ticket[7]}','${ticket[8]}', 'ticketid','tickettype','desc','application','priority','activity','status','comments','tester')" />
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

		<br /> <br /> <br />
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<b> Documentation </b>
					</h3>
				</div>
				<div class="panel-body">
					<form id="update" action="updatedocadmin.jsp" method="post">
						<input type="hidden" name="ticketId" id="ticketId" value="" />
						<table id="doc_table" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Ticket Id</th>
									<th>Ticket type</th>
									<th>Description</th>
									<th>Application</th>
									<th>Status</th>
									<th>Comments</th>
									<th>Remedy</th>
									<th>Documentation</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allTickets1}" var="ticket">

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
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="status" value="${ticket[4]}">
											<c:out value="${ticket[4]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="comments" value="${ticket[5]}">
											<c:out value="${ticket[5]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="remedy" value="${ticket[6]}">
											<c:out value="${ticket[6]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group"></div> <input type="hidden"
											class="form-control" name="doc" value="${ticket[7]}">
											<c:out value="${ticket[7]}"></c:out>
										</td>
										<td align="left"><input type="submit"
											class="btn btn-success" value="Update"
											onclick="fun('update', '${ticket[0]}', 'ticketId')" /></td>
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