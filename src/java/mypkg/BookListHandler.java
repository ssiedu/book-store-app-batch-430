/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author SSILABIST
 */
public class BookListHandler extends SimpleTagSupport {

   
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        String sql="SELECT * FROM books";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/booksdata";
            Connection con = DriverManager.getConnection(url, "root", "root");
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int n=rsmd.getColumnCount();
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Book-List-Using-Our-Own-Tag</h3>");
            out.println("<hr>");
            out.println("<table border=2>");
            out.println("<tr>");
            for(int i=1;i<=n;i++){
                out.println("<th>");
                out.println(rsmd.getColumnName(i).toUpperCase());
                out.println("</th>");
            }
            out.println("</tr>");
            while(rs.next()){
                out.println("<tr>");
                for(int i=1; i<=n; i++){
                    out.println("<td>");
                    out.println(rs.getString(i));
                    out.println("</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<hr>");
            out.println("<a href=adminpage.jsp>Admin-Page</a>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            e.printStackTrace();
        }
   
    }
    
}
