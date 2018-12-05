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
        oben=p2.x-p1.x;
        unten=p2.y-p1.y;
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
