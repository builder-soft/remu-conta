package cl.buildersoft.web.servlet.config.employee;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.Board;
import cl.buildersoft.framework.beans.ContractType;
import cl.buildersoft.framework.beans.GratificationType;
import cl.buildersoft.framework.beans.Horary;
import cl.buildersoft.framework.beans.Profile;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.framework.util.BSConfig;

public class AgreementServiceImpl implements AgreementService {

	@Override
	public Agreement getDefaultAgreement(Connection conn, Long employee) {
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Agreement agreement = new Agreement();
		agreement.setEmployee(employee);
		agreement.setEndContract(new Date());
		agreement.setFeeding(0D);
		agreement.setMobilization(0D);
		agreement.setSalaryRoot(getSalaryRoot(conn));
		agreement.setStartContract(new Date());
		agreement.setContractType(getContractType(conn, bu));
		agreement.setProfile(getProfile(conn, bu));
		agreement.setPfm(getPFM(conn, bu));
		agreement.setHealth(getHealth(conn, bu));
		agreement.setGratificationType(getGratificationType(conn, bu));
		agreement.setPaymentType(getPaymentType(conn, bu));
		agreement.setHorary(getHorary(conn, bu));
		agreement.setAdditionalHealthCLP(0D);
		agreement.setAdditionalHealthUF(0D);
		agreement.setSimpleLoads(0);
		agreement.setDisabilityBurdens(0);
		agreement.setMaternalLoads(0);

		bu.save(conn, agreement);

		return agreement;
	}

	private Long getHorary(Connection conn, BSBeanUtilsSP bu) {
		List<Horary> horary= (List<Horary>) bu.list(conn,
				new Horary(), "pListHorary", null);
		return horary.get(0).getId();
	}

	private Long getPaymentType(Connection conn, BSBeanUtilsSP bu) {
		List<Board> paymentType = (List<Board>) bu.list(conn,
				new Board(), "pListBoardByType", "PAYMENT_TYPE");
		return paymentType.get(0).getId();
		
	}

	private Long getGratificationType(Connection conn, BSBeanUtilsSP bu) {
		List<GratificationType> gratificationType = (List<GratificationType>) bu.list(conn,
				new GratificationType(), "pListGratificationType", null);
		return gratificationType.get(0).getId();
	}

	private Long getHealth(Connection conn, BSBeanUtilsSP bu) {
		List<Board> health = (List<Board>) bu.list(conn,
				new Board(), "pListBoardByType", "HEALTH");
		return health.get(0).getId();
	}

	private Long getPFM(Connection conn, BSBeanUtilsSP bu) {
		List<Profile> profiles = (List<Profile>) bu.list(conn,
				new Profile(), "pListProfile", null);
		return profiles.get(0).getId();
	}

	private Long getProfile(Connection conn, BSBeanUtilsSP bu) {
		List<Profile> profiles = (List<Profile>) bu.list(conn,
				new Profile(), "pListProfile", null);
		return profiles.get(0).getId();
	}

	private Long getContractType(Connection conn, BSBeanUtilsSP bu) {
		List<ContractType> contractTypes = (List<ContractType>) bu.list(conn,
				new ContractType(), "pListContractType", null);
		return contractTypes.get(0).getId();
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

		Agreement out = agreements.size() == 0 ? getDefaultAgreement(conn,
				idEmployee) : (Agreement) agreements.get(0);
		return out;
	}

}
