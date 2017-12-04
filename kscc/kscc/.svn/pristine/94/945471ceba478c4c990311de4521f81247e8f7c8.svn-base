package com.fable.kscc.bussiness.service.loginService;

import com.fable.kscc.api.model.user.FbsUser;
import com.fable.kscc.api.utils.MD5Encrypt;
import com.fable.kscc.bussiness.mapper.login.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Wanghairui on 2017/8/1.
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public FbsUser toLogin(FbsUser user) {
        user.setPassword(MD5Encrypt.encode(user.getPassword()));
        return loginMapper.login(user);
    }
}
