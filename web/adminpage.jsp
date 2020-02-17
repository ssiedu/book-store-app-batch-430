<%
    session.setMaxInactiveInterval(1200);
    int n=session.getMaxInactiveInterval();
%>
<html>
    <body>
        <h5>Session Timeout Period : <%=n%> Seconds</h5>
        <h3>ADMIN-DASHBOARD</h3>
        <hr>
        <pre>
        <a href="bookentry.jsp">Add-New-Book</a>
        <a href="BookListServlet">Update-Book</a>
        <a href="BookListServlet">View-All-Books</a>
        <a href="">View-Orders</a>
        <a href="index.jsp">Logout</a>
        </pre>
        <hr>
    </body>
</html>
