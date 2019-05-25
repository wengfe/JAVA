package com.spring.smm.controllser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class messageController {
    @RequestMapping("message/go")
    public String golest(){
        return "reach";
    }
}
