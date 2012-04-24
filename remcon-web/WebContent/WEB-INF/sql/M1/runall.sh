clear
echo 'borrando...'
mysql -u root -padmin < erase-tables.sql.txt
echo 'creando tablas...'
mysql -u root -padmin < create-bsframework.sql.txt
mysql -u root -padmin < create-remcon.sql.txt
echo 'creando indices...'
mysql -u root -padmin < rules-bsframework.sql.txt
mysql -u root -padmin < rules-remcon.sql.txt
echo 'creando sp....'
for i in $(ls sp*.sql.txt); do mysql -u root -padmin < $i; done 
echo 'creando datos basicos'
mysql -u root -padmin < data-bsframework.sql.txt
mysql -u root -padmin < data-remcon.sql.txt
mysql -u root -padmin < data-menu.sql.txt
echo 'fin'
