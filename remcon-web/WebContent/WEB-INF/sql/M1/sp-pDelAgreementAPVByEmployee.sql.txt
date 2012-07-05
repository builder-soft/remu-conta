DROP PROCEDURE if exists pDelAgreementAPVByEmployee;
DELIMITER $$

CREATE PROCEDURE pDelAgreementAPVByEmployee(IN pEmployee BIGINT)
BEGIN
	SELECT	cId INTO @agreement 
	FROM	tAgreement 
	WHERE	cEmployee = pEmployee;

	DELETE
	FROM tR_AgreementAPV
	WHERE cAgreement = @agreement;
END$$

DELIMITER ;

