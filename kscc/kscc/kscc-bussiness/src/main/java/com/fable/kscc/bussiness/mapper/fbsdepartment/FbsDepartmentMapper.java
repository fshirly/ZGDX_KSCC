package com.fable.kscc.bussiness.mapper.fbsdepartment;

import com.fable.kscc.api.model.department.FbsDepartment;

import java.util.List;

public interface FbsDepartmentMapper {

    /**
     * 查询医院里的所有部门(科室)
	 * @return
     */
	List<FbsDepartment> findAllDepartment();

	/**
	 * 根据部门id查询相应的部门名称
	 * @param id
	 * @return
	 */
	FbsDepartment queryDepartmentById(int id);
}