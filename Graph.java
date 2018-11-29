package GraphColoring;
/**
 * Author:
 * Cavid Karca
 */
import java.util.ArrayList;
public class Graph{
    private int edges;
    private int vertices;
    private int upperBound=-1;
    private int lowerBound=-1;
    private int cNumber=-1;
    private ArrayList<Dot> list;
    public Graph(int vertices,int edges,ArrayList list){
        //A class were all information of a Graph are safed
        this.edges=edges;
        this.vertices=vertices;
        this.list=(ArrayList)list.clone();
    }
    //Setters and Getters
    //If lowerBound/upperBound/cNumber == -1 it follows Graph was not calculated
    public int getEdges() {
        return edges;
    }
    public ArrayList getList() {
        return list;
    }
    public int getVertices() {
        return vertices;
    }
    public void setUpperBound(int upperBound){
        this.upperBound=upperBound;
    }
    public void setLowerBound(int lowerBound){
        this.lowerBound=lowerBound;
    }
    public void setCNumer(int cNumber){
        this.cNumber=cNumber;
    }
    public int getUpperBound(){
        return upperBound;
    }
    public int getLowerBound(){
        return lowerBound;
    }
    public int getCNumber(){
        return cNumber;
    }

}