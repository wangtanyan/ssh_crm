package cn.wt.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wt.domain.Customer;
import cn.wt.domain.PageBean;
import cn.wt.domain.User;
import cn.wt.domain.Visit;
import cn.wt.service.CustomerService;
import cn.wt.service.UserService;
import cn.wt.service.VisitService;

/**
 * @author Administrator
 *
 */
public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	private CustomerService customerService;
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	
	//删除客户访问记录
	public String deleteVisit() {
		visitService.deleteVisit(visit);
		return "Success";
	}
	
	//修改客户访问记录
	public String updateVisit() {
		visitService.updateVisit(visit);
		return "Success";
	}
	
	//跳转到客户访问编辑页面
	public String toEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Visit visit1 = visitService.findVisitById(new Integer(request.getParameter("vid")));
		request.setAttribute("visit", visit1);
		List<Customer> customers = customerService.findAll();
		List<User> users = userService.findAll();
		request.setAttribute("customers", customers);
		request.setAttribute("users", users);
		return "toEditPage";
	}
	
	//综合查询客户访问记录
	public String moreCondition() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Visit visit = setEncodind(request);
		int currentPage = getPc(request);
		PageBean pageBean = visitService.moreCondition(currentPage,visit);
		String uri = getUri(request);
		pageBean.setUri(uri);
		request.setAttribute("pageBean", pageBean);
		return "visitList";
	}
	
	//跳转到综合查询页面
	public String toMoreConditionPage() {
		List<Customer> customers = customerService.findAll();
		List<User> users = userService.findAll();
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("customers", customers);
		request.setAttribute("users", users);
		return "moreConPage";
	}
	//跳转到添加页面
	public String toAddPage() {
		List<Customer> customers = customerService.findAll();
		List<User> users = userService.findAll();
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("customers", customers);
		request.setAttribute("users", users);
		return "toAddPage";
	}
	
	//添加客户访问记录
	public String addVisit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> msg = new HashMap<String,String>();
		if(visit.getVcustomer().getCid().toString().equals("-1")||visit.getVcustomer().getCid()==null){
			msg.put("customerMsg", "访问目标客户不能为空！");
		}
		if(visit.getVuser().getUid().toString().equals("-1")||visit.getVuser().getUid()==null) {
			msg.put("userMsg", "所属用户不能为空！");
		}
		if(msg.size()>0) {
			request.setAttribute("msg", msg);
			request.setAttribute("visit", visit);
			return toAddPage();
		}else {
			visitService.addVisit(visit);
			return "Success";
		}
	}
	
	//查询所有客户拜访记录
	public String allVisit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = getPc(request);
		PageBean pageBean = visitService.visitList(currentPage);
		String uri = getUri(request);
		pageBean.setUri(uri);
		request.setAttribute("pageBean", pageBean);
		return "visitList";
	}
	//获取请求uri
	private String getUri(HttpServletRequest request) {
		String queryString = request.getQueryString();
		String requestUri = request.getRequestURI();
		int index = queryString.indexOf("&currentPage=");
		queryString = queryString.substring(0, index);
		return requestUri+"?"+queryString;
	}
	//获取当前页
	private int getPc(HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		if(currentPage==null||currentPage.trim().isEmpty()) {
			return 1;
		}else {
			return Integer.parseInt(currentPage);
		}
	}
	
	//把get请求参数编码修改为UTF-8
	private Visit setEncodind(HttpServletRequest request) throws UnsupportedEncodingException {
		String vaddress = request.getParameter("vaddress");
		String vcontent = request.getParameter("vcontent");
		if(vaddress!=null&&!vaddress.trim().isEmpty()) {
			vaddress = new String(vaddress.getBytes("ISO-8859-1"),"UTF-8");
			visit.setVaddress(vaddress);
		}
		if(vcontent!=null&&!vcontent.trim().isEmpty()) {
			vcontent = new String(vcontent.getBytes("ISO-8859-1"),"UTF-8");
			visit.setVcontent(vcontent);
		}
		return visit;
	}
}
