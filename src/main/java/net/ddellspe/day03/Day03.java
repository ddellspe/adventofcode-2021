package net.ddellspe.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day03 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day03.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    StringBuilder gammaBit = new StringBuilder();
    StringBuilder epsilonBit = new StringBuilder();
    for (int i = 0; i < data.get(0).length(); i++) {
      int cnt = 0;
      for (String str : data) {
        if (str.charAt(i) == '1') {
          cnt++;
        }
      }
      gammaBit.append(cnt > (data.size() - cnt) ? "1" : "0");
      epsilonBit.append(cnt > (data.size() - cnt) ? "0" : "1");
    }
    return Long.parseLong(gammaBit.toString(), 2) * Long.parseLong(epsilonBit.toString(), 2);
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    List<String> oxygenData = new ArrayList<>(data);
    List<String> co2Data = new ArrayList<>(data);
    for (int i = 0; i < data.get(0).length(); i++) {
      int cnt = 0;
      if (oxygenData.size() == 1) {
        break;
      }
      for (String str : oxygenData) {
        if (str.charAt(i) == '1') {
          cnt++;
        }
      }
      int finalI = i;
      if (cnt >= (oxygenData.size() - cnt)) {
        oxygenData =
            oxygenData.stream()
                .filter(str -> str.charAt(finalI) == '1')
                .collect(Collectors.toList());
      } else {
        oxygenData =
            oxygenData.stream()
                .filter(str -> str.charAt(finalI) == '0')
                .collect(Collectors.toList());
      }
    }
    for (int i = 0; i < data.get(0).length(); i++) {
      int cnt = 0;
      if (co2Data.size() == 1) {
        break;
      }
      for (String str : co2Data) {
        if (str.charAt(i) == '1') {
          cnt++;
        }
      }
      int finalI2 = i;
      if (cnt >= (co2Data.size() - cnt)) {
        co2Data =
            co2Data.stream().filter(str -> str.charAt(finalI2) == '0').collect(Collectors.toList());
      } else {
        co2Data =
            co2Data.stream().filter(str -> str.charAt(finalI2) == '1').collect(Collectors.toList());
      }
    }
    return Long.parseLong(oxygenData.get(0), 2) * Long.parseLong(co2Data.get(0), 2);
  }
}
