import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterTest implements Filter {
    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        System.out.println("filter doFilter method");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (session.getAttribute("userName") == null ){
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("../index.html");
        } else {
            chain.doFilter(req,resp);
        }
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        System.out.println("filter init method");
        String value = config.getInitParameter("filterParam");
        System.out.println("filter config param: " + value);
    }

}
