package com.muke.employee.action;

import com.muke.employee.domain.Department;
import com.muke.employee.domain.PageBean;
import com.muke.employee.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends ActionSupport implements
		ModelDriven<Department> {
	// 模型驱动使用的对象
	private Department department = new Department();

	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}

	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	// 注入部门管理的Service
	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// 提供一个查询的方法
	public String findAll() {
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	// 跳转到添加部门的页面方法
	public String saveUI() {
		return "saveUI";
	}

	// 添加部门的方法
	public String save() {
		departmentService.save(department);
		return "saveSuccess";
	}

	// 编辑部门的执行方法
	public String edit() {
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}

	// 修改部门的执行方法
	public String update() {
		departmentService.update(department);
		return "updateSuccess";
	}

	// 删除部门的执行方法
	// 要实现删除部门，然后直接删除员工这样级联操作，需要先查询，然后再改配置文件
	public String delete() {// 传入ID，封装对象，传入对象，级联删除
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";

	}
}
