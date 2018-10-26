<%@page import="com.iu.page.pager"%>
<%@page import="java.util.List"%>
<%@page import="com.iu.page.MakePager"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@page import="com.iu.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	int curPage=1; //exeption 발생할수 있으니까
	try{
		curPage = Integer.parseInt(request.getParameter("curPage"));
	}catch(Exception e){
		
	}
	
	String kind = request.getParameter("kind");
	if(kind == null || kind.equals("")){
		kind = "title";
	}
	
	String search = request.getParameter("search");
	if(search == null){
		search = "";
	}
	
	BoardDAO boardDAO = new NoticeDAO();
	MakePager mk = new MakePager(curPage, search, kind);
	List<BoardDTO> ar = boardDAO.selectList(mk.makeRow());
	int totalCount = boardDAO.getCount(kind, search);/////////////////////////////////////////////////////////
	response.setCharacterEncoding("UTF-8");
	
	String board = (String)request.getAttribute("board");
	i(List<boar>)
	
	//page
	Pager pager = mk.makePage(totalCount);
	
	request.setAttribute("list", ar); 
	request.setAttribute("board", "notice");
	
	RequestDispatcher view = request.getRequestDispatcher("../board/boardList.jsp");
	view.forward(request, response);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>