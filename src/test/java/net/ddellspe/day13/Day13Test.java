package net.ddellspe.day13;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day13Test {
  @Test
  public void providedInput1TestPart1() {
    assertThat(Day13.part1("example.txt"), is(equalTo(17L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 13 Part 1 Answer is: " + Day13.part1("input.txt"));
  }

  @Test
  public void providedInput1TestPart2() {
    assertThat(Day13.part2("example.txt"), is(equalTo("█████\n█   █\n█   █\n█   █\n█████\n")));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 13 Part 2 Answer is: \n" + Day13.part2("input.txt"));
  }
}
