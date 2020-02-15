
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String utype = request.getParameter("utype");

        out.println("<html><body>");
        if (utype.equals("admin")) {
            if (email.equals("admin@gmail.com") && password.equals("indore")) {
                //redirect the user to adminpage
                response.sendRedirect("adminpage.jsp");
            } else {
                out.println("<h4>Invalid Admin Account<h4>");
                out.println("<a href=index.jsp>Try-Again</a>");
            }
        } else {
            //buyer credintials will be checked from DB
            String sql = "SELECT uname FROM users WHERE email=? AND password=?";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/booksdata";
                Connection con = DriverManager.getConnection(url, "root", "root");
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                boolean found = rs.next();
                if (found) {
                    //email we are storing to session
                    HttpSession session=request.getSession();
                    session.setAttribute("user", email);
                    
                    //id/pwd are stored to cookies
                    Cookie ckId=new Cookie("email",email);
                    Cookie ckPw=new Cookie("password",password);
                    ckId.setMaxAge(60*60*24*7);
                    ckPw.setMaxAge(60*60*24*7);
                    response.addCookie(ckId);
                    response.addCookie(ckPw);
                    //user-is-valid
                    String name=rs.getString(1);
                    //we wish to write this username to
                    //client's disk using cookies.
                    //so that user name will be available
                    //in all upcoming request to any page.
                    //step-1 (creating cookie object)
                    Cookie ck=new Cookie("username",name);
                    //step-2 (set the maximum age)
                    ck.setMaxAge(60*60*24*7);
                    //step-2 (add the cookie to response)
                    response.addCookie(ck);
                    
                    
                    //redirect the user to buyer page
                    response.sendRedirect("buyerpage.jsp");
                } else {
                    out.println("<h4>Invalid Buyer Account<h4>");
                    out.println("<a href=index.jsp>Try-Again</a>");
                }
                con.close();
            } catch (Exception e) {
                out.println(e);
            }

        }
        out.println("</body></html>");

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
