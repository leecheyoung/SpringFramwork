<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		
		//alert("modify");
		
		operForm.attr("action", "/board/modify").submit();
		
	});
	
	$("button[data-oper='list']").on("click", function(e){
		
		//alert("list");
		
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list").submit();
		
	});
	
	
	
	
});

</script>


<body>
${board }

<div><input name='bno' value='<c:out value="${board.bno}"/>' readonly="readonly"></div>
<div><input name='title' value='<c:out value="${board.title}"/>' readonly="readonly"></div>
<div><input name='content' value='<c:out value="${board.content}"/>' readonly="readonly"></div>
<div><input name='writer' value='<c:out value="${board.writer}"/>' readonly="readonly"></div>


<button data-oper='modify'>Modify</button>
<button data-oper='list'>List</button>



<form id='operForm' action="/board/modify" method="get">
	<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
	
	<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
	<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
	<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
	<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
</form>

</body>
</html>