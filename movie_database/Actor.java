package Homeworks.SD1x.movie_database;
import java.util.*;

public class Actor implements Comparable<Actor> {
    private String name;
    private ArrayList<Movie> movies;

    /**
     * Overloaded constructor that takes the arguments
     * @param name String for name of the actor
     * @param movies ArrayList of Movies that the actor
     *               has acted in.
     */
    public Actor(String name, ArrayList<Movie> movies) {
        this.name = name;
        this.movies = new ArrayList<>();
        this.movies.addAll(movies);
    }

    /**
     * Overloaded constructor that does not take any
     * arguments
     */
    public Actor() {
        this.name = "";
        this.movies = new ArrayList<>();
    }

    /**
     * Helper function for determining equality of
     * Actor objects
     * @param o The Other actor with which to compare
     * @return true or false based on the result of
     * comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return name.equals(actor.name);
    }

    /**
     * Helper function for determining the hash value
     * of an Actor
     * @return the hash code as an int
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Function specifying the field to use for
     * comparing Aovies.
     * @param that The other actor against which to
     *            compare
     * @return int based on the result of the comparison
     */
    @Override
    public int compareTo(Actor that) {
        return this.getName().compareTo(that.getName());
    }

    /**
     * Getter for the actor's name
     * @return String of the actor's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the movie name
     * @param name The String name of the actor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the actor's list of movies
     * @return an ArrayList of movie objects
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Getter for movie names
     * @return ArrayList of Strings of the movie names
     */
    private ArrayList<String> getMovieNames() {
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie movie: this.movies) {
            movieNames.add(movie.getName());
        }
        return movieNames;
    }

    /**
     * Adds a movie to the actor's list of movies
     * @param movie Movie object to be added
     */
    public void addMovie(Movie movie) {
        if (!this.getMovieNames().contains(movie.getName())) {
            this.movies.add(movie);
        }
    }
}
