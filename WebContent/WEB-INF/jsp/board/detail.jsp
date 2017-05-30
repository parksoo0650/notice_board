<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head><jsp:include page="header.jsp"/></head>
<body>
<div class="container-fluid" style="width:80%">
<jsp:include page="navbar.jsp"/>
<h1>ARTICLE DETAIL</h1>
		<input type="hidden" name="max" value="${seqNo}"/>
		<input type="text" name="writer" style="margin-top:20px"  class="form-control" placeholder="작성자 이름" value="${writer}"/>
		<input type="text" name="title" style="margin-top:20px" class="form-control"  placeholder="제목"  value="${title}"/><br />
	    <div class="row">
		    <div style="width:97%; margin:10px auto" >
	      		<textarea class="form-control" rows="20" name="comment">${content}</textarea>
		    </div>
	    </div>
	<a href="${context}/board.do?action=delete&max=${seqNo}&writer=${writer}&title=${title}&comment=${content}"><input type="submit" name="action"  class="btn btn-danger" style="float:right;width:100px;margin-right:10px" value="DELETE"/>DELETE</a>
	<a href="${context}/board.do?action=move"><input type="submit" class="btn btn-warning" style="float:right;width:100px;margin-right:10px" value="UPLOAD"/>UPLOAD</a>
	<a href="${context}/board.do?action=move&pageName=update&writer=${writer}&title=${title}&comment=${content}"><input type="submit" class="btn btn-success" style="float:right;width:100px;margin-right:10px" value="UPDATE"/>UPDATE</a>
</div>
</body>
</html>