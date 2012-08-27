DROP PROCEDURE if exists pSavePFM;
DELIMITER $$

CREATE PROCEDURE pSavePFM(IN pId BIGINT, IN pKey VARCHAR(15), 
	IN pName VARCHAR(200), IN pEnable BIT, IN pFactor DOUBLE, IN pSIS DOUBLE)
BEGIN

	IF EXISTS(	SELECT	cId 
				FROM	tBoard
				WHERE	cId = pId) THEN

		SELECT	cId INTO @attributeId
		FROM	tPFMAttribute
		WHERE	cPFM = pId;
		
		UPDATE	tBoard
		SET		cKey = pKey,
				cValue = pName,
				cEnable = pEnable
		WHERE	cId = pId;

		UPDATE	tPFMAttribute
		SET		cFactor = pFactor,
				cSIS = pSIS
		WHERE	cId = @attributeId;
		
	ELSE
		INSERT INTO tBoard(cType, cKey, cValue, cEnable) 
		VALUES('PFM', pKey, pName, pEnable);

		INSERT INTO tPFMAttribute(cPFM, cFactor, cSIS)
		VALUES(LAST_INSERT_ID(), pFactor, pSIS);
	END IF;
END$$

DELIMITER ;
