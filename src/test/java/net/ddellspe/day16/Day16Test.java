package net.ddellspe.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day16Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(16L, Day16.part1("example.txt"));
    assertEquals(12L, Day16.part1("example2.txt"));
    assertEquals(23L, Day16.part1("example3.txt"));
    assertEquals(31L, Day16.part1("example4.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 16 Part 1 Answer is: " + Day16.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(3L, Day16.part2("example5.txt"));
    assertEquals(54L, Day16.part2("example6.txt"));
    assertEquals(7L, Day16.part2("example7.txt"));
    assertEquals(9L, Day16.part2("example8.txt"));
    assertEquals(1L, Day16.part2("example9.txt"));
    assertEquals(0L, Day16.part2("example10.txt"));
    assertEquals(0L, Day16.part2("example11.txt"));
    assertEquals(1L, Day16.part2("example12.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 16 Part 2 Answer is: " + Day16.part2("input.txt"));
  }
}
