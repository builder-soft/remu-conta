<%@page import="cl.buildersoft.business.beans.FileCategory"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.EmployeeFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	List<EmployeeFile> filesEmployee = (List<EmployeeFile>)request.getAttribute("filesEmployee");
	Employee employee = (Employee) request.getAttribute("Employee");
	List<FileCategory> categories = (List<FileCategory>)request.getAttribute("Category");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Documentacion del trabajador</h1>

<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>

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
						<td class="cHeadTD" width="30%">Tamaño</td>
						<td class="cHeadTD"></td>
					</tr>
							<%
								if(filesEmployee.size() == 0)
												{
							%>
							<tr>
								<td class="cDataTD_odd" colspan="3">No existen registros</td>
							</tr>
							<%
								}
												
												for(EmployeeFile document : filesEmployee)
												{
							%>
								<tr>
									<td class="cDataTD_odd"><a href="javascript:$('#Method').val('downloadFile');$('#idDocument').val('<%=document.getId()%>');$('#editForm').submit();"><%=document.getFileName()%></a></td>
									<td class="cDataTD_odd"><%=BSWeb.dateTime2String(request,document.getDateTime())%></td>
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

<form action="${pageContext.request.contextPath}/servlet/config/employee/DocumentEmployee?Method=uploadFile" method="post" enctype='multipart/form-data'>
			<input type="hidden" name="cIdEmployee" value="<%=request.getParameter("cId") != null ? request.getParameter("cId") : request.getAttribute("cId")%>">
			<input type="hidden" name="Method" value="uploadFile">
			
			<br/>
			<table border="0">
			<tr>
				<td class="cLabel">Nombre del archivo:</td>
				<td><input type="text" name="desc"></td>
			</tr>
			<tr>
				
				<td class="cLabel">Categoría del archivo:</td>
				<td>
				<select name="cCategory">
					<%
						for (FileCategory category : categories) {
					%>
					<option value="<%=category.getId()%>"><%=category.getName()%></option>
					<%
						}
					%>
			</select>
				</td>
				
			</tr>
			<tr>
				<td class="cLabel">Documento:</td>
				<td colspan="3"><input type="file" name="file1"></td>
			</tr>
						
			<tr>
				<td><input type="submit"></td>
				<td colspan="3"><a href="javascript:window.location.href='${pageContext.request.contextPath}/servlet/table/LoadTable'">Volver</a></td>
			</tr>
			</table>
</form>

<br>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
