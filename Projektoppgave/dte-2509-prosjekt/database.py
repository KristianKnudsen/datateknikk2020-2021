import mysql.connector


class Database:

    def __init__(self) -> None:
        dbconfig = {'host': 'kark.uit.no',
                    'user': 'stud_v21_waagan',
                    'password': '4SvanteSofie',
                    'database': 'stud_v21_waagan', }
        self.configuration = dbconfig

    def __enter__(self) -> 'cursor':
        self.conn = mysql.connector.connect(**self.configuration)
        self.cursor = self.conn.cursor(prepared=True)
        self.cursor = self.conn.cursor(buffered=True)
        return self

    def __exit__(self, exc_type, exc_val, exc_trace) -> None:
        self.conn.commit()
        self.cursor.close()
        self.conn.close()

    def vis_alle_brukere(self):
        try:
            self.cursor.execute("select * from bruker")
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_alle_brukere_by_treff(self):
        try:
            sql1 = '''select blogginnlegg.blogg_bruker_id, bruker.alias as bruker_alias, sum(blogginnlegg.treff) as bruker_treff 
            from blogginnlegg join bruker on bruker.id = blogginnlegg.blogg_bruker_id 
            group by blogg_bruker_id order by bruker_treff desc'''
            self.cursor.execute(sql1)
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return result

    def vis_bruker(self, id):
        try:
            self.cursor.execute("select * from bruker where id=(%s)", (id,))
            result = self.cursor.fetchone()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_alle_blogginnlegg(self):
        try:
            self.cursor.execute("select * from blogginnlegg order by dato_tidspunkt_opprettet desc LIMIT 15;")
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_blogginnlegg_med_alias(self, id):
        try:
            self.cursor.execute("select blogginnlegg.id, blogginnlegg.tittel, blogginnlegg.tekst, blogginnlegg.dato_tidspunkt_opprettet, blogginnlegg.treff, blogginnlegg.blogg_id, blogginnlegg.blogg_bruker_id, bruker.alias as bruker_alias from blogginnlegg join bruker on bruker.id=blogginnlegg.blogg_bruker_id where blogginnlegg.id=(%s)", (id,))
            result = self.cursor.fetchone()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_alle_blogginnlegg_til_blogg(self, id):
        try:
            self.cursor.execute("select * from blogginnlegg where blogg_id=(%s) order by dato_tidspunkt_opprettet desc", (id,))
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_blogginnlegg(self, id):
        try:
            self.cursor.execute("select * from blogginnlegg where id=(%s)", (id,))
            result = self.cursor.fetchone()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_alle_kommentarer(self, id):
        try:
            self.cursor.execute("select * from kommentar where blogginnlegg_id=(%s) order by dato_tid_opprettet asc", (id,))
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_kommentar(self, id):
        try:
            self.cursor.execute("select * from kommentar where id=(%s)", (id,))
            result = self.cursor.fetchone()
        except mysql.connector.Error as err:
                print(err)
        return result

    def antall_kommentarer(self, id):
        try:
            self.cursor.execute("select count(kommentar.id) as antKom from kommentar where kommentar.blogginnlegg_id=(%s)", (id,))
            return self.cursor.fetchone()
        except mysql.connector.Error as err:
                print(err)

    def oppdater_treff(self, id):
        try:
            self.cursor.execute("update blogginnlegg set treff = treff + 1 where id=(%s)", (id,))
        except mysql.connector.Error as err:
                print(err)

    def vis_alle_blogger_til_bruker(self, id):
        try:
            self.cursor.execute("select * from blogg where bruker_id=(%s)", (id,))
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_blogg_med_id(self, id):
        self.cursor.execute("SELECT * FROM blogg where id=(%s)", (id,))
        resultat = self.cursor.fetchone()
        return resultat

    def vis_blogg_med_alias(self, id):
        try:
            self.cursor.execute("SELECT blogg.id, blogg.blogg_tittel, blogg.bruker_id, bruker.alias as bruker_alias from blogg join bruker on bruker.id=blogg.bruker_id where blogg.id=(%s)", (id,))
            result = self.cursor.fetchone()
        except mysql.connector.Error as err:
                print(err)
        return result

    def vis_alle_tags(self, id):
        try:
            self.cursor.execute("SELECT t.id, t.navn FROM blogginnlegg AS bi JOIN (tag_blogginnlegg AS tb JOIN tag AS t ON tb.tag_id = t.id) ON bi.id = tb.bloggInnlegg_id WHERE bi.id = (%s)", (id,))
            result = self.cursor.fetchall()
        except mysql.connector.Error as err:
                print(err)
        return result


    def vis_alle(self):
        try:
            self.cursor.execute("SELECT * FROM blogginnlegg")
            r = self.cursor.fetchall()
        except mysql.connector.Error as e:
            print("test")
        return r

    def vis_en(self, id):
        try:
            self.cursor.execute("SELECT id, blogg_bruker_id, tittel, dato_tidspunkt_opprettet, treff, tekst FROM blogginnlegg WHERE  id=(%s)", (id,))
            r = self.cursor.fetchone()
        except mysql.connector.Error as e:
            print(e)
        return r

    def opprett_blogg(self, verdier):
        sql = '''INSERT INTO blogg (blogg_tittel, bruker_id)
              VALUES ((%s), (%s))'''
        self.cursor.execute(sql, verdier)

    def opprett_innlegg(self, verdier):
        sql = '''INSERT INTO blogginnlegg (tittel, blogg_bruker_id, tekst, blogg_id)
              VALUES ((%s), (%s), (%s), (%s))'''
        self.cursor.execute(sql, verdier)
        self.cursor.execute('SELECT LAST_INSERT_ID()')
        id = self.cursor.fetchone()
        return id

    def endre_innlegg(self, innlegg):
        sql = '''UPDATE 
                          blogginnlegg
                          SET
                              tittel = %s, tekst = %s
                          WHERE
                              id = %s'''
        self.cursor.execute(sql, innlegg)

    def last_opp_bilde(self, verdier):
        sql = '''INSERT INTO fil (id, mimetype, kode)
                      VALUES ((%s), (%s), (%s))'''
        self.cursor.execute(sql, verdier)

    def binde_pk(self, insatt_id, uuid):
        sql = '''INSERT INTO fil_blogginnlegg (blogginnlegg_id, fil_id)
                              VALUES ((%s), (%s))'''
        pks = [insatt_id, uuid]
        self.cursor.execute(sql, pks)

    def vis_bilde(self, id):
        try:
            self.cursor.execute("SELECT * FROM fil WHERE  id=(%s)", (id,))
            return self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)

    def sjekk_u_uid(self, uuid):
        try:
            self.cursor.execute("SELECT id FROM fil WHERE id=(%s)", (uuid,))
            r = self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)
        return r is not None

    def vis_bilder(self, id):
        self.cursor.execute("select fil_id from fil_blogginnlegg where blogginnlegg_id = (%s)", (id,))
        r = self.cursor.fetchall()
        return r


    # Brukt til noe?
    # def query(self, sql):
    #     self.cursor.execute(sql)
    #     result = self.cursor.fetchall()
    #     return result

    def vis_oppslag_med_tag_sortert_dato(self, tagid):
        try:
            self.cursor.execute("SELECT bi.id, bi.tittel, br.alias, br.id, bi.dato_tidspunkt_opprettet, bi.treff, t.navn FROM blogginnlegg AS bi JOIN (tag_blogginnlegg AS tb JOIN tag AS t ON tb.tag_id = t.id) ON bi.id = tb.bloggInnlegg_id JOIN (blogg AS b JOIN bruker AS br ON b.bruker_id = br.id) ON bi.blogg_id = b.id WHERE t.id = (%s) ORDER BY bi.dato_tidspunkt_opprettet DESC", (tagid,))
            result = self.cursor.fetchall()
            return result
        except mysql.connector.Error as err:
            print(err)


    def vis_oppslag_med_tag_sortert_treff(self, tagid):
        try:
            self.cursor.execute("SELECT bi.id, bi.tittel, br.alias, br.id, bi.dato_tidspunkt_opprettet, bi.treff, t.navn FROM blogginnlegg AS bi JOIN (tag_blogginnlegg AS tb JOIN tag AS t ON tb.tag_id = t.id) ON bi.id = tb.bloggInnlegg_id JOIN (blogg AS b JOIN bruker AS br ON b.bruker_id = br.id) ON bi.blogg_id = b.id WHERE t.id = (%s) ORDER BY bi.treff DESC", (tagid,))
            result = self.cursor.fetchall()
            return result
        except mysql.connector.Error as err:
            print(err)


    def vis_oppslag_med_tag_sortert_alfabetisk(self, tagid):
        try:
            self.cursor.execute("SELECT bi.id, bi.tittel, br.alias, br.id, bi.dato_tidspunkt_opprettet, bi.treff, t.navn FROM blogginnlegg AS bi JOIN (tag_blogginnlegg AS tb JOIN tag AS t ON tb.tag_id = t.id) ON bi.id = tb.bloggInnlegg_id JOIN (blogg AS b JOIN bruker AS br ON b.bruker_id = br.id) ON bi.blogg_id = b.id WHERE t.id = (%s) ORDER BY bi.tittel", (tagid,))
            return self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)


    def sok_etter_innlegg_hvor_tittel_inneholder_streng(self, tekst):
        tekststring = "%" + tekst + "%"
        # Brukt w3schools innhold om Python String Concatenation for å finne ut hvordan sette sammen string i python:
        # https://www.w3schools.com/python/gloss_python_string_concatenation.asp
        try:
            self.cursor.execute("SELECT bi.id, bi.tittel, br.alias, br.id, bi.dato_tidspunkt_opprettet, bi.treff FROM blogginnlegg AS bi JOIN blogg AS b ON bi.blogg_id = b.id JOIN bruker AS br ON bi.blogg_bruker_id = br.id WHERE bi.tittel LIKE (%s) ORDER BY bi.dato_tidspunkt_opprettet DESC", (tekststring,))
            resultat = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return resultat

    def sok_etter_innlegg_hvor_tag_inneholder_streng(self, tekst):
        tekststring = "%" + tekst + "%"
        try:
            self.cursor.execute("SELECT bi.id, bi.tittel, br.alias, br.id, bi.dato_tidspunkt_opprettet, bi.treff, t.id, t.navn FROM blogginnlegg AS bi JOIN (tag_blogginnlegg AS tb JOIN tag AS t ON tb.tag_id = t.id) ON bi.id = tb.bloggInnlegg_id JOIN (blogg AS b JOIN bruker AS br ON b.bruker_id = br.id) ON bi.blogg_id = b.id WHERE t.navn LIKE (%s) ORDER BY bi.id", (tekststring,))
            resultat = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return resultat

    def sok_etter_innlegg_hvor_innhold_inneholder_streng(self, tekst):
        tekststring = "%" + tekst + "%"
        try:
            self.cursor.execute("SELECT bi.id, bi.tittel, br.alias, br.id, bi.dato_tidspunkt_opprettet, bi.treff FROM blogginnlegg AS bi JOIN (blogg AS b JOIN bruker AS br ON b.bruker_id = br.id) ON bi.blogg_id = b.id WHERE bi.tekst LIKE (%s) GROUP BY bi.id ORDER BY bi.dato_tidspunkt_opprettet DESC", (tekststring,))
            resultat = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return resultat

    def sok_etter_bruker_hvor_brukeralias_inneholder_streng(self, tekst):
        tekststring = "%" + tekst + "%"
        try:
            self.cursor.execute("SELECT id, alias FROM bruker WHERE alias LIKE (%s) ORDER BY alias", (tekststring,))
            resultat = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return resultat

    def vis_blogger_tilhorende_bruker(self, brukerid):
        try:
            self.cursor.execute("SELECT br.alias, b.id, b.blogg_tittel FROM blogg AS b JOIN bruker AS br ON b.bruker_id = br.id WHERE b.bruker_id = (%s) ORDER BY b.blogg_tittel", (brukerid,))
            resultat = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return resultat


    def hent_bruker_ved_navn(self, brukernavn):
        try:
            self.cursor.execute("SELECT * FROM bruker WHERE alias=(%s)", (brukernavn,))
            return self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)

    def hent_bruker_ved_epost(self, epost):
        try:
            self.cursor.execute("SELECT * FROM bruker WHERE epost=(%s)", (epost,))
            return self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)

    def hent_bruker_ved_id(self, bruker_id):
        try:
            self.cursor.execute("SELECT * FROM bruker WHERE id=(%s)", (bruker_id,))
            return self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)

    def lagre_ny_bruker(self, bruker_info):
        try:
            sql = ("INSERT INTO `bruker` (alias, epost, passord_hash) VALUES (%s, %s, %s)")
            self.cursor.execute(sql, bruker_info)
        except mysql.connector.Error as err:
            print(err)

    def ny_kommentar(self, kommentar):
        try:
            sql1 = '''INSERT
                INTO
                kommentar(id, tekst, dato_tid_opprettet, bruker_id, blogginnlegg_id, blogginnlegg_blogg_id, blogginnlegg_blogg_bruker_id)
                VALUES(NULL, %s, current_timestamp(), %s, %s, %s, %s);'''
            self.cursor.execute(sql1, kommentar)
        except mysql.connector.Error as err:
            print(err)

    def vis_alle_tagger(self):
        try:
            self.cursor.execute("SELECT navn FROM tag")
            return self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)

    def koble_tag_til_innlegg(self, tag, brukers_nyeste_innlegg):
        try:
            self.cursor.execute('''INSERT INTO tag_blogginnlegg (tag_id, blogginnlegg_id) 
                                    VALUES ((SELECT id from tag where navn like (%s)), (%s))''', (tag, brukers_nyeste_innlegg,))
        except mysql.connector.Error as err:
            print(err)

    def vis_id_nyeste_innlegg_til_bruker(self, bruker_id):
        try:
            self.cursor.execute("SELECT max(id) FROM blogginnlegg WHERE blogg_bruker_id = (%s)", (bruker_id,))
            return self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)

    def opprett_ny_tag(self, tag):
        try:
            self.cursor.execute("INSERT INTO tag (navn) VALUES (%s)", (tag,))
        except mysql.connector.Error as err:
            print(err)

    def slett_kommentar(self, id):
        try:
            self.cursor.execute("DELETE FROM kommentar WHERE  id=(%s)", (id,))
        except mysql.connector.Error as err:
                print(err)

    def slett_innlegg(self, id):
        try:
            self.cursor.execute("DELETE FROM blogginnlegg WHERE  id=(%s)", (id,))
        except mysql.connector.Error as err:
                print(err)
