package customertableview;

import java.sql.Date;

public class UserBean 
{
	String mob,name,address,paper,price,hawker;
	Date curdate;
	UserBean(){}
	public UserBean(String mob, String name, String address, String paper,String price, String hawker, Date curdate) {
		super();
		this.mob = mob;
		this.name = name;
		this.address = address;
		this.paper = paper;
		this.price=price;
		this.hawker = hawker;
		this.curdate = curdate;
	}
	public String getMob()
	{
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getHawker() {
		return hawker;
	}
	public void setHawker(String hawker) {
		this.hawker=hawker;
	}
	public Date getCurdate()
	{
		return curdate;
	}
	public void setDate(Date curdate)
	{
		this.curdate=curdate;
	}
}

	