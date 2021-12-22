package net.ddellspe.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day18Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(
        "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
        Day18.reduce(
            "[[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]"));
    assertEquals(
        "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",
        Day18.reduce(
            "[[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]],[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]]"));
    assertEquals(
        "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]",
        Day18.reduce("[[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]],[2,9]]"));
    assertEquals(143L, Day18.sum("[[1,2],[[3,4],5]]"));
    assertEquals(1384L, Day18.sum("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));
    assertEquals(445L, Day18.sum("[[[[1,1],[2,2]],[3,3]],[4,4]]"));
    assertEquals(791L, Day18.sum("[[[[3,0],[5,3]],[4,4]],[5,5]]"));
    assertEquals(1137L, Day18.sum("[[[[5,0],[7,4]],[5,5]],[6,6]]"));
    assertEquals(3488L, Day18.sum("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"));
    assertEquals(4140L, Day18.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 18 Part 1 Answer is: " + Day18.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(3993L, Day18.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 18 Part 2 Answer is: " + Day18.part2("input.txt"));
  }
}
