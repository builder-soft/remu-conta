DROP PROCEDURE if exists pListPaymentType;
DELIMITER $$

CREATE PROCEDURE pListPaymentType()
BEGIN
	SELECT		 cId, cKey, cName 
	FROM 		tPaymentType
	ORDER BY	cId;
END$$

DELIMITER ;
