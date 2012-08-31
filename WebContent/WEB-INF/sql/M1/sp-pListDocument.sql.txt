DROP PROCEDURE if exists pListDocument;
DELIMITER $$

CREATE PROCEDURE pListDocument(IN pEmployee BIGINT)
BEGIN
	SELECT		a.cId, cEmployee, cDesc, cFileName, cFileRealName, cSize, cFileCategory, cDateTime, cContentType, b.cName AS cCategoryName 
	FROM 		tFile	AS a 
	LEFT JOIN	tFileCategory AS b ON a.cFileCategory = b.cId
	WHERE		cEmployee = pEmployee;
END$$

DELIMITER ;