<%
  //feching all cookies
  Cookie cookies[]=request.getCookies();
  //search for email, password
  String v1="",v2="";
  for(Cookie cookie:cookies){
      String name=cookie.getName();
      if(name.equals("email")){
          v1=cookie.getValue();
      }else if(name.equals("password")){
          v2=cookie.getValue();
      }
  }
%>

<html>
    <body>
        <h3>Xyz Book Stores</h3>
        <hr>
        <form action="AuthenticationServlet" method="get">
            <pre>
            Email       <input type="text" name="email" value="<%=v1%>"/>
            Password    <input type="password" name="password" value="<%=v2%>"/>
            Usertype    <select name="utype"><option>buyer</option><option>admin</option></select>
            SavePwd     <input type="checkbox" name="save" value="yes" checked="checked"/>
                        <input type="submit" value="Login"/>
            </pre>
        </form>
        <hr>
        <a href="registration.jsp">New-User</a>
    </body>
</html>
