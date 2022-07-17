package bill_history;


public class UserBean2 {
	int billid;
	String cust_mob;
	int noofdays;
	String dateofbill;
	float amount;
	
	UserBean2(){}
	public UserBean2(int billid,String cust_mob, int noofdays, String dateofbill, float amount) {
		super();
		this.billid = billid;
		this.cust_mob = cust_mob;
		this.noofdays = noofdays;
		this.dateofbill = dateofbill;
		this.amount = amount;
		
		
	}
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}
	public String getCust_mob() {
		return cust_mob;
	}
	public void setCust_mob(String cust_mob) {
		this.cust_mob = cust_mob;
	}
	public int getNoofdays() {
		return noofdays;
	}
	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
	}
	public String getDateofbill() {
		return dateofbill;
	}
	public void setDateofbill(String dateofbill) {
		this.dateofbill = dateofbill;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}