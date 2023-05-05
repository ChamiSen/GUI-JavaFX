package senarath_chami.river;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class TileView extends Button implements PropertyChangeListener {
    private final Tile land;

    /**
     * Constructor for TileView
     */
    public TileView(Tile land, Controller controller) {
        this.land = land;
        setTextAlignment(TextAlignment.CENTER);
        setText(land.toString());
        setOnMouseClicked(controller.changingLandAreaHandler);
    }

    @Override
    // This is a method that is being used to change the text of the button.
    // GRADING: OBSERVE
    public void propertyChange(PropertyChangeEvent evt) {
        LandArea newVal = (LandArea) evt.getNewValue();
        if (Objects.equals(evt.getPropertyName(), "landType")) {
            setText(newVal.toString());
        }

        if (Objects.equals(evt.getPropertyName(), "nextMonth")) {
            setText(land.getName().charAt(0) + "\n" + land.getChangeMonthInfo());
        }
    }

    /**
     * This function returns a string that contains the information of the land section at the given column and row number
     *
     * @param colNum The column number of the land you want to get the information of.
     * @param rowNum The row number of the land you want to get the information of.
     * @return The landInfoSection method is being returned.
     */
    public String LandInfoSection(int colNum, int rowNum) {
        return land.landInfoSection(colNum, rowNum);
    }
}