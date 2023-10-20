import java.util.Arrays;

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

    // instance variable
    private int[][] con;
    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        if (mat == null || mat.length < 0 || mat[0].length < 0) {
            throw new IllegalArgumentException("The argument "
                    + "values cannot be null");
        }
        con = deepCopy(mat);
    }

    // pre: none
    // post: return a 2d array deep copy of given values.
    private int[][] deepCopy(int[][] initialVals) {
        final int rowLength = initialVals.length;
        final int colLength = initialVals[0].length;

        int[][] result = new int [rowLength][colLength];
        for(int i = 0; i < rowLength; i++) {
            result[i] = Arrays.copyOf(initialVals[i], initialVals[i].length);
        }
        return result;
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the initialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if (numRows < 0 || numCols < 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "numRows and numCols must be greater than 0.");
        }
        con = createMat(numRows, numCols, initialVal);
    }

    // pre: none
    // post: return a 2d array with the inital values in each element
    // in each given row and column.
    private int[][] createMat(int numRows, int numCols, int initialVal) {
        int[][] result = new int[numRows][numCols];
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                result[row][col] = initialVal;
            }
        }
        return result;
    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return con.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){
        return con[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        if (row < 0 || row > getNumRows()
            || col < 0 || col > getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                + "The given number of rows or columns is out of bounds.");
        }
        return con[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){
        if (rightHandSide.getNumRows() != getNumRows()
            || rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Arguments out of bounds.");
        }
        int add[][] = new int[getNumRows()][getNumColumns()];
        MathMatrix result = new MathMatrix(add);

        for(int r = 0; r < getNumRows(); r++) {
            for(int c = 0; c < getNumColumns(); c++) {
                result.con[r][c] = con[r][c] + rightHandSide.con[r][c];
            }
        }
        return result;
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){
        if (rightHandSide.getNumRows() != getNumRows()
                || rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Arguments out of bounds.");
        }
        int sub[][] = new int[getNumRows()][getNumColumns()];
        MathMatrix result = new MathMatrix(sub);

        for(int r = 0; r < getNumRows(); r++) {
            for(int c = 0; c < getNumColumns(); c++) {
                result.con[r][c] = con[r][c] - rightHandSide.con[r][c];
            }
        }
        return result;
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){
        if (rightHandSide.getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                + "Right Hand Side Matrix must have the same amount of rows as "
                + "Left Hand Side Matrix columns.");
        }

        int[][] multi = new int[getNumRows()][rightHandSide.getNumColumns()];
        MathMatrix result = new MathMatrix(multi);
        for (int r = 0; r < getNumRows(); r++) {
            for(int c = 0; c < rightHandSide.getNumColumns(); c++) {
                result.con[r][c] = multiplyCell(rightHandSide, r, c);
            }
        }
        return result;
    }
    // pre: none
    // post: return the added values from LHS col * RHS rows.
    // Multiply each individual value from this Matrix current columns
    // by the rightHandSide Matrix current rows.
    private int multiplyCell(MathMatrix rightHandSide, int row, int col) {
        int total = 0;
        for (int i = 0; i < rightHandSide.getNumRows(); i++) {
            total += con[row][i] * rightHandSide.con[i][col];
        }
        return total;
    }

    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix 
     * multiplied by factor. 
     * In other words after this method has been called 
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
        int scaleMat[][] = new int[getNumRows()][getNumColumns()];
        for(int i = 0; i < getNumRows(); i++) {
            for(int j = 0; j < getNumColumns(); j++) {
                scaleMat[i][j] = con[i][j] * factor;
            }
        }
        return new MathMatrix(scaleMat);
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        int[][] temp = new int[getNumColumns()][getNumRows()];
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumColumns(); c++) {
                temp[c][r] = con[r][c];
            }
        }
        return new MathMatrix(temp);
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         */
        boolean result = false;
        // We use getClass instead of instanceof because we only want a MathMatrix to equal 
        // another MathMatrix as opposed to any possible sub classes. We would
        // use instance of if we were implementing am interface and wanted to equal
        // other objects that are instances of that interface but not necessarily
        // MathMatrix objects.
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix) rightHandSide;

            // complete the method, delete this comment
            // I recommend not changing any other code.
            for (int r = 0; r < getNumRows(); r++) {
                for (int c = 0; c < getNumColumns(); c++) {
                    if (getVal(r, c) == otherMatrix.getVal(r, c)) {
                        result = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return result;
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
        int longestVal = getLongNum();
        StringBuilder result = new StringBuilder();

        for (int r = 0; r < getNumRows(); r++) {
            result.append("|");
            for (int c = 0; c < getNumColumns(); c++) {
                // Only inputs amount of spaces depending on the length
                // of the current string up to the already found
                // longest value.
                int lengthOfCurrStr = ("" + con[r][c]).length();
                for (int k = lengthOfCurrStr; k < longestVal; k++) {
                    result.append(" ");
                }
                result.append(con[r][c]);
            }
            result.append("|\n");
        }
        return result.toString();
    }

    // pre: none
    // post: Returns the values with the largest amount of char + 1;
    // Cycles through each values of the array until it finds the
    // largest amount of characters in the string, including negative.
    private int getLongNum() {
        int max = 0;

        for (int i = 0; i <  getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                int temp = con[i][j];
                int lengthOfTemp = ("" + temp).length();
                if (lengthOfTemp > max) {
                    max = lengthOfTemp;
                }
            }
        }
        return max + 1;
    }



    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular(){
        if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                + "Number of rows must equals number of columns.");
        }
        for(int r = 1; r < getNumRows(); r++) {
            for(int c = 0; c < r; c++) {
                if (con[r][c] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // method to ensure mat is rectangular. It is public so that
    // tester classes can use it. 
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }
}
