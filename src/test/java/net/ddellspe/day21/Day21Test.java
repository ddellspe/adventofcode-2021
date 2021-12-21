package net.ddellspe.day21;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day21Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(Day21.part1("example.txt"), is(equalTo(739785L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 21 Part 1 Answer is: " + Day21.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day21.part2("example.txt"), is(equalTo(444356092776315L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 21 Part 2 Answer is: " + Day21.part2("input.txt"));
  }
}
