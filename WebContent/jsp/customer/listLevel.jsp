<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户级别统计列表</TITLE> 
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
<script type="text/javascript">
	/* /* 无法获取cid的值 */
	<%--function cus_delete(){
		if(window.confirm("您确定要删除此客户？")){
			var del = document.getElementById("cus_delete");
			
			del.href="${pageContext.request.contextPath}/customer_deleteCustomer.action"+"?cid=";
		}else{
			return;
		}
	} --%>
</script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByName.action"
		method="get">
		
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
								<TD class=manageHead>当前位置：统计分析 &gt; 客户级别统计</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户级别</TD>
													<TD>数量</TD>
												</TR>
						<c:forEach items="${pageBean.list }" var="map">
						<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
							<TD>${map.dname }</TD>
							<TD>${map.num }</TD>
						</TR>
						
						</c:forEach>
						
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: center">
												共找到<b>${pageBean.totalCount }</b>条记录&nbsp;&nbsp;第<b>${pageBean.currentPage }</b>页/共<b>${pageBean.totalPage }</b>页
													 <a href="${pageBean.uri }&currentPage=1">首页</a>
												<a 
													<c:if test="${pageBean.currentPage>1 }">href="${pageBean.uri }&currentPage=${pageBean.currentPage-1}"</c:if>
													>上一页</a>
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
															<c:set var="begin" value="${pageBean.currentPage-9 }"></c:set>
															<c:set var="end" value="${pageBean.totalPage }"></c:set>
														</c:if>
													</c:otherwise>
												</c:choose>
												<c:forEach var="i" begin="${begin }" end="${end }">
												<c:choose>
													<c:when test="${pageBean.currentPage eq i }">
														<i>${i }&nbsp;</i>
													</c:when>
													<c:otherwise>
														<i>
															<a href="${pageBean.uri }&currentPage=${i}">${i }</a>&nbsp;
														</i>
													</c:otherwise>
												</c:choose>
												</c:forEach>
												<a 
												<c:if test="${pageBean.currentPage<pageBean.totalPage }">href="${pageBean.uri }&currentPage=${pageBean.currentPage+1 }"</c:if>
												>下一页</a>
												<a href="${pageBean.uri }&currentPage=${pageBean.totalPage}">尾页</a>
											</DIV>
									</SPAN></TD>
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
