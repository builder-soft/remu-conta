DROP PROCEDURE if exists pGetAgreementByEmployee;
DELIMITER $$

CREATE PROCEDURE pGetAgreementByEmployee(IN pEmployee BIGINT)
BEGIN
	SELECT		cId 
	FROM 		tAgreement
	WHERE		cEmployee = pEmployee;

END$$

DELIMITER ;




