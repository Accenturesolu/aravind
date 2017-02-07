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
	function Validate() {
		var work = document.getElementById("work");
		if (work.value == "No") {
			alert("Please fill the comments!");
			return false;
		}
	}

	$(document).ready(
			function() {
				$('#updateticket').validate(
						{
							rules : {
								ticktype : {
									required : true
								},
								app : {
									required : true
								},
								priority : {
									required : true
								},
								activity : {
									required : true
								},
								status : {
									required : true
								},
								tester : {
									required : true
								},
								worktoday : {	required : true
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
	<%@ include file="/menuuser.jsp"%>
	<br />
	<br />
	<br />
	<div class="container">
		<div>
			<br> <br> <br>
			<div class="rowAlign">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3 class="panel-title text-center">
							<b> Update Ticket Status </b>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-group" name="updateticket" id="updateticket"
							action="updateTicket.htm" method="post">
							<div align="Center">
								<span class="message" style="color: red">${MESSAGE}</span>
							</div>
							<div class="form-group col-lg-3">
								<label for="ticketid" class="control-label">TicketID<sup
									style="color: red;">*</sup>:
								</label> <input type="text" class="form-control" name="tickid"
									id="select" value="${param.ticketid}" readonly>
							</div>

							<div class="form-group col-lg-3">
								<label for="tickettype" class="control-label">Ticket
									Type<sup style="color: red;">*</sup>:
								</label> <select class="form-control" name="ticktype" id="select1">
									<option value=""></option>
									<option value="Project"
										${"Project".equals(param["tickettype"])?"selected":""}>Project</option>
									<option value="Support"
										${"Support".equals(param["tickettype"])?"selected":""}>Support</option>
									<option value="Enhancement"
										${"Enhancement".equals(param["tickettype"])?"selected":""}>Enhancement</option>
								</select>
							</div>

							<div class="form-group col-lg-3">
								<label for="description" class="control-label">Description<sup
									style="color: red;">*</sup>:
								</label> <input type="text" class="form-control" name="desc"
									id="select2" value="${param.desc}" readonly>
							</div>

							<div class="form-group col-lg-3">
								<label for="application" class="control-label">Application<sup
									style="color: red;">*</sup>:
								</label> <select class="form-control" name="app" id="select3">
									<option value=""></option>
									<option value="CTS"
										${"CTS".equals(param["application"])?"selected":""}>CTS</option>
									<option value="DDS"
										${"DDS".equals(param["application"])?"selected":""}>DDS</option>
									<option value="DMF"
										${"DMF".equals(param["application"])?"selected":""}>DMF</option>
									<option value="EFIC"
										${"EFIC".equals(param["application"])?"selected":""}>EFIC</option>
									<option value="FGW"
										${"FGW".equals(param["application"])?"selected":""}>FGW</option>
									<option value="LA"
										${"LA".equals(param["application"])?"selected":""}>LA</option>
									<option value="MPIM"
										${"MPIM".equals(param["application"])?"selected":""}>MPIM</option>
									<option value="OS"
										${"OS".equals(param["application"])?"selected":""}>OS</option>
									<option value="PEIM"
										${"PEIM".equals(param["application"])?"selected":""}>PEIM</option>
									<option value="PPP"
										${"PPP".equals(param["application"])?"selected":""}>PPP</option>
									<option value="PSIM"
										${"PSIM".equals(param["application"])?"selected":""}>PSIM</option>
									<option value="PTMI"
										${"PTMI".equals(param["application"])?"selected":""}>PTMI</option>
									<option value="S&OP (CG)"
										${"S&OP (CG)".equals(param["application"])?"selected":""}>S&OP
										(CG)</option>
									<option value="S&OP (DV)"
										${"S&OP (DV)".equals(param["application"])?"selected":""}>S&OP
										(DV)</option>
									<option value="S&OP (IC)"
										${"S&OP (IC)".equals(param["application"])?"selected":""}>S&OP
										(IC)</option>

									<option value="TMI"
										${"TMI".equals(param["application"])?"selected":""}>TMI</option>
									<option value="WSA"
										${"WSA".equals(param["application"])?"selected":""}>WSA</option>
								</select>
							</div>

							<div class="form-group col-lg-3">
								<label for="priority" class="control-label">Priority<sup
									style="color: red;">*</sup>:
								</label> <select class="form-control" name="priority" id="select4">
									<option value=""></option>
									<option value="1"
										${"1".equals(param["priority"])?"selected":""}>1</option>
									<option value="2"
										${"2".equals(param["priority"])?"selected":""}>2</option>
									<option value="3"
										${"3".equals(param["priority"])?"selected":""}>3</option>
									<option value="4"
										${"4".equals(param["priority"])?"selected":""}>4</option>
									<option value="5"
										${"5".equals(param["priority"])?"selected":""}>5</option>
									<option value="6"
										${"6".equals(param["priority"])?"selected":""}>6</option>
									<option value="7"
										${"7".equals(param["priority"])?"selected":""}>7</option>
									<option value="8"
										${"8".equals(param["priority"])?"selected":""}>8</option>
								</select>
							</div>

							<div class="form-group col-lg-3">
								<label for="activity" class="control-label">Activity<sup
									style="color: red;">*</sup>:
								</label> <select class="form-control" name="activity" id="select5">
									<option value=""></option>
									<option value="Analysis"
										${"Analysis".equals(param["activity"])?"selected":""}>Analysis</option>
									<option value="Build"
										${"Build".equals(param["activity"])?"selected":""}>Build</option>
									<option value="Deployment"
										${"Deployment".equals(param["activity"])?"selected":""}>Deployment</option>
									<option value="Design"
										${"Design".equals(param["activity"])?"selected":""}>Design</option>
									<option value="Documentation"
										${"Documentation".equals(param["activity"])?"selected":""}>Documentation</option>
									<option value="Estimation"
										${"Estimation".equals(param["activity"])?"selected":""}>Estimation</option>
									<option value="Testing"
										${"Testing".equals(param["activity"])?"selected":""}>Testing</option>
									<option value="UAT"
										${"UAT".equals(param["activity"])?"selected":""}>UAT</option>
								</select>

							</div>

							<div class="form-group col-lg-3">
								<label for="currentstatus" class="control-label">Status
									<sup style="color: red;">*</sup>:
								</label> <select class="form-control" name="status" id="select6">
									<option value=""></option>
									<option value="In Progress"
										${"In Progress".equals(param["status"])?"selected":""}>In
										Progress</option>
									<option value="On Hold"
										${"On Hold".equals(param["status"])?"selected":""}>On
										Hold</option>
									<option value="Approved"
										${"Approved".equals(param["status"])?"selected":""}>Approved</option>
									<option value="Completed"
										${"Completed".equals(param["status"])?"selected":""}>Completed</option>
									<option value="Closed"
										${"Closed".equals(param["status"])?"selected":""}>Closed</option>
									<option value="Not Started"
										${"Not Started".equals(param["status"])?"selected":""}>Not
										Started</option>
								</select>
							</div>
							<div class="form-group col-lg-3">
								<label for="tester" class="control-label">Tester:</label> <select
									class="form-control" name="tester" id="select7">
									<option value=""></option>
									<option value="Vivek"
										${"Vivek".equals(param["tester"])?"selected":""}>Vivek</option>
									<option value="Dinesh"
										${"Dinesh".equals(param["tester"])?"selected":""}>Dinesh</option>
								</select>
							</div>

							<div class="form-group col-lg-3">
								<label for="comments">Comments<sup style="color: red;">*</sup>:
								</label> <input type="text" class="form-control" name="comments"
									id="select8" value="${param.comments}" required>
							</div>

							<div class="form-group col-lg-3">
								<label for="radio"><b>Work On Today<sup
										style="color: red;">*</sup>:
								</b></label> <br> <label class="radio-inline"> <input
									type="radio" name="worktoday" id="work" value="Yes">Yes
								</label> <label class="radio-inline"> <input type="radio"
									name="worktoday" id="work" value="No"
									onclick="return Validate()">No
								</label>
								<label for="worktoday" class="error" style="display:none;">* Please pick an option above</label>
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

