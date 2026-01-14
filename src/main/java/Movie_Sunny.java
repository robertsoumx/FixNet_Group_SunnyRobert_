public class Movie_Sunny implements Comparable<Movie_Sunny> {

    private String title;
    private double rating;

    public Movie_Sunny(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    /**
     * Used by BST ordering (alphabetical by title)
     */
    @Override
    public int compareTo(Movie_Sunny other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    /**
     * IMPORTANT:
     * This string is what appears in the GUI ListView
     * and what FixNet_Robert uses as a HashMap key.
     */
    @Override
    public String toString() {
        return title + " (" + rating + ")";
    }
}