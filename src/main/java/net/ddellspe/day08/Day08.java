package net.ddellspe.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day08 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day08.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
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

  public static Map<String, String> determineMapping(Map<Integer, Set<String>> input) {
    Map<String, String> mappings = new HashMap<>();
    String one = input.get(2).stream().findFirst().get();
    mappings.put(one, "1");
    String seven = input.get(3).stream().findFirst().get();
    mappings.put(seven, "7");
    String four = input.get(4).stream().findFirst().get();
    mappings.put(four, "4");
    String eight = input.get(7).stream().findFirst().get();
    mappings.put(eight, "8");
    Set<String> pending = input.get(5);
    pending.addAll(input.get(6));
    String six =
        pending.stream()
            .filter(val -> val.length() == 6)
            .filter(
                val ->
                    eight.chars().filter(c -> val.indexOf((char) c) == -1).count() == 1
                        && one.chars().filter(c -> val.indexOf((char) c) == -1).count() == 1L)
            .findFirst()
            .get();
    pending.remove(six);
    mappings.put(six, "6");
    String five =
        pending.stream()
            .filter(val -> val.length() == 5)
            .filter(val -> six.chars().filter(c -> val.indexOf((char) c) == -1).count() == 1L)
            .findFirst()
            .get();
    pending.remove(five);
    mappings.put(five, "5");
    String nine =
        pending.stream()
            .filter(val -> val.length() == 6)
            .filter(
                val ->
                    eight.chars().filter(c -> val.indexOf((char) c) == -1).findFirst().getAsInt()
                        == six.chars()
                            .filter(c -> five.indexOf((char) c) == -1)
                            .findFirst()
                            .getAsInt())
            .findFirst()
            .get();
    pending.remove(nine);
    mappings.put(nine, "9");
    String zero = pending.stream().filter(val -> val.length() == 6).findFirst().get();
    pending.remove(zero);
    mappings.put(zero, "0");
    String three =
        pending.stream()
            .filter(val -> val.chars().filter(c -> one.indexOf((char) c) == -1).count() == 3L)
            .findFirst()
            .get();
    pending.remove(three);
    mappings.put(three, "3");
    String two = pending.stream().findFirst().get();
    mappings.put(two, "2");
    return mappings;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    List<List<String>> inputValues =
        data.stream()
            .map(
                line ->
                    Arrays.stream(line.split(" \\| ")[0].split("[ ]+"))
                        .map(
                            str -> {
                              char[] chars = str.toCharArray();
                              Arrays.sort(chars);
                              return new String(chars);
                            })
                        .collect(Collectors.toList()))
            .collect(Collectors.toList());
    List<List<String>> outputValues =
        data.stream()
            .map(
                line ->
                    Arrays.stream(line.split(" \\| ")[1].split("[ ]+"))
                        .map(
                            str -> {
                              char[] chars = str.toCharArray();
                              Arrays.sort(chars);
                              return new String(chars);
                            })
                        .collect(Collectors.toList()))
            .collect(Collectors.toList());
    long sum = 0;
    for (int i = 0; i < inputValues.size(); i++) {
      Map<Integer, Set<String>> map = new HashMap<>();
      for (String letter : inputValues.get(i)) {
        if (map.get(letter.length()) != null) {
          map.get(letter.length()).add(letter);
        } else {
          map.put(
              letter.length(),
              new HashSet<String>() {
                {
                  add(letter);
                }
              });
        }
      }
      Map<String, String> mappings = determineMapping(map);
      StringBuilder value = new StringBuilder();
      for (String outValue : outputValues.get(i)) {
        value.append(mappings.get(outValue));
      }
      sum += Integer.parseInt(value.toString());
    }
    return sum;
  }
}
