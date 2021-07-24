/*
Instructions
Implement Insertion Sort Algorithm in your programming language.
The program should read file to initialize the array.
Use the variable "cost" and do cost++ every time a line in algorithm is executed.


for(int i = 1;i < count;i++, cost++) {
    key = number[i];
    cost++;

    j=i-1;
    cost++;

    while((key < number[j]) && (j >= 0)) {
        number[j+1] = number[j];
        cost++;

        j=j-1;
        cost++;

        cost++;
    }
    number[j+1]=key;
    cost++;
}


Task1:
Do not consider cost of reading from and writing to files.
Print the cost value after you sort random number files of 10,100,1000,1000....so on..... try the limit of your machine.
Sort as many numbers you can.
Writing is a time consuming operation.
Even after sorting, writing many numbers will take time. (Some milli seconds - few seconds).

Task 2:
Do the same Task 1 but this time do not consider cost variable,
but use start time and end time values to see how much time is spend for different size of array and different size of number.

Task 3:
Making graphs. Plot the values on graph using excel sheet.

Hint: while sorting large array of numbers, you can check your processors performance from task manager. Take a screen shot if anything unusual.

INSTRUCTIONS FOR INPUT
See the TEST CASE at the end of file before running the program
 */
package ADA_Practice_Package;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Sorting_Random_Numbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String answer;

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        do {
            //take the source file name form the user
            System.out.print("\nGive the unsorted number list file \n[ex. 1_Digit_100_entries.txt] :: ");
            String fName = sc.next();

            //create file instance for source file
            File sourceFile = new File(fName);

            // validate the file
            if (!sourceFile.exists()) {
                System.out.println(fName + " File doesn't exists :(");
                System.exit(1);
            }

            //take the destination file name form the user
            System.out.print("\nEnter file name you want to store sorted data\n[ex. 1_Digit_100_sorted_entries.txt] :: ");
            String flName = sc.next();

            //create file instance for destination file
            File destFile = new File(flName);

            //validate the file
            if (destFile.exists()) {
                System.out.println(destFile + " file already exists :(");
                System.exit(2);
            }

            //using try-with-resources
            try (
                    //create a scanner instance for reading data form the file
                    Scanner input = new Scanner(sourceFile);

                    //create Print Writer instance for writing sorted data to new file
                    PrintWriter output = new PrintWriter(destFile);
            ) {

                System.out.println("\nReading data form the file...");

                while (input.hasNext()) {
                    //Add data to the array list one by one
                    arrayList.add(input.nextInt());
                }

                //convert to array
                int[] arr = new int[arrayList.size()];

                for (int i = 0; i < arrayList.size(); i++)
                    arr[i] = arrayList.get(i);

                System.out.println("Sorting Data...");
                System.out.println("Calculating the Cost...");

                //note the starting time
                long startTime = System.currentTimeMillis();

                //sort the data using insertion sort algo and get the cost
                long cost = insertionSort(arr);

                //redirect the sorted data to the file
                for (int i = 0; i < arr.length; i++)
                    output.println(arr[i]);

                //note the end time
                long endTime = System.currentTimeMillis();

                System.out.println("\nSorted !!");

                //display the cost of sorting
                System.out.println("Cost for sorting :: " + cost);

                //calculate the time
                long millisecond = (endTime - startTime);
                System.out.println("\nTIME TAKEN :: " + millisecond + " Millisecond");

//            if (millisecond >= 1000) {
//                //convert it to seconds
//                long second = millisecond / 1000;
//                System.out.println("\nTIME TAKEN :: " + second + " Second");
//            } else {
//                //display the time in milli second
//                System.out.println("\nTIME TAKEN :: " + millisecond + " Millisecond");
//            }

            } catch (Exception e) {
                e.printStackTrace();
            }

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to continue ? [Y/N] ");
            answer = sc.next().toLowerCase();

            if (answer.charAt(0) == 'n')
                break;

        } while (true);

        System.out.println("\nHave a good day :)");
    }

    /**
     * The insertion sort method will sort the array passed as argument
     *
     * @param arr is the array of integer which is to be sorted
     * @return the  cost of sorting the array using insertion sort algo.
     */
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
}

/*
TEST CASE
Give the unsorted number list file
[ex. 1_Digit_100_entries.txt] :: 1_Digit_100000_entries.txt

Enter file name you want to store sorted data
[ex. 1_Digit_100_sorted_entries.txt] :: 1_Digit_100000_sorted_entries.txt

Reading data form the file...
Sorting Data...
Calculating the Cost...

Sorted !!
Cost for sorting :: 6750876012

TIME TAKEN :: 844 Millisecond

Do you want to continue ? [Y/N] n

Have a good day :)

 */
