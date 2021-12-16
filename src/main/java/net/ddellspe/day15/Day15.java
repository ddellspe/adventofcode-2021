package net.ddellspe.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.Point;

public class Day15 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day15.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  static class QueueEntry implements Comparable<QueueEntry> {
    Point point;
    long cost;

    public QueueEntry(Point point, long cost) {
      this.point = point;
      this.cost = cost;
    }

    @Override
    public int hashCode() {
      return Objects.hash(point, cost);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      QueueEntry queueEntry = (QueueEntry) o;
      return point == queueEntry.point && cost == queueEntry.cost;
    }

    public Point getPoint() {
      return point;
    }

    public long getCost() {
      return cost;
    }

    @Override
    public int compareTo(QueueEntry o) {
      return Long.compare(cost, o.cost);
    }
  }

  public static Set<Point> getPaths(Point point, int maxX, int maxY) {
    Set<Point> pts = new HashSet<>();
    if (point.getX() + 1 < maxX) {
      pts.add(new Point(point.getX() + 1, point.getY()));
    }
    if (point.getX() - 1 >= 0) {
      pts.add(new Point(point.getX() - 1, point.getY()));
    }
    if (point.getY() + 1 < maxY) {
      pts.add(new Point(point.getX(), point.getY() + 1));
    }
    if (point.getY() - 1 >= 0) {
      pts.add(new Point(point.getX(), point.getY() - 1));
    }
    return pts;
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    Map<Point, Long> costs = new HashMap<>();
    Map<Point, Set<Point>> paths = new HashMap<>();
    int maxY = data.size();
    int maxX = data.get(0).length();
    for (int y = 0; y < maxY; y++) {
      for (int x = 0; x < maxX; x++) {
        Point pt = new Point(x, y);
        costs.put(pt, Long.parseLong(String.valueOf(data.get(y).charAt(x))));
        paths.put(pt, getPaths(pt, maxX, maxY));
      }
    }
    long best = -1;
    HashMap<Point, Long> bestAt = new HashMap<>();
    PriorityQueue<QueueEntry> todo = new PriorityQueue<>();
    todo.add(new QueueEntry(new Point(0, 0), 0L));
    long cnt = 0;
    while (!todo.isEmpty()) {
      QueueEntry entry = todo.poll();
      Point lastCoord = entry.getPoint();
      long cost = entry.getCost();

      if (best > -1 && cost >= best) {
        continue;
      } else if (lastCoord.equals(new Point(maxX - 1, maxY - 1))) {
        best = cost;
      }
      if (bestAt.containsKey(lastCoord) && cost >= bestAt.get(lastCoord)) {
        continue;
      } else {
        bestAt.put(lastCoord, cost);
      }

      for (Point pt : paths.get(lastCoord)) {
        todo.add(new QueueEntry(pt, cost + costs.get(pt)));
      }
      cnt++;
      if (cnt % 10000000 == 0) {}
    }
    return bestAt.get(new Point(maxX - 1, maxY - 1));
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    Map<Point, Long> costs = new HashMap<>();
    Map<Point, Set<Point>> paths = new HashMap<>();
    int maxY = data.size();
    int maxMaxY = maxY * 5;
    int maxX = data.get(0).length();
    int maxMaxX = maxX * 5;
    for (int y = 0; y < maxY; y++) {
      for (int x = 0; x < maxX; x++) {
        long cost = Long.parseLong(String.valueOf(data.get(y).charAt(x)));
        for (int inY = 0; inY < 5; inY++) {
          for (int inX = 0; inX < 5; inX++) {
            Point pt = new Point(x + inX * maxX, y + inY * maxY);
            long tempCost = cost + inX + inY;
            tempCost = tempCost > 9 ? (tempCost - 9) : tempCost;
            costs.put(pt, tempCost);
            paths.put(pt, getPaths(pt, maxMaxX, maxMaxY));
          }
        }
      }
    }
    long best = -1;
    HashMap<Point, Long> bestAt = new HashMap<>();
    PriorityQueue<QueueEntry> todo = new PriorityQueue<>();
    todo.add(new QueueEntry(new Point(0, 0), 0L));
    long cnt = 0;
    while (!todo.isEmpty()) {
      QueueEntry entry = todo.poll();
      Point lastCoord = entry.getPoint();
      long cost = entry.getCost();

      if (best > -1 && cost >= best) {
        continue;
      } else if (lastCoord.equals(new Point(maxMaxX - 1, maxMaxY - 1))) {
        best = cost;
      }
      if (bestAt.containsKey(lastCoord) && cost >= bestAt.get(lastCoord)) {
        continue;
      } else {
        bestAt.put(lastCoord, cost);
      }

      for (Point pt : paths.get(lastCoord)) {
        todo.add(new QueueEntry(pt, cost + costs.get(pt)));
      }
      cnt++;
      if (cnt % 10000000 == 0) {}
    }
    return bestAt.get(new Point(maxMaxX - 1, maxMaxY - 1));
  }
}