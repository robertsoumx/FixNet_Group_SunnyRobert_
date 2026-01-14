public class Movie_Sunny {
    String title;
    double rating;

    public Movie_Sunny(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    // Returns negative if this < other, positive if this > other
    public int compareTo(Movie_Sunny other) {return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {return title + " (" + rating + ")";
    }
}