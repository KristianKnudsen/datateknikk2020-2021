#!/usr/bin/python
activate_this = '/stud/pda020/public_html/flask_test/flask_test/venv/bin/activate_this.py'
with open(activate_this) as file_:
    exec(file_.read(), dict(__file__=activate_this))

#!/usr/bin/python
import sys
import logging
logging.basicConfig(stream=sys.stderr)
sys.path.insert(0,"/stud/pda020/public_html/flask_test/")
sys.path.insert(1,"/stud/pda020/public_html/flask_test/flask_test/")

from flask_test import app as application