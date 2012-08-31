DROP PROCEDURE if exists pListCurrency;
DELIMITER $$

CREATE PROCEDURE pListCurrency()
BEGIN
	SELECT		cId, cKey, cName 
	FROM 		tCurrency;
END$$

DELIMITER ;

/*
call pGetAPVList();
*/


