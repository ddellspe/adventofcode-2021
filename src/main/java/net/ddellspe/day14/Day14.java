package net.ddellspe.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.ddellspe.utils.InputUtils;

public class Day14 {
  private Day14() {}

  public static long part1(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day14.class);
    String template = data.get(0);
    Map<String, String> rules = new HashMap<>();
    boolean blank = false;
    for (String rule : data) {
      if (rule.isEmpty() && !blank) {
        blank = true;
        continue;
      }
      if (blank) {
        rules.put(rule.split(" -> ")[0], rule.split(" -> ")[1]);
      }
    }
    Map<String, Long> counts = new HashMap<>();
    for (int i = 0; i < template.length() - 1; i++) {
      counts.merge(template.substring(i, i + 2), 1L, Long::sum);
    }
    for (int i = 0; i < 10; i++) {
      Map<String, Long> newCounts = new HashMap<>();
      for (Entry<String, Long> entry : counts.entrySet()) {
        newCounts.merge(
            entry.getKey().charAt(0) + rules.get(entry.getKey()), entry.getValue(), Long::sum);
        newCounts.merge(
            rules.get(entry.getKey()) + entry.getKey().charAt(1), entry.getValue(), Long::sum);
      }
      counts.clear();
      counts.putAll(newCounts);
    }
    Map<String, Long> charCounts = new HashMap<>();
    for (Entry<String, Long> cnt : counts.entrySet()) {
      charCounts.merge(cnt.getKey().substring(0, 1), cnt.getValue(), Long::sum);
    }
    charCounts.merge(template.substring(template.length() - 1), 1L, Long::sum);

    return charCounts.values().stream().max(Long::compareTo).get()
        - charCounts.values().stream().min(Long::compareTo).get();
  }

  public static long part2(String filename) {
    List<String> data = InputUtils.stringPerLine(filename, Day14.class);
    String template = data.get(0);
    Map<String, String> rules = new HashMap<>();
    boolean blank = false;
    for (String rule : data) {
      if (rule.isEmpty() && !blank) {
        blank = true;
        continue;
      }
      if (blank) {
        rules.put(rule.split(" -> ")[0], rule.split(" -> ")[1]);
      }
    }
    Map<String, Long> counts = new HashMap<>();
    for (int i = 0; i < template.length() - 1; i++) {
      counts.merge(template.substring(i, i + 2), 1L, Long::sum);
    }
    for (int i = 0; i < 40; i++) {
      Map<String, Long> newCounts = new HashMap<>();
      for (Entry<String, Long> entry : counts.entrySet()) {
        newCounts.merge(
            entry.getKey().charAt(0) + rules.get(entry.getKey()), entry.getValue(), Long::sum);
        newCounts.merge(
            rules.get(entry.getKey()) + entry.getKey().charAt(1), entry.getValue(), Long::sum);
      }
      counts.clear();
      counts.putAll(newCounts);
    }
    Map<String, Long> charCounts = new HashMap<>();
    for (Entry<String, Long> cnt : counts.entrySet()) {
      charCounts.merge(cnt.getKey().substring(0, 1), cnt.getValue(), Long::sum);
    }
    charCounts.merge(template.substring(template.length() - 1), 1L, Long::sum);

    return charCounts.values().stream().max(Long::compareTo).get()
        - charCounts.values().stream().min(Long::compareTo).get();
  }
}
