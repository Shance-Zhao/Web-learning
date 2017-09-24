<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<%
		String name = request.getParameter("val");
		if (name == null || name.trim().equals("")) {
			out.print("");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String user = "root";
				String password = "12345";
				String url = "jdbc:mysql://localhost:3306/final";
				Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con
						.prepareStatement("select * from product_table where name like '" + name + "%'");
				ResultSet rs = ps.executeQuery();

				if (!rs.isBeforeFirst()) {
					out.println("<p>No Record Found!</p>");
				} else {
					out.print("<table width='100%'>");
					out.print("<tr><th>Name</th><th>Picture</th><th>Sale</th><th>Description</th></tr>");
					while(rs.next()){  
						out.print("<tr><td>"+rs.getString(5)+"</td><td><img src='"+rs.getString(4)+"' width='150' height='170'/></td><td>"+rs.getString(8)+"</td><td>"+rs.getString(3)+"</td></tr>");  
						}  
					out.print("</table>");
				} //end of else for rs.isBeforeFirst  
				con.close();
			} catch (Exception e) {
				out.print(e);
			}
		} //end of else
	%>
</body>
</html>