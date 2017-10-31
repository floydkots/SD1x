# Movie Database

First, we want you to make three classes (Make sure your classes are named exactly as shown here):

1 **Actor**
This class will have the following instance variables:
- *String name* – the full name of the actor or actress.
- *ArrayList<Movie> movies* – an Arraylist that has movies that this actor has acted in.

Add getters and setters for these instance variables.
Make sure to create a constructor that initializes your variables appropriately.

2 **Movie**
This class will have the following instance variables:
- _String_ name – the name of the movie
- _ArrayList<Actor>_ actors – an ArrayList of the actors in the movie. 
- _double_ rating – a freshness rating from rotten tomatoes (www.rottentomatoes.com)
Add getters and setters for these instance variables.

Make sure to create a constructor that initializes your variables appropriately.

3 **MovieDatabase** 
This class has two instance variables:
- _ArrayList<Movie>_ movieList – an ArrayList of movies
- _ArrayList<Actor>_ actorList – an ArrayList of actors
   
Note: Make sure to add getters for both these instance variables. That is, add a `getMovieList()` and a `getActorList()` method.
   
Add the following methods to this class:
- **MovieDatabase()**
a constructor that just creates a new movieList and a new actorList. At the time of construction, both of these lists will be empty.

- **void addMovie(String name, String[] actors)**
This method takes in the name of a movie that is not currently in the database along with a list of actors for that movie. 
If the movie is already in the database, your code ignores this request (this specification is an oversimplification). 
If the movie is a new movie, a movie object is created and added to the movieList. 
If any of the actors happen to be new, they will be added to the actorList.

- **void addRating(String name, double rating)**
Add a rating for this movie. Assume that the name argument will definitely be a name that is currently in the database.
void updateRating(String name, double newRating)
Overwrite the current rating of a movie with this new rating. Again, assume that the name argument will definitely be a name that is currently in the database.

- **String getBestActor()**
Returns the name of the actor that has the best average rating for their movies.
 
In the case of a tie, return any one of the best actors.

- **String getBestMovie()**
Returns the name of the movie with the highest rating.

In the case of a tie, return any one of the best movies.

- **Main method**
Finally, write a main method where:

1. You create a new instance of a movieDatabase.
2. Add all the movies in the file movies.txt.
3. Go through the ratings of the movies in the file ratings.txt and add the ratings for the movies.
4. Now call the methods that you created and print out the name of the best actor and the name of the highest rated movie.
