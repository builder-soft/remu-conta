@echo off
rem borrar tablas OK
rem crear tablas OK 
rem crear vistas, constrains e indices OK
rem crear procedimientos almacenados OK
rem cargar datos escenciales OK
rem crear menu para todos los módulos
rem cargar datos para prueba
@echo on

cls
mysql -u root -padmin < erase-tables.sql.txt
mysql -u root -padmin < create-bsframework.sql.txt
mysql -u root -padmin < create-remcon.sql.txt

mysql -u root -padmin < rules-bsframework.sql.txt
mysql -u root -padmin < rules-remcon.sql.txt

mysql -u root -padmin < data-bsframework.sql.txt
mysql -u root -padmin < data-remcon.sql.txt
mysql -u root -padmin < data-menu.sql.txt

for %%i in (sp-*.sql.txt) do mysql -u root -t -padmin < %%i

mysql -u root -t -padmin < testSP.sql.txt
