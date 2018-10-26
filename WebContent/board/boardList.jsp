<%@page import="com.iu.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.iu.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	String board = (String)request.getAttribute("board");
	List<BoardDTO> ar = (List<BoardDTO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../temp/bootStrap.jsp" %>
</head>
<body>
<jsp:include page="../temp/header.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
		<h1><%=board %></h1>
	</div>
	<div class="row">
		<table class="table table-hover">
			<tr>
				<td>NUM</td>
				<td>TITLE</td>
				<td>WRITER</td>
				<td>DATE</td>
				<td>HIT</td>
			</tr>
			<% for(BoardDTO boardDTO: ar){ //ar에서 꺼낸 boardDTO%>
			<tr>
				<td><%=boardDTO.getNum()%></td>
				<td><%=boardDTO.getTitle() %></td>
				<td><%=boardDTO.getWriter() %></td>
				<td><%=boardDTO.getReg_date() %></td>
				<td><%=boardDTO.getHit()%></td>
			</tr>
			<%} %>
		</table>
	</div>
</div>
<jsp:include page="../temp/footer.jsp"></jsp:include>
</body>
</html>