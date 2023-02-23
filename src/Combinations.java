public class Combinations {
    /*
      Creates matrices which contain various combinations: all possible orders of the six given numbers in Countdown, all
      orders of the possible operations, and all combinations of the order of the operations.
     */
    private final int n;
    private final int[] combination;
    private final int[][] matrix;
    private int count;

    public Combinations(int n, int matrixLength) {
        this.n = n;  // number of entries in array
        this.combination = new int[n];  // combination to be added to matrix
        this.matrix = new int[matrixLength][n];  // matrix to store all the combinations
        this.count = 0;  // index of array in matrix
    }
    int[][] getNumbers(int[] numbers) {
        /*
          Returns a matrix containing, arrays of all combinations of the six numbers given to you in the game.
          For example: (50, 25, 5, 6, 9, 1), (50, 25, 5, 6, 1, 9), (50, 25, 5, 1, 9, 6), ...
         */
        count++;
        // fill in first row of matrix with the starting order
        System.arraycopy(numbers, 0, matrix[0], 0, n);
        // fill in the rest of the combinations of numbers
        int swapHold;
        int i = 1;
        while (i < n) {
            if (combination[i] < i) {
                if (i % 2 == 0) {
                    swapHold = numbers[0];
                    numbers[0] = numbers[i];
                } else {
                    swapHold = numbers[combination[i]];
                    numbers[combination[i]] = numbers[i];
                }
                numbers[i] = swapHold;
                System.arraycopy(numbers, 0, matrix[count], 0, n);
                count++;
                combination[i]++;
                i = 1;

            } else {
                combination[i] = 0;
                i++;
            }
        }
        return matrix;
    }
    int[][] getOperations(){
        /*
          Returns a matrix containing, arrays of the four possible operations (add, subtract, multiply and divide).
          For example: (+++++), (++++-), (+++--)...
        */

        for(int i = 0; i < 4; i++) {
            combination[0] = i;
            for(int j = 0; j < 4; j++) {
                combination[1] = j;
                for(int k = 0; k < 4; k++) {
                    combination[2] = k;
                    for(int l = 0; l < 4; l++) {
                        combination[3] = l;
                        for(int m = 0; m < 4; m++) {
                            combination[4] = m;
                            System.arraycopy(combination, 0, matrix[count], 0, n);
                            count++;
                        }
                    }

                }
            }

        }
        return matrix;
    }
    int[][] getOperationOrders(){
        /*
          Returns a matrix containing, arrays of all the different orders the operations can be done in. This uses reverse polish
          notation to do the sum for convenience
          For example: (100 50 5 9 8 2 +++++),((100 50 5 9 8 + 2 ++++),(100 50 5 9 8 + + 2 +++), ...
          There must be five operations and the number in each position can't be greater than the position in the array
        */

        for(int i = 0; i <= 1; i++) {
            combination[0] = i;
            for(int j = 0; j <= 2; j++) {
                combination[1] = j;
                for(int k = 0; k <= 3; k++) {
                    combination[2] = k;
                    for(int l = 0; l <= 4; l++) {
                        combination[3] = l;
                        for(int m = 0; m <= 5; m++) {
                            combination[4] = m;

                            if(sumArray(combination, 4) == 5) {  // there are five operations
                                for(int p = 0; p < 5; p++){
                                    if(sumArray(combination, p) > p + 1){
                                        break;
                                    }
                                    if(p == 4){  // last loop
                                        System.arraycopy(combination, 0, matrix[count], 0, n);
                                        count++;
                                    }
                                }
                            }

                        }
                    }

                }
            }

        }
        return matrix;
    }
    private int sumArray(int[] array, int indexToSumUpto){
        /*
          Sum array up-to index
        */
        assert indexToSumUpto <= array.length;
        int sum = 0;
        for(int i = 0; i <= indexToSumUpto; i++){
            sum += array[i];
        }
        return sum;
    }
}

