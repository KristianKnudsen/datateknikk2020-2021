# Must do: pip install wtforms & pip install email_validator

from wtforms import Form, BooleanField,StringField, SubmitField, validators
from wtforms.validators import DataRequired, Email, Length

class StudentForm(Form):
    givenName = StringField('First name', validators=[DataRequired()])
    lastName = StringField('Last name', validators=[DataRequired()])
    email = StringField('Email Address', validators=[DataRequired(), Email(), Length(max=120)])
    studyProgram = StringField('Study program', [validators.Length(min=6, max=500)])
    submit = SubmitField('Update')
    #password = PasswordField('New Password', [
    #    validators.InputRequired(),
    #    validators.EqualTo('confirm', message='Passwords must match')
    #])
    #confirm = PasswordField('Repeat Password')
    #accept_tos = BooleanField('I accept the Terms of Service and Privacy Notice (updated Jan 22, 2015)',
    #                          [validators.InputRequired()])