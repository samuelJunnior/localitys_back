INSERT INTO TB_PARAM_DISCOUNT (ID, COIN, COST, PARAM_CORRECTION_VALUE,PERC_DISCOUNT)
					   VALUES (1, 1, 123.45, 5000, 12.3);

INSERT INTO TB_STATE (ID, NAME, SIGLA, REGION, ALLOWS_DELETE, ID_PARAM_DISCOUNT ) 
			   VALUES(1, 'RIO GRANDE DO SUL', 'RS', 'SUL', 0, 1);
			   
INSERT INTO TB_STATE (ID, NAME, SIGLA, REGION, ALLOWS_DELETE, ID_PARAM_DISCOUNT ) 
			   VALUES(2, 'SANTA CATARINA', 'SC', 'SUL', 1, 1);

INSERT INTO TB_STATE (ID, NAME, SIGLA, REGION, ALLOWS_DELETE, ID_PARAM_DISCOUNT ) 
			   VALUES(3, 'PARANÁ', 'PR', 'SUL', 1,  1);