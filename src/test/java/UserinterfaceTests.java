import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class UserinterfaceTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Userinterface should display file list with no arguments")
    void testNoArguments() {
        Userinterface.run(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("01"));
        assertTrue(output.contains("filea.txt"));
    }


    @Test
    @DisplayName("Userinterface runs with default key")
    void testOneArgument() {
        Userinterface.run(new String[]{"01"});

        String output = outContent.toString();
        assertTrue(output.contains("key.txt"));
        assertTrue(output.contains("Displaying file"));
    }

    @Test
    @DisplayName("Userinterface runs with given key")
    void testTwoArguments() {
        Userinterface.run(new String[]{"01", "key1.txt"});

        String output = outContent.toString();
        assertTrue(output.contains("key1.txt"));
        assertTrue(output.contains("Displaying file"));
    }

    @Test
    @DisplayName("Userinterface displays error with invalid file number")
    void testInvalidFileNumber() {
        Userinterface.run(new String[]{"yo"});

        String output = outContent.toString();
        assertTrue(output.contains("Error"));
        assertTrue(output.contains("Invalid file number"));
    }

    @Test
    @DisplayName("Userinterface fails with too many args")
    void testMoreArguments() {
        Userinterface.run(new String[]{"01", "key.txt", "hey"});

        String output = outContent.toString();
        assertTrue(output.contains("Error"));
        assertTrue(output.contains("Too Many Arguments"));
    }

}
