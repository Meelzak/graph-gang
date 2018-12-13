package GraphColoring;
/*
* Short summary:
* The class Electron, which is used by the Dot class, is important for the force directed graph. 
* It makes sure that the dots don't overlap, and the graph gets its shape.
*/

import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.Random;

public class Electron extends StackPane {
    public Position position;
    
    //first, set the position, and then relocates it using the relocate method
    public void setPosition(Position p){
        this.position=p;
        this.relocate(position.x,position.y);
    }
    
    //creates a vector with the position you put in before, and the position of the Dot
    //after that, calculates what length the vector should be, and then multiply the vector to get that length
    public Vector calculateVector(Dot electron){
        Vector vector= new Vector(this.position,electron.position);
        if(vector.length<=0){
            Random random = new Random();
            int x= random.nextInt(2);
            if(x==1){
                vector.down=2;
                vector.top=2;
                vector.length=Math.sqrt(8);
            }else{
                vector.down=-3;
                vector.top=1;
                vector.length=Math.sqrt(10);
            }
        }
        double plannedDistance=0;
        if(vector.length<Parameters.pushOfTo-1){
            plannedDistance=Parameters.maxPushOf-Math.pow(Math.pow(Parameters.maxPushOf,1/Parameters.pushOfTo),vector.length);
        }else{
            plannedDistance=0;
        }
        vector.multiply(plannedDistance/vector.length);
        return vector;
    }
    
    //needs connected Electron, then calculates what the length of the vector using this electron should be, and gives the vector
    public Vector calculateSpring(Dot electron){
        Vector vector= new Vector(this.position,electron.position);
        if(vector.length<=0){
            return new Vector(0,0);
        }
        double plannedDistance=0;
        if(vector.length>Parameters.pushOfToSpring){
            plannedDistance=Parameters.maxPushOfSpring;
        }
        else{
            plannedDistance=Parameters.maxPushOfSpring/Parameters.pushOfToSpring*vector.length;
        }
        vector.multiply((plannedDistance/vector.length)*-1);
        return vector;
    }
    
    //makes sure that the dots are not being pushed outside the border, where the player can't reach them
    //so it changes the vector so this won't happen
    public Vector borderPush(double witdth,double heigth){
        int i=-1;
        if(this.position.y<0){ i=1; }
        Vector vectorTop = new Vector(new Position(position.x,0),this.position);
        vectorTop.multiply(borderAlgorithm(vectorTop)/vectorTop.length*i);

        i=-1;
        if(this.position.y>heigth){ i=1; }
        Vector vectorBottom = new Vector(new Position(position.x,heigth),this.position);
        vectorBottom.multiply(borderAlgorithm(vectorBottom)/vectorBottom.length*i);

        i=-1;
        if(this.position.x<0){ i=1; }
        Vector vectorRight = new Vector(new Position(0,position.y),this.position);
        vectorRight.multiply(borderAlgorithm(vectorRight)/vectorRight.length*i);

        i=-1;
        if(this.position.x>witdth){ i=1; }
        Vector vectorLeft = new Vector(new Position(witdth,position.y),this.position);
        vectorLeft.multiply(borderAlgorithm(vectorLeft)/vectorLeft.length*i);

        vectorTop.add(vectorBottom);
        vectorTop.add(vectorRight);
        vectorTop.add(vectorLeft);
        return vectorTop;
    }
    
    //calculates how much the vectors should be adjusted
    private double borderAlgorithm(Vector vector){
        if(vector.length<=0){
            return Parameters.maxPushOfBorder;
        }
        if(vector.length>=Parameters.pushOfToBorder){
            return 0;
        }
        /*if(vector.length<50){
            return (100-Math.pow((vector.length*1/13),2));
        }*/
        return Parameters.maxPushOfBorder-(Parameters.maxPushOfBorder/Parameters.pushOfToBorder*vector.length);
    }

    //calculates the position that should be returned, using the vectors, and then can give it to the other classes
    public Position calculateVectors(ArrayList<Dot> electronArrayList, ArrayList<Dot> connectedElectronsArrayList, double width, double height){
        Vector finalVector = new Vector(0,0);

        for(int i=0;i<electronArrayList.size();i++){
            finalVector.add(this.calculateVector(electronArrayList.get(i)));
        }


        finalVector.add(this.borderPush(width,height));


        for(int i=0;i<connectedElectronsArrayList.size();i++){
            finalVector.add(this.calculateSpring(connectedElectronsArrayList.get(i)));
        }


        Position positionNew = new Position(position.x-finalVector.top,position.y-finalVector.down);

        return positionNew;

    }
}
