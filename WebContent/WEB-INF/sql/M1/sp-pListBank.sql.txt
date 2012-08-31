DROP PROCEDURE if exists pListBank;
DELIMITER $$

CREATE PROCEDURE pListBank()
BEGIN
	SELECT		cId 
	FROM 		tBank
	ORDER BY	cId;

END$$
