﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户拜访记录查询</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/visit_moreCondition.action?1=1"
		method=get>
		

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
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：综合查询 &gt; 客户拜访记录查询</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
							 		<select name="vcustomer.cid">
							 		<c:choose>
										<c:when test="${visit.vcustomer.cid eq null || visit.vcustomer.cid eq -1 }">
											<option value="-1">--请选择--</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${customers }" var="customer">
												<c:if test="${visit.vcustomer.cid eq customer.cid }">
													<option value="${customer.cid }">${customer.custName }</option>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
										<c:forEach items="${customers }" var="customer">
											<c:if test="${visit.vcustomer.cid ne customer.cid }">
												 <option value="${customer.cid }">${customer.custName }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								</tr>
								
								<tr>
								<td>用户名称 ：</td>
								<td>
									<select name="vuser.uid">
									<c:choose>
										<c:when test="${visit.vuser.uid eq null || visit.vuser.uid eq -1 }">
											<option value="-1">--请选择--</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${users }" var="user">
												<c:if test="${visit.vuser.uid eq user.uid }">
													<option value="${user.uid }">${user.username }</option>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
										
										<c:forEach items="${users }" var="user">
											<c:if test="${user.uid ne visit.vuser.uid }">
												<option value="${user.uid}">${user.username }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</TR>
							
							<TR>
								
								<td>访问地址 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="vaddress">
								</td>
								<td>访问内容 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="vcontent">
								</td>
							</TR>
							
							<tr>
								<td><input type="hidden" name="currentPage" value="1"/></td>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="查询 " name=sButton2>
								</td>
								<td align="center">
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
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
	</FORM>
</BODY>
</HTML>
