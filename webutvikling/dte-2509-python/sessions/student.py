

from StudentRegister import StudReg
from werkzeug.security import generate_password_hash

class Student:

    # construct / attributes
    def __init__(self,id,givenName,lastName,email, studyProgram):
        self.id = id
        self.givenName = givenName
        self.lastName = lastName
        self.email = email
        self.studyProgram = studyProgram

    def login(self, username, password):

        with StudReg() as db:
            student = Student(*db.getStudent(id))
            check_password_hash(student.givenName, password)