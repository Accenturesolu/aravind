<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
<title>Status Tracking System</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
<title>Status Tracking System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css"></link>

<script>
	history.forward();

	$(document).ready(
			function() {
				$('#resource').validate(
						{
							rules : {
								admin_pref : {
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
</head>

<body>
<body>
	<%@ include file="/menu.jsp"%>

	<br>
	<br>
	<br>
	<div class="container">
		<div class="rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
				<h4 class="panel-title text-right">
						Ticket dump on <b><c:out value="${showDate}" ></c:out></b> 
						by <b><c:out value="${showCreate}" ></c:out></b>
					</h4>
					<h3 class="panel-title text-center">
						<b> Add Resource </b>
					</h3>
				</div>
				<div class="panel-body">

					<form action="addResource.htm" method="post" id="resource">
						<div align="Center" style="color: green">
							<span class="success">${msg}</span>
						</div>
						<div align="Center" style="color: red">
							<span class="success">${err}</span>
						</div>
						<div class="form-group col-lg-6">
							<label for="emp_name">Employee Name<sup
								style="color: red;">*</sup>:
							</label> <input type="text" class="form-control" name="emp_name"
								value="${param.emp_name}" required>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="emp_id">Employee ID<sup style="color: red;">*</sup>:
							</label> <input type="text" class="form-control" name="emp_id"
								value="${param.emp_id}" required>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="admin_pref" class="control-label">Admin
								Preference<sup style="color: red;">*</sup>:
							</label> <select class="form-control" name="admin_pref" required>
								<option value=""></option>
								<option value="Yes"
									${param.admin_pref == 'Yes' ? 'selected' : '' }>Yes</option>
								<option value="No"
									${param.admin_pref == 'No' ? 'selected' : '' }>No</option>
							</select>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="email">Email ID<sup style="color: red;">*</sup>:
							</label> <input type="email" class="form-control" name="email"
								value="${param.email}" required>
						</div>
						<div class="form-group col-lg-6">
							<div align="center"></div>
							<br>
							<button type="submit" class="btn btn-success">Submit</button>
							&nbsp;&nbsp;&nbsp; <input type="reset" value="Clear"
								class="btn btn-warning" />
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
