<%@include file="info.jsp" %>


<%!
    public int highRate=20;
    public int lowRate=10;
    
    int getDiscount(int p){
        int d=0;
        if(p>=1000){
            d=p*highRate/100;
        }else{
            d=p*lowRate/100;
        }
        return d;
    }   
%>

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
                <th>Dis</th>
            </tr>
<%
    while(rs.next()){
        String code=rs.getString(1);
        String title=rs.getString(2);
        String subject=rs.getString(3);
        String author=rs.getString(4);
        int price=rs.getInt(5);
        //int discount=getDiscount(price);
%>
<tr>
    <td><%=code%></td>
    <td><%=title%></td>
    <td><%=subject%></td>
    <td><%=author%></td>
    <td><%=price%></td>
    <td><%=getDiscount(price)%></td>
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