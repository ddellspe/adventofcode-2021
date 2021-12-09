package net.ddellspe.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.Point;

public class Day09 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day09.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Queue<Point> getNeighbors(Point point) {
    Queue<Point> neighbors = new LinkedList<>();
    neighbors.add(new Point(point.getX() - 1, point.getY()));
    neighbors.add(new Point(point.getX() + 1, point.getY()));
    neighbors.add(new Point(point.getX(), point.getY() - 1));
    neighbors.add(new Point(point.getX(), point.getY() + 1));
    return neighbors;
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    Map<Point, Integer> points = new HashMap<>();
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).length(); j++) {
        points.put(new Point(j, i), Integer.parseInt(String.valueOf(data.get(i).charAt(j))));
      }
    }
    long sum = 0;
    for (Point point : points.keySet()) {
      int val = points.get(point);
      Queue<Point> neighbors = getNeighbors(point);
      boolean low = true;
      for (Point neighbor : neighbors) {
        if (points.get(neighbor) != null && points.get(neighbor) < val) {
          low = false;
          break;
        }
      }
      if (low) {
        sum += points.get(point) + 1;
      }
    }
    return sum;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    Map<Point, Integer> points = new HashMap<>();
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).length(); j++) {
        points.put(new Point(j, i), Integer.parseInt(String.valueOf(data.get(i).charAt(j))));
      }
    }
    Set<Point> lowPoints = new HashSet<>();
    for (Point point : points.keySet()) {
      int val = points.get(point);
      Queue<Point> neighbors = getNeighbors(point);
      boolean low = true;
      for (Point neighbor : neighbors) {
        if (points.get(neighbor) != null && points.get(neighbor) < val) {
          low = false;
          break;
        }
      }
      if (low) {
        lowPoints.add(point);
      }
    }
    Set<Point> visited = new HashSet<>();
    List<Long> basins = new ArrayList<>();
    for (Point point : lowPoints) {
      int val = points.get(point);
      long size = 1;
      if (visited.contains(point)) {
        continue;
      }
      visited.add(point);
      Queue<Point> neighbors = getNeighbors(point);
      while (neighbors.size() > 0) {
        Point pt = neighbors.poll();
        if (visited.contains(pt)) {
          continue;
        }
        visited.add(pt);
        if (points.get(pt) == null) {
          continue;
        }
        if (points.get(pt) > val && points.get(pt) != 9) {
          size += 1;
          neighbors.addAll(getNeighbors(pt));
        }
      }
      basins.add(size);
    }
    basins = basins.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    return basins.get(0) * basins.get(1) * basins.get(2);
  }
}
