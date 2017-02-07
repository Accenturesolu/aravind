<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
<script type="text/javascript">
	history.forward();
	function validateForm() {
		var uname = document.forms["logForm"]["empid"].value;
		var password = document.forms["logForm"]["pass1"].value;
		if (uname == null || uname == "") {
			alert("Please provide Employee ID");
			return false;
		}
		if (password == null || password == "") {
			alert("Please provide Password");
			return false;
		}

	}
</script>
<title>Status Tracking System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css"></link>
</head>

<body>

	<div class="container">

		<div>
			<div class="container title">
				<h1>Welcome to Status Tracking System</h1>
			</div>

			<br> <br> <br>
			<div class="container container1">
				<div class="row">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<h3 class="panel-title">Login Here</h3>
						</div>
						<div class="panel-body">
							<form action="searchResource.htm" method="post"
								onsubmit="return validate()">
								<div align="Center">
									<span class="success" style="color: green">${status}</span>
								</div>
								<div align="Center">
									<span class="message" style="color: red">${ERROR}</span>
								</div>
								<div class="form-group has-feedback">
									<label for="emp_id">Employee ID<sup style="color: red;">*</sup>:
									</label> <i class="glyphicon glyphicon-user form-control-feedback"></i>
									<input type="text" class="form-control" id="input"
										name="emp_id" placeholder="Enter Your Employee ID" required>

								</div>
								<div class="form-group has-feedback ">
									<label for="pwd">Password<sup style="color: red;">*</sup>:
									</label> <i
										class="glyphicon glyphicon-warning-sign form-control-feedback"></i>
									<input type="password" class="form-control" id="pwd"
										name="password" placeholder="Enter password" required>
								</div>
								<div>
									<button type="submit" class="btn btn-success">Submit</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="Clear" class="btn btn-warning" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
</body>
</html>

