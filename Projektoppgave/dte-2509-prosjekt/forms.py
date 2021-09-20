from wtforms.validators import DataRequired
from wtforms import PasswordField, BooleanField
from wtforms.fields.html5 import EmailField
from wtforms.validators import Email, InputRequired, Length, EqualTo
from wtforms import FieldList, FormField, SelectField, StringField, \
    TextAreaField, SubmitField, MultipleFileField, HiddenField, IntegerField
from wtforms import validators
from flask_wtf import FlaskForm


class InnloggingForm(FlaskForm):
    brukernavn = StringField('Brukernavn')
    passord = PasswordField('Passord')


class BloggForm(FlaskForm):
    bloggnavn = StringField('Bloggnavn', validators=[InputRequired(), Length(min=1, max=44)])


class SokForm(FlaskForm):
    tekst = StringField(validators=[DataRequired()])
    kategori = SelectField(choices=["Tittel", "Tag", "Innhold", "Brukernavn"])
    submit = SubmitField('Søk')


# Kilde. WTForms dokumentasjon, forelesning DTE-2509 dato 11.03
class RegForm(FlaskForm):
    brukernavn = StringField('Brukernavn', validators=[InputRequired(message="*"),
                                                       Length(min=3, max=20,
                                                              message="Brukernavnet må være mellom 3 og 20 karakterer")])

    epost = EmailField('Epost', validators=[InputRequired(message="*"), Email()])
    passord = PasswordField('Nytt passord', validators=[
        InputRequired(message="*"),
        Length(min=8, max=25, message="Passordet må være mellom 8 og 25 karakterer"),
        EqualTo('bekreft', message='Passordene er ulike'),
    ])

    bekreft = PasswordField('Gjenta passord')
    lovnad = BooleanField('Jeg lover å oppføre meg', validators=[InputRequired(message="*")])
    registrer = SubmitField('Registrer ny bruker')


class FeltForm(FlaskForm):
    bilde = MultipleFileField('bilde')  # Må være 'Multiple' her. Source: Timevis av feilsøking
    # FileAllowed validator funker ikke i denne situasjonen

    tekst = TextAreaField('tekst', validators=[validators.Length(max=3000)])

    bildeuuid = HiddenField('uuid')


class HovedForm(FlaskForm):
    tittel = StringField('tittel', validators=[validators.data_required(), Length(min=1, max=44)])
    id = HiddenField('id')
    felt = FieldList(FormField(FeltForm), min_entries=1,
                     max_entries=18)  # Liste med forms i dette tilfellet bare FeltForm
    tag1 = StringField('tag')
    tag2 = StringField('tag')
    tag3 = StringField('tag')
    bloggid = IntegerField('bloggid')


class SorteringForm(FlaskForm):
    sortering = SelectField(choices=["Dato", "Treff", "Alfabetisk"])
    submit = SubmitField('Sorter')
    tagid = HiddenField()


class KommentarForm(FlaskForm):
    tekst = TextAreaField('Kommentar tekst', validators=[validators.data_required()])
    submit = SubmitField('Publiser kommentar')


class SlettInnlegg(FlaskForm):
    innlegg_id = HiddenField()
    submit = SubmitField('Slett innlegg')


class SlettKommentar(FlaskForm):
    kommentar_id = HiddenField()
    submit = SubmitField('Slett')
