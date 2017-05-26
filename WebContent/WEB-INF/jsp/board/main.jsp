<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="<%=application.getContextPath() %>"/>
<!doctype html>
<html lang="en">
<head><jsp:include page="header.jsp"/></head>
<body>
<div class="container-fluid" style="width:90%">
	<div style="width:90%;margin:20px auto;">
		<select class="form-control"  style="width:20%;float:left;margin-right:36px">
			<option value="writer">작성자</option>
			<option value="title">제목</option>
		</select>
		<div class="input-group" style="width:60%;float:left;margin-right:30px">
		    <span class="input-group-addon">SEARCH</span>
		    <input id="msg" type="text" class="form-control" style="width:100%" name="msg" placeholder="Additional Info" >
		</div>
		<button type="button" class="btn btn-primary" style="width:100px">SUBMIT</button>
		<div style="margin:20px 0" >
			<span> 총게시글수61</span>
			<a href="${context}/board.do?action=write">
			<button class="btn btn-danger" style="float:right;width:100px">글쓰기</button>
			</a>
		</div>
	</div>
	<%-- <a href="${context}/board.do?action=move&pageName=write"> --%>
	 <table class="table table-hover" style="width:90%;margin:0 auto;">
		<tr class="hanbit-table tr">
			<td >NO</td>
				<td>제 목</td>
				<td >내 용</td>
			<td>작성자</td>
				<td>등록일</td>
				<td>조회수</td>
		</tr>
		<tr>
			<td>1</td>
			<td>공지사항</td>
			<td><a href="${context}/board.do?action=datail">게시판 업데이트 합니다.</a></td>
			<td>홍길동</td>
			<td>2017-05-26</td>
			<td>15</td>
		</tr>
	</table>
	<nav style="width:30%;margin:0 auto">
		<ul class="pagination">
		<li>
			<a href="${context}/board.do?action=list&pageName=main&pageNumber=${requestScope.prevBlock}">◀PREV</a>
		</li>
			<li>
					<a href="#"><font style="color:red">1</font></a>
					<a href="${context}/board.do?action=list&pageName=main&pageNumber=1">2 3 4 5</a>
			</li>
		<li>
			<a href="${context}/board.do?action=list&pageName=main&pageNumber=5">NEXT▶</a>
		</li>

		</ul>
	</nav> 
</div>
</body>
<script>
function moveTo(x){
	location.href='${context}/board.do?action=write;
}
</script>
</html>