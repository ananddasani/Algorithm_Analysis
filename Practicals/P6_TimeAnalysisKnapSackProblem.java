package Practical_Package;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class P6_TimeAnalysisKnapSackProblem {

    public static long cost = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        String answer;

        do {
            System.out.print("How many objects, with you want to do analyze? ::  ");
            int times = sc.nextInt();

            //generate weight array, price array & maxWeight of knapSack
            int[] weightArray = generateArray(times);
            System.out.println("\nWeight array generated.");
            int[] priceArray = generateArray(times);
            System.out.println("Price array generated.");
            int maxWeight = rd.nextInt(1000);
            System.out.println("Max Weight Fixed.");

            long startTime = System.currentTimeMillis();
            int ans = knapSack(priceArray, weightArray, maxWeight, weightArray.length);
            long endTime = System.currentTimeMillis();

            System.out.println("\nans :: " + ans);
            System.out.println("Cost :: " + cost);
            System.out.println("TIME TAKEN :: " + (endTime - startTime) + " Millisecond");

            System.out.print("\nDo you want to continue ? [y/n] :: ");
            answer = sc.next().toLowerCase(Locale.ROOT);
        } while (answer.charAt(0) != 'n');
    }

    public static int knapSack(int[] p, int[] wt, int W, int n) {
        cost++;

        // table to store the calculated subProblem's result
        int[][] t = new int[n + 1][W + 1];

        //initialize the table with -1
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++)
                t[i][j] = -1;
            cost += 2;
        }

        //base condition
        if (W == 0 || n == 0) {
            cost++;
            return 0;
        }

        //check if this particular subProblem is already solved ?
        if (t[n][W] != -1) {
            cost++;
            return t[n][W];
        }

        //Able to choose
        if (wt[n - 1] <= W) //                                  choosing                                not choosing
            return t[n][W] = Math.max(p[n - 1] + knapSack(p, wt, W - wt[n - 1], n - 1), knapSack(p, wt, W, n - 1));
        else  // not able to choose as W < wt[n - 1]
            return t[n][W] = knapSack(p, wt, W, n - 1);
    }

    public static int[] generateArray(int times) {
        Random rd = new Random();

        //preparing array to keep random number same for recursive and iterative
        int[] arr = new int[times];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rd.nextInt(1000) + 1; //preventing 0 to be calculated for factorial

        return arr;
    }
}

/*
OUTPUT
How many objects, with you want to do analyze? ::  100

Weight array generated.
Price array generated.
Max Weight Fixed.

ans :: 2717
Cost :: 139050
TIME TAKEN :: 32 Millisecond
 */
