package com.spring.smm.service.impl;

import com.spring.smm.dao.UserinfoMapper;
import com.spring.smm.entity.Userinfo;
import com.spring.smm.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    private UserinfoMapper mapper;
    public int insert(Userinfo record){
        return mapper.insert(record);
    }

    public ArrayList<Userinfo> selectSelective(Userinfo record){
        return mapper.selectSelective(record);
    }
}
