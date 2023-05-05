package senarath_chami.river;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RiverSimView extends GridPane {

    private RiverSim model;
    public final Controller controller;
    public TileView tileButtons;

    /**
     * Constructor for RiverSimView
     */
    public RiverSimView(RiverSim model, Controller controller) {
        this.model = model;
        this.controller = controller;
        this.maxWidth(Double.MAX_VALUE);
        this.maxHeight(Double.MAX_VALUE);
    }

    /**
     * This function sets the model to a new model
     *
     * @param newModel The new model to be used.
     */
    public void setNewModel(RiverSim newModel) {
        this.model = newModel;
    }

    /**
     * It creates a grid of buttons, with a river running down the middle
     *
     * @param col the number of columns in the grid
     * @param row number of rows
     */
    public void resize (int col, int row) {
        Pane riverPain = new Pane();
        riverPain.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        riverPain.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        double riverThiccness = 100.0/(col * 2);
        double buttonThiccness = (100.0 - riverThiccness)/(col - 1);

        HBox.setHgrow(riverPain, Priority.ALWAYS);

        for (int y = 0; y < col; y++) {
            ColumnConstraints colConstr = new ColumnConstraints();
            if (y == col/2) {
                colConstr.setPercentWidth(riverThiccness);
            } else {
                colConstr.setPercentWidth(buttonThiccness);
            }
            this.getColumnConstraints().add(colConstr);
        }

        for (int k = 0; k < row; k++){
            RowConstraints rowConstr = new RowConstraints();
            rowConstr.setPercentHeight(100.0/row);
            this.getRowConstraints().add(rowConstr);
        }

        this.add(riverPain, col/2, 0, 1, row);

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (i != col/2) {
                    tileButtons = new TileView(this.model.getTile(i, j), controller);
                    // GRADING: SUBJECT
                    this.model.getTile(i, j).addPropertyChangeListener(tileButtons);
                    tileButtons.setTextFill(Color.BLUE);
                    tileButtons.setStyle("-fx-font-weight: bold;");
                    tileButtons.setPrefSize(1000, 1000);
                    this.add(tileButtons, i, j);
                }
            }
        }
    }
}
