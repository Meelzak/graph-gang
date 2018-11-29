package GraphColoring;
import java.util.ArrayList;
import java.util.ArrayList;

public class Bk{
    public int doBK(Graph g){
        ArrayList<Dot> list = (ArrayList)g.getList().clone();
        int r = doTheBK(new ArrayList<Dot>(), list, new ArrayList<Dot>());
        gReset(list);
        return r;
    }

    public int doTheBK(ArrayList<Dot> r,ArrayList<Dot> p,ArrayList<Dot> x){
        int max =0;
        if(p.isEmpty() && x.isEmpty()){
            max =Math.max(max, r.size());
        }
        for(int i=0;i<p.size();i++){
            Dot dot = p.get(i);

            ArrayList<Dot> a = (ArrayList <Dot> )r.clone();
            a.add(dot);
            ArrayList<Dot> b = (ArrayList <Dot> )p.clone();
            b.retainAll(dot.giveList());
            ArrayList<Dot> c = (ArrayList <Dot> )x.clone();
            c.retainAll(dot.giveList());
            max = Math.max(doTheBK(a, b, c),max);

            p.remove(dot);
            x.add(dot);
        }
        return max;
    }
    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }
}   