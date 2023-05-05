package senarath_chami.river;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Controller {
    private Layout layout;
    private final RiverSim model;

    public Controller(RiverSim model, Layout layout) {
        this.model = model;
        this.layout = layout;
    }

    public EventHandler<MouseEvent> changingLandAreaHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent actionEvent) {
            TileView whichTile = (TileView)actionEvent.getSource();
            int rowNum = GridPane.getRowIndex(whichTile);
            int colNum = GridPane.getColumnIndex(whichTile);
            layout.riverSimView.setDisable(false);

            if (layout.addCheckBox.isSelected()) {
                layout.riverSimView.setDisable(false);
                String checkBoxSelected = ((RadioButton)layout.toggleGroup.getSelectedToggle()).getText();
                layout.model.setTile(colNum, rowNum, checkBoxSelected);
                updateRiverInfoBar();
            } else {
                layout.riverSimView.setDisable(true);
                layout.labelLandInfo.setText(whichTile.LandInfoSection(colNum, rowNum));
            }
        }
    };

    public EventHandler<MouseEvent> nextMonthHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            layout.model.nextMonth();
            updateRiverInfoBar();
        }
    };

    public EventHandler<ActionEvent> resizeHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String newSize = "";
            Object fromWhere = actionEvent.getSource();

            if (fromWhere instanceof Button) {
                Button btnSize = (Button) actionEvent.getSource();
                newSize = btnSize.getText();
            } else if (fromWhere instanceof MenuItem){
                MenuItem mnSize = (MenuItem) actionEvent.getSource();
                newSize = mnSize.getText();
            }

            layout.riverSimView.getChildren().clear();
            layout.riverSimView.getRowConstraints().clear();
            layout.riverSimView.getColumnConstraints().clear();
            switch (newSize) {
                case "5X3" -> {
                    layout.model = new RiverSim(5,3);
                    layout.riverSimView.setNewModel(layout.model);
                    layout.riverSimView.resize(5, 3);
                }
                case "7X5" -> {
                    layout.model = new RiverSim(7, 5);
                    layout.riverSimView.setNewModel(layout.model);
                    layout.riverSimView.resize(7, 5);
                }
                case "9X7" -> {
                    layout.model = new RiverSim(9, 7);
                    layout.riverSimView.setNewModel(layout.model);
                    layout.riverSimView.resize(9, 7);
                }
            };
            updateRiverInfoBar();
        }
    };

    public void updateRiverInfoBar() {
        int time = layout.model.getTime();
        int month = (time % 12) + 1;
        int year = time / 12;

        layout.labelRiverInfo.setText("Year: " + year + " Month: " + month + "\nFilled: " + layout.model.getNumOfFilledTiles() + "\nFunds: $" + layout.model.getFunds() + "k");
    }
}