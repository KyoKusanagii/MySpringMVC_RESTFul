package com.max.springmvc.restful.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component	//要使用autoweird，這個標誌一定要加
public class DepartmentDAO {
	
	List<DepartmentVO> list = new ArrayList<>();
	{
		list.add(new DepartmentVO("D001","HR"));
		list.add(new DepartmentVO("D002","Sales"));
		list.add(new DepartmentVO("D003","RD"));
		list.add(new DepartmentVO("D004","Engineer"));
	}
	public List<DepartmentVO> getAll(){
		return list;
	}
	
	public void delete(String ID){



	}


}
