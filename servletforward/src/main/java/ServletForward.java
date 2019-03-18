import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletForward extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init method");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        从请求对象获取转发对象

        RequestDispatcher rd = request.getRequestDispatcher("/servletFE");//创建转发对象，设置转发路径
//        通过servletContext 获取转发对象; 有两种方式获取转发对象
//        rd = this.getServletContext().getNamedDispatcher("ServletForwardEX");
//        rd = this.getServletContext().getRequestDispatcher("/servletFE");
        rd.forward(request,response);     // 转发
    }
}
