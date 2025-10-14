import model.Node;
import model.Primer_link;
import model.Segundo_link1;
import model.Tercer_link;

public class main {

    public static void main(String args[])
    {
        System.out.println("Primer link\nSort n numbers in range from 0 to n^2 - 1 in linear time");
        System.out.println("https://www.geeksforgeeks.org/dsa/sort-n-numbers-range-0-n2-1-linear-time/");
        Primer_link pl = new Primer_link();

        // Since array size is 7, elements should be from 0 to 48
        int arr[] = {40, 12, 45, 32, 33, 1, 22};
        int n = arr.length;
        System.out.println("Given array");
        pl.printArr(arr, n);

        pl.sort(arr, n);
        System.out.println("\nSorted array");
        pl.printArr(arr, n);
        System.out.println("\n\n\n");







        System.out.println("Segundo link método N°1\nSort an array according to the order defined by another array");
        System.out.println("https://www.geeksforgeeks.org/dsa/sort-array-according-order-defined-another-array/");
        Segundo_link1 sl1 = new Segundo_link1();
        int[] a1 = {2, 1, 2, 3, 4};
        int[] a2 = {2, 1, 2};

        System.out.print("Primer arreglo a1: ");
        for (int i = 0; i < a1.length; i++) {
            System.out.print(a1[i]+" ");
        }
        System.out.println();
        System.out.print("Segundo arreglo a2: ");
        for (int i = 0; i < a2.length; i++) {
            System.out.print(a2[i]+" ");
        }
        System.out.println();

        sl1.relativeSort(a1, a2);

        for (int num : a1) {
            System.out.print(num + " ");
        }

        System.out.println();







        System.out.println("Segundo link método N°2\nSort an array according to the order defined by another array");
        System.out.println("https://www.geeksforgeeks.org/dsa/sort-array-according-order-defined-another-array/");
        int[] b1 = {2, 1, 2, 3, 4};
        int[] b2 = {2, 1, 2};

        System.out.print("Primer arreglo a1: ");
        for (int i = 0; i < b1.length; i++) {
            System.out.print(b1[i]+" ");
        }
        System.out.println();
        System.out.print("Segundo arreglo a2: ");
        for (int i = 0; i < b2.length; i++) {
            System.out.print(b2[i]+" ");
        }
        System.out.println();

        Segundo_link1 sl2 = new Segundo_link1();
        sl2.relativeSort(b1, b2);


        for (int num : b1) {
            System.out.print(num + " ");
        }
        System.out.println("\n\n");










        System.out.println("Tercer link método\nSort an array according to the order defined by another array");
        System.out.println("https://www.geeksforgeeks.org/dsa/sort-a-linked-list-of-0s-1s-or-2s/");
        // Create a hard-coded linked list:
        // 1 -> 1 -> 2 -> 1 -> 0 -> NULL
        Tercer_link tl = new Tercer_link();
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(0);

        System.out.print("Linked List before Sorting:");
        tl.printList(head);

        tl.sortList(head);

        System.out.print("Linked List after Sorting:");
        tl.printList(head);
    }
}
