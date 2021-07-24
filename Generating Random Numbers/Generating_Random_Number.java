/*
AIM :: To generate a file contains random numbers
Written by :: Anand Dasani
Date :: 18-jun-2021

QUESTION
Create a program which you can use to generate sequence of random numbers of 1,2,3,4,5 and 6 digits.
(example 9,99,999,9999,99999,999999)
The program should generate random number of given digit into a file.
Create multiple files with 100,1000, 100000 and 10000000 entries of numbers.

INSTRUCTION FOR INPUT
See the TEST CASE at the end of file :)

 */
package basic_package;

import java.io.File; // for crating file object
import java.io.PrintWriter; // for writing data to a file
import java.util.Random; // for generating random numbers
import java.util.Scanner; // for scanning input form the user

public class Generating_Random_Number {

    public static void main(String[] args) {

        Random rd = new Random();
        Scanner sc = new Scanner(System.in);

        String answer;

        do {
            //getting the digits for the generating random form the user.
            System.out.print("\n1. 1 digit [1 - 9]\n2. 2 digits [1 - 99]");
            System.out.print("\n3. 3 digits [1 - 999]\n4. 4 digits [1 - 9999]");
            System.out.print("\n5. 5 digits [1 - 99999]\n6. 6 digits [1 - 999999]");
            System.out.print("\nElse give the last limit [ex. 999999]");
            System.out.print("\n\nEnter your choice :: ");
            int digit = sc.nextInt();
            int bound;

            switch (digit) {
                case 1 -> bound = 10;
                case 2 -> bound = 100;
                case 3 -> bound = 1000;
                case 4 -> bound = 10000;
                case 5 -> bound = 100000;
                case 6 -> bound = 1000000;
                default -> bound = digit;
            }

            //getting total number of random number user want to generate.
            System.out.print("\nHow much entries did you want ? \n[ex. 10000000] :: ");
            int totalEntries = sc.nextInt();

            //getting file name to generate all random number into it.
            System.out.print("\nEnter file name in which you want to copy all random numbers. \n[ex. 2_Digit_1000_entries.txt] :: ");
            String fileName = sc.next();

            //creating a file object
            File file = new File(fileName);

            //exit if file already present
            if (file.exists()) {
                System.out.println(fileName + " Named file already exists on your local machine :(");
                System.out.println("exiting...");
                System.exit(1);
            }


            //using try-with-resource
            try (
                    //creating Print Writer object to write data to a file
                    PrintWriter output = new PrintWriter(file);
            ) {
                //writing random number to the file of total entries user want
                for (int i = 0; i < totalEntries; i++) {

                    //the bound is excluded so adding last limit manually
                    output.println(rd.nextInt(bound));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\nFile Created and Random number generated in it!!");

            //prompt user if he wants to continue or not.
            System.out.print("\nDo you want to continue ? [Y/N] ");
            answer = sc.next().toLowerCase();

            if (answer.charAt(0) == 'n')
                break;

        } while (true);

        System.out.println("\nHave a great day :)");
    }
}

/*
TEST CASE

1. 1 digit [1 - 9]
2. 2 digits [1 - 99]
3. 3 digits [1 - 999]
4. 4 digits [1 - 9999]
5. 5 digits [1 - 99999]
6. 6 digits [1 - 999999]
Else give the last limit [ex. 999999]

Enter your choice :: 3

How much entries did you want ?
[ex. 10000000] :: 10000

Enter file name in which you want to copy all random numbers.
[ex. 2_Digit_1000_entries.txt] :: 3_Digit_10000_entries.txt

File Created and Random number generated in it!!

Do you want to continue ? [Y/N] n
Have a great day :)

 */