<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>
<head>
<title>Status Tracking System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
<link rel="stylesheet" href="css/style.css"></link>
<script type="text/javascript">
	history.forward();

	function validatePassword() {
		var opwd = document.forms["changepassword"]["password"].value;
		var npwd = document.forms["changepassword"]["new_pwd"].value;
		var cpwd = document.forms["changepassword"]["conf_pwd"].value;
		if (npwd != cpwd) {
			document.getElementById('errfn').innerHTML = "New Password and confirm password must be same !!";
			return false;
		}
		if (cpwd == opwd) {
			document.getElementById('errfn').innerHTML = "Old Password and New password must be different !!";
			return false;
		}

	}
</script>
</head>

<body>
	<%@ include file="/menuuser.jsp"%>

	<br>
	<br>
	<br>
	<div class="container">
		<div class="rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<b> Set Your Password </b>
					</h3>
				</div>
				<div class="panel-body">
					<form action="changepassword.htm" method="post"
						name="changepassword" id="changepassword"
						onsubmit="return validatePassword();">
						<div align="Center" style="color: green">
							<span class="success" style="color: red">${status}</span>
						</div>
						<div align="Center" style="color: red">
							<span class="success" style="color: red">${err}</span>
						</div>
						<div id="errfn" align="center" style="color: red;"></div>
						<div class="form-group col-lg-6">
							<label for="password">Old Password<sup
								style="color: red;">*</sup>:
							</label> <input type="password" class="form-control" name="password"
								id="password" placeholder="Enter your old password" required>
						</div>
						<div class="form-group col-lg-6">
							<label for="new_pwd">New Password<sup style="color: red;">*</sup>:
							</label> <input type="password" class="form-control" name="new_pwd"
								id="new_pwd" placeholder="Enter your new password" required>
						</div>
						<div class="form-group col-lg-6">
							<label for="conf_pwd">Confirm Password<sup
								style="color: red;">*</sup>:
							</label> <input type="password" class="form-control" name="conf_pwd"
								id="conf_pwd" placeholder="Enter your confirm password" required>
						</div>
						<div class="form-group col-lg-6">
							<div align="center"></div>
							<br>
							<button type="submit" class="btn btn-success">Change</button>
							<!-- <input type="submit" class="btn btn-success" value="Change" id="submit"/> -->
							&nbsp;&nbsp;&nbsp; <input type="reset" value="Clear"
								class="btn btn-warning" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>