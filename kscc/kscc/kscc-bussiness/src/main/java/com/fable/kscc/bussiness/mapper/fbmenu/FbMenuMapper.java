package com.fable.kscc.bussiness.mapper.fbmenu;

import com.fable.kscc.api.model.menu.FbsMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface FbMenuMapper {

	/**
	 * 根据kscc管理员查看菜单(权限)
	 * @return
	 */
	List<FbsMenu> findAllByRole();

	/**
	 * 初始化kscc管理员的菜单
	 * @return
	 */
	int InitializationMenuRole();

	/**
	 * 更改kscc管理员的菜单
	 * @return
	 */
	int updateMenuByRole(@Param("params") Map<String,Object> params);

	/**
	 * 普通管理员新增菜单项
	 * @param params
	 * @return
	 */
	int inserMenuRole(@Param("params") Map<String,Object> params);

	/**
	 * 查询普通管理员已选菜单项
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> findMenuAllByRole(@Param("params") Map<String,Object> params);
}
