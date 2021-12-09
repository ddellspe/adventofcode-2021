package net.ddellspe.day09;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day09Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day09.part1("example.txt"), is(equalTo(15L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 09 Part 1 Answer is: " + Day09.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day09.part2("example.txt"), is(equalTo(1134L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 09 Part 2 Answer is: " + Day09.part2("input.txt"));
  }
}
