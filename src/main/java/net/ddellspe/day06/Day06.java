package net.ddellspe.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day06 {
  public static List<Integer> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day06.class.getResourceAsStream(filename))))) {
      return Arrays.stream(reader.lines().findFirst().get().split(","))
          .map(Integer::parseInt)
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<Integer> data = readInData(filename);
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
    List<Integer> data = readInData(filename);
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
