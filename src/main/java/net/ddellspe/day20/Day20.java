package net.ddellspe.day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day20 {
  private Day20() {}

  public static void print(Set<Point> points, int it, String defaultValue) {
    int xMin = points.stream().mapToInt(Point::getX).min().getAsInt();
    int xMax = points.stream().mapToInt(Point::getX).max().getAsInt();
    int yMin = points.stream().mapToInt(Point::getY).min().getAsInt();
    int yMax = points.stream().mapToInt(Point::getY).max().getAsInt();
    System.out.println("Iteration " + it);
    for (int y = yMin - 3; y <= yMax + 3; y++) {
      StringBuilder line = new StringBuilder();
      if (y == yMin - 3 || y == yMax + 3) {
        line.append("+");
        line.append("-".repeat(Math.max(0, xMax - xMin + 7)));
        line.append("+");
      } else {
        line.append("|");
        for (int x = xMin - 3; x <= xMax + 3; x++) {
          if (x < xMin || x > xMax || y < yMin || y > yMax) {
            line.append(defaultValue.equals("1") ? "#" : ".");
          } else if (points.contains(new Point(x, y))) {
            line.append("#");
          } else {
            line.append(".");
          }
        }
        line.append("|");
      }
      System.out.println(line);
    }
  }

  public static long part1(String filename, boolean debug) {
    List<String> data = InputUtils.stringPerLine(filename, Day20.class);
    String algorithm = data.get(0).replaceAll("\\.", "0").replaceAll("#", "1");
    data = data.subList(2, data.size());
    Set<Point> points = new HashSet<>();
    for (int y = 0; y < data.size(); y++) {
      for (int x = 0; x < data.get(y).length(); x++) {
        if (data.get(y).charAt(x) == '#') {
          points.add(new Point(x, y));
        }
      }
    }
    String defaultValue = "0";
    if (debug) {
      print(points, 0, defaultValue);
    }
    for (int i = 0; i < 2; i++) {
      int xMin = points.stream().mapToInt(Point::getX).min().getAsInt();
      int xMax = points.stream().mapToInt(Point::getX).max().getAsInt();
      int yMin = points.stream().mapToInt(Point::getY).min().getAsInt();
      int yMax = points.stream().mapToInt(Point::getY).max().getAsInt();
      Set<Point> newPoints = new HashSet<>();
      for (int y = yMin - 1; y <= yMax + 1; y++) {
        for (int x = xMin - 1; x <= xMax + 1; x++) {
          String point = "";
          for (int ys = y - 1; ys <= y + 1; ys++) {
            for (int xs = x - 1; xs <= x + 1; xs++) {
              if (xs < xMin || xs > xMax || ys < yMin || ys > yMax) {
                point += defaultValue;
              } else if (points.contains(new Point(xs, ys))) {
                point += "1";
              } else {
                point += "0";
              }
            }
          }
          int position = Integer.parseInt(point, 2);
          if (algorithm.charAt(position) == '1') {
            newPoints.add(new Point(x, y));
          }
        }
      }
      points.clear();
      points.addAll(newPoints);
      String defaultVal = defaultValue;
      defaultValue =
          String.valueOf(
              algorithm.charAt(
                  Integer.parseInt(
                      IntStream.range(0, 9).mapToObj(v -> defaultVal).reduce(String::concat).get(),
                      2)));
      if (debug) {
        print(points, i + 1, defaultValue);
      }
    }
    return points.size();
  }

  public static long part2(String filename, boolean debug) {
    List<String> data = InputUtils.stringPerLine(filename, Day20.class);
    String algorithm = data.get(0).replaceAll("\\.", "0").replaceAll("#", "1");
    data = data.subList(2, data.size());
    Set<Point> points = new HashSet<>();
    for (int y = 0; y < data.size(); y++) {
      for (int x = 0; x < data.get(y).length(); x++) {
        if (data.get(y).charAt(x) == '#') {
          points.add(new Point(x, y));
        }
      }
    }
    String defaultValue = "0";
    if (debug) {
      print(points, 0, defaultValue);
    }
    for (int i = 0; i < 50; i++) {
      int xMin = points.stream().mapToInt(Point::getX).min().getAsInt();
      int xMax = points.stream().mapToInt(Point::getX).max().getAsInt();
      int yMin = points.stream().mapToInt(Point::getY).min().getAsInt();
      int yMax = points.stream().mapToInt(Point::getY).max().getAsInt();
      Set<Point> newPoints = new HashSet<>();
      for (int y = yMin - 1; y <= yMax + 1; y++) {
        for (int x = xMin - 1; x <= xMax + 1; x++) {
          String point = "";
          for (int ys = y - 1; ys <= y + 1; ys++) {
            for (int xs = x - 1; xs <= x + 1; xs++) {
              if (xs < xMin || xs > xMax || ys < yMin || ys > yMax) {
                point += defaultValue;
              } else if (points.contains(new Point(xs, ys))) {
                point += "1";
              } else {
                point += "0";
              }
            }
          }
          int position = Integer.parseInt(point, 2);
          if (algorithm.charAt(position) == '1') {
            newPoints.add(new Point(x, y));
          }
        }
      }
      points.clear();
      points.addAll(newPoints);
      String defaultVal = defaultValue;
      defaultValue =
          String.valueOf(
              algorithm.charAt(
                  Integer.parseInt(
                      IntStream.range(0, 9).mapToObj(v -> defaultVal).reduce(String::concat).get(),
                      2)));
      if (debug) {
        print(points, i + 1, defaultValue);
      }
    }
    return points.size();
  }
}
