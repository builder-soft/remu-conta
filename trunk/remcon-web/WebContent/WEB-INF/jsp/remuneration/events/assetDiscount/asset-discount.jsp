<%@page import="java.sql.ResultSet"%>
<%@page import="cl.buildersoft.business.beans.AssetDiscount"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
	List<AssetDiscount> assetDiscounts = (List<AssetDiscount>) request.getAttribute("AssetDiscount");
	ResultSet assetDiscountData = (ResultSet) request.getAttribute("AssetDiscountData");
	Boolean haveData = assetDiscountData.next();

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

<!-- 
<form method="post" action="${pageContext.request.contextPath}/servlet/ShowParameters">
 -->
<form method="post"
	action="${pageContext.request.contextPath}/servlet/remuneration/events/assetDiscount/SaveAssetDiscount">
	<input name="cPeriod" type="hidden" value="<%=period.getId()%>">
	<input name="cEmployee" type="hidden" value="<%=employee.getId()%>">
	<table>
		<tr>
			<td valign="top">
				<table border="0"><%=write_asset_discount(1L, assetDiscounts, assetDiscountData, haveData)%>
					<tr>
						<td class='cLabel'>Participación:</td>
						<td><input name="cParticipation"
							value="<%=haveData ? assetDiscountData.getString("cParticipation") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Aguinaldo:</td>
						<td><input name="cExtraPay"
							value="<%=haveData ? assetDiscountData.getString("cExtraPay") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Familiar Retroactivo:</td>
						<td><input name="cFamilyRetroactive"
							value="<%=haveData ? assetDiscountData.getString("cFamilyRetroactive") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Alimentación:</td>
						<td><input name="cFeeding"
							value="<%=haveData ? assetDiscountData.getString("cFeeding") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Movilización:</td>
						<td><input name="cMobilization"
							value="<%=haveData ? assetDiscountData.getString("cMobilization") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Viaticos:</td>
						<td><input name="cBounty"
							value="<%=haveData ? assetDiscountData.getString("cBounty") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Mes de aviso:</td>
						<td><input name="cMonthNotification"
							value="<%=haveData ? assetDiscountData.getString("cMonthNotification") : 0L%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Indemnización años de servicio:</td>
						<td><input name="cIAS"
							value="<%=haveData ? assetDiscountData.getString("cIAS") : 0L%>"></td>
					</tr>
				</table>
			</td>
			<td valign="top">
				<table border="0"><%=write_asset_discount(2L, assetDiscounts, assetDiscountData, haveData)%>
				</table>
	</table>
	<input type="submit" value="Aceptar"> <a
		href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>

</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String write_asset_discount(Long type, List<AssetDiscount> assetDiscounts, ResultSet assetDiscountData,
			Boolean haveData) throws Exception {
		String out = "<caption>" + (type.equals(1L) ? "Abonos" : "Descuentos") + "</caption>";
		String preFix = type.equals(1L) ? "B" : "D";
		String nameField = null;
		for (AssetDiscount assetDiscount : assetDiscounts) {
			if (assetDiscount.getAssetDiscountType().equals(type)) {
				nameField = preFix + "0" + assetDiscount.getIndex();
				out += "<tr>";
				out += "<td class='cLabel'>" + assetDiscount.getName() + ":</td>";

				/*
				out += "<td><input type='text' name='" + nameField + "' value='" + "c" + nameField + "'></td>";
				 */

				out += "<td><input type='text' name='" + nameField + "' value='" + assetDiscountData.getObject("c" + nameField)
						+ "'></td>";

				out += "</tr>";
			}
		}
		return out;
	}%>