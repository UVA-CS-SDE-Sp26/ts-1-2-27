import java.util.HashMap;

public class ProgramControl {

    private Filehandler filehandler;
    private Cipher cipher;

    public ProgramControl(Filehandler filehandler) {
        this.filehandler = filehandler;
        this.cipher = new Cipher();
    }

    public void execute(String[] args) {

        if (args.length == 0) {
            String[] files = filehandler.files();

            for (int i = 0; i < files.length; i++) {
                System.out.printf("%02d %s%n", i + 1, files[i]);
            }
            return;
        }

        try {
            String fileNumber = args[0];
            String content = filehandler.getFile(fileNumber);

            HashMap<Character, Character> map;

            if (args.length == 1) {
                map = cipher.loadKey("key.txt"); // DEFAULT
            }
            else if (args.length == 2) {
                map = cipher.loadKey(args[1]);   // CUSTOM
            }
            else {
                System.out.println("Error: Invalid number of arguments.");
                return;
            }

            content = cipher.decipher(content, map);
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

