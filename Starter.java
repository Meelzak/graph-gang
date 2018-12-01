package GraphColoring;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Starter extends Application{
    public ChromaticManager chromaticManager = new ChromaticManager("C:/Users/cavid/Dropbox/Private/Final/src/GraphColoring/Graphs");
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
            game2.setSize(mainInterface.getSize().width,mainInterface.getSize().height);
            stage.setScene(scene);
            if(mainInterface.graphMode==1){
                game2.graphMode=1;
                game2.selectedSize=mainInterface.selectedSize;
                Random random = new Random();
                if(mainInterface.selectedSize==1){
                    game2.currentGraph=chromaticManager.calculate(random.nextInt(myInformation.smallGraph)+1,-1);
                }
                if(mainInterface.selectedSize==2){
                    game2.currentGraph=chromaticManager.calculate(random.nextInt(myInformation.middleGraph-myInformation.smallGraph)+myInformation.smallGraph+1,-1);
                }
                if(mainInterface.selectedSize==3){
                    game2.currentGraph=chromaticManager.calculate(random.nextInt(myInformation.bigGraph-myInformation.middleGraph)+myInformation.middleGraph+1,-1);
                }
            }
            if(mainInterface.graphMode==2){
                game2.graphMode=2;
                game2.txtGraph=mainInterface.selectedGraph;
                game2.currentGraph=(chromaticManager.calculate(mainInterface.selectedGraph));
            }
            if(mainInterface.graphMode==3){
                game2.graphMode=3;
                game2.myVertices=mainInterface.selectedVertices;
                game2.myEdges=mainInterface.selectedEdges;
                game2.currentGraph=(chromaticManager.calculate(mainInterface.selectedVertices,mainInterface.selectedEdges));
            }
            game2.setSize(mainInterface.getSize().width,mainInterface.getSize().height);
            game2.setGraph();
        });
    }
}
