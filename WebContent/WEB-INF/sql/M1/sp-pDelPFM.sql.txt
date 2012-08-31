DROP PROCEDURE if exists pDelPFM;
DELIMITER $$

CREATE PROCEDURE pDelPFM(IN pId BIGINT)
BEGIN

	SELECT	cId INTO @attributeId
	FROM	tPFMAttribute
	WHERE	cPFM = pId;

	DELETE
	FROM	tPFMAttribute
	WHERE	cId = @attributeId;
	
	DELETE
	FROM	tBoard
	WHERE	cId = pId;
END$$

DELIMITER ;
