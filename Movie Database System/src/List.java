import java.io.*;

public class List {
    private String text;

    public List(String text) {
        this.text = text;
    }

    public void print() throws IOException {
        File output = new File("output.txt");
        FileWriter writer = new FileWriter(output,true);
        writer.write(this.text);
        writer.flush();
        writer.close();
    }
}