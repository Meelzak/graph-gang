package GraphColoring;
import java.io.IOException;

import java.util.ArrayList;

public class GeneratorTest2{

    public static void main(String[] args){
        /*
        Reader reader = new Reader("C:/Users/cavid/Dropbox/Private/Project/Project New");
        Graph graph = reader.read(args[0]);
        Bk bk = new Bk();
        int b = bk.doBK(graph);
        System.out.println("Ergebnis BK: "+b );
        Powell powell = new Powell();
        int p = powell.doPowell(graph);
        System.out.println("Ergebnis Powell: "+p );
        Force force = new Force();
        int f = force.doForce(graph, b, p);
        System.out.println("Ergebnis Force:"+ f);
        */
        ChromaticManager chromaticManager = new ChromaticManager("C:/Users/cavid/Dropbox/Private/Project/Project New");
        ArrayList<String> strg = chromaticManager.listFiles();
        for(int i=0;i<strg.size();i++){
            System.out.println(strg.get(i));
        } 
        Graph g1 = chromaticManager.calculate("graph1.txt");
        Graph g2 = chromaticManager.calculate(10,-1);
        chromaticManager.generate(10, -1, "testtest.txt");
        System.out.println(g1.getUpperBound());
        System.out.println(g1.getLowerBound());
        System.out.println(g1.getCNumber());

        System.out.println(g2.getUpperBound());
        System.out.println(g2.getLowerBound());
        System.out.println(g2.getCNumber());
   
    }
}