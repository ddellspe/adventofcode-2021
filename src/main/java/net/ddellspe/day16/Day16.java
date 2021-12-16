package net.ddellspe.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day16 {
  public static List<String> readInData(String filename) {
    try (BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Day16.class.getResourceAsStream(filename))))) {
      return reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static class Packet {
    private Integer version;
    private Integer typeId;
    private Long n;
    private Integer i;
    private List<Packet> packets;

    public Packet(int version, int typeId, int i, List<Packet> packets) {
      this.version = version;
      this.typeId = typeId;
      this.n = null;
      this.i = i;
      this.packets = packets;
    }

    public Packet(int version, int typeId, long n, int i) {
      this.version = version;
      this.typeId = typeId;
      this.n = n;
      this.i = i;
      this.packets = null;
    }

    public Long getPacketScore() {
      if (typeId == 0) {
        return packets.stream().mapToLong(Packet::getPacketScore).sum();
      } else if (typeId == 1) {
        return packets.stream().mapToLong(Packet::getPacketScore).reduce(1L, (p, c) -> p * c);
      } else if (typeId == 2) {
        return packets.stream().mapToLong(Packet::getPacketScore).min().getAsLong();
      } else if (typeId == 3) {
        return packets.stream().mapToLong(Packet::getPacketScore).max().getAsLong();
      } else if (typeId == 4) {
        return n;
      } else if (typeId == 5) {
        Packet packet1 = packets.get(0);
        Packet packet2 = packets.get(1);
        return packet1.getPacketScore() > packet2.getPacketScore() ? 1L : 0L;
      } else if (typeId == 6) {
        Packet packet1 = packets.get(0);
        Packet packet2 = packets.get(1);
        return packet1.getPacketScore() < packet2.getPacketScore() ? 1L : 0L;
      } else if (typeId == 7) {
        Packet packet1 = packets.get(0);
        Packet packet2 = packets.get(1);
        return packet1.getPacketScore() == packet2.getPacketScore() ? 1L : 0L;
      }
      throw new RuntimeException("Unknown type ID: " + typeId);
    }

    public int getIndex() {
      return i;
    }

    public int getVersion() {
      return version;
    }

    public List<Packet> getPackets() {
      return packets;
    }

    public boolean hasPackets() {
      return packets != null && packets.size() > 0;
    }
  }

  public static String zeroPad(String str, int length) {
    if (str.length() > length) {
      throw new RuntimeException("Invalid String: " + str);
    } else if (str.length() == length) {
      return str;
    } else {
      return zeroPad("0" + str, length);
    }
  }

  public static int read(String str, int offset, int length) {
    return Integer.parseInt(str.substring(offset, offset + length), 2);
  }

  public static Packet readPacket(String bits, int strPos) {
    int index = strPos;
    int version = read(bits, index, 3);
    index += 3;
    int typeId = read(bits, index, 3);
    index += 3;
    if (typeId == 4) {
      boolean done = read(bits, index, 1) == 0;
      index += 1;
      StringBuilder representation = new StringBuilder();
      representation.append(bits, index, index + 4);
      index += 4;
      while (!done) {
        done = read(bits, index, 1) == 0;
        index += 1;
        representation.append(bits, index, index + 4);
        index += 4;
      }
      return new Packet(version, typeId, Long.parseLong(representation.toString(), 2), index);
    } else {
      int operator = read(bits, index, 1);
      index += 1;
      if (operator == 1) {
        int numPackets = read(bits, index, 11);
        index += 11;
        List<Packet> packets = new ArrayList<>();
        for (int i = 0; i < numPackets; i++) {
          Packet packet = readPacket(bits, index);
          index = packet.getIndex();
          packets.add(packet);
        }
        return new Packet(version, typeId, index, packets);
      } else {
        int length = read(bits, index, 15);
        index += 15;
        length += index;
        List<Packet> packets = new ArrayList<>();
        while (index < length) {
          Packet packet = readPacket(bits, index);
          index = packet.getIndex();
          packets.add(packet);
        }
        return new Packet(version, typeId, index, packets);
      }
    }
  }

  public static long part1(String filename) {
    List<String> data = readInData(filename);
    String packet = data.get(0);
    Map<String, String> bitMap = new HashMap<>();
    for (int i = 0; i < 16; i++) {
      bitMap.put(Integer.toString(i, 16).toUpperCase(), zeroPad(Integer.toString(i, 2), 4));
    }
    String packetBits =
        Arrays.stream(packet.split("")).map(bitMap::get).reduce(String::concat).get();
    Packet initPacket = readPacket(packetBits, 0);
    Queue<Packet> packets = new LinkedList<>();
    packets.add(initPacket);
    int versionSum = 0;
    while (!packets.isEmpty()) {
      Packet pack = packets.poll();
      versionSum += pack.getVersion();
      if (pack.hasPackets()) {
        packets.addAll(pack.getPackets());
      }
    }

    return versionSum;
  }

  public static long part2(String filename) {
    List<String> data = readInData(filename);
    String packet = data.get(0);
    Map<String, String> bitMap = new HashMap<>();
    for (int i = 0; i < 16; i++) {
      bitMap.put(Integer.toString(i, 16).toUpperCase(), zeroPad(Integer.toString(i, 2), 4));
    }
    String packetBits =
        Arrays.stream(packet.split("")).map(bitMap::get).reduce(String::concat).get();

    Packet initPacket = readPacket(packetBits, 0);

    return initPacket.getPacketScore();
  }
}
