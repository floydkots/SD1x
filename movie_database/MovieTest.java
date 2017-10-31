package Homeworks.SD1x.movie_database;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {
    private Actor actor_1;
    private Actor actor_2;
    private Actor actor_3;
    private Movie movie_1;
    private Movie movie_2;
    private Movie movie_3;
    private ArrayList<Movie> movieList_1;
    private ArrayList<Movie> movieList_2;
    private ArrayList<Movie> movieList_3;
    private ArrayList<Actor> actorList;

    @Before
    public void setUp() {
        movie_1 = new Movie("movie_1");
        movie_2 = new Movie("movie_2");
        movie_3 = new Movie("movie_3");

        movieList_1 = new ArrayList<>();
        movieList_1.add(movie_1);
        movieList_2 = new ArrayList<>();
        movieList_2.add(movie_2);
        movieList_3 = new ArrayList<>();
        movieList_3.add(movie_3);

        actorList = new ArrayList<>();

        actor_1 = new Actor("actor_1", movieList_1);
        actor_2 = new Actor("actor_2", movieList_2);
        actor_3 = new Actor("actor_3", movieList_3);

        movie_1.addActor(actor_1);
        movie_2.addActor(actor_2);
        movie_3.addActor(actor_3);
    }

    @Test
    public void testConstructor() {
        assertEquals("movie_1", movie_1.getName());

        assertEquals(actor_1, movie_1.getActors().get(0));
    }

    @Test
    public void testAddActor() {
        movie_2.addActor(actor_1);
        actorList.add(actor_2);
        actorList.add(actor_1);
        assertEquals(actorList, movie_2.getActors());

        movie_2.addActor(actor_2);
        assertEquals(actorList, movie_2.getActors());
    }

}
