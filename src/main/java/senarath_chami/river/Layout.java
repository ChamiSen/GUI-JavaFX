package senarath_chami.river;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;


public class Layout {
    public Controller controller;
    private final VBox root;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private VBox tg;
    private VBox actionCommands;
    public Button nextMonthButton;
    public CheckBox addCheckBox;
    public ToggleGroup toggleGroup;

    public Button button53;
    public Button button75;
    public Button button97;
    public Label labelRiverInfo;
    public Label labelLandInfo;
    public RiverSim model;
    public RiverSimView riverSimView = new RiverSimView(null, null);

    /**
     * Constructor for Layout
     */
    public Layout(RiverSim riversim) {
        this.controller = new Controller(riversim, this);
        VBox root = new VBox();
        VBox.setVgrow(root, Priority.ALWAYS);
        this.root = root;
        this.root.setAlignment(Pos.BOTTOM_CENTER);
        this.model = riversim;
    }

    /**
     * This function creates the scene and the stage, and then calls the functions that create the menu and the info bars
     *
     * @param primaryStage The stage that the scene is being shown on.
     */
    public void show(Stage primaryStage) {
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("RiverSim Simulator");
        createMenu();
        notRiverInfoBar();
        riverInfoBar();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * It creates a menu bar with a menu called "Resize" that has three menu items called "5X3", "7X5", and "9X7". Each
     * menu item has an action handler that calls the resizeHandler function in the controller
     */
    private void createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu resizingMenu = new Menu("Resize");
        resizingMenu.setStyle("-fx-font-weight: bold;");
        MenuItem resize53Menu = new MenuItem("5X3");
        MenuItem resize75Menu = new MenuItem("7X5");
        MenuItem resize97Menu = new MenuItem("9X7");
        resize53Menu.setOnAction(controller.resizeHandler);
        resize75Menu.setOnAction(controller.resizeHandler);
        resize97Menu.setOnAction(controller.resizeHandler);
        resizingMenu.getItems().addAll(resize53Menu, resize75Menu, resize97Menu);

        menuBar.getMenus().add(resizingMenu);

        root.getChildren().add(menuBar);
    }

    /**
     * This function creates a horizontal box that contains the river view and the right most boxes
     */
    private void notRiverInfoBar() {
        HBox notRiverInfo = new HBox();
        HBox.setHgrow(notRiverInfo, Priority.ALWAYS);
        notRiverInfo.setAlignment(Pos.CENTER);
        notRiverInfo.getChildren().add(riverView());
        notRiverInfo.getChildren().add(rightMostBoxes());
        this.root.getChildren().add(notRiverInfo);
    }

    /**
     * This function creates a new RiverSimView object and sets the model to the current model. It then resizes the view to
     * the current model's dimensions and sets the horizontal and vertical growth to always
     *
     * @return A GridPane with the riverSimView in it.
     */
    private GridPane riverView () {
        riverSimView = new RiverSimView(model, controller);
        riverSimView.setNewModel(model);
        riverSimView.resize(model.getCol(), model.getRow());
        GridPane.setHgrow(riverSimView, Priority.ALWAYS);
        GridPane.setVgrow(riverSimView, Priority.ALWAYS);
        HBox.setHgrow(riverSimView, Priority.ALWAYS);
        return riverSimView;
    }

    /**
     * This function creates a VBox that contains the landInfoBar and actionCommandsBar
     *
     * @return A VBox with two HBoxes inside of it.
     */
    private VBox rightMostBoxes() {
        VBox rightMost = new VBox();
        rightMost.setMinWidth(180);
        rightMost.getChildren().add(landInfoBar());
        rightMost.getChildren().add(actionCommandsBar());
        return rightMost;
    }

