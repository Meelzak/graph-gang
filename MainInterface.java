package GraphColoring;

import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

public class MainInterface {
    //Regions
    //Main
    private StackPane stackPaneMain=new StackPane();
    private VBox vBoxMain = new VBox();
    //Sub
    private HBox hBoxTop = new HBox();
    private HBox hBoxBottom = new HBox();
    private VBox vBoxLeft = new VBox();
    private VBox vBoxRight = new VBox();
    private StackPane buttonRight1StackPane = new StackPane();
    private StackPane buttonRight2StackPane = new StackPane();
    private StackPane buttonRight3StackPane = new StackPane();
    //Objects
    //Left Buttons
    private Button buttonLeft1 = new Button();
    private Button buttonLeft2 = new Button();
    private Button buttonLeft3 = new Button();
    //Right Buttons
    private Button buttonRight1 = new Button();
    private Button buttonRight2 = new Button();
    private Button buttonRight3 = new Button();
    //Help Button
    private Button buttonHelp = new Button();
    //rest
    private int leftOrRigth=1;
    private BetterScene scene;
    private Starter starter;
    //Informations
    public int gameMode=-1;
    public int graphMode=-1;
    public BooleanProperty finished = new SimpleBooleanProperty(false);

    //Second Period of mainInterface
    //Regions
    private HBox buttonRight1HBox = new HBox();
    private ScrollPane buttonRight2ScrollPane = new ScrollPane();
    private HBox buttonRight3HBox = new HBox();
    //Objects
    private Button smallButton = new Button();
    private Button middleButton = new Button();
    private Button bigButton = new Button();

    private TextField textFieldVertices = new TextField();
    private TextField textFieldEdges = new TextField();

//----------------------------------------------------------------------------------------------------------------
    public MainInterface(Starter starter){
        this.starter=starter;
    }


    public Scene getScene(){
        setSize(myInformation.startDimension.width,myInformation.startDimension.height);
        connect();
        scene = new BetterScene(stackPaneMain,myInformation.startDimension);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        styling();
        listen();
        return scene;
    }

