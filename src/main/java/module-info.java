module org.example.fixnet_group_sunnyrobert_ {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fixnet_group_sunnyrobert_ to javafx.fxml;
    exports org.example.fixnet_group_sunnyrobert_;
}