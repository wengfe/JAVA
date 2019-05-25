package com.spring.smm.controllser;

import com.spring.smm.entity.UserInfo;
import com.spring.smm.service.IUserInfoService;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoController implements Controller {
    private IUserInfoService service;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String uname = request.getParameter("name");
        Integer uage = Integer.valueOf(request.getParameter("age"));

        UserInfo info = new UserInfo();
        info.setAge(uage);
        info.setName(uname);

        service.add(info);
        return  new ModelAndView("/welcome.jsp");
    }

    public IUserInfoService getService(){
        return service;
    }
    public void setService(IUserInfoService service){
        this.service = service;
    }
}
