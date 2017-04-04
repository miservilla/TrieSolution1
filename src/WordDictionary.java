import java.util.HashMap;
import java.util.Map;

/**
 * http://www.programcreek.com/2014/05/leetcode-add-and-search-word-data-structure-design-java/
 * @author Michael Servilla
 * @version date 2017-04-04
 */
class WordDictionary {
    private TrieNode root;

    WordDictionary() {
        root = new TrieNode();
    }
    void addWord(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            if (i == word.length() - 1) {
                t.isLeaf = true;
            }
        }
    }
    public class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf;

        TrieNode() {

        }
        TrieNode(char c) {
            this.c = c;
        }
    }

    boolean search(String word) {
        return dfsSearch(root.children, word, 0);
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
        } else if (c== '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child :
                    children.entrySet()) {
                if (start == word.length() - 1 && child.getValue().isLeaf) {
                    return true;
                }
                if(dfsSearch(child.getValue().children, word, start + 1)){
                    result = true;
                }
            }
            return result;
        } else {
            return false;
        }
    }
}
