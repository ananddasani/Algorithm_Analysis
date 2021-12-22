package Practical_Package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class P3_TimeAnalysisLinearAndBinarySearch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        String answer;
        int cost = 0;

        do {
            System.out.print("Enter the size of array you want to generate Randomly :: ");
            int arraySize = sc.nextInt();
            System.out.print("Give upper bound for " + arraySize + " entries :: ");
            int bound = sc.nextInt();

            System.out.println("\nGenerating " + arraySize + " Random entries with upper bound " + bound + "...");
            ArrayList<Integer> arr = new ArrayList<>(arraySize);
            for (int i = 0; i < arraySize; i++) {
                arr.add(rd.nextInt(bound));
            }

            int key = rd.nextInt(bound) + 1; //preventing zero to be generated
            System.out.println("Randomly generated Key :: " + key);

            int ans = 0;
            int[] map;

            long startTime = System.currentTimeMillis();
            map = linearSearch(arr, key);
            ans = map[0];
            cost = map[1];
            long endTime = System.currentTimeMillis();

            if (ans >= 0) {
                System.out.println("\nKey Found in Array !!");
                System.out.println("Linear Search Cost :: " + cost);
                System.out.println("Time Taken :: " + (endTime - startTime));
            } else
                System.out.println("\nKey not found in array :(");

            startTime = System.currentTimeMillis();
            map = binarySearch(arr, key);
            ans = map[0];
            cost = map[1];
            endTime = System.currentTimeMillis();

            //print here cost and time
            if (ans >= 0) {
                System.out.println("\n\nKey Found in Array !!");
                System.out.println("Binary Search Cost :: " + cost);
                System.out.println("Time Taken :: " + (endTime - startTime));
            } else
                System.out.println("\nKey not found in array :(");

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to continue ? [y/n] ");
            answer = sc.next().toLowerCase();

        } while (answer.charAt(0) != 'n');
    }

    public static int[] linearSearch(ArrayList<Integer> arr, int key) {

        int i;
        int[] map = new int[2];
        int cost = 0;
        boolean found = false;

        for (i = 0; i < arr.size(); i++, cost++)
            if (arr.get(i) == key) {
                found = true;
                break;
            }

        if (found)
            map[0] = i;
        else
            map[0] = -1;
        map[1] = cost;

        return map;
    }

    public static int[] binarySearch(ArrayList<Integer> arr, int key) {

        //first sort the array to run binary search algo
        Collections.sort(arr);

        int low = 0, finalMid = 0, cost = 0;
        boolean found = false;
        int high = arr.size() - 1;
        int[] map = new int[2];

        while (high >= low) {

            int mid = low + (high - low) / 2;
            cost++;

            if (arr.get(mid) == key) {
                cost++;
                finalMid = mid;
                found = true;
                break;
            } else if (arr.get(mid) > key)
                high = mid - 1;
            else
                low = mid + 1;
            cost++;
        }

        if (found)
            map[0] = finalMid;
        else
            map[0] = -1;
        map[1] = cost;
        return map;
    }
}

/*
OUTPUT

Enter the size of array you want to generate Randomly :: 10
Give upper bound for 10 entries :: 12

Generating 10 Random entries with upper bound 12...
Randomly generated Key :: 11

Key Found in Array !!
Linear Search Cost :: 0
Time Taken :: 0


Key Found in Array !!
Binary Search Cost :: 8
Time Taken :: 0
 */
