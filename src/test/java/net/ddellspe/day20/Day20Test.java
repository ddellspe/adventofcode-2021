package net.ddellspe.day20;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day20Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day20.part1("example.txt"), is(equalTo(35L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 20 Part 1 Answer is: " + Day20.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day20.part2("example.txt"), is(equalTo(3351L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 20 Part 2 Answer is: " + Day20.part2("input.txt"));
  }
}
