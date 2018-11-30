package GraphColoring;

public class Vector {
    public double oben;
    public double unten;
    public double length;
    public Vector(double oben,double unten){
        this.oben=oben;
        this.unten=unten;
    }
    //show 1 to 2
    public Vector(Position p1,Position p2){
        oben=p1.x-p2.x;
        unten=p1.y-p2.y;
        length= Math.sqrt(this.oben*this.oben+this.unten*this.unten);
    }
    public void multiply(double multiplier){
        oben=oben*multiplier;
        unten=unten*multiplier;

    }
    public void add(Vector vector){
        this.oben=this.oben+vector.oben;
        this.unten=this.unten+vector.unten;
    }
}
