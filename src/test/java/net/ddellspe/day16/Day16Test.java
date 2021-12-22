package net.ddellspe.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day16Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(Day16.part1("example.txt"), 16L);
    assertEquals(Day16.part1("example2.txt"), 12L);
    assertEquals(Day16.part1("example3.txt"), 23L);
    assertEquals(Day16.part1("example4.txt"), 31L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 16 Part 1 Answer is: " + Day16.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(Day16.part2("example5.txt"), 3L);
    assertEquals(Day16.part2("example6.txt"), 54L);
    assertEquals(Day16.part2("example7.txt"), 7L);
    assertEquals(Day16.part2("example8.txt"), 9L);
    assertEquals(Day16.part2("example9.txt"), 1L);
    assertEquals(Day16.part2("example10.txt"), 0L);
    assertEquals(Day16.part2("example11.txt"), 0L);
    assertEquals(Day16.part2("example12.txt"), 1L);
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 16 Part 2 Answer is: " + Day16.part2("input.txt"));
  }
}
