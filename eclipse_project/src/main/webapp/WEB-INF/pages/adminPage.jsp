<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" import="java.util.List"%>

<!-- This page contains the admin frontend for adding and removing users. -->

<html>
<head>
<title><%=request.getAttribute("title")%></title>
</head>
<body>
	<jsp:include page="_menu.jsp" />

	<h2>Admin Page</h2>

	<h3>
		Welcome
		<%=request.getUserPrincipal().getName()%>!
	</h3>

	<c:if test='<%=response.containsHeader("failureReason")%>'>
		<div style="color: red; margin: 0px 10px">
			<%=response.getHeader("lastOp") %> failed! Reason:
			<%=response.getHeader("failureReason")%></div>
			<br>
	</c:if>

	<table style="border: 1px solid">
		<thead>
			<tr>
				<th>User</th>
				<th>Roles</th>
			</tr>
		</thead>
		<%
						List<String> userList = (List<String>) request.getAttribute("userlist");
						List<String> roleList = (List<String>) request.getAttribute("rolelist");
						for (int i = 0; i < userList.size(); ++i) {
					%>
		<tr>
			<td><%=userList.get(i) %></td>
			<td><%=roleList.get(i) %></td>

			<%}%>
		
	</table>
	<br>
	<b>Add User:</b>
	<form name='add' action="<%=request.getContextPath()%>/add_user"
		method='POST'>
		<table>
			<tr>
				<td>Username:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' value=''></td>
			</tr>
			<tr>
				<td><input type='submit' name='submit' value='Add User'></td>
			</tr>
		</table>
	</form>
	
	<b>Add Role to User:</b>
	<form name='modify'
		action="<%=request.getContextPath()%>/add_role_to_user" method='POST'>
		<table>
			<tr>
				<td>Username:</td>
				<td colspan="4"><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>New Role:</td>
				<td><input type='radio' name='role' value='super' checked>Super</td>
				<td><input type='radio' name='role' value='finance'>Finance</td>
				<td><input type='radio' name='role' value='sales'>Sales</td>
				<td><input type='radio' name='role' value='hr'>Human
					Resources</td>
				<td><input type='radio' name='role' value='engi'>Engineering</td>
			</tr>
			<tr>
				<td><input type='submit' name='submit' value='Add Role'></td>
			</tr>
		</table>
	</form>
	
	<b>Delete User:</b>
	<form name='delete' action="<%=request.getContextPath()%>/delete_user"
		method='POST'>
		<table>
			<tr>
				<td>Username:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td><input type='submit' name='submit' value='Delete User'></td>
			</tr>
		</table>
	</form>
<br>
Help Desk

</body>
</html>