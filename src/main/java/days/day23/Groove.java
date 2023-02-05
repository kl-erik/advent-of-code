package days.day23;

import java.util.Arrays;

public class Groove {
    private final Elf[][] elves;

    public Groove(Elf[][] elves) {
        this.elves = elves;
    }

    public boolean hasAdjacentElf(Elf elf) {
        if (hasAdjacentElfNorth(elf)) return true;
        if (hasAdjacentElfSouth(elf)) return true;
        if (hasAdjacentElfWest(elf)) return true;
        return hasAdjacentElfEast(elf);
    }

    public boolean hasAdjacentElfNorth(Elf elf) {
        int y = elf.y;
        int x = elf.x;

        if (y > 0) {
            if (elves[y-1][x] != null) {
                return true;
            }

            if (x > 0) {
                if (elves[y-1][x-1] != null) {
                    return true;
                }
            }

            if (x < elves[y-1].length - 1) {
                return elves[y - 1][x + 1] != null;
            }
        }

        return false;
    }

    public boolean hasAdjacentElfSouth(Elf elf) {
        int y = elf.y;
        int x = elf.x;

        if (y < elves.length - 1) {
            if (elves[y+1][x] != null) {
                return true;
            }

            if (x > 0) {
                if (elves[y+1][x-1] != null) {
                    return true;
                }
            }

            if (x < elves[y+1].length - 1) {
                return elves[y + 1][x + 1] != null;
            }
        }

        return false;
    }

    public boolean hasAdjacentElfWest(Elf elf) {
        int y = elf.y;
        int x = elf.x;

        if (x > 0) {
            if (elves[y][x-1] != null) {
                return true;
            }

            if (y > 0) {
                if (elves[y-1][x-1] != null) {
                    return true;
                }
            }

            if (y < elves.length - 1) {
                return elves[y+1][x-1] != null;
            }
        }

        return false;
    }

    public boolean hasAdjacentElfEast(Elf elf) {
        int y = elf.y;
        int x = elf.x;

        if (x < elves[y].length) {
            if (elves[y][x+1] != null) {
                return true;
            }

            if (y > 0) {
                if (elves[y-1][x+1] != null) {
                    return true;
                }
            }

            if (y < elves.length - 1) {
                return elves[y+1][x + 1] != null;
            }
        }

        return false;
    }

    public Elf[][] getElves() {
        return elves;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Elf[] row : elves) {
            for (Elf elf : row) {
                if (elf == null) {
                    stringBuilder.append('.');
                } else {
                    stringBuilder.append('#');
                }
            }

            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
