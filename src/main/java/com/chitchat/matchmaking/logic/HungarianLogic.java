package com.chitchat.matchmaking.logic;

import java.util.Arrays;

public class HungarianLogic {
    private static final int UNASSIGNED = -1;

    public static int[] getOptimizedMatches(double[][] costMatrix) {
        int numRows = costMatrix.length;
        int numCols = costMatrix[0].length;

        int[] rowCover = new int[numRows];
        int[] colCover = new int[numCols];
        int[] assignment = new int[numRows];

        Arrays.fill(assignment, UNASSIGNED);

        // Step 1: Subtract the minimum value from each row.
        for (int row = 0; row < numRows; row++) {
            double minVal = Integer.MAX_VALUE;
            for (int col = 0; col < numCols; col++) {
                if (costMatrix[row][col] < minVal) {
                    minVal = costMatrix[row][col];
                }
            }
            for (int col = 0; col < numCols; col++) {
                costMatrix[row][col] -= minVal;
            }
        }

        // Step 2: Subtract the minimum value from each column.
        for (int col = 0; col < numCols; col++) {
            double minVal = Integer.MAX_VALUE;
            for (int row = 0; row < numRows; row++) {
                if (costMatrix[row][col] < minVal) {
                    minVal = costMatrix[row][col];
                }
            }
            for (int row = 0; row < numRows; row++) {
                costMatrix[row][col] -= minVal;
            }
        }

        // Steps 3 and 4: Cover rows and columns with minimum lines.
        int pathRow = -1;
        while (true) {
            boolean pathFound = false;
            for (int row = 0; row < numRows; row++) {
                if (rowCover[row] == 0) {
                    for (int col = 0; col < numCols; col++) {
                        if (colCover[col] == 0 && costMatrix[row][col] == 0) {
                            rowCover[row] = 1;
                            colCover[col] = 1;
                            assignment[row] = col;
                            pathFound = true;
                            break;
                        }
                    }
                    if (pathFound) {
                        break;
                    }
                }
            }
            if (!pathFound) {
                double minUncovered = Integer.MAX_VALUE;
                for (int row = 0; row < numRows; row++) {
                    if (rowCover[row] == 0) {
                        for (int col = 0; col < numCols; col++) {
                            if (colCover[col] == 0) {
                                if (costMatrix[row][col] < minUncovered) {
                                    minUncovered = costMatrix[row][col];
                                    pathRow = row;
                                }
                            }
                        }
                    }
                }
                for (int col = 0; col < numCols; col++) {
                    if (colCover[col] == 1) {
                        rowCover[assignment[col]] = 0;
                        colCover[col] = 0;
                    }
                }
                colCover[pathRow] = 1;
            }
            boolean done = true;
            for (int row = 0; row < numRows; row++) {
                if (rowCover[row] == 0) {
                    done = false;
                    break;
                }
            }
            if (done) {
                break;
            }
        }

        return assignment;
    }
}
