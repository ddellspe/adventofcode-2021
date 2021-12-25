package net.ddellspe.day25;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day25 {
  private Day25() {}

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day25.class);
    Set<Point> eastCucumbers = new HashSet<>();
    Set<Point> southCucumbers = new HashSet<>();
    int xRange = data.get(0).length();
    int yRange = data.size();
    int y = 0;
    for (String line : data) {
      for (int x = 0; x < line.length(); x++) {
        switch (line.charAt(x)) {
          case 'v':
            southCucumbers.add(new Point(x, y));
            break;
          case '>':
            eastCucumbers.add(new Point(x, y));
            break;
        }
      }
      y++;
    }
    long steps = 0;
    while (true) {
      int movements = 0;
      Set<Point> newEast = new HashSet<>();
      for (Point cucumber : eastCucumbers) {
        Point newPoint = new Point((cucumber.getX() + 1) % xRange, cucumber.getY());
        if (eastCucumbers.contains(newPoint) || southCucumbers.contains(newPoint)) {
          newEast.add(cucumber);
        } else {
          newEast.add(newPoint);
          movements++;
        }
      }
      eastCucumbers.clear();
      eastCucumbers.addAll(newEast);
      Set<Point> newSouth = new HashSet<>();
      for (Point cucumber : southCucumbers) {
        Point newPoint = new Point(cucumber.getX(), (cucumber.getY() + 1) % yRange);
        if (eastCucumbers.contains(newPoint) || southCucumbers.contains(newPoint)) {
          newSouth.add(cucumber);
        } else {
          newSouth.add(newPoint);
          movements++;
        }
      }
      southCucumbers.clear();
      southCucumbers.addAll(newSouth);
      steps++;
      if (movements == 0) {
        break;
      }
    }
    return steps;
  }
}
