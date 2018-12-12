package GraphColoring;
/**
 * Short summary:
 * This class manages all the graphs and algorithms to calculate and generate them.
 * It will set the lower and upper bound, and the chromatic number.
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
        //create class instances of the classes that are used to calculate:
        reader = new Reader(path);
        bk = new Bk();
        powell = new Powell();
        newForce = new NewForce();
        generator = new Generator();
        files = new ArrayList();

        //add files to an ArrayList of Strings:
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
        
        //will calculate the upperbound, lowerbound and chromatic number, and then set their values
        int upper=powell.doPowell(graph);
        int lower=bk.doBK(graph);
        //int c = newForce.doNewForce(graph, upper, lower);
        //graph.setCNumer(c);
        graph.setLowerBound(lower);
        graph.setUpperBound(upper);
        return graph;
    }
    public Graph calculate(int vertices,int edges){
        //Returns a random Graph made by a generator
        Graph graph = generator.GenerateGraph(vertices, edges);
        
        //will calculate the upperbound, lowerbound and chromatic number, and then set their values
        int upper=powell.doPowell(graph);
        int lower=bk.doBK(graph);
        //int c = newForce.doNewForce(graph, upper, lower);
        //graph.setCNumer(c);
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
