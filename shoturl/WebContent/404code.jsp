<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Driver"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!--  to redirect shorten url 
	1. shorten url 이  들어오면, 404code.jsp 로 들어온다. (web.xml)
	2. 진짜 error url 이면  404error page로 (만들어 놓은 경로 외의 경로)
	3. 아니면 database에서 잘못된 url 로 처리된 uri 가져온다
	4. database 에 있으면 origin url redirect
	5. 없으면 "요청하신 페이지 없음"-->
<%
//if real 404 error url -> real 404 error page
//if shortrn url -> go to 404code.jsp
response.setStatus(HttpServletResponse.SC_OK);

//get shorten uri-> cut '/'
String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
String sub_url = uri.substring(1);

String origin_url = null;
String url = "jdbc:mysql://localhost:3306/shorturl";

Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(url,"root","gkduswkd1004!@#");  //my db
Statement stmt = conn.createStatement();

String SQL ="select * from url where short_url='"+ sub_url +"'";

ResultSet rs = stmt.executeQuery(SQL);

 if(rs.next()){
	//if db has sub_url
	origin_url = rs.getString(2);
	response.sendRedirect(origin_url);
 }else{
	//if db has no such sub_url
	 out.print("요청하신 페이지는 존재하지 않습니다.");
 }
%>
</body>
</html>