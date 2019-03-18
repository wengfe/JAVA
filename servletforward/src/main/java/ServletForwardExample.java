import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletForwardExample extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init servletForwardExample method");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String user = request.getParameter("user");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head><title>转发示例</title></head>");
        writer.println("<body>");
        writer.println("<p>用户名：" + user + "</p>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }
}
