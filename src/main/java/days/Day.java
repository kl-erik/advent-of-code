package days;

import java.io.File;
import java.io.FileNotFoundException;

public interface Day {
    int puzzle1(File file) throws FileNotFoundException;
    int puzzle2(File file) throws FileNotFoundException;
}
