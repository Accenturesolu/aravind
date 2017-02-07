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
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" lang="javascript"
	src="code.jquery.com/jquery-1.12.4.js"></script>

<script type="text/javascript" lang="javascript"
	src="https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="css/style.css"></link>
<script type="text/javascript">
history.forward();
	function fun(formName, ticket_id, ticket_desc, app_name, empname,
			Ticket_id, Ticket_desc, App_name, Empname) {
		$("#" + Ticket_id).val(ticket_id);
		$("#" + Ticket_desc).val(ticket_desc);
		$("#" + App_name).val(app_name);
		$("#" + Empname).val(empname);
		$("#" + formName).submit();
	}

	$(document).ready(function() {
		$("#assign").DataTable({

			responsive : {
				"columns" : [ {
					"data" : "TicketId",
					className : "all"
				}, {
					"data" : "Ticket Description",
					className : "all"
				}, {
					"data" : "Application Name",
					className : "all"
				}, {
					"data" : "AssignTo",
					className : "all"
				}, {
					"data" : "Assign",
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

	function assign(ticket_id, ticket_desc, app_name) {

		var empname = $("#empname" + ticket_id).val();

		if (empname.trim().length == 0) {
			var empname = $("span.dtr-data #empname" + ticket_id).val();

		}

		$.ajax({
			type : 'GET',
			url : "assignticket.htm",
			contentType : "application/json",
			data : {
				'ticket_id' : ticket_id,
				'ticket_desc' : ticket_desc,
				'app_name' : app_name,
				'empname' : empname
			},

			success : function(data) {
				$('#success').html(data);	
				setTimeout(function(){
					location.reload();
				}, 1000);

			},

			error : function(xhr, status, error) {
				$("#failed").html('Failed').css('display','block');
			}

		});
	}
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
						<b> Assign Ticket </b>
					</h3>
				</div>
				<div class="panel-body">
					<div align="Center">
						<span class="success" id="success" style="color: green;">${SUCCESS}</span>
					</div>
					<div align="Center">
						<span class="failed" id="failed" style="color: red">${FAILED}</span>
					</div>
					<table id="assign" class="display responsive  nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Ticket Id</th>
								<th>Ticket Description</th>
								<th>Application Name</th>
								<th>AssignTo</th>
								<th>Assign</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${showToassign}" var="list">
								<form action="assignticket.htm" method="post" id="assign">
									<tr>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="ticket_id"
											id="ticket_id" value="${list[0]}"> <c:out
												value="${list[0]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="ticket_desc"
											id="ticket_desc" value="${list[1]}"> <c:out
												value="${list[1]}"></c:out>
										</td>
										<td align="left">
											<div class="form-group col-xs-6 col-md-6 col-centered"></div>
											<input type="hidden" class="form-control" name="app_name"
											id="app_name" value="${list[2]}"> <c:out
												value="${list[2]}"></c:out>
										</td>
										<td><select class="form-control" name="empname${list[0]}"
											id="empname${list[0]}">
												<option value=""></option>
												<c:forEach items="${emplist}" var="emplist">
													<option label="${emplist}">${emplist}</option>
												</c:forEach>
										</select></td>
										<td><div class="form-group col-lg-6 col-md-6">
												<input type="button" class="btn btn-success" value="Assign"
													onclick="assign('${list[0]}', '${list[1]}', '${list[2]}')" />
											</div></td>
										<td></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
</body>
</html>