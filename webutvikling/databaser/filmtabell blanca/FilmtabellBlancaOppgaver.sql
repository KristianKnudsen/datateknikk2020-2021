#a:
select * from film where år = 1988;
#b:
select tittel, år from film where år between 1980 and 1989;
#c 
select * from film where alder < 10 and tid < 130;
#d
select tittel from film where sjanger IN ("Action", "Western");
#e
select distinct land from film order by land;
#f
select sjanger, min(tid), max(tid) from film group by sjanger;
#g
select count(fnr) from film where pris is null;
#h
select count(fnr) from film where pris < 100;
#i
select * from film where tittel like "%now";
#j
select sjanger, avg(pris) from film group by sjanger having count(fnr) > 2;
#k
select sjanger, (max(pris)-min(pris)) as differansen from film group by sjanger;
#l
select distinct land, count(fnr) as "totalt antall", count(pris) as "til salgs" from film group by land;
#m
select tittel, year(curdate())-år as "År siden utgivelse" from film where year(curdate()) - år > 60;
