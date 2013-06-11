<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="cl.buildersoft.framework.util.BSPaging"%>


<div class="row-fluid">
	<div class="span12">
		<div class="pagination pagination-centered"><%=write_pagination_jsp(request)%></div>
	</div>
</div>

<script type="text/javascript">
	function keyPressPaging(o, search, path) {
		var out = true;
		var key = window.event.keyCode;
		if (key >= 48 && key <= 57) {
			out = true;
		} else {
			if (key == '13') {
				var url = path + "?Page=" + o.value + "&Search=" + search;
				self.location.href = url;
				out = false;
			}
		}
		return out;
	}
</script>

<%!private String write_pagination_jsp(HttpServletRequest request) {
		String ctxPath = request.getContextPath();
		BSPaging paging = (BSPaging) request.getAttribute("Paging");
		BSTableConfig table = (BSTableConfig) request.getSession().getAttribute("BSTable");

		String uri = table.getUri();
		String out = "";
		//String search = getSearch(request);
		String search = paging.getSearchValue(request);

		//		out += "+" + search + "+";

		if (paging != null && paging.getRequiresPaging()) {
			Integer currentPage = paging.getCurrentPage();

			if (paging.getCurrentPage() > 1) {
				out += linkToPage(ctxPath, 1, "First", uri, search);
				out += linkToPage(ctxPath, paging.getCurrentPage() - 1, "Prev", uri, search);
			} else {

			}

			out += "<ul>";
			out += "<input id='CurrentPage' size='2' type='text' onkeypress='return keyPressPaging(this, \"" + search + "\", \""
					+ (ctxPath + uri) + "\");' value='" + currentPage + "'>";
			out += " de ";
			out += paging.getPageCount();

			if (paging.getCurrentPage() < paging.getPageCount()) {
				out += linkToPage(ctxPath, currentPage + 1, "Next", uri, search);
				out += linkToPage(ctxPath, paging.getPageCount(), "Last", uri, search);
			}
		}
		return out;
	}

	private String linkToPage(String ctxPath, Integer page, String type, String uri, String search) {
		String out = "<a href='" + ctxPath + uri + "?Page=" + page + "&Search=" + search + "'><img height='15px' src='" + ctxPath
				+ "/img/common/button_page" + type + ".jpg'></a>";

		return out;
	}

	/**
	private String getSearch(HttpServletRequest request) {
		String out = (String) request.getAttribute("Search");
		if (out == null) {
			out = "";
		} else {
			out = out.trim();
		}
		return out;
	}*/%>