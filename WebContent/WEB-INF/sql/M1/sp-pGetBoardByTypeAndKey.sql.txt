DROP PROCEDURE if exists pGetBoardByTypeAndKey;
DELIMITER $$

CREATE PROCEDURE pGetBoardByTypeAndKey(IN pType VARCHAR(15), IN pKey VARCHAR(15))
BEGIN
	SELECT		cId, cType, cKey, cValue, cEnable 
	FROM 		tBoard
	WHERE		cType = pType AND cKey = pKey
	ORDER BY	cId;
END$$

DELIMITER ;
 


