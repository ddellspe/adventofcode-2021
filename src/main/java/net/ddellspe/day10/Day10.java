package net.ddellspe.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day10 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day10.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    Map<String, Long> points =
        new HashMap<>() {
          {
            put(")", 3L);
            put("]", 57L);
            put("}", 1197L);
            put(">", 25137L);
          }
        };
    Map<String, String> closings =
        new HashMap<>() {
          {
            put("(", ")");
            put("{", "}");
            put("[", "]");
            put("<", ">");
          }
        };
    String closers = "]})>";
    long sum = 0;
    for (String line : data) {
      Stack<String> openings = new Stack<>();
      for (String item : line.split("")) {
        if (closers.contains(item)) {
          if (item.equals(closings.get(openings.peek()))) {
            openings.pop();
          } else {
            sum += points.get(item);
            break;
          }
        } else {
          openings.add(item);
        }
      }
    }
    return sum;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    Map<String, Long> points =
        new HashMap<>() {
          {
            put(")", 1L);
            put("]", 2L);
            put("}", 3L);
            put(">", 4L);
          }
        };
    Map<String, String> closings =
        new HashMap<>() {
          {
            put("(", ")");
            put("{", "}");
            put("[", "]");
            put("<", ">");
          }
        };
    String closers = "]})>";
    List<Long> sums = new ArrayList<>();
    for (String line : data) {
      Stack<String> openings = new Stack<>();
      boolean invalid = false;
      for (String item : line.split("")) {
        if (closers.contains(item)) {
          if (item.equals(closings.get(openings.peek()))) {
            openings.pop();
          } else {
            invalid = true;
            break;
          }
        } else {
          openings.add(item);
        }
      }
      if (!invalid) {
        long lineSum = 0L;
        while (!openings.isEmpty()) {
          lineSum = lineSum * 5L + points.get(closings.get(openings.pop()));
        }
        sums.add(lineSum);
      }
    }
    return sums.stream().sorted().collect(Collectors.toList()).get(sums.size() / 2);
  }
}
