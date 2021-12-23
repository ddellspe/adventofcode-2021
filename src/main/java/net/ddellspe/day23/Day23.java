package net.ddellspe.day23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import net.ddellspe.day21.Day21.GameState;
import net.ddellspe.utils.InputUtils;

public class Day23 {
  private Day23() {}

  public static final int[] MOVE_COSTS = {1, 10, 100, 1000};
  public static int NUM_EACH_TYPE = 0;

  public static class GameState {
    int[] positions;
    long cost;

    public GameState(int[] positions, long cost) {
      this.positions = positions;
      this.cost = cost;
    }

    public GameState moveUnit(int unit, int position, int price) {
      int[] newPos = Arrays.copyOf(positions, positions.length);
      newPos[unit] = position;
      return new GameState(newPos, cost + price);
    }

    public boolean isComplete() {
      for (int i = 0; i < positions.length; i++) {
        int type = getType(i);
        if (positions[i] < 7 || (positions[i] + 1) % 4 != type) {
          return false;
        }
      }
      return true;
    }

    public String toString() {
      int length = positions.length + 7;
      int[] occupied = new int[length];
      for (int i = 0; i < length; i++) {
        occupied[i] = -1;
      }
      for (int i = 0; i < positions.length; i++) {
        occupied[positions[i]] = i;
      }
      StringBuilder value = new StringBuilder();
      for (int i = 0; i < length; i++) {
        int type = getType(occupied[i]);
        if (type == -1) {
          value.append("x");
        } else {
          value.append(type);
        }
      }
      return value.toString();
    }

    public long getCost() {
      return cost;
    }
  }

  public static int getType(int unit) {
    if (unit == -1) {
      return -1;
    }
    return unit / NUM_EACH_TYPE;
  }

  private static boolean[] findValidHallPositions(int[] positions, int unit) {
    int length = positions.length + 7;
    int[] occupied = new int[length];
    for (int i = 0; i < length; i++) {
      occupied[i] = -1;
    }
    for (int i = 0; i < positions.length; i++) {
      occupied[positions[i]] = i;
    }

    boolean[] validPositions = new boolean[7];
    int currentPosition = positions[unit];
    int type = getType(unit);

    // check for blocking Amphipods ahead in the room
    for (int i = currentPosition - 4; i > 6; i -= 4) {
      if (occupied[i] > -1) {
        return validPositions;
      }
    }

    // figure out if the current Amphipods is blocked by Amphipods of different types
    if ((currentPosition + 1) % 4 == type) {
      boolean mustMove = false;
      for (int i = currentPosition + 4; i < length; i += 4) {
        if (getType(occupied[i]) != type) {
          mustMove = true;
          break;
        }
      }
      if (!mustMove) {
        return validPositions;
      }
    }

    // get the virtual hallway position coming out of the room
    int virtualPos = currentPosition;
    while (virtualPos > 10) {
      virtualPos -= 4;
    }

    // set valid positioning between the virtual hallway position and the given hallway positions
    for (int i = 0; i < 7; i++) {
      if (occupied[i] == -1 && checkHallwayClear(i, virtualPos, occupied)) {
        validPositions[i] = true;
      }
    }
    return validPositions;
  }

  private static boolean[] findValidRoomPositions(int[] positions, int unit) {
    int length = positions.length + 7;
    int[] occupied = new int[length];
    for (int i = 0; i < length; i++) {
      occupied[i] = -1;
    }
    for (int i = 0; i < positions.length; i++) {
      occupied[positions[i]] = i;
    }
    boolean[] validPositions = new boolean[length];

    int currentPosition = positions[unit];
    int type = getType(unit);
    int roomFirstSpot = type + 7;

    if (!checkHallwayClear(currentPosition, roomFirstSpot, occupied)) {
      return validPositions;
    }
    int target = roomFirstSpot;
    // get the deepest available spot in the room (if any door is occupied by a non-matching type,
    // it is not able to move into the room
    for (int i = 0; i < NUM_EACH_TYPE; i++) {
      if (occupied[roomFirstSpot + 4 * i] == -1) {
        target = roomFirstSpot + 4 * i;
      } else if (getType(occupied[roomFirstSpot + 4 * i]) != type) {
        return validPositions;
      }
    }
    // all positions are either occupied by appropriate Amphipods or empty with target being the
    // deepest index that is available.
    validPositions[target] = true;
    return validPositions;
  }

  private static boolean[] findValidPositions(int[] positions, int unit) {
    if (positions[unit] < 7) {
      // Amphipod is in the hallway
      return findValidRoomPositions(positions, unit);
    } else {
      // Amphipod is in a room
      return findValidHallPositions(positions, unit);
    }
  }

  private static boolean checkHallwayClear(int hallPos, int roomPos, int[] occupied) {
    int min = Math.min(hallPos + 1, roomPos - 5);
    int max = Math.max(hallPos - 1, roomPos - 6);

    for (int i = min; i <= max; i++) {
      if (occupied[i] != -1) {
        return false;
      }
    }
    return true;
  }

