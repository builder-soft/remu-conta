DROP PROCEDURE if exists pOpenPeriod;
DELIMITER $$

CREATE PROCEDURE pOpenPeriod(IN pPeriod BIGINT)
/*
1.- se crea
2.- se abre
3.- se calcula
4.- se pueden modificar parametros para calcular de nuevo
5.- se calcula con correcciones
6.- se cierra periodo
7.- no se puede volver a abrir nuevamente ni tampoco recalcular
*/
BEGIN
	UPDATE		tPeriod
	SET			cPeriodStatus = 3
	WHERE		cPeriodStatus = 2;
	
	UPDATE		tPeriod
	SET			cPeriodStatus = 2
	WHERE		cId = pPeriod AND
				cPeriodStatus <= 3;
END$$

DELIMITER ;
 