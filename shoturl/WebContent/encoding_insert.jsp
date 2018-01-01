<%@page import="java.sql.Connection"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Driver"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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


<%
String origin_url = request.getParameter("origin_url");

//when redirecting, long url sometimes get cut 
//declare varialbe in java class to save original url to avoid cutting
base62.origin_url = request.getParameter("origin_url");
int ascii_sum = base62.sum_ascii(origin_url);
String encoding_url = null;

try{
	//open db
	String url = "jdbc:mysql://localhost:3306/shorturl";

	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url,"root","gkduswkd1004!@#");  
	Statement stmt = conn.createStatement();
	
	while(true){
		String SQL = "select * from url where encoding="+ ascii_sum;
		ResultSet rs = stmt.executeQuery(SQL);
	
		if(rs.next()){
			//if db has same ascii -> check same origin_url or not
			//same ascii_sum means = same url || different order with same char
			origin_url = rs.getString(2);
			encoding_url=rs.getString(3);
			if(origin_url.equals(rs.getString(2))){
				break;
				//same url
			}else{
				//diff order
				ascii_sum=ascii_sum*2;
				if(ascii_sum > Math.pow(2,64)){//max 8char -> 8bytes -> 2^64 bits
					ascii_sum = (ascii_sum%10)+1;
				}
			}
		}
		else{
	      //no such ascii_sum -> insert to db 
	      encoding_url = base62.encodeToLong(ascii_sum);
		
		  String query = "INSERT INTO url  VALUES('"+ascii_sum+"','"+origin_url+"','"+encoding_url+"')";
		  stmt.executeUpdate(query);
		  stmt.close();
		  conn.close();
		  break;
		}
	 }
	 }
	 catch(Exception e){
	  out.println( e );
	 }
		
	//redirect & pass encoding_url as parameter 
	response.sendRedirect("/main.jsp?encoding_url="+encoding_url);
   
%>


</body>
</html>