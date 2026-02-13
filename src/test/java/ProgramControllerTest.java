import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ProgramControllerTest {

    @Test
    @DisplayName("execute with no arguments should list numbered files")
    void testListFiles(@TempDir Path tempDir) throws Exception {

        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.txt"));

        Filehandler f = new Filehandler(tempDir.toFile());
        ProgramControl control = new ProgramControl(f);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.execute(new String[]{});

        String output = out.toString();

        assertTrue(output.contains("01 file1.txt"));
        assertTrue(output.contains("02 file2.txt"));
    }

    @Test
    @DisplayName("execute with valid file number should display file contents")
    void testValidFile(@TempDir Path tempDir) throws Exception {

        Path file = tempDir.resolve("file1.txt");
        Files.writeString(file, "hello world");

        Filehandler f = new Filehandler(tempDir.toFile());
        ProgramControl control = new ProgramControl(f);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.execute(new String[]{"01"});

        assertTrue(out.toString().contains("hello world"));
    }

    @Test
    @DisplayName("execute with invalid file number should display error")
    void testInvalidFileNumber(@TempDir Path tempDir) {

        Filehandler f = new Filehandler(tempDir.toFile());
        ProgramControl control = new ProgramControl(f);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.execute(new String[]{"01"});

        assertTrue(out.toString().contains("Invalid file number"));
    }

    @Test
    @DisplayName("execute with non-numeric argument should display number error")
    void testNonNumericArgument(@TempDir Path tempDir) {

        Filehandler f = new Filehandler(tempDir.toFile());
        ProgramControl control = new ProgramControl(f);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.execute(new String[]{"abc"});

        assertTrue(out.toString().contains("Argument must be a number"));
    }

    @Test
    @DisplayName("execute with cipher key should decipher file contents")
    void testCipherApplied(@TempDir Path tempDir) throws Exception {

        Path file = tempDir.resolve("file1.txt");
        Files.writeString(file, "XYZ");

        Path key = tempDir.resolve("key.txt");
        Files.writeString(key, "ABC\nXYZ");

        Filehandler f = new Filehandler(tempDir.toFile());
        ProgramControl control = new ProgramControl(f);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.execute(new String[]{"01", key.toString()});

        assertTrue(out.toString().contains("ABC"));
    }

    @Test
    @DisplayName("execute with missing cipher file should display error")
    void testMissingCipherFile(@TempDir Path tempDir) throws Exception {

        Path file = tempDir.resolve("file1.txt");
        Files.writeString(file, "XYZ");

        Filehandler f = new Filehandler(tempDir.toFile());
        ProgramControl control = new ProgramControl(f);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.execute(new String[]{"01", "missing.txt"});

        assertTrue(out.toString().contains("Unable to read or decipher"));
    }
}
