<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>

<body>
	<form action="editTask">
		<lable>task name</lable>
		<input type="text" name="taskName" required><br>
		<lable>task description</lable>
		<input type="text" name="description" required><br>
		<input type="hidden" name="id" value="<%= request.getParameter("id")%>"> 
		<button>save task</button>
	</form>
</body>

</html>