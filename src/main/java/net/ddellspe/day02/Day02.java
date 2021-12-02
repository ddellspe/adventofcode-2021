package net.ddellspe.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day02 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day02.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    long hPos = 0;
    long depth = 0;
    List<String> movements = readInData(filename);
    for (String movement : movements) {
      String action = movement.split(" ")[0];
      int amt = Integer.parseInt(movement.split(" ")[1]);
      switch (action) {
        case "forward":
          hPos += amt;
          break;
        case "up":
          depth -= amt;
          break;
        case "down":
          depth += amt;
          break;
      }
    }
    return hPos * depth;
  }

  public static long part2(String filename) {
    long hPos = 0;
    long depth = 0;
    long aim = 0;
    List<String> movements = readInData(filename);
    for (String movement : movements) {
      String action = movement.split(" ")[0];
      int amt = Integer.parseInt(movement.split(" ")[1]);
      switch (action) {
        case "forward":
          hPos += amt;
          depth += (aim * amt);
          break;
        case "up":
          aim -= amt;
          break;
        case "down":
          aim += amt;
          break;
      }
    }
    return hPos * depth;
  }
}
