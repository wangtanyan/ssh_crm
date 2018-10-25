package cn.wt.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wt.domain.Customer;
import cn.wt.domain.LinkMan;
import cn.wt.domain.PageBean;
import cn.wt.service.CustomerService;
import cn.wt.service.LinkManService;

@SuppressWarnings("serial")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//封装表单提交的参数
	LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}
	
	//封装上传参数
	private File upload;
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	//跳转到综合查询页面
	public String toSelLinkManPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Customer> customers = customerService.findAll();
		request.setAttribute("customers", customers);
		return "toSelLinkManPage";
	}
	//跳转到添加页面
	public String toAddPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Customer> customers= customerService.findAll();
		request.setAttribute("customers", customers);
		return "toAddPage";
	}
	//添加联系人
	public String addLinkMan() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if(upload!=null) {
			//新建服务器目标文件路径
			File newFile = new File("F:\\ssh_File"+"/"+uploadFileName);
			//复制目标文件
			FileUtils.copyFile(upload, newFile);
		}
		
		int result = linkManService.addLinkMan(linkMan);
		if(result>0) {
			List<Customer> customers= customerService.findAll();
			request.setAttribute("customers", customers);
			request.setAttribute("addMsg", "添加成功，请继续添加！");
			return "toAddPage";
		}else {
			List<Customer> customers= customerService.findAll();
			request.setAttribute("customers", customers);
			request.setAttribute("addMsg", "添加失败，请继续添加！");
			return "toAddPage";
		}
	}

	//分页查询所有联系人
	public String findPageList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String uri = getUri(request);
		PageBean pageBean = linkManService.findPageList(currentPage);
		pageBean.setUri(uri);
		request.setAttribute("pageBean", pageBean);
		return "findPageListSuccess";
	}
	
	//获取uri
	private String getUri(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		System.out.println(queryString);
		int index = queryString.indexOf("&currentPage=");
		queryString = queryString.substring(0, index);
		System.out.println(queryString);
		return uri+"?"+queryString;
	}
	//跳转到修改联系人页面
	public String toEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int lid = Integer.parseInt(request.getParameter("lid"));
		LinkMan linkMan = linkManService.findLinkManById(lid);
		request.setAttribute("linkMan", linkMan);
		
		List<Customer> customers= customerService.findAll();
		request.setAttribute("customers", customers);
		return "toEditPage";
	}
	//修改联系人
	public String editLinkMan() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int clid = Integer.parseInt(request.getParameter("clid"));
		return linkManService.editLinkMan(linkMan,clid);
	}
	
	//删除联系人
	public String deleteLinkMan() {
		return linkManService.deleteLinkMan(linkMan);
	}
	
	//通过条件筛选联系人
	public String moreCondition() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri = getUri(request);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		LinkMan linkMan1 = encoding(linkMan);
		PageBean pageBean = linkManService.findByRequirement(linkMan1,currentPage);
		pageBean.setUri(uri);
		request.setAttribute("pageBean", pageBean);
		return "findPageListSuccess";
	}
	
	//修改编码
	private LinkMan encoding(LinkMan linkMan2) throws UnsupportedEncodingException {
		String lkmName = linkMan2.getLkmName();
		String lkmGender = linkMan2.getLkmGender();
		if(lkmName!=null) {
			lkmName = new String(lkmName.getBytes("ISO-8859-1"),"UTF-8");
			linkMan2.setLkmName(lkmName);
		}
		if(lkmGender!=null){
			lkmGender = new String(lkmGender.getBytes("ISO-8859-1"),"UTF-8");
			linkMan2.setLkmGender(lkmGender);
		}
		return linkMan2;
	}
	
}
