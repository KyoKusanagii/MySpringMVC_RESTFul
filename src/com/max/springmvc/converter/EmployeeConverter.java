package com.max.springmvc.converter;

import com.max.springmvc.restful.model.DepartmentVO;
import com.max.springmvc.restful.model.EmployeeVO;
import com.max.springmvc.util.DepartmentMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements Converter<String, EmployeeVO>{

    @Autowired
    private DepartmentMapping demapping;

    @Override
    public EmployeeVO convert(String source) {
        if(source != null){
            String [] values = source.split("-");
            if(values != null && values.length == 5){
                String empId = values[0];
                String name = values[1];
                Integer age = Integer.parseInt(values[2]);
                String gender = values[3];
                DepartmentVO departmentVO = new DepartmentVO();
                departmentVO.setDepId(values[4]);
                departmentVO.setDepartmentName(demapping.departmentMapping(values[4]));
                EmployeeVO employeeVO = new EmployeeVO(empId,name,age,gender,departmentVO);
                System.out.println(source +" 轉換後 = " + employeeVO);
                return employeeVO;
            }
        }
        return null;
    }
}
