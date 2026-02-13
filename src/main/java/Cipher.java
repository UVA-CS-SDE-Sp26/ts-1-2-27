import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Cipher {

        public HashMap<Character, Character> loadKey(String filename) throws FileNotFoundException {
                HashMap<Character, Character> map = new HashMap<>();

                File file = new File(filename);

                // --- SMART PATH RESOLUTION ---
                // 1. Try the exact path first
                // 2. If it doesn't exist, automatically check the "ciphers/" folder
                if (!file.exists()) {
                        File fallback = new File("ciphers/" + filename);
                        if (fallback.exists()) {
                                file = fallback;
                        } else {
                               
                                File srcFallback = new File("src/main/java/ciphers/" + filename);
                                if (srcFallback.exists()) {
                                        file = srcFallback;
                                }
                        }
                }
                // -----------------------------

                // try-with-resources ensures the scanner is automatically closed
                try (Scanner scanner = new Scanner(file)) {

                        // 1. Read first line (Actual Letter / Plain Text)
                        if (!scanner.hasNextLine()) {
                                throw new IllegalArgumentException("Error: File is empty.");
                        }
                        String line1 = scanner.nextLine();

                        // 2. Read second line (Cipher Match)
                        if (!scanner.hasNextLine()) {
                                throw new IllegalArgumentException("Error: File has only one line.");
                        }
                        String line2 = scanner.nextLine();

                        // 3. Check for more lines (The Error Condition)
                        if (scanner.hasNextLine()) {
                                throw new IllegalArgumentException("Error: File has more than two lines.");
                        }

                        // 4. Validate matching lengths
                        if (line1.length() == line2.length()) {
                                for (int i = 0; i < line1.length(); i++) {
                                        map.put(line2.charAt(i), line1.charAt(i));
                                }
                        } else {
                                throw new IllegalArgumentException("Error: Lengths do not match.");
                        }
                }

                return map;
        }

        public String decipher(String text, HashMap<Character, Character> map) {
                if (text == null) return null;

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < text.length(); i++) {
                        char originalChar = text.charAt(i);

                        if (map.containsKey(originalChar)) {
                                builder.append(map.get(originalChar));
                        } else {
                                builder.append(originalChar);
                        }
                }

                return builder.toString();
        }
}
