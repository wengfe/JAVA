import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServletTwo extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext context = this.getServletContext();
        String gv1 = context.getInitParameter("globalData1");
        String gv2 = context.getInitParameter("globalData2");
        System.out.println("SimpleServletTwo: gv1 = " + gv1 + ", gv2 = " + gv2);
        String attribute = (String) context.getAttribute("attribute1");
        System.out.println(attribute);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("s2 service method");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("s2 doGET method");
        PrintWriter pw = resp.getWriter();
        pw.print("This is SimpleServletTwo's world");
        pw.close();

    }
    @Override
    public void destroy() {
//        super.destroy();
        System.out.println("s2 destroy method");
    }

}
