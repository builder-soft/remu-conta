DROP PROCEDURE if exists pListExBoxSystem;
DELIMITER $$

CREATE PROCEDURE pListExBoxSystem()
BEGIN
	SELECT		cId 
	FROM 		tExBoxSystem;
END$$

DELIMITER ;