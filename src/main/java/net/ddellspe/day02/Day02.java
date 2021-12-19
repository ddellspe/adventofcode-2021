package net.ddellspe.day02;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day02 {
  public static long part1(String filename) {
    List<String> movements = InputUtils.stringPerLine(filename, Day02.class);
    long hPos = 0;
    long depth = 0;
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
    List<String> movements = InputUtils.stringPerLine(filename, Day02.class);
    long hPos = 0;
    long depth = 0;
    long aim = 0;
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
