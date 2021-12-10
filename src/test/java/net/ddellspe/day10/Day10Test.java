package net.ddellspe.day10;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day10Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day10.part1("example.txt"), is(equalTo(26397L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 10 Part 1 Answer is: " + Day10.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day10.part2("example.txt"), is(equalTo(288957L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 10 Part 2 Answer is: " + Day10.part2("input.txt"));
  }
}
