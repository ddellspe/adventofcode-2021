package net.ddellspe.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day13Test {
  @Test
  public void providedInput1TestPart1() {
    assertEquals(Day13.part1("example.txt"), 17L);
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 13 Part 1 Answer is: " + Day13.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertEquals(Day13.part2("example.txt"), "█████\n█   █\n█   █\n█   █\n█████\n");
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 13 Part 2 Answer is: \n" + Day13.part2("input.txt"));
  }
}
