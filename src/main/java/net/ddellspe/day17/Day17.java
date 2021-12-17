package net.ddellspe.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import net.ddellspe.utils.Point;

public class Day17 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day17.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long hitsTarget(int xInit, int yInit, int xMin, int xMax, int yMin, int yMax) {
    boolean passedXMin = false;
    boolean passedXMax = false;
    boolean passedYMin = false;
    boolean passedYMax = false;
    boolean inTarget = false;
    int x = 0;
    int y = 0;
    long maxY = 0;
    do {
      maxY = Math.max(y, maxY);
      passedXMax = passedXMax || x > xMax;
      passedXMin = passedXMin || x > xMin;
      passedYMin = y < yMin;
      passedYMax = passedYMax || y <= yMax;
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
    List<String> data = readInData(filename);
    String input = data.get(0).split(": ")[1];
    String xStr = input.split(", ")[0].split("=")[1];
    String yStr = input.split(", ")[1].split("=")[1];
    Map<Point, Long> workingVelocities = new HashMap<>();
    int xMin = Integer.parseInt(xStr.split("\\.\\.")[0]);
    int xMax = Integer.parseInt(xStr.split("\\.\\.")[1]);
    int yMin = Integer.parseInt(yStr.split("\\.\\.")[0]);
    int yMax = Integer.parseInt(yStr.split("\\.\\.")[1]);
    for (int initialX = 0; initialX <= xMax; initialX++) {
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
    List<String> data = readInData(filename);
    String input = data.get(0).split(": ")[1];
    String xStr = input.split(", ")[0].split("=")[1];
    String yStr = input.split(", ")[1].split("=")[1];
    Map<Point, Long> workingVelocities = new HashMap<>();
    int xMin = Integer.parseInt(xStr.split("\\.\\.")[0]);
    int xMax = Integer.parseInt(xStr.split("\\.\\.")[1]);
    int yMin = Integer.parseInt(yStr.split("\\.\\.")[0]);
    int yMax = Integer.parseInt(yStr.split("\\.\\.")[1]);
    for (int initialX = 0; initialX <= xMax; initialX++) {
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
