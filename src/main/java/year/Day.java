package year;

import java.io.File;
import java.io.FileNotFoundException;

public interface Day {
    Object puzzle1(File file) throws FileNotFoundException;
    Object puzzle2(File file) throws FileNotFoundException;
}
