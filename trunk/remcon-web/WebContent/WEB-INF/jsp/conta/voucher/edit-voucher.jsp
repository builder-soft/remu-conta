<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.business.beans.VoucherType"%>
<%@page import="cl.buildersoft.business.beans.Voucher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Voucher voucher = (Voucher) request.getAttribute("Voucher");
	List<VoucherType> voucherTypeList = (List<VoucherType>) request.getAttribute("VoucherTypeList");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
 
<script
	src="${pageContext.request.contextPath}/js/conta/voucher/edit-voucher.js?<%=Math.random()%>">	
</script>

<h1 class="cTitle">Comprobante</h1>

<table border="1">
	<tr>
		<td class="cLabel">Tipo:</td>
		<td><select name="cVoucherType" onchange="saveVoucher()">
				<%
					Boolean selected = Boolean.FALSE;
					for (VoucherType voucherType : voucherTypeList) {
						selected = voucherType.getId().equals(voucher.getVoucherType());
				%>
				<option value="<%=voucherType.getId()%>" <%=selected ? "selected" : ""%>><%=voucherType.getName()%></option>
				<%
					}
				%>
		</select></td>
		<td class="cLabel">N�mero:</td>
		<td><input type="text" Name="cNumber"></td>
	</tr>
	<tr>
		<td class="cLabel">Fecha Creaci�n:</td>
		<td class="cData"><%=BSDateTimeUtil.dateTime2String(request, voucher.getCreationTime())%></td>
		<td class="cLabel">Estado:</td>
		<td class="cData"><%=voucher.getVoucherStatus()%></td>
	</tr>
	<tr>
		<td class="cLabel">Usuario:</td>
		<td class="cData"><%= voucher.getUser()%></td>
		<td colspan="2"></td>
	</tr>
	
</table>
<br>
<!-- 
<input type="text" name="SomeObject" id="SomeObject"
	onfocus="javascript:doubleFocus(this);"
	onblur="javascript:doubleBlur(this);"
	value="<%=BSWeb.formatDouble(request, 1234.567)%>">

<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>encabezado</td>
	</tr>

	<tr>
		<td class='cDataTD'>dato</td>
	</tr>
	<tr>
		<td class='cDataTD_odd'>dato</td>
	</tr>

</table>
 -->

<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/conta/voucher/VoucherManager">Cancelar</a>

<!-- 
<div id="divShowDetail" style="display: none">
	<h2 class="cTitle2">Detalle de valores</h2>

	<div class="contentScroll">
		<table class="cList" cellpadding="0" cellspacing="0" id="movesTable">
			<tr>
				<td class="cHeadTD">Detalle</td>
				<td class="cHeadTD">Comentario</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Monto</td>
			</tr>
		</table>
	</div>
	<br /> <input type="button" value="Cancelar"
		onclick="javascript:closeTooltip()" />
</div>
 -->

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

