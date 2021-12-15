package net.ddellspe.day15;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day15Test {
  @Test
  public void providedInput1TestPart1() {
    assertThat(Day15.part1("example.txt"), is(equalTo(40L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 15 Part 1 Answer is: " + Day15.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertThat(Day15.part2("example.txt"), is(equalTo(315L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 15 Part 2 Answer is: " + Day15.part2("input.txt"));
  }
}
