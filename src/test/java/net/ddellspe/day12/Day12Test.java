package net.ddellspe.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day12Test {
  @Test
  public void providedInput1TestPart1() {
    assertEquals(10L, Day12.part1("example1.txt"));
  }

  @Test
  public void providedInput2TestPart1() {
    assertEquals(19L, Day12.part1("example2.txt"));
  }

  @Test
  public void providedInput3TestPart1() {
    assertEquals(226L, Day12.part1("example3.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 12 Part 1 Answer is: " + Day12.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertEquals(36L, Day12.part2("example1.txt"));
  }

  @Test
  public void providedInput2TestPart2() {
    assertEquals(103L, Day12.part2("example2.txt"));
  }

  @Test
  public void providedInput3TestPart2() {
    assertEquals(3509L, Day12.part2("example3.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 12 Part 2 Answer is: " + Day12.part2("input.txt"));
  }
}
