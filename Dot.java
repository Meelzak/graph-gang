package GraphColoring;
/**
 * Author:
 * Cavid Karca
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Dot extends Electron {
    private int content=0;
    private ArrayList<Dot> list;
    private int coloredAs=0;

    Button mainButton = new Button();
    ListView listView = new ListView();
    HBox hBox = new HBox();
    //-----------------------
    public Dot(){
        //A class representing a single vertice of a graph
        list =new ArrayList<Dot>();
        this.setMinSize(100,100);
        mainButton.setMinSize(30,30);
        listView.setItems(myColors());
        this.setAlignment(Pos.TOP_LEFT);
        listView.setMinSize(70,100);
        listView.setMaxSize(70,100);
        hBox.setSpacing(5);
        hBox.getChildren().add(mainButton);
        this.getChildren().add(hBox);
        mainButton.getStyleClass().add("graphB");
        listen();
    }
    public void setConnection(Dot dot){
        list.add(dot);
    }
    public void setContent(int c){
        content = c;
    }
    public int giveContent(){ return content; }
    public ArrayList giveList(){
        return list;
    }
    public void setColoredAs(int color){coloredAs=color;}
    public int getColor(){ return coloredAs; }
    public void test(){
        System.out.println("test");
    }
    private ObservableList<String> myColors(){
        ObservableList<String> data = FXCollections.observableArrayList(
                "chocolate", "salmon", "gold", "coral", "darkorchid",
                "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
                "blueviolet", "brown");
        return data;
    }
    private void listen(){
        mainButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            hBox.getChildren().removeAll(listView);
            hBox.getChildren().add(listView);
        });
        mainButton.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            for(int i=0;i<list.size();i++){
                list.get(i).mainButton.getStyleClass().removeAll("graphB");
                list.get(i).mainButton.getStyleClass().add("graphNeighbour");
            }
        });
        mainButton.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            for(int i=0;i<list.size();i++){
                list.get(i).mainButton.getStyleClass().removeAll("graphNeighbour");
                list.get(i).mainButton.getStyleClass().add("graphB");
            }
        });
    }

}