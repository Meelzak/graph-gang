package GraphColoring;
/**
 * Author:
 * Cavid Karca
 */
import java.util.ArrayList;
public class Dot extends Electron{
    private int content=0;
    private ArrayList<Dot> list;
    private int coloredAs=0;
    public Dot(){
        //A class representing a single vertice of a graph
        list =new ArrayList<Dot>();
        this.setMinSize(30,30);
    }
    public void setConnection(Dot dot){
        list.add(dot);
    }
    public void setContent(int c){
        content = c;
    }
    public int giveContent(){ return content; }
    public ArrayList giveList(){
        return list;
    }
    public void setColoredAs(int color){coloredAs=color;}
    public int getColor(){ return coloredAs; }

}