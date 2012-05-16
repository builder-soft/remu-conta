<%@page import="cl.buildersoft.framework.beans.Account2"%>
<%@page import="cl.buildersoft.framework.beans.Board"%>
<%@page import="cl.buildersoft.framework.beans.Agreement"%>
<%@page import="cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.BSHeadConfig"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
	BSField[] fields = table.getFields();
	List<Board> listadoApv = (List<Board>)request.getAttribute("listadoApv");
	List<Board> listadoCurrency = (List<Board>)request.getAttribute("listadoCurrency");
	List<Account2> listadoApvEmp = (List<Account2>)request.getAttribute("listadoApvEmp");
	List<Board> listadoAfp = (List<Board>)request.getAttribute("listadoAfp");
	List<Board> listadoExBox = (List<Board>)request.getAttribute("listadoExBox");	
	List<Board> listadoHealth = (List<Board>)request.getAttribute("listadoHealth");	
	Agreement agreementEmp = (Agreement)request.getAttribute("agreementEmp");	
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
	
	alert("currencies seleccionadas= " + currencies);
	alert("amounts seleccionadas= " + amounts);
	alert("institutions seleccionadas= " + institutions);
}
</script>
<%
	BSHeadConfig head = (BSHeadConfig) session.getAttribute("BSHead");
	if (head != null) {
		BSScript script = head.getScript();
		BSCss css = head.getCss();
		for (String oneScript : script.getListScriptNames()) {
			out.print("<script src='" + request.getContextPath()
					+ script.getPath() + oneScript + ".js'></script>");
		}

		for (String oneCss : css.getListCssNames()) {
			out.print("<LINK rel='stylesheet' type='text/css' src='"
					+ request.getContextPath() + css.getPath() + oneCss
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
<!--config/employee/SavePrevitionalInfo-->
<div style="display: none" id="divHide">
<table id="tableHide">
					<tr id="apvHide">
								<td class="cLabel" valign='top'>APV:</td>
								<td class="cData">
								<select id="apvCurrency" name="apvCurrency">
										<%			
										for(Board bsCurrency : listadoCurrency)
										{											
										%>
											<OPTION value="<%=bsCurrency.getId()%>"><%=bsCurrency.getKey()%></OPTION>
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
										for(Board bsApv : listadoApv)
										{
										%>
											<OPTION value="<%=bsApv.getId()%>"><%=bsApv.getValue()%></OPTION>
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
<form action="${pageContext.request.contextPath}/servlet/config/employee/SavePrevitionalInfo" method="post" id="editForm">
	<input type="hidden" name="cId" value="<%=request.getParameter("cId")%>">
	<table>
			<tr>
					<td class="cLabel" valign='top'>AFP:</td>
					<td class="cData">
					<select id="afpEmp" name="afpEmp">
							<%			
							for(Board bsAfp : listadoAfp)
							{											
							%>
								<OPTION value="<%=bsAfp.getKey()%>"<%=agreementEmp.getPfm()!=null && bsAfp.getKey().equals(agreementEmp.getPfm()) ? "selected" : "" %>><%=bsAfp.getValue()%></OPTION>
							<%
							}
							%>
					</select>						
				</td>
			</tr>	
			<tr>
				<td colspan="4">
					<table id="apvs">
					<%	
					int contador = 0;
					int cantApvs = listadoApvEmp.size();
					for(Account2 bsApvEmp : listadoApvEmp){

							out.print("<script>addApv('"+bsApvEmp.getCurrency()+"','"+bsApvEmp.getInstitution() +"','"+bsApvEmp.getAmount()+"');</script>");
						
					}
					%>
					</table>
				<td>
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Caja Ex-Régimen:</td>
				<td class="cData" colspan="3">
				<select id="exBox" name="exBox">
						<%			
						for(Board bsExbox : listadoExBox)
						{											
						%>
							<OPTION value="<%=bsExbox.getKey()%>"<%=agreementEmp.getExBoxSystem() != null && bsExbox.getKey().equals(agreementEmp.getExBoxSystem()) ? "selected" : "" %>><%=bsExbox.getValue()%></OPTION>
						<%
						}
						%>
				</select>
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Isapre:</td>
				<td class="cData">
				<select id="health" name="health">
						<%			
						for(Board bsHealth : listadoHealth)
						{											
						%>
							<OPTION value="<%=bsHealth.getKey()%>"<%=agreementEmp.getHealth()!= null && bsHealth.getKey().equals(agreementEmp.getHealth()) ? "selected" : "" %>><%=bsHealth.getValue()%></OPTION>
						<%
						}
						%>
				</select>
				</td>
				<td class="cLabel" valign='top'>Plan UF:</td>
				<td class="cData">
				<input id="additionalHealthUF" name="additionalHealthUF" value="<%=agreementEmp.getAdditionalHealthUF()%>">
				</td>				
				
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Plan $:</td>
				<td class="cData">
				<input id="additionalHealthCLP" name="additionalHealthCLP" value="<%=agreementEmp.getAdditionalHealthCLP()%>">
				</td>
				<td class="cLabel" valign='top'>Plan %:</td>
				<td class="cData">
				<input id="additionalHealthPorc" name="additionalHealthPorc" value="<%=agreementEmp.getAdditionalHealthCLP()%>">
				</td>				
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Cargas simples:</td>
				<td class="cData">
				<input id="simpleLoad" id="simpleLoad" value="<%=agreementEmp.getSimpleLoads()%>">
				</td>
				<td class="cLabel" valign='top'>Cargas maternales:</td>
				<td class="cData">
					<input id="maternalLoad" name="maternalLoad" value="<%=agreementEmp.getMaternalLoads()%>">
				</td>				
			</tr>
			<tr>
				<td class="cLabel" valign='top'>Cargas invalidez:</td>
				<td class="cData" colspan="3">
					<input id="disabilityBurdens" name="disabilityBurdens" value="<%=agreementEmp.getDisabilityBurdens()%>">
				</td>
			</tr>														
			
	</table>
</form>
<input type="button" value="Aceptar"
	onclick="javascript:$('#editForm').submit();">
&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>&nbsp;&nbsp;&nbsp;
<a href="javascript:addApv()">Agregar APV</a>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeHTMLField(BSField field, HttpServletRequest request) {
		String out = "";
		BSFieldType type = field.getType();
		Object value = field.getValue();
		Integer maxlength = 0;
		String name = field.getName();
		String format = "";
		Integer size = 0;
		String afterInput = "";
		Boolean isPk = field.isPK();
		//Boolean isNew = Boolean.FALSE;
		Boolean isReadOnly = isPk ? Boolean.TRUE : field.isReadonly();
		String validationOnBlur = field.getValidationOnBlur() != null ? field
				.getValidationOnBlur() : "";

		if (isFK(field)) {
			out += getFKSelect(field);
		} else {
			if (type.equals(BSFieldType.Boolean)) {
				out += "<SELECT name='" + name + "' ";
				out += isReadOnly ? " DISABLED " : "";
				out += ">";

				out += writeOptionHTML("true", "Si", value);
				out += writeOptionHTML("false", "No", value);

				out += "</SELECT>";
			} else {
				if (type.equals(BSFieldType.String)) {
					value = value == null ? "" : value;
					maxlength = field.getLength();
					size = maxlength;
					if (size > 75) {
						size = 75;
					}
				} else if (type.equals(BSFieldType.Date)) {
					maxlength = 10;
					format = BSWeb.getFormatDate(request);
					value = BSWeb.date2String(value, format);
					size = maxlength;
					afterInput = "(formato: " + format + ")";
				} else if (type.equals(BSFieldType.Timestamp)) {
					maxlength = 16;
					format = BSWeb.getFormatDatetime(request);
					value = BSWeb.date2String(value, format);
					size = maxlength;
					afterInput = "(formato: " + format + ")";
				} else if (type.equals(BSFieldType.Double)) {
					maxlength = 15;
					format = BSWeb.getFormatNumber(request);
					value = BSWeb.number2String(value, format);
					size = maxlength;
				} else if (type.equals(BSFieldType.Integer)) {
					maxlength = 8;
					format = BSWeb.getFormatNumber(request);
					value = BSWeb.number2String(value, format);
					size = maxlength;
				} else if (type.equals(BSFieldType.Long)) {
					maxlength = 10;
					format = BSWeb.getFormatNumber(request);
					if (isPk && value == null) {
						value = "[Nuevo]";
						//isNew = Boolean.TRUE;
					} else {
						value = value == null ? "" : BSWeb.number2String(value,
								format);
					}
					size = maxlength;
				}

				out += drawInputText("text", name, maxlength, isReadOnly,
						value, size, afterInput, validationOnBlur, isPk);
			}
		}
		return out;
	}

	private String writeOptionHTML(String option, String display, Object value) {
		String out = "<OPTION value='" + option + "'";
		out += (value != null && value.toString().equals(option) ? " selected"
				: "");
		out += ">" + display + "</OPTION>";
		return out;
	}

	private Boolean isFK(BSField field) {
		Boolean out = Boolean.FALSE;
		List<Object[]> data = field.getFKData();
		out = data != null;
		return out;
	}

	private String drawInputText(String type, String name, Integer maxlength,
			Boolean isReadonly, Object value, Integer size, String afterInput,
			String validationOnBlur, Boolean isPk) {
		String out = "";

		if (isPk) {
			out += "<span class='cData'>"+value+"</span>";
		} else {
			out += "<input type='" + type + "' name='";
			out += name;
			out += "' ";
			out += "maxlength='" + maxlength + "' ";
			out += isReadonly ? "READONLY " : "";
			out += "value='" + value + "' ";
			out += "size='" + size + "px' ";
			out += "onBlur='javascript:" + validationOnBlur + "(this)'";
			out += ">&nbsp;" + afterInput;
		}
		return out;
	}

	private String getFKSelect(BSField field) {
		String name = field.getName();
		Object value = field.getValue();

		String out = "<select name='";
		out += name + "'>";
		List<Object[]> data = field.getFKData();
		for (Object[] row : data) {
			out += "<option value='" + row[0] + "' ";
			if (value != null) {
				out += value.equals(row[0]) ? " selected " : "";
			}
			out += ">" + row[1] + "</option>";
		}
		out += "</select>";
		return out;
	}%>
