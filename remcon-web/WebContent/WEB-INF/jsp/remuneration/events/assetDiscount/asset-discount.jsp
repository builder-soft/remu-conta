<%@page import="cl.buildersoft.business.beans.AssetDiscount"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
Period period = (Period) request.getAttribute("Period");
Employee employee = (Employee) request.getAttribute("Employee");
List<AssetDiscount> assetDiscounts = (List<AssetDiscount>) request.getAttribute("AssetDiscount");


String periodName = BSWeb.month2Word(period.getDate()) + " de " +   BSWeb.getYear(period.getDate());
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Haberes y descuentos para empleado</h1>

<span class="cLabel">Período:&nbsp;&nbsp;</span>
<span class="cData"><%=periodName%></span>
<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<br>



 
<table>
<%for(AssetDiscount assetDiscount : assetDiscounts ){ %>
	<tr>
		<td class='cLabel'><%=assetDiscount.getName() %></td>
		<td><input type="text"></td>
	</tr>
<%} %>	
	
</table>
<!-- 
<a href="${pageContext.request.contextPath}/servlet/...">Volver</a>
 -->
 
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

