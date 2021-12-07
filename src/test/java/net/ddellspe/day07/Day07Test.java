package net.ddellspe.day07;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day07Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day07.part1("example.txt"), is(equalTo(37L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 07 Part 1 Answer is: " + Day07.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day07.part2("example.txt"), is(equalTo(168L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 07 Part 2 Answer is: " + Day07.part2("input.txt"));
  }
}
