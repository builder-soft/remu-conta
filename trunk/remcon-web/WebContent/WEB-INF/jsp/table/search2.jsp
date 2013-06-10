<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="cl.buildersoft.framework.util.BSPaging"%>


<div class="row-fluid">
	<div class="span4 offset8 text-right">
		<!-- Busqueda:-->

		<form class="form-search">
			<div class="input-append">
				<%=write_input_field_for_search(request)%>
			</div>
		</form>

<script type="text/javascript">
	function keyPressSearch(o, path) {alert(o + "," + path);
		var out = true;

		var key = window.event.keyCode;
		/**
		if (key >= 48 && key <= 57) {
			out = true;
		}
		 */
		if (key == '13') {
			var url = path + "?Search=" + o.value + "&Page=" + $("#CurrentPage").val();
			//			alert(url);
			self.location.href = url;
			out = false;
		}
		return out;
	}
//-->
</script>
	</div>
</div>

<%!private String write_input_field_for_search(HttpServletRequest request) {
		BSTableConfig table = (BSTableConfig) request.getSession().getAttribute("BSTable");
		String uri = table.getUri();
		String ctxPath = request.getContextPath();

		String path = ctxPath + uri;

		//	 BSPaging paging = (BSPaging) request.getAttribute("Paging");

		String out = "<input name='Search' size='30' maxlength='50' type='search' placeholder='busqueda...' onkeypress='return keyPressSearch(this, \""
				+ path + "\");' value='" + request.getAttribute("Search") + "'>";

		out = "<input type='search' name='Search' placeholder='busqueda...' id='searchInputText' class='search-query' value='" + request.getAttribute("Search") + "'>";
		out += "<button type='button' onclick='javascript:keyPressSearch($(\"#searchInputText\"),\"" + path + "\");' class='btn'>Buscar</button>";

		return out;
	}%>