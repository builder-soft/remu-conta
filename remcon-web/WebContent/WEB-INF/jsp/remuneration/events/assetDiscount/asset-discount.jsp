<%@page import="cl.buildersoft.business.beans.AssetDiscount"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
	List<AssetDiscount> assetDiscounts = (List<AssetDiscount>) request.getAttribute("AssetDiscount");

	String periodName = BSWeb.month2Word(period.getDate()) + " de " + BSWeb.getYear(period.getDate());
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
	<tr>
		<td valign="top">
			<table border="1"><%=write_asset_discount(1L, assetDiscounts)%>
				<tr>
					<td class='cLabel'>cParticipation</td>
				</tr>
				<tr>
					<td class='cLabel'>cExtraPay</td>
				</tr>
				<tr>
					<td class='cLabel'>cFamilyRetroactive</td>
				</tr>
				<tr>
					<td class='cLabel'>cFeeding</td>
				</tr>
				<tr>
					<td class='cLabel'>cMobilization</td>
				</tr>
				<tr>
					<td class='cLabel'>cBounty</td>
				</tr>
				<tr>
					<td class='cLabel'>cMonthNotification</td>
				</tr>
				<tr>
					<td class='cLabel'>cIAS</td>
				</tr>
			</table>
		</td>
		<td valign="top">
			<table border="1"><%=write_asset_discount(2L, assetDiscounts)%>
			</table>
</table>
<!-- 
<a href="${pageContext.request.contextPath}/servlet/...">Volver</a>
 -->

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String write_asset_discount(Long type, List<AssetDiscount> assetDiscounts) throws Exception {
		String out = "<caption>" + (type.equals(1L) ? "Abonos" : "Descuentos") + "</caption>";
		String preFix = type.equals(1L) ? "B" : "D";

		for (AssetDiscount assetDiscount : assetDiscounts) {
			if (assetDiscount.getAssetDiscountType().equals(type)) {
				out += "<tr>";
				out += "<td class='cLabel'>" + assetDiscount.getName() + "</td>";
				out += "<td><input type='text' name='" + preFix + assetDiscount.getIndex() + "'></td>";
				out += "</tr>";
			}
		}
		return out;
	}%>