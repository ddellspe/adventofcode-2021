package net.ddellspe.day19;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point3D;

public class Day19 {
  private Day19() {}

  public static Map<Integer, Set<Point3D>> parseScanners(List<String> data) {
    Map<Integer, Set<Point3D>> scanners = new HashMap<>();
    Set<Point3D> points = new HashSet<>();
    int scannerNumber = 0;
    for (String line : data) {
      if (line.contains("---")) {
        scannerNumber = Integer.parseInt(line.split("[ ]+")[2]);
        continue;
      }
      if (line.isEmpty()) {
        if (!points.isEmpty()) {
          scanners.put(scannerNumber, points);
          points = new HashSet<>();
        }
        continue;
      }
      String[] pts = line.split(",");
      int x = Integer.parseInt(pts[0]);
      int y = Integer.parseInt(pts[1]);
      int z = Integer.parseInt(pts[2]);
      points.add(new Point3D(x, y, z));
    }
    scanners.put(scannerNumber, points);
    return scanners;
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day19.class);
    Map<Integer, Set<Point3D>> scanners = parseScanners(data);
    Set<Point3D> original = scanners.get(0);
    Map<Point3D, Set<Integer>> commonPoints = new HashMap<>();
    for (Point3D pt : original) {
      commonPoints.putIfAbsent(pt, new HashSet<>());
      commonPoints.get(pt).add(0);
    }
    Set<Integer> mergesMade = new HashSet<>();
    while (mergesMade.size() + 1 != scanners.size()) {
      for (int i = 1; i < scanners.keySet().size(); i++) {
        if (mergesMade.contains(i)) {
          continue;
        }
        Set<Point3D> comparePoints = scanners.get(i);
        HashMap<Integer, HashMap<Point3D, Integer>> diffs = new HashMap<>();
        for (Point3D point : original) {
          for (Point3D point2 : comparePoints) {
            List<Point3D> difference = point.getDifference(point2);
            for (int orientation = 0; orientation < difference.size(); orientation++) {
              HashMap<Point3D, Integer> map = diffs.get(orientation);
              if (map == null) {
                map = new HashMap<>();
              }
              map.merge(difference.get(orientation), 1, Integer::sum);
              diffs.put(orientation, map);
            }
          }
        }
        for (int orientation = 0; orientation < 48; orientation++) {
          boolean orientationFound = false;
          Set<Entry<Point3D, Integer>> entries =
              diffs.get(orientation).entrySet().stream()
                  .filter(entry -> entry.getValue() >= 12)
                  .collect(Collectors.toSet());
          for (Entry<Point3D, Integer> entry : entries) {
            if (entry.getValue() >= 12) {
              final int orient = orientation;
              Point3D diffPoint = entry.getKey();
              Set<Point3D> updatedPoints =
                  comparePoints.stream()
                      .map(point -> point.getPointWithDiff(diffPoint, orient))
                      .collect(Collectors.toSet());
              original.addAll(updatedPoints);
              for (Point3D pt : updatedPoints) {
                commonPoints.putIfAbsent(pt, new HashSet<>());
                commonPoints.get(pt).add(i);
              }
              orientationFound = true;
              break;
            }
          }
          if (orientationFound) {
            mergesMade.add(i);
            break;
          }
        }
      }
    }
    return commonPoints.keySet().size();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day19.class);
    Map<Integer, Set<Point3D>> scanners = parseScanners(data);
    Set<Point3D> original = scanners.get(0);
    Map<Point3D, Set<Integer>> commonPoints = new HashMap<>();
    Set<Point3D> scannerPoints = new HashSet<>();
    for (Point3D pt : original) {
      commonPoints.putIfAbsent(pt, new HashSet<>());
      commonPoints.get(pt).add(0);
    }
    Set<Integer> mergesMade = new HashSet<>();
    while (mergesMade.size() + 1 != scanners.size()) {
      for (int i = 1; i < scanners.keySet().size(); i++) {
        if (mergesMade.contains(i)) {
          continue;
        }
        Set<Point3D> comparePoints = scanners.get(i);
        HashMap<Integer, HashMap<Point3D, Integer>> diffs = new HashMap<>();
        for (Point3D point : original) {
          for (Point3D point2 : comparePoints) {
            List<Point3D> difference = point.getDifference(point2);
            for (int orientation = 0; orientation < difference.size(); orientation++) {
              HashMap<Point3D, Integer> map = diffs.get(orientation);
              if (map == null) {
                map = new HashMap<>();
              }
              map.merge(difference.get(orientation), 1, Integer::sum);
              diffs.put(orientation, map);
            }
          }
        }
        for (int orientation = 0; orientation < 48; orientation++) {
          boolean orientationFound = false;
          Set<Entry<Point3D, Integer>> entries =
              diffs.get(orientation).entrySet().stream()
                  .filter(entry -> entry.getValue() >= 12)
                  .collect(Collectors.toSet());
          for (Entry<Point3D, Integer> entry : entries) {
            if (entry.getValue() >= 12) {
              final int orient = orientation;
              Point3D diffPoint = entry.getKey();
              scannerPoints.add(diffPoint);
              Set<Point3D> updatedPoints =
                  comparePoints.stream()
                      .map(point -> point.getPointWithDiff(diffPoint, orient))
                      .collect(Collectors.toSet());
              original.addAll(updatedPoints);
              for (Point3D pt : updatedPoints) {
                commonPoints.putIfAbsent(pt, new HashSet<>());
                commonPoints.get(pt).add(i);
              }
              orientationFound = true;
              break;
            }
          }
          if (orientationFound) {
            mergesMade.add(i);
            break;
          }
        }
      }
    }
    long maxDistance = 0;
    for (Point3D pt1 : scannerPoints) {
      for (Point3D pt2 : scannerPoints) {
        if (pt1.equals(pt2)) {
          continue;
        }
        long dist = pt1.manhattanDistance(pt2);
        if (dist > maxDistance) {
          maxDistance = dist;
        }
      }
    }
    return maxDistance;
  }
}
