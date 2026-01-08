public class Movie {
    String title;
    double rating;

    public Movie(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    // Returns negative if this < other, positive if this > other
    public int compareTo(Movie other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return title + " (" + rating + ")";
    }
}