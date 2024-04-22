<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import = "java.util.*" %>
<%
	ArrayList<HashMap<String, String>> jobByList = DeptDAO.selectJobCaseList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>CASE식을 이용한 정렬</h1>
		<%
			for(HashMap<String, String> m: jobByList) {
		%>
				<option value='<%=m.get("job")%>'>
					<%=m.get("job")%>
					<%=m.get("color")%>
				</option>
		<%	
			}
		%>
</body>
</html>