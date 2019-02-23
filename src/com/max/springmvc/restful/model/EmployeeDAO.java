package com.max.springmvc.restful.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component  //要使用autoweird，這個標誌一定要加
public class EmployeeDAO {
	
	List<EmployeeVO> list = new ArrayList<>();
	{
		list.add(new EmployeeVO("1001","Max", 25,"1",new DepartmentVO("D001","HR")));
		list.add(new EmployeeVO("1002","Nancy", 22,"0",new DepartmentVO("D002","Sales")));
		list.add(new EmployeeVO("1003","Minnie", 23,"1",new DepartmentVO("D003","RD")));
		list.add(new EmployeeVO("1004","Ken", 20,"0",new DepartmentVO("D004","Engineer")));
	}
	public List<EmployeeVO> getAll(){
		
		return list;
	}
	
	public void delete(String ID){

		/*
			因為要從list移除物件，要比對物件是否相同，要改寫equals和hashcode
			改寫完之後，要得到完整的該物件，用java8的串流過濾找出該員工的員工編號(ID)
			，比對要刪除的員工邊號，進而得到完整的員工物件後再予以刪除
		 */
		Optional<EmployeeVO> result = null;
		if(list.stream().anyMatch(e -> e.getEmpId().equals(ID))){
			result = list.stream().filter(e -> e.getEmpId().equals(ID)).findFirst();

			if(result.isPresent()){
				list.remove(result.get());
			}
		}

	}

	public EmployeeVO get(String ID){

		Optional<EmployeeVO> result = null;
		EmployeeVO emp = null;
		if(list.stream().anyMatch(e -> e.getEmpId().equals(ID))){
			result = list.stream().filter(e -> e.getEmpId().equals(ID)).findFirst();
			if(result.isPresent()){
				emp = result.get();
			}
		}
		return emp;
	}

	public void save(EmployeeVO employee) {
	
		list.add(employee);
	}
}
