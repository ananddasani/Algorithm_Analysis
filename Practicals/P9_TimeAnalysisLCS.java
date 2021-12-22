package Practical_Package;

public class P9_TimeAnalysisLCS {

    public static void main(String[] args) {

        String S1 = "IMPOSSIBLE";
        String S2 = "POSSIBLE";

        int m = S1.length();
        int n = S2.length();

        long startTime = System.currentTimeMillis();
        lcs(S1, S2, m, n);
        long endTime = System.currentTimeMillis();

        System.out.println("\n\nTime Taken " + (endTime - startTime) + " milliseconds");
    }

    static void lcs(String S1, String S2, int m, int n) {
        int[][] table = new int[m + 1][n + 1];

        // Building in bottom-up way
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    table[i][j] = 0;
                else if (S1.charAt(i - 1) == S2.charAt(j - 1))
                    table[i][j] = table[i - 1][j - 1] + 1;
                else
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
            }
        }

        int index = table[m][n];
        int temp = index;

        char[] lcs = new char[index + 1];
        lcs[index] = '\0';

        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                lcs[index - 1] = S1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (table[i - 1][j] > table[i][j - 1])
                i--;
            else
                j--;
        }

        // Printing the subSequences
        System.out.print("S1 : " + S1 + "\nS2 : " + S2 + "\nLCS: ");
        for (int k = 0; k <= temp; k++)
            System.out.print(lcs[k]);
    }
}

/*
OUTPUT

S1 : IMPOSSIBLE
S2 : POSSIBLE
LCS: POSSIBLE

Time Taken 24 milliseconds
 */
