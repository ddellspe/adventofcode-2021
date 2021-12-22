package net.ddellspe.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day10Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day10.part1("example.txt"), 26397L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 10 Part 1 Answer is: " + Day10.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day10.part2("example.txt"), 288957L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 10 Part 2 Answer is: " + Day10.part2("input.txt"));
  }
}
