import ciphers.Cipher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CipherTest {

    private Cipher cipherTool;
    private File tempKeyFile;

    @BeforeEach
    public void setup() throws IOException {
        cipherTool = new Cipher();
        // Create a temporary file to avoid cluttering the project directory
        tempKeyFile = File.createTempFile("testKey", ".txt");
    }

    @AfterEach
    public void teardown() {
        if (tempKeyFile != null && tempKeyFile.exists()) {
            tempKeyFile.delete();
        }
    }

    // Helper method to write specific scenarios to our temporary test file
    private void writeToFile(String content) throws IOException {
        try (FileWriter writer = new FileWriter(tempKeyFile)) {
            writer.write(content);
        }
    }

    @Test
    public void testDecipherCorrectly() {
        // Arrange
        HashMap<Character, Character> map = new HashMap<>();
        map.put('X', 'A'); // Cipher X translates to Actual A
        map.put('Y', 'B'); // Cipher Y translates to Actual B
        map.put('Z', 'C'); // Cipher Z translates to Actual C

        String encryptedInput = "XYZ 123!";
        String expectedOutput = "ABC 123!";

        // Act
        String actualOutput = cipherTool.decipher(encryptedInput, map);

        // Assert
        assertEquals(expectedOutput, actualOutput, "The string should be correctly deciphered and preserve unmapped characters.");
    }

    @Test
    public void testLoadKeyValid() throws Exception {
        writeToFile("ABC\nXYZ\n");
        HashMap<Character, Character> map = cipherTool.loadKey(tempKeyFile.getAbsolutePath());

        // Assert that it mapped Cipher keys to Actual values
        assertEquals(3, map.size());
        assertEquals(Character.valueOf('A'), map.get('X'));
        assertEquals(Character.valueOf('B'), map.get('Y'));
        assertEquals(Character.valueOf('C'), map.get('Z'));
    }

    @Test
    public void testLoadKeyFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> {
            cipherTool.loadKey("some_missing_file.txt");
        });
    }

    @Test
    public void testLoadKeyEmptyFile() throws IOException {
        writeToFile("");
        assertThrows(IllegalArgumentException.class, () -> {
            cipherTool.loadKey(tempKeyFile.getAbsolutePath());
        });
    }

    @Test
    public void testLoadKeyOneLine() throws IOException {
        writeToFile("ABC\n");
        assertThrows(IllegalArgumentException.class, () -> {
            cipherTool.loadKey(tempKeyFile.getAbsolutePath());
        });
    }

    @Test
    public void testLoadKeyMoreThanTwoLines() throws IOException {
        writeToFile("ABC\nXYZ\nEXTRA\n");
        assertThrows(IllegalArgumentException.class, () -> {
            cipherTool.loadKey(tempKeyFile.getAbsolutePath());
        });
    }

    @Test
    public void testLoadKeyMismatchedLengths() throws IOException {
        writeToFile("ABC\nXY\n"); // Line 2 is shorter
        assertThrows(IllegalArgumentException.class, () -> {
            cipherTool.loadKey(tempKeyFile.getAbsolutePath());
        });
    }
}