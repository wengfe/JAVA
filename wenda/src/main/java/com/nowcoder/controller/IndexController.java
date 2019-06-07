package com.nowcoder.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nowcoder.service.WendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private WendaService wendaService;

    @RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
    @ResponseBody
    public String Index(HttpSession session){
        logger.info("index method.");
        return "Hello Now"+session.getAttribute("msg")+wendaService.getMessage(1);
    }

    @RequestMapping(path = {"profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId, @PathVariable("groupId") String groupId,
                          @RequestParam(value="type", defaultValue = "1") int type,
                          @RequestParam(value = "key", required = false) String key){
//        return "Profile page of "+userId ;
        return String.format("Profile page of %d from %s, t = %d; k = %s",userId,groupId,type,key);
    }

//    返回模板
    @RequestMapping(path = {"/home"}, method = {RequestMethod.GET})
    public String template(Model model){
        model.addAttribute("value1","name"); //简单变量
//        List<String> colors = Arrays.asList(new String[]{"RED","GREEN","BLUE"});
//        model.addAttribute("colors", colors);

        return "home";
    }

    @RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletResponse response,
                          HttpServletRequest request,
                          HttpSession session,
                          @CookieValue("JSESSIONID") String sessionId){
        StringBuilder sb = new StringBuilder();
//        注解获取 Cookie
        sb.append("CookieValue" + sessionId + "<br>");

//        请求头获取
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }

//        获取 cookie
        if (request.getCookies() != null){
            for (Cookie cookie : request.getCookies()){
                sb.append("Cookie : " + cookie.getName() + "value: " + cookie.getValue() + "<br>");
//                sb.append("Cookie domain : " + cookie.getDomain()+ "<br>");
//                sb.append("Cookie Comment : " + cookie.getComment()+ "<br>");
//                sb.append("Cookie Path : " + cookie.getPath()+ "<br>");
//                sb.append("Cookie MaxAge : " + cookie.getMaxAge()+ "<br>");
//                sb.append("Cookie secure : " + cookie.getSecure()+ "<br>");
//                sb.append("Cookie version : " + cookie.getVersion()+ "<br>");
//                sb.append("Cookie class : " + cookie.getClass()+ "<br>");
            }
        }
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getRequestURL() + "<br>");

        response.addHeader("nowcoder","Hello");
        response.addCookie(new Cookie("usename","nowcoder"));
        return sb.toString();
    }

//    302临时跳转
//    @RequestMapping(path = {"/redirect/{code}"},method = {RequestMethod.GET})
//    public String redirect302(@PathVariable("code") int code, HttpSession session){
//        session.setAttribute("msg"," jump from 302 redirect");
//        return "redirect:/";
//    }
//    301强制跳转
    @RequestMapping(path = {"/redirect/{code}"},method = {RequestMethod.GET})
    public RedirectView redirect301(@PathVariable("code") int code, HttpSession session){
        session.setAttribute("msg"," jump from 301 redirect");
        RedirectView red = new RedirectView("/",true);
        if (code == 301){
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red ;
    }

//    异常捕获
    @ExceptionHandler()
    @ResponseBody
    public String error(Exception e){
        return  "error" + e.getMessage();
    }

//    异常抛出
    @RequestMapping(path = {"/admin"},method = {RequestMethod.GET})
    @ResponseBody
    public String admin(@PathParam("key") String key){
        if ("admin".equals(key))
            return "Hello admin";
        throw new IllegalArgumentException("参数错误");
    }
}
