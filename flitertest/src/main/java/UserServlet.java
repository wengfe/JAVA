import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        logger.info("UserServlet post method is invoked.");
        process(request, response);
    }

    protected void process(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("userName");
        session.setMaxInactiveInterval(2 * 60);
//        session.invalidate();
        if (name != null) {
            System.out.println("second login: " + name);
            session.removeAttribute("userName");//移除 attribue 调用 HttpSessionAttributeListener 的attributeRemoved 方法
        }

        session.setAttribute("userName", userName);

        Cookie userNameCookie = new Cookie("userName", userName);
        Cookie pwdCookie = new Cookie("pwd", userPassword);

        userNameCookie.setMaxAge(10 * 60);
        pwdCookie.setMaxAge(10 * 60);

        response.addCookie(userNameCookie);
        response.addCookie(pwdCookie);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")) {
                    userName = cookie.getValue();
                }
                if (cookie.getName().equals("pwd")) {
                    userPassword = cookie.getValue();
                }
            }
        }
        response.setCharacterEncoding("utf-8");
        try {
            if (userName.equals("123") && userPassword.equals("123")) {
                PrintWriter writer = response.getWriter();
                writer.println("<html>");
                writer.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>用户中心</title></head>");
                writer.println("<body>");
                writer.println("<p>用户名：" + userName + "</p>");
                writer.println("<p>用户密码：" + userPassword + "</p>");
                writer.println("</body>");
                writer.println("</html>");
                writer.close();
            } else {
                dispatcher = request.getRequestDispatcher("/error.html");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dispatcher = request.getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
        }
    }
}
