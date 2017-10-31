package Homeworks.SD1x.movie_database;
import java.util.*;

public class Movie implements Comparable<Movie> {
    private String name;
    private ArrayList<Actor> actors;
    private double rating;

    /**
     * Initializes a Movie object
     * @param name The String of the name of the movie
     */
    public Movie(String name) {
        this.name = name;
        this.actors = new ArrayList<>();
    }

    /**
     * Helper function for checking equality
     * @param o The other Movie against which to check
     *          for equality
     * @return true or false based on result of equality
     * check
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (Double.compare(movie.rating, rating) != 0) return false;
        return name.equals(movie.name);
    }

    /**
     * Computes the hash value of a movie object
     * @return the hash code, an int
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Helper to compare movie objects. Useful during
     * sorting
     * @param that The other movie against which to
     *             compare
     * @return int based on result of comparison
     */
    @Override
    public int compareTo(Movie that) {
        //write code here for compare name
        return  this.getName().compareTo(that.getName());
    }

    /**
     * Getter for the movies Name
     * @return String for the name of movie
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the movie's list of actors
     * @return ArrayList of Actor objects
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }

    /**
     * Getter for the names of the actors of this movie
     * @return ArrayList of Strings of the actors' names
     */
    private ArrayList<String> getActorNames() {
        ArrayList<String> actorNames = new ArrayList<>();
        for (Actor actor: this.actors) {
            actorNames.add(actor.getName());
        }
        return actorNames;
    }

    /**
     * Add an actor to the movie's list of actors
     * @param actor an instance of class Actor
     */
    public void addActor(Actor actor) {
        if (!this.getActorNames().contains(actor.getName())) {
            this.actors.add(actor);
        }
    }


    /**
     * Getter for the movie's rating
     * @return a double representing the movie's rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for the movie's rating
     * @param rating: the double for the movie's rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
}
