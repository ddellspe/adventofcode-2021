package net.ddellspe.day06;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day06Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day06.part1("example.txt"), is(equalTo(5934L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 06 Part 1 Answer is: " + Day06.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day06.part2("example.txt"), is(equalTo(26984457539L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 06 Part 2 Answer is: " + Day06.part2("input.txt"));
  }
}
