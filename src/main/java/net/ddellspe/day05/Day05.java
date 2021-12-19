package net.ddellspe.day05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day05 {
  private Day05() {}

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day05.class);
    Map<Point, Integer> points = new HashMap<Point, Integer>();
    for (String instr : data) {
      int x1 = Integer.parseInt(instr.split(" -> ")[0].split(",")[0]);
      int y1 = Integer.parseInt(instr.split(" -> ")[0].split(",")[1]);
      int x2 = Integer.parseInt(instr.split(" -> ")[1].split(",")[0]);
      int y2 = Integer.parseInt(instr.split(" -> ")[1].split(",")[1]);
      if (x1 == x2 || y1 == y2) {
        int x = x1;
        int y = y1;
        for (int i = 0; i <= Math.max(Math.abs(y1 - y2), Math.abs(x1 - x2)); i++) {
          points.merge(new Point(x, y), 1, Integer::sum);
          if (x1 < x2) {
            x++;
          } else if (x1 > x2) {
            x--;
          }
          if (y1 < y2) {
            y++;
          } else if (y1 > y2) {
            y--;
          }
        }
      }
    }
    return points.values().stream().filter(val -> val >= 2).count();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day05.class);
    Map<Point, Integer> points = new HashMap<Point, Integer>();
    for (String instr : data) {
      int x1 = Integer.parseInt(instr.split(" -> ")[0].split(",")[0]);
      int y1 = Integer.parseInt(instr.split(" -> ")[0].split(",")[1]);
      int x2 = Integer.parseInt(instr.split(" -> ")[1].split(",")[0]);
      int y2 = Integer.parseInt(instr.split(" -> ")[1].split(",")[1]);
      int x = x1;
      int y = y1;
      for (int i = 0; i <= Math.max(Math.abs(y1 - y2), Math.abs(x1 - x2)); i++) {
        points.merge(new Point(x, y), 1, Integer::sum);
        if (x1 < x2) {
          x++;
        } else if (x1 > x2) {
          x--;
        }
        if (y1 < y2) {
          y++;
        } else if (y1 > y2) {
          y--;
        }
      }
    }
    return points.values().stream().filter(val -> val >= 2).count();
  }
}
