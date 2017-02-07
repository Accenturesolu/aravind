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
								emp_name : {
									required : true
								},
								isadmin : {
									required : true
								},
								isactive : {
									required : true
								},
								isinclude : {
									required : true
								},
								report : {
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
<%
String ename = request.getAttribute("emp_name") == null ? "" : (String) request.getAttribute("emp_name");
System.out.println("empname :"+ename);
%>
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
						<b> Modify Resource </b>
					</h3>
				</div>
				<div class="panel-body">

					<form action="modifyResource.htm" method="Post" id="resource">
						<div align="Center" style="color: green">
							<span class="success">${msg}</span>
						</div>
						<div class="form-group col-lg-6">
							<label for="emp_name">Employee Name<sup
								style="color: red;">*</sup>:
							</label> <select name="emp_name" class="form-control">
								<option value=""></option>
								<c:forEach items="${namelist}" var="empname" varStatus="loop">
									<option label="${empname}" ${empname == ename ? 'selected':''}>${empname}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="admin_preference">Admin Preference<sup
								style="color: red;">*</sup>:
							</label> <select name="isadmin" class="form-control">
								<option value=""></option>
								<option value="Yes" ${param.isadmin == 'Yes' ? 'selected' : '' }>Yes</option>
								<option value="No" ${param.isadmin == 'No' ? 'selected' : '' }>No</option>
							</select>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="active">Whether the resource is active<sup
								style="color: red;">*</sup>:
							</label> <select name="isactive" class="form-control">
								<option value=""></option>
								<option value="Yes"
									${param.isactive == 'Yes' ? 'selected' : '' }>Yes</option>
								<option value="No" ${param.isactive == 'No' ? 'selected' : '' }>No</option>
							</select>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="res">Include the resource in report<sup
								style="color: red;">*</sup>:
							</label> <select name="isinclude" class="form-control">
								<option value=""></option>
								<option value="Yes"
									${param.isinclude == 'Yes' ? 'selected' : '' }>Yes</option>
								<option value="No" ${param.isinclude == 'No' ? 'selected' : '' }>No</option>
							</select>
						</div>
						<div class="form-group col-lg-6 col-md-6">
							<label for="report">Report Reference<sup
								style="color: red;">*</sup>:
							</label> <select name="report" class="form-control">
								<option value=""></option>
								<option value="Yes" ${param.report == 'Yes' ? 'selected' : '' }>Yes</option>
								<option value="No" ${param.report == 'No' ? 'selected' : '' }>No</option>
							</select>
						</div>
						<div class="form-group col-lg-6">
							<div align="center"></div>
							<br>
							<button type="submit" class="btn btn-success">Change</button>
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