import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        HashSet<String> set=(HashSet<String>) session.getAttribute("cart");
        out.println("<html>");
        out.println("<body>");
        if(set==null){
            out.println("<h5>Your Cart Is Empty </h5>");
            out.println("<h5><a href=SubjectPageServlet>Start-Buying</a></h5>");
        }else{
            String items=set.toString();
            String sql="SELECT * FROM books WHERE BCODE IN "+items;
            sql=sql.replace('[', '(');
            sql=sql.replace(']', ')');
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            out.println("<h4>Your Cart</h4>");
            out.println("<hr>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<th>Code</th><th>Title</th><th>Subject</th><th>Author</th><th>Price</th>");
            out.println("</tr>");
            int sum=0;
            while(rs.next()){
                String code=rs.getString(1);
                String title=rs.getString(2);
                String subject=rs.getString(3);
                String author=rs.getString(4);
                int price=rs.getInt(5);
                sum=sum+price;
                out.println("<tr>");
                out.println("<td>"+code+"</td>");
                out.println("<td>"+title+"</td>");
                out.println("<td>"+subject+"</td>");
                out.println("<td>"+author+"</td>");
                out.println("<td>"+price+"</td>");
                out.println("<td><a href=RemoveBookFromCart?code="+code+">X</a>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td></td><td></td><td></td>");
            out.println("<td>Total</td>");
            out.println("<td>"+sum+"</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<hr>");
            out.println("<a href=SubjectPageServlet>Add-More-Books</a><br>");
            out.println("<a href=buyerpage.jsp>BuyerPage</a><br>");
            }catch(Exception e){
                out.println(e);
            }
        }
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
