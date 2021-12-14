package net.ddellspe.day14;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day14Test {
  @Test
  public void providedInput1TestPart1() {
    assertThat(Day14.part1("example.txt"), is(equalTo(1588L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 14 Part 1 Answer is: " + Day14.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertThat(Day14.part2("example.txt"), is(equalTo(2188189693529L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 14 Part 2 Answer is: " + Day14.part2("input.txt"));
  }
}
