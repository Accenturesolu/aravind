<!DOCTYPE html>
<html lang="en">
<%@ include file="/index.jsp"%>

<head>
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
</head>
<script type="text/javascript">
	history.forward();

	$(document).ready(
			function() {
				$('#addticket').validate(
						{
							rules : {
								app : {
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
<%
String appname = request.getAttribute("app") == null ? "" : (String) request.getAttribute("app");
request.setAttribute("appname", appname);
%>
	<%@ include file="/menu.jsp"%>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row rowAlign">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title text-center">
						<b> Add Tickets </b>
					</h3>
				</div>
				<div class="panel-body">
					<form class="form-group" name="addticket" id="addticket"
						action="addticket.htm" method="post">
						<div align="Center">
							<span class="success" style="color: green">${SUCCESS}</span>
						</div>
						<div align="Center">
							<span class="message" style="color: red">${MESSAGE}</span>
						</div>
						<div class="row">
							<div class="form-group col-lg-4">
								<div align="center"></div>
								<label for="ticketid" class="control-label">TicketID<sup
									style="color: red;">*</sup>:
								</label> <input type="text" class="form-control" name="tickid"
									id="select" value="${param.tickid}" required>
							</div>

							<div class="form-group col-lg-4">
								<div align="center"></div>
								<label for="description" class="control-label">Description<sup
									style="color: red;">*</sup>:
								</label> <input type="text" class="form-control" name="desc"
									id="select1" value="${param.desc}" required>
							</div>

							<div class="form-group col-lg-4">
								<div align="center"></div>
								<label for="application" class="control-label">Application<sup
									style="color: red;">*</sup>:
								</label> <select class="form-control" name="app" id="select2">
									<option value=""></option>
									<c:forEach items="${appList}" var="app">
										<option label="${app}" ${app == appname ? 'selected':''}>${app}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-lg-6">
								<div align="center"></div>
								<br>
								<button type="submit" class="btn btn-success">Add</button>
								&nbsp;&nbsp;&nbsp; <input type="reset" value="Clear"
									onclick="window.location.href='\ add.htm'"
									class="btn btn-warning" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
