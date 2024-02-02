=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: patwu
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections
  I used a LinkedList to represent the body of the snake. I chose to use this type because the snake needed
  to be resized every time it ate an apple. Therefore, I stored the body of the snake as a LinkedList of squares. Each
  square was associated with an x and y position. A square is added to the LinkedList each time the snake eats an apple.
  Furthermore, it was useful to iterate through the LinkedlList in order to update positions.

  2. Inheritance and Subtyping
  I defined an abstract class BoardObj for any object on the board. Each object has an x and y position within the board
  that can be changed, and the BoardObj class has getters and setters to retrieve the x and y coordinates.
  Then, I defined three subtypes that extend the abstract class: apple, snake, and square. Each has different behaviors
  and unique methods. Apple has a spawn method that allows us to randomly generate it on the board. Snake incorporates
  movement, and essentially represents the snake's head while square represented each component of the snake's body.
  is distinct

  3. File I/O
  I used File I/O to add the feature of saving and loading the state of the game. While the user is playing the game,
  they can use the save button to write the entire state of the game to a file, game_state.txt. This can then be later
  read and loaded in to resume playing the game with a load button.

  4. Testing
  I made sure that the game could function independently of the GUI by calling methods associated with snake and apple.
  I wrote unit tests to ensure that the behavior of the snake and it's interactions with other objects such as the
  walls, itself, and the apple, behaved correctly.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  Game: this class contains the main method and executes the game by calling RunSnake
  RunSnake: this class sets up and runs the game by displaying all the buttons and visual components
  we see and setting up the board.
  Board: this class stores and updates the state of the board. It handles key presses and the interactions between
  different objects contained on the board.
  BoardObj: this is an abstract class which represents any object within the board. It has an x and y position which can
  be updated and accessed using getters and setters.
  Apple: this class extends BoardObj. It draws a red circular apple and also handles the spawn feature of an apple.
  Square: this class extends BoardObj. It draws a black square which is later used by the Snake class.
  Snake: this class extends BoardObj. It extends it adding the movement feature of the snake. The coordinates and
  movement essentially all represent just the snake's head. Then, it has an associated LinkedList of squares that
  represent the snake's body and follow the head.
  FileLineIterator: borrowed from homework 8, this class sets up a FileLineIterator for buffered readers which is later
  used to read files to load the state of the game.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
Other than minor bugs that took time to spot, the part that I had the most trouble with was File I/O.
I think I wasn't super comfortable with it and did not know how to properly incorporate it. I spent a lot
of time looking at online documentation and past homeworks to figure it out.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I feel that I did separate functionality decently between the different class
  and kept values maintained privately within the classes. I think I would reconsider how I structured the
  snake class if given the chance -- I sometimes felt that it got a bit confusing with the separate head object and
  LinkedList for body.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
I played google snake to plan my game and visualize how the game worked. I also refereed to java
documentation on oracle for LinkedList and file reading/writing.