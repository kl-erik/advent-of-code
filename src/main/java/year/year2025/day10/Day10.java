package year.year2025.day10;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day10 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        List<Machine> machines = parse(file);
        return machines.stream().mapToInt(this::findMinToggles).sum();
    }

    private int findMinToggles(Machine machine) {
        char[] finalState = machine.state;
        int[][] buttons = machine.buttons;

        char[] initState = new char[finalState.length];
        Arrays.fill(initState, '.');
        for (int n = 0; n < buttons.length; n++) {
            Set<Result> results = toggleButtons(initState, buttons, n, 0);

            for (Result result : results) {
                if (Arrays.equals(result.state, finalState)) {
                    return n;
                }
            }
        }

        throw new IllegalArgumentException("No solution found");
    }

    private Set<Result> toggleButtons(char[] state, int[][] buttons, int n, int i) {
        if (n == 0) {
            return Collections.singleton(new Result((state)));
        }

        Set<Result> results = new HashSet<>();

        for (int j = i; j < buttons.length; j++) {
            char[] newState = toggleButton(state, buttons[j]);
            results.addAll(toggleButtons(newState, buttons, n - 1, j + 1));
        }

        return results;
    }

    private char[] toggleButton(char[] state, int[] button) {
        char[] newState = Arrays.copyOf(state, state.length);
        for (int index : button) {
            newState[index] = newState[index] == '.' ? '#' : '.';
        }
        return newState;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Loader.loadNativeLibraries();
        MPSolver solver = MPSolver.createSolver("SAT");
        if (solver == null) {
            throw new RuntimeException("Could not create solver");
        }

        List<Machine> machines = parse(file);
        int[] values = new int[machines.size()];

        for (int machineIndex = 0; machineIndex < machines.size(); machineIndex++) {
            Machine machine = machines.get(machineIndex);

            // each button is a variable
            MPVariable[] variables = new MPVariable[machine.buttons.length];
            for (int i = 0; i < variables.length; i++) {
                variables[i] = solver.makeIntVar(0, Integer.MAX_VALUE, "x" + i);
            }

            // each limit is a constraint (same lb and ub results in an equals constraint)
            MPConstraint[] constraints = new MPConstraint[machine.limits.length];
            for (int i = 0; i < machine.limits.length; i++) {
                constraints[i] = solver.makeConstraint(machine.limits[i], machine.limits[i]);
            }

            // combine variables and constraints
            int[][] coefficients = getCoefficients(machine.limits, machine.buttons);
            for (int i = 0; i < coefficients.length; i++) {
                for (int j = 0; j < coefficients[i].length; j++) {
                    constraints[i].setCoefficient(variables[j], coefficients[i][j]);
                }
            }

            // objective is to minimize the sum of the variables
            MPObjective objective = solver.objective();
            for (MPVariable variable : variables) {
                objective.setCoefficient(variable, 1);
            }
            objective.setMinimization();

            MPSolver.ResultStatus resultStatus = solver.solve();

            if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
                throw new RuntimeException("No optimal solution found");
            }

            values[machineIndex] = (int) objective.value();
            solver.clear();
        }

        return Arrays.stream(values).sum();
    }

    private static int[][] getCoefficients(int[] limits, int[][] buttons) {
        int[][] coefficients = new int[limits.length][buttons.length];
        for (int i = 0; i < limits.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                for (int bI : buttons[j]) {
                    if (bI == i) {
                        coefficients[i][j] = 1;
                        break;
                    }
                }
            }
        }
        return coefficients;
    }

    private List<Machine> parse(File file) throws FileNotFoundException {
        List<Machine> machines = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(" ");
            char[] state = split[0].substring(1, split[0].length() - 1).toCharArray();
            int[][] buttons = new int[split.length - 2][];
            for (int i = 1; i < split.length - 1; i++) {
                int[] button = Arrays.stream(split[i].substring(1, split[i].length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
                buttons[i - 1] = button;
            }
            int[] limits = Arrays.stream(split[split.length - 1].substring(1, split[split.length - 1].length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
            machines.add(new Machine(state, buttons, limits));
        }
        return machines;
    }

    private static class Machine {
        private final char[] state;
        private final int[][] buttons;
        private final int[] limits;

        public Machine(char[] state, int[][] buttons, int[] limits) {
            this.state = state;
            this.buttons = buttons;
            this.limits = limits;
        }
    }

    private static class Result {
        private final char[] state;

        public Result(char[] state) {
            this.state = state;
        }
    }
}
