import Util.GeneralUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init method");
//        super.init();
        ServletConfig config = this.getServletConfig();
        String v1 = config.getInitParameter("data1");
        String v2 = config.getInitParameter("data2");
        System.out.println("v1 = " + v1 + ", v2 = " + v2);
//      获取 context-param 中的配置数据
        ServletContext context = this.getServletContext();
        String gv1 = context.getInitParameter("globalData1");
        String gv2 = context.getInitParameter("globalData2");
        System.out.println("gv1 = " + gv1 + ", gv2 = " + gv2);

        context.setAttribute("attribute1","111");

//        getResource 方法
        try {
            URL url = context.getResource("/WEB-INF/classes/log4j.properties");
            InputStream in = url.openStream();
            String propertyValue = GeneralUtil.getProperty("log4j.rootLogger",in);
            System.out.println(propertyValue);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===============================================");

//        getResourceAsStream 方法，比较常用 省去了转换的过程
        InputStream in2 = context.getResourceAsStream("/WEB-INF/classes/log4j.properties");
        String p2 = GeneralUtil.getProperty("log4j.rootLogger",in2);
        System.out.println("p2 " + p2);
        System.out.println("===============================================");

//        getRealPath 方法
        String path = context.getRealPath("/WEB-INF/classes/log4j.properties");
        System.out.println("Real path: " + path);
        File f = new File(path);
        try {
            InputStream in3 = new FileInputStream(f);
            String p3 = GeneralUtil.getProperty("log4j.rootLogger",in3);
            System.out.println("p3: " + p3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service method");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("destroy method");
//        super.destroy();
    }

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGTE method");
        PrintWriter pw = resp.getWriter();
        pw.print("hello world");
        pw.close();
//        super.doGet(req, resp);

    }


}
