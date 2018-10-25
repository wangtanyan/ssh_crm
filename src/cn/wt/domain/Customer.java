package cn.wt.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户实体类
 * @author 离末
 *
 */
public class Customer {
	private Integer cid;
	private String custName;
	//private String custLevel;
	private String custSource;
	//private String custLinkman;
	private String custPhone;
	private String custMobile;
	private String custAddress;
	private String custZip;
	private String custFax;
	private String custWebsite;
	
	//关联客户级别数据字典表
	private Dict custLevel;
	
	//关联联系人
	private Set<LinkMan> linkMans;

	//关联用户访问管理
	
	private Set<Visit> visits = new HashSet<Visit>();

	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}


	public Dict getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(Dict custLevel) {
		this.custLevel = custLevel;
	}

	public String getCustSource() {
		return custSource;
	}

	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustZip() {
		return custZip;
	}

	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}

	public String getCustFax() {
		return custFax;
	}

	public void setCustFax(String custFax) {
		this.custFax = custFax;
	}

	public String getCustWebsite() {
		return custWebsite;
	}

	public void setCustWebsite(String custWebsite) {
		this.custWebsite = custWebsite;
	}

	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}

	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", custName=" + custName + ", custSource=" + custSource + ", custPhone="
				+ custPhone + ", custMobile=" + custMobile + ", custAddress=" + custAddress + ", custZip=" + custZip
				+ ", custFax=" + custFax + ", custWebsite=" + custWebsite + ", custLevel=" + custLevel + ", linkMans="
				+ linkMans + ", visits=" + visits + "]";
	}


}
