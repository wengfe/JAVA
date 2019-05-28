package com.spring.smm.service;

import com.spring.smm.entity.Userinfo;

import java.util.ArrayList;

public interface UserinfoService {
    int insert(Userinfo record);

    ArrayList<Userinfo> selectSelective(Userinfo record);

}
