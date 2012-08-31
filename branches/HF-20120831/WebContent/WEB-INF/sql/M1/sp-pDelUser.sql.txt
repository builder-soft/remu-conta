DROP PROCEDURE if exists bsframework.pDelUser;
DELIMITER $$

CREATE PROCEDURE bsframework.pDelUser(IN pId BIGINT)
BEGIN
	DELETE 
	FROM bsframework.tUser 
	WHERE cId=pId;
END$$

DELIMITER ;

