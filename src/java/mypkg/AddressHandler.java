package mypkg;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AddressHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            out.println("<center>");
            out.println("<h5>Mail : admin@xyz.com</h5>");
            out.println("<h5>Phone : 0731-43043043</h5>");
            out.println("</center>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
