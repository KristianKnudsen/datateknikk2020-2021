import html
import mimetypes
import re
import uuid
import bleach
import filetype  # https://pypi.org/project/filetype/
import flask_wtf
from flask import Flask, render_template, request, redirect, url_for, flash
from flask import make_response
from flask_login import login_required, logout_user, LoginManager, current_user
from flask_wtf.csrf import CSRFProtect

from forms import *
from klasser import *

app = Flask(__name__)
# app.secret_key = secrets.token_urlsafe(16)
app.secret_key = 'DetteErEnKatt45545/@#secretKeyViHaddeMyeProblemer]+@=44tdfgvnjkgnvgnjgvnrfrjkbjdjsbn'
app.config['MAX_CONTENT_LENGTH'] = 4 * 1024 * 1024 * 6  # Maks sammenlagt filstørrelse per innlegg. Trengs egentlig ikke
# men beholdes for sikkerhets skyld
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif', 'bmp'}  # Filertyper som kan leses av html og gjenkjennes av imghdr

# https://flask-wtf.readthedocs.io/en/stable/csrf.html
csrf = CSRFProtect(app)
csrf.init_app(app)

app.config['TEMPLATES_AUTO_RELOAD'] = True
app.config["SECURITY_CSRF_COOKIE"] = {"key": "XSRF-TOKEN"}

# Enable CSRF protection
flask_wtf.CSRFProtect(app)

# https://flask-login.readthedocs.io/en/latest/
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.session_protection = "strong"  # siden den er satt til strong så trengs strengt tatt ikke "fresh login_required" Midlertidig satt til basic

app.config.update(
    SESSION_COOKIE_SECURE=True,
    SESSION_COOKIE_SAMESITE='Strict',
    # PERMANENT_SESSION_LIFETIME=1800,  # 30 min
)


@app.after_request
def after_request(response):
    resp = make_response(response)
    resp.headers[
        'Strict-Transport-Security'] = 'max-age=31536000; includeSubDomains'  # forklar hvorfor denne ble fjernet
    resp.headers['X-Content-Type-Options'] = 'nosniff'
    resp.headers['Content-Security-Policy'] = 'unsafe-inline'
    resp.headers['Content-Security-Policy'] = "frame-ancestors 'self' blob:"
    #resp.headers['Content-Security-Policy'] = "default-src 'self'" bugged. Viser ikke icon

    return resp


@app.errorhandler(500)
def notFound(error):
    return feil("Kode 500")


@app.errorhandler(414)
def notFound(error):
    return feil("Kode 414")


@app.errorhandler(404)
def notFound(error):  # error trengs siden notFound tar 1 argument. Vet ikke om det også må være for 414,500
    return feil("Kode 404")


@app.context_processor
def form():
    return dict(sok_form=SokForm())


@app.route('/tagvis', methods=["GET", "POST"])
def tag() -> 'html':
    tagid = request.args.get('tagid')
    sortering_form = SorteringForm()
    sortering_form.tagid = tagid

    if request.method == "POST":
        skjema = SorteringForm(request.form)
        tagid = skjema.tagid.data
        sortering_form.tagid = tagid
        if skjema.sortering.data == "Treff" and skjema.validate():
            with Database() as db:
                resultat = db.vis_oppslag_med_tag_sortert_treff(tagid)
                innleggene = [InnleggMEnTag(*x) for x in resultat]
            return render_template('oppslag_med_tag.html',
                                   innleggene=innleggene,
                                   sortering_form=sortering_form)
        elif skjema.sortering.data == "Dato" and skjema.validate():
            with Database() as db:
                resultat = db.vis_oppslag_med_tag_sortert_dato(tagid)
                innleggene = [InnleggMEnTag(*x) for x in resultat]
                return render_template('oppslag_med_tag.html',
                                       innleggene=innleggene,
                                       sortering_form=sortering_form)
        elif skjema.sortering.data == "Alfabetisk" and skjema.validate():
            with Database() as db:
                resultat = db.vis_oppslag_med_tag_sortert_alfabetisk(tagid)
                innleggene = [InnleggMEnTag(*x) for x in resultat]
                return render_template('oppslag_med_tag.html',
                                       innleggene=innleggene,
                                       sortering_form=sortering_form)
    elif tagid:
        with Database() as db:
            resultat = db.vis_oppslag_med_tag_sortert_dato(tagid)
            innleggene = [InnleggMEnTag(*x) for x in resultat]
        return render_template('oppslag_med_tag.html',
                               innleggene=innleggene,
                               sortering_form=sortering_form)


