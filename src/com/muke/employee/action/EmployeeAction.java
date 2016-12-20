package com.muke.employee.action;

import java.util.List;

import com.muke.employee.domain.Department;
import com.muke.employee.domain.Employee;
import com.muke.employee.domain.PageBean;
import com.muke.employee.service.DepartmentService;
import com.muke.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 员工管理的Action
 */
public class EmployeeAction extends ActionSupport implements
		ModelDriven<Employee> {
	private Employee employee = new Employee();
	// 接收当前页数
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	// 注入业务层的类
	private EmployeeService employeeService;
	private DepartmentService departmentService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/*
	 * 登录执行的方法
	 */
	public String login() {
		System.out.println("login");
		// 调用业务层的类
		Employee existEmployee = employeeService.login(employee);
		if (existEmployee == null) {
			// 登录失败
			this.addActionError("用户名或密码错误");
			return INPUT;
		} else {
			// 登录成功
			ActionContext.getContext().getSession()
					.put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

	// 分页查询员工的执行方法
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		// 存入ValueStack
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	// 跳转到添加员工页面的执行方法
	public String saveUI() {
		List<Department> list = departmentService.findAll();
		// 集合用set，对象用push
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}

	// 保存员工的执行方法
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}

	// 编辑员工的执行方法
	public String edit() {
		// 根据员工ID查询员工
		employee = employeeService.findById(employee.getEid());
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}

	// 修改员工的执行方法
	public String update() {
		employeeService.update(employee);
		return "updateSuccess";

	}

	// 删除员工方法
	public String delete() {
		employee=employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
