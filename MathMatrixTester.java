import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: ajp4638
 *  email address: penajakk@gmail.com
 *  Grader name: Andrew Rodriguez
 *  Number of slip days I am using: 0
 */



/* CS314 Students. Put your experiment results and
 * answers to questions here.
 *
 * Experiment 1 Results:
 * 1000 x 1000 || N
 *      elapsed time: 2.778125301 seconds.
 * 2000 x 2000 || 2N
 *      elapsed time: 8.981061601 seconds.
 * 4000 x 4000 || 4N
 *      elapsed time: 36.1972819 seconds.
 *
 * Experiment 1 Code (COLS1 and ROWS1 increase N * 2):
 *    Random randNumGen = new Random(6201919);
 *    Stopwatch st = new Stopwatch();
 *    final int ROWS1 = 1000;
 *    final int COLS1 = 1000;
 *    final int LIMIT = 1000;
 *    mat1 = createMat(randNumGen, ROWS1, COLS1, LIMIT);
 *    mat2 = createMat(randNumGen, ROWS1, COLS1, LIMIT);
 *    st.start();
 *    for(int i = 0; i < 1000; i++) {
 *        mat3 = mat1.add(mat2);
 *    }
 *    st.stop();
 *    System.out.print(st.toString());
 *
 * Experiment 2 Results:
 * 300 x 300 || N
 *      elapsed time: 3.090726801 seconds.
 * 600 x 600 || 2N
 *      elapsed time: 26.8876957 seconds.
 * 1200 x 1200 || 4N
 *      elapsed time: 438.8175297 seconds.
 *
 * Experiment 2 Code (COLS1 and ROWS1 increase N * 2):
 *    Random randNumGen = new Random(6201919);
 *    Stopwatch st = new Stopwatch();
 *    final int ROWS1 = 300;
 *    final int COLS1 = 300;
 *    final int LIMIT = 1000;
 *    mat1 = createMat(randNumGen, ROWS1, COLS1, LIMIT);
 *    mat2 = createMat(randNumGen, ROWS1, COLS1, LIMIT);
 *    st.start();
 *    for(int i = 0; i < 100; i++) {
 *        mat3 = mat1.multiply(mat2);
 *    }
 *    st.stop();
 *    System.out.print(st.toString());
 *
 * Question Answers:
 *
 *  1. I expected it to take about as long as it did considering the operations in
 *      the add method are much less complicated.
 *  2. The Big O is O(n^2). Yes my data supports this.
 *  3. I expected it to be cubic, but the 4N number kind of threw me off
 *  4. The Big O is O(n^3). The first tests do, but the last one seems a it off.
 *  5. Roughly around a 12000 x 12000 matrix.
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = {{4, 9, 8},
                {12, 4, 2}};
        int[][] data2 = {{5, 6, 7},
                {3, 5, 6}};
        int[][] e1;

        //test 1 and test 2, get the size and values of matrix
        MathMatrix mat1 = new MathMatrix(3, 4, 5);
        e1 = new int[][]{{5, 5, 5, 5}, {5, 5, 5, 5}, {5, 5, 5, 5}};
        printTestResult(get2DArray(mat1), e1, 1, "Constructor with size and initial val specified");
        mat1 = new MathMatrix(2, 2, 3);
        e1 = new int[][]{{3, 3}, {3, 3}};
        printTestResult(get2DArray(mat1), e1, 2, "Constructor with size and initial val specified");


        //tests 3 and 4, int[][] constructor, Deep Copy Test
        mat1 = new MathMatrix(data1);
        data1[0][0] = 6;
        // alter data1. mat1 will not change if a Deep Copy is created
        e1 = new int[][]{{6, 9, 8}, {12, 4, 2}};
        printTestResult(data1, e1, 3, "constructor with one parameter of type int[][]");
        // . mat1 should be unchanged if deep copy made
        e1 = new int[][]{{4, 9, 8}, {12, 4, 2}};
        printTestResult(get2DArray(mat1), e1, 4, "constructor with one parameter of type int[][]. Testing deep copy made");

        //test 5 and 6, getVal method
        int val = 9;
        System.out.print("Test number 5 tests the getVal method.");
        if (mat1.getVal(0, 1) == val) {
            System.out.println("The test passed.");
        } else {
            System.out.println("The test failed.");
        }
        val = 12;
        System.out.print("Test number 5 tests the getVal method.");
        if (mat1.getVal(1, 0) == val) {
            System.out.println("The test passed.");
        } else {
            System.out.println("The test failed.");
        }


        //tests 7 and 8, addition
        data1[0][0] = 5;
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][]{{5, 9, 8}, {12, 4, 2}};
        printTestResult(get2DArray(mat1), e1, 7, "add method. Testing mat1 unchanged");
        e1 = new int[][]{{10, 15, 15}, {15, 9, 8}};
        printTestResult(get2DArray(mat3), e1, 8, "add method. Testing mat3 correct result");

        //tests 9 and 10, subtraction
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        mat3 = mat1.subtract(mat2);
        e1 = new int[][]{{5, 9, 8}, {12, 4, 2}};
        printTestResult(get2DArray(mat1), e1, 9, "subtract method. Testing mat1 unchanged");
        e1 = new int[][]{{0, 3, 1}, {9, -1, -4}};
        printTestResult(get2DArray(mat3), e1, 10, "subtract method. Testing mat3 correct result");

        //test 11 and 12, multiplication
        data2 = new int[][]{{2, 2}, {3, 2}, {2, 3}};
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][]{{34, 26, 20}, {39, 35, 28}, {46, 30, 22}};
        printTestResult(get2DArray(mat3), e1, 11, "multiply method");
        data2 = new int[][]{{1, 2}, {4, 2}, {1, 3}};
        mat2 = new MathMatrix(data2);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][]{{29, 17, 12}, {44, 44, 36}, {41, 21, 14}};
        printTestResult(get2DArray(mat3), e1, 12, "multiply method");


        //test 13 and 14, getScaledMatrix()
        data1 = new int[][]{{2, 3, 5}, {5, 6, 7}, {3, 4, 5}};
        mat1 = new MathMatrix(data1);
        mat3 = mat1.getScaledMatrix(2);
        e1 = new int[][] {{4, 6, 10}, {10, 12, 14}, {6, 8, 10}};
        printTestResult(get2DArray(mat3), e1, 13, "getScaledMatrix");
        mat3 = mat1.getScaledMatrix(3);
        e1 = new int[][] {{6, 9, 15}, {15, 18, 21}, {9, 12, 15}};
        printTestResult(get2DArray(mat3), e1, 14, "getScaledMatrix");

        //test 15 and 16. getTranspose
        e1 = new int[][]{{2, 5, 3}, {3, 6, 4}, {5, 7, 5}};
        mat3 = mat1.getTranspose();
        printTestResult(get2DArray(mat3), e1, 15, "getTranspose method");
        e1 = new int[][] {{1, 4, 1}, {2, 2, 3}};
        mat3 = mat2.getTranspose();
        printTestResult(get2DArray(mat3), e1, 16, "getTranspose method");

        // test 17 and 18, equals()
        e1 = new int[][] {{2, 3, 5}, {5, 6, 7}, {3, 4, 5}};
        printTestResult(get2DArray(mat1), e1, 17, "equals method");
        e1 = new int[][] {{1, 2}, {4, 2}, {1, 3}};
        printTestResult(get2DArray(mat2), e1, 18, "equals method");

        //test 19 and 20, toString()
        data1 = new int[][]{{20, 205, 605, -3456},
                {7845, 56, 55, 9},
                {1, -4, 9, 7}};
        mat1 = new MathMatrix(data1);
        String expected = "|    20   205   605 -3456|\n|  7845    56    55     9|\n|     1    -4     9     7|\n";
        System.out.print("Test number 19 tests the toString method.");
        if (mat1.toString().equals(expected)) {
            System.out.println(" The test passed");
        } else {
            System.out.println(" The test failed");
        }
        data1 = new int[][]{{1, 56, 25, 1000},
                {7, 8, 45, 783},
                {1, -8, 4, 2}};
        mat1 = new MathMatrix(data1);
        String expected2 = "|    1   56   25 1000|\n|    7    8   45  783|\n|    1   -8    4    2|\n";
        System.out.print("Test number 20 tests the toString method.");
        if (mat1.toString().equals(expected2)) {
            System.out.println(" The test passed");
        } else {
            System.out.println(" The test failed");
        }

        //test 21 and 22, upperTriangular
        data1 = new int[][] {{4, 5, 6, 0}, {0, 3, 2, 1}, {0, 0, 5, 7}, {0, 0, 0, 10}};
        mat1 = new MathMatrix(data1);
        System.out.print("Test number 21 tests the upperTriangular method.");
        if (mat1.isUpperTriangular()) {
            System.out.println(" The test passed");
        } else {
            System.out.println(" The test failed");
        }
        data1 = new int[][] {{78, 45, 67, 0}, {0, 3, 8, 23}, {0, 0, 10, 9}, {6, 3, 8, 9}};
        System.out.print("Test number 22 tests the upperTriangular method.");
        mat1 = new MathMatrix(data1);
        if (!mat1.isUpperTriangular()) {
            System.out.println(" The test passed");
        } else {
            System.out.println(" The test failed");
        }
    }
    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        }
        int result = 0;
        final int ROWS =  mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
                                       int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if(rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        if  ((m == null) || (m.getNumRows() == 0)
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for(int r = 0; r < result.length; r++) {
            for(int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
        //check precondition
        if((data1 == null) || (data1.length == 0)
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                ||  (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null"
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}

