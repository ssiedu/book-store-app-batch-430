<jsp:useBean id="bookdata" class="mypkg.Book" scope="session"/>
<html>
    <body>
        <h3>Book-Details</h3>
        <hr>
        <pre>
            Code    <jsp:getProperty name="bookdata" property="code"/>
            Title   <jsp:getProperty name="bookdata" property="title"/>
            Subject <jsp:getProperty name="bookdata" property="subject"/>
            Author  <jsp:getProperty name="bookdata" property="author"/>
            Price   <jsp:getProperty name="bookdata" property="price"/>
        </pre>
        <hr>
        <a href="search.jsp">Search-Again</a><br>
        <a href="buyerpage.jsp">Buyer-Page</a><br>
    </body>
</html>
