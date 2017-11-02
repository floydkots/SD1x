# Battleship
Battleship is usually a two-player game, where each player has a fleet and an ocean (hidden from the other player), and tries to be the first to sink the other player’s fleet. We will do just a solo version, where the computer places the ships, and the human attempts to sink them.

We’ll play this game on a 20x20 ocean. This is larger than the ocean in the traditional battleship game.  In this game we will have one 8-square Battleship, one 7-square Battlecruiser, two 6-square Cruisers, two 5-square Light Cruisers, three 4-square Destroyers and four 3-square Submarines. Finally, unlike the traditional game, A player can shoot 5 times in each turn.

The computer places these 13 ships on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally.

The human player does not know where the ships are. The initial display of the ocean shows a 20 by 20 array of locations, all the same.

The human player tries to hit the ships, by calling out a row and column number. The computer responds with one bit of information saying ”hit” or ”miss.” When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. However, when a ship is hit and sinks, the program prints out a message ”You just sank a ship-type.” After each shot, the computer redisplays the ocean with the new information.

A ship is ”sunk” when every square of the ship has been hit. Thus, it takes 8 hits to sink a battleship but only 6 to sink a cruiser. The objective is to sink the fleet with as few shots as possible.

When all ships have been sunk, the program prints out a message that the game is over, and tells the user how many shots were required.


## Implementation details
Name your project Battleship. Place all classes in the default package. Your program should have the following classes:

- class BattleshipGame - This is the ”main” class, containing the main method and an instance variable of type Ocean.
- class Ocean – This contains a 20x20 array of Ships, representing the ”ocean,” and some methods to manipulate it.
- class Ship – This describes characteristics common to all the ships. It has subclasses:
- class BattleShip extends Ship – Describes a battleship - a ship that occupies 8 squares.
- class BattleCruiser extends Ship - Describes a battlecruiser - a ship that occupies 7 squares.
- class Cruiser extends Ship - Describes a cruiser - a ship that occupies 6 squares.
- class LightCruiser extends Ship - Describes a light cruiser - a ship that occupies 5 squares.
- class Destroyer extends Ship - Describes a destroyer - a ship that occupies 4 squares.
- class Submarine extends Ship - Describes a submarine - a ship that occupies 3 squares.
- class EmptySea extends Ship - Describes a part of the ocean that does not have a ship in it. While it might seem silly to have the lack of a ship be a type of ship, this trick does simplify a number of things. 

### class BattleshipGame
The BattleshipGame class is the ”main” class–that is, it contains a main method. In this class you will set up the game; accept ”shots” from the user; display the results; print final scores; and ask the user if he/she wants to play again. All input/output is done here (although some of it is done by calling a print() method in the Ocean class.) All computation will be done in the Ocean class and the various Ship classes.

Note that you want to accept 5 shots from the user. So you need to ensure that you have a well defined format for this. For example you can provide an instruction to the user as follows
The input format should look like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17

Note you will need to use the split method to parse this input string. See the following link for documentation
split method

To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top. Numbers should be 0 to 19, not 1 to 20. The top left corner square should be 0, 0. Use different characters to indicate locations that contain a hit, locations that contain a miss, and locations that have never been fired upon.

Use methods. Don’t cram everything into one or two methods, but try to divide up the work into sensible parts with reasonable names.


### class Ship
Ship will be an abstract class. We never really want to create a Ship. We always want to create a specific type of Ship. So this means the declaration will be abstract.

**public abstract class Ship**<br/>
Since we don’t really care which end of a ship is the bow and which the stern, we will consider all ships to be facing up or left. Other parts of the ship are in higher-numbered rows or columns.

You don’t need to write a constructor for this class–Java will automatically supply one for you (with no arguments).

#### Instance variables for this class  
- `int bowRow` – the row (0 to 19) which contains the bow (front) of the ship.
- `int bowColumn` - the column which contains the bow (front) of the ship.    
- `int length` – the number of squares occupied by the ship. An ”empty sea” location has length 1. 
- `boolean horizontal` – true if the ship occupies a single row, false otherwise. Ships will either be placed vertically or horizontally in the ocean.
- `boolean[] hit` - this is a boolean array of size 8 that record hits. Only battleships use all the locations. The others will use fewer.

Since these instance variables will be private, please include getters and setters for them as well.

Write the following abstract method    

**abstract String getShipType()**<br/>
This is an abstract method and therefore has no body.

**boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean)**<br/>
Returns true if it is okay to put a ship of this length with its bow in this location, with the given orientation, and returns false otherwise. The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally), and it must not ”stick out” beyond the array. Do not actually change either the ship or the Ocean, just says whether it is legal to do so.

**void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)**<br/>
”Puts” the ship in the ocean. This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship, and it also involves putting a reference to the ship in each of 1 or more locations (up to 8) in the ships array in the Ocean object. (Note: This will be as many as eight identical references; you can’t refer to a ”part” of a ship, only to the whole ship.)

**boolean shootAt(int row, int column)**<br/>
If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as ”hit” (in the hit array, 0 indicates the bow) and return true, otherwise return false.

**boolean isSunk()**<br/>
Return true if every part of the ship has been hit, false otherwise.