  private static int calcPrice(int unit, int from, int to) {
    // we're always going from hall to room or room to hall, no exceptions, so one of these will be
    // a hallway (from) and the other will be a room (to) the only concern is that the distance is
    // known, no other reason to worry here
    if (from > to) {
      int tmp = from;
      from = to;
      to = tmp;
    }

    int roomDepth = (to - 3) / 4;
    int roomDoorwayIndex = ((to + 1) % 4) * 2 + 3;
    int startEndCorrection = (from == 0 || from == 6) ? 1 : 0;
    // 2 * from because index 1 is to the left of the first room, 3 is to the right
    int dist = Math.abs(2 * from - roomDoorwayIndex) + roomDepth - startEndCorrection;
    int type = getType(unit);
    return MOVE_COSTS[type] * dist;
  }

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day23.class);
    NUM_EACH_TYPE = 2;
    int[] startingPositions = new int[NUM_EACH_TYPE * 4];
    // store positions for each type (types are in this part 1, 1, 2, 2, 3, 3, 4, 4)
    for (int i = 0; i < NUM_EACH_TYPE; i++) {
      String line = data.get(i + 2);
      for (int j = 0; j < 4; j++) {
        char c = line.charAt(2 * j + 3);
        int unit = (c - 'A') * NUM_EACH_TYPE;
        while (startingPositions[unit] != 0) {
          unit++;
        }
        startingPositions[unit] = 4 * i + j + 7;
      }
    }

    PriorityQueue<GameState> queue =
        new PriorityQueue<>(Comparator.comparingLong(GameState::getCost));
    queue.add(new GameState(startingPositions, 0));

    long best = Long.MAX_VALUE;
    Map<String, Long> alreadyProcessed = new HashMap<>();
    while (!queue.isEmpty()) {
      GameState toProcess = queue.poll();
      if (toProcess.cost >= best) {
        break;
      }

      for (int unit = 0; unit < startingPositions.length; unit++) {
        boolean[] validPos = findValidPositions(toProcess.positions, unit);
        for (int i = 0; i < validPos.length; i++) {
          if (!validPos[i]) {
            continue;
          }
          // This is a valid position to move to, try the move, and store score
          int price = calcPrice(unit, toProcess.positions[unit], i);
          // Calculate the price for this move
          GameState next = toProcess.moveUnit(unit, i, price);
          // Get the state for the move
          if (next.isComplete()) {
            best = Math.min(best, next.cost);
          } else {
            if (next.cost < alreadyProcessed.getOrDefault(next.toString(), Long.MAX_VALUE)) {
              // if the cost for the current state is less than the previously existing cost, keep
              // processing from it
              alreadyProcessed.put(next.toString(), next.cost);
              queue.add(next);
            }
          }
        }
      }
    }
    return best;
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day23.class);
    NUM_EACH_TYPE = 4;
    int[] startingPositions = new int[NUM_EACH_TYPE * 4];
    for (int i = 0; i < NUM_EACH_TYPE; i++) {
      String line = data.get(Math.min(i + 2, data.size() - 2));
      // inject depth 2 and 3 data from puzzle
      if (i == 1) {
        line = "  #D#C#B#A#  ";
      } else if (i == 2) {
        line = "  #D#B#A#C#  ";
      }
      for (int j = 0; j < 4; j++) {
        char c = line.charAt(2 * j + 3);
        int unit = (c - 'A') * NUM_EACH_TYPE;
        while (startingPositions[unit] != 0) {
          unit++;
        }
        startingPositions[unit] = 4 * i + j + 7;
      }
    }

    PriorityQueue<GameState> queue =
        new PriorityQueue<>(Comparator.comparingLong(GameState::getCost));
    queue.add(new GameState(startingPositions, 0));

    long best = Long.MAX_VALUE;
    Map<String, Long> alreadyProcessed = new HashMap<>();
    while (!queue.isEmpty()) {
      GameState toProcess = queue.poll();
      if (toProcess.cost >= best) {
        break;
      }

      for (int unit = 0; unit < startingPositions.length; unit++) {
        boolean[] validPos = findValidPositions(toProcess.positions, unit);
        for (int i = 0; i < validPos.length; i++) {
          if (!validPos[i]) {
            continue;
          }
          // This is a valid position to move to, try the move, and store score
          int price = calcPrice(unit, toProcess.positions[unit], i);
          // Calculate the price for this move
          GameState next = toProcess.moveUnit(unit, i, price);
          // Get the state for the move
          if (next.isComplete()) {
            best = Math.min(best, next.cost);
          } else {
            if (next.cost < alreadyProcessed.getOrDefault(next.toString(), Long.MAX_VALUE)) {
              // if the cost for the current state is less than the previously existing cost, keep
              // processing from it
              alreadyProcessed.put(next.toString(), next.cost);
              queue.add(next);
            }
          }
        }
      }
    }
    return best;
  }
}
