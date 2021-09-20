from flask_login import login_user, UserMixin
from werkzeug.security import check_password_hash, generate_password_hash


from database import Database


class Bruker:

    def __init__(self, id, alias, epost, passord_hash):
        self.id = id
        self.alias = alias
        self.epost = epost
        self.passord_hash = passord_hash
        self.authenticated = False

    def get_id(self):
        return self.id

    # methods to satisfy login_manager
    def is_active(self):
        """True, as all users are active."""
        return True

    def is_authenticated(self):
        """Return True if the user is authenticated."""
        return self.authenticated

    def is_anonymous(self):
        """False, as anonymous users aren't supported."""
        return False


class Bilde:

    def __init__(self, uuid, mimetype, data):
        self.uuid = uuid  # integer, autoincrement, primary key, unique.
        # Potensiel dobbelt lagring? Lagrer størrelsen som man kan finne ut fra data variablen.
        self.mimetype = mimetype  # varchar(50) - skal lagre filtype i formen image/jpeg
        self.data = data  # MediumBlob maks 16mb


# Alle verdier er markert med "not null"

class Blogg:

    def __init__(self, id, blogg_tittel, bruker_id):
        self.id = id
        self.blogg_tittel = blogg_tittel
        self.bruker_id = bruker_id


class BloggMedAlias(Blogg):

    def __init__(self, id, blogg_tittel, bruker_id, alias):
        super().__init__(id, blogg_tittel, bruker_id)
        self.alias = alias


class BlogginnleggSokResultat:

    def __init__(self, id, tittel, alias, brukerid, dato, treff):
        self.id = id
        self.tittel = tittel
        self.alias = alias
        self.brukerid = brukerid
        self.dato = dato
        self.treff = treff


class Blogginnlegg:

    def __init__(self, id, tittel, tekst, dato_tidspunkt_opprettet, treff, blogg_id, blogg_bruker_id):
        self.id = id
        self.tittel = tittel
        self.tekst = tekst
        self.dato_tidspunkt_opprettet = dato_tidspunkt_opprettet
        self.treff = treff
        self.blogg_id = blogg_id
        self.blogg_bruker_id = blogg_bruker_id


class BlogginnleggMedAlias(Blogginnlegg):

    def __init__(self, id, tittel, tekst, dato_tidspunkt_opprettet, treff, blogg_id, blogg_bruker_id, bruker_alias):
        super().__init__(id, tittel, tekst, dato_tidspunkt_opprettet, treff, blogg_id, blogg_bruker_id)
        self.bruker_alias = bruker_alias


class BrukerByTreff:

    def __init__(self, blogg_bruker_id, bruker_alias, bruker_treff):
        self.blogg_bruker_id = blogg_bruker_id
        self.bruker_alias = bruker_alias
        self.bruker_treff = bruker_treff


class BrukersBlogger:
    def __init__(self, bloggeralias, bloggid, bloggTittel):
        self.bloggeralias = bloggeralias
        self.bloggid = bloggid
        self.bloggTittel = bloggTittel


class BrukerUPassord:

    def __init__(self, brukerid, alias):
        self.brukerid = brukerid
        self.alias = alias


class Innlegg:

    def __init__(self, id, forfatter, tittel, tidspunkt, treff, tekst):
        self.id = id
        self.forfatter = forfatter
        self.tittel = tittel
        self.tidspunkt = tidspunkt
        self.treff = treff
        self.tekst = tekst


class InnleggMEnTag:

    def __init__(self, id, tittel, brukernavn, brukerid, dato, treff, tag):
        self.id = id
        self.tittel = tittel
        self.brukernavn = brukernavn
        self.bruker_id = brukerid
        self.dato = dato
        self.treff = treff
        self.tag = tag


class Kommentar:
    def __init__(self, id, tekst, dato_tid_opprettet, bruker_id, blogginnlegg_id, blogginnlegg_blogg_id,
                 blogginnlegg_blogg_bruker_id):
        self.id = id
        self.tekst = tekst
        self.dato_tid_opprettet = dato_tid_opprettet
        self.bruker_id = bruker_id
        self.blogginnlegg_id = blogginnlegg_id
        self.blogginnlegg_blogg_id = blogginnlegg_blogg_id
        self.blogginnlegg_blogg_bruker_id = blogginnlegg_blogg_bruker_id


class SokeresultatBlogginnleggMEnTag(InnleggMEnTag):

    def __init__(self, id, tittel, brukernavn, brukerid, dato, treff, tagid, tagnavn):
        super().__init__(id, tittel, brukernavn, brukerid, dato, treff, "")
        self.tagid = tagid
        self.tagnavn = tagnavn


class Tag:

    def __init__(self, id, navn):
        self.id = id
        self.navn = navn
