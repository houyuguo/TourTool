package com.njusw.tourtool.bean;

public class PersonInfo {
	private Integer id;
	private String username;
	private Integer age;
	//gender=0:男性，gender=1:女性
	private Integer gender;
	private String schoolArea;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getSchoolArea() {
		return schoolArea;
	}
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	@Override
	public String toString() {
		return "PersonInfo [id=" + id + ", username=" + username + ", age=" + age + ", gender=" + gender
				+ ", schoolArea=" + schoolArea + "]";
	}
	
}
