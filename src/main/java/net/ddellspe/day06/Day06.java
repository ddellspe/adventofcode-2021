package net.ddellspe.day06;

import java.util.Arrays;
import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day06 {
  private Day06() {}

  public static long part1(String filename) {
    List<Integer> data = InputUtils.numbersInOneLine(filename, Day06.class);
    int[] dataList = new int[9];
    for (Integer fish : data) {
      dataList[fish]++;
    }
    for (int i = 0; i < 80; i++) {
      int[] newList = new int[9];
      for (int j = 0; j < 9; j++) {
        newList[j] = dataList[(j + 1) % 9];
      }
      newList[6] += dataList[0];
      System.arraycopy(newList, 0, dataList, 0, 9);
    }
    return Arrays.stream(dataList).sum();
  }

  public static long part2(String filename) {
    List<Integer> data = InputUtils.numbersInOneLine(filename, Day06.class);
    long[] dataList = new long[9];
    for (Integer fish : data) {
      dataList[fish]++;
    }
    for (int i = 0; i < 256; i++) {
      long[] newList = new long[9];
      for (int j = 0; j < 9; j++) {
        newList[j] = dataList[(j + 1) % 9];
      }
      newList[6] += dataList[0];
      System.arraycopy(newList, 0, dataList, 0, 9);
    }
    return Arrays.stream(dataList).sum();
  }
}
