package net.ddellspe.day16;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day16Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day16.part1("example.txt"), is(equalTo(16L)));
    assertThat(Day16.part1("example2.txt"), is(equalTo(12L)));
    assertThat(Day16.part1("example3.txt"), is(equalTo(23L)));
    assertThat(Day16.part1("example4.txt"), is(equalTo(31L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 16 Part 1 Answer is: " + Day16.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day16.part2("example5.txt"), is(equalTo(3L)));
    assertThat(Day16.part2("example6.txt"), is(equalTo(54L)));
    assertThat(Day16.part2("example7.txt"), is(equalTo(7L)));
    assertThat(Day16.part2("example8.txt"), is(equalTo(9L)));
    assertThat(Day16.part2("example9.txt"), is(equalTo(1L)));
    assertThat(Day16.part2("example10.txt"), is(equalTo(0L)));
    assertThat(Day16.part2("example11.txt"), is(equalTo(0L)));
    assertThat(Day16.part2("example12.txt"), is(equalTo(1L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 16 Part 2 Answer is: " + Day16.part2("input.txt"));
  }
}
