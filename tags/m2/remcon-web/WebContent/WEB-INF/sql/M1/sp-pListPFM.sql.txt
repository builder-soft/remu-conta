DROP PROCEDURE if exists pListPFM;
DELIMITER $$

CREATE PROCEDURE pListPFM()
BEGIN
	SELECT		cId, cKey, cName, cFactor, cSIS 
	FROM 		tPFM;
END$$

DELIMITER ;
 