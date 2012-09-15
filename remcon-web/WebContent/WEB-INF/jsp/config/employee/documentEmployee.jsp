<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="cl.buildersoft.business.beans.FileCategory"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>

<%
	ResultSet filesEmployee = (ResultSet)request.getAttribute("filesEmployee");
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
		 </form>
		<table class="cList" cellpadding="0" cellspacing="0">
		<tr>
			<td class="cHeadTD">Archivo</td>
			<td class="cHeadTD">Descripción</td>
			<td class="cHeadTD">Fecha</td>
			<td class="cHeadTD">Categoría</td>
			<td class="cHeadTD">Tamaño</td>
			<td class="cHeadTD">Acción</td>
		</tr>
		<%
		Boolean haveRecords = Boolean.FALSE;
		
		String color = "";
		Integer rowCount = 0;
		while (filesEmployee.next()) {
			haveRecords = Boolean.TRUE;
			color = rowCount++ % 2 != 0 ? "cDataTD" : "cDataTD_odd";
		%>
			<tr>
				<td class='<%=color%>'><a href="javascript:$('#Method').val('downloadFile');$('#idDocument').val('<%=filesEmployee.getLong("cID")%>');$('#editForm').submit();"><%=filesEmployee.getString("cFileName")%></a></td>
				<td class='<%=color%>'><%=filesEmployee.getString("cDesc")%></td>
				<td class='<%=color%>'><%=BSDateTimeUtil.dateTime2String(request,filesEmployee.getTimestamp("cDateTime"))%></td>
				<td class='<%=color%>'><%=filesEmployee.getString("cCategoryName")%></td>
				<td class='<%=color%>'><%=filesEmployee.getLong("cSize")%>kb</td>
				<td class='<%=color%>'><a href="javascript:$('#Method').val('delete');$('#idDocument').val('<%=filesEmployee.getLong("cID")%>');$('#editForm').submit();">Eliminar</a></td>
			<tr/>
<%
		}

		Connection conn = (Connection) request.getAttribute("Conn");
		new BSmySQL().closeConnection(conn);

		if(!haveRecords){
%>
			<tr>
				<td class="cDataTD" colspan="6">No existen registros</td>
			</tr>
<%
		}						
%>
		</table>					
 


<form action="${pageContext.request.contextPath}/servlet/config/employee/DocumentEmployee?Method=uploadFile" method="post" enctype='multipart/form-data'>
			<input type="hidden" name="cId" value="<%=request.getParameter("cId") != null ? request.getParameter("cId") : request.getAttribute("cId")%>">
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
				<td><button type="submit">Aceptar</button></td>
				<td colspan="3"><a class="cCancel" href="javascript:window.location.href='${pageContext.request.contextPath}/servlet/common/LoadTable'">Cancelar</a></td>
			</tr>
			</table>
</form>

<br>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
