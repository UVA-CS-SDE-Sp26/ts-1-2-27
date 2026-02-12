import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Filehandler {

    private File folder;

    public Filehandler(File folder) {
        this.folder = folder;
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public String[] files(){
        String[] result = getFolder().list();
        if(result != null) {
            //only sort if .list() doesn't return null
            Arrays.sort(result);
        }
        return result == null ? new String[0] : result; //returns empty array if data is empty, otherwise returns list of file names
    }

    public String getFile(String fileNumber) throws IOException {
        int num = Integer.parseInt(fileNumber);
        String[] fileList = files(); //get list of all file names
        //check for invalid indexes
        if (num < 1 || num > fileList.length) {
            throw new IllegalArgumentException("Invalid file number");
        }
        String fileName = fileList[num-1]; //get specified filename
        Path filePath = folder.toPath().resolve(fileName); //get specified file

        return Files.readString(filePath); //return the String contents of the file
    }
}
