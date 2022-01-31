import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DelAuthor {
    private String id;
    private String textArticle;
    private String textAuthor;

    public DelAuthor(String textArticle, String textAuthor, String id) throws IOException {
        this.textArticle = textArticle;
        this.textAuthor = textAuthor;
        this.id = id;
    }

    public String del() throws IOException {
        ArrayList<ArrayList<String>> deletedAuthor = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> articleList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> authorList = new ArrayList<ArrayList<String>>();
        ReadFile readArticle = new ReadFile(this.textArticle, articleList);
        ArrayList<ArrayList<String>> article = readArticle.author();
        ReadFile readAuthor = new ReadFile(this.textAuthor, authorList);
        ArrayList<ArrayList<String>> author = readAuthor.author();
        for (ArrayList<String> authorElements : author) {
            if (!authorElements.contains(id) && !deletedAuthor.contains(authorElements)) {
                deletedAuthor.add(authorElements);
            }
        }
        FileWriter writer = new FileWriter(textAuthor);
        for(ArrayList i: deletedAuthor) {
            for (Object str : i) {
                writer.write((String) str + " ");
            }
            writer.write("\n");
        }
        writer.close();
        ReadLines readSort = new ReadLines(deletedAuthor, textArticle);
        String text = readSort.completeAll();
        return text;
    }
}
