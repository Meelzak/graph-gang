package GraphColoring;
/*
* Short summary:
* This is the class that uses the Bron-Kerbosch algorithm to determine what the biggest clique is.
* The size of our biggest clique is the lower bound, and the biggest clique is one of our hints.	
*/

import java.util.ArrayList;

public class Bk{
	//create two ArrayLists, one for the maximumClique, and one that you will return as a hint
    	private static ArrayList<Dot> maximumClique = new ArrayList<Dot>();
	private static ArrayList<Dot> returningClique = new ArrayList<Dot>();
    
	//this will let the doTheBk method be executed, and return the result of this calculation
    public int doBK(Graph g){
        ArrayList<Dot> list = (ArrayList)g.getList().clone();
        int r = doTheBK(new ArrayList<Dot>(), list, new ArrayList<Dot>());
        gReset(list);
        return r;
    }

    /* This method will perform the BK, so it will return the maximum number of connected Dots
     * How it works: 
     * There are three ArrayLists, which will be used again and again, until two of them are totally empty.
     * It checks whether vertices are connected, and what the maximum number of fully connected vertices (so they all connect to each other) is.
     */
    public int doTheBK(ArrayList<Dot> r,ArrayList<Dot> p,ArrayList<Dot> x){
        int max =0;
        if(p.isEmpty() && x.isEmpty()){//if these two are empty, then check if r is bigger than the maximum
            max =Math.max(max, r.size());
            if(maximumClique.size() < r.size()) {//clone the r, so the maximumClique will be the biggest clique
            	maximumClique = (ArrayList<Dot>) r.clone();
            }
        }
        for(int i=0;i<p.size();i++){
            Dot dot = p.get(i);

            ArrayList<Dot> a = (ArrayList <Dot> )r.clone();
            a.add(dot);
            ArrayList<Dot> b = (ArrayList <Dot> )p.clone();
            b.retainAll(dot.giveList());
            ArrayList<Dot> c = (ArrayList <Dot> )x.clone();
            c.retainAll(dot.giveList());
            max = Math.max(doTheBK(a, b, c),max);//will use the doTheBK method again

            p.remove(dot);
            x.add(dot);
        }
        return max; //eventually return the maximum number of fully connected vertices
    }
    //set the content of every entry (a Dot) of the ArrayList to zero
    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }
    //will return the list of all the Dots that are a part of the maximumClique
    public static ArrayList<Dot> getMaximumClique() {
    	returningClique = (ArrayList<Dot>) maximumClique.clone();
    	maximumClique.clear();
    	return returningClique;
    }
}   
