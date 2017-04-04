import java.util.ArrayList;

/**
 * Class used to create 2D character array of puzzle, and to return requested
 * values.
 * @author Michael Servilla
 * @version date 2017-03-31
 */
class Puzzle {
    private static char [][] myArray;
    private static int puzzleWidth;
    private static int puzzleHeight;

    /**
     * Method to create puzzle, taking ArrayList and changing to 2D character
     * array.
     * @param puzzleArray Takes an ArrayList as string of lines.
     */
    static void puzzle2DArray(ArrayList<String> puzzleArray){
        myArray = new char[puzzleArray.size()][puzzleArray.get(0).length()];
        for (int i = 0; i < puzzleArray.size(); i++) {
            String line = puzzleArray.get(i);
            puzzleHeight = i;
            for (int j = 0; j < line.length(); j++) {
                myArray[i][j] = line.charAt(j);
                puzzleWidth = j;
            }
        }
    }

    /**
     * Method to return character at specified location in the 2D array.
     * @param i i parameter is the requested row.
     * @param j j parameter is the requested column.
     * @return Return character at specific location.
     */
    static char getPuzzleChar(int i, int j) {
        return myArray [i][j];
    }

    /**
     * Method to return width of the puzzle array (number of columns).
     * @return Returns int value of puzzle width.
     */
    static int getPuzzleWidth() {
        return puzzleWidth;
    }

    /**
     * Method to return height of the puzzle array (number of rows).
     * @return Returns int value of puzzle height.
     */
    static int getPuzzleHeight() {
        return puzzleHeight;
    }
}
