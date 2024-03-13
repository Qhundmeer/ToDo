<%@page import="dao.TodoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dto.TodoTask"%>
<%@ page import="dto.TodoUser"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
* {
	margin: 0px;
	padding: 0px;
}
td{
	padding:10px;
}
td button{
	padding:5px 20px;
}
</style>
</head>
<body>
	<%
	List<TodoTask> list = new ArrayList();
	%>
	<%
	if (session.getAttribute("user") != null) {
		TodoUser user = (TodoUser) session.getAttribute("user");
		list = new TodoDao().fetchTaskByUser(user.getId());
	}
	%>

	<%
	if (!list.isEmpty()) {
	%>
	<table border='1' cellspacing='0' style="text-align:center;">
		<tr>
			<td>Task Name</td>
			<td>Task Description</td>
			<td>Creation Time</td>
			<td>Status</td>
			<td>Delete</td>
			<td>Edit</td>
		</tr>
		<%
		for (TodoTask task : list) {
		%>

		<tr>
			<td><%=task.getTaskName()%></td>
			<td><%=task.getTaskDescription()%></td>
			<td><%=task.getDate()%></td>

			<td>
				<%
				if (task.isStatus()) {
				%> completed <%
				} else {
				%> <a
				href="complete?id=<%=task.getId()%>"><button>Complete</button></a> <%
 }
 %>
			</td>


			<td>
				<a href="deleteTask?id=<%= task.getId()%>"><button>Delete</button></a>
			</td>
			<td>
			<% if(!task.isStatus()){ %>
				<a href="edit.jsp?id=<%= task.getId()%>"><button>Edit</button></a>
				<%}else{%>
				<button disabled="disabled">Edit</button>
				<%} %>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	}
	%>

	<a href="add-task.html"><button>add task</button></a>
	<a href="logout"><button>log out</button></a>
	<script type="text/javascript">
		<% if(session.getAttribute("msg")!=null){ %>
		alert("<%= session.getAttribute("msg") %>");
		<% session.setAttribute("msg", null); }%>
	</script>
</body>
</html>