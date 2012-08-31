DROP PROCEDURE IF EXISTS pGetHolidayByEmployee;

DELIMITER $$

CREATE PROCEDURE pGetHolidayByEmployee(IN vEmployee BIGINT)
/* 
Retorna cuantos días de vacaciones se ha tomado el empleado, sea normales o progresivos.
*/
BEGIN
	SELECT	IFNULL(SUM(cNormal),0) AS cTakenNormal,
			IFNULL(SUM(cCreeping),0) AS cTakenCreeping
	FROM	tHoliday
	WHERE	cEmployee = vEmployee;
	
END$$


DELIMITER ;

