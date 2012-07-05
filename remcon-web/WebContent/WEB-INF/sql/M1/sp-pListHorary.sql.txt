DROP PROCEDURE if exists pListHorary;
DELIMITER $$

CREATE PROCEDURE pListHorary()
BEGIN
	SELECT		cId, cName, cDetail
	FROM 		tHorary
	ORDER BY	cId;
END$$

DELIMITER ;
 
