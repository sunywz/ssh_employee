package com.muke.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.muke.employee.dao.EmployeeDao;
import com.muke.employee.domain.Employee;

/*
 * 员工管理dao实现类
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
	/*
	 * DAO中根据用户名和密码查询用户的方法 (non-Javadoc)
	 * 
	 * @see
	 * com.muke.employee.dao.EmployeeDao#findByUsernameAndPassword(com.muke.
	 * employee.domain.Employee)
	 */
	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql = "from Employee where username=? and password=?";
		List<Employee> list = this.getHibernateTemplate().find(hql,
				employee.getUsername(), employee.getPassword());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Employee";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee> list = this.getHibernateTemplate().findByCriteria(
				criteria, begin, pageSize);
		return list;
	}

	@Override
	// dao保存员工的方法
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);

	}

	@Override
	// dao根据员工ID查询员工的方法
	public Employee findById(Integer eid) {
		return this.getHibernateTemplate().get(Employee.class, eid);
	}

	@Override
	// dao修改员工的方法
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
	}

	@Override
	//dao删除员工的方法
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
	}

}
