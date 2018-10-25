<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/linkMan_moreCondition.action"
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
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>联系人名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" maxLength=50 name="lkmName"></TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 "></TD>
												</TR>
											</TBODY>
										</TABLE>
										<input type="hidden" name="currentPage" value="1">
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>联系人名称</TD>
													<TD>性别</TD>
													<td>所属客户</td>
													<TD>办公电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${pageBean.list }" var="linkman">
												<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${linkman.lkmName }</TD>
													<TD>${linkman.lkmGender }</TD>
													<td>${linkman.customer.custName }</td>
													<TD>${linkman.lkmPhone }</TD>
													<TD>${linkman.lkmMobile }</TD>
													
													<TD>
													<a href="${pageContext.request.contextPath }/linkMan_toEditPage.action?lid=${linkman.lid}">修改</a>|
													<a href="${pageContext.request.contextPath }/linkMan_deleteLinkMan.action?lid=${linkman.lid}">删除</a>
													</TD>
												</TR>
												
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD>
										<%-- <SPAN id=pagelink>
												<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
													共   ${pageBean.totalPage }页=======
													
													共[<B>${total}</B>]条记录,[<B>${totalPage}</B>]页
													,当前${page}页
													[<A href="javascript:to_page(${page-1})">前一页</A>]
													&nbsp;
													[<A href="javascript:to_page(${page+1})">后一页</A>] 
													
												</DIV>
										</SPAN> --%>
										<span id="pagelink">
											<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: center">
												共找到<b>${pageBean.totalCount }</b>条记录&nbsp;&nbsp;第<b>${pageBean.currentPage }</b>页/共<b>${pageBean.totalPage }</b>页
												<a href="${pageBean.uri }&currentPage=1">首页</a>
												<a <c:if test="${pageBean.currentPage>1 }">href="${pageBean.uri }&currentPage=${pageBean.currentPage-1}"</c:if>>
												上一页</a>
												<c:choose>
													<c:when test="${pageBean.totalPage<=10 }">
														<c:set var="begin" value="1"></c:set>
														<c:set var="end" value="${pageBean.totalPage }"></c:set>
													</c:when>
													<c:otherwise>
														<c:set var="begin" value="${pageBean.currentPage-5 }"></c:set>
														<c:set var="end" value="${pageBean.currentPage+4 }"></c:set>
														<c:if test="${begin<1 }">
															<c:set var="begin" value="1"></c:set>
															<c:set var="end" value="10"></c:set>
														</c:if>
														<c:if test="${end>pageBean.totalPage }">
															<c:set var="end" value="${pageBean.totalPage }"></c:set>
															<c:set var="begin" value="${pageBean.totalPage-9 }"></c:set>
														</c:if>
													</c:otherwise>
												</c:choose>
												<c:forEach begin="${begin }" end="${end }" var="i">
												<c:choose>
													<c:when test="${pageBean.currentPage eq i }">
														${i }&nbsp;
													</c:when>
													<c:otherwise>
														<a href="${pageBean.uri }&currentPage=${i}">${i }&nbsp;</a>
													</c:otherwise>
												</c:choose>
													
												</c:forEach>
												<a <c:if test="${pageBean.currentPage<pageBean.totalPage }">href="${pageBean.uri }&currentPage=${pageBean.currentPage+1}"</c:if>>下一页</a>
												<a href="${pageBean.uri }&currentPage=${pageBean.totalPage}">尾页</a>
											</div>
										</span>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
