package Practical_Package;

import java.util.Random;
import java.util.Scanner;

class Cost {
    public int bound = 20;
    public int times = 1;

    public int[] generateArray() {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        System.out.print("How many Times you want to do analysis :: ");
        this.times = sc.nextInt();

        //preparing array to keep random number same for recursive and iterative
        int[] arr = new int[times];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rd.nextInt(bound) + 1; //preventing 0 to be calculated for factorial

        return arr;
    }
}

class RecursiveCost extends Cost {
    public long rCost = 0;

    public long factRecursive(int num) {
        this.rCost++;
        return (num == 1) ? 1 : num * factRecursive(num - 1);
    }
}

class IterativeCost extends Cost {
    public long iCost = 0;

    public long factIterative(int num) {
        long fact = 1;
        for (int i = 2; i <= num; i++, this.iCost++)
            fact = fact * i;

        return fact;
    }
}

public class P5_TimeAnalysisRecursiveAndIterative {

    public static void main(String[] args) {
        RecursiveCost recursiveCost = new RecursiveCost();
        IterativeCost iterativeCost = new IterativeCost();

        int[] arr = recursiveCost.generateArray();
        String answer;

        do {
            System.out.println("\n1. Iterative Way\n2.Recursive Way\nElse. Exit");
            System.out.print("Enter your choice :: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    //loop for iterative
                    for (int i = 0; i < arr.length; i++) {
                        iterativeCost.iCost = 0; //reset cost

                        long startTime = System.nanoTime();
                        long factAns = iterativeCost.factIterative(arr[i]);
                        long endTime = System.nanoTime();

                        System.out.println("\n" + arr[i] + "! = " + factAns);
                        System.out.println("Iterative Cost for " + arr[i] + "! :: " + iterativeCost.iCost);
                        System.out.println("Time Taken (nanoTime):: " + (endTime - startTime));
                    }
                    break;

                case 2:
                    //loop for recursive
                    for (int i = 0; i < recursiveCost.times; i++) {
                        recursiveCost.rCost = 0; // reset cost

                        long startTime = System.nanoTime();
                        long factAns = recursiveCost.factRecursive(arr[i]);
                        long endTime = System.nanoTime();

                        System.out.println("\n" + arr[i] + "! = " + factAns);
                        System.out.println("Recursive Cost " + recursiveCost.rCost);
                        System.out.println("Time Taken (nanoTime):: " + (endTime - startTime));
                    }
                    break;

                default:
                    System.out.println("Exiting...");
                    System.exit(1);
            }

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to do analysis for either way ? [y/n] ");
            answer = sc.next().toLowerCase();

        } while (answer.charAt(0) != 'n');
    }
}
