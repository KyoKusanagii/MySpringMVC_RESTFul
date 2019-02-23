package com.max.springmvc.converter;

import com.max.springmvc.restful.model.EmployeeDAO;
import com.max.springmvc.restful.model.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDAO emdao;

    @RequestMapping(value = "/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee") EmployeeVO employee){
        System.out.println("新增:" + employee);
        emdao.save(employee);
        return "redirect:/emps";
    }
}
