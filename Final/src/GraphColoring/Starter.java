package GraphColoring;
/*
* Short summary:
* This is the class that starts the application. It creates the stage, and launches it.
*/

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class Starter extends Application{
    public ChromaticManager chromaticManager = new ChromaticManager(System.getProperty("user.dir")+"/Graphs");
    private MainInterface mainInterface = new MainInterface(this);
    private Game2 game2 = new Game2(this);
    private Scene scene;
    private Stage stage;
    
    //launches the application:
    public static void main(String[] args){
        launch(args);
    }
    @Override
    //first, starts with the listener method, then edits the stage (sets the scene and shows the stage)
    public void start(Stage primaryStage) {
        listener();
        stage=primaryStage;
        scene = mainInterface.getScene();
        stage.setTitle("GraphColoring");
        stage.setScene(scene);
        mainInterface.test();
        stage.show();
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

    }
    //gives an arraylist of all the graphs
    public ArrayList giveGraphs(){
        return chromaticManager.listFiles();
    }
    //adds a listener to the mainInterface, so the game will be started when the user has put in everything in the main menu, and submitted it
    public void listener(){
        mainInterface.sceneFinished.addListener((observable, oldValue, newValue) -> {
            scene=game2.giveScene();
            stage.setWidth(mainInterface.getSize().width);
            stage.setHeight(mainInterface.getSize().height);
            stage.setScene(scene);
            game2.graphMode=mainInterface.graphMode;
            game2.selectedSize=mainInterface.selectedSize;
            game2.textFieldVertices.setText(Integer.toString(mainInterface.selectedVertices));
            game2.textFieldEdges.setText(Integer.toString(mainInterface.selectedEdges));
            game2.myGraph=mainInterface.selectedGraph;
            game2.gamemode=mainInterface.gameMode;
            game2.setSize(mainInterface.getSize().width,mainInterface.getSize().height);
            game2.setNewGraph();
            game2.setGraph();
        });
    }
}
