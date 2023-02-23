import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

public class DictionaryClean {
     /*
    Takes a dictionary file and outputs a cleaned dictionary that doesn't include words not allowed in Countdown.
    */
    public static void main(String[] args) {
        try {
            // sets current folder directory, may need to be altered depending on where running from
            String baseDirectory = System.getProperty("user.dir") + "\\res\\";
            // read dictionary text file
            File fileObject = new File(baseDirectory + "dictionary.txt");
            Scanner myReader = new Scanner(fileObject);
            // write cleaned dictionary text file
            FileWriter myWriter = new FileWriter(baseDirectory + "dictionary_cleaned.txt");
            // if true, keep the current dictionary entry in cleaned version
            boolean keep;
            // hold the current dictionary entry
            String line;
            // contains characters that are not allowed in words in cleaned dictionary
            String exclude_chars = "-./'&0123567890";

            // loop over words in dictionary
            while (myReader.hasNextLine()) {
                // start with keep as true
                keep = true;
                // set next line in list
                line = myReader.nextLine();
                // loop over forbidden characters
                for(int i = 0; i < exclude_chars.length(); i++){
                    // if any of these characters are in word
                    if(line.contains("" + exclude_chars.charAt(i))){
                        // don't keep line and stop look
                        keep = false;
                        break;
                    }
                }
                // if still keeping line, check it's not over 9 characters and there are no upper case characters
                if(keep && (line.length() > 9 || !line.equals(line.toLowerCase()))) {
                    // don't keep
                    keep = false;
                }
                // if word allowed, write to new file
                if(keep) {
                    myWriter.write(line + "\n");
                }
            }
            myWriter.close();
            myReader.close();
        } catch (IOException e) { // catch exceptions in reading writing files
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

