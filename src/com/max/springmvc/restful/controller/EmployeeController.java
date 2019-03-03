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
		//指定emp的值是從modelAttribute的map的employee這個key的值，被標記modelAttribute的參數，都會從model裡找這個物件，沒找到就實例化
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
		System.out.println("新增的員工資訊為 = " + emp.toString());


		if(result.getErrorCount() > 0){
			System.out.println("出錯了!");
			for(FieldError error : result.getFieldErrors()){
				System.out.println(error.getField() + ":" +  error.getDefaultMessage());
			}
			//若驗證出錯，轉向自訂頁面
			map.put("employee", emp);
			return "input";
		}
		emdao.save(emp);
		return "redirect:/emps";
	}

	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)	//有PathVariable的input的方法，不能與下面的intput方法共用
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

	//新增物件的時候，該屬性不新增，如age就不新增
//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setDisallowedFields("age");
//	}
}