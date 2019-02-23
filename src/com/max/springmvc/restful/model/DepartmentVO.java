package com.max.springmvc.restful.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

public class DepartmentVO {
	
	private String depId;
	private String departmentName;
	
	public DepartmentVO() {
		
	}
	
	public DepartmentVO(String depId, String departmentName) {
		super();
		this.depId = depId;
		this.departmentName = departmentName;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
