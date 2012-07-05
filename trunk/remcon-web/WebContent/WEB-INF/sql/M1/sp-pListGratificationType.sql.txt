DROP PROCEDURE if exists pListGratificationType;
DELIMITER $$

CREATE PROCEDURE pListGratificationType()
BEGIN
	SELECT		cId 
	FROM 		tGratificationType
	ORDER BY	cId;
END$$

DELIMITER ;
 