@app.route('/feil')
def feil(msg=None):
    if msg:
        flash(msg, "feil")
    return render_template('error.html')


@app.route('/search', methods=["GET", "POST"])
def sok() -> 'html':
    kategori = request.args.get('kategori')
    tekst = request.args.get('tekst')

    if not kategori or not tekst:
        flash("Ugyldig søk", "feil")
        return redirect(url_for('forside'))

    if len(kategori) > 50 or len(tekst) > 500:
        flash("Ugyldig søk", "feil")
        return redirect(url_for('forside'))

    if kategori == "Tittel":
        with Database() as db:
            resultat = db.sok_etter_innlegg_hvor_tittel_inneholder_streng(tekst)
            if not resultat:
                return render_template('ingen_resultat.html')
            else:
                blogginnlegg = [BlogginnleggSokResultat(*x) for x in resultat]
                return render_template('sok_resultat.html',
                                       blogginnlegg=blogginnlegg,
                                       tekst=tekst,
                                       hva="tittel")
    elif kategori == "Tag":
        with Database() as db:
            resultat = db.sok_etter_innlegg_hvor_tag_inneholder_streng(tekst)
            if not resultat:
                return render_template('ingen_resultat.html')
            else:
                blogginnlegg_m_tag = [SokeresultatBlogginnleggMEnTag(*x) for x in resultat]
                return render_template('sok_resultat.html',
                                       blogginnlegg_m_tag=blogginnlegg_m_tag,
                                       tekst=tekst,
                                       hva="tag")
    elif kategori == 'Innhold':
        with Database() as db:
            resultat = db.sok_etter_innlegg_hvor_innhold_inneholder_streng(tekst)
            if not resultat:
                return render_template('ingen_resultat.html')
            else:
                blogginnlegg = [BlogginnleggSokResultat(*x) for x in resultat]
                return render_template('sok_resultat.html',
                                       blogginnlegg=blogginnlegg,
                                       tekst=tekst,
                                       hva="innhold")
    elif kategori == "Brukernavn":
        with Database() as db:
            resultat = db.sok_etter_bruker_hvor_brukeralias_inneholder_streng(tekst)
            if not resultat:
                return render_template('ingen_resultat.html')
            else:
                bruker = [BrukerUPassord(*x) for x in resultat]
                return render_template('sok_resultat.html',
                                       bruker=bruker,
                                       tekst=tekst,
                                       hva="brukernavn")

    return render_template('ingen_resultat.html')


@login_manager.unauthorized_handler
def unauthorized_attempt():
    flash("Logg inn for å få tilgang til dette", "feil")
    return redirect(url_for('forside'))


@login_manager.user_loader
def load_user(user):
    with Database() as db:
        user = Bruker(*db.hent_bruker_ved_id(user))
        return user

def valider_blogg_navn(navn):
    if navn.strip() == "":
        flash("Mangler bloggnavn", "feil")
        return False
    else:
        return True

# @login_required bugga på servern? Forsøk med en annen løsning
@app.route('/hjemmeside', methods=["GET", "POST"])
@login_required  # autentisering på "utsiden" -> kan velge å ha det på innsiden slik som gjort i hjemmeside
def hjemmeside():
    blogg_form = BloggForm()
    bloggnavn = request.form.get("bloggnavn")

    if request.method == "POST" and blogg_form.validate() and valider_blogg_navn(bloggnavn):
        with Database() as db:
            verdier = [bloggnavn.strip(), current_user.id]
            db.opprett_blogg(verdier)
            flash("Opprettet blogg", "suksess")
            return redirect(url_for('hjemmeside', m="get"))  # Redirigierer til samme funksjon bare med en get slik at
        # dette ikke gjentar seg hvis man laster inn på nytt.
    with Database() as db:
        blogger = db.vis_blogger_tilhorende_bruker(current_user.id)
        blogger = [BrukersBlogger(*x) for x in blogger]
    return render_template('hjemmeside.html', blogger=blogger, blogg_form=blogg_form, tittel="Hjemmeside")


