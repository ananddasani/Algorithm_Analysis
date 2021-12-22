package Practical_Package;

import java.util.*;

public class P8_TimeAnalysisCoinChange {
    public static long cost = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        String answer;

        do {
            System.out.print("Enter the number of coins you have available :: ");
            int arraySize = sc.nextInt();

            System.out.println("\nCollecting the Coins...");
            ArrayList<Integer> arr = new ArrayList<>(arraySize);
            for (int i = 0; i < arraySize; i++) {
                int rNum = rd.nextInt(arraySize) + 10;
                if (arr.contains(rNum)) {
                    i--;
                } else
                    arr.add(rNum); //preventing zero
            }

            System.out.print("Coins Collected are :: ");
            System.out.println(arr);

            int toChange = rd.nextInt(arraySize) + 10; //preventing zero
            System.out.println("\nTrying to make a change of " + toChange + "...");

            long startTime = System.nanoTime();
            long ans = countWays(arr, arraySize, toChange);
            long endTime = System.nanoTime();

            System.out.println("\nCost :: " + cost);
            System.out.println("Total Ways :: " + ans);
            System.out.println("Time Taken :: " + (endTime - startTime) + " nanoseconds");

            System.out.print("\nDo you want to continue? [y/n] :: ");
            answer = sc.next().toLowerCase(Locale.ROOT);
        } while (answer.charAt(0) != 'n');
    }

    static long countWays(ArrayList<Integer> coins, int size, int targetAmount) {
        long[] table = new long[targetAmount + 1];

        // initially the table contains 0 values
        Arrays.fill(table, 0);
        cost += size;

        //base case
        table[0] = 1;

        for (int i = 0; i < size; i++)
            for (int j = coins.get(i); j <= targetAmount; j++) {
                table[j] += table[j - coins.get(i)];
                cost += 2;
            }
        return table[targetAmount];
    }
}

/*
OUTPUT

Enter the number of coins you have available :: 20

Collecting the Coins...
Coins Collected are :: [23, 22, 21, 12, 14, 28, 15, 13, 17, 16, 11, 19, 27, 26, 18, 24, 20, 29, 25, 10]

Trying to make a change of 22...

Cost :: 202
Total Ways :: 3
Time Taken :: 67800 nanoseconds

Do you want to continue? [y/n] :: n
 */