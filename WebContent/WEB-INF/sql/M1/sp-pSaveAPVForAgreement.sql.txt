DROP PROCEDURE if exists pSaveAPVForAgreement;
DELIMITER $$

CREATE PROCEDURE pSaveAPVForAgreement(IN pAgreement BIGINT, IN pAPV BIGINT,
	IN pCurrency BIGINT, IN pAmount DOUBLE)
BEGIN
	
	IF EXISTS(	SELECT	cAgreement 
				FROM	tR_AgreementAPV 
				WHERE	cAgreement = pAgreement AND 
						cAPV = pAPV AND
						cCurrency = pCurrency) THEN
						
		UPDATE	tR_AgreementAPV 
		SET		cAmount = pAmount
		WHERE	cAgreement = pAgreement AND 
				cAPV = pAPV AND 
				cCurrency = pCurrency;
	ELSE
		INSERT INTO tR_AgreementAPV(cAgreement, cAPV, cCurrency, cAmount)
		VALUES(pAgreement, pAPV, pCurrency, pAmount);
	END IF;
END$$

DELIMITER ;
