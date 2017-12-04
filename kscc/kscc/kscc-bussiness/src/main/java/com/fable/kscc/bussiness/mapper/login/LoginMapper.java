package com.fable.kscc.bussiness.mapper.login;

import com.fable.kscc.api.model.user.FbsUser;
import org.springframework.stereotype.Repository;

/**
 * Created by Wanghairui on 2017/8/1.
 */
@Repository
public interface LoginMapper {
    FbsUser login(FbsUser user);
}
