package net.ddellspe.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point3D {
  private int x;
  private int y;
  private int z;

  public Point3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point3D point = (Point3D) o;
    return x == point.x && y == point.y && z == point.z;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setZ(int z) {
    this.y = z;
  }

  public List<Point3D> getDifference(Point3D pt) {
    List<Point3D> points = new ArrayList<>();
    points.add(new Point3D(this.x - pt.x, this.y - pt.y, this.z - pt.z));
    points.add(new Point3D(this.x - pt.x, this.y + pt.y, this.z - pt.z));
    points.add(new Point3D(this.x - pt.x, this.y - pt.y, this.z + pt.z));
    points.add(new Point3D(this.x - pt.x, this.y + pt.y, this.z + pt.z));
    points.add(new Point3D(this.x + pt.x, this.y - pt.y, this.z - pt.z));
    points.add(new Point3D(this.x + pt.x, this.y + pt.y, this.z - pt.z));
    points.add(new Point3D(this.x + pt.x, this.y - pt.y, this.z + pt.z));
    points.add(new Point3D(this.x + pt.x, this.y + pt.y, this.z + pt.z));

    points.add(new Point3D(this.x - pt.x, this.y - pt.z, this.z - pt.y));
    points.add(new Point3D(this.x - pt.x, this.y + pt.z, this.z - pt.y));
    points.add(new Point3D(this.x - pt.x, this.y - pt.z, this.z + pt.y));
    points.add(new Point3D(this.x - pt.x, this.y + pt.z, this.z + pt.y));
    points.add(new Point3D(this.x + pt.x, this.y - pt.z, this.z - pt.y));
    points.add(new Point3D(this.x + pt.x, this.y + pt.z, this.z - pt.y));
    points.add(new Point3D(this.x + pt.x, this.y - pt.z, this.z + pt.y));
    points.add(new Point3D(this.x + pt.x, this.y + pt.z, this.z + pt.y));

    points.add(new Point3D(this.x - pt.y, this.y - pt.x, this.z - pt.z));
    points.add(new Point3D(this.x - pt.y, this.y + pt.x, this.z - pt.z));
    points.add(new Point3D(this.x - pt.y, this.y - pt.x, this.z + pt.z));
    points.add(new Point3D(this.x - pt.y, this.y + pt.x, this.z + pt.z));
    points.add(new Point3D(this.x + pt.y, this.y - pt.x, this.z - pt.z));
    points.add(new Point3D(this.x + pt.y, this.y + pt.x, this.z - pt.z));
    points.add(new Point3D(this.x + pt.y, this.y - pt.x, this.z + pt.z));
    points.add(new Point3D(this.x + pt.y, this.y + pt.x, this.z + pt.z));

    points.add(new Point3D(this.x - pt.y, this.y - pt.z, this.z - pt.x));
    points.add(new Point3D(this.x - pt.y, this.y + pt.z, this.z - pt.x));
    points.add(new Point3D(this.x - pt.y, this.y - pt.z, this.z + pt.x));
    points.add(new Point3D(this.x - pt.y, this.y + pt.z, this.z + pt.x));
    points.add(new Point3D(this.x + pt.y, this.y - pt.z, this.z - pt.x));
    points.add(new Point3D(this.x + pt.y, this.y + pt.z, this.z - pt.x));
    points.add(new Point3D(this.x + pt.y, this.y - pt.z, this.z + pt.x));
    points.add(new Point3D(this.x + pt.y, this.y + pt.z, this.z + pt.x));

    points.add(new Point3D(this.x - pt.z, this.y - pt.y, this.z - pt.x));
    points.add(new Point3D(this.x - pt.z, this.y + pt.y, this.z - pt.x));
    points.add(new Point3D(this.x - pt.z, this.y - pt.y, this.z + pt.x));
    points.add(new Point3D(this.x - pt.z, this.y + pt.y, this.z + pt.x));
    points.add(new Point3D(this.x + pt.z, this.y - pt.y, this.z - pt.x));
    points.add(new Point3D(this.x + pt.z, this.y + pt.y, this.z - pt.x));
    points.add(new Point3D(this.x + pt.z, this.y - pt.y, this.z + pt.x));
    points.add(new Point3D(this.x + pt.z, this.y + pt.y, this.z + pt.x));

    points.add(new Point3D(this.x - pt.z, this.y - pt.x, this.z - pt.y));
    points.add(new Point3D(this.x - pt.z, this.y + pt.x, this.z - pt.y));
    points.add(new Point3D(this.x - pt.z, this.y - pt.x, this.z + pt.y));
    points.add(new Point3D(this.x - pt.z, this.y + pt.x, this.z + pt.y));
    points.add(new Point3D(this.x + pt.z, this.y - pt.x, this.z - pt.y));
    points.add(new Point3D(this.x + pt.z, this.y + pt.x, this.z - pt.y));
    points.add(new Point3D(this.x + pt.z, this.y - pt.x, this.z + pt.y));
    points.add(new Point3D(this.x + pt.z, this.y + pt.x, this.z + pt.y));

    return points;
  }

  public Point3D getPointWithDiff(Point3D point, int orientation) {
    int x = point.getX();
    int y = point.getY();
    int z = point.getZ();
    if (orientation / 8 == 1) {
      int temp = this.y;
      this.y = this.z;
      this.z = temp;
    } else if (orientation / 8 == 2) {
      int temp = this.y;
      this.y = this.x;
      this.x = temp;
    } else if (orientation / 8 == 3) {
      int temp = this.x;
      this.x = this.y;
      this.y = this.z;
      this.z = temp;
    } else if (orientation / 8 == 4) {
      int temp = this.x;
      this.x = this.z;
      this.z = temp;
    } else if (orientation / 8 == 5) {
      int temp = this.x;
      this.x = this.z;
      this.z = this.y;
      this.y = temp;
    }
    int ori = orientation % 8;
    return new Point3D(
        x + (this.getX() * ((ori / 4 % 2 == 0) ? 1 : -1)),
        y + (this.getY() * ((ori % 2 == 0) ? 1 : -1)),
        z + (this.getZ() * ((ori / 2 % 2 == 0) ? 1 : -1)));
  }

  public long manhattanDistance(Point3D pt2) {
    return Math.abs(x - pt2.getX()) + Math.abs(y - pt2.getY()) + Math.abs(z - pt2.getZ());
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public String toString() {
    return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
  }
}