@app.route('/login', methods=["GET", "POST"])
def login():
    innlogging_form = InnloggingForm()

    if current_user.is_authenticated:
        return redirect(url_for('forside'))

    if request.method == "POST" and innlogging_form.validate():
        with Database() as db:
            clean_bruker = bleach.clean(innlogging_form.brukernavn.data)
            bruker_info = db.hent_bruker_ved_navn(clean_bruker)

            if bruker_info is None:
                flash("Passord/brukernavn stemmer dessverre ikke", "varsel")
                return render_template('landingside.html', form=innlogging_form, tittel="Logg inn")
            else:
                bruker = Bruker(*bruker_info)
                clean_passord = bleach.clean(innlogging_form.passord.data)
                if check_password_hash(bruker.passord_hash, clean_passord):
                    bruker.authenticated = True
                    login_user(bruker, remember=True)
                    if current_user.is_authenticated:
                        flash("Du er innlogget", "suksess")
                        return redirect(url_for('forside'))

                else:
                    flash("Passord/brukernavn stemmer dessverre ikke", "varsel")
                    return render_template('landingside.html',
                                           form=innlogging_form, tittel="Logg inn")

    else:
        return render_template('landingside.html', form=innlogging_form, tittel="Logg inn")


@app.route('/topliste')
def toppliste():
    with Database() as db:
        result2 = db.vis_alle_brukere()
        result3 = db.vis_alle_brukere_by_treff()
    brukere = [Bruker(*x) for x in result2]
    brukere_by_treff = [BrukerByTreff(*x) for x in result3]
    return render_template('toppliste.html', brukere=brukere,
                           brukereByTreff=brukere_by_treff, tittel="Topplisten")


@app.route('/', methods=["GET", "POST"])
def forside():
    slett_innlegg_form = SlettInnlegg()
    kommentar_form = KommentarForm(request.form)
    slett_kommentar_form = SlettKommentar()
    blogginnlegg_id = request.args.get('blogginnlegg')
    blogg_id = request.args.get('blogg')
    bruker_id = request.args.get('bruker')
    global innlegg_id

    if request.method == "POST" and kommentar_form.validate() and current_user.is_authenticated:
        with Database() as db:
            innlegg = db.vis_blogginnlegg(innlegg_id)

        tekst = kommentar_form.tekst.data
        bruker_id = current_user.id
        blogginnlegg_id = innlegg[0]
        blogginnlegg_blogg_id = innlegg[5]
        blogginnlegg_blogg_bruker_id = innlegg[6]
        kommentar = (tekst, bruker_id, blogginnlegg_id, blogginnlegg_blogg_id, blogginnlegg_blogg_bruker_id)

        with Database() as db:
            db.ny_kommentar(kommentar)
        return redirect(url_for('forside', blogginnlegg=str(innlegg_id)))

    elif blogginnlegg_id:
        innlegg_id = blogginnlegg_id
        with Database() as db:
            db.oppdater_treff(blogginnlegg_id)
            blogginnlegg = db.vis_blogginnlegg_med_alias(blogginnlegg_id)
            if blogginnlegg is None:
                flash("Fant ikke blogginnlegget")
                return redirect(url_for('forside'))
            blogginnlegg = BlogginnleggMedAlias(*blogginnlegg)
            result = db.vis_alle_kommentarer(blogginnlegg_id)
            result2 = db.vis_alle_brukere()
            antall = db.antall_kommentarer(blogginnlegg_id)
            tag = db.vis_alle_tags(blogginnlegg_id)
        kommentarer = [Kommentar(*x) for x in result]
        brukere = [Bruker(*x) for x in result2]
        antall_kommentarer = int(antall[0])
        antall_kommentarer_msg = "kommentar" if antall_kommentarer == 1 else "kommentarer"
        tags = [Tag(*x) for x in tag]
        return render_template('blogginnlegg.html', blogginnlegg=blogginnlegg,
                               kommentarer=kommentarer, brukere=brukere, antall_kommentarer=antall_kommentarer,
                               antall_kommentarer_msg=antall_kommentarer_msg, tags=tags,
                               kommentar_form=kommentar_form, slett_innlegg_form=slett_innlegg_form,
                               slett_kommentar_form=slett_kommentar_form, tittel=blogginnlegg.tittel)

    elif blogg_id:
        with Database() as db:
            blogg = db.vis_blogg_med_alias(blogg_id)
            if blogg is None:
                flash("Fant ikke bloggen", "varsel")
                return redirect(url_for('forside'))
            blogg = BloggMedAlias(*blogg)
            result = db.vis_alle_blogginnlegg_til_blogg(blogg_id)
            blogginnlegg = [Blogginnlegg(*x) for x in result]
            antall_kommentarer = [int(db.antall_kommentarer(x.id)[0]) for x in blogginnlegg]
        blogginnlegg = [Blogginnlegg(*x) for x in result]
        return render_template('blogg.html', blogginnlegg=blogginnlegg, blogg=blogg,
                               antall_kommentarer=antall_kommentarer, tittel=blogg.blogg_tittel)

    elif bruker_id:
        with Database() as db:
            resultat = db.vis_blogger_tilhorende_bruker(bruker_id)
            if not resultat:
                flash("Bruker ikke funnet", "varsel")
                return redirect(url_for('forside'))
            else:
                blogger = [BrukersBlogger(*x) for x in resultat]
                return render_template('vis_bruker.html',
                                       blogger=blogger, tittel=blogger[0].bloggeralias)

    else:
        with Database() as db:
            result = db.vis_alle_blogginnlegg()
            result2 = db.vis_alle_brukere()
            result3 = db.vis_alle_brukere_by_treff()
            blogginnlegg = [Blogginnlegg(*x) for x in result]
            antall_kommentarer = [int(db.antall_kommentarer(x.id)[0]) for x in blogginnlegg]
        brukere = [Bruker(*x) for x in result2]
        brukere_by_treff = [BrukerByTreff(*x) for x in result3]
        return render_template('forside.html', blogginnlegg=blogginnlegg, brukere=brukere,
                               brukere_by_treff=brukere_by_treff,
                               antall_kommentarer=antall_kommentarer, tittel="Blogg")


