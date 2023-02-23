# Countdown-Solver
Solves the numbers and the letters game for the TV Game-show Countdown, written in Java. It is composed of three main parts: a class which removes words from a dictionary which aren't allowed in countdown, a class which finds solutions to the word game, and a class which finds solutions to the numbers game.

## Usage

### Removing forbidden words from the dictionary
In Countdown only words of more than nine letters, proper nouns and hyphenated words are not allowed. To to find words to search for we can take a list of all English words, such at the one found [here](https://raw.githubusercontent.com/dwyl/english-words/master/words.txt) and remove any word that has any of these features. The class [DictionaryClean](src/DictionaryClean.java) does this for you.

### Solving the letters game
If you run the [LettersGame](src/LettersGame.java) you will be asked for your input as a string of letters, when you type these in and press enter all matching words longer than five letters will be displayed.

![alt text](/docs/letters_game_example.jpg "Letters Game Example")

### Solving the numbers game
If you run the [NumbersGame](src/NumbersGame.java) you will be asked for your six numbers, when you have entered them the programme will find you a solution to the game, if one exists. However, the it does not make any attempt to find a simple solution and will print the first it finds. This could be an area for further development.

![alt text](/docs/numbers_game_example.jpg "Numbers Game Example")

# Explanation of the numbers game solution
The program finds solutions by trying over every possible calculation and stopping if the target is reached.
#### Numbers combinations
The programme first finds every combination of the six numbers and puts them in a matrix which will have $6!=720$ arrays in.
#### Operations combinations
Then it creates a matrix containing every possible combination of operations (add, subtract, multiply and divide). As there are six numbers there are five positions to place the operations between them giving a total number of different combinations of $4^5=1024$. 
#### Operations order
There is one final combination to consider; the order of the operations. If we take an example like $100+25\times3+2$, there are various different answers to this depending on the position of the brackets i.e. $(100+25)\times3+2$ gives a different answer to $100+(25\times3)+2$. In fact it becomes difficult to calculate the number of combinations for this as it is possible to place many brackets to continually change the sum. To solve this problem we can employ Reverse Polish Notation, this removes the need for brackets. It is then possible to produce a matrix containing the different orders the operations can be performed in. See the code in [Combinations](Combinations.java) for details. Reverse Polish Notation can be studied in more detail [here](https://www-stone.ch.cam.ac.uk/documentation/rrf/rpn.html).
### Trying the sums
Now we have all of the possible combinations of numbers and operations we can simply over them checking with each new operation if the target number has been reached. If this happens the code will stop and print the solution found.

### Further Development
There are a couple of ways this could be improved; one is to remove sums that are in essence doing the same thing, i.e. $2+4=4+2$. This was not thought worth doing yet as the code runs quickly. The other avenue for further development could be getting the code to decide which of the solutions is the simplest  to output or perhaps find the most complex!
