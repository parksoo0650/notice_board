<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head><jsp:include page="header.jsp"/></head>
<body>
<div class="container-fluid" style="width:80%">
<jsp:include page="navbar.jsp"/>
<h1>ARTICLE WRITING</h1>
	<form action="${context}/board.do" name="insert_item" method="get" ENCTYPE="multipart/form-data" >
		<input type="hidden" name="action" value="write"/>
		<input type="text" name="writer" style="margin-top:20px"  class="form-control" placeholder="작성자 이름" />
		<input type="text" name="title" style="margin-top:20px" class="form-control"  placeholder="제목" /><br />
	    <div class="row">
		    <div style="width:97%; margin:10px auto" >
	      		<textarea class="form-control" rows="20" name="comment"></textarea>
		    </div>
	    </div>
	    <input type="reset" class="btn btn-danger" style="float:right;width:100px;margin-right:10px" value="CANCEL"/>
	    <input type="submit" class="btn btn-primary" style="float:right;width:100px;margin-right:10px" value="SUBMIT"/>
</form>
</div>
</body>
</html>