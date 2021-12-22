package net.ddellspe.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day06Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day06.part1("example.txt"), 5934L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 06 Part 1 Answer is: " + Day06.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day06.part2("example.txt"), 26984457539L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 06 Part 2 Answer is: " + Day06.part2("input.txt"));
  }
}
