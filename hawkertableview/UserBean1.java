
package hawkertableview;

import java.sql.Date;

public class UserBean1
{
	String name,mobile,address,area,age;
	Float salary;
	Date dob;
	
	
	UserBean1(){}
	public UserBean1(String name, String mobile, String address, String area, String age, Float salary, Date dob) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.area = area;
		this.age = age;
		this.salary = salary;
		this.dob = dob;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
}
	