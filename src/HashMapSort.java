import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Max Garton
 */
public class HashMapSort {

    public static LinkedList<Integer> hashMapSortList(LinkedList<Integer> toSort){
        LinkedList<Integer> sorted = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer max = toSort.getFirst();
        Integer min = max;
        for(Integer i: toSort){ // Find min and max, while also inserting each
            if(map.get(i) == null){
                map.put(i, 1); // add one integer with value i to the map
            }
            else{
                map.replace(i, map.get(i)+1); // increment the quanitity of integer i seen
            }
            if(i > max)
                max = i;
            if(i < min)
                min = i;
        }
        for(Integer i = min; i<max+1; i++){
            int quanitityToAdd = -1;
            if(map.get(i) != null){
                quanitityToAdd = map.get(i);
            }
            if(quanitityToAdd == 0) // Only add the ones that
                continue;
            else{
                while(quanitityToAdd-- > 0){
                    sorted.add(i);
                }
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
