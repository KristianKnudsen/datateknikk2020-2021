#!/usr/bin/python
activate_this = '/stud/sno095/public_html/flask_prosjekt/flask_prosjekt/venv/bin/activate_this.py'
with open(activate_this) as file_:
    exec(file_.read(), dict(__file__=activate_this))

#!/usr/bin/python
import sys
import logging
logging.basicConfig(stream=sys.stderr)
sys.path.insert(0,"/stud/sno095/public_html/flask_prosjekt/")
sys.path.insert(1,"/stud/sno095/public_html/flask_prosjekt/flask_prosjekt/")

from flask_prosjekt import app as application