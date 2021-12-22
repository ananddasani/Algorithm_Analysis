package Practical_Package;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class P4_TimeAnalysisMaxHeapSort {

    public static void main(String[] args) {

        Random rd = new Random();
        Scanner sc = new Scanner(System.in);
        String answer;
        long cost;

        do {
            System.out.print("Enter the size of array you want to generate Randomly :: ");
            int arraySize = sc.nextInt();
            System.out.print("Give upper bound for " + arraySize + " entries :: ");
            int bound = sc.nextInt();

            System.out.println("\nGenerating " + arraySize + " Random entries with upper bound " + bound + "...");
            ArrayList<Integer> arrayList = new ArrayList<>(arraySize);
            for (int i = 0; i < arraySize; i++)
                arrayList.add(rd.nextInt(bound));

            int[] arr = new int[arrayList.size()];
            for (int i : arr)
                arr[i] = arrayList.get(i);

            System.out.println("\nSorting Data...");
            System.out.println("Calculating the Cost...");

            long startTime = System.nanoTime();
            cost = maxHeapSort(arr);
            long endTime = System.nanoTime();

            System.out.println("\nCost for sorting :: " + cost);
            System.out.println("Time Taken :: " + (endTime - startTime) + " nanoseconds");

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to continue ? [y/n] ");
            answer = sc.next().toLowerCase();

        } while (answer.charAt(0) != 'n');
    }

    public static long maxHeapSort(int[] arr) {
        long cost = 0;
        int size = arr.length;

        //building heap
        for (int i = size / 2 - 1; i >= 0; i--, cost++)
            heapify(arr, size, i, cost);

        for (int i = size - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0, cost);
            cost++;
        }
        return cost;
    }

    public static long heapify(int[] arr, int n, int i, long cost) {
        int largest = i;  // Initialize largest as root
        int left = 2 * i + 1;  // left = 2*i + 1
        int right = 2 * i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest, cost);
        }
        return cost;
    }
}

/*
OUTPUT

Enter the size of array you want to generate Randomly :: 2500
Give upper bound for 2500 entries :: 2510

Generating 2500 Random entries with upper bound 2510...

Sorting Data...
Calculating the Cost...

Cost for sorting :: 3750
Time Taken :: 208800 nanoseconds

Do you want to continue ? [y/n] N
 */