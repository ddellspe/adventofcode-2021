package net.ddellspe.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day01Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day01.part1("example.txt"), 7L);
  }

  @Test
  public void providedInputTestPart1Optimized() {
    assertEquals(Day01.part1Optimized("example.txt"), 7L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 01 Part 1 Answer is: " + Day01.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day01.part2("example.txt"), 5L);
  }

  @Test
  public void providedInputTestPart2Optimized() {
    assertEquals(Day01.part2Optimized("example.txt"), 5L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 01 Part 2 Answer is: " + Day01.part2("input.txt"));
  }
}
