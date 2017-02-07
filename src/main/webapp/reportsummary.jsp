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



<script type="text/javascript">
	history.forward();
	$(document).ready(function() {
		$("#tickets").DataTable({

			responsive : {
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
<%
String ename = request.getAttribute("empName") == null ? "" : (String) request.getAttribute("empName");
String appname = request.getAttribute("application") == null ? "" : (String) request.getAttribute("application");
request.setAttribute("ename", ename);
request.setAttribute("appname", appname);
%>
	<%@ include file="/menu.jsp"%>

	<br>
	<br>
	<br>
	<div class="container">
		<div class="rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title text-center"></h3>
				</div>
				<div class="panel-body">
					<form action="report.htm" method="post">
						<div class="row">
							<div class="form-group col-lg-3">
								<label for="emp_name" class="control-label">Employee
									Name:</label> <select name="empName" class="form-control">
									<option value=></option>
									<c:forEach items="${namelist}" var="empname" varStatus="loop">
										<option label="${empname}" ${empname == ename ? 'selected':''}>${empname}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group col-lg-2">
								<label for="application" class="control-label">Application:</label>
								<select class="form-control" name="application">
									<option value=""></option>
									<c:forEach items="${applist }" var="app">
										<option label="${app}" ${app == appname ? 'selected':''}>${app}</option>
									</c:forEach>

								</select>
							</div>
							<div class="form-group col-lg-2">
								<label for="state_id" class="control-label">Activity:</label> <select
									class="form-control" name="stateId">
									<option value=""></option>
									<option value="Analysis"
										${param.stateId == 'Analysis' ? 'selected' : '' }>Analysis</option>
									<option value="Build"
										${param.stateId == 'Build' ? 'selected' : '' }>Build</option>
									<option value="Deployment"
										${param.stateId == 'Deployment' ? 'selected' : '' }>Deployment</option>
									<option value="Design"
										${param.stateId == 'Design' ? 'selected' : '' }>Design</option>
									<option value="Documentation"
										${param.stateId == 'Documentation' ? 'selected' : '' }>Documentation</option>
									<option value="Estimation"
										${param.stateId == 'Estimation' ? 'selected' : '' }>Estimation</option>
									<option value="Testing"
										${param.stateId == 'Testing' ? 'selected' : '' }>Testing</option>
									<option value="UAT"
										${param.stateId == 'UAT' ? 'selected' : '' }>UAT</option>
								</select>
							</div>
							<div class="form-group col-lg-2">
								<label for="status" class="control-label">Current
									Status:</label> <select class="form-control" name="status">
									<option value=""></option>
									<option value="In progress"
										${param.status == 'In progress' ? 'selected' : '' }>In
										progress</option>
									<option value="On Hold"
										${param.status == 'On Hold' ? 'selected' : '' }>On
										Hold</option>
									<option value="Approved"
										${param.status == 'Approved' ? 'selected' : '' }>Approved</option>
									<option value="Completed"
										${param.status == 'Completed' ? 'selected' : '' }>Completed</option>
									<option value="Closed"
										${param.status == 'Closed' ? 'selected' : '' }>Closed</option>
									<option value="Not Started"
										${param.status == 'Not Started' ? 'selected' : '' }>Not
										Started</option>
									<option value="Open"
										${param.status == 'Open' ? 'selected' : '' }>Open</option>
									<option value="Yettoopen"
										${param.status == 'Yettoopen' ? 'selected' : '' }>Yet
										to open</option>
								</select>
							</div>
							
							<div class="form-group col-lg-2">
								<label for="release" class="control-label">Release:</label> <select
									class="form-control" name="release">
									<option value=""></option>
									<option value="Yes"
										${param.release == 'Yes' ? 'selected' : '' }>Yes</option>
									<option value="No" ${param.release == 'No' ? 'selected' : '' }>No</option>
								</select>
							</div>

						</div>

						<label class="control-label">Start Date:</label>

						<div class="row">
							<div id="StartFromDateContainer" class="date form-group col-lg-3">
								<label for="startDateFrom" class="control-label">From:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="startDateFrom"
										value="${param.startDateFrom}"><span
										class="input-group-addon"> <i
										class="glyphicon glyphicon-th"></i></span>
								</div>

							</div>
							<div id="StartToDateContainer" class="date form-group col-lg-3">
								<label for="startDate" class="control-label">To:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="startDateTo"
										value="${param.startDateTo}"><span
										class="input-group-addon"> <i
										class="glyphicon glyphicon-th"></i></span>
								</div>

							</div>
						</div>
						<label class="control-label">End Date:</label>
						<div class="row">
							<div id="EndFromDateContainer" class="date form-group col-lg-3">
								<label for="endDateFrom" class="control-label">From:</label>
								<div class="input-group date">
									<input type="text" class="form-control" name="endDateFrom"
										value="${param.endDateFrom}"><span
										class="input-group-addon"> <i
										class="glyphicon glyphicon-th"></i></span>
								</div>

							</div>
							<div id="EndToDateContainer" class="date form-group col-lg-3">
								<label for="endDateTo" class="control-label">To:</label>
								<div class="input-group date">
									<input type="text" class="form-control" name="endDateTo"
										value="${param.endDateTo}"><span
										class="input-group-addon"> <i
										class="glyphicon glyphicon-th"></i></span>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6">
								<br>
								<button type="submit" class="btn btn-success">Submit</button>
								&nbsp;&nbsp;&nbsp; <input type="reset" value="Clear"
									onclick="window.location.href='\ reportsummary.htm'"
									class="btn btn-warning" />
							</div>
						</div>

					</form>

				</div>
			</div>
		</div>
		<br /> <br />
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
				<h4 class="panel-title text-right">
						Ticket dump on <b><c:out value="${showDate}" ></c:out></b> 
						by <b><c:out value="${showCreate}" ></c:out></b>
					</h4>
					<h3 class="panel-title text-center">
						<b> Report Summary </b>
					</h3>
				</div>
				<div class="panel-body">
					<table id="tickets" class="display responsive  nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Resource</th>
								<th>Ticket ID</th>
								<th>Ticket Type</th>
								<th>Description</th>
								<th>Application</th>
								<th>Priority</th>
								<th>Activity</th>
								<th>Current Status</th>
								<th>Tester</th>
								<th>Comments</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Remedy</th>
								<th>Documentation</th>
								<th></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="ticket" items="${allTickets}">
								<tr>
									<td>${ticket[0].employee_name }</td>
									<td>${ticket[1] }</td>
									<td>${ticket[2] }</td>
									<td>${ticket[3] }</td>
									<td>${ticket[4].application_name }</td>
									<td>${ticket[5] }</td>
									<td>${ticket[6] }</td>
									<td>${ticket[7] }</td>
									<td>${ticket[8] }</td>
									<td>${ticket[9] }</td>
									<td>${ticket[10] }</td>
									<td>${ticket[11] }</td>
									<td>${ticket[12] }</td>
									<td>${ticket[13] }</td>
									<td></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#StartFromDateContainer').datepicker({
				format : 'yyyy-mm-dd'
			});
		});
		$(function() {
			$('#StartToDateContainer').datepicker({
				format : 'yyyy-mm-dd'
			});
		});
		$(function() {
			$('#EndFromDateContainer').datepicker({
				format : 'yyyy-mm-dd'
			});
		});
		$(function() {
			$('#EndToDateContainer').datepicker({
				format : 'yyyy-mm-dd'
			});
		});
	</script>
</body>
</html>
