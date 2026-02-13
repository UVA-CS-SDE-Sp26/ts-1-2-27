import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Userinterface {

    private static final String DEFAULT_CIPHER = "key.txt";

    public static void run(String[] args) {
        if (args.length == 0) {
            showFileList();
        } else if (args.length == 1) {
            displayFile(args[0], DEFAULT_CIPHER);
        } else if (args.length == 2) {
            displayFile(args[0], args[1]);
        } else {
            System.out.println("Error: Invalid Number of Args!");
        }
    }

    private static void showFileList() {
        System.out.println("Available Files:");

        Filehandler fh = new Filehandler(new File("data"));
        String[] files = fh.files();
        for(int i = 0; i < files.length; i++) {
            String n = i+1 + "";
            if(i < 10) {
                n = "0" + n;
            }
            System.out.println(n + " " + files[i]);
        }
    }

    private static void displayFile(String fileNumber, String cipher) {
        Filehandler fh = new Filehandler(new File("data"));
        Cipher c = new Cipher();

        System.out.println("Displaying file " + fileNumber + " with " + cipher);

        try {
            HashMap<Character, Character> key =  c.loadKey(cipher);
            System.out.println(c.decipher(fh.getFile(fileNumber), key));
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Cipher!");
        } catch (Exception e) {
            System.out.println("Invalid File Number!");
        }
    }
}

