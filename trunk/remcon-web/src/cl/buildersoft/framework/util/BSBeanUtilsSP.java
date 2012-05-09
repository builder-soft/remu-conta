package cl.buildersoft.framework.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;

public class BSBeanUtilsSP extends BSBeanUtils {
	public List<? extends BSBean> list(Connection conn, BSBean bean, String spName,
			List<Object> params) {

		BSmySQL mysql = new BSmySQL();
		ResultSet rs = mysql.callSingleSP(conn, spName, params);

		List<BSBean> out = new ArrayList<BSBean>();
		Long id = null;
		try {
			while (rs.next()) {
				id = rs.getLong(1);
				BSBean object = bean.getClass().newInstance();
				object.setId(id);
				super.search(conn, object);
				out.add((BSBean) object);
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("", e.getMessage());
		} catch (Exception e) {
			throw new BSProgrammerException("", e.getMessage());
		}

		return out;
	}
}
