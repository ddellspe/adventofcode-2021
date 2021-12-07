package net.ddellspe.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day07 {
  public static List<Integer> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day07.class.getResourceAsStream(filename))))) {
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
    List<Integer> data = readInData(filename);
    long max = data.stream().max(Integer::compare).get();
    List<Long> moveCost = new ArrayList<>();
    long sum = 0;
    for (int i = 0; i < max + 1; i++) {
      sum += i;
      moveCost.add(sum);
    }
    long minPos = Long.MAX_VALUE;
    for (int i = 0; i < max; i++) {
      final int check = i;
      long pos = data.stream().mapToLong(val -> moveCost.get(Math.abs(val - check))).sum();
      if (pos < minPos) {
        minPos = pos;
      }
    }
    return minPos;
  }
}
