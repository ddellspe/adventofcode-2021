package net.ddellspe.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.Point;

public class Day11 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day11.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    Map<Point, Integer> puzzle = new HashMap<>();
    long flashes = 0;
    for (int y = 0; y < data.size(); y++) {
      for (int x = 0; x < data.get(y).length(); x++) {
        puzzle.put(new Point(x, y), Integer.parseInt(String.valueOf(data.get(y).charAt(x))));
      }
    }
    for (int i = 0; i < 100; i++) {
      Queue<Point> flashers = new LinkedList<>();
      Set<Point> flashed = new HashSet<>();
      Map<Point, Integer> newPuzzle = new HashMap<>(puzzle);
      for (int y = 0; y < data.size(); y++) {
        for (int x = 0; x < data.get(y).length(); x++) {
          Point pt = new Point(x, y);
          newPuzzle.put(pt, newPuzzle.get(pt) + 1);
          if (newPuzzle.get(pt) == 10) {
            flashers.add(pt);
          }
        }
      }
      while (!flashers.isEmpty()) {
        Point flashPoint = flashers.poll();
        flashed.add(flashPoint);
        for (int y = -1; y <= 1; y++) {
          for (int x = -1; x <= 1; x++) {
            if (x == 0 & y == 0) {
              continue;
            }
            Point pt = new Point(flashPoint.getX() + x, flashPoint.getY() + y);
            if (!puzzle.containsKey(pt)) {
              continue;
            }
            newPuzzle.put(pt, newPuzzle.get(pt) + 1);
            if (newPuzzle.get(pt) >= 10 && !flashed.contains(pt) && !flashers.contains(pt)) {
              flashers.add(pt);
            }
          }
        }
      }
      for (Entry<Point, Integer> entry : newPuzzle.entrySet()) {
        if (entry.getValue() >= 10) {
          flashes += 1;
          entry.setValue(0);
        }
      }

      puzzle = new HashMap<>(newPuzzle);
    }
    return flashes;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    Map<Point, Integer> puzzle = new HashMap<>();
    for (int y = 0; y < data.size(); y++) {
      for (int x = 0; x < data.get(y).length(); x++) {
        puzzle.put(new Point(x, y), Integer.parseInt(String.valueOf(data.get(y).charAt(x))));
      }
    }
    int i = 0;
    long flashes = 0;
    while (flashes != 100L) {
      Queue<Point> flashers = new LinkedList<>();
      Set<Point> flashed = new HashSet<>();
      Map<Point, Integer> newPuzzle = new HashMap<>(puzzle);
      for (int y = 0; y < data.size(); y++) {
        for (int x = 0; x < data.get(y).length(); x++) {
          Point pt = new Point(x, y);
          newPuzzle.put(pt, newPuzzle.get(pt) + 1);
          if (newPuzzle.get(pt) == 10) {
            flashers.add(pt);
          }
        }
      }
      while (!flashers.isEmpty()) {
        Point flashPoint = flashers.poll();
        flashed.add(flashPoint);
        for (int y = -1; y <= 1; y++) {
          for (int x = -1; x <= 1; x++) {
            if (x == 0 & y == 0) {
              continue;
            }
            Point pt = new Point(flashPoint.getX() + x, flashPoint.getY() + y);
            if (!puzzle.containsKey(pt)) {
              continue;
            }
            newPuzzle.put(pt, newPuzzle.get(pt) + 1);
            if (newPuzzle.get(pt) >= 10 && !flashed.contains(pt) && !flashers.contains(pt)) {
              flashers.add(pt);
            }
          }
        }
      }
      flashes = 0;
      for (Entry<Point, Integer> entry : newPuzzle.entrySet()) {
        if (entry.getValue() >= 10) {
          flashes += 1;
          entry.setValue(0);
        }
      }
      puzzle = new HashMap<>(newPuzzle);
      i++;
    }
    return i;
  }
}
