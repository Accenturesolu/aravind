<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String empid = (String) session.getAttribute("emp_ID");
	String pass = (String) session.getAttribute("password");
	String name = (String) session.getAttribute("name");
	HttpSession sess = request.getSession();
	sess.setAttribute("empid", empid);
	sess.setAttribute("pass1", pass);
	sess.setAttribute("name", name);
	System.out.println("empid:" + empid);
	System.out.println("name:" + name);
	System.out.println("password:" + pass);
%>
<div class="container">
	<div class="style" align="center">
		<h2>
			<b> Status Tracking System </b>
		</h2>
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
			<c:set var="report" scope="session" value="${report}" />
			<c:choose>
				<c:when test="${report == true}">
					<div class="collapse navbar-collapse" id="navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a
								href="searchResource.htm?emp_id=<%=empid%>&password=<%=pass%>"><span
									class="glyphicon glyphicon-home"></span> <b> Home </b></a></li>
							<li><a href="add.htm"><span
									class="glyphicon glyphicon-saved"></span> <b> Add Tickets </b></a></li>
							<li><a href="closedtickets.htm"><span
									class="glyphicon glyphicon-remove"></span> <b> MyClosed
										Tickets </b></a></li>
							<li><a href="dailystatus.htm"><span
									class="glyphicon glyphicon-folder-open"></span> <b> Daily
										Status Report </b></a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" style="text-transform: capitalize"><span
									style="color: #E2DCBF"><b> Welcome <%=name%>
									</b></span><b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="changepassword.jsp"><span
											class="glyphicon glyphicon-edit"></span> ChangePassword</a></li>
									<li><a href="logout.htm"><span
											class="glyphicon glyphicon-log-out"></span> Logout</a></li>

								</ul></li>
						</ul>
					</div>

				</c:when>
				<c:otherwise>
					<div class="collapse navbar-collapse" id="navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a
								href="searchResource.htm?emp_id=<%=empid%>&password=<%=pass%>"><span
									class="glyphicon glyphicon-home"></span> <b> Home </b></a></li>
							<li><a href="add.htm"><span
									class="glyphicon glyphicon-saved"></span> <b> Add Tickets </b></a></li>
							<li><a href="closedtickets.htm"><span
									class="glyphicon glyphicon-remove"></span> <b> MyClosed
										Tickets </b></a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" style="text-transform: capitalize"><span
									style="color: #E2DCBF"><b> Welcome <%=name%>
									</b></span><b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="changepassword.jsp"><span
											class="glyphicon glyphicon-edit"></span> ChangePassword</a></li>
									<li><a href="logout.htm"><span
											class="glyphicon glyphicon-log-out"></span> Logout</a></li>

								</ul></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
</div>



