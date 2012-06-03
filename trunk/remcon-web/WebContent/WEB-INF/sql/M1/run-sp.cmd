cls

for %%i in (fn-*.sql.txt) do mysql -u root -t -padmin --default-character-set=utf8 < %%i
for %%i in (sp-*.sql.txt) do mysql -u root -t -padmin --default-character-set=utf8 < %%i

mysql -u root -padmin --default-character-set=utf8 -t < testSP.sql.txt
