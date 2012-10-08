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
	src="${pageContext.request.contextPath}/js/...FILE.js?<%=Math.random()%>">
	
</script>

<h1 class="cTitle">Comprobante</h1>

<table>
	<tr>
		<td class="cLabel">Tipo:</td>
		<td><select>
				<%
					for (VoucherType voucherType : voucherTypeList) {
				%>
				<option value="<%=voucherType.getId()%>"><%=voucherType.getName()%></option>
				<%
					}
				%>
		</select>
</table>
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

