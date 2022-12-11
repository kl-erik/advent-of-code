package days.day11;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day11 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Monkey[] monkeys = parse(file);
        int[] inspections = new int[monkeys.length];

        for (int round = 1; round <= 20; round++) {
            for (int i = 0; i < monkeys.length; i++) {
                Monkey monkey = monkeys[i];

                while (!monkey.items.isEmpty()) {
                    long item = monkey.items.remove();
                    item = monkey.inspect(item);
                    inspections[i]++;
                    item /= 3;
                    int toMonkey = monkey.test(item);
                    monkeys[toMonkey].items.add(item);
                }
            }
        }

        return calcMonkeyBusiness(inspections);
    }

    private static Monkey[] parse(File file) throws FileNotFoundException {
        ArrayList<Monkey> monkeyList = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            LinkedList<Long> items = new LinkedList<>();
            String[] operation = new String[2];
            int test = 1;
            int trueMonkey = 0;
            int falseMonkey = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.isEmpty())
                    break;

                if (line.contains("Starting items")) {
                    String[] itemList = line.split("Starting items: ")[1].split(", ");

                    for (String itemString : itemList) {
                        items.add(Long.parseLong(itemString));
                    }
                } else if (line.contains("Operation")) {
                    operation = line.split("Operation: new = old ")[1].split(" ");
                } else if (line.contains("Test")) {
                    test = Integer.parseInt(line.split("Test: divisible by ")[1]);
                } else if (line.contains("If true")) {
                    trueMonkey = Integer.parseInt(line.split("If true: throw to monkey ")[1]);
                } else if (line.contains("If false")) {
                    falseMonkey = Integer.parseInt(line.split("If false: throw to monkey ")[1]);
                }
            }

            Monkey monkey = getMonkey(items, operation, test, trueMonkey, falseMonkey);
            monkeyList.add(monkey);
        }

        return toArray(monkeyList);
    }

    private static Monkey getMonkey(LinkedList<Long> items, String[] operation, int test, int trueMonkey, int falseMonkey) {
        if (operation[1].equals("old")) {
            if (operation[0].equals("+")) {
                return new Monkey(items) {
                    @Override
                    public long inspect(long item) {
                        return item + item;
                    }

                    @Override
                    public int test(long item) {
                        return item % test == 0 ? trueMonkey : falseMonkey;
                    }
                };
            } else {
                return new Monkey(items) {
                    @Override
                    public long inspect(long item) {
                        return item * item;
                    }

                    @Override
                    public int test(long item) {
                        return item % test == 0 ? trueMonkey : falseMonkey;
                    }
                };
            }
        } else {
            int increase = Integer.parseInt(operation[1]);

            if (operation[0].equals("+")) {
                return new Monkey(items) {
                    @Override
                    public long inspect(long item) {
                        return item + increase;
                    }

                    @Override
                    public int test(long item) {
                        return item % test == 0 ? trueMonkey : falseMonkey;
                    }
                };
            } else {
                return new Monkey(items) {
                    @Override
                    public long inspect(long item) {
                        return item * increase;
                    }

                    @Override
                    public int test(long item) {
                        return item % test == 0 ? trueMonkey : falseMonkey;
                    }
                };
            }
        }
    }

    private static Monkey[] toArray(ArrayList<Monkey> monkeyList) {
        Monkey[] monkeys = new Monkey[monkeyList.size()];

        for (int i = 0; i < monkeyList.size(); i++) {
            monkeys[i] = monkeyList.get(i);
        }

        return monkeys;
    }

    private static int calcMonkeyBusiness(int[] inspections) {
        int mostInspections1 = 0;
        int mostInspections2 = 0;

        for (int nInspections : inspections) {
            if (nInspections > mostInspections1) {
                if (mostInspections1 > mostInspections2) {
                    mostInspections2 = mostInspections1;
                }

                mostInspections1 = nInspections;
            } else if (nInspections > mostInspections2) {
                mostInspections2 = nInspections;
            }
        }

        return mostInspections1 * mostInspections2;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }

    abstract static class Monkey implements KeepAwayPlayable {
        LinkedList<Long> items;

        public Monkey(LinkedList<Long> items) {
            this.items = items;
        }
    }

    interface KeepAwayPlayable {
        long inspect(long item);
        int test(long item);
    }
}
