import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FilehandlerTest {

    @Test
    @DisplayName("files ran when data folder is empty should return an empty array")
    void filesZero() throws IOException {
        Path tempDir = Files.createTempDirectory("testData");
        Filehandler f = new Filehandler(tempDir.toFile());
        assertArrayEquals(new String[0], f.files());
    }

    @Test
    @DisplayName("files ran with one file in data should return an array with the one file")
    void filesOne() throws IOException {
        Path tempDir = Files.createTempDirectory("testData");
        Files.createFile(tempDir.resolve("file1.txt"));
        Filehandler f = new Filehandler(tempDir.toFile());
        assertArrayEquals(new String[]{"file1.txt"}, f.files());
    }

    @Test
    @DisplayName("files ran with multiple files in data should return array with all file names")
    void filesFive() throws IOException {
        Path tempDir = Files.createTempDirectory("testData");
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.txt"));
        Files.createFile(tempDir.resolve("file3.txt"));
        Files.createFile(tempDir.resolve("file4.txt"));
        Files.createFile(tempDir.resolve("file5.txt"));
        Filehandler f = new Filehandler(tempDir.toFile());
        assertArrayEquals(new String[]{"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt"}, f.files());
    }

    @Test
    @DisplayName("Getting a file with no files in data should throw an illegal argument exception")
    void getFileNoFiles(@TempDir Path tempDir) {
        Filehandler f = new Filehandler(tempDir.toFile());
        assertThrows(IllegalArgumentException.class, () -> {
            f.getFile("01");
        });
    }

    @Test
    @DisplayName("Getting a file with not in data should throw an illegal argument exception")
    void getFileNotInData(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("file1.txt"));
        Filehandler f = new Filehandler(tempDir.toFile());
        assertThrows(IllegalArgumentException.class, () -> {
            f.getFile("00"); // only 1 file exists
        });
    }

    @Test
    @DisplayName("Should get the specified file")
    void getFileOne(@TempDir Path tempDir) throws IOException {
        Path file1 = tempDir.resolve("file1.txt");
        Files.writeString(file1, "this is file1");

        Filehandler f = new Filehandler(tempDir.toFile());
        assertEquals("this is file1", f.getFile("01"));
    }

    @Test
    @DisplayName("Should get the specified file")
    void getFileFive(@TempDir Path tempDir) throws IOException {
        Path file1 = tempDir.resolve("file1.txt");
        Files.writeString(file1, "this is file1");
        Path file2 = tempDir.resolve("file2.txt");
        Files.writeString(file2, "this is file2");
        Path file3 = tempDir.resolve("file3.txt");
        Files.writeString(file3, "this is file3");
        Path file4 = tempDir.resolve("file4.txt");
        Files.writeString(file4, "this is file4");
        Path file5 = tempDir.resolve("file5.txt");
        Files.writeString(file5, "this is file5");

        Filehandler f = new Filehandler(tempDir.toFile());
        assertEquals("this is file3", f.getFile("03"));
    }

}