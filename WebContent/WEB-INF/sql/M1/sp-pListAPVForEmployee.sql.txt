DROP PROCEDURE if exists pListAPVForEmployee;
DELIMITER $$

CREATE PROCEDURE pListAPVForEmployee(IN pEmployee BIGINT)
BEGIN
	SELECT	cId INTO @agreement 
	FROM	tAgreement 
	WHERE	cEmployee = pEmployee;

	SELECT		cAgreement, cAPV, cCurrency, cAmount
	FROM 		tR_AgreementAPV
	WHERE		cAgreement = @agreement
	ORDER BY	cCurrency, cAPV;
END$$

DELIMITER ;