DROP PROCEDURE if exists pListContractType;
DELIMITER $$

CREATE PROCEDURE pListContractType()
BEGIN
	SELECT		cId 
	FROM 		tContractType
	ORDER BY	cId;

END$$
