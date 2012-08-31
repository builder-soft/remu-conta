DROP PROCEDURE if exists pDelDocument;
DELIMITER $$

CREATE PROCEDURE pDelDocument(IN pDocument BIGINT)
BEGIN
	DELETE
	FROM 		tFile
	WHERE		cId = pDocument;
END$$

DELIMITER ;
 
