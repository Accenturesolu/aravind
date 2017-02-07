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
	$(document).ready(function() {
		$("#reassign_table").DataTable({

			responsive : {
				"columns" : [ {
					"data" : "TicketId",
					className : "all"
				}, {
					"data" : "Ticket Description",
					className : "all"
				}, {
					"data" : "AssignedTo",
					className : "all"
				}, {
					"data" : "ReAssign",
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

	function reassign(ticket_id, ticket_desc, oldempname) {

		var newempname = $("#newempname" + ticket_id).val();

		if (newempname.trim().length == 6) {
			var newempname = $("span.dtr-data #newempname" + ticket_id).val();

		}

		$.ajax({
			type : 'GET',
			url : "reassignticket.htm",
			contentType : "application/json",
			data : {
				'ticket_id' : ticket_id,
				'ticket_desc' : ticket_desc,
				'oldempname' : oldempname,
				'newempname' : newempname
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
						<b> Re-Assign Ticket </b>
					</h3>
				</div>
				<div class="panel-body">
					<div align="Center">
						<span class="success" id="success" style="color: green">${SUCCESS}</span>
					</div>
					<div align="Center">
						<span class="failed" id="failed" style="color: red">${FAILED}</span>
					</div>

					<table id="reassign_table" class="display responsive  nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>TicketId</th>
								<th>Ticket Description</th>
								<th>AssignedTo</th>
								<th>ReAssign</th>
								<th>Assign</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${showToreassign}" var="list">
								<form action="reassignticket.htm" method="post" id="reassign">
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
											<input type="hidden" class="form-control" name="oldempname"
											id="oldempname" value="${list[2].employee_name}"> <c:out
												value="${list[2].employee_name}"></c:out>
										</td>
										<td><select class="form-control"
											name="newempname${list[0]}" id="newempname${list[0]}">
												<option value=""></option>
												<c:forEach items="${emplist}" var="emplist">
													<option label="${emplist}">${emplist}</option>
												</c:forEach>
										</select></td>
										<td><div class="form-group col-lg-6 col-md-6">
												<input type="button" class="btn btn-success"
													value="Reassign"
													onclick="reassign('${list[0]}', '${list[1]}', '${list[2].employee_name}')" />
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