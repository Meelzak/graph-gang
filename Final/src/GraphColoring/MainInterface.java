package GraphColoring;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.util.ArrayList;

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
    private StackPane button123 = new StackPane();
    //Objects
    //Left Buttons
    private Button buttonLeft1 = new Button("To the bitter End");
    private Button buttonLeft2 = new Button("Best upper bound in Time");
    private Button buttonLeft3 = new Button("Random Order");
    //Right Buttons
    private Button buttonRight1 = new Button("Random Graph");
    private Button buttonRight2 = new Button("Graph from Textfile");
    private Button buttonRight3 = new Button("Create Graph");
    //Help Button
    private Button buttonHelp = new Button();
    private Button buttonTextfield = new Button("Submit");
    //rest
    private int leftOrRigth=1;
    private BetterScene scene;
    private Starter starter;
    //Informations
    public int gameMode=-1;
    public int graphMode=-1;
    public String selectedGraph="";
    public int selectedSize=-1;
    public int selectedVertices=-1;
    public int selectedEdges =-1;
    private BooleanProperty finished = new SimpleBooleanProperty(false);
    public BooleanProperty sceneFinished = new SimpleBooleanProperty(false);

    //Second Period of mainInterface
    //Regions
    private HBox buttonRight1HBox = new HBox();
    private ListView listView = new ListView();
    private HBox buttonRight3HBox = new HBox();
    //Objects
    private Button smallButton = new Button("Small");
    private Button middleButton = new Button("Middle");
    private Button bigButton = new Button("Big");

    private TextField textFieldVertices = new TextField();
    private TextField textFieldEdges = new TextField();
    private boolean rightFinished=false;
    private StackPane enterStackPane = new StackPane();
    private Text enter = new Text("Enter");
    //test
