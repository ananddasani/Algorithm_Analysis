package Practical_Package;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class P2_TimeAnalysisLinearSortingAlgorithm {

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

            //if file exists exit
            if (destFile.exists()) {
                System.out.println("\nDuplicate File names :(");
                System.exit(2);
            }

            try (
                    Scanner input = new Scanner(sourceFile);
                    PrintWriter output = new PrintWriter(destFile)
            ) {
                //fetch data from source file to arrayList
                while (input.hasNext())
                    arrayList.add(input.nextInt());

                int[] arr = new int[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++)
                    arr[i] = arrayList.get(i);

                System.out.println("\n1. Bucket Sort\n2. Counting Sort\n3. Radix Sort\nElse. Exit");
                System.out.print("Enter your Choice for sorting technique :: ");
                int sortChoice = sc.nextInt();

                long startTime = System.currentTimeMillis();

                switch (sortChoice) {
                    case 1 -> {
                        arr = bucketSort(arr);
                        cost = arr[arr.length - 1];
                    }
                    case 2 -> cost = countingSort(arr);
                    case 3 -> cost = radixSort(arr);
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

    public static int[] bucketSort(int[] arr) {

        long cost = 0;
        int max_value = maxInArray(arr);

        int[] bucket = new int[max_value + 1];
        int[] sorted_arr = new int[arr.length + 1];

        for (int i = 0; i < arr.length; i++, cost++)
            bucket[arr[i]]++;

        int pos = 0;
        for (int i = 0; i < bucket.length; i++, cost++)
            for (int j = 0; j < bucket[i]; j++, cost++)
                sorted_arr[pos++] = i;

        //add cost to the end
        sorted_arr[arr.length] = (int)cost;
        return sorted_arr;
    }

    public static long countingSort(int[] arr) {
        long cost = 0;

        int maxElement = maxInArray(arr);

        int[] count = new int[maxElement + 1];
        int[] brr = new int[arr.length];

        for (int i = 0; i < arr.length; i++, cost++)
            ++count[arr[i]]; // increment the index(element) from 0 to 1 and if the number repeat then 1 to 2 and so on...

        for (int i = 1; i <= maxElement; i++, cost++)
            count[i] += count[i - 1]; // arranging the count array in order

        for (int i = arr.length - 1; i >= 0; i--, cost++)
            brr[--count[arr[i]]] = arr[i]; // inserting the element from main array(arr) into brr by decrementing by one first

        for (int i = 0; i < arr.length; i++, cost++)
            arr[i] = brr[i]; // copying array brr to array arr

        return cost;
    }

    public static long radixSort(int[] arr) {
        long cost = 0;
        int max = maxInArray(arr); // finding the max element from the main array

        // pos variable shows the position of the digit in the number
        // (pos = 1 means unit , pos = 10 means tens , pos = 100 = hundred)
        for (int pos = 1; max / pos > 0; pos *= 10, cost++)
            cost += countSortRadix(arr, pos); // calling count function for pos = 1 , pos = 10 , pos = 100

        return cost;
    }

    public static long countSortRadix(int[] arr, int pos) {
        long cost = 0;
        int[] count = new int[10];
        int[] brr = new int[arr.length];

        for (int i = 0; i < arr.length; i++, cost++)
            ++count[(arr[i] / pos) % 10];

        for (int i = 1; i < 10; i++, cost++)
            count[i] += count[i - 1];

        for (int i = arr.length - 1; i >= 0; i--, cost++)
            brr[--count[(arr[i] / pos) % 10]] = arr[i];

        for (int i = 0; i < arr.length; i++, cost++) // copying all element from brr to original array
            arr[i] = brr[i];

        return cost;
    }

    public static int maxInArray(int[] arr) {
        int max_value = 0;
        for (int j : arr)
            if (j > max_value)
                max_value = j;
        return max_value;
    }
}