@app.route('/slettInnlegg', methods=["GET", "POST"])
def slett_innlegg():
    if request.method == "POST":
        slette_form = SlettInnlegg(request.form)
        innlegg_id = slette_form.innlegg_id.data
        with Database() as db:
            innlegg = db.vis_blogginnlegg(innlegg_id)
            if current_user.id == innlegg[6]:
                db.slett_innlegg(innlegg_id)
        return redirect(url_for('forside'))


@app.route('/slettKommentar', methods=["GET", "POST"])
def slett_kommentar():
    if request.method == "POST":
        slette_form = SlettKommentar(request.form)
        kommentar_id = slette_form.kommentar_id.data
        with Database() as db:
            kommentar = db.vis_kommentar(kommentar_id)
            if current_user.id == kommentar[3] or current_user.id == kommentar[6]:
                db.slett_kommentar(kommentar_id)
        return redirect(url_for('forside', blogginnlegg=str(innlegg_id)))


@app.route('/logout', methods=["GET", "POST"])
@login_required  # bedre med validering på innsiden? Mulig bug. Må sjekkes
def logout():
    if current_user.is_authenticated:
        logout_user()
    flash("Du har logget ut", "varsel")
    return redirect(url_for('forside'))


@app.route('/registrer', methods=['GET', 'POST'])
def registrer():
    if current_user.is_authenticated:
        return redirect(url_for('forside'))
    form = RegForm(request.form)
    if request.method == 'POST' and form.validate():
        clean_bruker = bleach.clean(form.brukernavn.data)
        clean_epost = bleach.clean(form.epost.data)
        clean_passord = bleach.clean(form.passord.data)

        with Database() as db:
            bruker = db.hent_bruker_ved_navn(clean_bruker)
            epost = db.hent_bruker_ved_epost(clean_epost)

            if bruker is not None:
                flash("Brukernavn/epost er opptatt", "varsel")
                return render_template('registrer.html', form=form)
            elif epost is not None:
                flash("Epost er allerede registrert, sikker på at du ikke har en bruker her?", "varsel")
                return render_template('registrer.html', form=form)
            else:
                ny_bruker = clean_bruker, clean_epost, generate_password_hash(clean_passord)
                db.lagre_ny_bruker(ny_bruker)
                ny_bruker = Bruker(*db.hent_bruker_ved_navn(clean_bruker))

                if ny_bruker:
                    ny_bruker.authenticated = True
                    login_user(ny_bruker, remember=True)
                    flash("Velkommen", "suksess")
                    return redirect(url_for('hjemmeside'))

                else:  # Hvis noe gikk galt ved insert new user i databasenz
                    flash("Noe gikk galt, prøv igjen", "feil")
                    return render_template('registrer.html', form=form)
    else:
        return render_template('registrer.html', form=form, tittel="Registrer deg")


