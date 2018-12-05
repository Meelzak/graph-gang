package GraphColoring;
/**
 * Author:
 * Cavid Karca
 */
import java.io.File;
import java.util.ArrayList;

public class ChromaticManager{
    private ArrayList<String> files;
    private Reader reader;
    private Generator generator;
    private Bk bk;
    private Powell powell;
    private NewForce newForce;
    public ChromaticManager(String path){
        //Manages all the Graphs and Algorithms to calculate and Generate them
        reader = new Reader(path);
        bk = new Bk();
        powell = new Powell();
        newForce = new NewForce();
        generator = new Generator();
        files = new ArrayList();

        File file = new File(path);
        String[] f = file.list();
        for(int i=0;i<f.length;i++){
            if(f[i].endsWith(".txt")){
                files.add(f[i]);
            }
        }
        
    }
    public Graph calculate(String filename){
        // Returns the Colored graph filename
        Graph graph = reader.read(filename);
        int upper=powell.doPowell(graph);
        int lower=bk.doBK(graph);
        int c = newForce.doNewForce(graph, upper, lower);
        graph.setCNumer(c);
        graph.setLowerBound(lower);
        graph.setUpperBound(upper);
        return graph;
    }
    public Graph calculate(int vertices,int edges){
        //Returns a random Graph by generator
        Graph graph = generator.GenerateGraph(vertices, edges);
        int upper=powell.doPowell(graph);
        int lower=bk.doBK(graph);
        int c = newForce.doNewForce(graph, upper, lower);
        graph.setCNumer(c);
        graph.setLowerBound(lower);
        graph.setUpperBound(upper);
        return graph;
    }
    public void generate(int vertices,int edges,String filename){
        //Generates a new graph and saves it as filename
        generator.GenerateGraph(vertices, edges, filename);
        files.add(filename);
    }
    public ArrayList<String> listFiles(){
        //Returns a list of all graphs in this file
        return files;
    }
}