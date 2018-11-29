package GraphColoring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application{
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Game2 game2 = new Game2();
        MainInterface mainInterface = new MainInterface(this);
        Scene scene = mainInterface.getScene();
        primaryStage.setTitle("test");
        primaryStage.setScene(scene);
        game2.booleanProperty.addListener((observable, oldValue, newValue) -> System.out.println("gsjsfg"));
        primaryStage.show();


    }
}
