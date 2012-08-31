DROP PROCEDURE if exists pSaveDiscount;
DELIMITER $$

CREATE PROCEDURE pSaveDiscount(IN pPeriod BIGINT, IN pEmployee BIGINT, 
								IN pLoanEnterprise DOUBLE, IN pLoanCompensationFund DOUBLE, 
								IN pSavingCompensationFund DOUBLE, IN pJudicialRetention DOUBLE)
BEGIN	
	DECLARE vId, vBook BIGINT DEFAULT -1;
	
	SELECT	b.cId, a.cId
	INTO	vId, vBook
	FROM	tBook AS a
	LEFT JOIN tBookDiscounts AS b ON b.cBook = a.cId
	WHERE	a.cPeriod = pPeriod AND 
			a.cEmployee = pEmployee;
	
	IF (vId > -1) THEN
		UPDATE	tBookDiscounts
		SET		cLoanEnterprise = pLoanEnterprise,
				cLoanCompensationFund = pLoanCompensationFund,
				cSavingCompensationFund = pSavingCompensationFund,
				cJudicialRetention = pJudicialRetention
		WHERE	cId = vId;
	ELSE
		INSERT INTO 
			tBookDiscounts(cBook, cLoanEnterprise, cLoanCompensationFund, 
						cSavingCompensationFund, cJudicialRetention)
		VALUES(			vBook, pLoanEnterprise, pLoanCompensationFund, 
						pSavingCompensationFund, pJudicialRetention);
		
	END IF;
END$$

DELIMITER ;
