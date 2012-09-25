<%@page import="java.sql.SQLException"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
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

	String periodName = BSDateTimeUtil.month2Word(period.getDate()) + " de " + BSDateTimeUtil.getYear(period.getDate());
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
<hr>
<form method="post"
	action="${pageContext.request.contextPath}/servlet/remuneration/events/assetDiscount/SaveAssetDiscount">

	<input name="cPeriod" type="hidden" value="<%=period.getId()%>">
	<input name="cEmployee" type="hidden" value="<%=employee.getId()%>">
	<table>
		<tr>
			<td valign="top">
				<table border="0">
					<caption>Abonos</caption>
					<tr>
						<td class='cLabel'>Participación:</td>
						<td><input name="cParticipation"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cParticipation") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Aguinaldo:</td>
						<td><input name="cExtraPay"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cExtraPay") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Familiar Retroactivo:</td>
						<td><input name="cFamilyRetroactive"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cFamilyRetroactive") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Alimentación:</td>
						<td><input name="cFeeding"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cFeeding") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Movilización:</td>
						<td><input name="cMobilization"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cMobilization") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Viaticos:</td>
						<td><input name="cBounty"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cBounty") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Mes de aviso:</td>
						<td><input name="cMonthNotification"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cMonthNotification") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Indemnización años de servicio:</td>
						<td><input name="cIAS"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cIAS") : 0D)%>"></td>
					</tr>
					<%=write_asset_discount(request, 1L, assetDiscounts, assetDiscountData, haveData)%>
				</table>
			</td>
			<td valign="top">
				<table border="0">
					<caption>Descuentos</caption>
					<tr>
						<td class='cLabel'>Prestamo de empresa:</td>
						<td><input name="cLoanEnterprise"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cLoanEnterprise") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Prestamo Caja compensación:</td>
						<td><input name="cLoanCompensationFund"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cLoanCompensationFund") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Ahorro Caja compesación:</td>
						<td><input name="cSavingCompensationFund"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cSavingCompensationFund") : 0D)%>"></td>
					</tr>
					<tr>
						<td class='cLabel'>Retención Judicial:</td>
						<td><input name="cJudicialRetention"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"
							value="<%=BSWeb.formatDouble(request, haveData ? assetDiscountData.getDouble("cJudicialRetention") : 0D)%>"></td>
					</tr>
					<%=write_asset_discount(request, 2L, assetDiscounts, assetDiscountData, haveData)%>
				</table>
	</table>
	<button type="submit">Aceptar</button>
	&nbsp;&nbsp;<a class="cCancel"
		href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Cancelar</a>

</form>

<%
	Connection conn = (Connection) request.getAttribute("Conn");
	new BSmySQL().closeConnection(conn);
	new BSmySQL().closeSQL(assetDiscountData);
%>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String write_asset_discount(HttpServletRequest request, Long type, List<AssetDiscount> assetDiscounts, ResultSet assetDiscountData,
			Boolean haveData) throws Exception {
		String out = "";
		String preFix = type.equals(1L) ? "cB" : "cD";
		String nameField = null;
		for (AssetDiscount assetDiscount : assetDiscounts) {
			if (assetDiscount.getAssetDiscountType().equals(type) && assetDiscount.getEnable()) {
				if (assetDiscount.getIndex() < 10) {
					nameField = preFix + "0" + assetDiscount.getIndex();
				} else {
					nameField = preFix + assetDiscount.getIndex();
				}
				out += "<tr>";
				out += "<td class='cLabel'>" + assetDiscount.getName() + ":</td>";

				out += "<td><input type='text' " + 
						"onfocus='javascript:doubleFocus(this);' " +
						"onblur='javascript:doubleBlur(this);' " +
						"name='" + nameField + "' value='" + getValue(request, assetDiscountData, nameField)
						+ "'></td>";

				out += "</tr>";
			}
		}
		return out;
	}
	
	private String getValue(HttpServletRequest request, ResultSet assetDiscountData, String nameField) {
		String out = null;

		try {
			Double dbl = assetDiscountData.getDouble(nameField);
			out = BSWeb.formatDouble(request, dbl);
		} catch (SQLException e) {
			out = "";
		}

		return out;

	}%>