DROP PROCEDURE if exists pGetCurrencyByKey;
DELIMITER $$

CREATE PROCEDURE pGetCurrencyByKey(IN pKey CHAR(3))
BEGIN
	SELECT		cId, cKey, cName 
	FROM 		tCurrency
	WHERE		cKey = pKey;
END$$

DELIMITER ;