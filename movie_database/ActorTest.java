package Homeworks.SD1x.movie_database;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class ActorTest {
    private Actor actor_1;
    private Actor actor_2;
    private Actor actor_3;
    private Movie movie_1;
    private Movie movie_2;
    private Movie movie_3;
    private ArrayList<Movie> movieList_1;
    private ArrayList<Movie> movieList_2;
    private ArrayList<Movie> movieList_3;

    @Before
    public void setUpActors() {
        movie_1 = new Movie("movie_1");
        movie_2 = new Movie("movie_2");
        movie_3 = new Movie("movie_3");

        movieList_1 = new ArrayList<>();
        movieList_1.add(movie_1);
        movieList_2 = new ArrayList<>();
        movieList_2.add(movie_2);
        movieList_3 = new ArrayList<>();
        movieList_3.add(movie_3);

        actor_1 = new Actor("actor_1", movieList_1);
        actor_2 = new Actor("actor_2", movieList_2);
        actor_3 = new Actor("actor_3", movieList_3);
    }

    @Test
    public void testActorConstructor() {
        assertEquals("actor_1", actor_1.getName());

        assertEquals(movieList_1, actor_1.getMovies());
    }

    @Test
    public void testAddMovie() {
        actor_1.addMovie(movie_2);
        movieList_1.add(movie_2);
        assertEquals(movieList_1, actor_1.getMovies());
    }
}
