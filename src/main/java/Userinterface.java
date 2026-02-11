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
            showError("Too Many Arguments");
        }
    }

    private static void showFileList() {
        System.out.println("Output:");
        // Done by Programcontrol
    }

    private static void displayFile(String fileNumber, String cipher) {
        if (!isValidFileNumber(fileNumber)) {
            showError("Invalid file number: " + fileNumber);
            return;
        }

        // Display file contents using Filehandler

        System.out.println("Displaying file " + fileNumber);
    }


    private static boolean isValidFileNumber(String fileNumber) {
        return fileNumber.matches("\\d{2}"); // placeholder, should probably be managed by Filehandler
    }


    private static void showError(String message) {
        System.out.println("Error: " + message);
    }
}

