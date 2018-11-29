package GraphColoring;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application{
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Game2 game2 = new Game2();
        MainInterface mainInterface = new MainInterface();
        Scene scene = mainInterface.getScene();
        primaryStage.setTitle("test");
        primaryStage.setScene(scene);
        game2.booleanProperty.addListener((observable, oldValue, newValue) -> System.out.println("gsjsfg"));
        primaryStage.show();


    }
}
