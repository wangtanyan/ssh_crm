package cn.wt.domain;


public class LinkMan {
	private Integer lid;
	private String lkmName;
	private String lkmPhone;
	private String lkmMobile;
	private String lkmGender;

	//关联客户
	private Customer customer;
	
	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getLkmName() {
		return lkmName;
	}

	public void setLkmName(String lkmName) {
		this.lkmName = lkmName;
	}

	public String getLkmPhone() {
		return lkmPhone;
	}

	public void setLkmPhone(String lkmPhone) {
		this.lkmPhone = lkmPhone;
	}

	public String getLkmMobile() {
		return lkmMobile;
	}

	public void setLkmMobile(String lkmMobile) {
		this.lkmMobile = lkmMobile;
	}

	public String getLkmGender() {
		return lkmGender;
	}

	public void setLkmGender(String lkmGender) {
		this.lkmGender = lkmGender;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "LinkMan [lid=" + lid + ", lkmName=" + lkmName + ", lkmPhone=" + lkmPhone + ", lkmMobile=" + lkmMobile
				+ ", lkmGender=" + lkmGender + "]";
	}


}
