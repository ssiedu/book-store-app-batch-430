<%
    
    String aname=request.getParameter("author");
    //jdbc code to fetch the data
    String sql="SELECT * FROM books WHERE author=?";
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
    java.sql.PreparedStatement ps=con.prepareStatement(sql);
    ps.setString(1,aname);
    java.sql.ResultSet rs=ps.executeQuery();
%>
<html>
    <body>
        <h3>Book-List</h3>
        <hr>
        <table border="2">
            <tr>
                <th>BCode</th>
                <th>Title</th>
                <th>Subject</th>
                <th>Author</th>
                <th>Price</th>
            </tr>
<%
    while(rs.next()){
        String code=rs.getString(1);
        String title=rs.getString(2);
        String subject=rs.getString(3);
        String author=rs.getString(4);
        String price=rs.getString(5);
%>
<tr>
    <td><% out.println(code); %></td>
    <td><% out.println(title); %></td>
    <td><% out.println(subject); %></td>
    <td><% out.println(author); %></td>
    <td><% out.println(price); %></td>
</tr>
<%
    }
%>
        </table>
        <hr>
        <a href="searchpage.jsp">Search-Again</a><br>
        <a href="buyerpage.jsp">Buyer-Page</a><br>
    </body>
</html>
<%
    con.close();
%>