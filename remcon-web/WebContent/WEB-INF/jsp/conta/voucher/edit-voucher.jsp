<%@page import="cl.buildersoft.business.beans.VoucherDetail"%>
<%@page import="cl.buildersoft.business.beans.BusinessArea"%>
<%@page import="cl.buildersoft.business.beans.DocumentType"%>
<%@page import="cl.buildersoft.business.beans.VoucherStatus"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.business.beans.VoucherType"%>
<%@page import="cl.buildersoft.business.beans.Voucher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Voucher voucher = (Voucher) request.getAttribute("Voucher");
	List<VoucherType> voucherTypeList = (List<VoucherType>) request.getAttribute("VoucherTypeList");
	List<DocumentType> documentTypeList	= (List<DocumentType>) request.getAttribute("DocumentTypeList");
	List<BusinessArea> businessAreaList = (List<BusinessArea>)request.getAttribute("BusinessAreaList");
	List<VoucherDetail> voucherDetailList = (List<VoucherDetail>) request.getAttribute("VoucherDetailList");
	String voucherStatus = (String)request.getAttribute("VoucherStatus");
	String userVoucher = (String)request.getAttribute("UserVoucher");
	Boolean selected = null;
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<script
	src="${pageContext.request.contextPath}/js/conta/voucher/edit-voucher.js?<%=Math.random()%>">
</script>
<script>
	var documentTypeList = [<%Integer index = 1;
		for(DocumentType documentType : documentTypeList){%>
	    	{id : <%=documentType.getId()%>, name : '<%=documentType.getName()%>'}<%=index<documentTypeList.size()?",":""%><%index++;
	}%>];
</script>
<%!private String fixNull(Object value, Object defaultValue) {
		return value == null ? "" + defaultValue : "'" + value + "'";
	}%>

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
					selected = Boolean.FALSE;
					for (VoucherType voucherType : voucherTypeList) {
						selected = voucherType.getId().equals(voucher.getVoucherType());
				%>
				<option value="<%=voucherType.getId()%>"
					<%=selected ? "selected" : ""%>><%=voucherType.getName()%></option>
				<%
					}
				%>
		</select></td>

		<td class="cLabel">�rea de Negocios:</td>
		<td colspan="3"><select id="cBusinessArea"
			onchange="javascript:saveVoucher();fillCostCenter();">
				<option value="">-Seleccione-</option>
				<%
					selected = Boolean.FALSE;
					for (BusinessArea businessArea : businessAreaList) {
						selected = Boolean.FALSE;
				%>
				<option value="<%=businessArea.getId()%>"
					<%=selected ? "selected" : ""%>><%=businessArea.getName()%></option>
				<%
					}
				%>
		</select></td>

	</tr>
	<tr>
		<td class="cLabel">Fecha Creaci�n:</td>
		<td class="cData"><%=BSDateTimeUtil.dateTime2String(request, voucher.getCreationTime())%></td>
		<td class="cLabel">Fecha Contabilizaci�n:</td>
		<td><input type="text" id="cAccountingDate" size="10"
			maxLength="10" onblur='javascript:saveVoucher()'></td>
		<td colspan="2" class="cLabel">(<%=BSDateTimeUtil.getFormatDate(request)%>)
		</td>
	</tr>
	<tr>
		<td class="cLabel">Estado:</td>
		<td class="cData"><%=voucherStatus%></td>
		<td class="cLabel">Usuario:</td>
		<td class="cData" colspan="3"><%=userVoucher%></td>
	</tr>

</table>

<br>

<table class="cList" cellpadding="0" cellspacing="0" id="voucherDetail">
	<tr>
		<td class="cHeadTD" rowspan="2" style="text-align: center">Cuenta</td>

		<td class="cHeadTD" colspan="2" style="text-align: center">Documento</td>

		<td class="cHeadTD" rowspan="2" style="text-align: right">Monto</td>
		<!-- 
		<td class="cHeadTD" rowspan="2" style="text-align: right">Impuesto</td>
		<td class="cHeadTD" rowspan="2" style="text-align: center">�rea
			de negocio</td>
		 -->
		<td class="cHeadTD" rowspan="2" style="text-align: center">Centro
			de Costo</td>
		<td class="cHeadTD" rowspan="2" style="text-align: center">Acci�n</td>
	</tr>

	<tr>
		<td class="cHeadTD" style="text-align: center">Tipo</td>
		<td class="cHeadTD" style="text-align: center">N�mero</td>
	</tr>
</table>

<br>
<br>

<button type="button"
	onclick="javascript:addNewRow(<%=voucher.getId()%>, true)">Nuevo
	Movimiento</button>
<br>
<br>
<button type="button"
	onclick="javascript:confirm('Esta seguro de confirmar este comprobante?')">Grabar
	y Contabilizar</button>
&nbsp;&nbsp;&nbsp;
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/conta/voucher/VoucherManager">Volver</a>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

