package net.ddellspe.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.Point;

public class Day13 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day13.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    Set<Point> points = new HashSet<>();
    List<String> folds = new ArrayList<>();
    boolean foldSection = false;
    for (String line : data) {
      if (line.isEmpty()) {
        foldSection = true;
        continue;
      }
      if (!foldSection) {
        Point pt =
            new Point(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1]));
        points.add(pt);
      } else {
        folds.add(line);
      }
    }
    for (String fold : folds.subList(0, 1)) {
      Set<Point> newPoints = new HashSet<>();
      if (fold.contains("x")) {
        int x = Integer.parseInt(fold.split("=")[1]);
        for (Point point : points) {
          if (point.getX() > x) {
            point.setX(2 * x - (point.getX()));
          }
          newPoints.add(point);
        }
      } else {
        int y = Integer.parseInt(fold.split("=")[1]);
        for (Point point : points) {
          if (point.getY() > y) {
            point.setY(2 * y - (point.getY()));
          }
          newPoints.add(point);
        }
      }
      points.clear();
      points.addAll(newPoints);
    }
    return points.size();
  }

  public static String part2(String filename) {
    List<String> data = readInData(filename);
    Set<Point> points = new HashSet<>();
    List<String> folds = new ArrayList<>();
    boolean foldSection = false;
    for (String line : data) {
      if (line.isEmpty()) {
        foldSection = true;
        continue;
      }
      if (!foldSection) {
        Point pt =
            new Point(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1]));
        points.add(pt);
      } else {
        folds.add(line);
      }
    }
    for (String fold : folds) {
      Set<Point> newPoints = new HashSet<>();
      if (fold.contains("x")) {
        int x = Integer.parseInt(fold.split("=")[1]);
        for (Point point : points) {
          if (point.getX() > x) {
            point.setX(2 * x - (point.getX()));
          }
          newPoints.add(point);
        }
      } else {
        int y = Integer.parseInt(fold.split("=")[1]);
        for (Point point : points) {
          if (point.getY() > y) {
            point.setY(2 * y - (point.getY()));
          }
          newPoints.add(point);
        }
      }
      points.clear();
      points.addAll(newPoints);
    }
    int x = points.stream().mapToInt(Point::getX).max().getAsInt();
    int y = points.stream().mapToInt(Point::getY).max().getAsInt();
    StringBuilder builder = new StringBuilder();
    for (int j = 0; j <= y; j++) {
      for (int i = 0; i <= x; i++) {
        Point pt = new Point(i, j);
        if (points.contains(pt)) {
          builder.append("#");
        } else {
          builder.append(" ");
        }
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}
