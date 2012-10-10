<%@page import="cl.buildersoft.business.beans.VoucherStatus"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.business.beans.VoucherType"%>
<%@page import="cl.buildersoft.business.beans.Voucher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Voucher voucher = (Voucher) request.getAttribute("Voucher");
	List<VoucherType> voucherTypeList = (List<VoucherType>) request.getAttribute("VoucherTypeList");
	String voucherStatus = (String)request.getAttribute("VoucherStatus");
	String userVoucher = (String)request.getAttribute("UserVoucher");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<script
	src="${pageContext.request.contextPath}/js/conta/voucher/edit-voucher.js?<%=Math.random()%>">
	
</script>

<h1 class="cTitle">Comprobante</h1>
<!-- 
<form id="frm" method="post"
	action="${pageContext.request.contextPath}/servlet/conta/voucher/SaveVoucher?<%=BSWeb.randomString()%>">
	 -->
<input id="cId" type="hidden" value="<%=voucher.getId()%>">
<table border="0">
	<tr>
		<td class="cLabel">Tipo:</td>
		<td><select id="cVoucherType" onchange="javascript:saveVoucher()">
				<%
					Boolean selected = Boolean.FALSE;
					for (VoucherType voucherType : voucherTypeList) {
						selected = voucherType.getId().equals(voucher.getVoucherType());
				%>
				<option value="<%=voucherType.getId()%>"
					<%=selected ? "selected" : ""%>><%=voucherType.getName()%></option>
				<%
					}
				%>
		</select></td>
		<td class="cLabel">Número:</td>
		<td><input type="number" id="cNumber" value="<%=voucher.getNumber() %>"
			onchange="javascript:saveVoucher()"></td>
	</tr>
	<tr>
		<td class="cLabel">Fecha Creación:</td>
		<td class="cData"><%=BSDateTimeUtil.dateTime2String(request, voucher.getCreationTime())%></td>
		<td class="cLabel">Estado:</td>
		<td class="cData"><%=voucherStatus%></td>
	</tr>
	<tr>
		<td class="cLabel">Usuario:</td>
		<td class="cData" colspan="3"><%=userVoucher%></td>
	</tr>

</table>

<br>
<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class="cHeadTD" rowspan="2" style="text-align: center">Rut</td>
		<td class="cHeadTD" colspan="2" style="text-align: center">Documento</td>
		<td class="cHeadTD" rowspan="2" style="text-align: right">Monto</td>
		<td class="cHeadTD" rowspan="2" style="text-align: right">Impuesto</td>
		<td class="cHeadTD" rowspan="2" style="text-align: center">Área de negocio</td>
		<td class="cHeadTD" rowspan="2" style="text-align: center">Centro de Costo</td>
		<td class="cHeadTD" rowspan="2" style="text-align: center">Cuenta</td>
	</tr>

	<tr><!-- 
		<td class="cHeadTD">Rut</td> -->
		<td class="cHeadTD" style="text-align: center">Tipo</td>
		<td class="cHeadTD" style="text-align: center">Número</td>
	</tr>

</table>

<!-- 
</form> -->
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
<br> 
<button type="button">Nueva Cuenta</button>
<br><br>
<button type="button" onclick="javascript:confirm('Esta seguro de confirmar este comprobante?')">Grabar y Contabilizar</button>&nbsp;&nbsp;&nbsp;
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/conta/voucher/VoucherManager">Volver</a>

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

