package net.ddellspe.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day22Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(590784L, Day22.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 22 Part 1 Answer is: " + Day22.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(2758514936282235L, Day22.part2("example2.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 22 Part 2 Answer is: " + Day22.part2("input.txt"));
  }
}
