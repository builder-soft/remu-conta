DROP PROCEDURE if exists pListProfile;
DELIMITER $$

CREATE PROCEDURE pListProfile()
BEGIN
	SELECT		cId 
	FROM 		tProfile;
END$$

DELIMITER ;