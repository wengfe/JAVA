import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class SynchronizedServlet extends javax.servlet.http.HttpServlet {

    String name; // 为引入线程不安全问题引入实例变量，不推荐使用实例变量
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //使用 synchronized 标识进行代码加锁
        synchronized (this) {
            name = request.getParameter("username");//从 url 中获取 username 的值
            PrintWriter printWriter = response.getWriter();  // 创建 PrintWriter 对象，在页面中打印
            try {
                //为模拟大量并发 设置线程休眠时间。
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printWriter.print("username: " + name);
        }
    }
}
