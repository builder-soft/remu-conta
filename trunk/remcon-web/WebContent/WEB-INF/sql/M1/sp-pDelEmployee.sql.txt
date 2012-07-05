DROP PROCEDURE if exists pDelEmployee;
DELIMITER $$

CREATE PROCEDURE pDelEmployee(IN pEmployee BIGINT)
BEGIN
	SELECT	cId INTO @agreement 
	FROM	tAgreement 
	WHERE	cEmployee = pEmployee;

	DELETE
	FROM 		tR_AgreementAPV
	WHERE		cAgreement = @agreement;
	
	DELETE
	FROM 		tAgreement
	WHERE		cEmployee = pEmployee;

	DELETE
	FROM 		tEmployee
	WHERE		cId = pEmployee;
END$$

DELIMITER ;
 
