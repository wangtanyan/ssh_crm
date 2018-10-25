package cn.wt.domain;

import java.util.HashSet;
import java.util.Set;

public class Dict {
	private String did;
	private String dname;
	
	private Set<Customer> customers = new HashSet<Customer>();
	
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Dict [did=" + did + ", dname=" + dname + ", customers=" + customers + "]";
	}
	
}
