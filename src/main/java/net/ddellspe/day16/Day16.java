package net.ddellspe.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import net.ddellspe.utils.InputUtils;

public class Day16 {
  private Day16() {}

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
      switch (typeId) {
        case 0:
          return packets.stream().mapToLong(Packet::getPacketScore).sum();
        case 1:
          return packets.stream().mapToLong(Packet::getPacketScore).reduce(1L, (p, c) -> p * c);
        case 2:
          return packets.stream().mapToLong(Packet::getPacketScore).min().getAsLong();
        case 3:
          return packets.stream().mapToLong(Packet::getPacketScore).max().getAsLong();
        case 4:
          return n;
        case 5:
        case 6:
        case 7:
          Packet packet1 = packets.get(0);
          Packet packet2 = packets.get(1);
          switch (typeId) {
            case 5:
              return packet1.getPacketScore() > packet2.getPacketScore() ? 1L : 0L;
            case 6:
              return packet1.getPacketScore() < packet2.getPacketScore() ? 1L : 0L;
            case 7:
              return packet1.getPacketScore() == packet2.getPacketScore() ? 1L : 0L;
          }
        default:
          throw new RuntimeException("Unknown type ID: " + typeId); // $COVERAGE-IGNORE$
      }
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
    List<String> data = InputUtils.stringPerLine(filename, Day16.class);
    Map<String, String> bitMap = new HashMap<>();
    for (int i = 0; i < 16; i++) {
      bitMap.put(
          Integer.toString(i, 16).toUpperCase(),
          String.format("%4s", (Integer.toString(i, 2))).replace(" ", "0"));
    }
    String packetBits =
        Arrays.stream(data.get(0).split("")).map(bitMap::get).reduce(String::concat).get();

    Queue<Packet> packets = new LinkedList<>();
    packets.add(readPacket(packetBits, 0));

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
    List<String> data = InputUtils.stringPerLine(filename, Day16.class);
    Map<String, String> bitMap = new HashMap<>();
    for (int i = 0; i < 16; i++) {
      bitMap.put(
          Integer.toString(i, 16).toUpperCase(),
          String.format("%4s", (Integer.toString(i, 2))).replace(" ", "0"));
    }
    String packetBits =
        Arrays.stream(data.get(0).split("")).map(bitMap::get).reduce(String::concat).get();

    Packet packet = readPacket(packetBits, 0);

    return packet.getPacketScore();
  }
}
