<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		var result = '<c:out value="${result }"/>';

		if (parseInt(result) > 0) {
			window.alert("게시글 " + result + " 번이 등록되었습니다.");
		}else if (result){
			
			alert("처리가 완료되었습니다.")
		}
		
		
		history.replaceState({}, null, null);
		
		
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
		
		var actionForm=$("#actionForm");
		$("button a").on("click", function(e){
			e.preventDefault();
			//alert("click a tag");
			
			console.log("click a tag");
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			
			actionForm.submit();
		});
		
		$(".move").on("click", function(e){
			e.preventDefault();
			//alert("click title");
			
			actionForm.append("<input type='hidden' name='bno' value='"+
					$(this).attr("href")+"'>");
			actionForm.attr("action", "/board/get");
			actionForm.submit();
			
		});

		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e){
			
			if(!searchForm.find("option:selected").val()){
				alert("검색 종류를 선택하세요");
				return false;
			}
			
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하세요");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			
			searchForm.submit();
			
		});
		
		
		
		
		
		
	});
	

</script>

${pageMaker.cri }
${pageMaker.startPage }
${pageMaker.endPage }

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id="regBtn" type="button" class="btn btn-xs pull-right">Register
					New Board
				</button>
			</div>



			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}" /></td>
							<td><a id="move" class='move' href='<c:out value="${board.bno}" />' ><c:out value="${board.title}" /></a></td>
							<td><c:out value="${board.content}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}" /></td>
						</tr>
					</c:forEach>
				</table>
				
				<form id='searchForm' action="/board/list" method='get'>
				<select name='type'>
				<option value="" 
				<c:out value="${pageMaker.cri.type == null?'selected':''}" />>--</option>
				<option value="T"
				<c:out value="${pageMaker.cri.type eq 'T' ?'selected':''}" />>제목</option>
				<option value="C"
				<c:out value="${pageMaker.cri.type eq 'C' ?'selected':''}" />>내용</option>
				<option value="W"
				<c:out value="${pageMaker.cri.type eq 'W' ?'selected':''}" />>작성자</option>
				<option value="TC"
				<c:out value="${pageMaker.cri.type eq 'TC' ?'selected':''}" />>제목 or 내용</option>
				<option value="TW"
				<c:out value="${pageMaker.cri.type eq 'TW' ?'selected':''}" />>제목 or 작성자</option>
				<option value="TWC"
				<c:out value="${pageMaker.cri.type eq 'TWC' ?'selected':''}" />>제목 or 내용 or 작성자</option>
				</select>
				<input type='text' name='keyword' 
				value='<c:out value="${pageMaker.cri.keyword}"/>' />
				<input type='hidden' name='pageNum'
				value='<c:out value="${pageMaker.cri.pageNum}"/>' />
				<input type='hidden' name='amount' 
				value='<c:out value="${pageMaker.cri.amount}"/>' />
                <button>Search</button>	
				</form>
				
				

				<c:if test="${pageMaker.prev }">				
				<button><a href="${pageMaker.startPage-1 }" >Previous</a></button>
				</c:if>

				<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">				
				<button><a href="${num }" >${num }</a></button>
				</c:forEach>
				
				<c:if test="${pageMaker.next }">				
				<button><a href="${pageMaker.endPage + 1 }" >Next</a></button>
				</c:if>
				
				<form id='actionForm' action="/board/list" method='get'>
					<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'>
					<input type='hidden' name='amount' value='${pageMaker.cri.amount }'>
					<input type='hidden' name='type' value='<c:out value="${pageMaker.cri.type}"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'>
					<!-- input type='hidden' name='bno' value='${board.bno }'> -->
				</form>
				
				
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /.panel -->
</div>
<!-- /.col-lg-6 -->
</div>
<!-- /.row -->

<%@include file="../includes/footer.jsp"%>
