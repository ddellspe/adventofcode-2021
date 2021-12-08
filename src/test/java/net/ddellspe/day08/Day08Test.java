package net.ddellspe.day08;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day08Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day08.part1("example.txt"), is(equalTo(26L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 08 Part 1 Answer is: " + Day08.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day08.part2("example.txt"), is(equalTo(61229L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 08 Part 2 Answer is: " + Day08.part2("input.txt"));
  }
}