**@Override<br/>
public String toString()<br/>**
Returns a single-character String to use in the Ocean’s print method (see below).

This method should return ”x” if the ship has been sunk, ”S” if it has not been sunk. This method can be used to print out locations in the ocean that have been shot at; it should not be used to print locations that have not been shot at.

Since toString behaves exactly the same for all ship types, it can be moved into the Ship class.

Note that the toString method for the EmptySea class has to override the Ship class's implementation. In order to figure out what needs to be done, please see the description of the print method in the Ocean class.

### Extending classes
Use the Ship class as a parent class for every single ship type. Make the following. Keep each class in a separate file.

1. class BattleShip extends Ship 
2. class BattleCruiser extends Ship
3. class Cruiser extends Ship
4. class LightCruiser extends Ship
5. class Destroyer extends Ship
6. class Submarine extends Ship

Each of these classes has a constructor, the purpose of which is to set the inherited length variable to the correct value, and to initialize the hit array.

Aside from the constructor you have to override this method

**@Override<br/>
String getShipType()<br/>**
Returns one of the strings “battleship”, “battlecruiser”, “cruiser”, “light cruiser”,  “destroyer” or “submarine” as appropriate 

Aside from this you really do not have to write more code in any of these classes. Most of the heavy lifting has been done for you because of the abstract class design.      

### class EmptySea extends Ship
You may wonder why ”EmptySea” is a type of Ship. The answer is that the Ocean contains a Ship array, every location of which is, or can be, a reference to some Ship. If a particular location is empty, the obvious thing to do is to put a null in that location. But this obvious approach has the problem that, every time we look at some location in the array, we have to check if it is null. By putting a non-null value in empty locations, denoting the absence of a ship, we can save all that null checking.

Methods for EmptySea
**EmptySea()<br/>** 
This constructor sets the inherited length variable to 1.

**@Override<br/>
boolean shootAt(int row, int column)**<br/>
This method overrides shootAt(int row, int column) that is inherited from Ship, and always returns false to indicate that nothing was hit.

**@Override<br/> 
boolean isSunk()**<br/>
This method overrides isSunk() that is inherited from Ship, and always returns false to indicate that you didn’t sink anything.

**@Override<br/>
public String toString()**<br/>
Returns a single-character String to use in the Ocean’s print method.
Since this is the emptysea, you could choose to have an unoccupied sea in many ways

**@Override<br/>
String getShipType()**<br/>
This method just returns the string ”empty”

### class Ocean 
**Instance variables**
- `Ship[][] ships = new Ship[20][20]`; :-Used to quickly determine which ship is in any given location.
- `int shotsFired` - The total number of shots fired by the user.
- `int hitCount` - The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is counted, even though the additional ”hits” don’t do the user any good.
- `int shipsSunk` – The number of ships sunk. Remember that you have a total of 13 ships.       

#### Methods 
- **Ocean()**<br/>
The constructor. Creates an ”empty” ocean (fills the ships array with a bunch of EmptySea instances).
Also initializes any game variables, such as how many shots have been fired.

- **void placeAllShipsRandomly()**<br/>
Place all randomly on the (initially empty) ocean. Place larger ships before smaller ones, or you may end up with no legal place to put a large ship. You will want to use the Random class in the java.util package, so look that up in the Java API. 

- **boolean isOccupied(int row, int column)**<br/>
Returns true if the given location contains a ship, false if it does not.

- **boolean shootAt(int row, int column)**<br/>
Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not. In addition, this method updates the number of shots that have been fired, and the number of hits. Note: If a location contains a ”real” ship, shootAt should return true every time the user shoots at that same location. Once a ship has been ”sunk”, additional shots at its location should return false.

- **int getShotsFired()**<br/>
Returns the number of shots fired (in this game).

- **int getHitCount()**<br/>
Returns the number of hits recorded (in this game). All hits are counted, not just the first time a given square is hit.

- **int getShipsSunk()**<br/>
Returns the number of ships sunk (in this game).

- **boolean isGameOver()**<br/>
Returns true if all ships have been sunk, otherwise false.

- **Ship[][] getShipArray()**<br/>
Returns the 20x20 array of ships. The methods in the Ship class that take an Ocean parameter really need to be able to look at the contents of this array; the placeShipAt method even needs to modify it. While it is undesirable to allow methods in one class to directly access instance variables in another class, sometimes there is just no good alternative.

- **void print()**<br/>
Prints the ocean. To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top. Numbers should be 00 to 19, not 1 to 20.

The top left corner square should be 0, 0.

Use ’S’ to indicate a location that you have fired upon and hit a (real) ship, ’-’ to indicate a location that you have fired upon and found nothing there, ’x’ to indicate a location containing a sunken ship,
and ’.’ (a period) to indicate a location that you have never fired upon.

This is the only method in the Ocean class that does any input/output, and it is never called from within the Ocean class (except possibly during debugging), only from the BattleshipGame class.

You are welcome to write additional methods of your own if you feel the need. 

**Finally, remember that you should write unit tests! Test Driven Development is a concept that we have tried to emphasize in this course. In particular, write and submit OceanTest, which test the methods in the Ocean class.** 
