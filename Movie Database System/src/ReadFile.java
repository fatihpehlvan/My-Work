import java.util.ArrayList;
import java.io.*;



public class ReadFile {
    private String fileName1;
    private String fileName2;
    private ArrayList<ArrayList<String>> data;
    public ReadFile(String fileName1, ArrayList<ArrayList<String>> data) throws IOException {
        this.fileName1 = fileName1;
        this.data = data;
    }

    // return author.txt to  2D arraylist
    public ArrayList<ArrayList<String>> author() throws IOException {
        FileReader fr1 = new FileReader(this.fileName1);
        BufferedReader bf1 = new BufferedReader(fr1);
        String line;

        while ((line = bf1.readLine()) != (null)) {
            String[] strSplit = line.split(" ");

            ArrayList<String> elements = new ArrayList<String>();
            for (String i : strSplit) {
                elements.add(i);


            }

            this.data.add(elements);

        }
        bf1.close();
        return this.data;
    }
}