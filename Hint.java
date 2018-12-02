package GraphColoring;
public class Hint {

    public int giveUpperbound(){
            Powell powell = new Powell();
            Test testGraph = new Test();
            return powell.doPowell(testGraph.graph);
    }
}
