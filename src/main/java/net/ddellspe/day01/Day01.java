package net.ddellspe.day01;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import net.ddellspe.utils.InputUtils;

public class Day01 {
  private Day01() {}

  public static long part1(String filename) {
    int count = 0;
    List<Integer> data = InputUtils.numberPerLine(filename, Day01.class);
    int prev = data.get(0);
    for (int i = 1; i < data.size(); i++) {
      if (data.get(i) > prev) {
        count++;
      }
      prev = data.get(i);
    }
    return count;
  }

  public static long part2(String filename) {
    int count = 0;
    List<Integer> data = InputUtils.numberPerLine(filename, Day01.class);
    int n1 = data.get(0);
    int n2 = data.get(1);
    int n3 = data.get(2);
    int prev = n1 + n2 + n3;
    for (int i = 3; i < data.size(); i++) {
      n1 = n2;
      n2 = n3;
      n3 = data.get(i);
      if (n1 + n2 + n3 > prev) {
        count++;
      }
      prev = n1 + n2 + n3;
    }
    return count;
  }

  public static long part1Optimized(String filename) {
    List<Integer> data = InputUtils.numberPerLine(filename, Day01.class);
    return IntStream.range(1, Objects.requireNonNull(data).size())
        .filter(index -> (data.get(index) - data.get(index - 1)) > 0)
        .count();
  }

  public static long part2Optimized(String filename) {
    List<Integer> data = InputUtils.numberPerLine(filename, Day01.class);
    /*
     * index     0   1   2   3
     *          100 105 106 108
     * sum 1    |_________|
     * sum 2        |_________|
     * same values  =======
     * diff     xxx         xxx
     */
    return IntStream.range(3, Objects.requireNonNull(data).size())
        .filter(index -> (data.get(index) - data.get(index - 3)) > 0)
        .count();
  }
}
