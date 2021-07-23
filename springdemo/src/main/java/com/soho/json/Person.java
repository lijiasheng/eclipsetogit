package com.soho.json;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Person {
    
    @JSONField(name = "AGE")
    private int age;
 
    @JSONField(name = "FULL NAME")
    private String fullName;
 
    @JSONField(name = "DATE OF BIRTH", format="yyyy/MM/dd")
    private Date dateOfBirth;
 
    public Person(int age, String fullName, Date dateOfBirth) {
        this.age = age;
        this.fullName= fullName;
        this.dateOfBirth = dateOfBirth;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
