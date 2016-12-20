package com.muke.employee.dao;

import java.util.List;

import com.muke.employee.domain.Department;

/*
 * 部门管理Dao接口
 */
public interface DepartmentDao {

	int findCount();

	List<Department> finByPage(int begin, int pageSize);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
