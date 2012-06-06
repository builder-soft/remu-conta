package cl.buildersoft.business.service;

import java.sql.Connection;

import cl.buildersoft.framework.beans.Agreement;

public interface AgreementService {
	public Agreement getDefaultAgreement(Connection conn, Long employee);

	public Agreement getAgreementByEmployee(Connection conn, Long employee);
}
