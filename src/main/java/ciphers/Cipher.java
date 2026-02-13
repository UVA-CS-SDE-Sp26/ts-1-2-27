package ciphers;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {

        public HashMap<Character, Character> loadKey(String filename) throws FileNotFoundException {
                HashMap<Character, Character> map = new HashMap<>();
                File file = new File(filename);
                Scanner scanner = new Scanner(file);

                // 1. Read first line
                if (!scanner.hasNextLine()) {
                        scanner.close();
                        throw new IllegalArgumentException("Error: File is empty.");
                }
                String line1 = scanner.nextLine();

                // 2. Read second line
                if (!scanner.hasNextLine()) {
                        scanner.close();
                        throw new IllegalArgumentException("Error: File has only one line.");
                }
                String line2 = scanner.nextLine();

                // 3. Check for more lines (The Error Condition)
                if (scanner.hasNextLine()) {
                        scanner.close();
                        throw new IllegalArgumentException("Error: File has more than two lines.");
                }

                scanner.close();

    /*Just to show it worked
    System.out.println("Line 1: " + line1);
    System.out.println("Line 2: " + line2);
     */


                // i starts at 0; keeps going as long as i is less than length; increases by 1 each time
                if (line1.length() == line2.length()) {
                        for (int i = 0; i < line1.length(); i++) {
                                map.put(line1.charAt(i), line2.charAt(i));
                        }

                } else {
                        throw new IllegalArgumentException("Lengths do not match.");
                }
                return map;
        }


        public String decipher(String text, HashMap<Character, Character> map) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                        char originalChar = text.charAt(i);

                        if (map.containsKey(originalChar)){
                                builder.append(map.get(originalChar));
                        } else{
                                builder.append(originalChar);
                        }


                }

                return builder.toString();
        }
}