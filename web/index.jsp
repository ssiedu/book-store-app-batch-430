<html>
    <body>
        <h3>Xyz Book Stores</h3>
        <hr>
        <form action="AuthenticationServlet" method="get">
            <pre>
            Email       <input type="text" name="email"/>
            Password    <input type="password" name="password"/>
            Usertype    <select name="utype"><option>buyer</option><option>admin</option></select>
                        <input type="submit" value="Login"/>
            </pre>
        </form>
        <hr>
        <a href="registration.jsp">New-User</a>
    </body>
</html>
