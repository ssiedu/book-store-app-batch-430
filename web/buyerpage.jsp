<%
    int n=session.getMaxInactiveInterval();
    long val=session.getCreationTime();
    java.util.Date dt=new java.util.Date(val);
    String id=(String)session.getAttribute("user");
    if(id==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body>
<%--        
        <h5>Session Timeout Period : <%=n%> Seconds</h5>
        <h3>Welcome <%=id%></h3>
        <h5>Your Session Was Created On <%=dt%></h5>
--%>
        <h3>BUYER-DASHBOARD</h3>
        <hr>
        <pre>
        <a href="SubjectPageServlet">View-Books-Subject-Wise</a>
        <a href="searchpage.jsp">View-Books-Author-Wise</a>
        <a href="CartViewServlet">View-Cart</a>
        <a href="EndSession">Logout</a>
        </pre>
        <hr>
    </body>
</html>
