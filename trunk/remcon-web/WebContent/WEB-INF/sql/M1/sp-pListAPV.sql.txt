DROP PROCEDURE if exists pListAPV;
DELIMITER $$

CREATE PROCEDURE pListAPV()
BEGIN
	SELECT		cId, cKey, cName 
	FROM 		tAPV;
END$$

DELIMITER ;
 
