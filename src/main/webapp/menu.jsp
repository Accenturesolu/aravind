
<%
	HttpSession sess = request.getSession();
	String empid = (String) session.getAttribute("emp_ID");
	String pass = (String) session.getAttribute("password");
	String name = (String) session.getAttribute("name");
	sess.setAttribute("empid", empid);
	sess.setAttribute("pass1", pass);
	sess.setAttribute("name", name);
	System.out.println("empid:" + empid);
	System.out.println("name:" + name);
	System.out.println("password:" + pass);
%>
<div class="container">
	<div class="style" align="center">
		<h1>
			<b> Status Tracking System </b>
		</h1>
	</div>

	<nav class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a
						href="searchResource.htm?emp_id=<%=empid%>&password=<%=pass%>"><span
							class="glyphicon glyphicon-home"></span> <b> Home </b></a></li>
					<li><a href="uploadfile.jsp"><span
							class="glyphicon glyphicon-upload"></span> <b> UploadFile </b></a></li>
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
							class="glyphicon glyphicon-tasks"></span> <b> Tickets </b><b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="add.htm">Add Tickets</a></li>
							<li><a href="showdelete.htm">Delete Tickets</a></li>
							<li><a href="closedtickets.htm">MyClosed Tickets</a></li>
							<li><a href="showtoassign.htm">Assign Tickets</a></li>
							<li><a href="showtoreassign.htm">Re-assign Tickets</a></li>
							<li><a href="release.htm">Release Tickets</a></li>
						</ul></li>
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
							class="glyphicon glyphicon-user"></span> <b> Resource </b><b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="addresource.jsp">Add Resource</a></li>
							<li><a href="resname.htm">Modify Resource</a></li>
						</ul></li>
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
							class="glyphicon glyphicon-folder-open"></span> <b> Report </b><b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="dailystatus.htm">Daily Status Report</a></li>
							<li><a href="reportsummary.htm">Report Summary</a></li>
							<li><a href="resource.htm">Ticket Status</a></li>
							<li><a href="dayticket.htm">Ticket Of The Day</a></li>
							<li><a href="dayresource.htm">Ticket History Status</a></li>
							<li><a href="clarificationreport.htm">Clarification</a></li>
							<li><a href="notreportedemployee.htm">Not Reported
									Employee</a></li>
							<li><a href="nochangeinstatus.htm">No Change In Status</a></li>
						</ul></li>
				</ul>
			 	<ul class="nav navbar-nav navbar-right">
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						style="text-transform: capitalize"><span
							style="color: #E2DCBF"><b> Welcome <%=name%>
							</b></span><b class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><a href="changepasswordadmin.jsp"><span
									class="glyphicon glyphicon-edit"></span> ChangePassword</a></li>
							<li><a href="logout.htm"><span
									class="glyphicon glyphicon-log-out"></span> Logout</a></li>

						</ul></li>
				</ul> 

			</div>
		</div>
	</nav>
</div>