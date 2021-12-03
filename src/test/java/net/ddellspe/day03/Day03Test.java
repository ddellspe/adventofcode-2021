package net.ddellspe.day03;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day03Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day03.part1("example.txt"), is(equalTo(198L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 03 Part 1 Answer is: " + Day03.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day03.part2("example.txt"), is(equalTo(230L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 03 Part 2 Answer is: " + Day03.part2("input.txt"));
  }
}
