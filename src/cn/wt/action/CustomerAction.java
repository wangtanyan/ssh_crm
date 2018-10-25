package cn.wt.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wt.domain.Customer;
import cn.wt.domain.Dict;
import cn.wt.domain.PageBean;
import cn.wt.service.CustomerService;

@SuppressWarnings("serial")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	//根据客户级别统计
	public String levelStat() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = getPc(request);
		PageBean pageBean  =customerService.levelStat(currentPage);
		String uri = getURI(request);
		pageBean.setUri(uri);
		request.setAttribute("pageBean", pageBean);
		return "listLevel";
	}
	
	//根据客户来源统计
	public String sourceStat() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = getPc(request);
		PageBean pageBean = customerService.sourceStat(currentPage);
		String uri = getURI(request);
		pageBean.setUri(uri);
		request.setAttribute("pageBean", pageBean);
		return "listSource";
	}
	//跳转到多条件组合查询
	public String toMoreCondition() {
		List<Dict> dicts = customerService.findDict();
		ServletActionContext.getRequest().setAttribute("dicts", dicts);
		return "toMoreCondition";
	}
	//多条件组合查询
	public String moreCondition() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		customer = encoding(customer);
		int currentPage = getPc(request);
		PageBean pageBean = customerService.moreCondition(currentPage,customer);
		pageBean.setUri(getURI(request));
		request.setAttribute("pageBean", pageBean);
		return "findList";
	}
	
	//跳转到添加页面
	public String toAddPage() {
		List<Dict> dicts = customerService.findDict();
		ServletActionContext.getRequest().setAttribute("dicts", dicts);
		return "toAddAage";
	}
	//添加客户
	public String addCustomer() {
		String result = customerService.addCustoemr(customer);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(result.equals("addSuccess")) {
			request.setAttribute("addMsg", "添加客户成功，请继续添加！");
			return "toAddAage";
		}else {
			request.setAttribute("addMsg", "添加客户失败，请重新添加！");
			return result;
		}
	}
	
	/*//查询全部客户
	private List<Customer> customers;
	public List<Customer> getCustomers() {
		return customers;
	}
	public String findAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		customers = customerService.findAll();
		request.setAttribute("customers", customers);
		return "findList";
	}*/
	
	//分页查询全部客户
	public String findAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = getPc(request);
		PageBean pageBean = customerService.findPageList(currentPage);
		pageBean.setUri(getURI(request));
		request.setAttribute("pageBean", pageBean);
		return "findList";
	}
	
	//跳转到修改页面
	public String toUpdatePage() {
		int cid = customer.getCid();
		Customer result = customerService.findById(cid);
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Dict> dicts = customerService.findDict();
		request.setAttribute("dicts", dicts);
		request.setAttribute("customer", result);
		return "toUpdatePage";
	}
	//修改客户
	public String updateCustomer() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String s = customerService.updateCustomer(customer);
		request.setAttribute("updateMsg", "客户信息修改成功！");
		return s;
	}
	
	//删除客户
	public String deleteCustomer() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cusId = request.getParameter("cid");
		String result = customerService.deleteCustomer(cusId);
		return result;
	}
	
	//通过客户名查找
	public String findByName() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPageI = getPc(request);
		 customer = encoding(customer);
		 
		PageBean pageBean = customerService.findByName(customer,currentPageI);
		pageBean.setUri(getURI(request));
		request.setAttribute("pageBean",pageBean);
		return "findList";
	}
	//获取当前页
	private int getPc(HttpServletRequest request) {
		String pc = request.getParameter("currentPage");
		if(pc==null||pc.trim().isEmpty()) {
			return 1;
		}else {
			return Integer.parseInt(pc);
		}
	}
	//获取请求uri
	private String getURI(HttpServletRequest request)  {
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		System.out.println(queryString);
		int index = queryString.indexOf("&currentPage=");
		queryString = queryString.substring(0,index);
		return uri+"?"+queryString;
	}
	//修改编码格式
	private Customer encoding(Customer customer) throws UnsupportedEncodingException {
		String custName = customer.getCustName();
		if(!custName.trim().isEmpty()||!custName.equals("")) {
			custName = new String(custName.getBytes("ISO-8859-1"),"UTF-8");
			customer.setCustName(custName);
		}
		return customer;
	}
}
