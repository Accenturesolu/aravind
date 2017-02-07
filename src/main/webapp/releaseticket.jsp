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

<script>
	history.forward();
	$(document).ready(function() {
		$('#releases').DataTable({

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

	
	function fun(formName, ticketid, release, fieldName, commentName) {
		$("#" + fieldName).val(ticketid);
		$("#" + commentName).val(release);
		$("#" + formName).submit();

	}
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
					<h3 class="panel-title text-center"></h3>
				</div>

				<div class="panel-body">
					<form action="releaseticket.htm" id="ticket${release[0]}"
						name="ticket${release[0]}" method="post">
						<div align="Center">
							<span class="success" style="color: green;">${success}</span>
						</div>
						<div align="Center">
							<span class="failed" style="color: red">${err}</span>
						</div>
						<table id="releases" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Ticket ID</th>
									<th class="col-md-4">Description</th>
									<th>Application</th>
									<th>Resource</th>
									<th>Release</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="release" items="${releaseTickets}">

									<tr>

										<td>
											<div class="form-group col-lg-6">
												<input type="hidden" class="form-control" name="ticketid"
													id="ticketid" value="${release[0]}">
												<c:out value="${release[0]}">
												</c:out>
											</div>
										</td>

										<td>
											<div class="form-group">
												<input type="hidden" class="form-control" name="desc"
													value="${release[1]}">
												<c:out value="${release[1]}">
												</c:out>
											</div>
										</td>

										<td>
											<div class="form-group col-lg-6">
												<input type="hidden" class="form-control" name="app"
													value="${release[2]}">
												<c:out value="${release[2].application_name}">
												</c:out>
											</div>
										</td>

										<td>
											<div class="form-group col-lg-6">
												<input type="hidden" class="form-control" name="resname"
													value="${release[3]}">
												<c:out value="${release[3].employee_name}">
												</c:out>
											</div>
										</td>

										<td>
											<div class="form-group col-lg-6">
												<input type="hidden" class="form-control" name="release"
													value="${release[4]}">
												<c:out value="${release[4]}">
												</c:out>
											</div>
										</td>
										<td>
											<div class="form-group col-lg-6 col-md-6">
												<input type="button" class="btn btn-success" value="Update"
													onclick="fun('ticket','${release[0]}','${release[4]}','ticketid','release') ">
											</div>
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

