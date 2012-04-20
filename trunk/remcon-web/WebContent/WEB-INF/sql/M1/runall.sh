clear
echo 'borrando...'
mysql -u root -padmin < delete-tables.sql.txt
echo 'creando tablas...'
mysql -u root -padmin < create-bsframework.sql.txt
mysql -u root -padmin < create-bscommon.sql.txt
mysql -u root -padmin < create-remu.sql.txt
echo 'creando indices...'
mysql -u root -padmin < rules-bscommon.sql.txt
mysql -u root -padmin < rules-bsframework.sql.txt
mysql -u root -padmin < rules-remu.sql.txt
echo 'creando sp....'
for i in $(ls sp*.sql.txt); do mysql -u root -padmin < $i; done 
echo 'creando datos basicos'
for i in $(ls data*.sql.txt); do mysql -u root -padmin < $i; done
echo 'fin'
