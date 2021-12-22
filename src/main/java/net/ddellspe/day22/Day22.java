package net.ddellspe.day22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point3D;

public class Day22 {
  private Day22() {}

  public static class PointRange3D {
    boolean on;
    long x0;
    long x1;
    long y0;
    long y1;
    long z0;
    long z1;

    public PointRange3D(boolean on, long x0, long x1, long y0, long y1, long z0, long z1) {
      this.on = on;
      this.x0 = x0;
      this.x1 = x1;
      this.y0 = y0;
      this.y1 = y1;
      this.z0 = z0;
      this.z1 = z1;
    }

    public long getCount() {
      return (x1 - x0 + 1) * (y1 - y0 + 1) * (z1 - z0 + 1);
    }

    public boolean overlaps(PointRange3D range) {
      // l11 >= l20 && l21 >= l10
      return this.x1 >= range.x0
          && range.x1 >= this.x0
          && this.y1 >= range.y0
          && range.y1 >= this.y0
          && this.z1 >= range.z0
          && range.z1 >= this.z0;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      PointRange3D that = (PointRange3D) o;
      return x0 == that.x0
          && x1 == that.x1
          && y0 == that.y0
          && y1 == that.y1
          && z0 == that.z0
          && z1 == that.z1;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x0, x1, y0, y1, z0, z1);
    }

    public long getX0() {
      return x0;
    }

    public void setX0(long x0) {
      this.x0 = x0;
    }

    public long getX1() {
      return x1;
    }

    public void setX1(long x1) {
      this.x1 = x1;
    }

    public long getY0() {
      return y0;
    }

    public void setY0(long y0) {
      this.y0 = y0;
    }

    public long getY1() {
      return y1;
    }

    public void setY1(long y1) {
      this.y1 = y1;
    }

    public long getZ0() {
      return z0;
    }

    public void setZ0(long z0) {
      this.z0 = z0;
    }

    public long getZ1() {
      return z1;
    }

    public void setZ1(long z1) {
      this.z1 = z1;
    }

    public boolean isOn() {
      return on;
    }

