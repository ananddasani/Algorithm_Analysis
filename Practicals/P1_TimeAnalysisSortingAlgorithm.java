package Practical_Package;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class P1_TimeAnalysisSortingAlgorithm {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String answer;
        long cost = 0;

        do {
            System.out.print("\nGive the file Name of Random Numbers\nEg. [1_Digit_100_entries.txt] :: ");
            String fName = sc.next();

            File sourceFile = new File(fName);

            //if file doesn't exists exit
            if (!sourceFile.exists()) {
                System.out.println("\nCan't Locate " + fName);
                System.exit(1);
            }

            System.out.print("\nEnter the File name you want to store the Result\nEx. [1_Digit_100_sorted_entries.txt] :: ");
            fName = sc.next();

            File destFile = new File(fName);

//            //if file exists exit
//            if (destFile.exists()) {
//                System.out.println("\nDuplicate File names :(");
//                System.exit(2);
//            }

            try (
                    Scanner input = new Scanner(sourceFile);
                    PrintWriter output = new PrintWriter(destFile)
            ) {
                //fetch data from source file to arrayList
                while (input.hasNext())
                    arrayList.add(input.nextInt());

//                int[] arr = new int[arrayList.size()];
//                for (int i = 0; i < arrayList.size(); i++)
//                    arr[i] = arrayList.get(i);

                //reversing sorting for worst case analysis
                int[] arr = new int[arrayList.size()];
                for (int i = arrayList.size() - 1, j = 0; i >= 0; i--, j++)
                    arr[j] = arrayList.get(i);

                System.out.println("\n1. Bubble Sort\n2. Insertion Sort\n3. Selection Sort\n4. Merge Sort \n5. Quick Sort\nElse. Exit");
                System.out.print("Enter your Choice for sorting technique :: ");
                int sortChoice = sc.nextInt();

                long startTime = System.currentTimeMillis();

                switch (sortChoice) {
                    case 1 -> cost = bubbleSort(arr);
                    case 2 -> cost = insertionSort(arr);
                    case 3 -> cost = selectionSort(arr);
                    case 4 -> cost = mergeSort(arr, 0, arr.length - 1);
                    case 5 -> cost = quickSort(arr, 0, arr.length - 1);
                    default -> {
                        System.out.println("\nExiting...");
                        System.exit(3);
                    }
                }

                System.out.println("\nSorting Data...");
                System.out.println("Calculating the Cost...");

                for (int j : arr) output.println(j);

                long endTime = System.currentTimeMillis();

                System.out.println("\nCost for sorting :: " + cost);
                System.out.println("Time Taken :: " + (endTime - startTime) + " milliseconds");

            } catch (Exception e) {
                e.printStackTrace();
            }

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to continue ? [y/n] ");
            answer = sc.next().toLowerCase();

        } while (answer.charAt(0) != 'n');
    }

    public static long bubbleSort(int[] arr) {
        long cost = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            cost++;
            for (int j = 0; j < arr.length - i - 1; j++) {
                cost++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    cost++;
                }
            }
        }
        return cost;
    }

    public static long insertionSort(int[] arr) {
        int key, j;
        long cost = 0;

        for (int i = 1; i < arr.length; i++, cost++) {
            key = arr[i];
            cost++; // for assuming key

            j = i - 1;
            cost++; // for assigning j

            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                cost++; // for shifting data in the array

                j--;
                cost++; // for decrementing j

                cost++; //for while loop
            }
            arr[j + 1] = key;
            cost++; // for writing data to the correct position
        }
        return cost;
    }

    public static long selectionSort(int[] arr) {
        int cost = 0;
        int min, loc;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            cost++;
            loc = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    loc = j;
                    cost++;
                }
            }
            if (loc != i) {
                int temp = arr[i];
                arr[i] = arr[loc];
                arr[loc] = temp;
                cost++;
            }
        }
        return cost;
    }

    public static long mergeSort(int[] arr, int low, int high) {

        //find mid everytime
        int mid = (low + (high - 1)) / 2;

        //note the cost
        long costLeft = 0, costRight = 0, costMerge = 0;

        if (low < high) {
            costLeft = mergeSort(arr, low, mid);
            costRight = mergeSort(arr, mid + 1, high);

            costMerge = merge(arr, low, mid, high);
        }
        return costLeft + costRight + costMerge;
    }

    public static long merge(int[] arr, int low, int mid, int high) {

        //note the cost
        long cost = 0;

        //calculate the size of arrays
        int sizeLeft = mid - low + 1;
        int sizeRight = high - mid;

        //create 2 arrays for both size
        int[] left = new int[sizeLeft];
        int[] right = new int[sizeRight];

        //copying data
        for (int index = 0; index < sizeLeft; index++) {
            left[index] = arr[low + index];
            cost++;
        }
        for (int index = 0; index < sizeRight; index++) {
            right[index] = arr[mid + 1 + index];
            cost++;
        }

        int i = 0, j = 0, k = low;
        while (i < sizeLeft && j < sizeRight) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            cost++;
            k++;
            cost++;
        }

        //copy remaining element of left
        while (i < sizeLeft) {
            arr[k] = left[i];
            k++;
            i++;
            cost++;
        }

        //copy remaining element of right
        while (j < sizeRight) {
            arr[k] = right[j];
            k++;
            j++;
            cost++;
        }
        return cost;
    }

    public static long quickSort(int[] arr, int low, int high) {

        long costLeft = 0, costRight = 0, partitionCost = 0;

        int mid;
        if (low < high) {
            long[] costMap = partition(arr, low, high);
            mid = (int) costMap[0];
            partitionCost = costMap[1];

            costLeft = quickSort(arr, low, mid - 1);
            costRight = quickSort(arr, mid + 1, high);
        }
        return costLeft + costRight + partitionCost;
    }

    public static long[] partition(int[] arr, int start, int end) {

        long cost = 0;
        long[] costMap = new long[2];

        int pivot = arr[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            cost++;
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++; // increment index of smaller element
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                cost += 3;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;
        cost += 3;

        costMap[0] = (i + 1);
        costMap[1] = cost;

        return costMap;
    }
}
