package year.year2022.day13;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day13 implements Day {
    enum Result {
        LEFT,
        RIGHT,
        NONE
    }

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int sum = 0;
        int i = 1;

        while (scanner.hasNext()) {
            String leftString = scanner.next();
            String rightString = scanner.next();
            Packet leftPacket = getElements(leftString);
            Packet rightPacket = getElements(rightString);
            Result result = compare(leftPacket, rightPacket);
            if (result == Result.LEFT)
                sum += i;
            i++;
        }

        return sum;
    }

    private Packet getElements(String string) {
        return getElements(string, 1, new ArrayList<>());
    }

    private Packet getElements(String string, int i, ArrayList<Element> elements) {
        char c = string.charAt(i);

        if (Character.isDigit(c)) {
            int j = i + 1;
            while (Character.isDigit(string.charAt(j)))
                j++;
            elements.add(new Value(Integer.parseInt(string.substring(i, j))));
            i = j;
        } else if (c == '[') {
            Packet packet = getElements(string, i + 1, new ArrayList<>());
            elements.add(packet);
            i += packet.toString().length();
        } else if (c == ']') {
            return new Packet(elements);
        } else {
            i++;
        }

        return getElements(string, i, elements);
    }

    private static Result compare(Packet leftPacket, Packet rightPacket) {
        ArrayList<Element> leftElements = leftPacket.elements;
        ArrayList<Element> rightElements = rightPacket.elements;
        Result result = Result.NONE;

        for (int i = 0; i < leftElements.size() && i < rightElements.size(); i++) {
            Element leftElement = leftPacket.elements.get(i);
            Element rightElement = rightPacket.elements.get(i);

            if (leftElement instanceof Value && rightElement instanceof Value) {
                int left = ((Value) leftElement).value;
                int right = ((Value) rightElement).value;

                if (left < right) {
                    result = Result.LEFT;
                } else if (right < left) {
                    result = Result.RIGHT;
                }
            } else if (leftElement instanceof Value && rightElement instanceof Packet) {
                ArrayList<Element> elements = new ArrayList<>();
                elements.add(leftElement);
                result = compare(new Packet(elements), (Packet) rightElement);
            } else if (leftElement instanceof Packet && rightElement instanceof Value) {
                ArrayList<Element> elements = new ArrayList<>();
                elements.add(rightElement);
                result = compare((Packet) leftElement, new Packet(elements));
            } else if (leftElement instanceof Packet && rightElement instanceof Packet) {
                result = compare((Packet) leftElement, (Packet) rightElement);
            }

            if (result != Result.NONE) {
                return result;
            }
        }

        if (leftElements.size() < rightElements.size()) {
            return Result.LEFT;
        } else if (rightElements.size() < leftElements.size()) {
            return Result.RIGHT;
        } else {
            return Result.NONE;
        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Packet> packetList = new ArrayList<>();

        while (scanner.hasNext()) {
            String packetString = scanner.next();
            Packet packet = getElements(packetString);
            packetList.add(packet);
        }

        Packet divider1 = getElements("[[2]]");
        Packet divider2 = getElements("[[6]]");
        packetList.add(divider1);
        packetList.add(divider2);

        Packet[] packets = new Packet[packetList.size()];
        for (int i = 0; i < packetList.size(); i++) {
            packets[i] = packetList.get(i);
        }

        Arrays.sort(packets);

        int i = getInteger(divider1, packets);
        int j = getInteger(divider2, packets);

        return i * j;
    }

    private static int getInteger(Packet divider1, Packet[] packets) {
        int i = 0;
        while (++i <= packets.length) {
            if (packets[i-1] == divider1)
                break;
        }
        return i;
    }

    private abstract static class Element {
    }

    private static class Packet extends Element implements Comparable<Packet> {
        private final ArrayList<Element> elements;

        public Packet(ArrayList<Element> elements) {
            this.elements = elements;
        }

        @Override
        public String toString() {
            if (elements.isEmpty())
                return "[]";
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < elements.size() - 1; i++)
                sb.append(elements.get(i).toString()).append(",");
            sb.append(elements.get(elements.size() - 1).toString());
            sb.append("]");
            return sb.toString();
        }

        @Override
        public int compareTo(Packet packet) {
            Result result = compare(this, packet);
            return result == Result.LEFT ? -1 : result == Result.RIGHT ? 1 : 0;
        }
    }

    private static class Value extends Element {
        private final int value;

        public Value(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
