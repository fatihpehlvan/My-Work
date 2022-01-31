import java.util.ArrayList;
import java.io.*;

public class ReadFile {
    private String fileName;
    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    public ReadFile(String fileName) throws IOException {
        this.fileName = fileName;
    }

    // return txt File to  2D arraylist
    public ArrayList<ArrayList<String>> read() throws IOException {
        FileReader fr1 = new FileReader(this.fileName);
        BufferedReader bf1 = new BufferedReader(fr1);
        String line;

        while ((line = bf1.readLine()) != (null)) {
            String[] strSplit = line.split(" ");

            ArrayList<String> elements = new ArrayList<>();
            for (String i : strSplit) {
                elements.add(i);


            }

            data.add(elements);

        }
        bf1.close();
        return data;
    }
}