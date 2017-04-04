import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Program to search for given words in a text file (entered via CLI as arg[0]),
 * through a grid of characters ("the puzzle") in a text file (entered via CLI
 * as arg[1]). Uses a "trie" algorithm to store and search through the
 * dictionary (Algorithms, Fourth Edition, by Kevin Wayne; Robert Sedgewick,
 * Published by Addison-Wesley Professional, 2011).
 * Class CS251
 * @author Michael Servilla
 * @version date 2017-03-31
 */
public class WordSearch {
    //Creates instance of a new ArrayList to hold results of search.
    private static List<Results> newResults = new ArrayList<>();

    /**
     * Main method to begin running the program.
     * @param args These are the dictionary text file entered as arg[0], and the
     *             puzzle text file entered as arg[1].
     */
    public static void main(String[] args) {

        //Offset values to perform directional search.
        int[] x = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] y = {0, 1, 1, 1, 0, -1, -1, -1};

        //Map of ints to label direction for output.
        HashMap<Integer, String> directions = new HashMap<>();
        directions.put(0, "N");
        directions.put(1, "NE");
        directions.put(2, "E");
        directions.put(3, "SE");
        directions.put(4, "S");
        directions.put(5, "SW");
        directions.put(6, "W");
        directions.put(7, "NW");

        //Routine to call methods to scan in text files and call methods to
        // build ArrayLists of the text files.
        Path dictionarySource = Paths.get("dictionary.txt"); //source for
        // dictionary, need to change to CLI args[0]
        Path puzzleSource = Paths.get("puzzle.txt"); //source for puzzle,
        // need to change to CLI args[1]
        ArrayList<String> puzzle = new ArrayList<>();
        ArrayList<String> dictionary = new ArrayList<>();
        buildArray(puzzleSource, puzzle);
        buildArray(dictionarySource, dictionary);

        puzzle.remove(0);//Need to comment out if 1st line of puzzle does
        //not have metadata for puzzle.

        //Routine to obtain row(height) and column(width) dimensions.
        Puzzle.puzzle2DArray(puzzle);
        int C = Puzzle.getPuzzleWidth() + 1;
        int R = Puzzle.getPuzzleHeight() + 1;

        //Routine to determine length of longest word in dictionary. This
        //determines the extent of directional search.
        int a = 0;
        for (String word :
                dictionary) {
            if (word.length() > a) {
                a = word.length();
            }
        }

        //Creates instance of a new dictionary and calls routine to build trie.
        WordDictionary newDict = new WordDictionary();
        for (int i = 0; i < dictionary.size(); i++) {
            String word = dictionary.get(i);
            newDict.addWord(word);
        }


        //The search algorithm to move column and row through the puzzle,
        // includes the direction search from each index.
        for (int row = 0; row < Puzzle.getPuzzleHeight() + 1; row++) {
            for (int col = 0; col < Puzzle.getPuzzleWidth() + 1; col++) {
                for (int dir = 0; dir < 8; dir++) {
                    String word = "";

                    int rd = row;
                    int cd = col;

                    for (int k = 0; k < a; k++) {
                        word = word + Puzzle.getPuzzleChar(rd, cd);
                        testWord(word, newDict, row, col, dir);
                        rd = rd + x[dir];
                        cd = cd + y[dir];
                        // If out of bound break
                        if (rd >= R || rd < 0 || cd >= C || cd < 0) {
                            break;
                        }

                    }
                }
            }
        }

        //This begins the sort routine on the results ArrayList and finally
        // prints out the results.
        newResults.sort(Comparator.comparingInt(Results::getDir));
        newResults.sort(Comparator.comparingInt(Results::getCol));
        newResults.sort(Comparator.comparingInt(Results::getRow));
        newResults.sort(Comparator.comparing(Results::getWord));
        for (Results r :
                newResults) {
            String out = r.getWord() + " " + r.getRow() + " " + r.getCol() +
                    " " + directions.get(r.getDir());
            System.out.println(out);
        }

    }

    /**
     * The try/catch method to read in text file and create an ArrayList.
     * @param file Text file to be scanned in.
     * @param arrayFile New ArrayList to be created.
     */
    private static void buildArray(Path file, ArrayList<String> arrayFile) {
        try (BufferedReader reader = Files.newBufferedReader(file))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayFile.add(line.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("No such file " + e.getMessage());
        }
    }

    /**
     * Routine to add word to results ArrayList if found during search.
     * @param word Word that was found in the dictionary from search.
     * @param newDict Instance of dictionary ("trie") that is being used for
     *                search.
     * @param row Row index of first character of found word.
     * @param col Column index of first character of found word.
     * @param dir Direction of search (as an integer) for the found word.
     */
    private static void testWord(String word, WordDictionary newDict, int row, int col, int dir) {
        if (newDict.search(word)) {
            newResults.add(new Results(word, row, col, dir));
        }
    }
}