//----------------------------------------------------------------------------------------------------------------
    public MainInterface(Starter starter){
        this.starter=starter;
    }


    public Scene getScene(){
        textFieldVertices.setPromptText("Vertices");
        textFieldEdges.setPromptText("Edges");
        enterStackPane.setMouseTransparent(true);
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

        smallButton.getStyleClass().add("buttonMenu");
        middleButton.getStyleClass().add("buttonMenu");
        bigButton.getStyleClass().add("buttonMenu");
        textFieldVertices.getStyleClass().add("textfield");
        textFieldEdges.getStyleClass().add("textfield");
        buttonTextfield.getStyleClass().add("buttonMenu");
        //Regions p2
        buttonRight1HBox.getStyleClass().add("buttonRight1HBox");
        listView.getStyleClass().add("listView");
        buttonRight3HBox.getStyleClass().add("buttonRight3HBox");

        enterStackPane.getStyleClass().add("enterStackPane");
        enter.getStyleClass().add("enter");


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
        listView.setItems(FXCollections.observableArrayList(starter.giveGraphs()));
        button123.getChildren().add(listView);
        buttonRight3HBox.getChildren().addAll(textFieldVertices,textFieldEdges,buttonTextfield);
        enterStackPane.getChildren().add(enter);
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
        double scrollPaneWidth=buttonWidthRight/100*myInformation.ListViewWidthPercent;
        double sMBButtonWidth=buttonWidthRight/100*myInformation.SmallMiddleBigButtonPercent.width;
        double sMBButtonHeight=buttonHeight/100*myInformation.SmallMiddleBigButtonPercent.height;
        double textFieldWidth=buttonWidthRight/100*myInformation.TextfiledPercent.width;
        double textFieldHeight=buttonHeight/100*myInformation.TextfiledPercent.height;

        enterStackPane.setMinSize(width,height);
        enterStackPane.setMaxSize(width,height);

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
        listView.setMinSize(scrollPaneWidth,buttonHeight-6);
        listView.setMaxSize(scrollPaneWidth,buttonHeight-6);
        buttonRight3HBox.setMinSize(buttonWidthRight,buttonHeight);
        buttonRight3HBox.setMaxSize(buttonWidthRight,buttonHeight);

        smallButton.setMinSize(sMBButtonWidth,sMBButtonHeight);
        smallButton.setMaxSize(sMBButtonWidth,sMBButtonHeight);
        middleButton.setMinSize(sMBButtonWidth,sMBButtonHeight);
        middleButton.setMaxSize(sMBButtonWidth,sMBButtonHeight);
        bigButton.setMinSize(sMBButtonWidth,sMBButtonHeight);
        bigButton.setMaxSize(sMBButtonWidth,sMBButtonHeight);
        buttonTextfield.setMinSize(sMBButtonWidth/1.3,sMBButtonHeight);
        buttonTextfield.setMaxSize(sMBButtonWidth/1.3,sMBButtonHeight);

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
        //phase 2
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedGraph = newValue.toString();
            rightFinished=true;
            if(gameMode>0){
                finished.setValue(true);
            }
        });
        smallButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            rightFinished=true;
            selectedSize=1;
            if(gameMode>0){
                finished.setValue(true);
            }
        });
        middleButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            rightFinished=true;
            selectedSize=2;
            if(gameMode>0){
                finished.setValue(true);
            }
        });
        bigButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            rightFinished=true;
            selectedSize=3;
            if(gameMode>0){
                finished.setValue(true);
            }
        });
        buttonTextfield.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            try {
                selectedVertices = Integer.parseInt(textFieldVertices.getText());
                selectedEdges = Integer.parseInt(textFieldEdges.getText());
                if(selectedVertices>0&&selectedEdges>0){
                    if(gameMode>0){
                        finished.setValue(true);
                    }
                }
            }catch (NumberFormatException e){

            }

        });
        finished.addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                stackPaneMain.getChildren().add(enterStackPane);
            }
        });
        scene.setOnKeyTyped(keyEvent -> {
            if(keyEvent.getCharacter().equals(" ")&&finished.getValue()){
                if(gameMode>0){
                    if(graphMode==1 && selectedSize>0||graphMode==2 && selectedGraph!=""||graphMode==3 && selectedEdges>0&&selectedVertices>0){
                        sceneFinished.setValue(true);
                    }
                }
            }
        }
        );

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
            if(rightFinished){
                finished.setValue(true);
            }
        });
        buttonLeft2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gameMode=2;
            buttonLeft2.getStyleClass().removeAll("buttonNormal");
            buttonLeft2.getStyleClass().add("buttonClicked");

            buttonLeft1.getStyleClass().removeAll("buttonClicked");
            buttonLeft1.getStyleClass().add("buttonNormal");
            buttonLeft3.getStyleClass().removeAll("buttonClicked");
            buttonLeft3.getStyleClass().add("buttonNormal");
            if(rightFinished){
                finished.setValue(true);
            }
        });
        buttonLeft3.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            gameMode=3;
            buttonLeft3.getStyleClass().removeAll("buttonNormal");
            buttonLeft3.getStyleClass().add("buttonClicked");

            buttonLeft1.getStyleClass().removeAll("buttonClicked");
            buttonLeft1.getStyleClass().add("buttonNormal");
            buttonLeft2.getStyleClass().removeAll("buttonClicked");
            buttonLeft2.getStyleClass().add("buttonNormal");
            if(rightFinished){
                finished.setValue(true);
            }
        });


        buttonRight1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            graphMode=1;
            buttonRight1.getStyleClass().removeAll("buttonNormal");
            buttonRight1.getStyleClass().add("buttonClicked");

            buttonRight1.setText("");
            buttonRight2.setText("Graph from Textfile");
            buttonRight3.setText("Create Graph");

            buttonRight1StackPane.getChildren().add(buttonRight1HBox);
            buttonRight2StackPane.getChildren().removeAll(button123);
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

            buttonRight2.setText("");
            buttonRight1.setText("Random Graph");
            buttonRight3.setText("Create Graph");

            buttonRight2StackPane.getChildren().add(button123);
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

            buttonRight3.setText("");
            buttonRight1.setText("Random Graph");
            buttonRight2.setText("Create Graph");

            buttonRight3StackPane.getChildren().add(buttonRight3HBox);
            buttonRight1StackPane.getChildren().removeAll(buttonRight1HBox);
            buttonRight2StackPane.getChildren().removeAll(button123);

            buttonRight1.getStyleClass().removeAll("buttonClicked");
            buttonRight1.getStyleClass().add("buttonNormal");
            buttonRight2.getStyleClass().removeAll("buttonClicked");
            buttonRight2.getStyleClass().add("buttonNormal");
        });
    }
    public Dimension getSize(){
        Dimension d = new Dimension();
        d.setSize(scene.getWidth(),scene.getHeight());
        return d;
    }

}
