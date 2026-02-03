import org.junit.Test;
import static org.junit.Assert.assertEquals; // You need this for the assertion to work

public class tests {

    @Test
    public void testDecipherCorrectly() {
        // 1. Arrange: Define your cipher map and the encrypted input
        String encryptedInput = "ABC";
        String expectedOutput = "BCD";

        // 2. Act: Call your decipher method
        // (Assuming your class is named CipherTool)
        String actualOutput = CipherTool.decipher(encryptedInput, "key.txt");

        // 3. Assert: Check if it matches
        assertEquals(expectedOutput, actualOutput, "The string should be correctly deciphered based on the key.");
    }
}
