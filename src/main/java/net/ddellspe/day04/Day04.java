package net.ddellspe.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class BingoBoard {
  int[] board;
  boolean[] selectedBoard;

  public BingoBoard(List<String> board) {
    this.board = new int[25];
    this.selectedBoard = new boolean[25];
    int ind = 0;
    for (String row : board) {
      for (String cell : row.stripLeading().split("[ ]+")) {
        this.board[ind] = Integer.parseInt(cell);
        ind++;
      }
    }
  }

  public boolean checkBingo() {
    for (int row = 0; row < 5; row++) {
      if (selectedBoard[row * 5]
          && selectedBoard[row * 5 + 1]
          && selectedBoard[row * 5 + 2]
          && selectedBoard[row * 5 + 3]
          && selectedBoard[row * 5 + 4]) {
        return true;
      }
    }
    for (int col = 0; col < 5; col++) {
      if (selectedBoard[col]
          && selectedBoard[col + 5]
          && selectedBoard[col + 10]
          && selectedBoard[col + 15]
          && selectedBoard[col + 20]) {
        return true;
      }
    }
    return false;
  }

  public boolean notBingo() {
    return !checkBingo();
  }

  public boolean draw(int value) {
    for (int i = 0; i < 25; i++) {
      if (board[i] == value) {
        selectedBoard[i] = true;
        break;
      }
    }
    return checkBingo();
  }

  public long calculateScore(int pick) {
    printBoard();
    System.out.println(pick);
    long sum = 0L;
    for (int i = 0; i < 25; i++) {
      if (selectedBoard[i] == false) {
        sum += board[i];
      }
    }
    System.out.println(sum);
    return sum * pick;
  }

  public void printBoard() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.printf(
            "%1c%2d ", this.selectedBoard[i * 5 + j] ? '*' : ' ', this.board[i * 5 + j]);
      }
      System.out.println();
    }
  }
}

public class Day04 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day04.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    List<Integer> numbers =
        Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    List<BingoBoard> boards = new ArrayList<>();
    List<String> board = new ArrayList<>();
    for (String line : data) {
      if (line.equals(data.get(0))) {
        board.clear();
        continue;
      }
      if (line.isBlank() && board.isEmpty()) {
        continue;
      }
      if (line.isBlank() && !board.isEmpty()) {
        boards.add(new BingoBoard(board));
        board.clear();
        continue;
      }
      board.add(line);
    }
    BingoBoard finalBoard = null;
    int lastPick;
    boards.add(new BingoBoard(board));
    boolean bingo = false;
    for (int pick : numbers) {
      for (BingoBoard brd : boards) {
        if (brd.draw(pick)) {
          bingo = true;
          return brd.calculateScore(pick);
        }
      }
      if (bingo) {
        break;
      }
    }
    return 0L;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    List<Integer> numbers =
        Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    List<BingoBoard> boards = new ArrayList<>();
    List<String> board = new ArrayList<>();
    for (String line : data) {
      if (line.equals(data.get(0))) {
        board.clear();
        continue;
      }
      if (line.isBlank() && board.isEmpty()) {
        continue;
      }
      if (line.isBlank() && !board.isEmpty()) {
        boards.add(new BingoBoard(board));
        board.clear();
        continue;
      }
      board.add(line);
    }
    boards.add(new BingoBoard(board));
    boolean lastBingo = false;
    for (int pick : numbers) {
      for (BingoBoard brd : boards) {
        if (brd.draw(pick) && lastBingo) {
          return brd.calculateScore(pick);
        }
      }
      if (boards.stream().filter(brd -> brd.notBingo()).count() == 1) {
        lastBingo = true;
        boards = boards.stream().filter(brd -> brd.notBingo()).collect(Collectors.toList());
      }
    }
    return 0L;
  }
}
