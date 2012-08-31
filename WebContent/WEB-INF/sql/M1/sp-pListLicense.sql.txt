DROP PROCEDURE if exists pListLicense;
DELIMITER $$

CREATE PROCEDURE pListLicense(IN pEmployee BIGINT)
BEGIN
	SELECT a.cId, a.cFrom, a.cTo, c.cName AS cLicenseCauseName, 32 AS cDays, d.cFileName
	FROM tLicense AS a
	LEFT JOIN tFile AS b ON a.cFile = b.cId
	LEFT JOIN tLicenseCause AS c ON a.cLicenseCause = c.cId
	LEFT JOIN tFile AS d ON a.cFile = d.cId
	WHERE a.cEmployee = pEmployee;
	
END$$

DELIMITER ;
 