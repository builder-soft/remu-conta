DROP PROCEDURE if exists pListFamilyAssignmentStretch;
DELIMITER $$

CREATE PROCEDURE pListFamilyAssignmentStretch()
BEGIN
	SELECT		cId 
	FROM 		tFamilyAssignmentStretch
	ORDER BY	cId;
END$$

DELIMITER ;
 


