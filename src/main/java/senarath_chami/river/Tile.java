package senarath_chami.river;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tile {
    LandArea landType;
    PropertyChangeSupport support;

    /**
     * Constructor for Tile
     */
    public Tile() {
        this.landType = new Unused(0);
        support = new PropertyChangeSupport(this);
    }

    /**
     * When the landType property is changed, fire a property change event.
     *
     * @param landType The type of land area.
     */
    public void setLandType(LandArea landType) {
        // GRADING: TRIGGER
        support.firePropertyChange("landType", this.landType, landType);
        this.landType = landType;
    }

    @Override
    // Overriding the toString method.
    public String toString() {
        return this.landType.toString();
    }

    /**
     * Add a listener to the list of listeners that will be notified when a property changes.
     *
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // GRADING: SUBJECT
        support.addPropertyChangeListener(listener);
    }

    /**
     * NextMonth() returns the amount of funds the land type has after the next month.
     *
     * @return The funds for the land type.
     */
    public int nextMonth() {
        int funds = landType.nextMonth();
        // GRADING: TRIGGER
        support.firePropertyChange("nextMonth", null, null);
        return funds;
    }

    /**
     * When the user clicks the resize button, the program will fire a property change event to the listeners, which will
     * then call the resizeTiles() function in the controller.
     */
    public void resizeTiles() {
        // GRADING: TRIGGER
        support.firePropertyChange("resizeTiles", this.landType, landType);
    }

    /**
     * Get the name of the land type.
     *
     * @return The name of the land type.
     */
    public String getName() {
        return landType.getName();
    }

    /**
     * > This function returns the change month info of the land type
     *
     * @return The changeMonthInfo method is being returned.
     */
    public String getChangeMonthInfo() {
        return landType.getChangeMonthInfo();
    }

    /**
     * This function returns the land type of the land at the given column and row
     *
     * @param colNum the column number of the land
     * @param rowNum the row number of the land
     * @return The landInfoSection method is being returned.
     */
    public String landInfoSection(int colNum, int rowNum) {
        return landType.landInfoSection(colNum, rowNum);
    }
}
