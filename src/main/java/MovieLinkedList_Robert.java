import java.util.ArrayList;

public class MovieLinkedList_Robert {

    // TODO: Define your inner Node class here

    public static class Node {
        Movie_Sunny nextMovie;
        Node nextNode;
        public Node(Movie_Sunny nextOneMovie, Node nextOneNode) {
            this.nextMovie = nextOneMovie;
            this.nextNode = nextOneNode;
        }
    }

    public Movie_Sunny removeFirst() {
        if (head == null) return null;
        Movie_Sunny removed = head.nextMovie;
        head = head.nextNode;
        return removed;
    }

    private Node head;
    public MovieLinkedList_Robert() {
        head = null;
    }

    public void addFirst(Movie_Sunny m) {
        // TODO: Implement adding to the front of the list (O(1))
        // 1. Create new node
        Node newNode = new Node(m, null);
        // 2. Link new node to head
        newNode.nextNode = head;
        // 3. Update head
        head = newNode;
    }

    // Helper for the GUI
    public ArrayList<String> getHistoryList() {
        ArrayList<String> result = new ArrayList<>();
        // TODO: Iterate through your list and add .toString() to result
        Node current = head;
        while (current != null) {
            result.add(current.nextMovie.toString());
            current = current.nextNode;
        }
        return result;
    }
}