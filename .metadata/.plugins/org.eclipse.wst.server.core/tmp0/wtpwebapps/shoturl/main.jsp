<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>

<jsp:useBean id="base62" class="shortUrl.Base62" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>

</head>
<body>

<h1 style="color:blue"> hiyeon short url</h1>
<%
	
	String short_url = (String)request.getParameter("encoding_url");
	//when redirect , get url as parameter
%>
<br>

<!-- print url in text box -->

<form name=frm1 action="encoding_insert.jsp">
<%if(base62.origin_url == null){ %>
<input type="text" name="origin_url" hint="url을 입력하세요">
<%}else{%>
<input type="text" name="origin_url" value="<%=base62.origin_url%>" maxlength="1000">
<%} %>
<input type="submit" value="short_url">
</form>

<%if(short_url == null){ %>
<input type="text" name="member_name" value="">
<%}else{%>
<input type="text" name="member_name" value="http://localhost/<%=short_url%>">
<%} %>
</body>
</html>