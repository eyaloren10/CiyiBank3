import java.io.FileNotFoundException;
import java.util.List;

public interface IDBReader {

    List<String> getAllLines() throws FileNotFoundException;



}
