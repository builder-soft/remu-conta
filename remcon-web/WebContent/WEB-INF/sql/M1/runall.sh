clear
echo 'borrando...'
mysql -u root -p3479564..Mysql < erase-tables.sql.txt
mysql -u root -p3479564..Mysql < erase-deprecated-object.sql.txt
echo 'creando tablas...'
mysql -u root -p3479564..Mysql < create-bsframework.sql.txt
mysql -u root -p3479564..Mysql < create-remcon.sql.txt
echo 'creando indices...'
mysql -u root -p3479564..Mysql < rules-bsframework.sql.txt
mysql -u root -p3479564..Mysql < rules-remcon.sql.txt
echo 'creando fn....'
for i in $(ls fn*.sql.txt); do mysql -u root -p3479564..Mysql < $i; done 
echo 'creando sp....'
for i in $(ls sp*.sql.txt); do mysql -u root -p3479564..Mysql < $i; done 
echo 'creando datos basicos'
mysql -u root -p3479564..Mysql < data-bsframework.sql.txt
mysql -u root -p3479564..Mysql < data-remcon.sql.txt
mysql -u root -p3479564..Mysql < data-menu.sql.txt
echo 'Pruebas'
mysql -u root -t -p3479564..Mysql < testSP.sql.txt
echo 'fin'
