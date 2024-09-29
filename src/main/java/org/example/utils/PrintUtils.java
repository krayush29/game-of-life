package org.example.utils;

public class PrintUtils {

    public static void print(int[][] cells, PrintFormat format) {
        switch (format) {
            case RAW:
                rawPrint(cells);
                break;
            case PRETTY:
                prettyPrint(cells);
                break;
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
    public static void rawPrint(int[][] cells) {
        for (int[] row : cells) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    public static void prettyPrint(int[][] cells) {
        for (int[] row : cells) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "# " : ". ");
            }
            System.out.println();
        }
    }
}
