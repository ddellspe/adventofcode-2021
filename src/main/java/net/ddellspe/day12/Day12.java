package net.ddellspe.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day12.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    Map<String, Set<String>> graph = new HashMap<>();
    for (String line : Objects.requireNonNull(data)) {
      String[] path = line.split("-");
      if (graph.containsKey(path[0])) {
        graph.get(path[0]).add(path[1]);
      } else {
        graph.put(
            path[0],
            new HashSet<>() {
              {
                add(path[1]);
              }
            });
      }
      if (graph.containsKey(path[1])) {
        graph.get(path[1]).add(path[0]);
      } else {
        graph.put(
            path[1],
            new HashSet<>() {
              {
                add(path[0]);
              }
            });
      }
    }
    Deque<List<String>> todo = new LinkedList<>();
    todo.add(
        new ArrayList<>() {
          {
            add("start");
          }
        });
    Set<List<String>> completePaths = new HashSet<>();
    while (!todo.isEmpty()) {
      List<String> path = todo.pop();
      if (path.get(path.size() - 1).equals("end")) {
        completePaths.add(path);
        continue;
      }
      for (String choice : graph.get(path.get(path.size() - 1))) {
        if (choice.equals(choice.toUpperCase()) || !path.contains(choice)) {
          List<String> newPath = new ArrayList<>(path);
          newPath.add(choice);
          todo.add(newPath);
        }
      }
    }
    return completePaths.size();
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    Map<String, Set<String>> graph = new HashMap<>();
    for (String line : Objects.requireNonNull(data)) {
      String[] path = line.split("-");
      if (graph.containsKey(path[0])) {
        graph.get(path[0]).add(path[1]);
      } else {
        graph.put(
            path[0],
            new HashSet<>() {
              {
                add(path[1]);
              }
            });
      }
      if (graph.containsKey(path[1])) {
        graph.get(path[1]).add(path[0]);
      } else {
        graph.put(
            path[1],
            new HashSet<>() {
              {
                add(path[0]);
              }
            });
      }
    }
    List<String> initial =
        new ArrayList<>() {
          {
            add("start");
          }
        };
    Map<List<String>, Boolean> doubledSmall =
        new HashMap<>() {
          {
            put(initial, false);
          }
        };
    Deque<List<String>> todo = new LinkedList<>();
    todo.add(initial);
    Set<List<String>> completePaths = new HashSet<>();
    while (!todo.isEmpty()) {
      List<String> path = todo.pop();
      boolean alreadyDoubled = doubledSmall.get(path);
      if (path.get(path.size() - 1).equals("end")) {
        completePaths.add(path);
        continue;
      }
      for (String choice :
          graph.get(path.get(path.size() - 1)).stream()
              .filter(c -> !c.equals("start"))
              .collect(Collectors.toSet())) {
        if (choice.equals(choice.toUpperCase())) {
          List<String> newPath = new ArrayList<>(path);
          newPath.add(choice);
          todo.add(newPath);
          doubledSmall.put(newPath, alreadyDoubled);
        } else if (!alreadyDoubled
            && path.stream().filter(element -> element.equals(choice)).count() == 1) {
          List<String> newPath = new ArrayList<>(path);
          newPath.add(choice);
          todo.add(newPath);
          doubledSmall.put(newPath, true);
        } else if (!path.contains(choice)) {
          List<String> newPath = new ArrayList<>(path);
          newPath.add(choice);
          todo.add(newPath);
          doubledSmall.put(newPath, alreadyDoubled);
        }
      }
    }
    return completePaths.size();
  }
}
