<%@page import="cl.buildersoft.business.beans.Document"%>
<%@page import="cl.buildersoft.business.beans.Health"%>
<%@page import="cl.buildersoft.business.beans.ExBoxSystem"%>
<%@page import="cl.buildersoft.business.beans.PFM"%>
<%@page import="cl.buildersoft.business.beans.RagreementAPV"%>
<%@page import="cl.buildersoft.business.beans.Currency"%>
<%@page import="cl.buildersoft.business.beans.APV"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%@page import="cl.buildersoft.business.beans.Agreement"%>
<%@page import="cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.BSHeadConfig"%>
<%@page import="static cl.buildersoft.framework.util.BSWeb.dateTime2String"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
	BSField[] fields = table.getFields();
	
	List<Document> listadoArchivos = (List<Document>)request.getAttribute("listadoArchivos");
	Employee employee = (Employee) request.getAttribute("Employee");

%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Documentacion del trabajador</h1>
<table>
	<tr>
		<td class="cLabel">RUT:</td>
		<td class="cData"><%=employee.getRut()%></td>
	</tr>
	<tr>
		<td class="cLabel">Empleado:</td>
		<td class="cData"><%=employee.getName() + " " + employee.getLastName1() + " "
					+ employee.getLastName2()%></td>
	</tr>
</table>
<br>
<form action="${pageContext.request.contextPath}/servlet/config/employee/DocumentEmployee" method="post" id="editForm">
	<input type="hidden" name="cId" value="<%=request.getParameter("cId") != null ? request.getParameter("cId") : request.getAttribute("cId")%>">
	<input type="hidden" id="idDocument" name="idDocument" value=""/>
	<input type="hidden" id="Method" name="Method"/>
	<table border="0" width="600px">
			<tr>
					<td>
					<table class="cList" cellpadding="0" cellspacing="0">
					<tr>
						<td class="cHeadTD" width="30%">Documento</td>
						<td class="cHeadTD" width="40%">Fecha</td>
						<td class="cHeadTD" width="30%">Tama�o</td>
						<td class="cHeadTD"></td>
					</tr>
							<%	
							if(listadoArchivos.size() == 0)
							{
							%>
							<tr>
								<td class="cDataTD_odd" colspan="3">No existen registros</td>
							</tr>
							<%
							}
							
							for(Document document : listadoArchivos)
							{											
							%>
								<tr>
									<td class="cDataTD_odd"><a href="javascript:$('#Method').val('downloadFile');$('#idDocument').val('<%=document.getId()%>');$('#editForm').submit();"><%=document.getFileName()%></a></td>
									<td class="cDataTD_odd"><%=dateTime2String(request,document.getDateTime())%></td>
									<td class="cDataTD_odd"><%=document.getSize()%> KB</td>
									<td class="cDataTD_odd"><a href="javascript:$('#Method').val('delete');$('#idDocument').val('<%=document.getId()%>');$('#editForm').submit();">Eliminar</a></td>
								<tr/>
							<%
							}
							%>
					</table>					
			</tr>		
	</table>
</form>

<form action="${pageContext.request.contextPath}/servlet/config/employee/UploadDocument" method="post" id="uploadForm" enctype='multipart/form-data'>
			<input type="hidden" name="cIdEmployee" value="<%=request.getParameter("cId") != null ? request.getParameter("cId") : request.getAttribute("cId")%>">
			<br/>
			<table border="0">
			<tr>
				<td class="cLabel">Nombre del archivo:</td>
				<td class="cInputNoFocus"><input type="text" name="desc"></td>
			</tr>
			<tr>
				<td class="cLabel">Documento:</td>
				<td class="cInputNoFocus"><input type="file" name="file1"><br></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
				<td><a href="javascript:window.location.href='${pageContext.request.contextPath}/servlet/table/LoadTable'">Volver</a></td>
			</tr>
			</table>
</form>

<br>



<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>