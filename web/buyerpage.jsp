<%
   
    String id=(String)session.getAttribute("user");
    if(id==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body>
        <h3>Welcome <%=id%></h3>
        <h3>BUYER-DASHBOARD</h3>
        <hr>
        <pre>
        <a href="SubjectPageServlet">View-Books-Subject-Wise</a>
        <a href="">View-Books-Author-Wise</a>
        <a href="">View-Cart</a>
        <a href="index.jsp">Logout</a>
        </pre>
        <hr>
    </body>
</html>
