package net.ddellspe.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day11Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day11.part1("example.txt"), 1656L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 11 Part 1 Answer is: " + Day11.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day11.part2("example.txt"), 195L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 11 Part 2 Answer is: " + Day11.part2("input.txt"));
  }
}
