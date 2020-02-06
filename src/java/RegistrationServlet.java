
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

    private Connection con;
    private PreparedStatement ps;
    
    public void init() {
        //driver-loading & connection establishment
        String sql = "insert into users values(?,?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/booksdata";
            con = DriverManager.getConnection(url, "root", "root");
            ps = con.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        //connection close
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //reads-the-request
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String uname = request.getParameter("uname");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        //process-the-request (store them to DB)
        try {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, uname);
            ps.setString(4, address);
            ps.setString(5, mobile);
            ps.executeUpdate();

        } catch (Exception e) {
            out.println(e);
        } 
        //provides-the-response
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Registration Completed</h3>");
        out.println("<h4><a href=index.jsp>Login-Now</a></h4>");
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
