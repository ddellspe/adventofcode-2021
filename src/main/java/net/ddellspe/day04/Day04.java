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
  int boardNum;

  public BingoBoard(String board, int num) {
    this.board = new int[25];
    this.selectedBoard = new boolean[25];
    this.boardNum = num;
    int ind = 0;
    for (String val : board.stripLeading().split("[ ]+")) {
      this.board[ind] = Integer.parseInt(val);
      ind++;
    }
  }

  public boolean checkBingo() {
    for (int num = 0; num < 5; num++) {
      if ((selectedBoard[num * 5]
              && selectedBoard[num * 5 + 1]
              && selectedBoard[num * 5 + 2]
              && selectedBoard[num * 5 + 3]
              && selectedBoard[num * 5 + 4])
          || (selectedBoard[num]
              && selectedBoard[num + 5]
              && selectedBoard[num + 10]
              && selectedBoard[num + 15]
              && selectedBoard[num + 20])) {
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
    long sum = 0L;
    for (int i = 0; i < 25; i++) {
      if (!selectedBoard[i]) {
        sum += board[i];
      }
    }
    return sum * pick;
  }

  public void printBoard() {
    System.out.printf("Board #%d\n", boardNum);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        char ind = this.selectedBoard[i * 5 + j] ? '*' : ' ';
        System.out.printf("%1c%2d%1c ", ind, this.board[i * 5 + j], ind);
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
    StringBuilder board = new StringBuilder();
    int boardNum = 1;
    for (String line : data) {
      if (line.equals(data.get(0))) {
        board = new StringBuilder();
        continue;
      }
      if (line.isBlank() && board.length() == 0) {
        continue;
      }
      if (line.isBlank() && board.length() > 0) {
        boards.add(new BingoBoard(board.toString(), boardNum++));
        board = new StringBuilder();
        continue;
      }
      board.append(" ").append(line);
    }
    boards.add(new BingoBoard(board.toString(), boardNum));
    for (int pick : numbers) {
      for (BingoBoard brd : boards) {
        if (brd.draw(pick)) {
          return brd.calculateScore(pick);
        }
      }
    }
    return 0L;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    List<Integer> numbers =
        Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    List<BingoBoard> boards = new ArrayList<>();
    StringBuilder board = new StringBuilder();
    int boardNum = 1;
    for (String line : data) {
      if (line.equals(data.get(0))) {
        board = new StringBuilder();
        continue;
      }
      if (line.isBlank() && board.length() == 0) {
        continue;
      }
      if (line.isBlank() && board.length() > 0) {
        boards.add(new BingoBoard(board.toString(), boardNum++));
        board = new StringBuilder();
        continue;
      }
      board.append(" ").append(line);
    }
    boolean lastBingo = false;
    for (int pick : numbers) {
      for (BingoBoard brd : boards) {
        if (brd.notBingo() && brd.draw(pick) && lastBingo) {
          return brd.calculateScore(pick);
        }
      }
      if (boards.stream().filter(BingoBoard::notBingo).count() == 1) {
        lastBingo = true;
      }
    }
    return 0L;
  }
}
