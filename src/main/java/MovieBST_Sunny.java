import java.util.ArrayList;

public class MovieBST_Sunny {

    /**
     * Inner node class for BST
     */
    private class Node {
        Movie_Sunny movie;
        Node left;
        Node right;

        Node(Movie_Sunny movie) {
            this.movie = movie;
        }
    }

    private Node root;

    public MovieBST_Sunny() {
        root = null;
    }

    /**
     * Public insert method
     */
    public void insert(Movie_Sunny m) {
        root = insertRec(root, m);
    }

    /**
     * Recursive BST insert
     */
    private Node insertRec(Node current, Movie_Sunny m) {
        if (current == null) {
            return new Node(m);
        }

        if (m.compareTo(current.movie) < 0) {
            current.left = insertRec(current.left, m);
        } else {
            current.right = insertRec(current.right, m);
        }

        return current;
    }

    /**
     * Search for a movie by title
     */
    public Movie_Sunny search(String title) {
        return searchRec(root, title);
    }

    private Movie_Sunny searchRec(Node current, String title) {
        if (current == null) return null;

        int cmp = title.compareToIgnoreCase(current.movie.getTitle());

        if (cmp == 0) return current.movie;
        else if (cmp < 0) return searchRec(current.left, title);
        else return searchRec(current.right, title);
    }

    /**
     * Returns sorted movie titles for GUI catalog
     */
    public ArrayList<String> getSortedTitles() {
        ArrayList<String> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    /**
     * In-order traversal (A–Z)
     */
    private void inorderTraversal(Node current, ArrayList<String> list) {
        if (current == null) return;

        inorderTraversal(current.left, list);
        list.add(current.movie.toString());
        inorderTraversal(current.right, list);
    }
}