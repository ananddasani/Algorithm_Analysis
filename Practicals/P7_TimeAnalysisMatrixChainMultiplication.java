package Practical_Package;

import java.util.Random;
import java.util.Scanner;

public class P7_TimeAnalysisMatrixChainMultiplication {
    public static long cost = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        String answer;

        do {
            System.out.print("Enter the number of matrix you want to multiply :: ");
            int numOfMatrix = sc.nextInt();
            numOfMatrix += 1; //1 extra dimensions

            System.out.println("\nGenerating dimensions for " + (numOfMatrix - 1) + " Matrices...");
            int[] arr = new int[numOfMatrix];
            int size = arr.length;

            for (int i = 0; i < size; i++)
                arr[i] = ((rd.nextInt(5) + 1) * 10); // 10 - 50

            System.out.println("\nThis are the Matrices Dimension");
            for (int ele : arr)
                System.out.print(ele + " ");

            long startTime = System.nanoTime();
            int ans = MatrixChainOrder(arr, size);
            long endTime = System.nanoTime();

            System.out.println("\nCost :: " + cost);
            System.out.println("Minimum number of multiplications :: " + ans);
            System.out.println("Time Taken :: " + (endTime - startTime) + " nanoseconds");

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to continue ? [y/n] ");
            answer = sc.next().toLowerCase();

        } while (answer.charAt(0) != 'n');
    }

    static int MatrixChainOrder(int[] p, int n) {
        int[][] table = new int[n][n];

        int i, j, k, L, q;

        for (i = 1; i < n; i++) {
            table[i][i] = 0;
            cost++;
        }

        // L is chain length.
        for (L = 2; L < n; L++) {
            cost++;
            for (i = 1; i < n - L + 1; i++) {
                cost++;
                j = i + L - 1;
                if (j == n)
                    continue;
                table[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++) {
                    cost++;
                    q = table[i][k] + table[k + 1][j] + p[i - 1] * p[k] * p[j];
                    cost++;
                    if (q < table[i][j]) {
                        table[i][j] = q;
                        cost++;
                    }
                }
            }
        }
        return table[1][n - 1];
    }
}

/*
OUTPUT

Enter the number of matrix you want to multiply :: 11

Generating dimensions for 12 Matrices...

This are the Matrices Dimension
40 20 50 30 30 20 50 40 10 10 30 30
Cost :: 2152
Minimum number of multiplications :: 101000
Time Taken :: 37300 nanoseconds

Do you want to continue ? [y/n] y
 */