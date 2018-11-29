package GraphColoring;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Starter extends Application{
    private ChromaticManager chromaticManager = new ChromaticManager("C:/Users/cavid/Dropbox/Private/Final/src/GraphColoring/Graphs");
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
        stage.setTitle("test");
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
        });
    }
}
