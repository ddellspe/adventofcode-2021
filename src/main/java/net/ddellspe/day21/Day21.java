package net.ddellspe.day21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day21 {
  private Day21() {}

  public static class GameState implements Comparable<GameState> {
    Point positions;
    Point scores;
    int turn;

    public GameState(Point positions, Point scores, int turn) {
      this.positions = positions;
      this.scores = scores;
      this.turn = turn;
    }

    public GameState copy() {
      return new GameState(
          new Point(positions.getX(), positions.getY()),
          new Point(scores.getX(), scores.getY()),
          turn);
    }

    public int getPosition() {
      if (turn == 0) {
        return positions.getX();
      } else {
        return positions.getY();
      }
    }

    public int getScore() {
      if (turn == 0) {
        return scores.getX();
      } else {
        return scores.getY();
      }
    }

    public void setPosition(int pos) {
      if (turn == 0) {
        positions.setX(pos);
      } else {
        positions.setY(pos);
      }
    }

    public void setScore(int score) {
      if (turn == 0) {
        scores.setX(score);
      } else {
        scores.setY(score);
      }
    }

    public int getTurn() {
      return turn;
    }

    public void changeTurn() {
      turn = 1 - turn;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      GameState gameState = (GameState) o;
      return turn == gameState.turn
          && positions.equals(gameState.positions)
          && scores.equals(gameState.scores);
    }

    @Override
    public int hashCode() {
      return Objects.hash(positions, scores, turn);
    }

    @Override
    public int compareTo(GameState o) {
      return (scores.getX() + scores.getY()) - (o.scores.getX() + o.scores.getY());
    }
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day21.class);
    int player1 = Integer.parseInt(data.get(0).split(": ")[1]);
    long p1Score = 0;
    int player2 = Integer.parseInt(data.get(1).split(": ")[1]);
    long p2Score = 0;
    int rolls = 1;
    int player = 0;
    while (p1Score < 1000 && p2Score < 1000) {
      if (player == 0) {
        player1 += (((rolls + (rolls + 2)) * 3 / 2) % 10);
        player1 = ((player1 - 1) % 10 + 1);
      } else {
        player2 += (((rolls + (rolls + 2)) * 3 / 2) % 10);
        player2 = ((player2 - 1) % 10 + 1);
      }
      if (player == 0) {
        p1Score += player1;
      } else {
        p2Score += player2;
      }
      player = 1 - player;
      rolls += 3;
    }
    rolls--;
    return p1Score >= 1000 ? p2Score * rolls : p1Score * rolls;
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day21.class);
    int player1 = Integer.parseInt(data.get(0).split(": ")[1]);
    int player2 = Integer.parseInt(data.get(1).split(": ")[1]);
    long[] winners = new long[2];
    Map<GameState, Long> counts = new HashMap<>();
    GameState state = new GameState(new Point(player1, player2), new Point(0, 0), 0);
    counts.put(state, 1L);
    Map<Integer, Integer> countsPerSum = new HashMap<>();
    for (int i = 1; i <= 3; i++) {
      for (int j = 1; j <= 3; j++) {
        for (int k = 1; k <= 3; k++) {
          countsPerSum.merge(i + j + k, 1, Integer::sum);
        }
      }
    }
    while (!counts.isEmpty()) {
      GameState tempState = counts.keySet().stream().min(GameState::compareTo).get();
      long count = counts.get(tempState);
      counts.remove(tempState);
      for (Entry<Integer, Integer> sum : countsPerSum.entrySet()) {
        GameState newState = tempState.copy();
        newState.setPosition((newState.getPosition() + sum.getKey() - 1) % 10 + 1);
        newState.setScore(newState.getScore() + newState.getPosition());
        if (newState.getScore() >= 21) {
          winners[newState.getTurn()] += (count * sum.getValue());
        } else {
          newState.changeTurn();
          counts.merge(newState, (count * sum.getValue()), Long::sum);
        }
      }
    }
    return Arrays.stream(winners).max().getAsLong();
  }
}
