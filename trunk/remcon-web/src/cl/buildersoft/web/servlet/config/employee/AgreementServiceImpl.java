package cl.buildersoft.web.servlet.config.employee;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.framework.util.BSConfig;

public class AgreementServiceImpl implements AgreementService {

	@Override
	public Agreement getDefaultAgreement(Connection conn, Long employee) {
		Agreement agreement = new Agreement();
		agreement.setEmployee(employee);
		agreement.setEndContract(new Date());
		agreement.setFeeding(0D);
		agreement.setMobilization(0D);
		agreement.setSalaryRoot(getSalaryRoot(conn));
		agreement.setStartContract(new Date());
		agreement.setContractType(1L);
		agreement.setProfile(1L);
		agreement.setPfm(34L);
		agreement.setHealth(40L);
		agreement.setGratificationType(2L);
		agreement.setPaymentType(7L);
		agreement.setHorary(1L);
		agreement.setAdditionalHealthCLP(0D);
		agreement.setAdditionalHealthUF(0D);
		agreement.setSimpleLoads(0);
		agreement.setDisabilityBurdens(0);
		agreement.setMaternalLoads(0);

		BSBeanUtilsSP bu = new BSBeanUtilsSP();
		bu.save(conn, agreement);

		return agreement;
	}

	private Double getSalaryRoot(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getDouble(conn, "BASE_SALARY");
	}

	@Override
	public Agreement getAgreementByEmployee(Connection conn, Long idEmployee) {
		List<Object> prms = new ArrayList<Object>();
		BSBeanUtilsSP bu = new BSBeanUtilsSP();
		prms.add(idEmployee);
		List<BSBean> agreements = (List<BSBean>) bu.list(conn, new Agreement(),
				"pGetAgreementByEmployee", prms);

		Agreement out = agreements.size() == 0 ? null : (Agreement) agreements
				.get(0);
		return out;
	}

}
