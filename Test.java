package GraphColoring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Test extends Application {
    private ChromaticManager chromaticManager;
    private Scene scene;
    StackPane stackPane = new StackPane();
    Canvas canvas = new Canvas();
    private Pane pane = new Pane();

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        pane.setMinSize(800,800);
        stackPane.setMinSize(800,800);
        canvas.setHeight(800);
        canvas.setWidth(800);
        chromaticManager = new ChromaticManager("C:/Users/cavid/Dropbox/Private/Final/src/GraphColoring/Graphs");
        Graph graph = chromaticManager.calculate(10,20);
        ArrayList<Dot> list = graph.getList();
        Random random = new Random();
        for(int i=0;i<list.size();i++){
            int x=random.nextInt(300)+100;
            int y=random.nextInt(300)+100;
            list.get(i).setPosition(new Position(x,y));
            pane.getChildren().add(list.get(i));
        }
        Button button = new Button("MacheWas");
        pane.getChildren().add(button);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            ArrayList<Position> positionArrayList=new ArrayList();
            for(int i=0;i<list.size();i++){
                Dot d= list.remove(i);
                positionArrayList.add(d.test(list,d.giveList()));
                list.add(i,d);
            }
            for(int i=0;i<list.size();i++){
                list.get(i).setPosition(positionArrayList.get(i));
            }

            for(int i=0;i<list.size();i++){
                ArrayList<Dot> myList = list.get(i).giveList();
                for(int x=0;x<myList.size();x++){
                    Position position1 = list.get(i).position;
                    Position position2 = myList.get(x).position;
                    canvas.getGraphicsContext2D().strokeLine(position1.x,position1.y,position2.x,position2.y);
                }
            }
        });
        stackPane.getChildren().add(canvas);
        stackPane.getChildren().add(pane);
        Scene scene = new Scene(stackPane,800,800);
        primaryStage.setTitle("test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
