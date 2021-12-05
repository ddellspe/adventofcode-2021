package net.ddellspe.day05;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day05Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day05.part1("example.txt"), is(equalTo(5L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 05 Part 1 Answer is: " + Day05.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day05.part2("example.txt"), is(equalTo(12L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 05 Part 2 Answer is: " + Day05.part2("input.txt"));
  }
}
