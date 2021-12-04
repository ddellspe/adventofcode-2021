package net.ddellspe.day04;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day04Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day04.part1("example.txt"), is(equalTo(4512L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 04 Part 1 Answer is: " + Day04.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day04.part2("example.txt"), is(equalTo(1924L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 04 Part 2 Answer is: " + Day04.part2("input.txt"));
  }
}
