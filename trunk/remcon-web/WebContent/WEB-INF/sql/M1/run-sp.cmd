cls

for %%i in (sp-*.sql.txt) do mysql -u root -t -padmin --default-character-set=utf8 < %%i
for %%i in (fn-*.sql.txt) do mysql -u root -t -padmin --default-character-set=utf8 < %%i

mysql -u root -t -padmin < testSP.sql.txt
