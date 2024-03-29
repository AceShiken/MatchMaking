package com.chitchat.matchmaking.logic;

import javafx.util.Pair;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.chitchat.matchmaking.utils.ArrayOperationsUtils.*;

/**
 * An implemetation of the Kuhn–Munkres assignment algorithm of the year 1957.
 * https://en.wikipedia.org/wiki/Hungarian_algorithm
 *
 */
@Slf4j
public class HungarianLogic {

    public static final int SKIP = 100000;
    Integer[][] matrix; // initial matrix (cost matrix)

    @Getter
    double[][] inputMatrix;

    // markers in the matrix
    int[] squareInRow, squareInCol, rowIsCovered, colIsCovered, starredZeroesInRow;

    public HungarianLogic(double[][] data) {
        int size = (int) Math.sqrt(2 * data.length);
        inputMatrix = data;
        matrix = updateMatrix(inputMatrix);
        printMatrix(matrix);
        if (matrix.length != matrix[0].length) {
            try {
                throw new IllegalAccessException("The matrix is not square!");
            } catch (IllegalAccessException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

        squareInRow = new int[matrix.length];       // squareInRow & squareInCol indicate the position
        squareInCol = new int[matrix[0].length];    // of the marked zeroes

        rowIsCovered = new int[matrix.length];      // indicates whether a row is covered
        colIsCovered = new int[matrix[0].length];   // indicates whether a column is covered
        starredZeroesInRow = new int[matrix.length]; // storage for the 0*
        Arrays.fill(starredZeroesInRow, -1);
        Arrays.fill(squareInRow, -1);
        Arrays.fill(squareInCol, -1);
    }

    public HungarianLogic(double[] data) {
        int size = (int) Math.sqrt(2 * data.length);
        inputMatrix = createSymmetricMatrix(data, size);
        matrix = updateMatrix(inputMatrix);
        printMatrix(matrix);
        if (matrix.length != matrix[0].length) {
            try {
                throw new IllegalAccessException("The matrix is not square!");
            } catch (IllegalAccessException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

        squareInRow = new int[matrix.length];       // squareInRow & squareInCol indicate the position
        squareInCol = new int[matrix[0].length];    // of the marked zeroes

        rowIsCovered = new int[matrix.length];      // indicates whether a row is covered
        colIsCovered = new int[matrix[0].length];   // indicates whether a column is covered
        starredZeroesInRow = new int[matrix.length]; // storage for the 0*
        Arrays.fill(starredZeroesInRow, -1);
        Arrays.fill(squareInRow, -1);
        Arrays.fill(squareInCol, -1);
    }

    /**
     * find an optimal assignment
     *
     * @return optimal assignment
     */
    public Pair<Map<Integer, Integer>,Map<Integer, Double>>  findOptimalAssignment() {
        step1();    // reduce matrix
        step2();    // mark independent zeroes
        step3();    // cover columns which contain a marked zero
//        printMatrix(matrix);

        while (!allColumnsAreCovered()) {
            int[] mainZero = step4();
            while (mainZero == null) {      // while no zero found in step4
                step7();
                mainZero = step4();
            }
            if (squareInRow[mainZero[0]] == -1) {
                // there is no square mark in the mainZero line
                step6(mainZero);
                step3();    // cover columns which contain a marked zero
            } else {
                // there is square mark in the mainZero line
                // step 5
                rowIsCovered[mainZero[0]] = 1;  // cover row of mainZero
                colIsCovered[squareInRow[mainZero[0]]] = 0;  // uncover column of mainZero
                step7();
            }
        }

        Integer[][] optimalAssignment = new Integer[matrix.length][];
        for (int i = 0; i < squareInCol.length; i++) {
            optimalAssignment[i] = new Integer[]{i, squareInCol[i]};
        }
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (Integer[] assigned : optimalAssignment) {
            if(!(result.contains(new Pair<>(assigned[1], assigned[0])) || result.contains(new Pair<>(assigned[0], assigned[1]))) &&
                    inputMatrix[assigned[0]][assigned[1]] > 0) {
                if(assigned[0]>assigned[1]) {
                    result.add(new Pair<>(assigned[1], assigned[0]));
                } else {
                    result.add(new Pair<>(assigned[0], assigned[1]));
                }
            }
        }
        result.sort(Comparator.comparingInt((Pair<Integer, Integer> e) -> e.getKey()).thenComparingInt(Pair::getValue));
        return selectBestPair(result);
    }

    /**
     * Check if all columns are covered. If that's the case then the
          * optimal solution is found
     *
     * @return true or false
     */
    private boolean allColumnsAreCovered() {
        for (int i : colIsCovered) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Step 1:
     * Reduce the matrix so that in each row and column at least one zero exists:
     * 1. subtract each row minima from each element of the row
     * 2. subtract each column minima from each element of the column
     */
    private void step1() {
        // rows
        for (int i = 0; i < matrix.length; i++) {
            // find the min value of the current row
            int currentRowMin = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < currentRowMin) {
                    currentRowMin = matrix[i][j];
                }
            }
            // subtract min value from each element of the current row
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] -= currentRowMin;
            }
        }

        // cols
        for (int i = 0; i < matrix[0].length; i++) {
            // find the min value of the current column
            int currentColMin = Integer.MAX_VALUE;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] < currentColMin) {
                    currentColMin = matrix[j][i];
                }
            }
            // subtract min value from each element of the current column
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][i] -= currentColMin;
            }
        }
    }

    /**
     * Step 2:
     * mark each 0 with a "square", if there are no other marked zeroes in the same row or column
     */
    private void step2() {
        int[] rowHasSquare = new int[matrix.length];
        int[] colHasSquare = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // mark if current value == 0 & there are no other marked zeroes in the same row or column
                if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
                    rowHasSquare[i] = 1;
                    colHasSquare[j] = 1;
                    squareInRow[i] = j; // save the row-position of the zero
                    squareInCol[j] = i; // save the column-position of the zero
                    // Due to symmetric matrix
                    rowHasSquare[j] = 1;
                    colHasSquare[i] = 1;
                    squareInRow[j] = i;
                    squareInCol[i] = j;
                    continue; // jump to next row
                }
            }
        }
    }

    /**
     * Step 3:
     * Cover all columns which are marked with a "square"
     */
    private void step3() {
        for (int i = 0; i < squareInCol.length; i++) {
            colIsCovered[i] = squareInCol[i] != -1 ? 1 : 0;
        }
        for (int i = 0; i < squareInRow.length; i++) {
            rowIsCovered[i] = squareInRow[i] != -1 ? 1 : 0;
        }
    }

    /**
     * Step 4:
     * Find zero value Z_0 and mark it as "0*".
     *
     * @return position of Z_0 in the matrix
     */
    private int[] step4() {
        for (int i = 0; i < matrix.length; i++) {
            if (rowIsCovered[i] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0 && colIsCovered[j] == 0) {
                        starredZeroesInRow[i] = j; // mark as 0*
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Step 6:
     * Create a chain K of alternating "squares" and "0*"
     *
     * @param mainZero => Z_0 of Step 4
     */
    private void step6(int[] mainZero) {
        int i = mainZero[0];
        int j = mainZero[1];

        Set<int[]> K = new LinkedHashSet<>();
        //(a)
        // add Z_0 to K
        K.add(mainZero);
        boolean found = false;
        do {
            // (b)
            // add Z_1 to K if
            // there is a zero Z_1 which is marked with a "square " in the column of Z_0
            if (squareInCol[j] != -1) {
                K.add(new int[]{squareInCol[j], j});
                found = true;
            } else {
                found = false;
            }

            // if no zero element Z_1 marked with "square" exists in the column of Z_0, then cancel the loop
            if (!found) {
                break;
            }

            // (c)
            // replace Z_0 with the 0* in the row of Z_1
            i = squareInCol[j];
            j = starredZeroesInRow[i];
            // add the new Z_0 to K
            if (j != -1) {
                K.add(new int[]{i, j});
                found = true;
            } else {
                found = false;
            }

        } while (found); // (d) as long as no new "square" marks are found

        // (e)
        for (int[] zero : K) {
            // remove all "square" marks in K
            if (squareInCol[zero[1]] == zero[0]) {
                squareInCol[zero[1]] = -1;
                squareInRow[zero[0]] = -1;
            }
            // replace the 0* marks in K with "square" marks
            if (starredZeroesInRow[zero[0]] == zero[1]) {
                squareInRow[zero[0]] = zero[1];
                squareInCol[zero[1]] = zero[0];
            }
        }

        // (f)
        // remove all marks
        Arrays.fill(starredZeroesInRow, -1);
        Arrays.fill(rowIsCovered, 0);
        Arrays.fill(colIsCovered, 0);
    }

    /**
     * Step 7:
     * 1. Find the smallest uncovered value in the matrix.
     * 2. Subtract it from all uncovered values
     * 3. Add it to all twice-covered values
     */
    private void step7() {
        // Find the smallest uncovered value in the matrix
        int minUncoveredValue = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (rowIsCovered[i] == 1) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (colIsCovered[j] == 0 && matrix[i][j] < minUncoveredValue) {
                    minUncoveredValue = matrix[i][j];
                }
            }
        }

        if (minUncoveredValue > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (rowIsCovered[i] == 1 && colIsCovered[j] == 1) {
                        // Add min to all twice-covered values
                        matrix[i][j] += minUncoveredValue;
                    } else if (rowIsCovered[i] == 0 && colIsCovered[j] == 0) {
                        // Subtract min from all uncovered values
                        matrix[i][j] -= minUncoveredValue;
                    }
                }
            }
        }
    }

    private Pair<Map<Integer, Integer>,Map<Integer, Double>> selectBestPair(List<Pair<Integer, Integer>> result) {
        // Map to store the best scores for each column
        Map<Integer, Double> bestScores = new HashMap<>();

        // Map to store the selected pairs
        Map<Integer, Integer> selectedPairs = new HashMap<>();

        // Process the input data
        for (Pair<Integer, Integer> colRow : result) {

            int col = colRow.getKey();
            int row = colRow.getValue();
            double score = inputMatrix[colRow.getKey()][colRow.getValue()];

            // Check if the current pair has a better score than the existing best for the column
            if (!bestScores.containsKey(col) || score > bestScores.get(col)) {
                boolean isEligiblePair = true;
                if(selectedPairs.containsValue(col)) {
                    int prevSelectedKey = 0;
                    for(int key : selectedPairs.keySet()) {
                        if(selectedPairs.get(key) == col) {
                            prevSelectedKey = key;
                            break;
                        }
                    }
                    if(bestScores.get(prevSelectedKey) <= score) {
                        bestScores.remove(prevSelectedKey);
                        selectedPairs.remove(prevSelectedKey);
                    } else {
                        isEligiblePair = false;
                    }
                } else if(selectedPairs.containsValue(row)) {
                    int prevSelectedKey = 0;
                    for(int key : selectedPairs.keySet()) {
                        if(selectedPairs.get(key) == row) {
                            prevSelectedKey = key;
                            break;
                        }
                    }
                    if(bestScores.get(prevSelectedKey) <= score) {
                        bestScores.remove(prevSelectedKey);
                        selectedPairs.remove(prevSelectedKey);
                    } else {
                        isEligiblePair = false;
                    }
                }
                if(isEligiblePair) {
                    bestScores.put(col, score);
                    selectedPairs.put(col, row);
                }
            }

        }
        return new Pair<>(selectedPairs, bestScores);
    }
}
