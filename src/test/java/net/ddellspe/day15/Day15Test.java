package net.ddellspe.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day15Test {
  @Test
  public void providedInput1TestPart1() {
    assertEquals(Day15.part1("example.txt"), 40L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 15 Part 1 Answer is: " + Day15.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertEquals(Day15.part2("example.txt"), 315L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 15 Part 2 Answer is: " + Day15.part2("input.txt"));
  }
}
