DROP PROCEDURE if exists pGetEnterpriseConfig;
DELIMITER $$

CREATE PROCEDURE pGetEnterpriseConfig(IN pId BIGINT)
BEGIN
	SELECT		* 
	FROM 		tEnterpriseConfig
	WHERE		cEnterprise = pId;
END$$

DELIMITER ;

