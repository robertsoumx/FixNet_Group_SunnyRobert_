import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator_Sunny {
    // Generates 10,000 fake movies for testing
    private static final String[] ADJ = {"Angry", "Frozen", "Lost", "Eternal", "Dark", "Infinite", "Secret", "Pulp"};
    private static final String[] NOUN = {"Samurai", "Matrix", "Knight", "Hope", "Empire", "Love", "Sky", "Fiction"};

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("src/main/resources/movies.csv")) {
            Random r = new Random();
            for (int i = 0; i < 10000; i++) {
                String title = ADJ[r.nextInt(ADJ.length)] + " " + NOUN[r.nextInt(NOUN.length)] + " #" + i;
                writer.write(title + "," + String.format("%.1f", 1.0 + r.nextDouble() * 9.0) + "\n");
            }
            System.out.println("✅ FixNet Database Generated (10k movies).");
        } catch (IOException e) { e.printStackTrace(); }
    }
}