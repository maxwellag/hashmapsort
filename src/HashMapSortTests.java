import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Max Garton
 */
public class HashMapSortTests {


    @Test
    public void testSorting(){
        LinkedList<Integer> toSort = new LinkedList<>();
        fillWithRandomData(toSort, 100, 100);
        LinkedList<Integer> result = HashMapSort.hashMapSortList(toSort);
        assertTrue(isInIncreasingOrder(result));
        assertEquals(toSort.size(), result.size());
    }

    @Test
    public void testTwo(){
        runNTestsAndComputeAverageRunningTime(100, 1000, 100, true);
        runNTestsAndComputeAverageRunningTime(100, 1000, 100, false);
    }
    @Test
    public void testThree(){
        runNTestsAndComputeAverageRunningTime(1000, 1000, 1000, false);
    }

    private boolean isInIncreasingOrder(LinkedList<Integer> l){
        Integer last = l.getFirst();
        for(Integer i : l){
            if(last > i)
                return false;
        }
        return true;
    }

    private void fillWithRandomData(LinkedList<Integer> list, int length, int bound){
        Random rand = new Random();
        for(int i=0; i<length; i++){
            list.add(rand.nextInt(bound));
        }
    }

    public void runNTestsAndComputeAverageRunningTime(int n, int lengthOfArrays, int boundForVals, boolean removeDuplicates){
        LinkedList<Integer>[] arr = new LinkedList[n];
        LinkedList<Integer>[] results = new LinkedList[n];
        long[] hashMapSortTimes = new long[n];
        long[] quickSortTimes = new long[n];
        for(int i=0; i<n; i++){
            LinkedList<Integer> toSort = new LinkedList<>();
            fillWithRandomData(toSort, lengthOfArrays, boundForVals);
            arr[i] = toSort;
        }
        long avgHashMapSortTime = 0;
        long avgQuickSortTime = 0;
        for(int j=0; j<n; j++){
            long startTime = System.nanoTime();
            if(removeDuplicates)
                results[j] = HashMapSort.sortAndRemoveDuplicates(arr[j]);
            else results[j] = HashMapSort.hashMapSortList(arr[j]);
            hashMapSortTimes[j] = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            Collections.sort(arr[j]);
            quickSortTimes[j] = System.nanoTime() - startTime;
            avgHashMapSortTime += hashMapSortTimes[j];
            avgQuickSortTime += quickSortTimes[j];
        }
        avgHashMapSortTime = (long)((double)avgHashMapSortTime / (double)n);
        avgQuickSortTime = (long)((double)avgQuickSortTime / (double)n);
        for(int i=0; i<n; i++){
            assertTrue(isInIncreasingOrder(results[i]));
            if(!removeDuplicates)
                assertEquals(arr[i].size(), results[i].size());
        }
        System.out.println("-------------------------------------------------------------------------");
        if(removeDuplicates)
            System.out.println("Testing w/ remove dups with " + n + " trials with length " + lengthOfArrays + " and vals btwn 0 and " + boundForVals);
        else System.out.println("Testing w/o remove dups with " + n + " trials with length " + lengthOfArrays + " and vals btwn 0 and " + boundForVals);
        System.out.println("Avg hashmap sort time: " + avgHashMapSortTime);
        System.out.println("Avg collections.sort time: " + avgQuickSortTime);
        System.out.println("-------------------------------------------------------------------------");
    }
}
