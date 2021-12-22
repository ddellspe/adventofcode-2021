package net.ddellspe.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day14Test {
  @Test
  public void providedInput1TestPart1() {
    assertEquals(Day14.part1("example.txt"), 1588L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 14 Part 1 Answer is: " + Day14.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertEquals(Day14.part2("example.txt"), 2188189693529L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 14 Part 2 Answer is: " + Day14.part2("input.txt"));
  }
}
