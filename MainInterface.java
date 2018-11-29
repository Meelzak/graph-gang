package GraphColoring;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        vBoxRight.getChildren().addAll(buttonRight1,buttonRight2,buttonRight3);

        hBoxTop.getChildren().add(buttonHelp);
        hBoxBottom.getChildren().addAll(vBoxLeft,vBoxRight);

        vBoxMain.getChildren().addAll(hBoxTop,hBoxBottom);
        stackPaneMain.getChildren().add(vBoxMain);
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
    public void listen(){
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

}
