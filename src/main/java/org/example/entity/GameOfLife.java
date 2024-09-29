package org.example.entity;

import org.example.utils.PrintFormat;
import org.example.utils.PrintUtils;

public class GameOfLife {
    private final Grid grid;

    public GameOfLife(int rows, int cols) {
        grid = new Grid(rows, cols);
    }

    public void initialize(int[][] seedData) {
        grid.serialize(seedData);
    }

    public void playAndPrint(int generations, PrintFormat printFormat) {
        System.out.println("------------ Initial State -------------");
        PrintUtils.print(grid.deSerialize(), printFormat);

        for (int i = 0; i < generations; i++) {
            grid.nextGeneration();
            System.out.println("------------ Generation: " + (i + 1) + " -------------");
            PrintUtils.print(grid.deSerialize(), printFormat);
        }
    }
}
