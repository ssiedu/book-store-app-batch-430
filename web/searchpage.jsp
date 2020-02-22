<%
    String stname=application.getInitParameter("store");
    String sql="SELECT DISTINCT author FROM books ORDER BY author";
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
    java.sql.PreparedStatement ps=con.prepareStatement(sql);
    java.sql.ResultSet rs=ps.executeQuery();
%>
<html>
    <body>
        <h3><% out.println(stname); %></h3>
        <h3>Book-Search</h3>
        <hr>
        <form action="searchbook.jsp">
            Author <select name="author">
<%
    while(rs.next()){
        String aname=rs.getString(1);
%>
<option><% out.println(aname); %></option>
<%
    }
%>
                    </select>
            <input type="submit" value="Search"/>
        </form>
        <hr>
        <a href="buyerpage.jsp">Buyer-Page</a>
    </body>
</html>
