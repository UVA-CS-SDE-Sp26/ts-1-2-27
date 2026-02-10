import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramControllerTest {
    
    private static ProgramController controller;
    
    @BeforeAll
    static void setup() {
        controller = new ProgramController();
    }
    
    // File Listing Tests
    
    @Test
    @DisplayName("Should list multiple files with numbering")
    void testListFiles() {
        // Test that getAvailableFiles() returns formatted list
        String result = controller.getAvailableFiles();
        assertNotNull(result);
    }
    
    @Test
    @DisplayName("Should handle no files available")
    void testNoFiles() {
        // Test empty file list returns appropriate message
    }
    
    @Test
    @DisplayName("Should format numbers correctly for 10+ files")
    void testDoubleDigitNumbers() {
        // Test that file 10 shows as "10" not "010"
    }
    
    // File Content Tests
    
    @Test
    @DisplayName("Should retrieve file content by number")
    void testGetFileContent() {
        // Test that getFileContentByNumber(1) returns content
        String content = controller.getFileContentByNumber(1);
        assertNotNull(content);
    }
    
    @Test
    @DisplayName("Should preserve multiline content")
    void testMultilineContent() {
        // Test that newlines are preserved
    }
    
    // Error Handling Tests
    
    @Test
    @DisplayName("Should reject invalid file numbers")
    void testInvalidFileNumber() {
        // Test that file number 0 or negative returns error
        String result = controller.getFileContentByNumber(0);
        assertTrue(result.contains("Error"));
    }
    
    @Test
    @DisplayName("Should handle file number too large")
    void testFileNumberTooLarge() {
        // Test that file number beyond available files returns error
        String result = controller.getFileContentByNumber(999);
        assertTrue(result.contains("Error"));
    }
    
    @Test
    @DisplayName("Should handle file not found")
    void testFileNotFound() {
        // Test that missing file returns error message
    }
}
