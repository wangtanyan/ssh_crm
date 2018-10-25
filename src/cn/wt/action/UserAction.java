package cn.wt.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.wt.domain.User;
import cn.wt.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport{
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//用户登录
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User result = userService.login(user);
		if(result==null) {
			return "loginFailure";
		}else if(result.getUsername().equals(username)&&result.getPassword().equals(password)) {
			request.getSession().setAttribute("user", result);
			return "loginSuccess";
		}else {
			return "loginFailure";
		}
	}
	
	//修改密码
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		String oldPassword = request.getParameter("oldPassword");
		String new1Password = request.getParameter("new1Password");
		String new2Password = request.getParameter("new2Password");
		if(oldPassword==null||oldPassword=="") {
			request.setAttribute("oldMsg", "请输入原始密码！");
			return "editError";
		}else if(!oldPassword.equals(user.getPassword())) {
			request.setAttribute("oldMsg", "原始密码输入错误,请重新输入！");
			return "editError";
		}else {
			if(new1Password==""||new1Password==null) {
				request.setAttribute("new1Msg", "新密码不能为空！");
					return "editError";
			}else {
				if(!new1Password.equals(new2Password)) {
					request.setAttribute("new2Msg", "两次输入的密码不一致，请重新输入！");
					return "editError";
				}else {
					user.setPassword(new2Password);
					userService.updateUser(user);
					return exit();
				}
			}
		}
		
	}
	
	//退出登录
	public String exit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		return "loginFailure";
	}
}