@app.route('/redigerInnlegg')
@login_required
def rediger_innlegg():
    id = request.args.get('id')
    reg_form = RegForm(request.form)
    kan_redigere = har_tilgang(current_user.id, id)
    if not id or not kan_redigere:
        return feil("Ingen tilgang")

    with Database() as db:
        resultat = db.vis_en(id)
        innlegg = Innlegg(*resultat)

    innhold = re.split("<br>", innlegg.tekst)[:-1]
    rekkefolge = []
    for i, x in enumerate(innhold):
        if x[1] == "p":
            innhold[i] = x[3:][:-4]
            rekkefolge.append("tekst")
        elif x[1] == "i":
            innhold[i] = x[25:][:-9]
            rekkefolge.append("bilde")

    return render_template('rediger.html',
                           hoved_form=HovedForm(),
                           innlegg=innlegg,
                           innhold=innhold,
                           rekkefolge=rekkefolge,
                           reg_form=reg_form)


def validerRedigeringsFelt(form, indeks):
    try:
        bilde = form.felt[indeks].bilde.data
        tekst = form.felt[indeks].tekst.data
        uuid = form.felt[indeks].bildeuuid.data
    except IndexError:
        return False

    if (not bilde or not valider_bilde(bilde[0])) and not valider_tekst(tekst) and not validerUuid(uuid):
        return False
    return True


def validerUuid(uuid):
    if not uuid:
        return False
    with Database() as db:
        return db.sjekk_u_uid(uuid)


def har_tilgang(forfatter_id, innlegg_id):
    if innlegg_id:
        with Database() as db:
            return Innlegg(*db.vis_en(innlegg_id)).forfatter == forfatter_id
    return False


@app.route('/lagreRedigering', methods=["GET", "POST"])
@login_required
def lagreRedigering():
    form = HovedForm()
    kan_redigere = har_tilgang(current_user.id, form.id.data)
    if (request.method == "POST") and form.validate() \
            and validerRedigeringsFelt(form, 0) and kan_redigere:
        n = 0
        antall_bilder = 0
        tittel = form.tittel.data
        uuids = []
        innhold = ""
        while validerRedigeringsFelt(form, n) and antall_bilder < 6:
            bilde = form.felt[n].bilde.data  # kan være tom og kan ikke tildele en index enda
            tekst = form.felt[n].tekst.data
            uuidData = form.felt[n].bildeuuid.data
            if valider_tekst(tekst):
                innhold += '<p>' + bleach.clean(tekst) + '</p><br>'
            elif validerUuid(uuidData):
                innhold += '<img src="./lastNedBilde/' + uuidData + '" alt=""><br>'
                antall_bilder += 1
            else:
                mimetype = bilde[0].mimetype  # Bilde[0] kan nå ikke returnere index error
                bilde[0].seek(0)
                kode = bilde[0].read()
                uuids.append(uuid.uuid1().hex)  # uuid inneholder tiden
                verdier = [uuids[-1], mimetype, kode]

                with Database() as db:  # Splittede opplastninger kan føre til ufullstendige blogginnlegg hvis
                    # databasen går ned
                    db.last_opp_bilde(verdier)

                innhold += '<img src="./lastNedBilde/' + uuids[-1] + '" alt=""><br>'

                antall_bilder += 1
            n += 1

        innlegg = [tittel, innhold, form.id.data]

        with Database() as db:
            db.endre_innlegg(innlegg)
            for x in uuids:
                db.binde_pk(form.id.data, x)

        return redirect(url_for('forside', blogginnlegg=innlegg_id))
    return feil("Noe gikk galt under redigering")


@app.route('/lagInnlegg', methods=["GET", "POST"])
@login_required
def lag_innlegg():
    valgtblogg = request.args.get('bloggid')
    with Database() as db:
        blogger = db.vis_alle_blogger_til_bruker(current_user.id)
        blogger = [Blogg(*x) for x in blogger]
        if not blogger:
            flash("Du må opprette en blogg før du kan lage et innlegg", "varsel")
            return redirect(url_for('hjemmeside'))
    return render_template('opprett.html', formk=HovedForm(),
                           valgtblogg=valgtblogg, blogger=blogger)


def eier_blogg(bruker_id, blogg_id):
    if blogg_id:
        with Database() as db:
            return Blogg(*db.vis_blogg_med_id(blogg_id)).bruker_id == bruker_id
    return False


