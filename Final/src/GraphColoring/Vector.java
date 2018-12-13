package GraphColoring;
/*
* Short summary:
* A class that creates vectors, and can change their position.
*/

public class Vector {
    public double top;
    public double down;
    public double length;
    
    //creates a vector, with a top and a down you can put in, so the vector is like this: [top; down]
    public Vector(double top,double down){
        this.top=top;
        this.down=down;
    }
    //creates a vector, that goes from one position to the other, and calculates its length
    public Vector(Position p1,Position p2){
        top=p2.x-p1.x;
        down=p2.y-p1.y;
        length= Math.sqrt(this.top*this.top+this.down*this.down);
    }
    //multiplies a vector
    public void multiply(double multiplier){
        top=top*multiplier;
        down=down*multiplier;

    }
    //adds a vector to another vector
    public void add(Vector vector){
        this.top=this.top+vector.top;
        this.down=this.down+vector.down;
    }
}
