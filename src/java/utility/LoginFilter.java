package utility;

import admin.entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/faces/admin/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            
            User tmp = null;
            if (ses != null) {
                if (ses.getAttribute("user") != null) {
                    tmp = (User) ses.getAttribute("user");
                }
            }
            String reqURI = reqt.getRequestURI();
            if (reqURI.contains("/admin/login.xhtml")
                    || (ses != null && ses.getAttribute("user") != null &&tmp!=null && "Admin".equalsIgnoreCase(tmp.getRole().getRole_adi()))
                    || reqURI.contains("/frontend/")
                    || reqURI.contains("javax.faces.resources")) {
                chain.doFilter(request, response);
            } 
            else 
            {
                 resp.sendRedirect(reqt.getContextPath() + "/faces/admin/login.xhtml");
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
