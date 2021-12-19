package net.ddellspe.day07;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day07 {
  private Day07() {}

  public static long part1(String filename) {
    List<Integer> data = InputUtils.numbersInOneLine(filename, Day07.class);
    long max = data.stream().max(Integer::compare).get();
    long minPos = Long.MAX_VALUE;
    for (int i = 0; i < max; i++) {
      final int check = i;
      long pos = data.stream().mapToLong(val -> Math.abs(val - check)).sum();
      if (pos < minPos) {
        minPos = pos;
      }
    }
    return minPos;
  }

  public static long part2(String filename) {
    List<Integer> data = InputUtils.numbersInOneLine(filename, Day07.class);
    long max = data.stream().max(Integer::compare).get();
    long minPos = Long.MAX_VALUE;
    for (int i = 0; i < max; i++) {
      final int check = i;
      long pos =
          data.stream()
              .mapToLong(val -> Math.abs(val - check))
              .map(val -> val * (val + 1) / 2)
              .sum();
      if (pos < minPos) {
        minPos = pos;
      }
    }
    return minPos;
  }
}
