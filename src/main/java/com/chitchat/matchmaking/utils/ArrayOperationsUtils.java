package com.chitchat.matchmaking.utils;

import com.chitchat.matchmaking.logic.HungarianLogic;

import java.util.Arrays;

public class ArrayOperationsUtils {

    public static void printMatrix(Integer[][] matrix) {
        for (Integer[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println("_____________________________________________");
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println("_____________________________________________");
    }

    public static int[] appendArrays(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;

        // Create a new array with the combined length
        int[] resultArray = new int[length1 + length2];

        int i = 0, j = 0, k = 0;

        // Merge the two arrays in sorted order
        while (i < length1 && j < length2) {
            if (array1[i] <= array2[j]) {
                resultArray[k++] = array1[i++];
            } else {
                resultArray[k++] = array2[j++];
            }
        }

        // Copy the remaining elements from array1, if any
        while (i < length1) {
            resultArray[k++] = array1[i++];
        }

        // Copy the remaining elements from array2, if any
        while (j < length2) {
            resultArray[k++] = array2[j++];
        }

        return resultArray;
    }

    public static double[][] createSymmetricMatrix(double[] data, int size) {
        double[][] matrix = new double[size][size];

        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                matrix[i][j] = data[k];
                matrix[j][i] = data[k]; // Symmetric element
                k++;
            }
        }

        return matrix;
    }

    public static Integer[][] updateMatrix(double[][] matrix) {

        // Update each element in the matrix
        Integer[][] updatedMatrix = new Integer[matrix.length][matrix.length];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                updatedMatrix[i][j] =  (int)(-100 * Math.max(matrix[i][j],-1000));
                min = Math.min(updatedMatrix[i][j], min);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            updatedMatrix[i][i] = HungarianLogic.SKIP;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                updatedMatrix[i][j] = updatedMatrix[i][j] - min;
            }
        }


        return updatedMatrix;
    }

    // Method to remove specified rows and columns from a 2D array
    public static double[][] removeRowsAndColumns(double[][] inputArray, int[] rowsToRemove, int[] colsToRemove) {
        int numRows = inputArray.length;
        int numCols = inputArray[0].length;

        // Create a boolean array to mark rows and columns to be removed
        boolean[] removeRow = new boolean[numRows];
        boolean[] removeCol = new boolean[numCols];

        // Mark rows and columns to be removed
        for (int row : rowsToRemove) {
            if (row >= 0 && row < numRows) {
                removeRow[row] = true;
            }
        }

        for (int col : colsToRemove) {
            if (col >= 0 && col < numCols) {
                removeCol[col] = true;
            }
        }

        // Calculate the dimensions of the new array
        int newNumRows = numRows - countTrueValues(removeRow);
        int newNumCols = numCols - countTrueValues(removeCol);

        // Create the new array
        double[][] newArray = new double[newNumRows][newNumCols];

        // Copy elements to the new array, excluding the specified rows and columns
        int newRow = 0;
        int newCol;

        for (int i = 0; i < numRows; i++) {
            if (!removeRow[i]) {
                newCol = 0;
                for (int j = 0; j < numCols; j++) {
                    if (!removeCol[j]) {
                        newArray[newRow][newCol] = inputArray[i][j];
                        newCol++;
                    }
                }
                newRow++;
            }
        }

        return newArray;
    }

    // Helper method to count true values in a boolean array
    private static int countTrueValues(boolean[] array) {
        int count = 0;
        for (boolean value : array) {
            if (value) {
                count++;
            }
        }
        return count;
    }
}
