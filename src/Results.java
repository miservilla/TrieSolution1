
/**
 * Class for creating instance of result object from word search. Constructor
 * parameters include the located word, its initial row position, its initial
 * column position, and direction of search. The result object is stored in an
 * ArrayList.
 * @author Michael Servilla
 * @version date 2017-03-31
 */
class Results {
    private String word;
    private int row;
    private int col;
    private int dir;

    Results(String word, int row, int col, int dir) {
        this.word = word;
        this.row = row;
        this.col = col;
        this.dir = dir;
    }

    /**
     * Method to return string word to calling code.
     * @return Returns the found word as a string.
     */
    String getWord() {
        return word;
    }

    /**
     * Method to return int row location to calling code.
     * @return Returns the row number as an int.
     */
    int getRow() {
        return row;
    }

    /**
     * Method to return int column location to calling code.
     * @return Returns the column number as an int.
     */
    int getCol() {
        return col;
    }

    /**
     * Method to return int direction value to calling code.
     * @return Returns the direction value as an int.
     */
    int getDir() {
        return dir;
    }
}
