package GraphColoring;
import java.util.ArrayList;
import java.util.Collections;
public class Powell{
    
    public int doPowell(Graph g){
        ArrayList<Dot> list = (ArrayList)g.getList().clone();

        qSort(list);
        int answer=0;
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(cNumber(list.get(i).giveList()));
            answer = Math.max(list.get(i).giveContent(), answer);
        }
        gReset(list);
        return answer;
    }
    public int cNumber(ArrayList<Dot> dList){
        //Needs the list of connected dots
        ArrayList<Integer> list =new  ArrayList<Integer>();
        for(int i=0;i<dList.size();i++){
                list.add(dList.get(i).giveContent());
        }
        int i=1;
        while(true){
            if(list.contains(i)){
                i++;
            }else{
                break;
            }
        }
        return i;
    }
    public static void qSort(ArrayList<Dot> array){
        int left=0;
        int right=array.size()-1;
        sort(array,left,right);
    }
    public static ArrayList<Dot> sort(ArrayList<Dot> array,int pLeft,int pRight){
        int left=pLeft;
        int right=pRight;
        int pivot= array.get((left+right)/2).giveList().size();
        do{
            while(array.get(left).giveList().size()>pivot){
                left++;
            }
            while(array.get(right).giveList().size()<pivot){
                right=right-1;
            }
            if(left<=right){
                Collections.swap(array,left,right);
                left++;
                right=right-1;
            }
        }while(left<=right);
            if(pLeft<right){
                array = sort(array,pLeft,right);
            }
            if(pRight>left){
                array = sort(array,left,pRight);
            }
            return array;
    }
    public void gReset(ArrayList<Dot> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setContent(0);
        }
    }
}