package com.fable.kscc.bussiness.service.fbmenu;

import com.fable.kscc.api.model.menu.FbsMenu;
import com.fable.kscc.api.model.page.ServiceResponse;

import java.util.List;
import java.util.Map;

public interface FbsMenuService {
    /**
     * 根据kscc管理员查看菜单(权限)
     * @return
     */
    List<FbsMenu> findAllByRole(String userId);

    /**
     * 更改kscc管理员的菜单
     * @param params
     * @return
     */
    ServiceResponse updateMenuByRole(Map<String,Object> params);

    ServiceResponse findMenuAllByRole(Map<String,Object> params);

    /**
     * 根据不同的用户显示不同的菜单项
     * @param params
     * @return
     */
    ServiceResponse findMenuAllByUserId(Map<String,Object> params);
}
