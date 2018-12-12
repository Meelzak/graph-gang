package GraphColoring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Starter extends Application{
    public ChromaticManager chromaticManager = new ChromaticManager(System.getProperty("user.dir")+"/src/GraphColoring/Graphs");
    private MainInterface mainInterface = new MainInterface(this);
    private Game2 game2 = new Game2(this);
    private Scene scene;
    private Stage stage;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        listener();
        stage=primaryStage;
        scene = mainInterface.getScene();
        stage.setTitle("GraphColoring");
        stage.setScene(scene);
        stage.show();

    }
    public ArrayList giveGraphs(){
        return chromaticManager.listFiles();
    }
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
