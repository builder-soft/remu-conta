<%@page import="cl.buildersoft.business.beans.Health,cl.buildersoft.business.beans.ExBoxSystem,cl.buildersoft.business.beans.PFM"%>
<%@page import="cl.buildersoft.business.beans.RagreementAPV,cl.buildersoft.business.beans.Currency,cl.buildersoft.business.beans.APV"%>
<%@page import="cl.buildersoft.business.beans.Employee,cl.buildersoft.business.beans.Agreement,cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript,cl.buildersoft.framework.beans.BSHeadConfig,cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType,cl.buildersoft.framework.beans.BSField,cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet, cl.buildersoft.business.beans.FamilyAssignmentStretch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
	BSField[] fields = table.getFields();

	List<APV> listadoApv = (List<APV>) request.getAttribute("listadoApv");
	List<Currency> listadoCurrency = (List<Currency>) request.getAttribute("listadoCurrency");
	List<RagreementAPV> listadoApvEmp = (List<RagreementAPV>) request.getAttribute("listadoApvEmp");
	List<PFM> listadoAfp = (List<PFM>) request.getAttribute("listadoAfp");
	List<ExBoxSystem> listadoExBox = (List<ExBoxSystem>) request.getAttribute("listadoExBox");
	List<Health> listadoHealth = (List<Health>) request.getAttribute("listadoHealth");
	List<FamilyAssignmentStretch> familyAssignmentStretchs = (List<FamilyAssignmentStretch>) request.getAttribute("FamilyAssignmentStretch");

	Agreement agreementEmp = (Agreement) request.getAttribute("agreementEmp");
	Employee employee = (Employee) request.getAttribute("Employee");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<script>
function addApv(idCurrency,idApv,monto)
{

	var rowCount = $("#apvs tr").length;
	if(rowCount == 0)
		{
			cloneApv = $('#apvHide').clone();
			cloneApv.attr('id','apv'+rowCount);
			$('#apvs').append(cloneApv);
			
			$('#apv'+rowCount).find("select[id='apvCurrency']").val(idCurrency);
			$('#apv'+rowCount).find("select[id='apvInstitution']").val(idApv);
			$('#apv'+rowCount).find("input[id='apvAmount']").val(monto);			
			return;
		}
		
	
	var clon = $('#apvHide').clone();
	clon.attr('id','apv'+rowCount);
	clon.find('#eliminarHide').remove();
	newTD = "<td><a href='javascript:delApv("+rowCount+")'>Eliminar</a></td>";
	clon.append(newTD);
	apvId = rowCount == 1 ? "#"+$('#apvs tr:first').attr('id') : '#apv'+(rowCount-1);	
	$(apvId).after(clon);
	
		$('#apv'+rowCount).find("select[id='apvCurrency']").val(idCurrency);
		$('#apv'+rowCount).find("select[id='apvInstitution']").val(idApv);
		$('#apv'+rowCount).find("input[id='apvAmount']").val(monto);
}

function delApv(idApv)
{
	$('#apv'+idApv).remove();
}

function getApvSelected(){
	apvs = $("#apvs");
	currencies = [];
	amounts = [];
	institutions = [];
	apvs.find('#apvCurrency').each(function(){currencies.push($(this).val());});
	apvs.find('#apvAmount').each(function(){amounts.push($(this).val());});
	apvs.find('#apvInstitution').each(function(){institutions.push($(this).val());});
}

function changePFM(o){
	if(o.value > 1){
		document.getElementById('exBox').value = 1;
	}
}

function changeExBox(o){
	if(o.value > 1){
		document.getElementById('afpEmp').value = 1;
	}
}
</script>
<%
	BSHeadConfig head = (BSHeadConfig) session.getAttribute("BSHead");
	if (head != null) {
		BSScript script = head.getScript();
		BSCss css = head.getCss();
		for (String oneScript : script.getListScriptNames()) {
			out.print("<script src='" + request.getContextPath() + script.getPath() + oneScript + ".js'></script>");
		}

		for (String oneCss : css.getListCssNames()) {
			out.print("<LINK rel='stylesheet' type='text/css' src='" + request.getContextPath() + css.getPath() + oneCss
					+ ".css'/>");
		}
	}
