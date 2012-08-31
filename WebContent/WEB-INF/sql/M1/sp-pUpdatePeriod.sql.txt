DROP PROCEDURE if exists pUpdatePeriod;
DELIMITER $$

/***********************
Actualiza la tabla tPeriod si no existe el registro.
*/
CREATE PROCEDURE pUpdatePeriod(IN pId BIGINT)
BEGIN
	DECLARE vCurrentUF, vOvertimeFactor, vGratificationFactor, vMinSalary,
			vLimitIPS, vLimitInsurance, vLimitHealth, vUTM DOUBLE DEFAULT 0;
	DECLARE vDaysForYear INTEGER DEFAULT 0;
		
	SET vCurrentUF =			(SELECT cValue FROM tParameter WHERE cKey = 'CURRENT_UF');
	SET vOvertimeFactor =		(SELECT cValue FROM tParameter WHERE cKey = 'OVERTIME_FACTOR');
	SET vGratificationFactor =	(SELECT cValue FROM tParameter WHERE cKey = 'GRATIFICATION_FACTOR');
	SET vMinSalary =			(SELECT cValue FROM tParameter WHERE cKey = 'BASE_SALARY');
	SET vLimitIPS =				(SELECT cValue FROM tParameter WHERE cKey = 'LIMIT_IPS');
	SET vLimitInsurance =		(SELECT cValue FROM tParameter WHERE cKey = 'LIMIT_INSURANCE');
	SET vLimitHealth =			(SELECT cValue FROM tParameter WHERE cKey = 'LIMIT_HEALTH');
	SET vUTM =					(SELECT cValue FROM tParameter WHERE cKey = 'UTM');
	SET vDaysForYear =			(SELECT cValue FROM tParameter WHERE cKey = 'DAYS_FOR_YEAR');

	UPDATE	tPeriod
	SET		cUF = vCurrentUF,
			cOvertimeFactor = vOvertimeFactor,
			cGratificationFactor = vGratificationFactor,
			cMinSalary = vMinSalary,
			cLimitGratification = vMinSalary * vGratificationFactor,
			cLImitIPS = vLimitIPS,
			cLimitInsurance = vLimitInsurance,
			cLimitHealth = vLimitHealth,
			cUTM = vUTM,
			cDaysForYear = vDaysForYear 
	WHERE	cId = pId AND 
			cPeriodStatus < 3;

END$$

DELIMITER ;
