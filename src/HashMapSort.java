import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Max Garton
 */
public class HashMapSort {

    public static LinkedList<Integer> hashMapSortList(LinkedList<Integer> toSort){
        LinkedList<Integer> sorted = new LinkedList<>();
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        Integer max = toSort.getFirst();
        Integer min = max;
        for(Integer i: toSort){ // Find min and max, while also inserting each
            LinkedList<Integer> l = map.get(i);
            if(l == null){
                l = new LinkedList<Integer>();
                map.put(i, l);
            }
            l.add(i);
            if(i > max)
                max = i;
            if(i < min)
                min = i;
        }
        for(Integer i = min; i<max+1; i++){
            LinkedList<Integer> toAdd = map.get(i);
            if(toAdd == null || toAdd.size()==0)
                continue;
            Integer addThis = toAdd.getFirst();
            while(addThis != null){
                sorted.add(addThis);
                toAdd.removeFirst();
                if(toAdd.size() > 0)
                    addThis = toAdd.getFirst();
                else break;
            }
        }
        return sorted;
    }

    public static LinkedList<Integer> sortAndRemoveDuplicates(LinkedList<Integer> toSort){
        LinkedList<Integer> sorted = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer max = toSort.getFirst();
        Integer min = max;
        for(Integer i: toSort){ // Find min and max. O(n).
            map.put(i, i);
            if(i > max)
                max = i;
            if(i < min)
                min = i;
        }
        for(Integer i = min; i<max+1; i++){
            sorted.add(i);
            map.remove(i, i);
        }
        return sorted;
    }
}
