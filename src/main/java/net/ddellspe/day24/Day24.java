package net.ddellspe.day24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.LongPoint;
import net.ddellspe.utils.Point;

public class Day24 {
  private Day24() {}

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day24.class);
    List<Integer> divide = new ArrayList<>();
    List<Integer> addX = new ArrayList<>();
    List<Integer> addZ = new ArrayList<>();
    List<Point> pairs = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      divide.add(Integer.parseInt(data.get(i * 18 + 4).split(" ")[2]));
      addX.add(Integer.parseInt(data.get(i * 18 + 5).split(" ")[2]));
      addZ.add(Integer.parseInt(data.get(i * 18 + 15).split(" ")[2]));
    }
    Map<Integer, LongPoint> zValues = new HashMap<>();
    zValues.put(0, new LongPoint(0, 0));
    for (int i = 0; i < 14; i++) {
      Map<Integer, LongPoint> newZValues = new HashMap<>();
      for (Entry<Integer, LongPoint> entry : zValues.entrySet()) {
        for (int w = 9; w > 0; w--) {
          int z = entry.getKey() / divide.get(i);
          if (entry.getKey() % 26 + addX.get(i) != w) {
            z = z * 26 + w + addZ.get(i);
          }
          if (divide.get(i) == 1 || (divide.get(i) == 26 && z < entry.getKey())) {
            if (!newZValues.containsKey(z)) {
              newZValues.put(
                  z,
                  new LongPoint(
                      entry.getValue().getX() * 10 + w, entry.getValue().getY() * 10 + w));
            } else {
              newZValues
                  .get(z)
                  .setX(Math.max(newZValues.get(z).getX(), entry.getValue().getX() * 10 + w));
              newZValues
                  .get(z)
                  .setY(Math.max(newZValues.get(z).getY(), entry.getValue().getY() * 10 + w));
            }
          }
        }
      }
      zValues.clear();
      zValues.putAll(newZValues);
    }
    return zValues.get(0).getX();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day24.class);
    List<Integer> part1 = new ArrayList<>();
    List<Integer> part2 = new ArrayList<>();
    List<Integer> part3 = new ArrayList<>();
    List<Point> pairs = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      part1.add(Integer.parseInt(data.get(i * 18 + 4).split(" ")[2]));
      part2.add(Integer.parseInt(data.get(i * 18 + 5).split(" ")[2]));
      part3.add(Integer.parseInt(data.get(i * 18 + 15).split(" ")[2]));
    }
    Map<Integer, LongPoint> zValues = new HashMap<>();
    zValues.put(0, new LongPoint(0, 0));
    for (int i = 0; i < 14; i++) {
      Map<Integer, LongPoint> newZValues = new HashMap<>();
      for (Entry<Integer, LongPoint> entry : zValues.entrySet()) {
        for (int w = 9; w > 0; w--) {
          int z = entry.getKey();
          if (z % 26 + part2.get(i) != w) {
            z = z / part1.get(i) * 26 + w + part3.get(i);
          } else {
            z = z / part1.get(i);
          }
          if (part1.get(i) == 1 || (part1.get(i) == 26 && z < entry.getKey())) {
            if (!newZValues.containsKey(z)) {
              newZValues.put(
                  z,
                  new LongPoint(
                      entry.getValue().getX() * 10 + w, entry.getValue().getY() * 10 + w));
            } else {
              newZValues
                  .get(z)
                  .setX(Math.min(newZValues.get(z).getX(), entry.getValue().getX() * 10 + w));
              newZValues
                  .get(z)
                  .setY(Math.min(newZValues.get(z).getY(), entry.getValue().getY() * 10 + w));
            }
          }
        }
      }
      zValues.clear();
      zValues.putAll(newZValues);
    }
    return zValues.get(0).getX();
  }
}
