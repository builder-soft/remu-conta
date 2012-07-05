DROP PROCEDURE if exists pListHealth;
DELIMITER $$

CREATE PROCEDURE pListHealth()
BEGIN
	SELECT		cId 
	FROM 		tHealth;
END$$

DELIMITER ;