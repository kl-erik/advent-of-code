#!/bin/bash

create_files() {
  local year=$1
  local day=$(printf "%02d" $2)

  # Define the paths
  local main_dir="src/main/java/year/year${year}/day${day}"
  local test_dir="src/test/java/year/year${year}/day${day}"
  local main_input_dir="src/main/resources/year${year}"
  local test_input_dir="src/test/resources/year${year}"
  local main_file="${main_dir}/Day$2.java"
  local test_file="${test_dir}/Day$2Test.java"
  local main_input_file="${main_input_dir}/input_${day}.txt"
  local test_input_file="${test_input_dir}/input_${day}.txt"

  # Create directories if they don't exist
  mkdir -p "${main_dir}"
  mkdir -p "${test_dir}"
  mkdir -p "${main_input_dir}"
  mkdir -p "${test_input_dir}"

  # Create the input files
  touch "${main_input_file}"
  touch "${test_input_file}"

  # Create the main Java file
  cat <<EOL > "${main_file}"
package year.year${year}.day${day};

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;

public class Day$2 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        // TODO: Implement puzzle1
        return null;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        // TODO: Implement puzzle2
        return null;
    }
}
EOL

  # Create the test Java file
  cat <<EOL > "${test_file}"
package year.year${year}.day${day};

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day$2Test extends TestCase {
    Day$2 day = new Day$2();

    public void testPuzzle1() throws FileNotFoundException {
        // assertEquals(null, day.puzzle1(new File("${test_input_file}")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        // assertEquals(null, day.puzzle2(new File("${test_input_file}")));
    }
}
EOL
}

create_files $1 $2