import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FixNet_Robert extends Application {

    // --- YOUR BACKEND ---
    private MovieBST_Sunny catalogBST;
    private MovieLinkedList_Robert historyList;
    private HashMap<String, Movie_Sunny> movieLookup;

    // --- GUI STATE ---
    private ObservableList<String> guiCatalog;
    private ObservableList<String> guiHistory;
    private ListView<String> catalogView;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        catalogBST = new MovieBST_Sunny();
        historyList = new MovieLinkedList_Robert();
        movieLookup = new HashMap<>();
        guiCatalog = FXCollections.observableArrayList();
        guiHistory = FXCollections.observableArrayList();

        // 1. Load the data
        loadDataFromCSV();

        // 2. Build Layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        Label header = new Label("FixNet Admin Panel");
        header.setStyle("-fx-font-size: 24px; -fx-text-fill: #E50914; -fx-font-weight: bold;");

        // Search Bar
        HBox searchBox = new HBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search 10k movies...");
        Button searchBtn = new Button("Find");
        searchBox.getChildren().addAll(searchField, searchBtn);
        searchBox.setAlignment(Pos.CENTER);

        VBox topContainer = new VBox(10, header, searchBox);
        topContainer.setAlignment(Pos.CENTER);
        root.setTop(topContainer);

        // Center Split
        SplitPane split = new SplitPane();
        split.setStyle("-fx-box-border: transparent; -fx-background-color: transparent;");

        catalogView = new ListView<>(guiCatalog);
        VBox leftBox = new VBox(5, new Label("Catalog (A-Z)"), catalogView);

        ListView<String> historyView = new ListView<>(guiHistory);
        VBox rightBox = new VBox(5, new Label("Recently Watched"), historyView);

        split.getItems().addAll(leftBox, rightBox);
        root.setCenter(split);

        // Watch Button
        Button watchBtn = new Button("WATCH SELECTED");
        watchBtn.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-weight: bold;");
        watchBtn.setMaxWidth(Double.MAX_VALUE);
        root.setBottom(watchBtn);

        // Delete Button
        Button deleteBtn = new Button("DELETE TOP HISTORY");
        deleteBtn.setMaxWidth(Double.MAX_VALUE);

        // Layout
        VBox bottomBox = new VBox(8, watchBtn, deleteBtn);
        bottomBox.setPadding(new Insets(10));
        root.setBottom(bottomBox);

        // --- EVENT HANDLERS (CONNECT YOUR CODE HERE) ---

        watchBtn.setOnAction(e -> {
            String selectedStr = catalogView.getSelectionModel().getSelectedItem();
            if (selectedStr != null) {
                Movie_Sunny m = movieLookup.get(selectedStr);
                // TODO: Uncomment after implementing Linked List
                historyList.addFirst(m);
                guiHistory.setAll(historyList.getHistoryList());
            }
        });

        deleteBtn.setOnAction(e -> {
            historyList.removeFirst();
            guiHistory.setAll(historyList.getHistoryList());
        });

        searchBtn.setOnAction(e -> {
            String query = searchField.getText();
            // TODO: Uncomment after implementing BST Search
            Movie_Sunny result = catalogBST.search(query);
            if (result != null) {
                catalogView.getSelectionModel().select(result.toString());
                catalogView.scrollTo(result.toString());
            }
        });

        Scene scene = new Scene(root, 800, 600);
        if(getClass().getResource("/style_Robert.css") != null)
            scene.getStylesheets().add(getClass().getResource("/style_Robert.css").toExternalForm());

        primaryStage.setTitle("FixNet - Definitely Not Netflix");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadDataFromCSV() {
        try {
            InputStream is = getClass().getResourceAsStream("/movies.csv");
            if (is == null) { System.out.println("Run DataGenerator first!"); return; }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Movie_Sunny m = new Movie_Sunny(parts[0], Double.parseDouble(parts[1]));
                movieLookup.put(m.toString(), m);
                // TODO: Uncomment after implementing BST
                catalogBST.insert(m);
            }
            // TODO: Uncomment after implementing BST
            guiCatalog.setAll(catalogBST.getSortedTitles());
        } catch (Exception e) { e.printStackTrace(); }
    }
}