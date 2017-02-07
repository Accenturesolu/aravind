<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
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
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css" />
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
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="css/style.css"></link>
</head>
<script type="text/javascript">
	history.forward();
	function Validate() {
		var select2 = document.getElementById("select2");
		if (select2.value == "No") {
			alert("Please fill the comments!");
			return false;
		}
	}

	$(document).ready(
			function() {
				$('#updatedocument').validate(
						{
							rules : {
								rem : {
									required : true
								},
								doc : {
									required : true
								}
							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							}
						});
			});
</script>
<body>
	<%@ include file="/menu.jsp"%>
	<br />
	<br />
	<br />
	<div class="container">
		<div>
			<br> <br> <br>
			<div class="row rowAlign">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title text-center">
							<b> Update Document </b>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-group" name="updatedocument" id="updatedocument"
							action="updatedocument.htm" method="post">
							<div align="Center">
								<span class="message" style="color: red">${MESSAGE}</span>
							</div>
							<div class="form-group col-lg-3">
								<label for="ticketid" class="control-label">TicketID<sup
									style="color: red;">*</sup>:
								</label> <input type="text" class="form-control" name="tickid"
									id="select" value="${param.ticketId}" readonly>
							</div>

							<div class="form-group col-lg-3">
								<label for="remedy" class="control-label">Remedy<sup
									style="color: red;">*</sup>:
								</label> <select class="form-control" name="rem" id="select1">
									<option value=""></option>
									<option value="Progress"
										${param.rem == 'Progress' ? 'selected' : '' }>Progress</option>
									<option value="Close"
										${param.rem == 'Close' ? 'selected' : '' }>close</option>
								</select>
							</div>

							<div class="form-group col-lg-3">
								<label for="documentation" class="control-label">Documentation<sup
									style="color: red;">*</sup>:
								</label> <select class="form-control" name="doc" id="select2">
									<option value=""></option>
									<option value="Yes" ${param.doc == 'Yes' ? 'selected' : '' }>Yes</option>
									<option value="No" onclick="return Validate()"
										${param.doc == 'No' ? 'selected' : '' }>No</option>
								</select>
							</div>

							<div class="form-group col-lg-3">
								<label for="comment">Comment:</label> <input type="text"
									class="form-control" name="comment" id="select3"
									value="${param.comment}" required>
							</div>

							<div class="form-group col-lg-6">
								<div align="center"></div>
								<br>
								<button type="submit" class="btn btn-success">Save</button>
								&nbsp;&nbsp;&nbsp; <input type="reset" value="Clear"
									class="btn btn-warning" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

