<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>


<script type="text/javascript">

$(document).ready(function(){
	
	var formObj = $("form");
	
	$('button').on("click", function(e){
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(this);
		console.log(operation)
		
		if(operation === 'remove'){
			formObj.attr("action", "/board/remove");
		}else if(operation === 'list'){
			
			// move to list
			formObj.attr("action", "/board/list").attr("method", "get");
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			var typeTag = $("input[name='type']").clone();
			//self.location = "/board/list";
			//return;
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
		}
		formObj.submit();
		
	});
	
});


</script>

</head>
<body>
${board }

<form role="form" action="/board/modify" method="post">
	<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
	<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
	<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
    <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>

	<div><label>Bno</label><input name='bno' value='<c:out value="${board.bno}"/>' readonly="readonly"></div>
	<div><label>Title</label><input name='title' value='<c:out value="${board.title}"/>'></div>
	<div><label>Context</label><input name='content' value='<c:out value="${board.content}"/>'></div>
	<div><label>Writer</label><input name='writer' value='<c:out value="${board.writer}"/>' readonly="readonly"></div>
	
	<div><label>RegDate</label><input name='regDate' value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate}"/>' readonly="readonly"></div>
	<div><label>Update Date</label><input name='updateDate' value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}"/>' readonly="readonly"></div>
	
	<button type="submit" data-oper='modify'>Modify</button>
	<button type="submit" data-oper='remove'>Remove</button>
	<button type="submit" data-oper='list'>List</button>

</form>

</body>
</html>