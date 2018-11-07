<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootStrap.jsp"></c:import>
</head>
<body>
<c:import url="../../../temp/header.jsp" />
	<div class="container-fluid">
		<div class="row">
			<h3>${message}</h3>
		</div>
		<div class="row">
			<form action="./memberLogin.do" method="post">
		    <div class="form-group">
		      <label for="title">ID:</label>
		      <input type="text" class="form-control" id="title" placeholder="Enter Title" name="id">
		    </div>
		    <div class="form-group">
		      <label for="writer">PW:</label>
		      <input type="password" class="form-control" id="writer" placeholder="Enter Writer" name="pw">
		    </div>
		    
		    <div class="form-group">
		      <label for="title">Kind:</label>
		      <input type="text" class="form-control" id="title" placeholder="Enter Title" name="kind">
		    </div>
		    
		    
		    <button type="submit" class="btn btn-default">Write</button>
		  </form>
	
		
		
		
		</div>
	</div>


<c:import url="../../../temp/footer.jsp" />
</body>
</html>