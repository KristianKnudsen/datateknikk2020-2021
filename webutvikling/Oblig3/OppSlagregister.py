import mysql.connector
from mysql.connector import errorcode


class OpslReg:

    def __init__(self) -> None:
        dbconfig = {'host': 'kark.uit.no',
                    'user': 'stud_v21_knudsen',
                    'password': 'plsdonthackme',
                    'database': 'stud_v21_knudsen', }
        self.configuration = dbconfig

    def __enter__(self):
        self.conn = mysql.connector.connect(**self.configuration)
        self.cursor = self.conn.cursor(prepared=True)
        return self

    def __exit__(self, exc_type, exc_val, exc_trace) -> None:
        self.conn.commit()
        self.cursor.close()
        self.conn.close()

    def query(self, sql):
        self.cursor.execute(sql)
        svar = self.cursor.fetchall()
        return svar

    def visAlle(self):
        try:
            self.cursor.execute("SELECT * FROM oppslag ORDER BY dato desc")
            svar = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return svar

    def visMedId(self, id):
        try:
            self.cursor.execute("SELECT * FROM oppslag WHERE  id=(%s)", (id,))
            svar = self.cursor.fetchone()
        except mysql.connector.Error as err:
            print(err)
        return svar

    def visMedKatId(self, katId):
        try:
            self.cursor.execute("SELECT * FROM oppslag WHERE kategori=(%s) ORDER BY dato desc", (katId,))
            svar = self.cursor.fetchall()
        except mysql.connector.Error as err:
            print(err)
        return svar

    def opprettOppslag(self, oppslag):
        try:
            sql = '''INSERT INTO oppslag (tittel, kategori, ingress, oppslagtekst, bruker)
                VALUES ((%s), (%s), (%s), (%s), (%s))'''
            self.cursor.execute(sql, oppslag)

        except mysql.connector.Error as err:
            print(err)

    def oppdaterOppslag(self, oppslag):
        try:
            sql = '''UPDATE 
                oppslag
                SET
                    tittel = %s, kategori = %s, ingress = %s, oppslagtekst = %s,
                    bruker = %s, dato = %s, treff = %s
                WHERE
                    id = %s'''
            self.cursor.execute(sql, oppslag)
        except mysql.connector.Error as err:
            print(err)

    def slettOppslag(self, id):
        try:
            self.cursor.execute("DELETE FROM oppslag WHERE id=(%s)", (id,))
        except mysql.connector.Error as err:
            print(err)

    def registrerVising(self, id):
        self.cursor.execute("UPDATE oppslag SET treff = treff+1 WHERE id=(%s)", (id,))

    @staticmethod
    def validerTittel(tittel):
        if tittel.length > 1:
            return False, "Too short"
        elif tittel.length > 60:
            return False, "Too long"
        return True, "Valid"

    @staticmethod
    def validerKategori(kategori):
        try:
            val = int(kategori)
        except ValueError:
            return False, "Not Integer"

        if val < 1:
            return False, "Too low"
        elif val > 2147483647:
            return False, "Too high"
        return True, "Valid"
