package GraphColoring;

/*
 * Short summary:
 * A dot represents a vertix, and contains an ArrayList with Dots in it. 
 * The Dots in this ArrayList are the vertices this particular vertix is connected to.
 * The vertices can also be filled (in this case with other pictures), when a hint is given.
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
public class Dot extends Electron {
    
    //there need to be two lists, one for the connections, on for the unavailable colours
    private ArrayList<Dot> list;
    private ArrayList<Color> notAvailableColors = new ArrayList<>();
    
    //create instance variables you need
    private int content=0;
    public Color coloredAs;
    public int gameMode=-1;//when there's no game mode selected yet, this will be -1
    private boolean wasClicked=false;

    //create components for the GUI
    private Circle mainButton = new Circle();
     //the next components are all for the colour-buttons
    public HBox hBox = new HBox();
    private VBox vBox1 = new VBox();
    private VBox vBox2 = new VBox();
    
    //create the buttons with which you can choose a colour, and a ColorPicker with which you can pick a colour
    private ColorPicker colorPicker = new ColorPicker();
    private Button button1=new Button();
    private Button button2=new Button();
    private Button button3=new Button();
    private Button button4=new Button();
    private Button button5=new Button();
    private Button button6=new Button();
    private Button button7=new Button();
    
    //create an object of Game2
    private Game2 game2;
    //-----------------------
    //firstly, the constructor:
    public Dot(){
        //A class representing a single vertice of a graph
        list =new ArrayList<Dot>(); //create a new ArrayList for the connections
        this.setPickOnBounds(true);
        
        //set sizes of different components of the GUI, and add a style using CSS
        mainButton.setRadius(15);
        vBox1.setSpacing(2);
        vBox2.setSpacing(2);
        this.getStyleClass().add("test");
        buttonStuff(); //a method that edits the graphics of the buttons that let you choose colours, and adds them to layouts
        this.setAlignment(Pos.TOP_LEFT);
        hBox.setSpacing(5);
        hBox.getChildren().add(mainButton);
        this.getChildren().add(hBox);
        mainButton.getStyleClass().add("graphB");
        
        //to add events to the dots
        listen();
    }
    public void setConnection(Dot dot){//you can add another dot to the ArrayList inside your Dot, so you know these are connected
        list.add(dot);
    }
    
    public int getNrOfConnections() {//returns the number of connections a dot has
    	return list.size();
    }
    
    public void setContent(int c){//the vertix can get content, if the user gives it to it
        content = c;
    }
    public int giveContent(){ return content; }//returns the content of the vertix
    
    public ArrayList giveList(){//returns the list of the connections
        return list;
    }
    public void test(){
        System.out.println("calculateVectors");
    }
    private void listen(){
         //what happens when the dot is clicked (inside of the game):
        mainButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if (!wasClicked) {//wasClicked has to be false for this to work
                //show a menu with all the colours you can choose from:
                this.toFront();
                hBox.getChildren().removeAll(vBox1, vBox2);
                hBox.getChildren().addAll(vBox1, vBox2);
                ArrayList<Dot> dotList = game2.currentGraph.getList();
                for (int i = 0; i < dotList.size(); i++) {
                    if (dotList.get(i) != this) {
                        dotList.get(i).remove();
                    }
                }
            }
        });
        
        //make a white line around the dots that are connected to this dot, when your mouse hovers over a dot
        mainButton.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            for(int i=0;i<list.size();i++){
                Dot myDot =list.get(i);
                myDot.mainButton.setStrokeWidth(3);
                myDot.mainButton.setStroke(Color.GHOSTWHITE);
            }
            game2.printDot(this,true);//colours the edges connected to the dot
        });
        
        //let the white line disappear when your mouse exits the dot
        mainButton.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            for(int i=0;i<list.size();i++){
                Dot myDot =list.get(i);
                myDot.mainButton.setStroke(myDot.coloredAs);
            }
            game2.printDot(this,false);//will make the edges connected to the dot lose their colour
        });

    }
    //the method to change the appearance if the chosing-colour buttons
    private void buttonStuff(){
        //the basic width and height of the colour-buttons
        int width=25;
        int height=15;
        //make every button the same width and height, and let them have the same styleclass:
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
        
        //add the buttons to VBoxes, so they will line up from top to bottom
        vBox1.getChildren().addAll(button1,button2,button3,button4);
        vBox2.getChildren().addAll(button5,button6,button7);

        //give the buttons the colours you will want them to give the dot:
        button1.setStyle("-fx-background-color: YELLOW");
        button2.setStyle("-fx-background-color: NAVY");
        button3.setStyle("-fx-background-color: CYAN");
        button4.setStyle("-fx-background-color: MAROON");
        button5.setStyle("-fx-background-color: RED");
        button6.setStyle("-fx-background-color: GREY");

        //add events to the colour-chosing buttons, so it will set the coloredAs to the colour of the buttons when it's clicked
        //after that, check if the colour was available (which will be used for some hints):
        
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs =  Color.YELLOW;
            //checkIfAvailable();
            mainSetColor();
        });
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.NAVY;
            //checkIfAvailable();
            mainSetColor();
        });
        button3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.CYAN;
            //checkIfAvailable();
            mainSetColor();
        });
        button4.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.MAROON;
            //checkIfAvailable();
            mainSetColor();
        });
        button5.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.RED;
            //checkIfAvailable();
            mainSetColor();
        });
        button6.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            coloredAs = Color.GREY;
            //checkIfAvailable();
            mainSetColor();
        });
        
        //for the last two ones, add a colorPicker to the button, so when the user clicks it, an additional menu will appear with more colours:
        button7.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            hBox.getChildren().removeAll(colorPicker);
            hBox.getChildren().add(colorPicker);
        });
        colorPicker.setOnAction(event -> {
            coloredAs=colorPicker.getValue();
            //checkIfAvailable();
            mainSetColor();
        });

    }
    
    //set the parent of this dot, it will be the Game2 you pass as a parameter
    public void setParent(Game2 game2){
        this.game2=game2;
    }
    //remove the colour-chosing buttons from the HBox that will appear next to the Dot
    public void remove(){
        hBox.getChildren().removeAll(vBox1,vBox2,colorPicker);
    }
    //a method that will set the colour of the dot:
    public void mainSetColor(){
        remove();//the colour-chosing menu will disappear
        game2.colorsUsers.add(coloredAs);
        mainButton.setFill(coloredAs);
        if(gameMode!=3){//doesn't work for game mode nr 3
            return;
        }
        //for the first and second game mode:
            this.wasClicked=true;
            for(int i=0;i<list.size();i++){
                if(!list.get(i).getWasClicked()){
                    list.get(i).setMain();
                    game2.newDot(list.get(i),false);
                    return;
                }
            }
            Dot dot = game2.findNewDot();
            if(dot!=null){
                dot.setMain();
                game2.newDot(dot,false);
                return;
            }
            game2.finished();

    }
    public boolean getWasClicked(){//get the private boolean wasClicked
        return wasClicked;
    }
    public void setMain(){//add the hBox to the Dot and start the newDot method of the game2 class
        this.getChildren().removeAll(hBox);
        this.getChildren().add(hBox);
        game2.newDot(this,true);
    }
    public void removeMain(){//remove hBox from the Dot
        this.getChildren().removeAll(hBox);
    }
    //used to mark the most connected vertix:
    public void markedAsHint() {
        Image image = new Image("GraphColoring/Exclamation_Mark.png");
        mainButton.setFill(new ImagePattern(image));
    }
    //used to mark the biggest clique:
    public void markedAsSecondHint() {
    	Image imageTwo = new Image("GraphColoring/Signal.png");
    	mainButton.setFill(new ImagePattern(imageTwo));
    }
    //checks if a particular colour is available, used for hint 4 and for hint 9     
    public void checkIfAvailable(){
        if (game2.upper2Label.getText().equals("Color not available")){
            game2.upper2Label.setText("");
        }
        if(game2.hint4==false&&game2.hint9==false){//set the colour if there are no hints activated
            mainSetColor();
        }else{//check if the colour is available, and if not, give a warning to the user
            ArrayList<Dot> dots = (ArrayList<Dot>) game2.currentGraph.getList().clone();

            for(int i =0; i<list.size();i++){//run through all the connected dots and make a list of the colours used in the adjacent vertices
                for (int j = 0; j<dots.size();j++){
                    if (list.get(i).equals(dots.get(j))){
                        Color current = dots.get(j).coloredAs;
                        notAvailableColors.add(current);
                    }
                }
            }
            boolean colorAvailable=true;
            for(int i = 0;i<notAvailableColors.size();i++){
                if (notAvailableColors.get(i)==coloredAs){
                    System.out.println("Color not available"); //this should exactly happen in game but I'm not used to css so I don't know how that works
                    colorAvailable=false;
                    game2.upper2Label.setText("Color not available");
                    game2.hint4=false;
                }
            }
            if (colorAvailable==true){//set the color if it's available
                mainSetColor();
            }
        }
    }
}
