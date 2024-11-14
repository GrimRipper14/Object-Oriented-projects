//Worked on by Isabel Paynter and Zachary Boggs
import java.util.Scanner;

public class DriverClass {
    public static void main(String args[]) {
        int numRows, numColumns;
        int index, start, end;
        char charAtIndex;
        int length;
        String subSequence;
        Scanner myScan = new Scanner(System.in);

        System.out.print("Enter how many rows and how many columns to load: ");
        numRows = myScan.nextInt();
        numColumns = myScan.nextInt();

        Code codeObject = new Code(numRows, numColumns);
        codeObject.loadCodeArray(numRows, numColumns);
        codeObject.printCodeArray(numRows, numColumns);

        System.out.print("\n\nTesting charAt: Enter your index [a number greater or equal to 0 and less or equal to ");
        System.out.print((numRows * numColumns - 1) + "]:");
        index = myScan.nextInt();
        charAtIndex = codeObject.charAt(index);
        System.out.println("The character located at index " + index + " is: " + charAtIndex);

        length = codeObject.length();
        System.out.println("\n\nTesting length: there are " + length + " characters.");

        System.out.print("\n\nTesting subSequence: Enter start and end indexes: ");
        start = myScan.nextInt();
        end = myScan.nextInt();
        subSequence = codeObject.subSequence(start, end);
        System.out.println("The subsequence is: " + subSequence);

        System.out.println("\nGoodbye!");
    }
}

class Code implements CharSequence {
    private int[][] codeArray;
    private int numRows, numColumns;

    public Code(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        codeArray = new int[numRows][numColumns];
    }

    public void loadCodeArray(int numRows, int numColumns) {
        Scanner myScan = new Scanner(System.in);
        for (int i = 0; i < numRows; i++) {
            System.out.print("Enter Row " + (i + 1) + ": ");
            for (int j = 0; j < numColumns; j++) {
                codeArray[i][j] = myScan.nextInt();
            }
        }
    }

    public void printCodeArray(int numRows, int numColumns) {
        System.out.println("\n_____________\n");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(codeArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public char charAt(int index) {
        int row = index / numColumns;
        int col = index % numColumns;
        return (char) codeArray[row][col];
    }

    @Override
    public int length() {
        return numRows * numColumns;
    }

    @Override
    public String subSequence(int start, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            int row = i / numColumns;
            int col = i % numColumns;
            result.append((char) codeArray[row][col]);
        }
        return result.toString();
    }
}