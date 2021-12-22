package net.ddellspe.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day21Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day21.part1("example.txt"), 739785L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 21 Part 1 Answer is: " + Day21.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day21.part2("example.txt"), 444356092776315L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 21 Part 2 Answer is: " + Day21.part2("input.txt"));
  }
}
