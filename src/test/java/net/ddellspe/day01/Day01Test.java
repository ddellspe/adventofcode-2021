package net.ddellspe.day01;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day01Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day01.part1("example.txt"), is(equalTo(7L)));
  }

  @Test
  public void providedInputTestPart1Optimized() {
    assertThat(Day01.part1Optimized("example.txt"), is(equalTo(7L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 01 Part 1 Answer is: " + Day01.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day01.part2("example.txt"), is(equalTo(5L)));
  }

  @Test
  public void providedInputTestPart2Optimized() {
    assertThat(Day01.part2Optimized("example.txt"), is(equalTo(5L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 01 Part 2 Answer is: " + Day01.part2("input.txt"));
  }
}