    public void setOn(boolean on) {
      this.on = on;
    }
  }

  public static void checkRanges(Set<PointRange3D> ranges) {
    for (PointRange3D range : ranges) {
      if (range.x1 < range.x0 || range.y1 < range.y0 || range.z1 < range.z0) {
        // This should never happen, just a safeguard
        throw new RuntimeException("Invalid range: " + range);
      }
    }
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day22.class);
    Set<Point3D> onPixels = new HashSet<>();
    for (String line : data) {
      boolean state = false;
      String[] ranges = line.split(" ")[1].split(",");
      String status = line.split(" ")[0];
      if (status.equals("on")) {
        state = true;
      }
      int[] xs =
          Arrays.stream(ranges[0].split("=")[1].split("\\.\\."))
              .mapToInt(Integer::parseInt)
              .toArray();
      int[] ys =
          Arrays.stream(ranges[1].split("=")[1].split("\\.\\."))
              .mapToInt(Integer::parseInt)
              .toArray();
      int[] zs =
          Arrays.stream(ranges[2].split("=")[1].split("\\.\\."))
              .mapToInt(Integer::parseInt)
              .toArray();
      for (int x = xs[0]; x <= xs[1]; x++) {
        if (x < -50 || x > 50) {
          continue;
        }
        for (int y = ys[0]; y <= ys[1]; y++) {
          if (y < -50 || y > 50) {
            continue;
          }
          for (int z = zs[0]; z <= zs[1]; z++) {
            if (z < -50 || z > 50) {
              continue;
            }
            if (state) {
              onPixels.add(new Point3D(x, y, z));
            } else {
              onPixels.remove(new Point3D(x, y, z));
            }
          }
        }
      }
    }
    return onPixels.size();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day22.class);
    List<PointRange3D> inputRanges = new ArrayList<>();
    for (String line : data) {
      boolean state = false;
      String[] ranges = line.split(" ")[1].split(",");
      String status = line.split(" ")[0];
      if (status.equals("on")) {
        state = true;
      }
      int[] xs =
          Arrays.stream(ranges[0].split("=")[1].split("\\.\\."))
              .mapToInt(Integer::parseInt)
              .toArray();
      int[] ys =
          Arrays.stream(ranges[1].split("=")[1].split("\\.\\."))
              .mapToInt(Integer::parseInt)
              .toArray();
      int[] zs =
          Arrays.stream(ranges[2].split("=")[1].split("\\.\\."))
              .mapToInt(Integer::parseInt)
              .toArray();
      inputRanges.add(new PointRange3D(state, xs[0], xs[1], ys[0], ys[1], zs[0], zs[1]));
    }
    Set<PointRange3D> availableRanges = new HashSet<>();
    long max =
        inputRanges.stream()
            .mapToLong(
                range ->
                    Math.max(
                        Math.max(
                            Math.max(
                                Math.max(
                                    Math.max(Math.abs(range.getX0()), Math.abs(range.getX1())),
                                    Math.abs(range.getY0())),
                                Math.abs(range.getY1())),
                            Math.abs(range.getZ0())),
                        Math.abs(range.getZ1())))
            .max()
            .getAsLong();
    availableRanges.add(new PointRange3D(false, max * -1, max, max * -1, max, max * -1, max));
    for (PointRange3D trial : inputRanges) {
      Set<PointRange3D> newAvailableRanges = new HashSet<>();
      for (PointRange3D range : availableRanges) {
        if (!range.overlaps(trial)) {
          newAvailableRanges.add(range);
          continue;
        }
        if (range.getX0() < trial.getX0()) {
          newAvailableRanges.add(
              new PointRange3D(
                  range.isOn(),
                  range.getX0(),
                  trial.getX0() - 1,
                  range.getY0(),
                  range.getY1(),
                  range.getZ0(),
                  range.getZ1()));
          range.setX0(trial.getX0());
        }
        if (range.getX1() > trial.getX1()) {
          newAvailableRanges.add(
              new PointRange3D(
                  range.isOn(),
                  trial.getX1() + 1,
                  range.getX1(),
                  range.getY0(),
                  range.getY1(),
                  range.getZ0(),
                  range.getZ1()));
          range.setX1(trial.getX1());
        }
        if (range.getY0() < trial.getY0()) {
          newAvailableRanges.add(
              new PointRange3D(
                  range.isOn(),
                  range.getX0(),
                  range.getX1(),
                  range.getY0(),
                  trial.getY0() - 1,
                  range.getZ0(),
                  range.getZ1()));
          range.setY0(trial.getY0());
        }
        if (range.getY1() > trial.getY1()) {
          newAvailableRanges.add(
              new PointRange3D(
                  range.isOn(),
                  range.getX0(),
                  range.getX1(),
                  trial.getY1() + 1,
                  range.getY1(),
                  range.getZ0(),
                  range.getZ1()));
          range.setY1(trial.getY1());
        }
        if (range.getZ0() < trial.getZ0()) {
          newAvailableRanges.add(
              new PointRange3D(
                  range.isOn(),
                  range.getX0(),
                  range.getX1(),
                  range.getY0(),
                  range.getY1(),
                  range.getZ0(),
                  trial.getZ0() - 1));
          range.setZ0(trial.getZ0());
        }
        if (range.getZ1() > trial.getZ1()) {
          newAvailableRanges.add(
              new PointRange3D(
                  range.isOn(),
                  range.getX0(),
                  range.getX1(),
                  range.getY0(),
                  range.getY1(),
                  trial.getZ1() + 1,
                  range.getZ1()));
          range.setZ1(trial.getZ1());
        }
        range.setOn(trial.isOn());
        newAvailableRanges.add(range);
      }
      availableRanges.clear();
      availableRanges.addAll(newAvailableRanges);
      checkRanges(availableRanges);
    }
    return availableRanges.stream()
        .filter(PointRange3D::isOn)
        .mapToLong(PointRange3D::getCount)
        .sum();
  }
}
