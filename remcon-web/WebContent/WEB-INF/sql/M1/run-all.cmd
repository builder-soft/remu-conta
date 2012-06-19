@echo on

mysql -t -u root -padmin --default-character-set=utf8 < erase-tables.sql.txt
mysql -t -u root -padmin --default-character-set=utf8 < erase-deprecated-object.sql.txt
mysql -t -u root -padmin --default-character-set=utf8 < create-bsframework.sql.txt
mysql -t -u root -padmin --default-character-set=utf8 < create-remcon.sql.txt

mysql -t -u root -padmin --default-character-set=utf8 < rules-bsframework.sql.txt
mysql -t -u root -padmin --default-character-set=utf8 < rules-remcon.sql.txt

for %%i in (fn-*.sql.txt) do mysql -t -u root -padmin --default-character-set=utf8 < %%i
for %%i in (sp-*.sql.txt) do mysql -t -u root -padmin --default-character-set=utf8 < %%i

mysql -t -u root -padmin --default-character-set=utf8 < data-bsframework.sql.txt
mysql -t -u root -padmin --default-character-set=utf8 < data-remcon.sql.txt
mysql -t -u root -padmin --default-character-set=utf8 < data-menu.sql.txt

mysql -t -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
