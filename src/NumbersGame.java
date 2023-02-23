import java.util.Arrays;
import java.util.Scanner;

public class NumbersGame {
    /*
    Solves the numbers game in Countdown, printing the first solution it finds.
    */
    public static void main(String[] args) {

        int[] inputNumbers = new int[6];  // holds numbers given in the game

        // reads numbers given in game
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers:");
        for(int i = 0; i < inputNumbers.length; i++){
            inputNumbers[i] = sc.nextInt();
        }
        // prints numbers that have been entered
        System.out.println("\nEntered all numbers");
        System.out.println(Arrays.toString(inputNumbers));

        // reads target number
        System.out.println("\nEnter Target");
        int target = sc.nextInt();
        System.out.println("\nCalculating...\n");

        // creates the matrices containing the combinations
        int[][] numbers = new Combinations(6,720).getNumbers(inputNumbers); // contains all the orders the numbers could be in
        int[][] operations = new Combinations(5, 1024).getOperations();  // contains the combinations of the operations
        int[][] operationOrders = new Combinations(5, 42).getOperationOrders();  // contains the combinations of the order of the operations

        String success = null;  // a String representation of the operations carried out
        outerLoop:
        // loops over: the numbers, the order of the operations, and the operations
        for (int[] number : numbers) {
            for (int[] operationOrder : operationOrders) {
                for (int[] operation : operations) {
                    // checks if current combination sums to the target
                    if (checkSumsToTarget(number, operationOrder, operation, target, false) != null) {  // if the sum is successful
                        // find the series of calculations that give the target number
                        success = checkSumsToTarget(number, operationOrder, operation, target, true);
                        System.out.println("Solution Found!\n\nSum in Reverse Polish Notation");
                        printSumRPN(number, operationOrder, operation);  // print the operations carried out in RPN
                        System.out.println("\n\nTarget of " + target + " reached");
                        System.out.println(success);
                        break outerLoop;  // break when a solution is found
                    }
                }
            }
        }
        if (success == null) {
            System.out.println("No solutions found");
        }
    }
    static String checkSumsToTarget(int[] numbersOriginal, int[] operationOrderOriginal, int[] operations, int target, boolean print) {
        /*
          Checks if the sum equals the target at any point, can choose to print to screen
         */
        int[] numbers = new int [6];
        int[] operationOrder = new int [5];

        //copies arrays, so they can be altered without changing the originals
        System.arraycopy(numbersOriginal, 0, numbers, 0, 6);
        System.arraycopy(operationOrderOriginal, 0, operationOrder, 0, 5);

        int operationPosition = 0;
        int numberPosition;
        StringBuilder printString = new StringBuilder();

        //loops over the five operations to carry out on the six numbers
        for(int runInstance = 0; runInstance < 5; runInstance++) {

            //finds the position of the first operation to carry out
            for(int j = 0; j < 5; j++) {
                if(operationOrder[j] != 0) {
                    operationPosition = j;
                    break;
                }
            }
            numberPosition = operationPosition - runInstance;

            if (print) {printString.append(numbers[numberPosition]);}
            //do operation
            switch (operations[runInstance]) {
                //add
                case 0 -> {
                    if (print){printString.append(" + ");}
                    numbers[numberPosition] += numbers[numberPosition + 1];
                }
                case 1 -> {
                    if (print){printString.append(" - ");}
                    numbers[numberPosition] -= numbers[numberPosition + 1];
                }
                case 2 -> {
                    if (print){printString.append(" * ");}
                    numbers[numberPosition] *= numbers[numberPosition + 1];
                }
                case 3 -> {
                    //check for dividing by zero
                    if (numbers[numberPosition + 1] == 0) {
                        return null;
                    }
                    //check remainder is zero
                    if (numbers[numberPosition] % numbers[numberPosition + 1] != 0) {
                        return null;
                    }
                    if (print){printString.append(" / ");}
                    numbers[numberPosition] /= numbers[numberPosition + 1];
                }
            }
            if (print){printString.append(numbers[numberPosition + 1]).append(" = ").append(numbers[numberPosition]).append("\n");}

            //if result is less than zero it is not valid
            if(numbers[numberPosition] < 0) {
                return null;
            }
            //if result is equal to the target returns the number of runs
            if(numbers[numberPosition] == target) {
                if (print) {
                    return printString.toString();
                }else {
                    return " ";
                }
            }
            //moves order of array so not needed number is at the end
            for(int i = (numberPosition + 1); i < 5; i++) {
                numbers[i] = numbers[i + 1];
            }

            //takes one off the number of operations to do in a particular position in the sum
            operationOrder[operationPosition]--;
        }

        return null;
    }
    static void printSumRPN(int[] numbers, int[] operationOrder, int[] operations) {
        /*
          Prints sum in reverse polish notation
        */
        int runNumber = 0; //runNumber 0-4
        System.out.print(numbers[0] + " ");
        for(int j = 0; j < 5; j++) {
            System.out.print(numbers[j+1] + " ");
            if (operationOrder[j] != 0) {
                for(int i = 0; i < operationOrder[j]; i++) {
                    switch (operations[i + runNumber]) {
                        case 0 -> System.out.print("+ ");
                        case 1 -> System.out.print("- ");
                        case 2 -> System.out.print("* ");
                        case 3 -> System.out.print("/ ");
                    }
                }
                runNumber += operationOrder[j];
            }
        }
    }
}

