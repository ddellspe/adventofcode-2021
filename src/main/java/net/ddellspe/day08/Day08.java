package net.ddellspe.day08;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;

public class Day08 {
  private Day08() {}

  public static List<String> determineMapping(List<String> input) {
    Map<Integer, List<String>> data = input.stream().collect(Collectors.groupingBy(String::length));
    String[] mappings = new String[10];
    mappings[1] = data.get(2).get(0);
    mappings[7] = data.get(3).get(0);
    mappings[4] = data.get(4).get(0);
    mappings[8] = data.get(7).get(0);
    List<String> pendingFives = data.get(5);
    List<String> pendingSixes = data.get(6);
    mappings[6] =
        pendingSixes.stream()
            .filter(
                val ->
                    mappings[8].chars().filter(c -> val.indexOf((char) c) == -1).count() == 1
                        && mappings[1].chars().filter(c -> val.indexOf((char) c) == -1).count()
                            == 1L)
            .findFirst()
            .get();
    pendingSixes.remove(mappings[6]);
    mappings[3] =
        pendingFives.stream()
            .filter(
                val -> val.chars().filter(c -> mappings[1].indexOf((char) c) == -1).count() == 3L)
            .findFirst()
            .get();
    pendingFives.remove(mappings[3]);
    mappings[5] =
        pendingFives.stream()
            .filter(
                val -> mappings[6].chars().filter(c -> val.indexOf((char) c) == -1).count() == 1L)
            .findFirst()
            .get();
    pendingFives.remove(mappings[5]);
    mappings[9] =
        pendingSixes.stream()
            .filter(
                val ->
                    mappings[8]
                            .chars()
                            .filter(c -> val.indexOf((char) c) == -1)
                            .findFirst()
                            .getAsInt()
                        == mappings[6]
                            .chars()
                            .filter(c -> mappings[5].indexOf((char) c) == -1)
                            .findFirst()
                            .getAsInt())
            .findFirst()
            .get();
    pendingSixes.remove(mappings[9]);
    mappings[0] = pendingSixes.get(0);
    mappings[2] = pendingFives.get(0);
    return Arrays.stream(mappings).collect(Collectors.toList());
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day08.class);
    return data.stream()
        .map(line -> line.split(" \\| ")[1])
        .mapToLong(
            outputs ->
                Arrays.stream(outputs.split("[ ]+"))
                    .filter(
                        digit ->
                            digit.length() == 2
                                || digit.length() == 7
                                || digit.length() == 3
                                || digit.length() == 4)
                    .count())
        .sum();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day08.class);
    return Objects.requireNonNull(data).stream()
        .map(
            line ->
                Arrays.stream(line.split(" \\| "))
                    .map(
                        letters ->
                            Arrays.stream(letters.split("[ ]+"))
                                .map(
                                    letter -> {
                                      char[] chars = letter.toCharArray();
                                      Arrays.sort(chars);
                                      return new String(chars);
                                    })
                                .collect(Collectors.toList()))
                    .collect(Collectors.toList()))
        .mapToLong(
            dataRow -> {
              List<String> mappings = determineMapping(dataRow.get(0));
              return Integer.parseInt(
                  dataRow.get(1).stream()
                      .map(val -> String.valueOf(mappings.indexOf(val)))
                      .reduce((curr, prev) -> curr + prev)
                      .get());
            })
        .sum();
  }
}
