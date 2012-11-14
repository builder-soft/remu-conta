@echo off
IF "%1" == "" GOTO error

@echo on
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < create-conta.sql.txt

mysql -D%1 -t -u root -padmin --default-character-set=utf8 < rules-conta.sql.txt

rem for %%i in (fn-*.sql.txt) do mysql -D%1 -t -u root -padmin --default-character-set=utf8 < %%i
rem for %%i in (sp-*.sql.txt) do mysql -D%1 -t -u root -padmin --default-character-set=utf8 < %%i

rem call data-bsframework.sql.cmd %%1

mysql -D%1 -t -u root -padmin --default-character-set=utf8 < data-conta.sql.txt
mysql -D%1 -t -u root -padmin --default-character-set=utf8 < data-menu.sql.txt

rem mysql -D%1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt

@echo off
goto fin

:error
@echo off
echo No se indico nombre de la base de datos, ejecute: 
echo $ run-all remcon

:fin
@echo off
echo *** FIN ***