package net.ddellspe.day18;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day18Test {
  @Test
  public void providedInputTestPart1() {
    assertThat(
        Day18.reduce(
            "[[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]"),
        is(equalTo("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]")));
    assertThat(
        Day18.reduce(
            "[[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]],[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]]"),
        is(equalTo("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]")));
    assertThat(
        Day18.reduce("[[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]],[2,9]]"),
        is(equalTo("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]")));
    assertThat(Day18.sum("[[1,2],[[3,4],5]]"), is(equalTo(143L)));
    assertThat(Day18.sum("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"), is(equalTo(1384L)));
    assertThat(Day18.sum("[[[[1,1],[2,2]],[3,3]],[4,4]]"), is(equalTo(445L)));
    assertThat(Day18.sum("[[[[3,0],[5,3]],[4,4]],[5,5]]"), is(equalTo(791L)));
    assertThat(Day18.sum("[[[[5,0],[7,4]],[5,5]],[6,6]]"), is(equalTo(1137L)));
    assertThat(
        Day18.sum("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"), is(equalTo(3488L)));
    assertThat(Day18.part1("example.txt"), is(equalTo(4140L)));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 18 Part 1 Answer is: " + Day18.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertThat(Day18.part2("example.txt"), is(equalTo(3993L)));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 18 Part 2 Answer is: " + Day18.part2("input.txt"));
  }
}
