from flask import Flask, render_template, request
from MovieRegister import MovieRegister
from movie import Movie

app = Flask(__name__)


@app.route('/')
def hello() -> 'html':
    id = request.args.get('id')
    if not id:
        with MovieRegister() as db:
            result = db.getAll()
        movies = [Movie(*x) for x in result]
        return render_template('movies.html',
                               movies=movies)
    else:
        with MovieRegister() as db:
            movie = Movie(*db.get(id))
        return render_template('movies.html',
                               movie=movie)


if __name__ == "__main__":
    app.run(debug=True)


class Movie:
    def __init__(self, id, title, director, year, url):
        self.id = id
        self.title = title
        self.director = director
        self.year = year
        self.url = url


def update() -> 'html':
    if request.method == "POST":

        id = request.form['id']
        title = request.form['title']
        director = request.form['director']
        year = request.form.get('year')
        url = request.form.get('url')

        verdier = [id, title, director, year, url]
        with MovieRegister() as db:
                db.updateMove(verdier)
        return redirect('/')

def updateMovie(self, movie):
    try:
        sql = "update movie id, title, director, year, url"
        cursor.execute(sql, movie)

    except mysql.connector.Error as err:
            print(err)