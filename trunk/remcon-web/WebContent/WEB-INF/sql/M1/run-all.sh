#!/bin/sh

if [ -n "$1" ]; then
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < erase-deprecated-object.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-bsframework.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < create-remcon.sql.txt

	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-bsframework.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < rules-remcon.sql.txt
	
   #for %%i in (fn-*.sql.txt) do mysql -D%1 -t -u root -padmin --default-character-set=utf8 < %%i
	
	for i in $(ls fn*.sql.txt); do mysql -D$1 -u root -padmin < $i; done
   #for %%i in (sp-*.sql.txt) do mysql -D%1 -t -u root -padmin --default-character-set=utf8 < %%i
	for i in $(ls sp*.sql.txt); do mysql -D$1 -u root -padmin < $i; done
	
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-bsframework.sql.txt
#	~/workspace-bs/remcon-web/WebContent/WEB-INF/sql/M1/data-bsframework.sh
	
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-remcon.sql.txt
	mysql -D$1 -t -u root -padmin --default-character-set=utf8 < data-menu.sql.txt

	mysql -D$1 -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
	
	
else
	echo "error"
fi

