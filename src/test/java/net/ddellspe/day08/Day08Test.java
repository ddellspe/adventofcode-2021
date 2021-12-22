package net.ddellspe.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day08Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day08.part1("example.txt"), 26L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 08 Part 1 Answer is: " + Day08.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day08.part2("example.txt"), 61229L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 08 Part 2 Answer is: " + Day08.part2("input.txt"));
  }
}
