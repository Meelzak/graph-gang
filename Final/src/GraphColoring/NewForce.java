package GraphColoring;

import java.util.ArrayList;

public class NewForce{
    public int chromaticNumber;

    public NewForce() {
    }

    public int doNewForce(Graph graph, int upperBound, int lowerBound){
        if(lowerBound==upperBound){
            return lowerBound;
        }
        upperBound--;
        while(dF(graph.getList(), upperBound, 0)){
            upperBound--;
            gReset(graph.getList());
        }
        doIt(graph,0,upperBound+1);

        return upperBound+1;
        }

    public boolean dF(ArrayList<Dot> list,int color,int dotPosition){
        boolean allColored=true;
        for(int i=0;i<list.size();i++){ //Add a size and getSize to graph too.
            if(list.get(i).giveContent()==0){
                allColored=false;
                break;
            }
        }
        if(allColored){
            return true;
        }
        for(int i=1;i<=color;i++){
            if(cNumberTrue(list.get(dotPosition).giveList(), i)){
                list.get(dotPosition).setContent(i);
                int next=dotPosition + 1;
                if(next==list.size()||dF(list, color, next)){
                    return true;
                }
                list.get(dotPosition).setContent(0);
            }
        }
        return false;
    }

    public boolean cNumberTrue(ArrayList<Dot> list,int color){
        for(int i=0;i<list.size();i++){
            if(list.get(i).giveContent()==color){
                return false;
            }
        }
        return true;
    }

    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }


    public void doIt(Graph graph,int startDot,int smallesChrom){
        this.chromaticNumber = smallesChrom;
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }
}