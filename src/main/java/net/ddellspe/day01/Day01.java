package net.ddellspe.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day01 {
  public static List<Integer> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day01.class.getResourceAsStream(filename))))) {
      return reader.lines().map(Integer::parseInt).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static int part1(String filename) {
    int count = 0;
    List<Integer> data = readInData(filename);
    int prev = data.get(0);
    for (int i = 1; i < data.size(); i++) {
      if (data.get(i) > prev) {
        count++;
      }
      prev = data.get(i);
    }
    return count;
  }

  public static int part2(String filename) {
    int count = 0;
    List<Integer> data = readInData(filename);
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
}
