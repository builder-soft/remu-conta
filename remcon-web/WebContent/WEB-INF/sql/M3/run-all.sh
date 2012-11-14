#!/bin/sh

if [ -n "$1" ]; then
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-conta.sql.txt

	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-conta.sql.txt
	
	
##	for i in $(ls fn*.sql.txt); do echo "Running $i..."; mysql -D$1 -u root -padmin < $i; done
##	for i in $(ls sp*.sql.txt); do echo "Running $i..."; mysql -D$1 -u root -padmin < $i; done
	
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-menu.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-conta.sql.txt

##	mysql -D$1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
else
	echo "Error, falta indicar la base de datos por parámetro..."
fi

