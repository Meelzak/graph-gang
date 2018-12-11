package GraphColoring;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Game2 {
    //Regions
    private VBox leftVBox = new VBox();
    private HBox upperHBox = new HBox();
    private Canvas canvas = new Canvas();
    private StackPane stackPane = new StackPane();
    private Pane pane = new Pane();
    private StackPane canvasStackPane = new StackPane();
    private VBox mainVBox = new VBox();
    //Scene
    private BetterScene scene;
    //Objects
    private Button left1HintButton;
    public Boolean hint3;
    private Button left2Button;
    private Button left3Button;
    private Button left4Button;
    private Button left5Button;
    private Label leftRestLabel;
    public static ArrayList<Color> colorsUsers=new ArrayList<>();

    private Label upperLeftButton;

    private Label upper1Label;
    private Label upper2Label;
    private Label upper3Label;
    private Label upperRestLabel;
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
    private Timeline timer;

    private EventHandler graphHandeler;
    private VBox gameEnd = new VBox();
    private Button gameEndButton = new Button("Try Again");
    private StackPane gameEndTop = new StackPane();
    private StackPane gameEndStackPane = new StackPane();

    private StackPane hintMenuStack = new StackPane();
    private HBox hintMenu = new HBox();
    private VBox vBoxHint = new VBox();
    private VBox vBoxHint2 = new VBox();
    private VBox vBoxHint3 = new VBox();
    //add Buttons
    private Button hintButton1 = new Button("1");
    private Button hintButton2 = new Button("2");
    private Button hintButton3 = new Button("3");
    private Button hintButton4 = new Button("4");
    private Button hintButton5 = new Button("5");
    private Button hintButton6 = new Button("6");
    private Button hintButton7 = new Button("7");
    private Button hintButton8 = new Button("8");
    private Button hintButton9 = new Button("9");

    //Display Stuff
    private ChromaticManager chromaticManager;
    public Graph currentGraph;
    public int graphMode=0;
    public int gamemode=0;
    public String txtGraph="";
    public int myEdges=0;
    public int myVertices=0;
    public int selectedSize=0;
    private int timing=0;
    public boolean hint2=false;


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
        styling();
        listen();
        canvas.setMouseTransparent(true);
        return scene;

    }

    private void builder(){
        //Objects
        //Left
        canvasStackPane.setAlignment(Pos.TOP_LEFT);
        left1HintButton = new Button("Hint");
        left2Button = new Button("New Graph");
        left3Button = new Button("New GameMode");
        left4Button = new Button("New GraphMode");
        left5Button = new Button("Finished");
        leftRestLabel = new Label();
        //Up
        upperLeftButton = new Label("Menu");
        upperLeftButton.setAlignment(Pos.CENTER);
        upper1Label = new Label();
        upper2Label = new Label();
        upper3Label = new Label();
        upperRestLabel = new Label();
        //calculateVectors
        backPane.setPickOnBounds(false);
        textFieldVertices.setPromptText("Vertices");
        textFieldEdges.setPromptText("Edges");
        gameEnd.setSpacing(30);
        gameEnd.setAlignment(Pos.CENTER);
        canvasStackPane.setPickOnBounds(true);
        canvas.setPickOnBounds(true);
        canvas.getGraphicsContext2D().setLineWidth(2.5);
    }
    private void styling(){
        canvasStackPane.getStyleClass().add("canvasStackPane");

        upper1Label.getStyleClass().add("myLabelTest");
        upper2Label.getStyleClass().add("myLabelTest1");
        upper3Label.getStyleClass().add("myLabelTest2");
        upperRestLabel.getStyleClass().add("myLabelTest");
        upperHBox.getStyleClass().add("upperHBox");
        leftVBox.getStyleClass().add("leftVBox");
        upperLeftButton.getStyleClass().add("upperLeftButton");
        left1HintButton.getStyleClass().add("leftButton");
        left2Button.getStyleClass().add("leftButton");
        left3Button.getStyleClass().add("leftButton");
        left4Button.getStyleClass().add("leftButton");
        left5Button.getStyleClass().add("leftButton");
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
        gameEndTop.getStyleClass().add("gameEndTop");
        gameEndStackPane.getStyleClass().add("gameEndStackPane");

        hintMenuStack.getStyleClass().add("newGraphModeHBox");

        hintButton1.getStyleClass().add("chooseButtons");
        hintButton2.getStyleClass().add("chooseButtons");
        hintButton3.getStyleClass().add("chooseButtons");
        hintButton4.getStyleClass().add("chooseButtons");
        hintButton5.getStyleClass().add("chooseButtons");
        hintButton6.getStyleClass().add("chooseButtons");
        hintButton7.getStyleClass().add("chooseButtons");
        hintButton8.getStyleClass().add("chooseButtons");
        hintButton9.getStyleClass().add("chooseButtons");


        vBoxHint.setAlignment(Pos.CENTER);
        vBoxHint2.setAlignment(Pos.CENTER);
        vBoxHint3.setAlignment(Pos.CENTER);
        hintMenu.setAlignment(Pos.CENTER);
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

        upperHBox.setMinSize(width,topLaneHeight);
        upperHBox.setMaxSize(width,topLaneHeight);

        canvas.setWidth(width);
        canvas.setHeight(bottomLaneHeight);

        pane.setMinSize(width,bottomLaneHeight);
        pane.setMaxSize(width,bottomLaneHeight);

        stackPane.setMinSize(width, height);
        stackPane.setMaxSize(width, height);

        mainVBox.setMinSize(width, height);
        mainVBox.setMaxSize(width, height);

        canvasStackPane.setMinSize(width,bottomLaneHeight);
        canvasStackPane.setMaxSize(width,bottomLaneHeight);

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





        double restwidth=width-upperLeftButton.getWidth()-(labelTopWidth*countTopLabels);
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

        gameEndButton.setMinSize(nBWidth,nBHeight);
        gameEndButton.setMaxSize(nBWidth,nBHeight);
        gameEndTop.setMinSize(width/2,height/5);
        gameEndTop.setMaxSize(width/2,height/5);


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

        hintButton1.setMinSize(nBWidth,nBHeight);
        hintButton1.setMaxSize(nBWidth,nBHeight);
        hintButton2.setMinSize(nBWidth,nBHeight);
        hintButton2.setMaxSize(nBWidth,nBHeight);
        hintButton3.setMinSize(nBWidth,nBHeight);
        hintButton3.setMaxSize(nBWidth,nBHeight);
        hintButton4.setMinSize(nBWidth,nBHeight);
        hintButton4.setMaxSize(nBWidth,nBHeight);
        hintButton5.setMinSize(nBWidth,nBHeight);
        hintButton5.setMaxSize(nBWidth,nBHeight);
        hintButton6.setMinSize(nBWidth,nBHeight);
        hintButton6.setMaxSize(nBWidth,nBHeight);
        hintButton7.setMinSize(nBWidth,nBHeight);
        hintButton7.setMaxSize(nBWidth,nBHeight);
        hintButton8.setMinSize(nBWidth,nBHeight);
        hintButton8.setMaxSize(nBWidth,nBHeight);
        hintButton9.setMinSize(nBWidth,nBHeight);
        hintButton9.setMaxSize(nBWidth,nBHeight);


        hintMenuStack.setMinSize(width, height);
        hintMenuStack.setMaxSize(width, height);

        hintMenu.setSpacing(width/30);
        vBoxHint.setSpacing(height/20);
        vBoxHint2.setSpacing(height/20);
        vBoxHint3.setSpacing(height/20);

    }
    private void insert(){
        leftVBox.getChildren().addAll(left1HintButton,left2Button,left3Button,left4Button,left5Button,leftRestLabel);
        upperHBox.getChildren().addAll(upperLeftButton,upper1Label,upper2Label,upper3Label,upperRestLabel);//upperBoundLabel should not be visible since no text added to it
        canvasStackPane.getChildren().addAll(canvas,pane);
        mainVBox.getChildren().addAll(upperHBox,canvasStackPane);
        stackPane.getChildren().add(mainVBox);
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
        gameEnd.getChildren().addAll(gameEndTop,gameEndButton);
        gameEndStackPane.getChildren().add(gameEnd);
        vBoxHint.getChildren().addAll(hintButton1,hintButton2,hintButton3);
        vBoxHint2.getChildren().addAll(hintButton4,hintButton5,hintButton6);
        vBoxHint3.getChildren().addAll(hintButton7,hintButton8,hintButton9);
        hintMenu.getChildren().addAll(vBoxHint,vBoxHint2,vBoxHint3);
        hintMenuStack.getChildren().add(hintMenu);

    }
    private void listen(){
        //new Size Listeners
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            setSize(scene.getWidth(),scene.getHeight());
            setDisplay(currentGraph);
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            setSize(scene.getWidth(),scene.getHeight());
            setDisplay(currentGraph);
        });
        //Button Listeners
        upperLeftButton.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
            canvasStackPane.getChildren().add(leftVBox);
        });
        leftVBox.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
        });
        upper1Label.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
        });
        left1HintButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().addAll(hintMenuStack,backPane);
        });
        left2Button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
            stackPane.getChildren().add(newGraphHBox);
            stackPane.getChildren().add(backPane);
        });
        left3Button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
            stackPane.getChildren().add(newGamemodeHBox);
            stackPane.getChildren().add(backPane);
        });
        left4Button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
            stackPane.getChildren().add(newGraphModeHBox);
            stackPane.getChildren().add(backPane);
        });
        left5Button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            canvasStackPane.getChildren().removeAll(leftVBox);
            finished();
        });

        //Back Button
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphHBox,newGamemodeHBox,newGraphModeHBox,sMBHBox,backPane,listView,textFieldHBox,listViewVBox,hintMenuStack);
        });
        //Other Buttons
        newGraphButtonYes.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(newGraphHBox,backPane);
            setNewGraph();
            setDisplay(currentGraph);
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
            setNewGraph();
            setDisplay(currentGraph);
        });
        middleButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(sMBHBox,backPane);
            graphMode=1;
            selectedSize=2;
            setNewGraph();
            setDisplay(currentGraph);
        });
        bigButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(sMBHBox,backPane);
            graphMode=1;
            selectedSize=3;
            setNewGraph();
            setDisplay(currentGraph);
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            myGraph=newValue.toString();
        });
        submit2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(listViewVBox,backPane);
            graphMode=2;
            setNewGraph();
            setDisplay(currentGraph);
        });
        buttonTextfield.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(textFieldHBox,backPane);
            graphMode=3;
            setNewGraph();
        });
        newGamemodebutton1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gamemode=1;
            stackPane.getChildren().removeAll(newGamemodeHBox,backPane);
        });
        newGamemodebutton2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gamemode=2;
            stackPane.getChildren().removeAll(newGamemodeHBox,backPane);
            setNewGraph();
            setDisplay(currentGraph);
        });
        newGamemodebutton3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gamemode=3;
            stackPane.getChildren().removeAll(newGamemodeHBox,backPane);
            setNewGraph();
            setDisplay(currentGraph);
        });
        gameEndButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            stackPane.getChildren().removeAll(gameEndStackPane);
            setDisplay(currentGraph);
        });

        hintButton1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            giveHint(1);
            hintButton1.setMouseTransparent(true);
            stackPane.getChildren().removeAll(hintMenuStack,backPane);
        });
        hintButton2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            giveHint(1);
            hintButton2.setMouseTransparent(true);
            stackPane.getChildren().removeAll(hintMenuStack,backPane);
        });
        hintButton3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            giveHint(1);
            hintButton3.setMouseTransparent(true);
            stackPane.getChildren().removeAll(hintMenuStack,backPane);
        });
        hintButton4.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            giveHint(1);
            hintButton4.setMouseTransparent(true);
            stackPane.getChildren().removeAll(hintMenuStack,backPane);
        });
        hintButton5.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            giveHint(1);
            hintButton5.setMouseTransparent(true);
            stackPane.getChildren().removeAll(hintMenuStack,backPane);
        });
        hintButton6.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            giveHint(1);
            hintButton6.setMouseTransparent(true);
            stackPane.getChildren().removeAll(hintMenuStack,backPane);
        });

    }
    private void setDisplay(Graph graph){
        upper2Label.setText("");
        if(currentGraph!=null) {

            if (timer != null) {
                timer.stop();
            }
            if (gamemode == 2) {
                double time = 15;
                timing = (int) time;
                timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    timing--;
                    upper2Label.setText(Double.toString(timing));
                }));

                timer.setCycleCount((int) time);
                timer.setOnFinished(event -> {
                    timing = 0;
                    timerUp();
                });
                timer.play();

            }
            clear();
            double myWidth = canvas.getWidth();
            double myHeight = canvas.getHeight();
            System.out.println(myWidth + " " + myHeight);
            ArrayList<Dot> list = graph.getList();
            Random random = new Random();
            for (int i = 0; i < list.size(); i++) {
                int x = random.nextInt((int) Math.round(myWidth / 4)) + (int) Math.round((myWidth / 2) - myWidth / 8);
                int y = random.nextInt((int) Math.round(myHeight / 4)) + (int) Math.round((myHeight / 2) - myHeight / 8);
                list.get(i).setPosition(new Position(x, y));
                list.get(i).setParent(this);
                if (gamemode == 3) {
                    list.get(i).gameMode = 3;
                    list.get(i).removeMain();
                }
                //list.get(i).setOnAction(graphHandeler);
                list.get(i).getStyleClass().add("graphButton");
                pane.getChildren().add(list.get(i));
            }
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), ev -> {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                ArrayList<Position> positionArrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Dot d = list.remove(i);
                    positionArrayList.add(d.calculateVectors(list, d.giveList(), canvas.getWidth(), canvas.getHeight()));
                    list.add(i, d);
                }
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setPosition(positionArrayList.get(i));
                }
                for (int i = 0; i < list.size(); i++) {
                    printDot(list.get(i), true);
                    printDot(list.get(i), false);
                }
            }));
            timeline.setCycleCount(Parameters.trys);
            timeline.play();
            if (gamemode == 3) {
                list.get(1).getChildren().add(list.get(1).hBox);
            }
        }

    }
    public void newDot(Dot dot,boolean colored){
        if(colored){
            canvas.getGraphicsContext2D().setLineWidth(2);
            canvas.getGraphicsContext2D().setStroke(Color.DEEPPINK);
        }else{
            canvas.getGraphicsContext2D().setStroke(Color.BLACK);
            canvas.getGraphicsContext2D().setLineWidth(2);
        }
        ArrayList<Dot> myList = dot.giveList();
        for(int x=0;x<myList.size();x++){
            if(myList.get(x).getWasClicked()){
                Position position1 = dot.position;
                Position position2 = myList.get(x).position;
                canvas.getGraphicsContext2D().strokeLine(position1.x+15,position1.y+15,position2.x+15,position2.y+15);
            }
        }
    }
    public Dot findNewDot(){
        ArrayList<Dot> myList = currentGraph.getList();
        for(int i=0;i<myList.size();i++){
            if(!myList.get(i).getWasClicked()){
                return myList.get(i);
            }
        }
        return null;
    }
    public void setGraph(){
        setDisplay(currentGraph);
    }
    public void clear(){
        canvas.getGraphicsContext2D().clearRect(canvas.getWidth(),canvas.getHeight(),1,1);
        pane.getChildren().clear();
    }
    public void printDot(Dot dot,boolean colored){
        if(gamemode==3){
            return;
        }
        else {
        	ArrayList<Dot> myList = dot.giveList();
        	for(int x=0;x<myList.size();x++){
        		Position position1 = dot.position;
        		Dot myDot = myList.get(x);
        		Position position2 = myDot.position;
        		if(colored){
        			if( true &&myDot.coloredAs!=null && CalculateScore.hintEightUsed){
        				canvas.getGraphicsContext2D().setStroke(myDot.coloredAs);
        			}
        			else if (true && myDot.coloredAs!= null && CalculateScore.hintSevenUsed){
        				canvas.getGraphicsContext2D().setStroke(Color.DEEPPINK);
        			}
        		}
        		else{
        			canvas.getGraphicsContext2D().setStroke(Color.BLACK);
        		}
        		canvas.getGraphicsContext2D().strokeLine(position1.x+15,position1.y+15,position2.x+15,position2.y+15);
        	}
        }
    }
    public boolean coloredRight(Graph graph){
        ArrayList<Dot> list = graph.getList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).coloredAs==null){
                return false;
            }
            ArrayList<Dot> innerList = list.get(i).giveList();
            for(int x=0;x<innerList.size();x++){
                if(list.get(i).coloredAs.equals(innerList.get(x).coloredAs)){
                    return false;
                }
            }
        }

        if(timer!=null){ timer.stop();}
        return true;
    }
    private void timerUp(){
        stackPane.getChildren().add(gameEndStackPane);
        System.out.println("TOP");
    }
    private void setNewGraph() {
	//CalculateScore.resetHints();
        Random random = new Random();
        if (graphMode == 1) {
            if (selectedSize == 1) {
                currentGraph = chromaticManager.calculate(random.nextInt(myInformation.smallGraph) + 1, -1);
            }
            if (selectedSize == 2) {
                currentGraph = chromaticManager.calculate(random.nextInt(myInformation.middleGraph - myInformation.smallGraph) + myInformation.smallGraph + 1, -1);
            }
            if (selectedSize == 3) {
                currentGraph = chromaticManager.calculate(random.nextInt(myInformation.bigGraph - myInformation.middleGraph) + myInformation.middleGraph + 1, -1);
            }
        }
        if (graphMode == 2) {
            currentGraph = chromaticManager.calculate(myGraph);
        }
        if (graphMode == 3) {
            try {
                stackPane.getChildren().removeAll(textFieldHBox, backPane);
                myVertices = Integer.parseInt(textFieldVertices.getText());
                myEdges = Integer.parseInt(textFieldEdges.getText());
                currentGraph = chromaticManager.calculate(myVertices, myEdges);
                setDisplay(currentGraph);
            } catch (NumberFormatException e) {

            }
        }
    }
	
