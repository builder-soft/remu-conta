DROP PROCEDURE if exists pListPeriod;
DELIMITER $$

CREATE PROCEDURE pListPeriod()
BEGIN
	SELECT		cId
	FROM 		tPeriod;
END$$

DELIMITER ;
 