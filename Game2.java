package GraphColoring;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Game2 {
    //Regions
    private VBox leftVBox;
    private VBox rightVBox;
    private HBox upperHBox;
    private Canvas canvas;
    private FlowPane flowPane;
    private StackPane stackPane;
    private StackPane canvasStackPane=new StackPane();
    //Scene
    private BetterScene scene;
    //Objects
    private Button left1HintButton;
    private Button left2Button;
    private Button left3Button;
    private Button left4Button;
    private Button left5Button;
    private Label leftRestLabel;
    private int HintButtonCounter;

    private Label upperLeftButton;

    private Label upper1Label;
    private Label upper2Label;
    private Label upper3Label;
    private Label upperRestLabel;
    private Label upperBoundLabel; //hint to display the upperbound when asked for

    //New GameMode Button
    private HBox newGamemodeHBox=new HBox();
    private Button newGamemodebutton1=new Button("Bitter End");
    private Button newGamemodebutton2=new Button("Best upper Bound");
    private Button newGamemodebutton3=new Button("Random Order");
    //Back Button
    private StackPane backPane=new StackPane();
    private Button backButton=new Button("Back");
    //New GraphMode
    private HBox newGraphModeHBox=new HBox();
    private Button newGraphModebutton1=new Button("Random Graph");
    private Button newGraphModebutton2=new Button("Graph from Textfile");
    private Button newGraphModebutton3=new Button("Create Graph");
    //New Graph Yes or No
    private HBox newGraphHBox = new HBox();
    private Button newGraphButtonYes=new Button("Yes");
    private Button newGraphButtonNo=new Button("No");
    //new Graph Mode
    private HBox sMBHBox = new HBox();
    private VBox listViewVBox = new VBox();
    private Button smallButton = new Button("Small");
    private Button middleButton = new Button("Middle");
    private Button bigButton = new Button("Big");

    private ListView listView = new ListView();

    private HBox textFieldHBox = new HBox();
    private TextField textFieldVertices = new TextField();
    private TextField textFieldEdges = new TextField();
    private Button buttonTextfield = new Button("Submit");
    private Button submit2 = new Button("Submit");
    private String myGraph="";


    private EventHandler graphHandeler;

    //Display Stuff
    private Pane pane = new Pane();
    private ChromaticManager chromaticManager;
    public Graph currentGraph;
    public int graphMode=0;
    public String txtGraph="";
    public int myEdges=0;
    public int myVertices=0;
    public int selectedSize=0;


    private Starter starter;
    //Booleans
    public BooleanProperty booleanProperty= new SimpleBooleanProperty(false);

    //-------------------------------------------------------------------------------------
    public Game2(Starter starter){
        chromaticManager = starter.chromaticManager;
        this.starter=starter;
    }

    public Scene giveScene(){
        builder();
        setSize(myInformation.startDimension.width,myInformation.startDimension.height);
        insert();
        scene = new BetterScene(stackPane,myInformation.startDimension);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        test();
        styling();
        graphListener();
        listen();
        canvas.setMouseTransparent(true);
        return scene;

    }

    private void builder(){
        //Regions
        leftVBox = new VBox();
        rightVBox = new VBox();
        upperHBox = new HBox();
        canvas = new Canvas();
        flowPane = new FlowPane();
        stackPane = new StackPane();
        //Objects
        //Left
        left1HintButton = new Button("Hint");
        left2Button = new Button("New Graph");
        left3Button = new Button("New GameMode");
        left4Button = new Button("New GraphMode");
        left5Button = new Button();
        leftRestLabel = new Label();
        //Up
        upperLeftButton = new Label();
        upper1Label = new Label();
        upper2Label = new Label();
        upper3Label = new Label();
        upperRestLabel = new Label();
        upperBoundLabel = new Label();
        //test
        backPane.setPickOnBounds(false);
        textFieldVertices.setPromptText("Vertices");
        textFieldEdges.setPromptText("Edges");
    }
    private void styling(){
        canvasStackPane.getStyleClass().add("canvasStackPane");

        upper1Label.getStyleClass().add("myLabelTest");
        upper2Label.getStyleClass().add("myLabelTest1");
        upper3Label.getStyleClass().add("myLabelTest2");
        upperBoundLabel.getStyleClass().add("myLabelTest2"); //just copied a style to it but can't test it so you can change the style
        upperHBox.getStyleClass().add("upperHBox");
        leftVBox.getStyleClass().add("leftVBox");
        upperLeftButton.getStyleClass().add("upperLeftButton");
        left1HintButton.getStyleClass().add("left1Button");
        left2Button.getStyleClass().add("left2Button");
        left3Button.getStyleClass().add("left3Button");
        left4Button.getStyleClass().add("left4Button");
        left5Button.getStyleClass().add("left5Button");
        //new GameMode
        newGamemodeHBox.getStyleClass().add("newGameModeHBox");
        newGamemodebutton1.getStyleClass().add("chooseButtons");
        newGamemodebutton2.getStyleClass().add("chooseButtons");
        newGamemodebutton3.getStyleClass().add("chooseButtons");
        //back Button
        backPane.getStyleClass().add("backPane");
        backButton.getStyleClass().add("backButton");
        //new Graphmode
        newGraphModeHBox.getStyleClass().add("newGraphModeHBox");
        newGraphModebutton1.getStyleClass().add("chooseButtons");
        newGraphModebutton2.getStyleClass().add("chooseButtons");
        newGraphModebutton3.getStyleClass().add("chooseButtons");
        sMBHBox.getStyleClass().add("newGraphModeHBox");
        smallButton.getStyleClass().add("chooseButtons");
        middleButton.getStyleClass().add("chooseButtons");
        bigButton.getStyleClass().add("chooseButtons");
        textFieldHBox.getStyleClass().add("newGraphModeHBox");
        buttonTextfield.getStyleClass().add("chooseButtons");
        //new Graph
        newGraphHBox.getStyleClass().add("newGraphHBox");
        newGraphButtonYes.getStyleClass().add("chooseButtons");
        newGraphButtonNo.getStyleClass().add("chooseButtons");
        submit2.getStyleClass().add("chooseButtons");
        listViewVBox.getStyleClass().add("newGraphHBox");
        textFieldVertices.getStyleClass().add("textfield");
        textFieldEdges.getStyleClass().add("textfield");
    }
    public void setSize(double width, double height){
        double topLaneHeight=height/100*myInformation.upperToDownPercent;
        double bottomLaneHeight=height-topLaneHeight;
        double leftSideWidth=width/100*myInformation.leftToRightPercent;
        double rightSideWidth=width-leftSideWidth-1;

        double buttonLeftHeight=(height-topLaneHeight)/100*myInformation.leftButtonsPercent;
        int countLeftButtons=0;
        double labelTopWidth=rightSideWidth/100*myInformation.upperButtonsPercent;
        int countTopLabels=0;
        //Regions
        leftVBox.setMinSize(leftSideWidth,height);
        leftVBox.setMaxSize(leftSideWidth,height);

        rightVBox.setMinSize(rightSideWidth,height);
        rightVBox.setMaxSize(rightSideWidth,height);

        upperHBox.setMinSize(rightSideWidth,topLaneHeight);
        upperHBox.setMaxSize(rightSideWidth,topLaneHeight);

        canvas.setWidth(rightSideWidth);
        canvas.setHeight(bottomLaneHeight);

        pane.setMinSize(rightSideWidth,bottomLaneHeight);
        pane.setMaxSize(rightSideWidth,bottomLaneHeight);
        canvasStackPane.setMinSize(rightSideWidth,bottomLaneHeight);
        canvasStackPane.setMaxSize(rightSideWidth,bottomLaneHeight);

        flowPane.setMinSize(width, height);
        flowPane.setMaxSize(width, height);

        stackPane.setMinSize(width, height);
        stackPane.setMaxSize(width, height);

        listViewVBox.setMinSize(width, height);
        listViewVBox.setMaxSize(width, height);
        //Objects
        upperLeftButton.setMinSize(leftSideWidth,topLaneHeight);
        upperLeftButton.setMaxSize(leftSideWidth,topLaneHeight);
        //Left Side
        left1HintButton.setMinSize(leftSideWidth,buttonLeftHeight);
        left1HintButton.setMaxSize(leftSideWidth,buttonLeftHeight);
        countLeftButtons++;

        left2Button.setMinSize(leftSideWidth,buttonLeftHeight);
        left2Button.setMaxSize(leftSideWidth,buttonLeftHeight);
        countLeftButtons++;

        left3Button.setMinSize(leftSideWidth,buttonLeftHeight);
        left3Button.setMaxSize(leftSideWidth,buttonLeftHeight);
        countLeftButtons++;

        left4Button.setMinSize(leftSideWidth,buttonLeftHeight);
        left4Button.setMaxSize(leftSideWidth,buttonLeftHeight);
        countLeftButtons++;

        left5Button.setMinSize(leftSideWidth,buttonLeftHeight);
        left5Button.setMaxSize(leftSideWidth,buttonLeftHeight);
        countLeftButtons++;

        double restHeight=height-(buttonLeftHeight*countLeftButtons+topLaneHeight);
        leftRestLabel.setMinSize(leftSideWidth,restHeight);
        leftRestLabel.setMaxSize(leftSideWidth,restHeight);

        //Top
        upper1Label.setMinSize(labelTopWidth,topLaneHeight);
        upper1Label.setMaxSize(labelTopWidth,topLaneHeight);
        countTopLabels++;

        upper2Label.setMinSize(labelTopWidth,topLaneHeight);
        upper2Label.setMaxSize(labelTopWidth,topLaneHeight);
        countTopLabels++;

        upper3Label.setMinSize(labelTopWidth,topLaneHeight);
        upper3Label.setMaxSize(labelTopWidth,topLaneHeight);
        countTopLabels++;

        upperBoundLabel.setMinSize(labelTopWidth,topLaneHeight); //I just copied the style but it needs to be set correctly
        upperBoundLabel.setMaxSize(labelTopWidth,topLaneHeight);



        double restwidth=width-leftSideWidth-(labelTopWidth*countTopLabels);
        upperRestLabel.setMinSize(restwidth,topLaneHeight);
        upperRestLabel.setMaxSize(restwidth,topLaneHeight);
        //New Gamemode stuff
        newGamemodeHBox.setMinSize(width,height);
        newGamemodeHBox.setMaxSize(width,height);
        double nBWidth=width/100*myInformation.newGamemodeButtonsPercent.width;
        double nBHeight=height/100*myInformation.newGamemodeButtonsPercent.height;

        newGamemodebutton1.setMinSize(nBWidth,nBHeight);
        newGamemodebutton1.setMaxSize(nBWidth,nBHeight);

        newGamemodebutton2.setMinSize(nBWidth,nBHeight);
        newGamemodebutton2.setMaxSize(nBWidth,nBHeight);

        newGamemodebutton3.setMinSize(nBWidth,nBHeight);
        newGamemodebutton3.setMaxSize(nBWidth,nBHeight);

        //BackButton
        backPane.setMinSize(width, height);
        backPane.setMaxSize(width, height);
        double backWidth=width/100*myInformation.backButtonPercent.width;
        double backHeight=height/100*myInformation.backButtonPercent.height;
        backButton.setMinSize(backWidth,backHeight);
        backButton.setMaxSize(backWidth,backHeight);
        //New Graphmode
        newGraphModeHBox.setMinSize(width,height);
        newGraphModeHBox.setMaxSize(width,height);

        newGraphModebutton1.setMinSize(nBWidth,nBHeight);
        newGraphModebutton1.setMaxSize(nBWidth,nBHeight);

        newGraphModebutton2.setMinSize(nBWidth,nBHeight);
        newGraphModebutton2.setMaxSize(nBWidth,nBHeight);

        newGraphModebutton3.setMinSize(nBWidth,nBHeight);
        newGraphModebutton3.setMaxSize(nBWidth,nBHeight);

        sMBHBox.setMinSize(width,height);
        sMBHBox.setMaxSize(width,height);
        smallButton.setMinSize(nBWidth,nBHeight);
        smallButton.setMaxSize(nBWidth,nBHeight);
        middleButton.setMinSize(nBWidth,nBHeight);
        middleButton.setMaxSize(nBWidth,nBHeight);
        bigButton.setMinSize(nBWidth,nBHeight);
        bigButton.setMaxSize(nBWidth,nBHeight);

        listView.setMinSize(width/8,height/2);
        listView.setMaxSize(width/8,height/2);
        submit2.setMinSize(width/8,height/10);
        submit2.setMaxSize(width/8,height/10);

        textFieldHBox.setMinSize(width,height);
        textFieldHBox.setMaxSize(width,height);
        textFieldVertices.setMinSize(nBWidth,nBHeight);
        textFieldVertices.setMaxSize(nBWidth,nBHeight);
        textFieldEdges.setMinSize(nBWidth,nBHeight);
        textFieldEdges.setMaxSize(nBWidth,nBHeight);

        buttonTextfield.setMinSize(nBWidth/2,nBHeight);
        buttonTextfield.setMaxSize(nBWidth/2,nBHeight);

        //New Graph
        newGraphHBox.setMinSize(width,height);
        newGraphHBox.setMaxSize(width,height);

        newGraphButtonYes.setMinSize(nBWidth,nBHeight);
        newGraphButtonYes.setMaxSize(nBWidth,nBHeight);

        newGraphButtonNo.setMinSize(nBWidth,nBHeight);
        newGraphButtonNo.setMaxSize(nBWidth,nBHeight);

    }
    private void insert(){
        leftVBox.getChildren().addAll(upperLeftButton,left1HintButton,left2Button,left3Button,left4Button,left5Button,leftRestLabel);
        upperHBox.getChildren().addAll(upper1Label,upper2Label,upper3Label, upperBoundLabel);//upperBoundLabel should not be visible since no text added to it
        canvasStackPane.getChildren().addAll(pane,canvas);
        rightVBox.getChildren().addAll(upperHBox,canvasStackPane);
        flowPane.getChildren().addAll(leftVBox,rightVBox);
        stackPane.getChildren().addAll(flowPane);
        //new GameMode stuff
        newGamemodeHBox.getChildren().addAll(newGamemodebutton1,newGamemodebutton2,newGamemodebutton3);
        //Back button
        backPane.getChildren().add(backButton);
        //new GraphMode
        newGraphModeHBox.getChildren().addAll(newGraphModebutton1,newGraphModebutton2,newGraphModebutton3);
        //new Graph
        newGraphHBox.getChildren().addAll(newGraphButtonYes,newGraphButtonNo);

        sMBHBox.getChildren().addAll(smallButton,middleButton,bigButton);
        textFieldHBox.getChildren().addAll(textFieldVertices,textFieldEdges,buttonTextfield);
        listViewVBox.getChildren().addAll(listView,submit2);

    }
    private void test(){
        //left1Button.setText("test");
    }
    private void listen(){
        //new Size Listeners
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            setSize(scene.getWidth(),scene.getHeight());

        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            setSize(scene.getWidth(),scene.getHeight());
        });
        //Button Listeners
        left1HintButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if(HintButtonCounter==0){

                upperBoundLabel.setText(Integer.toString(currentGraph.getUpperBound()));
                currentGraph.addHints(1);
            }
        });
        left2Button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().add(newGraphHBox);
            stackPane.getChildren().add(backPane);
        });
        left3Button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stackPane.getChildren().add(newGamemodeHBox);
            stackPane.getChildren().add(backPane);
        });
        left4Button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().add(newGraphModeHBox);
            stackPane.getChildren().add(backPane);
        });
        //Back Button
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphHBox,newGamemodeHBox,newGraphModeHBox,sMBHBox,backPane,listView,textFieldHBox,listViewVBox);
        });
        //Other Buttons
        newGraphButtonYes.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphHBox,backPane);
            if(graphMode==1){
                Random random = new Random();
                if(selectedSize==1){
                    currentGraph=chromaticManager.calculate(random.nextInt(myInformation.smallGraph)+1,-1);
                    setDisplay(currentGraph);
                }
                if(selectedSize==2){
                    currentGraph=chromaticManager.calculate(random.nextInt(myInformation.middleGraph-myInformation.smallGraph)+myInformation.smallGraph+1,-1);
                    setDisplay(currentGraph);
                }
                if(selectedSize==3){
                    currentGraph=chromaticManager.calculate(random.nextInt(myInformation.bigGraph-myInformation.middleGraph)+myInformation.middleGraph+1,-1);
                    setDisplay(currentGraph);
                }
            }
            if(graphMode==2){
                currentGraph=starter.chromaticManager.calculate(txtGraph);
                setDisplay(currentGraph);
            }
            if(graphMode==3){
                currentGraph=starter.chromaticManager.calculate(myVertices,myEdges);
                setDisplay(currentGraph);
            }
        });
        newGraphButtonNo.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphHBox,backPane);
        });
        newGraphModebutton1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphModeHBox,backPane);
            stackPane.getChildren().addAll(sMBHBox,backPane);
        });
        newGraphModebutton2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            listView.setItems(FXCollections.observableArrayList(starter.giveGraphs()));
            stackPane.getChildren().removeAll(newGraphModeHBox,backPane);
            stackPane.getChildren().addAll(listViewVBox,backPane);
        });
        newGraphModebutton3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphModeHBox,backPane);
            stackPane.getChildren().addAll(textFieldHBox,backPane);
        });
        //New Graphs Buttons
        smallButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(sMBHBox,backPane);
            graphMode=1;
            selectedSize=1;
            Random random = new Random();
            currentGraph=chromaticManager.calculate(random.nextInt(myInformation.smallGraph)+1,-1);
            setDisplay(currentGraph);
        });
        middleButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(sMBHBox,backPane);
            graphMode=1;
            selectedSize=2;
            Random random = new Random();
            currentGraph=chromaticManager.calculate(random.nextInt(myInformation.middleGraph-myInformation.smallGraph)+myInformation.smallGraph+1,-1);
            setDisplay(currentGraph);
        });
        bigButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(sMBHBox,backPane);
            graphMode=1;
            selectedSize=3;
            Random random = new Random();
            currentGraph=chromaticManager.calculate(random.nextInt(myInformation.bigGraph-myInformation.middleGraph)+myInformation.middleGraph+1,-1);
            setDisplay(currentGraph);
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            myGraph=newValue.toString();
        });
        submit2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(listViewVBox,backPane);
            graphMode=2;
            currentGraph=chromaticManager.calculate(myGraph);
            setDisplay(currentGraph);
        });
        buttonTextfield.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            graphMode=3;
            try {
                stackPane.getChildren().removeAll(textFieldHBox,backPane);
                myVertices = Integer.parseInt(textFieldVertices.getText());
                myEdges = Integer.parseInt(textFieldEdges.getText());
                currentGraph=chromaticManager.calculate(myVertices,myEdges);
                setDisplay(currentGraph);
            }catch (NumberFormatException e){

            }
        });

    }
    private void setDisplay(Graph graph){
        clear();
        double myWidth=canvas.getWidth();
        double myHeight=canvas.getHeight();
        System.out.println(myWidth+" "+myHeight);
        ArrayList<Dot> list = graph.getList();
        Random random = new Random();
        for(int i=0;i<list.size();i++){
            int x=random.nextInt((int)Math.round(myWidth/4))+(int)Math.round((myWidth/2)-myWidth/8);
            int y=random.nextInt((int)Math.round(myHeight/4))+(int)Math.round((myHeight/2)-myHeight/8);
            list.get(i).setPosition(new Position(x,y));
            //list.get(i).setOnAction(graphHandeler);
            list.get(i).getStyleClass().add("graphButton");
            pane.getChildren().add(list.get(i));
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), ev -> {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            ArrayList<Position> positionArrayList=new ArrayList();
            for(int i=0;i<list.size();i++){
                Dot d= list.remove(i);
                positionArrayList.add(d.test(list,d.giveList(),canvas.getWidth(),canvas.getHeight()));
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
                    canvas.getGraphicsContext2D().strokeLine(position1.x+15,position1.y+15,position2.x+15,position2.y+15);
                }
            }
        }));
        timeline.setCycleCount(10000);
        timeline.play();

    }
    public void setGraph(){
        setDisplay(currentGraph);
    }
    public void clear(){
        canvas.getGraphicsContext2D().clearRect(canvas.getWidth(),canvas.getHeight(),1,1);
        pane.getChildren().clear();
    }
    public void graphListener(){
        graphHandeler = event -> {
                Dot dot = (Dot)event.getSource();
                dot.test();
        };
    }
}
