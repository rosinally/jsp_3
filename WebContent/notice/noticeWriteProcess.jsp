<%@page import="com.iu.file.FileDAO"%>
<%@page import="com.iu.file.FileDTO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@page import="com.iu.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//										getRealPath("upload") :upload라는 폴더까지의 실제경로
	String path = request.getServletContext().getRealPath("upload");
	System.out.println(path);
	int max = 1024*1024*10;
	
	MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
	//path 경로에 파일 업로드 끝
	NoticeDTO noticeDTO = new NoticeDTO();
	noticeDTO.setTitle(multi.getParameter("title"));
	noticeDTO.setWriter(multi.getParameter("writer"));
	noticeDTO.setContents(multi.getParameter("contents"));
	
	//파일의 정보								를 가지고 오려면 파라미터의 이름을 알고있어야함
	FileDTO f1 = new FileDTO();	//(" 파일의 파라미터의 이름 ")
	f1.setFname(multi.getFilesystemName("f1"));//Penguins2.jpg
	f1.setOname(multi.getOriginalFileName("f1"));//penguins.jpg 올렸을 때의 이름 파라미터명
	FileDTO f2 = new FileDTO();
	f2.setFname(multi.getFilesystemName("f2"));
	f2.setOname(multi.getOriginalFileName("f2"));
	
/* 	File f = multi.getFile("f1"); //내가 올릴때의 파라미터이름
	Enumeration e = multi.getFileNames();// 파라미터 이름들 */
	
	NoticeDAO noticeDAO = new NoticeDAO();
	int num = noticeDAO.getNum();
	noticeDTO.setNum(num);
	int result = noticeDAO.insert(noticeDTO);
	
	f1.setNum(num);
	f2.setNum(num);
	f1.setKind("N");
	f2.setKind("N");
	
	FileDAO fileDAO = new FileDAO();
	fileDAO.insert(f1);
	fileDAO.insert(f2); //ㅁㅅㄷㅎㅊ
	
	String s = "Write Fail";
	if(result>0){
		s = "Write Success";
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert('<%=s%>');
	location.href="./noticeList.jsp";
</script>
</head>
<body>

</body>
</html>