    private void styling(){
        //Region
        stackPaneMain.getStyleClass().add("stackPaneMain");
        hBoxTop.getStyleClass().add("hBoxTop");
        hBoxBottom.getStyleClass().add("hBoxBottom");
        vBoxLeft.getStyleClass().add("vBoxLeft");
        vBoxRight.getStyleClass().add("vBoxRight");
        // Buttons
        buttonHelp.getStyleClass().add("buttonHelp");
        buttonLeft1.getStyleClass().add("buttonNormal");
        buttonLeft2.getStyleClass().add("buttonNormal");
        buttonLeft3.getStyleClass().add("buttonNormal");
        buttonRight1.getStyleClass().add("buttonNormal");
        buttonRight2.getStyleClass().add("buttonNormal");
        buttonRight3.getStyleClass().add("buttonNormal");
    }
    private void connect(){
        vBoxLeft.getChildren().addAll(buttonLeft1,buttonLeft2,buttonLeft3);
        buttonRight1StackPane.getChildren().add(buttonRight1);
        buttonRight2StackPane.getChildren().add(buttonRight2);
        buttonRight3StackPane.getChildren().add(buttonRight3);
        vBoxRight.getChildren().addAll(buttonRight1StackPane,buttonRight2StackPane,buttonRight3StackPane);

        hBoxTop.getChildren().add(buttonHelp);
        hBoxBottom.getChildren().addAll(vBoxLeft,vBoxRight);

        vBoxMain.getChildren().addAll(hBoxTop,hBoxBottom);
        stackPaneMain.getChildren().add(vBoxMain);

        //Phase 2 stuff
        buttonRight1HBox.getChildren().addAll(smallButton,middleButton,bigButton);

        buttonRight3HBox.getChildren().addAll(textFieldVertices,textFieldEdges);
    }
    private void setSize(double width,double height){
        double topHeight=height/100*myInformation.topToBottomPercent;
        double bottomHeigth=height-topHeight;
        double leftWidth=0;
        double rightWidth=0;
        if(leftOrRigth==1){
            leftWidth=width/100*myInformation.leftToRigthPercent;
            rightWidth=width-leftWidth;
        }
        if(leftOrRigth==2){
            rightWidth=width/100*myInformation.leftToRigthPercent;
            leftWidth=width-rightWidth;
        }
        double buttonHeight=bottomHeigth/100*myInformation.ButtonHeigthPercent;
        double buttonWidthLeft=leftWidth/100*myInformation.ButtonWidthPercent;
        double buttonWidthRight=rightWidth/100*myInformation.ButtonWidthPercent;
        double scrollPaneWidth=buttonWidthRight/100*myInformation.ScrollPaneWidthPercent;
        double sMBButtonWidth=buttonWidthRight/100*myInformation.SmallMiddleBigButtonPercent.width;
        double sMBButtonHeight=buttonHeight/100*myInformation.SmallMiddleBigButtonPercent.height;
        double textFieldWidth=buttonWidthRight/100*myInformation.TextfiledPercent.width;
        double textFieldHeight=buttonHeight/100*myInformation.TextfiledPercent.height;

        stackPaneMain.setMinSize(width, height);
        stackPaneMain.setMaxSize(width, height);

        vBoxMain.setMinSize(width, height);
        vBoxMain.setMaxSize(width, height);

        hBoxTop.setMinSize(width,topHeight);
        hBoxTop.setMaxSize(width,topHeight);

        hBoxBottom.setMinSize(width,bottomHeigth);
        hBoxBottom.setMaxSize(width,bottomHeigth);

        vBoxLeft.setMinSize(leftWidth,bottomHeigth);
        vBoxLeft.setMaxSize(leftWidth,bottomHeigth);

        vBoxRight.setMinSize(rightWidth,bottomHeigth);
        vBoxRight.setMaxSize(rightWidth,bottomHeigth);

        //Phase 2 stuff
        buttonRight1StackPane.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight1StackPane.setMaxSize(buttonWidthRight,buttonHeight);
        buttonRight2StackPane.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight2StackPane.setMaxSize(buttonWidthRight,buttonHeight);
        buttonRight3StackPane.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight3StackPane.setMaxSize(buttonWidthRight,buttonHeight);

        buttonRight1HBox.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight1HBox.setMaxSize(buttonWidthRight,buttonHeight);
        buttonRight2ScrollPane.setMinSize(scrollPaneWidth,buttonHeight-6);
        buttonRight2ScrollPane.setMaxSize(scrollPaneWidth,buttonHeight-6);
        buttonRight3HBox.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight3HBox.setMaxSize(buttonWidthRight,buttonHeight);

        smallButton.setMinSize(sMBButtonWidth,sMBButtonHeight);
        smallButton.setMaxSize(sMBButtonWidth,sMBButtonHeight);
        middleButton.setMinSize(sMBButtonWidth,sMBButtonHeight);
        middleButton.setMaxSize(sMBButtonWidth,sMBButtonHeight);
        bigButton.setMinSize(sMBButtonWidth,sMBButtonHeight);
        bigButton.setMaxSize(sMBButtonWidth,sMBButtonHeight);

        textFieldVertices.setMinSize(textFieldWidth,textFieldHeight);
        textFieldVertices.setMaxSize(textFieldWidth,textFieldHeight);
        textFieldEdges.setMinSize(textFieldWidth,textFieldHeight);
        textFieldEdges.setMaxSize(textFieldWidth,textFieldHeight);
        //Phase 2 stuff end


        buttonLeft1.setMinSize(buttonWidthLeft,buttonHeight);
        buttonLeft1.setMaxSize(buttonWidthLeft,buttonHeight);
        buttonLeft2.setMinSize(buttonWidthLeft,buttonHeight);
        buttonLeft2.setMaxSize(buttonWidthLeft,buttonHeight);
        buttonLeft3.setMinSize(buttonWidthLeft,buttonHeight);
        buttonLeft3.setMaxSize(buttonWidthLeft,buttonHeight);

        buttonRight1.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight1.setMaxSize(buttonWidthRight,buttonHeight);
        buttonRight2.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight2.setMaxSize(buttonWidthRight,buttonHeight);
        buttonRight3.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight3.setMaxSize(buttonWidthRight,buttonHeight);

        buttonHelp.setMinSize(width/100*myInformation.ButtonHelpWidthPercent,topHeight/100*myInformation.ButtonHelpHeightPercent);
    }
    private void listen(){
        buttonListen();
        //new Size Listeners
        scene.widthProperty().addListener((observable, oldValue, newValue) -> setSize(scene.getWidth(),scene.getHeight()));

        scene.heightProperty().addListener((observable, oldValue, newValue) -> setSize(scene.getWidth(),scene.getHeight()));

        vBoxLeft.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            leftOrRigth=1;
            setSize(scene.getWidth(),scene.getHeight());
        });
        vBoxRight.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            leftOrRigth=2;
            setSize(scene.getWidth(),scene.getHeight());
        });

    }
    private void buttonListen(){
        //buttons
        buttonLeft1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gameMode=1;
            buttonLeft1.getStyleClass().removeAll("buttonNormal");
            buttonLeft1.getStyleClass().add("buttonClicked");

            buttonLeft2.getStyleClass().removeAll("buttonClicked");
            buttonLeft2.getStyleClass().add("buttonNormal");
            buttonLeft3.getStyleClass().removeAll("buttonClicked");
            buttonLeft3.getStyleClass().add("buttonNormal");
        });
        buttonLeft2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gameMode=2;
            buttonLeft2.getStyleClass().removeAll("buttonNormal");
            buttonLeft2.getStyleClass().add("buttonClicked");

            buttonLeft1.getStyleClass().removeAll("buttonClicked");
            buttonLeft1.getStyleClass().add("buttonNormal");
            buttonLeft3.getStyleClass().removeAll("buttonClicked");
            buttonLeft3.getStyleClass().add("buttonNormal");
        });
        buttonLeft3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gameMode=3;
            buttonLeft3.getStyleClass().removeAll("buttonNormal");
            buttonLeft3.getStyleClass().add("buttonClicked");

            buttonLeft1.getStyleClass().removeAll("buttonClicked");
            buttonLeft1.getStyleClass().add("buttonNormal");
            buttonLeft2.getStyleClass().removeAll("buttonClicked");
            buttonLeft2.getStyleClass().add("buttonNormal");
        });


        buttonRight1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            graphMode=1;
            buttonRight1.getStyleClass().removeAll("buttonNormal");
            buttonRight1.getStyleClass().add("buttonClicked");
            buttonRight1StackPane.getChildren().add(buttonRight1HBox);
            buttonRight2StackPane.getChildren().removeAll(buttonRight2ScrollPane);
            buttonRight3StackPane.getChildren().removeAll(buttonRight3HBox);

            buttonRight2.getStyleClass().removeAll("buttonClicked");
            buttonRight2.getStyleClass().add("buttonNormal");
            buttonRight3.getStyleClass().removeAll("buttonClicked");
            buttonRight3.getStyleClass().add("buttonNormal");
        });
        buttonRight2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            graphMode=2;
            buttonRight2.getStyleClass().removeAll("buttonNormal");
            buttonRight2.getStyleClass().add("buttonClicked");
            buttonRight2StackPane.getChildren().add(buttonRight2ScrollPane);
            buttonRight1StackPane.getChildren().removeAll(buttonRight1HBox);
            buttonRight3StackPane.getChildren().removeAll(buttonRight3HBox);

            buttonRight1.getStyleClass().removeAll("buttonClicked");
            buttonRight1.getStyleClass().add("buttonNormal");
            buttonRight3.getStyleClass().removeAll("buttonClicked");
            buttonRight3.getStyleClass().add("buttonNormal");
        });
        buttonRight3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            graphMode=3;
            buttonRight3.getStyleClass().removeAll("buttonNormal");
            buttonRight3.getStyleClass().add("buttonClicked");
            buttonRight3StackPane.getChildren().add(buttonRight3HBox);
            buttonRight1StackPane.getChildren().removeAll(buttonRight1HBox);
            buttonRight2StackPane.getChildren().removeAll(buttonRight2ScrollPane);

            buttonRight1.getStyleClass().removeAll("buttonClicked");
            buttonRight1.getStyleClass().add("buttonNormal");
            buttonRight2.getStyleClass().removeAll("buttonClicked");
            buttonRight2.getStyleClass().add("buttonNormal");
        });
    }

}
