import java.util.HashMap;

/**
 * Class to build trie tree and to search it for word. Code obtained from http:
 * //www.programcreek.com/2014/05/leetcode-add-and-search-word-data-structure
 * -design-java/ and adapted for this program.
 * @author Michael Servilla
 * @version date 2017-04-04
 */
class WordDictionary {
    private final TrieNode GROOT = new TrieNode(); //Instantiates, assigns and
    // finalizes groot node.

    WordDictionary() {
    }

    /**
     * Method to build trie tree.
     * @param word Word from dictionary list is parsed and added to tree.
     */
    void addWord(String word) {
        HashMap<Character, TrieNode> children = GROOT.children; //Assigns root
        //to current children.

        for (int i = 0; i < word.length(); i++) { //Loop to run through word and
            //pull out each character.
            char c = word.charAt(i);

            TrieNode t; //Instantiates temporary node.
            if (children.containsKey(c)) { //Checks to see if character c is
                //present at current node level.
                t = children.get(c); //Assigns current node with character c to
                //temporary node t.
            } else {
                t = new TrieNode(c); //If character c not found at current node
                //level, creates new node with character c to temporary node t.
                children.put(c, t); //Adds current character c and temporary
                //node t children HashMap.
            }

            children = t.children; //Reassigns node to child node using
            //character c as the key, and the node as the value.

            if (i == word.length() - 1) { //Checks to see if end of word is
                //reached, if yes, then returns variable isLeaf as true.
                t.isLeaf = true;
            }
        }
    }

    /**
     * Nested node class.
     */
    public class TrieNode { //Inner class to define node object.
        char c;
        HashMap<Character, TrieNode> children = new HashMap<>();  //Builds child
        // node as a HashMap, character is key, and points to node as value.
        boolean isLeaf; //Variable to indicate end of word.

        TrieNode() { //Default non-parameter constructor used by GROOT.

        }
        TrieNode(char c) { //Constructor to build child nodes.
            this.c = c;
        }
    }


    /**
     *
     * @param word
     * @return
     */
    boolean search(String word) {
        return dfsSearch(GROOT.children, word, 0);
    }

    private boolean dfsSearch(HashMap<Character, TrieNode> children, String word,
                              int start) {
        if (start == word.length()) {
            return children.size() == 0;
        }
        char c = word.charAt(start);

        if (children.containsKey(c)) {
            if (start == word.length() - 1 && children.get(c).isLeaf) {
                return true;
            }
            return dfsSearch(children.get(c).children, word, start + 1);
        } else {
            return false;
        }
    }
}
