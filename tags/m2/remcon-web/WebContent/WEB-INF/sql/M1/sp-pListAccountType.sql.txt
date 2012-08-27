DROP PROCEDURE if exists pListAccountType;
DELIMITER $$

CREATE PROCEDURE pListAccountType()
BEGIN
	SELECT		cId, cKey, cName 
	FROM 		tAccountType;
END$$

DELIMITER ;
 