@echo off
rem borrar tablas OK
rem crear tablas OK 
rem crear vistas, constrains e indices OK
rem crear procedimientos almacenados OK
rem cargar datos escenciales OK
rem crear menu para todos los módulos
rem cargar datos para prueba

cls
@echo on

mysql -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
mysql -u root -padmin --default-character-set=utf8 < create-bsframework.sql.txt
mysql -u root -padmin --default-character-set=utf8 < create-remcon.sql.txt

mysql -u root -padmin --default-character-set=utf8 < rules-bsframework.sql.txt
mysql -u root -padmin --default-character-set=utf8 < rules-remcon.sql.txt

for %%i in (fn-*.sql.txt) do mysql -u root -padmin --default-character-set=utf8 < %%i
for %%i in (sp-*.sql.txt) do mysql -u root -t -padmin --default-character-set=utf8 < %%i

mysql -u root -padmin --default-character-set=utf8 < data-bsframework.sql.txt
mysql -u root -padmin --default-character-set=utf8 < data-remcon.sql.txt
mysql -u root -padmin --default-character-set=utf8 < data-menu.sql.txt


mysql -u root -t -padmin --default-character-set=utf8 < testSP.sql.txt
