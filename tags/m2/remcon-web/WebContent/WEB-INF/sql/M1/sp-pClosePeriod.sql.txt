DROP PROCEDURE if exists pClosePeriod;
DELIMITER $$

CREATE PROCEDURE pClosePeriod(IN pPeriod BIGINT)

BEGIN
	UPDATE		tPeriod
	SET			cPeriodStatus = 3
	WHERE		cId = pPeriod;
END$$

DELIMITER ;
 