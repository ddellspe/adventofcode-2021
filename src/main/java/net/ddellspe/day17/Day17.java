package net.ddellspe.day17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day17 {
  public static long hitsTarget(int xInit, int yInit, int xMin, int xMax, int yMin, int yMax) {
    boolean passedYMin;
    boolean inTarget;
    int x = 0;
    int y = 0;
    long maxY = 0;
    do {
      maxY = Math.max(y, maxY);
      passedYMin = y < yMin;
      inTarget = x >= xMin && x <= xMax && y >= yMin && y <= yMax;
      x += xInit;
      y += yInit;
      xInit = xInit == 0 ? xInit : xInit - 1;
      yInit--;
    } while (!(passedYMin || inTarget));
    if (inTarget) {
      return maxY;
    } else {
      return Long.MIN_VALUE;
    }
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day17.class);
    String input = data.get(0).split(": ")[1];
    String xStr = input.split(", ")[0].split("=")[1];
    String yStr = input.split(", ")[1].split("=")[1];
    Map<Point, Long> workingVelocities = new HashMap<>();
    int xMin = Integer.parseInt(xStr.split("\\.\\.")[0]);
    int xMax = Integer.parseInt(xStr.split("\\.\\.")[1]);
    int yMin = Integer.parseInt(yStr.split("\\.\\.")[0]);
    int yMax = Integer.parseInt(yStr.split("\\.\\.")[1]);
    for (int initialX = (int) Math.sqrt(xMin * 2); initialX <= xMax; initialX++) {
      for (int initialY = yMin; initialY < yMin * -1; initialY++) {
        long hit = hitsTarget(initialX, initialY, xMin, xMax, yMin, yMax);
        if (hit > Integer.MIN_VALUE) {
          workingVelocities.put(new Point(initialX, initialY), hit);
        }
      }
    }
    return workingVelocities.entrySet().stream().mapToLong(Entry::getValue).max().getAsLong();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day17.class);
    String input = data.get(0).split(": ")[1];
    String xStr = input.split(", ")[0].split("=")[1];
    String yStr = input.split(", ")[1].split("=")[1];
    Map<Point, Long> workingVelocities = new HashMap<>();
    int xMin = Integer.parseInt(xStr.split("\\.\\.")[0]);
    int xMax = Integer.parseInt(xStr.split("\\.\\.")[1]);
    int yMin = Integer.parseInt(yStr.split("\\.\\.")[0]);
    int yMax = Integer.parseInt(yStr.split("\\.\\.")[1]);
    for (int initialX = (int) Math.sqrt(xMin * 2); initialX <= xMax; initialX++) {
      for (int initialY = yMin; initialY < yMin * -1; initialY++) {
        long hit = hitsTarget(initialX, initialY, xMin, xMax, yMin, yMax);
        if (hit > Integer.MIN_VALUE) {
          workingVelocities.put(new Point(initialX, initialY), hit);
        }
      }
    }
    return workingVelocities.entrySet().stream().count();
  }
}
