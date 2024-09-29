package org.example.utils;

import org.example.entity.GameOfLife;

import java.util.Scanner;

public class ConsoleUtils {

    public static void playGameOfLife(){
        while (true){
            System.out.println("Do you want to play the Game of Life? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
                processGameOfLife();
                System.out.println("Thank you for playing!");
            }else if(input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")){
                System.out.println("Exiting the game. Thank you for playing!");
                System.exit(0);
            }else{
                System.out.println("Invalid input! Please enter Y for Yes, N for No.");
            }
        }
    }

    public static void processGameOfLife(){
        int MAX_RETRIES = 3;
        Scanner scanner = new Scanner(System.in);
        int[][] inputMatrix = getInputMatrix();
        int n = inputMatrix.length;
        int m = inputMatrix[0].length;

        int numGenerations = 0;

        for(int retries = 0; retries < MAX_RETRIES; retries++){
            System.out.println("Enter number of generations that grid to be evolved: ");
            try {
                numGenerations = Integer.parseInt(scanner.nextLine());
                if (numGenerations <= 0)
                    throw new IllegalArgumentException("Number of generations should be greater than 0");
                break;
            }
            catch (Exception e){
                System.out.println("Invalid input! Please enter valid positive integer for number of generations.");
                System.out.println("Retries left: " + (MAX_RETRIES - retries - 1));
                if(retries == MAX_RETRIES - 1){
                    System.out.println("Maximum retries reached. Exiting the game.");
                    System.exit(1);
                }
            }
        }

        GameOfLife gameOfLife = new GameOfLife(n, m);
        gameOfLife.initialize(inputMatrix);

        for(int retries = 0; retries < MAX_RETRIES; retries++){
            System.out.println("Enter the print format (R for raw print, P for pretty print): ");
            try {
                String printFormat = scanner.nextLine();
                if (printFormat.equalsIgnoreCase("R") || printFormat.equalsIgnoreCase("RAW")) {
                    gameOfLife.playAndPrint(numGenerations, PrintFormat.RAW);
                } else if (printFormat.equalsIgnoreCase("P") || printFormat.equalsIgnoreCase("PRETTY")) {
                    gameOfLife.playAndPrint(numGenerations, PrintFormat.PRETTY);
                } else {
                    throw new IllegalArgumentException("Invalid print format. Please enter R for raw print, P for pretty print.");
                }
                break;
            }
            catch (Exception e){
                System.out.println("Invalid input! Please enter valid print format (R for raw print, P for pretty print).");
                System.out.println("Retries left: " + (MAX_RETRIES - retries - 1));
                if(retries == MAX_RETRIES - 1){
                    System.out.println("Maximum retries reached. Exiting the game.");
                    System.exit(1);
                }
            }
        }
    }

    public static int[][] getInputMatrix(){
        int MAX_RETRIES = 3;
        Scanner scanner = new Scanner(System.in);
        int n = 0, m = 0;

        for(int retries = 0; retries < MAX_RETRIES; retries++){
            System.out.println("Enter the number of rows and columns for the grid (space separated):");

            try{
                System.out.println("Enter the number of rows (n): ");
                n = Integer.parseInt(scanner.next());

                System.out.println("Enter the number of columns (m): ");
                m = Integer.parseInt(scanner.next());

                if(n <= 0 || m<=0 ) throw new IllegalArgumentException("Grid dimensions should be greater than 0");
                break;
            }catch(Exception e){
                System.out.println("Invalid input! Please enter valid positive integers for rows and columns.");
                System.out.println("Retries left: " + (MAX_RETRIES - retries - 1));
                if(retries == MAX_RETRIES - 1){
                    System.out.println("Maximum retries reached. Exiting the game.");
                    System.exit(0);
                }
            }
        }


        // Create a matrix to store the input Matrix
        int[][] cellMatrix = new int[n][m];

        for(int retries = 0; retries < MAX_RETRIES; retries++){
            System.out.println("Enter the initial state of the grid (0s and 1s separated by space):");
            System.out.println("For Example, for a 3x3 grid enter");
            System.out.println("1 0 1");
            System.out.println("0 1 0");
            System.out.println("1 0 1");
            System.out.println();
            System.out.println("Enter " + n + "x" + m + " space separated Integer values.");
            int[][] inputMatrix = new int[n][m];

            try{
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < m; j++){
                        inputMatrix[i][j] = Integer.parseInt(scanner.next());
                        if(inputMatrix[i][j] != 0 && inputMatrix[i][j] != 1) throw new IllegalArgumentException("Input grid should contain only 0s and 1s");
                    }
                }

                cellMatrix = inputMatrix;
                break;
            }catch(Exception e){
                System.out.println("Invalid input! Please enter valid 0s and 1s separated by space.");
                System.out.println("Retries left: " + (MAX_RETRIES - retries - 1));
                if(retries == MAX_RETRIES - 1){
                    System.out.println("Maximum retries reached. Exiting the game.");
                    System.exit(0);
                }
            }
        }

        return cellMatrix;
    }
}