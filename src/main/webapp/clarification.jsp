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
<script>
	history.forward();

	function fun(ticketId) {
		var comment = $("#comment" + ticketId).val();
		if (comment.trim().length == 0) {

			comment = $("span.dtr-data  #comment" + ticketId).val();
		}
		$.ajax({
			type : 'GET',
			url : "clarification.htm",
			contentType : "application/json",
			data : {
				"ticketId" : ticketId,
				"comment" : comment
			},
			success : function(data) {
				$('#success').html(data);
				
				setTimeout(function() {
					location.reload();
				}, 1000);

			},

			error : function(xhr, status, error) {
				$("#failed").html('Failed').css('display', 'block');
			}
		});
	}

	function clarify(formName, deleteTicketId, comments, fieldName, commentName) {
		$("#" + fieldName).val(deleteTicketId);
		$("#" + commentName).val(comments);
		$("#" + formName).submit();

	}

	$(document).ready(function() {

		$("#tickets").DataTable({
			responsive : {
				details : {
					type : 'column',
					target : -1,
				}
			},
			columnDefs : [ {
				className : 'control',
				orderable : false,
				targets : -1,
			// "mData": null,
			} ]
		});

		$('#ticket').DataTable({

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
	<%@ include file="/menu.jsp"%>
	<br>
	<br>
	<br>
	<div class="container">

		<!-- <div class="table-responsive"> -->
		<div>
			<div class="row rowAlign">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title text-center"></h3>
					</div>
					<div class="panel-body">
						<form action="toclarification.htm" method="post" id="tick">
							<div align="Center">
								<span class="success" style="color: green">${MESSAGE}</span>
							</div>
							<div class="row">
								<div class="form-group col-lg-3">
									<label for="ticketId" class="control-label">Ticket Id:</label>
									<input type="text" class="form-control" name="ticketId"
										value="${param.ticketId}" id="select">
								</div>

							</div>
							<br>
							<button type="submit" class="btn btn-success">Submit</button>
							&nbsp;&nbsp;&nbsp; <input type="reset" value="Clear"
								class="btn btn-warning" />
						</form>
					</div>

				</div>
			</div>
		</div>
		<br /> <br />
		<c:if test="${not empty allTickets}">

			<div class="row rowAlign">
				<div class="panel panel-warning">
					<div class="panel-heading">
					<h4 class="panel-title text-right">
						Ticket dump on <b><c:out value="${showDate}" ></c:out></b> 
						by <b><c:out value="${showCreate}" ></c:out></b>
					</h4>

						<h3 class="panel-title text-center">
							<b> Ticket Status </b>
						</h3>
					</div>
					<div class="panel-body">
						<form action="clarification.htm" id="clarify" method="post">
							<div align="Center">
								<span class="success" id="success" style="color: green;">${SUCCESS}</span>
							</div>
							<div align="Center">
								<span class="failed" id="failed" style="color: red">${FAILED}</span>
							</div>
							<input type="hidden" name="ticketId" id="ticketId" value="" />
							<table id="ticket" class="display responsive  nowrap"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Ticket ID</th>
										<th>Ticket Type</th>
										<th>Employee name</th>
										<th>Description</th>
										<th>Application</th>
										<th>Current Status</th>
										<th>Supervisor Comments</th>
										<th></th>
										<th></th>
									</tr>
								</thead>

								<tbody>


									<c:forEach var="ticket" items="${allTickets}">

										<tr>

											<td><c:out value="${ticket[0]}">
												</c:out></td>
											<td><c:out value="${ticket[1]}">
												</c:out></td>
											<td><c:out value="${ticket[2]}">
												</c:out></td>
											<td><c:out value="${ticket[3]}">
												</c:out></td>
											<td><c:out value="${ticket[4]}">
												</c:out></td>
											<td><c:out value="${ticket[6]}">
												</c:out></td>
											<td><textarea class="form-control"
													id="comment${ticket[0]}" name="comment${ticket[0]}"></textarea>
											</td>
											<td><input type="submit" name="save" value="Save"
												class="btn btn-success" onclick="fun('${ticket[0]}')" /></td>
											<td></td>

										</tr>

									</c:forEach>

								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>


		</c:if>
		<br /> <br />
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">

					<h3 class="panel-title text-center">
						<b> Ticket Status </b>
					</h3>
				</div>
				<div class="panel-body">
					<form action="deleteclarification.htm" id="delete" method="post">
						<input type="hidden" name="deleteTicketId" id="deleteTicketId"
							value=""> <input type="hidden" name="comments"
							id="comments" value="">

						<table id="tickets" class="display responsive  nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Ticket ID</th>
									<th>Ticket Type</th>
									<th>Employee name</th>
									<th>Description</th>
									<th>Application</th>
									<th>Current Status</th>
									<th>Supervisor Comments</th>
									<th>Response</th>
									<th></th>
									<th></th>

								</tr>
							</thead>
							<tbody>

								<c:forEach var="ticket" items="${ticketList}">
									<tr>
										<td><c:out value="${ticket[0]}">
											</c:out></td>
										<td><c:out value="${ticket[1]}">
											</c:out></td>
										<td><c:out value="${ticket[2]}">
											</c:out></td>
										<td><c:out value="${ticket[3]}">
											</c:out></td>
										<td><c:out value="${ticket[4]}">
											</c:out></td>
										<td><c:out value="${ticket[5]}">
											</c:out></td>
										<td><c:out value="${ticket[6]}">
											</c:out></td>
										<td><c:out value="${ticket[7]}">
											</c:out></td>

										<td><input type="submit" name="delete" value="Delete"
											class="btn btn-success"
											onclick="clarify('delete', '${ticket[0]}', '${ticket[6]}', 'deleteTicketId' ,'comments')" /></td>
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