public void giveHint(int hintModeChosen) {
    	canvasStackPane.getChildren().removeAll(leftVBox);
        
     	if(hintModeChosen==1) {//give upper bound - level 1
             upper2Label.setText("UpperBound: " + Integer.toString(currentGraph.getUpperBound()));
             CalculateScore.hintOneUsed = true;
         }
        
     	if (hintModeChosen==2) {//give chromatic number - level 1
     		 upper2Label.setText("Chromatic number: " + Integer.toString(currentGraph.getCNumber()));
     		 CalculateScore.hintFourUsed = true;
     	}
     	
         if (hintModeChosen==3) {//give possible colours - level 1
        	 hint2 = true;
             CalculateScore.hintThreeUsed = true;
         }
         
		 if(hintModeChosen ==4){//most connected vertix - level 2
			 int maximumConnections = 0;
			  Dot mostConnections = new Dot();
			  ArrayList<Dot> dots = (ArrayList<Dot>) currentGraph.getList().clone();
			        
			  for(int i =0;i<dots.size();i++){
				  Dot currentDot = (Dot) dots.get(i);
			      int currentConnections = currentDot.getNrOfConnections();
			          if (currentConnections>=maximumConnections){
			               maximumConnections=currentConnections;
			               mostConnections = (Dot) currentDot;
			          }
			   }
			   mostConnections.markedAsHint();
			   CalculateScore.hintFourUsed = true;
		  }
		 
		 if (hintModeChosen==5){//show if a new colour should be used - level 2
             ArrayList<Dot> dots = (ArrayList<Dot>) currentGraph.getList().clone();
             for (int i = 0; i<dots.size();i++){
                 int p = 0;
                 if (dots.get(i).coloredAs==null){
                     ArrayList<Dot> connections = dots.get(i).giveList();
                     for (int j = 0; j<connections.size();j++){
                         if (colorsUsers.contains(connections.get(j).coloredAs)){
                           p++;
                         }
                         if (p==connections.size()){
                             upper3Label.setText("Add a new colour");
                         }
                     }
                 }
             }
             CalculateScore.hintFiveUsed = true;
         }
		 
		  if(hintModeChosen ==6) {//biggest clique - level 2
			   ArrayList<Dot> maximumClique = (ArrayList<Dot>) Bk.getMaximumClique().clone();
			   for (int i = 0; i< maximumClique.size(); i++) {
				   maximumClique.get(i).markedAsSecondHint();
			   }
			   CalculateScore.hintSixUsed = true;
		  }
		  
		  if (hintModeChosen ==7) {//show edges that are connected to vertix - level 3
			  CalculateScore.hintSevenUsed = true;
		  }
		  
		  if (hintModeChosen ==8) {//show coloured edges - level 3
			  CalculateScore.hintSevenUsed = true;
			  CalculateScore.hintEightUsed = true;
		  }
		  
		  if (hintModeChosen ==9) {//tells you if you can use a colour during the whole game - level 3
			  
			  CalculateScore.hintNineUsed = true;
		  }
    }

	
    public void finished(){
        System.out.println(coloredRight(currentGraph));
	CalculateScore score = new CalculateScore();
        System.out.println(score.giveScore() + "%");
	//should be displayed in the score window when the game is finished
    }
}
