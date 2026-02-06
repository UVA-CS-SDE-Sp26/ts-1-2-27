import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserinterfaceTests {
    // Class called Userinterface exists, accepts commands, and prints to command-line
    // I'm still quite confused on why we need to have tests and a separate class for this
    private static Userinterface ui;

    @BeforeAll
    static void setup() {
        ui = new Userinterface();
    }

    @Test
    @DisplayName("Userinterface should output message to command-line")
    void testInitialization() {
        assertNotNull(ui.getText()); // Class has some sort automatic text prompt?
    }

    @Test
    @DisplayName("Userinterface accepts commands, possibly through passing input into a method")
    void testCommands() {
        // Method .display() returns true if no errors
        assertTrue(ui.display("java topsecret 01"));
    }

}
