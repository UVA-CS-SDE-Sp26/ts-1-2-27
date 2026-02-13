import java.util.HashMap;
import ciphers.Cipher;

public class ProgramControl {

    private Filehandler filehandler;
    private Cipher cipher;

    public ProgramControl(Filehandler filehandler) {
        this.filehandler = filehandler;
        this.cipher = new Cipher();
    }

    public void execute(String[] args) {

        // No arguments → list files
        if (args.length == 0) {
            String[] files = filehandler.files();

            for (int i = 0; i < files.length; i++) {
                System.out.printf("%02d %s%n", i + 1, files[i]);
            }
            return;
        }

        try {
            String fileNumber = args[0];

            // Get file contents from Filehandler
            String content = filehandler.getFile(fileNumber);

            // If a cipher key is provided
            if (args.length == 2) {
                String keyFile = args[1];
                HashMap<Character, Character> map = cipher.loadKey(keyFile);
                content = cipher.decipher(content, map);
            }

            System.out.println(content);

        } catch (NumberFormatException e) {
            System.out.println("Error: Argument must be a number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unable to read or decipher file.");
        }
    }
}
