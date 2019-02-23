package com.max.springmvc.restful.model;

import java.util.Date;
import java.util.Objects;

public class EmployeeVO {

	private String empId;
	private String name;
	private Integer age;
	private DepartmentVO department;
	private String gender;
	private Date birth;


	@Override
	public String toString() {
		return "EmployeeVO{" +
				"empId='" + empId + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				", department=" + department +
				", gender='" + gender + '\'' +
				", birth=" + birth +
				'}';
	}

	public EmployeeVO(String empId, String name, Integer age, String gender, DepartmentVO department) {
		super();
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.department = department;
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public EmployeeVO() {


	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmployeeVO that = (EmployeeVO) o;
		return Objects.equals(empId, that.empId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId);
	}

	public DepartmentVO getDepartment() {
		return department;
	}
	
	public void setDepartment(DepartmentVO department) {
		this.department = department;
	}
	public String getEmpId() {
		return empId;
	}
	

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
