/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.82
 * Generated at: 2017-12-03 11:47:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;

public final class encoding_005finsert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      shortUrl.Base62 base62 = null;
      base62 = (shortUrl.Base62) _jspx_page_context.getAttribute("base62", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (base62 == null){
        base62 = new shortUrl.Base62();
        _jspx_page_context.setAttribute("base62", base62, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");

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
   

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}