%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Informacion previsional</h1>
<%
	String nextServlet = (String) request.getAttribute("Action");

	if ("insert".equalsIgnoreCase(nextServlet)) {
		nextServlet = "InsertRecord";
	} else {
		nextServlet = "UpdateRecord";
	}
%>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>

<br>


<form action="${pageContext.request.contextPath}/servlet/config/employee/SavePrevitionalInfo" method="post" id="editForm">
	<input type="hidden" name="cId" value="<%=request.getParameter("cId")%>">

	<table border="0">
		<tr>
			<td class="cLabel">Moneda cuenta 2:</td>
			<td><select name="CurrencyAccount2">
					<%
						for (Currency currency : listadoCurrency) {
					%>
					<OPTION value="<%=currency.getId()%>"
						<%=agreementEmp.getCurrencyAccount2() != null
						&& currency.getId().equals(agreementEmp.getCurrencyAccount2()) ? "selected" : ""%>><%=currency.getName()%></OPTION>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Monto Cuenta 2</td>
			<td>
			<Input type="text" name="AmountAccount2"
				onfocus="javascript:integerFocus(this);"
				onblur="javascript:integerBlur(this);"
				value="<%=BSWeb.formatInteger(request, agreementEmp.getAmountAccount2().intValue())%>">
			</td>
		</tr>
		<tr><td colspan="4">&nbsp;</td></tr>
		<tr>
			<td class="cLabel">Ahorro voluntario adicional:</td>
			<td><select name="AdditionalPFMCurrency">
					<%
						for (Currency currency : listadoCurrency) {
					%>
					<OPTION value="<%=currency.getId()%>"
						<%=agreementEmp.getAdditionalPFMCurrency() != null
						&& currency.getId().equals(agreementEmp.getAdditionalPFMCurrency()) ? "selected" : ""%>><%=currency.getName()%></OPTION>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Monto Ahorro adicional</td>
			<td><Input type="text" name="AdditionalPFMAmount"
			   value="<%=agreementEmp.getAdditionalPFMAmount()%>"></td>
		</tr>
		
		<tr><td colspan="4">&nbsp;</td></tr>
		
		<!-- 
	</table>
	<br>

	<table border="1"> -->
		<tr>
			<td class="cLabel" valign='top'>AFP:</td>
			<td class="cData"><select id="afpEmp" name="afpEmp"
				onchange="javascript:changePFM(this);">
					<%
						for (PFM bsAfp : listadoAfp) {
					%>
					<OPTION value="<%=bsAfp.getId()%>"
						<%=agreementEmp.getPfm() != null && bsAfp.getId().equals(agreementEmp.getPfm()) ? "selected" : ""%>><%=bsAfp.getName()%></OPTION>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Meses cotizados:</td>
			
			<td>
				<input name="MonthsQuoted" value="<%=agreementEmp.getMonthsQuoted()%>">
				</td>
		</tr>
		<tr>
				<td class="cLabel" valign='top'>Caja Ex-Régimen:</td>
				<td class="cData">
				<select id="exBox" name="exBox" onchange="javascript:changeExBox(this);">
						<%
							for (ExBoxSystem bsExbox : listadoExBox) {
						%>
							<OPTION value="<%=bsExbox.getId()%>"<%=agreementEmp.getExBoxSystem() != null && bsExbox.getId().equals(agreementEmp.getExBoxSystem()) ? "selected"
						: ""%>><%=bsExbox.getName()%></OPTION>
						<%
							}
						%>
				</select></td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Sistema de salud:</td>
				<td class="cData">
				<select id="health" name="health">
						<%
							for (Health bsHealth : listadoHealth) {
						%>
							<OPTION value="<%=bsHealth.getId()%>"<%=agreementEmp.getHealth() != null && bsHealth.getId().equals(agreementEmp.getHealth()) ? "selected" : ""%>><%=bsHealth.getName()%></OPTION>
						<%
							}
						%>
				</select>
				</td>
				<td colspan="2"></td>
				
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" class="cLabel">Complementario de salud (El complementario de salud solo aplica con valor mayor a 0)</td>
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Moneda :</td>
				<td class="cData">
					<select name="HealthCurrency">
							<%
								for (Currency bsCurrency : listadoCurrency) {
							%>
								<OPTION value="<%=bsCurrency.getId()%>"<%=agreementEmp.getHealthCurrency() != null
			&& bsCurrency.getId().equals(agreementEmp.getHealthCurrency()) ? "selected" : ""%>><%=bsCurrency.getName()%></OPTION>
							<%
								}
							%>
					</select>
				</td>				
				
				<td class="cLabel" valign='top'>Monto :</td>
				<td class="cData">
				<input name="HealthAmount" value="<%=agreementEmp.getHealthAmount()%>">
				</td>			
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			
			<tr>
				<td class="cLabel" valign='top'>Tramo asignación familiar:</td>
				<td class="cData" colspan="3">
								<select name="FamilyAssignmentStretch">
										<%
											for (FamilyAssignmentStretch familyAssignmentStretch : familyAssignmentStretchs) {
										%>
											<OPTION value="<%=familyAssignmentStretch.getId()%>" <%=agreementEmp.getFamilyAssignmentStretch() != null
						&& familyAssignmentStretch.getId().equals(agreementEmp.getFamilyAssignmentStretch()) ? "selected" : ""%>><%=familyAssignmentStretch.getKey()%></OPTION>
										<%
											}
										%>
								</select>
				</td>
			</tr>
			
			<tr>
				<td class="cLabel" valign='top'>Cargas simples:</td>
				<td class="cData">
				<input id="simpleLoad" name="simpleLoad" value="<%=agreementEmp.getSimpleLoads()%>">
				</td>
				<td class="cLabel" valign='top'>Cargas maternales:</td>
				<td class="cData">
					<input id="maternalLoad" name="maternalLoad" value="<%=agreementEmp.getMaternalLoads()%>">
				</td>				
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Cargas invalidez:</td>
				<td class="cData">
					<input id="disabilityBurdens" name="disabilityBurdens" value="<%=agreementEmp.getDisabilityBurdens()%>">
				</td>

			<td class="cLabel">Pensionado:</td>
			<td class="cData">
				<%
					Boolean pensionary = agreementEmp.getPensionary();
				%> <select Name="Pensionary">
					<option value="false" <%=(!pensionary ? "selected" : "")%>>No</option>
					<option value="true" <%=(pensionary ? "selected" : "")%>>Si</option>
			</select>
			</td>
		</tr>														

			<tr>
				<td colspan="4">
					<table id="apvs">
					<%
						int contador = 0;
						int cantApvs = listadoApvEmp.size();
						for (RagreementAPV bsApvEmp : listadoApvEmp) {
							out.print("<script>addApv('" + bsApvEmp.getCurrency() + "','" + bsApvEmp.getApv() + "','" + bsApvEmp.getAmount()
									+ "');</script>");
						}
					%>
					</table>
				</td>
			</tr>

			
	</table>
</form>
<br>
<button type="button"
	onclick="javascript:$('#editForm').submit();">Aceptar</button>
&nbsp;&nbsp;&nbsp;
<button type="button" onclick="javascript:addApv()">Agregar APV</button>
&nbsp;&nbsp;&nbsp;
<a class="cCancel" href="${pageContext.request.contextPath}/servlet/common/LoadTable">Cancelar</a>&nbsp;&nbsp;&nbsp;


<div style="display: none" id="divHide">
<table id="tableHide">
<tr id="apvHide">
			<td class="cLabel" valign='top'>APV:</td>
			<td class="cData">
			<select id="apvCurrency" name="apvCurrency">
					<%
					for (Currency bsCurrency : listadoCurrency) {
				%>
					<OPTION value="<%=bsCurrency.getId()%>"><%=bsCurrency.getName()%></OPTION>
				<%
					}
				%>
		</select>
		</td>
		<td class="cData"><input id="apvAmount" name="apvAmount" value="0"></td>
		<td class="cLabel" valign='top'>Institucion:</td>
		<td class="cData">
		<select id="apvInstitution" name="apvInstitution">
				<%
					for (APV bsApv : listadoApv) {
				%>
					<OPTION value="<%=bsApv.getId()%>"><%=bsApv.getName()%></OPTION>
				<%
					}
				%>	
		</select>
		</td>
		<td id="eliminarHide">
		<a href="javascript:delApv(0)">Eliminar</a>
		</td>
</tr>
</table>						
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>