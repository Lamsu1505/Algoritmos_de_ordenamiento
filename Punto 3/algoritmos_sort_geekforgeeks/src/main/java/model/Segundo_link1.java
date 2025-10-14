package model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/*
Sort an array according to the order defined by another array
 */

//https://www.geeksforgeeks.org/dsa/sort-array-according-order-defined-another-array/

public class Segundo_link1 {

    public void relativeSort(int[] a1, int[] a2) {

        int m = a1.length, n = a2.length;
        Map<Integer, Integer> freq = new HashMap<>();

        // Count frequency of each element in a1
        for (int i = 0; i < m; i++) {
            freq.put(a1[i], freq.getOrDefault(a1[i], 0) + 1);
        }

        int index = 0;

        // Place elements of a2 in a1 based on frequency
        for (int i = 0; i < n; i++) {
            while (freq.getOrDefault(a2[i], 0) > 0) {
                a1[index++] = a2[i];
                freq.put(a2[i], freq.get(a2[i]) - 1);
            }
            freq.remove(a2[i]);
        }

        // Collect remaining elements and sort them
        List<Integer> remaining = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                remaining.add(entry.getKey());
            }
        }
        Collections.sort(remaining);

        // Append remaining elements to a1
        for (int num : remaining) {
            a1[index++] = num;
        }
    }

}
