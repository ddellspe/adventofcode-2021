package net.ddellspe.day19;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day19Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day19.part1("example.txt"), is(equalTo(79L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 19 Part 1 Answer is: " + Day19.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day19.part2("example.txt"), is(equalTo(3621L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 19 Part 2 Answer is: " + Day19.part2("input.txt"));
  }
}
