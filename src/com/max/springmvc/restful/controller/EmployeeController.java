package com.max.springmvc.restful.controller;

import java.util.List;
import java.util.Map;

import com.max.springmvc.restful.model.DepartmentDAO;
import com.max.springmvc.restful.model.DepartmentVO;
import com.max.springmvc.restful.model.EmployeeVO;
import com.max.springmvc.util.DepartmentMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.max.springmvc.restful.model.EmployeeDAO;

import javax.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeDAO emdao;

	@Autowired
	private DepartmentDAO dedao;

	@Autowired
	private DepartmentMapping demap;

	@ModelAttribute
	public void getEmployee(@RequestParam(value = "empId",required = false) String empId ,
							Map<String , Object> map){
		if(empId != null){
			map.put("employee",emdao.get(empId));
		}
	}

	@RequestMapping(value="/emp",method = RequestMethod.PUT)
	public String update(@ModelAttribute(value = "emoloyee") EmployeeVO emp){
		//���wemp���ȬO�qmodelAttribute��map��employee�o��key���ȡA�Q�аOmodelAttribute���ѼơA���|�qmodel�̧�o�Ӫ���A�S���N��Ҥ�
		emdao.save(emp);
		return "redirect:/emps";
	}

	@RequestMapping(value="/emp/{id}",method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") String id){
		emdao.delete(id);
		return "redirect:/emps";
	}


	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String save(@Valid EmployeeVO emp, Errors result, Map<String,Object> map) {
		String depId = emp.getDepartment().getDepId();
		emp.setDepartment(new DepartmentVO(depId,demap.departmentMapping(depId)));
		System.out.println("�s�W�����u��T�� = " + emp.toString());


		if(result.getErrorCount() > 0){
			System.out.println("�X���F!");
			for(FieldError error : result.getFieldErrors()){
				System.out.println(error.getField() + ":" +  error.getDefaultMessage());
			}
			//�Y���ҥX���A��V�ۭq����
			map.put("employee", emp);
			return "input";
		}
		emdao.save(emp);
		return "redirect:/emps";
	}

	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)	//��PathVariable��input����k�A����P�U����intput��k�@��
	public String input(@PathVariable("id") String id ,Map<String , Object> map) {
		map.put("employee",emdao.get(id));
		map.put("departments", dedao.getAll());
		return "input";
	}

	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String , Object> map) {
		map.put("departments", dedao.getAll());
		map.put("employee",new EmployeeVO());
		return "input";
	}

	@RequestMapping("/emps")
	public String list(Map<String , Object> map) {
		map.put("employee", emdao.getAll());
		return "list";
	}

	//�s�W���󪺮ɭԡA���ݩʤ��s�W�A�page�N���s�W
//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setDisallowedFields("age");
//	}
}