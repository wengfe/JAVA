

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class UserServlet extends javax.servlet.http.HttpServlet {
    static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        process(request,response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logger.info("UserServlet doPost method is invoked.");
        process(request,response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        RequestDispatcher dispatcher = null;
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("userName");
        session.setMaxInactiveInterval(2 * 60); // 设置 session 失效时间
        session.invalidate();//调用 session 失效接口
        if (null != name){
            System.out.println("second login: "+ name);
        }

        session.setAttribute("userName",userName);

        Cookie userNameCookie = new Cookie("userName",userName);
        Cookie pwdCookie = new Cookie("pwd",userPassword);

        userNameCookie.setMaxAge(10*60);
        pwdCookie.setMaxAge(10*60);

        response.addCookie(userNameCookie);
        response.addCookie(pwdCookie);

        Cookie[] cookies = request.getCookies();
        if (null != cookies){
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
                if(cookie.getName().equals("pwd")){
                    userPassword = cookie.getValue();
                }
            }
        }

        try {
            if (userName.equals("123") && userPassword.equals("123")){
                PrintWriter writer = response.getWriter();
                writer.println("<html>");
                writer.println("<head><title>用户中心</title><meta charset=\"UTF-8\"></head>");
                writer.println("<body>");
                writer.println("<p>用户名：" + userName + "</p>");
                writer.println("<p>用户密码：" + userPassword + "</p>");
                writer.println("</body>");
                writer.println("</html>");
                writer.close();
            } else {
                dispatcher = request.getRequestDispatcher("/error.html");
                dispatcher.forward(request,response);
            }
        } catch (Exception e){
            e.printStackTrace();
            dispatcher = request.getRequestDispatcher("/error.html");
            dispatcher.forward(request,response);
        }
    }


}
