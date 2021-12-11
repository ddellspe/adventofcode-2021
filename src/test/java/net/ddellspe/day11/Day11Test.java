package net.ddellspe.day11;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day11Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day11.part1("example.txt"), is(equalTo(1656L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 11 Part 1 Answer is: " + Day11.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day11.part2("example.txt"), is(equalTo(195L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 11 Part 2 Answer is: " + Day11.part2("input.txt"));
  }
}
