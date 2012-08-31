DROP PROCEDURE if exists pSaveAssetOrDiscount;
DELIMITER $$

CREATE PROCEDURE pSaveAssetOrDiscount(IN pPeriod BIGINT, IN pEmployee BIGINT, IN pType BIGINT,
								IN pField VARCHAR(4), IN pValue DOUBLE)
BEGIN	
	DECLARE vId, vBook BIGINT DEFAULT -1;
	DECLARE vTableName NVARCHAR(15);
	
	SELECT	b.cId, a.cId
	INTO	vId, vBook
	FROM	tBook AS a
	LEFT JOIN tBookAssets AS b ON b.cBook = a.cId
	WHERE	a.cPeriod = pPeriod AND 
			a.cEmployee = pEmployee;
	
	IF (vId > -1) THEN
		IF(pType = 1) THEN
			SET vTableName = "tBookAssets";
		ELSE
			SET vTableName = "tBookDiscounts";
		END IF;
	
		SET @sql = CONCAT(	'UPDATE ', vTableName, ' ',
							'SET ', pField, '=', pValue, ' ',
							'WHERE cId = ', vId);
/*
select @sql;
*/

		PREPARE statement FROM @sql;
		EXECUTE statement;
		DEALLOCATE PREPARE statement;

	END IF;
END$$

DELIMITER ;
