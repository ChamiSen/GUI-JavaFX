/**
 * RiverSim - JavaFX
 * Chami Senarath
 *
 * Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is
 * working for partial credit.
 *
 *
 * DONE Followed the class OOP diagram
 * DONE Observer pattern (ignores tiers)
 *
 *
 * 1.	Tier: Views and animal
 * DONE a. All objects (ignoring the sim area)
 * DONE b. Have a starting number of tiles in sim area
 * DONE c. Able to add/remove a land area properly
 * DONE d. Info bar listed correctly with all the required elements
 * DONE e. Tile Text correct in land area
 * DONE f. Tile Text correct for each for all rectangles
 * DONE g. Radio buttons update properly
 * DONE h. Selecting a rectangle without “add” updates the land area info
 *
 *
 *
 * 2a Tier: Advanced functionality
 * DONE a. Next month button has some noticeable effect
 * DONE b. Land areas updated properly on “next”
 * DONE c. Sim info bar updated properly
 * DONE d. Selecting a tile after an update shows the new information
 *
 *
 *
 * 2b: Layout
 * DONE a. Location of all items in correct spot
 * DONE b. Layout still correct on window resize
 * DONE c. Resize grid at minimum resets the grid and info
 * DONE d. Everything still working that is listed above with resize
 *
 *
 * Final Tier: Extensions 30
 * Extension 1: 2a 5 points (X, Y) coordinate: When you click on a tile, which X, Y coordinate is selected is shown in
 *              the land info area. Coordinate starts from 0. Tiles have the default blue outline.
 * Extension 2: 2e 5 points Button & tile appearance with style: Different colors and a solid black border have been
 *              used in resize buttons and the next month button. Resize buttons texts, tile texts, next month button
 *              text, and resize menu item texts are bolded.
 * Extension 3: 2g 5 points Disabling land areas: If you uncheck the Add check box and try to click on a tile, it will
 *              freeze.
 * Extension 4: 3b 15 points Menu bar: The menu bar is another place where you can handle the 3 resize options. Click on
 *              each of them, the river view will change accordingly.
 *
 *
 * The grade you compute is the starting point for course staff, who reserve the right to change the grade if they
 * disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the
 * submission process, naming issues, etc.
 */

package senarath_chami.river;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // The main method of the application.
    public void start(Stage primaryStage) {
        RiverSim model = new RiverSim(5, 3);
        Layout layout = new Layout(model);
        layout.show(primaryStage);
    }
}