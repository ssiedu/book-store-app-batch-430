import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //fetch the details of a books from db
        PrintWriter out=response.getWriter();
        String code=request.getParameter("code");
        try{
            String sql="SELECT * FROM books WHERE bcode=?";
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/booksdata";
            Connection con=DriverManager.getConnection(url, "root", "root");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(code));
            ResultSet rs=ps.executeQuery();
            rs.next();
            String bcode=rs.getString(1);
            String title=rs.getString(2);
            String subject=rs.getString(3);
            String author=rs.getString(4);
            String price=rs.getString(5);
            con.close();
            
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Updation-Form</h3>");
            out.println("<pre>");
            out.println("<form action=SaveChanges>");
            out.println("Code     : <input type=text name=title value="+code+">");
            out.println("Title    : <input type=text name=title value="+title+">");
            out.println("Subject  : <input type=text name=title value="+subject+">");
            out.println("Author   : <input type=text name=title value="+author+">");
            out.println("Price    : <input type=text name=title value="+price+">");
            out.println("<input type=submit value=Save>");
            out.println("</pre>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            
            
        }catch(Exception e){
            
        }
        //generate the html form with pre-populated values
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