    /**
     * It creates a VBox, sets the alignment to center, sets the VBox to grow vertically, creates a label, and adds the
     * label to the VBox
     *
     * @return A VBox with a label inside of it.
     */
    private VBox landInfoBar() {
        VBox landInfo = new VBox();
        landInfo.setAlignment(Pos.CENTER);
        VBox.setVgrow(landInfo, Priority.ALWAYS);
        labelLandInfo = new Label("Unused\nLast changed: 0-1\nAge: 0-1\nTotal Cost: $0k\nTotal Revenue: $0k\nX: 0 Y: 0");
        landInfo.getChildren().addAll(labelLandInfo);

        return landInfo;
    }

    /**
     * This function creates a VBox that contains a button to go to the next month, a toggle group of radio buttons to
     * select the type of land to add, a checkbox to add land, and a HBox that contains buttons to resize the grid
     *
     * @return A VBox that contains the buttons and checkboxes that are used to control the simulation.
     */
    private VBox actionCommandsBar() {
        this.actionCommands = new VBox();
        this.actionCommands.setAlignment(Pos.CENTER);
        VBox.setVgrow(this.actionCommands, Priority.ALWAYS);

        nextMonthButton = new Button("Next Month");
        nextMonthButton.setStyle("-fx-font-weight: bold;");
        nextMonthButton.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        nextMonthButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        nextMonthButton.setTextFill(Color.WHITE);
        nextMonthButton.setOnMouseClicked(controller.nextMonthHandler);

        toggleGroup = new ToggleGroup();
        RadioButton agricultureRB = new RadioButton("Agriculture");
        agricultureRB.setToggleGroup(toggleGroup);
        agricultureRB.setSelected(true);
        RadioButton recreationRB = new RadioButton("Recreation");
        recreationRB.setToggleGroup(toggleGroup);
        RadioButton unusedRB = new RadioButton("Unused");
        unusedRB.setToggleGroup(toggleGroup);

        this.tg = new VBox();
        this.tg.setAlignment(Pos.CENTER);
        this.tg.getChildren().addAll(agricultureRB, recreationRB, unusedRB);

        addCheckBox = new CheckBox("Add");

        HBox resize = new HBox();
        Label label = new Label("Resize:       ");
        label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(resize, Priority.ALWAYS);
        button53 = new Button("5X3");
        button75 = new Button("7X5");
        button97 = new Button("9X7");
        button53.setOnAction(controller.resizeHandler);
        button75.setOnAction(controller.resizeHandler);
        button97.setOnAction(controller.resizeHandler);

        button53.setStyle("-fx-font-weight: bold;");
        button75.setStyle("-fx-font-weight: bold;");
        button97.setStyle("-fx-font-weight: bold;");

        button53.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        button53.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button75.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        button75.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button97.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        button97.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        resize.getChildren().addAll(label, button53, button75, button97);
        resize.setAlignment(Pos.CENTER);
        actionCommands.setAlignment(Pos.CENTER);
        Region ND = new Region();
        VBox.setVgrow(ND, Priority.ALWAYS);
        Region SD = new Region();
        VBox.setVgrow(SD, Priority.ALWAYS);
        Region NE = new Region();
        VBox.setVgrow(NE, Priority.ALWAYS);

        actionCommands.getChildren().addAll(nextMonthButton, ND, this.tg, SD, addCheckBox, NE, resize);
        return actionCommands;
    }

    /**
     * It creates a VBox, adds a label to it, and adds the VBox to the root
     */
    private void riverInfoBar() {
        VBox riverInfo = new VBox();
        riverInfo.setAlignment(Pos.BOTTOM_CENTER);
        labelRiverInfo = new Label("Year: 0 Month: 1\nFilled: 0\nFunds: $0k");
        labelRiverInfo.setMinHeight(65);
        labelRiverInfo.setPadding(new Insets(5));
        VBox.setVgrow(riverInfo, Priority.ALWAYS);
        riverInfo.getChildren().add(labelRiverInfo);
        this.root.getChildren().add(riverInfo);
    }
}