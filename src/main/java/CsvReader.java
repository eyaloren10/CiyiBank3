import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader implements IDBReader {

    private String filePath;

    public CsvReader(String filePath)
    {
        this.filePath = filePath;
    }


    @Override
    public List<String> getAllLines() throws FileNotFoundException {


        boolean isFirstLineRead = false;
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath));) {
            while (scanner.hasNextLine()) {

                if(!isFirstLineRead)
                {
                    isFirstLineRead = true;
                    scanner.nextLine();
                }
                else
                {
                    lines.add(scanner.nextLine());
                }
            }
        }
        return lines;
    }
}
