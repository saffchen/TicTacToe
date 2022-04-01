package com.saffchen.tictactoegame;

import java.util.*;

public class TicTacToeGame {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your placement 1-9: ");
            int playerPosition = scanner.nextInt();
            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Position taken! Enter a correct position: ");
                playerPosition = scanner.nextInt();
            }
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            placePiece(gameBoard, playerPosition, "player");

            Random random = new Random(); //Рандомный ход компьюетором
            int cpuPosition = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                System.out.println("CPU adopt taken position!");
                cpuPosition = random.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    private static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
        }
    }

    private static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> rightCol = Arrays.asList(1, 4, 7);
        List<Integer> leftCol = Arrays.asList(3, 6, 9);
        List<Integer> leftCross = Arrays.asList(1, 5, 9);
        List<Integer> rightCross = Arrays.asList(3, 5, 7);

        List<List> win = new ArrayList<>();
        win.add(topRow);
        win.add(midRow);
        win.add(botRow);
        win.add(rightCol);
        win.add(leftCol);
        win.add(leftCross);
        win.add(rightCross);

        for (List l : win) {
            if (playerPositions.containsAll(l)) {
                return "Congratulation, you win";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU win, sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }
        return "";
    }
}

