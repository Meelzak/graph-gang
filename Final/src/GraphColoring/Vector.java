package GraphColoring;

public class Vector {

    public double top;
    public double down;
    public double length;
    public Vector(double top,double down){
        this.top=top;
        this.down=down;
    }
    //show 1 to 2
    public Vector(Position p1,Position p2){
        top=p2.x-p1.x;
        down=p2.y-p1.y;
        length= Math.sqrt(this.top*this.top+this.down*this.down);
    }
    public void multiply(double multiplier){
        top=top*multiplier;
        down=down*multiplier;

    }
    public void add(Vector vector){
        this.top=this.top+vector.top;
        this.down=this.down+vector.down;
    }
}
