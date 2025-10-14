package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/*
Sort an array according to the order defined by another array
 */

//https://www.geeksforgeeks.org/dsa/sort-array-according-order-defined-another-array/

public class Segundo_link2 {

    public void relativeSort(int[] a1, int[] a2) {

        // Create a map to store the index of each
        // element in a2
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < a2.length; i++) {
            if (!mp.containsKey(a2[i]))
                mp.put(a2[i], i);
        }

        // Convert int[] to Integer[] for custom sorting
        Integer[] temp = Arrays.stream(a1).boxed().toArray(Integer[]::new);

        // Custom comparator using lambda
        Arrays.sort(temp, (a, b) -> {
            boolean aIn = mp.containsKey(a), bIn = mp.containsKey(b);
            if (!aIn && !bIn) return Integer.compare(a, b);
            if (!aIn) return 1;
            if (!bIn) return -1;
            return Integer.compare(mp.get(a), mp.get(b));
        });

        // Copy sorted values back to original array
        for (int i = 0; i < a1.length; i++) {
            a1[i] = temp[i];
        }
    }
}
