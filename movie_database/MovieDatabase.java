package Homeworks.SD1x.movie_database;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MovieDatabase {
    private ArrayList<Movie> movieList;
    private ArrayList<Actor> actorList;

    /**
     * Initializes the MovieDatabase object
     */
    public MovieDatabase() {
        this.movieList = new ArrayList<>();
        this.actorList = new ArrayList<>();
    }

    /**
     * Helper for testing for equality of MovieDatabases
     * @param o the other MovieDatabase against which to
     *          compare
     * @return boolean true or false based on result of
     * equality test
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDatabase that = (MovieDatabase) o;

        if (!movieList.equals(that.movieList)) return false;
        return actorList.equals(that.actorList);
    }

    /**
     * Computes the hash value of the movie database
     * @return the computed hash code, an int.
     */
    @Override
    public int hashCode() {
        int result = movieList.hashCode();
        result = 31 * result + actorList.hashCode();
        return result;
    }

    /**
     * Getter for the movies in the database
     * @return an ArrayList of all the movies in the
     * database
     */
    public ArrayList<Movie> getMovieList() {
        return this.movieList;
    }

    /**
     * Getter for the actors in the database
     * @return an ArrayList of all the actors in the
     * database
     */
    public ArrayList<Actor> getActorList() {
        return this.actorList;
    }

    /**
     * Getter for the String names of the movies in the
     * database
     * @return ArrayList of Strings of the names of all
     * the movies in the database.
     */
    private ArrayList<String> getMovieNames() {
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie: this.movieList) {
            movieNames.add(movie.getName());
        }
        return movieNames;
    }


    /**
     * Add a movie to the database
     * @param name String for the movie's name
     * @param actors Array of Strings for the actors'
     *               names.
     */
    public void addMovie(String name, String[] actors) {
        if (!this.getMovieNames().contains(name)) {
            ArrayList<Movie> movieList = new ArrayList<>();
            Movie newMovie = new Movie(name);
            movieList.add(newMovie);
            for (String actorName: actors) {
                Actor newActor = new Actor(actorName, movieList);
                newMovie.addActor(newActor);
                boolean added = false;
                for (Actor actor: this.actorList) {
                    if (actorName.equals(actor.getName())) {
                        actor.addMovie(newMovie);
                        added = true;
                    }
                }
                if (!added) this.actorList.add(newActor);

            }
            this.movieList.add(newMovie);
        }
    }

    /**
     * Add a rating to a particular movie
     * @param name The String name of the movie to be
     *             rated
     * @param rating The double representing the rating
     *               value
     */
    public void addRating(String name, double rating) {
        for (Movie movie: movieList) {
            if (movie.getName().equals(name)) {
                movie.setRating(rating);
            } else {
                continue;
            }
        }
    }

    /**
     * Update a particular movie's rating
     * @param name The String name of the movie
     * @param newRating The double representing the new
     *                  value for the movie's rating
     */
    public void updateRating(String name, double newRating) {
        this.addRating(name, newRating);
    }

    /**
     * Get the actor with the best average rating
     * for their movies
     * @return String of the name of the actor
     */
    public String getBestActor() {
        String actorName = "";
        double averageRating = 0.0;

        for (Actor actor: actorList) {
            double totalRating = 0.0;
            for (Movie movie: actor.getMovies()) {
                totalRating += movie.getRating();
            }
            double average = totalRating/actor.getMovies().size();

            if (average > averageRating) {
                averageRating = average;
                actorName = actor.getName();
            }
        }
        return actorName;
    }

    /**
     * Get the movie with the highest rating.
     * @return String of the name of the movie.
     */
    public String getBestMovie() {
        double bestRating = 0.0;
        String movieName = "";
        for (Movie movie: this.movieList) {
            if (movie.getRating() > bestRating) {
                bestRating = movie.getRating();
                movieName = movie.getName();
            }
        }
        return movieName;
    }


    public static void main(String[] args) {

        MovieDatabase mdb = new MovieDatabase();
        String moviesText = "./submissions/Homeworks/SD1x/movie_database/files/movies.txt";
        String splitBy = ",";
        String line;

        // Add movies and actors to the database
        try (BufferedReader br = new BufferedReader(new FileReader(moviesText))) {
            while ((line = br.readLine()) != null) {
                String[] movie = line.split(splitBy);
                String[] actor = new String[1];
                for (int i = 0; i < movie.length; i++) {
                    if (i == 0) {
                        actor[i] = movie[i];
                    }
                    else {
                        mdb.addMovie(movie[i].trim(), actor);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the ratings of the various movies
        String ratingsText = "./submissions/Homeworks/SD1x/movie_database/files/ratings.txt";
        splitBy = "\t";

        try (BufferedReader br = new BufferedReader(new FileReader(ratingsText))) {
            while ((line = br.readLine()) != null) {
                String[] rating = line.split(splitBy);
                try {
                    double value = Double.parseDouble(rating[1]);
                    mdb.addRating(rating[0].trim(), value);
                } catch (NumberFormatException e) {
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print out best actor and best movie
        System.out.println(mdb.getBestActor());
        System.out.println(mdb.getBestMovie());
    }
}
