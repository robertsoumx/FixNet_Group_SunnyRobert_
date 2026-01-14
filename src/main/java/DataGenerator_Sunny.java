import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Generates a CSV file with 10,000 movies for testing.
 * Standalone utility and is NOT part of the GUI.
 */
public class DataGenerator_Sunny {

    private static final String[] ADJ = {
            "Angry", "Frozen", "Lost", "Eternal",
            "Dark", "Infinite", "Secret", "Pulp"
    };

    private static final String[] NOUN = {
            "Samurai", "Matrix", "Knight", "Hope",
            "Empire", "Love", "Sky", "Fiction"
    };

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("src/main/resources/movies.csv")) {

            Random r = new Random();

            for (int i = 0; i < 10000; i++) {
                String title = ADJ[r.nextInt(ADJ.length)]
                        + " "
                        + NOUN[r.nextInt(NOUN.length)]
                        + " #" + i;

                double rating = 1.0 + r.nextDouble() * 9.0;

                writer.write(title + "," + String.format("%.1f", rating) + "\n");
            }

            System.out.println("Movie database generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}