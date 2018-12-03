package GraphColoring;
/**
 * Author:
 * Cavid Karca
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
public class Dot extends Electron {
    private int content=0;
    private ArrayList<Dot> list;
    public Color coloredAs;

    private Circle mainButton = new Circle();
    private HBox hBox = new HBox();
    private VBox vBox1 = new VBox();
    private VBox vBox2 = new VBox();
    private ColorPicker colorPicker = new ColorPicker();
    private Button button1=new Button();
    private Button button2=new Button();
    private Button button3=new Button();
    private Button button4=new Button();
    private Button button5=new Button();
    private Button button6=new Button();
    private Button button7=new Button();
    private Game2 game2;
    //-----------------------
    public Dot(){
        //A class representing a single vertice of a graph
        list =new ArrayList<Dot>();
        this.setMinSize(100,100);
        this.setPickOnBounds(true);
        mainButton.setRadius(15);
        vBox1.setSpacing(2);
        vBox2.setSpacing(2);
        buttonStuff();
        this.setAlignment(Pos.TOP_LEFT);
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
    public void test(){
        System.out.println("test");
    }
    private void listen(){
        mainButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {

            hBox.getChildren().removeAll(vBox1,vBox2);
            hBox.getChildren().addAll(vBox1,vBox2);
            ArrayList<Dot> dotList = game2.currentGraph.getList();
            for(int i=0;i<dotList.size();i++){
                if(dotList.get(i)!=this){
                    dotList.get(i).remove();
                }
            }
        });
        mainButton.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            for(int i=0;i<list.size();i++){
                list.get(i).mainButton.getStyleClass().removeAll("graphB");
                list.get(i).mainButton.getStyleClass().add("graphNeighbour");
            }
            game2.printDot(this,true);
        });
        mainButton.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            for(int i=0;i<list.size();i++){
                list.get(i).mainButton.getStyleClass().removeAll("graphNeighbour");
                list.get(i).mainButton.getStyleClass().add("graphB");
            }
            game2.printDot(this,false);
        });

    }
    private void buttonStuff(){
        int width=25;
        int height=15;
        button1.setMinSize(width,height);
        button2.setMinSize(width,height);
        button3.setMinSize(width,height);
        button4.setMinSize(width,height);
        button5.setMinSize(width,height);
        button6.setMinSize(width,height);
        button7.setMinSize(width,height);
        button1.getStyleClass().add("buttonC");
        button2.getStyleClass().add("buttonC");
        button3.getStyleClass().add("buttonC");
        button4.getStyleClass().add("buttonC");
        button5.getStyleClass().add("buttonC");
        button6.getStyleClass().add("buttonC");
        button7.getStyleClass().add("buttonC");
        vBox1.getChildren().addAll(button1,button2,button3,button4);
        vBox2.getChildren().addAll(button5,button6,button7);

        button1.setStyle("-fx-background-color: YELLOW");
        button2.setStyle("-fx-background-color: BROWN");
        button3.setStyle("-fx-background-color: BLUE");
        button4.setStyle("-fx-background-color: GREEN");
        button5.setStyle("-fx-background-color: RED");
        button6.setStyle("-fx-background-color: GREY");

        button1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs =  Color.YELLOW;
            mainSetColor();
        });
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.BROWN;
            mainSetColor();
        });
        button3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.BLUE;
            mainSetColor();
        });
        button4.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.GREEN;
            mainSetColor();
        });
        button5.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.RED;
            mainSetColor();
        });
        button6.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.GREY;
            mainSetColor();
        });
        button7.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            hBox.getChildren().removeAll(colorPicker);
            hBox.getChildren().add(colorPicker);
        });
        colorPicker.setOnAction(event -> {
            coloredAs=colorPicker.getValue();
            mainSetColor();
        });

    }
    public void setParent(Game2 game2){
        this.game2=game2;
    }
    public void remove(){
        hBox.getChildren().removeAll(vBox1,vBox2,colorPicker);
    }
    public void mainSetColor(){
        mainButton.setFill(coloredAs);
        remove();
    }


}