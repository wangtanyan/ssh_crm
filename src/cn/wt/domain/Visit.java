package cn.wt.domain;

public class Visit {
	private Integer vid;
	private String vaddress;
	private String vcontent;
	
	//所属客户
	private Customer vcustomer;
	//关联所属用户
	private User vuser;
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	public String getVcontent() {
		return vcontent;
	}
	public void setVcontent(String vcontent) {
		this.vcontent = vcontent;
	}
	public Customer getVcustomer() {
		return vcustomer;
	}
	public void setVcustomer(Customer vcustomer) {
		this.vcustomer = vcustomer;
	}
	public User getVuser() {
		return vuser;
	}
	public void setVuser(User vuser) {
		this.vuser = vuser;
	}
	@Override
	public String toString() {
		return "Visit [vid=" + vid + ", vaddress=" + vaddress + ", vcontent=" + vcontent + ", vcustomer=" + vcustomer
				+ ", vuser=" + vuser + "]";
	}
	
	
}