@app.route('/lagreInnlegg', methods=["GET", "POST"])
@login_required
def lagreInnlegg():  # trenger @login required
    form = HovedForm()
    blogg_id = form.bloggid.data
    eier_av_blogg = eier_blogg(current_user.id, blogg_id)
    if (request.method == "POST") and form.validate() \
            and valider_felt(form, 0) and eier_av_blogg:
        n = 0
        forfatter_id = current_user.id  # trenger å hente brukernavnet
        tittel = form.tittel.data
        uuids = []
        innhold = ""
        while valider_felt(form, n) and len(uuids) < 6:  # maks antall bilder
            # Maks antall felter totalt blir satt av forms og foreløpig er 18
            bilde = form.felt[n].bilde.data  # kan være tom og kan ikke tildele en index enda
            tekst = form.felt[n].tekst.data
            if valider_tekst(tekst):
                innhold += '<p>' + ' '.join(bleach.clean(tekst).split()) + '</p><br>'
            else:
                mimetype = bilde[0].mimetype  # Bilde[0] kan nå ikke returnere index error
                bilde[0].seek(0)
                kode = bilde[0].read()
                uuids.append(uuid.uuid1().hex)  # uuid inneholder tiden
                verdier = [uuids[-1], mimetype, kode]

                with Database() as db:  # Splittede opplastninger kan føre til ufullstendige blogginnlegg hvis
                    # databasen går ned
                    db.last_opp_bilde(verdier)

                innhold += '<img src="./lastNedBilde/' + uuids[-1] + '" alt=""><br>'
            n += 1

        # blogg_id = db.visAlleBloggerTilBruker(forfatter_id)
        innlegg = [tittel, forfatter_id, innhold, blogg_id]

        with Database() as db:
            insatt_id = db.opprett_innlegg(innlegg)
            for x in uuids:
                db.binde_pk(insatt_id[0], x)
            tagnavn = db.vis_alle_tagger()
            liste_alle_tagger = list(sum(tagnavn, ()))
            # Link til side brukt for lære hvordan omforme liste av tuppler til en liste:
            # https://www.geeksforgeeks.org/python-convert-list-of-tuples-into-list/
            tags = []
            tag1 = form.tag1.data
            tag2 = form.tag2.data
            tag3 = form.tag3.data
            if tag1:
                tags.append(tag1)
            if tag2 and tag2 != tag1:
                tags.append(tag2)
            if tag3 and tag3 != (tag2 or tag1):
                tags.append(tag3)
            brukers_nyeste_innlegg_ikke_tuppel = insatt_id[0]
            for x in tags:
                if x.upper() in (name.upper() for name in liste_alle_tagger):
                    db.koble_tag_til_innlegg(x, brukers_nyeste_innlegg_ikke_tuppel)
                else:
                    db.opprett_ny_tag(x)
                    db.koble_tag_til_innlegg(x, brukers_nyeste_innlegg_ikke_tuppel)
            # Link til side brukt for å sjekke om liste inneholder string
            # https://thispointer.com/python-how-to-check-if-an-item-exists-in-list-search-by-value-or-condition/
        return redirect(url_for('forside'))
    flash("Klarte ikke å opprette innlegget", "feil")
    return redirect(url_for('lag_innlegg'))


def valider_felt(form, indeks):
    try:
        bilde = form.felt[indeks].bilde.data
        tekst = form.felt[indeks].tekst.data
    except IndexError:
        return False

    if (not bilde or not valider_bilde(bilde[0])) and not valider_tekst(tekst):
        return False
    return True


def valider_bilde(fil):
    fil.seek(0)
    blob = fil.read()
    if False in (tillatt_fil(fil.filename),  # Sjekker at endelsen i navnet er riktig
                 tillatt_fil(".%s" % filetype.guess_extension(blob)),  # Sjekker at filen faktisk er et bilde
                 len(blob) <= 4 * 1024 * 1024):
        return False
    return True


def valider_tekst(tekst):
    x = len(tekst)
    return 0 < x <= 3000


def tillatt_fil(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


# Hentet fra https://flask.palletsprojects.com/en/1.1.x/patterns/fileuploads/

@app.route('/lastNedBilde/<id>')
def last_ned_bilde(id):
    with Database() as db:
        bilde = Bilde(*db.vis_bilde(id))  # Trenger error håntering hvis den ikke finner bildet.
    if bilde is None:
        pass
    else:
        pakke = make_response(bilde.data)
        pakke.headers.set('Content-Type', bilde.mimetype)
        pakke.headers.set(
            'Content-Disposition', 'inline',
            filename="last ned%s" % mimetypes.guess_extension(bilde.mimetype))
        return pakke


# Kode hentet fra fileUpload_db


if __name__ == '__main__':
    app.run(debug=False)
