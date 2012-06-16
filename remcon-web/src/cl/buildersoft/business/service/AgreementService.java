package cl.buildersoft.business.service;

import java.sql.Connection;

import cl.buildersoft.business.beans.Agreement;

public interface AgreementService {
	public Agreement getDefaultAgreement(Connection conn, Long employee);

	public Agreement getAgreementByEmployee(Connection conn, Long employee);
}
