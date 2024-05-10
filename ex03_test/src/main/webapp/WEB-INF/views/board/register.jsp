<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form role="form" action="/board/register" method="post">

<div><label>Title: </label><input name="title"></div>
<div><label>Content: </label><textarea rows="3" name="content"></textarea></div>
<div><label>Writer: </label><input name="writer"></div>


<button type="submit">Submit</button>
<button type="reset">Reset</button>
</form>

</body>
</html>