import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;  // Import the Scanner class

public class LettersGame {
    /*
    Solves the letters game in Countdown, printing any words with five letters or more.
    */
    public static void main(String[] args) {
        try {
            // read dictionary text file
            File fileObject = new File(System.getProperty("user.dir") + "\\src\\dictionary_clean.txt");
            Scanner scReadFile = new Scanner(fileObject);

            // get letters input
            System.out.println("Enter letters");
            Scanner scReadInput = new Scanner(System.in);  // Create a Scanner object
            String input = scReadInput.nextLine();  // Read user input
            scReadInput.close();

            // array to put letters in each time a new word is checked
            char[] letters = new char[9];
            // fixed array of letters that doesn't change
            char[] lettersFixed = new char[9];

            // put fixed letters into array
            for (int i = 0; i < input.length(); i++) {
                lettersFixed[i] = input.charAt(i);
            }

            // holds word from dictionary
            String word;
            // list to hold matching words
            ArrayList<String> foundWords = new ArrayList<>();

            // loop over all words in dictionary
            while (scReadFile.hasNextLine()) {
                word = scReadFile.nextLine();
                // copy fixed-letters to letters; the letters array will get change during check
                System.arraycopy(lettersFixed, 0, letters, 0, lettersFixed.length);
                if(isIn(word,letters)) {  // if the word is in the letters
                    foundWords.add(word);  // add word to list
                }

            }
            printWords(foundWords);  // print the found words to screen
            scReadFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    static void printWords(ArrayList<String> words) {
        /*
          Prints list of words to screen, showing the number of letters and excluding words less than 5 letters long.
         */
        System.out.println(" ");
        // words between 5 and 9 letters, inclusive
        for(int i = 5; i < 10; i++) {
            // print number of letters
            System.out.println("----- " + i + " letters" + " -----" );
            // print words with that number of letters
            for (String word : words) {
                if (word.length() == i) {
                    System.out.println(word);
                }
            }
            System.out.println(" ");
        }
    }
    static boolean isIn(String word, char[] letters) {
        /*
          Check if the letters can make up the word
         */
        for(int i = 0; i < word.length(); i++) {  // loop over characters in word
            // loop over letters
            for(int j = 0; j < 9; j++) {
                // if letter appears at current character in word
                if(word.charAt(i) == letters[j]) {
                    letters[j] = '0';  // remove letter
                    break;  // stop looping over letters
                }else {
                    // if current character in word does not appear in letters
                    if(j == 8) {
                        // can't make word from letters
                        return false;
                    }
                }
            }

        }
        // can make word
        return true;
    }

}
