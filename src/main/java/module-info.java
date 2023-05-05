module senarath_chami.river {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens senarath_chami.river to javafx.fxml;
    exports senarath_chami.river;
}