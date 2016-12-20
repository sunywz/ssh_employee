package com.muke.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.muke.employee.dao.EmployeeDao;
import com.muke.employee.domain.Employee;
import com.muke.employee.domain.PageBean;
import com.muke.employee.service.EmployeeService;

/*
 * 员工管理业务层实现类
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/*
	 * 业务层的登录方法 (non-Javadoc)
	 * 
	 * @see
	 * com.muke.employee.service.EmployeeService#login(com.muke.employee.domain
	 * .Employee)
	 */
	@Override
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao
				.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	@Override
	// 业务层的分页查询员工的方法
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页所显示的记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	// 业务层保存员工的方法
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public Employee findById(Integer eid) {
		// TODO Auto-generated method stub
		return employeeDao.findById(eid);
	}

	@Override
	// 修改
	public void update(Employee employee) {
		employeeDao.update(employee);

	}

	@Override
	// 删除
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

}
