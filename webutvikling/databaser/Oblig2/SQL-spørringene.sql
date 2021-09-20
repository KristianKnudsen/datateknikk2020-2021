#A
select KundeNr, Type, Navn, Epost, Telefonnummer , Fakturaadresse.adresse as Fakturaadresse, Fakturaadresse.postnr as FakturaPostnr, Leveringsadresse.adresse as Leveringsadresse, Leveringsadresse.postnr as LeveringsPostNr from Kunde inner join Fakturaadresse on Fakturaadresse_AID = Fakturaadresse.AID inner join Leveringsadresse on Leveringsadresse_AID = Leveringsadresse.AID inner join Telefonnummer on Kunde_KundeNr = KundeNr where Type = "Bedrift";
                
#B
create or replace view KunderMedFaktura as select KundeNr, Navn, postnr, poststed, adresse from Kunde, Fakturaadresse, Utleie where KundeNr = Kunde_KundeNr and Fakturaadresse_AID = AID group by Kunde_KundeNr;

create or replace view FlestOrdre as select Kunde_KundeNr,count(*) as Ordre from Utleie group by Kunde_KundeNr having count(*) =(select max(Ordre) from (select Kunde_KundeNr,count(*) as Ordre from Utleie group by Kunde_KundeNr) Utleie);
            
select Navn, postnr, poststed, adresse from FlestOrdre inner join KunderMedFaktura on Kunde_KundeNr = KundeNr;
#C
create or replace view KundenMestPenger as select sum(-1*datediff(UtleidDato, InnlevertDato) * LeieprisDøgn + LeveringsKostnad) as "Tjent per utstyr",Kunde_KundeNr, Utstyrsmerke, Utstyrsmodell, Utstyrstype from Utleie inner join UtstyrsDetaljer on Utstyr_UtstyrsID = UtstyrsID where InnlevertDato is not null group by Kunde_KundeNr;
select * from KundenMestPenger inner join Kunde on Kunde_KundeNr = KundeNr inner join Leveringsadresse on Leveringsadresse_AID = AID where `Tjent per utstyr` = (select max(`Tjent per utstyr`) from KundenMestPenger);
#D
create or replace view VanligsteUtstyr as select Utstyr_UtstyrsID, count(*) as ganger from Utleie group by Utstyr_UtstyrsID;
select MAX(ganger) as "ganger utleid", Utstyrsmerke, Utstyrsmodell, Utstyrstype, UtstyrsKategori from VanligsteUtstyr inner join UtstyrsDetaljer on Utstyr_UtstyrsID = UtstyrsID;
#E 
select sum(-1*datediff(UtleidDato, InnlevertDato) * LeieprisDøgn + LeveringsKostnad) as "Tjent per utstyr", Utstyrsmerke, Utstyrsmodell, Utstyrstype from Utleie inner join UtstyrsDetaljer on Utstyr_UtstyrsID = UtstyrsID where InnlevertDato is not null group by UtstyrsID order by -1*datediff(UtleidDato, InnlevertDato) * LeieprisDøgn + LeveringsKostnad desc;
#F 
SELECT * FROM Utleie inner join Kundebehandler on Kundebehandler_KBID = KBID where InnlevertDato is null and Fornavn = "Hilde" and Etternavn = "Pettersen";
 
