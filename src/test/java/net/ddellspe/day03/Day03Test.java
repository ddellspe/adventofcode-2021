package net.ddellspe.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day03Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day03.part1("example.txt"), 198L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 03 Part 1 Answer is: " + Day03.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day03.part2("example.txt"), 230L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 03 Part 2 Answer is: " + Day03.part2("input.txt"));
  }
}
