<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD id=Head1>
<TITLE>编辑</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
			<tr>
			   <TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg">
			   <IMG	src="${pageContext.request.contextPath }/images/new_022.jpg" border=0>
			   </TD>
					<TD align="center" bgColor=#ffffff>
					<form action="<c:url value="/UserAction/user_edit.action"/>" method="post">
						<table width="100%" height=300 border="0" cellpadding="0" cellspacing="0">
						
							<tr align="right">
							<td width="60%">
								<!-- <img src="images/welcome.gif" width="145" height="46" /> -->
								原 始 密 码：<input type="password" name="oldPassword" placeholder="请输入原始密码！"/>
							</td>
							<td align="left">
								&nbsp;<span style="color:red;">${oldMsg }</span>
							</td>
							</tr>
							<tr align="right">
							<td width="60%">
								新&nbsp;密&nbsp;码 ：<input type="password" name="new1Password" placeholder="请输入新密码！"/>
							</td>
							<td align="left">
								&nbsp;<span style="color: red">${new1Msg }</span>
							</td>
							</tr>
							<tr align="right">
							<td width="60%">
								确认新密码：<input type="password" name="new2Password" placeholder="请再次输入新密码！"/>
							</td>
							<td align="left">
								&nbsp;<span style="color: red">${new2Msg }</span>
							</td>
							</tr>
							<tr align="right">
							<td width="60%">
								<input type="submit" value="提交" style="width: 60px;height:25px" target=_top/>
								<input type="reset" value="重置" style="margin-left: 50px;width: 60px;height:25px"/>
							</td>
							</tr>
						</table>
						</form>
					</td>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
				<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0>
				</TD>
			</tr>
			</TBODY>
		</table>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
</BODY>
</HTML>
