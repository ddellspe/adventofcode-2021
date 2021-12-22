package net.ddellspe.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day17Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(45L, Day17.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 17 Part 1 Answer is: " + Day17.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(112L, Day17.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 17 Part 2 Answer is: " + Day17.part2("input.txt"));
  }
}
