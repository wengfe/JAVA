package com.spring.smm.service;

import com.spring.smm.dao.IUserInfoDAO;
import com.spring.smm.entity.UserInfo;

public class UserInfoServiceImpl implements IUserInfoService {
    private IUserInfoDAO dao;

    public void add(UserInfo info){
        dao.add(info);

    }
    public  IUserInfoDAO getDao(){
        return dao;

    }
    public void  setDao(IUserInfoDAO dao){
        this.dao = dao;
    }
}
