import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveChanges extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //?code=1&title=Statistics&subject=Maths&author=Sanjeev&price=650
        String code=request.getParameter("code");
        String title=request.getParameter("title");
        String subject=request.getParameter("subject");
        String author=request.getParameter("author");
        String price=request.getParameter("price");
        
        String sql="UPDATE books SET title=?, subject=?, author=?, price=? where bcode=?";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/booksdata";
            Connection con=DriverManager.getConnection(url, "root", "root");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, subject);
            ps.setString(3, author);
            ps.setInt(4, Integer.parseInt(price));
            ps.setInt(5, Integer.parseInt(code));
            ps.executeUpdate();
            con.close();
            response.sendRedirect("BookListServlet");
        }catch(Exception e){
            e.printStackTrace();
        }

        
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
