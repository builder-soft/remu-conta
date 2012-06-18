package cl.buildersoft.business.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.ContractType;
import cl.buildersoft.business.beans.GratificationType;
import cl.buildersoft.business.beans.Health;
import cl.buildersoft.business.beans.Horary;
import cl.buildersoft.business.beans.PaymentType;
import cl.buildersoft.business.beans.Profile;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.framework.util.BSConfig;

public class AgreementServiceImpl implements AgreementService {

	@Override
	public Agreement getDefaultAgreement(Connection conn, Long employee) {
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Agreement agreement = (Agreement) bu.get(conn, new Agreement(),
				"pGetAgreementByEmployee", employee);

		if (agreement == null) {
			agreement = new Agreement();
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
			agreement.setAccountNumber("");
						
			agreement.setHorary(getHorary(conn, bu));
			agreement.setAdditionalHealthAmount(0D);
			agreement.setSimpleLoads(0);
			agreement.setDisabilityBurdens(0);
			agreement.setMaternalLoads(0);
			agreement.setPensionary(Boolean.FALSE);

			bu.save(conn, agreement);
		}

		return agreement;
	}

	private Long getHorary(Connection conn, BSBeanUtilsSP bu) {
		List<Horary> horary = (List<Horary>) bu.list(conn, new Horary(),
				"pListHorary", null);
		return horary.get(0).getId();
	}

	private Long getPaymentType(Connection conn, BSBeanUtilsSP bu) {
		List<PaymentType> paymentType = (List<PaymentType>) bu.list(conn,
				new PaymentType(), "pListPaymentType", null);
		return paymentType.get(0).getId();

	}

	private Long getGratificationType(Connection conn, BSBeanUtilsSP bu) {
		List<GratificationType> gratificationType = (List<GratificationType>) bu
				.list(conn, new GratificationType(), "pListGratificationType",
						null);
		return gratificationType.get(0).getId();
	}

	private Long getHealth(Connection conn, BSBeanUtilsSP bu) {
		List<Health> health = (List<Health>) bu.list(conn, new Health(),
				"pListHealth");
		return health.get(0).getId();
	}

	private Long getPFM(Connection conn, BSBeanUtilsSP bu) {
		List<Profile> profiles = (List<Profile>) bu.list(conn, new Profile(),
				"pListProfile", null);
		return profiles.get(0).getId();
	}

	private Long getProfile(Connection conn, BSBeanUtilsSP bu) {
		List<Profile> profiles = (List<Profile>) bu.list(conn, new Profile(),
				"pListProfile", null);
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
		List<Agreement> agreements = (List<Agreement>) bu.list(conn, new Agreement(),
				"pGetAgreementByEmployee", prms);

		Agreement out = agreements.size() == 0 ? getDefaultAgreement(conn,
				idEmployee) : (Agreement) agreements.get(0);
		return out;
	}

}
