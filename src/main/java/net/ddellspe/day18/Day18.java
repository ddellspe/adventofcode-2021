package net.ddellspe.day18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day18 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day18.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  static Pattern PAIR_PATTERN = Pattern.compile("\\[(\\d+),(\\d+)\\]");
  static Pattern LEFT_NUMBER_PATTERN = Pattern.compile("\\d+(?!.*\\d)");
  static Pattern GREATER_THAN_10_PATTERN = Pattern.compile("\\d\\d+");
  static Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");

  public static String reduce(String input) {
    while (true) {
      Matcher pairMatch = PAIR_PATTERN.matcher(input);
      boolean continueSearch = false;
      while (pairMatch.find()) {
        String preMatch = input.substring(0, pairMatch.start());
        String postMatch = input.substring(pairMatch.end());
        if (Arrays.stream(preMatch.split("")).filter(v -> v.equals("[")).count()
                - Arrays.stream(preMatch.split("")).filter(v -> v.equals("]")).count()
            >= 4) {
          Matcher leftNumMatcher = LEFT_NUMBER_PATTERN.matcher(preMatch);
          String start = preMatch;
          if (leftNumMatcher.find()) {
            String leftNum =
                String.valueOf(
                    Integer.parseInt(leftNumMatcher.group(0))
                        + Integer.parseInt(pairMatch.group(1)));
            start = leftNumMatcher.replaceFirst(leftNum);
          }
          Matcher rightNumMatcher = NUMBER_PATTERN.matcher(postMatch);
          String end = postMatch;
          if (rightNumMatcher.find()) {
            String rightNum =
                String.valueOf(
                    Integer.parseInt(rightNumMatcher.group(0))
                        + Integer.parseInt(pairMatch.group(2)));
            end = rightNumMatcher.replaceFirst(rightNum);
          }
          input = start + "0" + end;
          continueSearch = true;
          break;
        }
      }
      if (continueSearch) {
        continue;
      }
      Matcher greaterThan10Match = GREATER_THAN_10_PATTERN.matcher(input);
      if (greaterThan10Match.find()) {
        int value = Integer.parseInt(greaterThan10Match.group());
        String inner =
            "[" + (int) Math.floor(value / 2.0) + "," + (int) Math.ceil(value / 2.0) + "]";
        input = input.replaceFirst("\\d\\d+", inner);
        continue;
      }
      return input;
    }
  }

  public static long sum(String input) {
    Matcher pairMatch = PAIR_PATTERN.matcher(input);
    while (pairMatch.find()) {
      String start = input.substring(0, pairMatch.start());
      String sum =
          String.valueOf(
              Long.parseLong(pairMatch.group(1)) * 3L + Long.parseLong(pairMatch.group(2)) * 2L);
      String end = input.substring(pairMatch.end());
      input = start + sum + end;
      pairMatch = PAIR_PATTERN.matcher(input);
    }
    return Long.parseLong(input);
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    String snailfishStr = "";
    for (String line : data) {
      if (snailfishStr.isEmpty()) {
        snailfishStr = line;
      } else {
        snailfishStr = "[" + snailfishStr + "," + line + "]";
      }
      snailfishStr = reduce(snailfishStr);
    }
    return sum(snailfishStr);
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    List<Long> sums = new ArrayList<>();
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.size(); j++) {
        if (i == j) {
          continue;
        }
        sums.add(sum(reduce("[" + data.get(i) + "," + data.get(j) + "]")));
      }
    }
    return sums.stream().max(Long::compareTo).get();
  }
}
