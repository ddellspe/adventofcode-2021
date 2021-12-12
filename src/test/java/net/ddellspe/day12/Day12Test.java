package net.ddellspe.day12;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day12Test {
  @Test
  public void providedInput1TestPart1() {
    assertThat(Day12.part1("example1.txt"), is(equalTo(10L)));
  }

  @Test
  public void providedInput2TestPart1() {
    assertThat(Day12.part1("example2.txt"), is(equalTo(19L)));
  }

  @Test
  public void providedInput3TestPart1() {
    assertThat(Day12.part1("example3.txt"), is(equalTo(226L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 12 Part 1 Answer is: " + Day12.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertThat(Day12.part2("example1.txt"), is(equalTo(36L)));
  }

  @Test
  public void providedInput2TestPart2() {
    assertThat(Day12.part2("example2.txt"), is(equalTo(103L)));
  }

  @Test
  public void providedInput3TestPart2() {
    assertThat(Day12.part2("example3.txt"), is(equalTo(3509L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 12 Part 2 Answer is: " + Day12.part2("input.txt"));
  }
}
