package net.ddellspe.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;

public class Day04 {
  public static boolean isBingo(Integer[] board) {
    for (int num = 0; num < 5; num++) {
      if (board[num * 5]
                  + board[num * 5 + 1]
                  + board[num * 5 + 2]
                  + board[num * 5 + 3]
                  + board[num * 5 + 4]
              == -5
          || board[num] + board[num + 5] + board[num + 10] + board[num + 15] + board[num + 20]
              == -5) {
        return true;
      }
    }
    return false;
  }

  public static long calculateScore(Integer[] board, int pick) {
    return Arrays.stream(board).filter(val -> val != -1).mapToLong(val -> val).sum() * pick;
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day04.class);
    List<Integer> numbers =
        Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    List<Integer[]> boards = new ArrayList<>();
    StringBuilder board = new StringBuilder();
    for (String line : data) {
      if (line.equals(data.get(0))) {
        board = new StringBuilder();
        continue;
      }
      if (line.isBlank()) {
        if (board.length() != 0) {
          boards.add(
              Arrays.stream(board.toString().stripLeading().split("[ ]+"))
                  .map(Integer::parseInt)
                  .toArray(Integer[]::new));
          board = new StringBuilder();
        }
        continue;
      }
      board.append(" ").append(line);
    }
    boards.add(
        Arrays.stream(board.toString().stripLeading().split("[ ]+"))
            .map(Integer::parseInt)
            .toArray(Integer[]::new));
    for (final int pick : numbers) {
      List<Integer[]> newBoards = new ArrayList<>();
      for (Integer[] brd : boards) {
        Integer[] newBrd =
            Arrays.stream(brd)
                .map(val -> val == pick ? -1 : val)
                .collect(Collectors.toList())
                .toArray(Integer[]::new);
        if (isBingo(newBrd)) {
          return calculateScore(newBrd, pick);
        }
        newBoards.add(newBrd);
      }
      boards.clear();
      boards.addAll(newBoards);
    }
    // this should not be reached, but might be if input is bad or wrong
    return 0L;
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day04.class);
    List<Integer> numbers =
        Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    List<Integer[]> boards = new ArrayList<>();
    StringBuilder board = new StringBuilder();
    for (String line : data) {
      if (line.equals(data.get(0))) {
        board = new StringBuilder();
        continue;
      }
      if (line.isBlank()) {
        if (board.length() != 0) {
          boards.add(
              Arrays.stream(board.toString().stripLeading().split("[ ]+"))
                  .map(Integer::parseInt)
                  .toArray(Integer[]::new));
          board = new StringBuilder();
        }
        continue;
      }
      board.append(" ").append(line);
    }
    boards.add(
        Arrays.stream(board.toString().stripLeading().split("[ ]+"))
            .map(Integer::parseInt)
            .toArray(Integer[]::new));
    boolean lastBingo = false;
    for (int pick : numbers) {
      List<Integer[]> newBoards = new ArrayList<>();
      for (Integer[] brd : boards) {
        Integer[] newBrd =
            Arrays.stream(brd)
                .map(val -> val == pick ? -1 : val)
                .collect(Collectors.toList())
                .toArray(Integer[]::new);
        newBoards.add(newBrd);
        if (!isBingo(brd) && isBingo(newBrd) && lastBingo) {
          return calculateScore(newBrd, pick);
        }
      }
      boards.clear();
      boards.addAll(newBoards);
      if (boards.stream().filter(Day04::isBingo).count() == boards.size() - 1) {
        lastBingo = true;
      }
    }
    // this should not be reached, but might be if input is bad or wrong
    return 0L;
  }
}
