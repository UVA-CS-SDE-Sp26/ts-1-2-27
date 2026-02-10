import ciphers.Cipher;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Commmand Line Utility
 */
public class TopSecret {

    //if this doesn't make sense feel free to change this

    private String key; //read from key.txt to get value
    //feel free to change key stuff this is just placeholder

    public static void main(String[] args) {
        String keyFilename;

        if(args.length==0) {
            TopSecret topSecret = new TopSecret();

        }
        if(args.length==1) {
            TopSecret topSecret = new TopSecret(locateFile(args[0]));
            //locate the file based on the number in args[0]

            //TODO: Call Decypher Function
            keyFilename = "key.txt"; //Assigns keyFilename to default if not specified
            Cipher cipher = new Cipher();
            try { //Try and loadKey, throw error if file not found
                cipher.loadKey(keyFilename);
            } catch(FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        if(args.length==2) {
            TopSecret topSecret = new TopSecret(locateFile(args[0]), args[1]); //locate file then enter key


            //TODO: Call Decypher Function
            keyFilename = args[1]; //Assigns keyFilename to the first argument (specified)
            Cipher cipher = new Cipher();
            try {
                HashMap<Character, Character> keyMap = cipher.loadKey(keyFilename);
            } catch(FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }


    public TopSecret() {
        //get all files then print them
        String[] data = files();
        //then use the files to print in ui class (?)
    }

    public TopSecret(String contents) {
        //...
    }

    public TopSecret(String contents, String newKey) {
        key = newKey;
        //...
    }

    public static String[] files(){
        return null;
    }

    public static String locateFile(String fileNumber) {
        return null;
    }
}
