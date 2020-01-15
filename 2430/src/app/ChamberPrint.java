package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class ChamberPrint extends Application implements java.io.Serializable {
    /**the stage.*/
    private Stage primaryStage;
    /**the main borderpane for the scene.*/
    private BorderPane root;
    /**To get the data.*/
    private Controller theController;
    /**String for the text in the very middle of the big pane.*/
    private Label middleText = new Label();
    /**Side pane.*/
    private BorderPane pane = new BorderPane();
    /**String for the save location of the file.*/
    private String saveLocation;
    /**String for the load location of the file.*/
    private String loadLocation;
    /**image for floor.*/
    private String floorImage = "/res/res_floor.png";
    /**image for monster.*/
    private String monsterImage = "/res/res_monst.png";
    /**image for treasure.*/
    private String treasureImage = "/res/res_treas.png";
    /**image for door.*/
    private String doorImage = "/res/res_door.png";
    /**image for blankness.*/
    private String whiteImage = "/res/res_white.png";
    /**Vbox for the middle of the main pane. */
    private VBox middle;
    /**The grid that is in the VBox.*/
    private GridPane grid = new GridPane();
    /**Start the GUI.
     * @param assignedStage stage to be assigned.
     */
    @Override
    public void start(Stage assignedStage) {
        theController = new Controller(this);
        primaryStage = assignedStage;
        root = setUpRoot();
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("Chamber & Passage Generation Tool.");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private ImageView viewVisual(String path) {
        Image image = new Image(path);
        ImageView imageview = new ImageView(image);
        return imageview;
    }
    private void createGridPass(Space space) {
        int i;
        int j;
        int startColumn = 4;
        //Clear grid before using
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                grid.add(viewVisual(whiteImage), i, j);
            }
        }
        ArrayList<String> sections = theController.getPassageSections(space);
        ArrayList<String> monsters = theController.getMonsters(space);
        ArrayList<String> treasures = theController.getTreasure(space);
        for (i = 0; i < sections.size(); i++) {
            if (sections.get(i).contains("Monster")) {
                grid.add(viewVisual(monsterImage), startColumn + (i / 10), i % 10);
            } else {
                grid.add(viewVisual(floorImage), startColumn + (i / 10), i % 10);
            }
        }
        for (i = i; i < sections.size() + monsters.size(); i++) {
            grid.add(viewVisual(monsterImage), startColumn + (i / 10), i % 10);
        }
        for (i = i; i < sections.size() + monsters.size() + treasures.size(); i++) {
            grid.add(viewVisual(treasureImage), startColumn + (i / 10), i % 10);
        }
        grid.add(viewVisual(doorImage), startColumn, 0);
        grid.add(viewVisual(doorImage), startColumn + (i / 10), (sections.size() + monsters.size() + treasures.size()) % 10);
        grid.setAlignment(Pos.CENTER);
    }
    private void createGridCham(Space space) {
        int i;
        int j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                grid.add(viewVisual(floorImage), i, j);
            }
        }
        ArrayList<String> monsters = theController.getMonsters(space);
        for (i = 0; i < monsters.size(); i++) {
            grid.add(viewVisual(monsterImage), i / 10, i % 10);
        }
        ArrayList<String> treasures = theController.getTreasure(space);
        for (i = monsters.size(); i < treasures.size() + monsters.size(); i++) {
            grid.add(viewVisual(treasureImage), i / 10, i % 10);
        }
        for (i = 9; i > 9 - space.getDoors().size(); i--) {
            grid.add(viewVisual(doorImage), 9, i);
        }
        grid.setAlignment(Pos.CENTER);
    }
    private VBox setupMiddle() {
        middle = new VBox();
        middle.setAlignment(Pos.CENTER);
        middle.getChildren().addAll(middleText, grid);
        middle.setSpacing(80);
        middle.setPadding(new Insets(10));
        return middle;
    }
    /*private FileChooser getPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Files", "*.ser"),
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            mainStage.display(selectedFile);
        }
    }*/
    private TextField displayText() {
        TextField text = new TextField();
        text.setText("Enter path with the filename.");
        text.setLayoutX(500);
        text.setLayoutY(400);
        return text;
    }
    private Button exitPathButton(Popup pop, TextField field) {
        Button button = new Button();
        button.setText("Done");
        button.setOnAction((ActionEvent event) -> {
            pop.hide();
            saveLocation = field.getCharacters().toString();
            saveFile();
        });
        return button;
    }
    private BorderPane displayGetPath(Popup pop) {
        BorderPane thePane = new BorderPane();
        TextField text = displayText();
        thePane.setCenter(text);
        thePane.setBottom(exitPathButton(pop, text));
        return thePane;
    }
    private void getSaveLocation() {
        Popup pop = new Popup();
        pop.getContent().add(displayGetPath(pop));
        pop.show(primaryStage);
    }
    private void saveFile() {
        int i;
        try {
            FileOutputStream fileOut = new FileOutputStream(saveLocation);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (i = 0; i < 5; i++) {
                out.writeObject(theController.getChamber(i));
            }
            fileOut.close();
        } catch (IOException e) {
        }
        //out.
    }
    private MenuItem saveFiles() {
        MenuItem item = new MenuItem("Save file");
        item.setOnAction((ActionEvent event) -> {
            getSaveLocation();
        });
        return item;
    }
    private String getSelectedLoadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Files", "*.ser"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        /*if (selectedFile != null) {
            primaryStage.display(selectedFile);
        }
         */
        return selectedFile.getName();
    }
    private BorderPane loadMenu(Popup pop) {
        BorderPane menu = new BorderPane();
        loadLocation = getSelectedLoadFile();
        return menu;
    }
    private void getLoadLocation() {
        Popup pop = new Popup();
        pop.getContent().add(loadMenu(pop));
        pop.show(primaryStage);
    }
    private MenuItem loadFiles() {
        MenuItem item = new MenuItem("Load file");
        item.setOnAction((ActionEvent event) -> {
            getLoadLocation();
        });
        return item;
    }
    private MenuButton setupSaveOption() {
        int i;
        ArrayList<MenuItem> options = new ArrayList<MenuItem>();
        options.add(saveFiles());
        options.add(loadFiles());
        MenuButton menu = new MenuButton();
        menu.getItems().addAll(options);
        menu.setText("File");
        return menu;
    }
    private BorderPane setUpRoot() {
        BorderPane temp = pane;
        temp.setTop(setupSaveOption());
        temp.setLeft(setupLeftScrollPane(temp));
        temp.setCenter(setupMiddle());

        //TilePane room = createTilePanel();
        /*GridPane room = new ChamberView(4,4);*/
        return temp;
    }
    //Create button for the left list of spaces. Once clicked then
    private Button createSpaceButton(Space value, BorderPane temp) {
        Button button = new Button();
        if (value.getDescription().contains("Passage")) {
            button.setText("Passage");
            button.setOnAction((ActionEvent event) -> {
                middleText.setText(value.getDescription());
                pane.setRight(setupRightSide(value));
                temp.setBottom(setupBottom(value));
                createGridPass(value);
            });
        } else {
            button.setText("Chamber");
            button.setOnAction((ActionEvent event) -> {
                middleText.setText(value.getDescription());
                pane.setRight(setupRightSide(value));
                temp.setBottom(setupBottom(value));
                createGridCham(value);
            });
        }

        return button;
    }
    //Create the list for the spaces
    private ListView<Button> setupLeftScrollPane(BorderPane bp) {
        int i;
        ArrayList<Button> test = new ArrayList<Button>();
        for (i = 0; i < 5; i++) {
            test.add(createSpaceButton(new Chamber(), bp));
        }
        for (i = 0; i < theController.passAmount(); i++) {
            test.add(createSpaceButton(theController.createPassage(), bp));
        }
        ListView<Button> list = new ListView<Button>(FXCollections.observableArrayList(test));
        list.setPrefSize(100, 100);
        return list;
    }
    private Button exitDoorButton(Popup popup, BorderPane menu) {
        Button exit = new Button();
        exit.setText("Done.");
        exit.setOnAction((ActionEvent event) -> {
            popup.hide();
        });
        menu.setAlignment(exit, Pos.BOTTOM_CENTER);
        return exit;
    }
    private Label doorDescription(Door door) {
        Label desc = new Label(door.getDescription());
        return desc;
    }
    private Label connectionDescription(Door door, Space value) {
        Label space;
        if (value.getDescription().contains("Passage")) {
            space = new Label("Door is also connected to:\n\n" + theController.getChamber((int) Math.random() % 5).getDescription());
        } else {
            space = new Label("Door is also connected to:\n\n" + theController.createPassage().getDescription());
        }
        return space;
    }
    private VBox allDoorDescription(Door door, Space value) {
        VBox all = new VBox();
        all.getChildren().addAll(doorDescription(door), connectionDescription(door, value));
        all.setAlignment(Pos.CENTER);
        all.setSpacing(80);
        all.setPadding(new Insets(10));
        return all;
    }
    private BorderPane doorDisplayPane(Door door, Popup popup, Space value) {
        BorderPane menu = new BorderPane();
        menu.setStyle(" -fx-background-color: white;");
        Label title = new Label("Description for the door.");
        menu.setTop(title);
        menu.setAlignment(title, Pos.TOP_CENTER);
        menu.setCenter(allDoorDescription(door, value));
        menu.setBottom(exitDoorButton(popup, menu));
        menu.setPrefSize(600, 600);
        return menu;
    }
    private Popup displayDoor(Door door, Space value) {
        Popup pop = new Popup();
        pop.getContent().add(doorDisplayPane(door, pop, value));
        return pop;
    }
    //Make a menuitem for each door
    private MenuItem makeDoorItem(Door door, Space value) {
        MenuItem menu = new MenuItem("Door");
        menu.setOnAction((ActionEvent event) -> {
            Popup popup = displayDoor(door, value);
            popup.show(primaryStage);
        });
        return menu;
    }
    //Create a menubutton for the doors
    private MenuButton setupRightSide(Space cham) {
        int i;

        ArrayList<MenuItem> doors = new ArrayList<MenuItem>();
        if (cham.getDoors().size() == 0) {
            doors.add(makeDoorItem(new Door(), cham));
            doors.add(makeDoorItem(new Door(), cham));
        } else {
            for (i = 0; i < cham.getDoors().size(); i++) {
                doors.add(makeDoorItem(cham.getDoors().get(i), cham));
            }
        }
        MenuButton menu = new MenuButton();
        menu.getItems().addAll(doors);
        menu.setText("List of doors");
        return menu;
    }
    private Button setupCloseEdit(Popup popup, BorderPane theMenu) {
        Button button = new Button();
        button.setText("       Confirm       ");
        button.setOnAction((ActionEvent event) -> {
            popup.hide();
        });
        theMenu.setAlignment(button, Pos.BOTTOM_CENTER);
        return button;
    }
    //Create button for each monster they choose. Then change it once chosen
    private Button editMonsTreaButton(String desc, Space alter, BorderPane menu, boolean isMonster) {
        Button button = new Button();
        button.setText(desc);
        if (isMonster) {
            button.setOnAction((ActionEvent event) -> {
                theController.changeMonster(desc, alter);
                middleText.setText(alter.getDescription());
                if (alter.getDescription().contains("Passage")) {
                    createGridPass(alter);
                } else {
                    createGridCham(alter);
                }
                menu.setCenter(new Label("Monster Added."));
            });
        } else {
            button.setOnAction((ActionEvent event) -> {
                theController.changeTreasure(desc, alter);
                middleText.setText(alter.getDescription());
                if (alter.getDescription().contains("Passage")) {
                    createGridPass(alter);
                } else {
                    createGridCham(alter);
                }
                menu.setCenter(new Label("Treasure Added."));
            });
        }
        return button;
    }
    //Setup List for monsters
    private ListView<Button> listOfEdit(Space alter, BorderPane bp, boolean isMonster) {
        int i;
        ArrayList<Button> list = new ArrayList<Button>();
        if (isMonster) {
            for (i = 0; i < 19; i++) {
                Button buttonMonster = editMonsTreaButton(theController.getMonstDesc(i), alter, bp, isMonster);
                list.add(buttonMonster);
            }
        } else {
            for (i = 0; i < 8; i++) {
                Button buttonTreasure = editMonsTreaButton(theController.getTreasDesc(i), alter, bp, isMonster);
                list.add(buttonTreasure);
            }
        }
        ListView<Button> all = new ListView<Button>(FXCollections.observableArrayList(list));
        all.setPrefSize(200, 500);
        return all;
    }
    private Button delMonsTreasButton(String desc, Space space, BorderPane menu, boolean isMonster) {
        Button button = new Button();
        button.setText(desc);
        if (isMonster) {
            button.setOnAction((ActionEvent event) -> {
                theController.removeMonstDesc(desc, space);
                middleText.setText(space.getDescription());
                if (space.getDescription().contains("Passage")) {
                    createGridPass(space);
                } else {
                    createGridCham(space);
                }
                menu.setCenter(new Label("Deleted."));
            });
        } else {
            button.setOnAction((ActionEvent event) -> {
                theController.removeTreasDesc(desc, space);
                middleText.setText(space.getDescription());
                if (space.getDescription().contains("Passage")) {
                    createGridPass(space);
                } else {
                    createGridCham(space);
                }
                menu.setCenter(new Label("Deleted."));
            });
        }
        return button;
    }
    private ListView<Button> delMonstTreasList(Space space, BorderPane thePane, boolean isMonster) {
        int i;
        ArrayList<Button> list = new ArrayList<Button>();
        if (isMonster) {
            ArrayList<String> monst = theController.getMonsters(space);
            for (i = 0; i < monst.size(); i++) {
                list.add(delMonsTreasButton(monst.get(i), space, thePane, true));
            }
        } else {
            ArrayList<String> treas = theController.getTreasure(space);
            for (i = 0; i < treas.size(); i++) {
                list.add(delMonsTreasButton(treas.get(i), space, thePane, false));
            }
        }
        ListView<Button> all = new ListView<Button>(FXCollections.observableArrayList(list));
        all.setPrefSize(200, 500);
        return all;
    }
    private BorderPane deleteMenu(Space space, Popup popup) {
        BorderPane thePane = new BorderPane();
        Label title = new Label("Click which you would like to delete.");
        thePane.setStyle(" -fx-background-color: gray;");
        thePane.setTop(title);
        thePane.setAlignment(title, Pos.TOP_CENTER);
        thePane.setLeft(delMonstTreasList(space, thePane, true));
        thePane.setRight(delMonstTreasList(space, thePane, false));
        thePane.setBottom(setupCloseEdit(popup, thePane));
        thePane.setPrefSize(600, 600);
        return thePane;
    }
    private Popup createDeletePopup(Space space) {
        Popup pop = new Popup();
        pop.getContent().add(deleteMenu(space, pop));
        return pop;
    }
    //Create a delete monster button
    private Button delMonst(Space space, BorderPane menu) {
        Button button = new Button();
        button.setText("Delete Monster or Treasure");
        button.setOnAction((ActionEvent event) -> {
            Popup popup = createDeletePopup(space);
            popup.show(primaryStage);
        });
        menu.setAlignment(button, Pos.BOTTOM_CENTER);

        return button;
    }
    private ScrollPane bottomRow(Popup window, BorderPane theMenu, Space space) {
        HBox theRoot = new HBox();
        theRoot.getChildren().addAll(delMonst(space, theMenu), setupCloseEdit(window, theMenu));
        theRoot.setSpacing(80);
        theRoot.setPadding(new Insets(10));
        ScrollPane row = new ScrollPane();
        row.setPrefSize(50, 50);
        row.setContent(theRoot);
        return row;
    }
    //Make BorderPane for the popup edit screen
    private BorderPane editMenu(Popup window, Space alter) {
        BorderPane theMenu = new BorderPane();
        Label label = new Label("Click what you want to add");
        theMenu.setStyle(" -fx-background-color: white;");
        theMenu.setAlignment(label, Pos.TOP_CENTER);
        theMenu.setLeft(listOfEdit(alter, theMenu, true));
        theMenu.setRight(listOfEdit(alter, theMenu, false));
        theMenu.setBottom(bottomRow(window, theMenu, alter));
        theMenu.setTop(label);
        theMenu.setPrefSize(600, 600);
        return theMenu;
    }
    //Create the popup for editing
    private Popup setupEditPopUp(Space alter) {
        Popup window = new Popup();
        window.getContent().add(editMenu(window, alter));
        return window;
    }
    //Setup the bottom of the GUI
    private Button setupBottom(Space alter) {
        Button button = new Button();
        button.setText("                  Edit                  ");
        button.setOnAction((ActionEvent event) -> {
            Popup window = setupEditPopUp(alter);
            window.show(primaryStage);
        });
        pane.setMargin(button, new Insets(20, 30, 30, 20));
        pane.setAlignment(button, Pos.BOTTOM_CENTER);
        return button;
    }
    /**Launch GUI.
     * @param args required
     */
    public static void main(String[] args) {
        launch(args);
    }
}
