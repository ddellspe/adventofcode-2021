package net.ddellspe.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day20Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day20.part1("example.txt", true), 35L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 20 Part 1 Answer is: " + Day20.part1("input.txt", false));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day20.part2("example.txt", false), 3351L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 20 Part 2 Answer is: " + Day20.part2("input.txt", false));
  }
}
