package GraphColoring;
/*
* Short summary:
* This is the class that inherets the scene, and adds a Parent and Dimension as parameters
*/

import javafx.scene.Parent;
import javafx.scene.Scene;
import java.awt.*;
public class BetterScene extends Scene{

    public BetterScene(Parent parent, Dimension dimension){
        super(parent,dimension.width,dimension.height);
    }
}
