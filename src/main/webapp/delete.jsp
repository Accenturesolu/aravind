<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
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
	function ConfirmDelete(formName, ticketId, fieldName) {
		var x = confirm("Are you sure you want to delete?");
		if (x) {
			$("#" + fieldName).val(ticketId);
			$("#" + formName).submit();
		} else
			return false;
	}

	$(document).ready(function() {
		$("#delete").DataTable({

			responsive : {
				"columns" : [ {
					"data" : "Ticketid",
					className : "all"
				}, {
					"data" : "Description",
					className : "all"
				}, {
					"data" : "Application",
					className : "all"
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
</script>
</head>

<body>
	<%@ include file="/menu.jsp"%>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<b> Tickets List </b>
					</h3>
				</div>
				<div class="panel-body">
					<form action="deleteticket.htm" id="deleteticket" method="get">
						<input type="hidden" name="ticketId" id="ticketId" value="" />
						<div align="Center">
							<span class="success" style="color: green">${SUCCESS}</span>
						</div>

						<table id="delete" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Ticket ID</th>
									<th>Description</th>
									<th>Application</th>
									<th>Delete</th>
									<th></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${ticket}" var="ticket" varStatus="loop">

									<tr>
										<td><c:out value="${ticket[0]}"></c:out></td>
										<td><c:out value="${ticket[1]}"></c:out></td>
										<td><c:out value="${ticket[2]}"></c:out></td>
										<td><input type="button" name="delete"
											class="btn btn-success" value="Delete"
											onclick="ConfirmDelete('deleteticket', '${ticket[0]}', 'ticketId');" /></td>
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

