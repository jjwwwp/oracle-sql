<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<!-- Controller -->
<%
	ArrayList<HashMap<String, Object>> list = EmpDAO.selectEmpGrade();
%>
<!-- View -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>empno</td>
			<td>ename</td>
			<td>grade</td>
			<td>mgrName</td>
			<td>mgrGrade</td>
		</tr>
		<%
			for(HashMap<String, Object> m : list){
		%>
			<tr>
				<td><%=m.get("empno")%></td>
				<td><%=m.get("ename")%></td>
				<td>
					<%
						for(int i=0; i<(Integer)(m.get("grade")); i=i+1){
					%>
						&#127775;
					<%
						}
					%>
					<td><%=m.get("mgrName")%></td>
					<td>
					<%
						for(int i=0; i<(Integer)(m.get("mgrGrade")); i=i+1){
					%>
						&#127775;
					<%
						}
					%>
				</td>		
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>