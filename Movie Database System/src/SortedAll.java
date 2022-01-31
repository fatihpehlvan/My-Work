import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedAll {
    private String textArticle;
    private String textAuthor;

    public SortedAll(String textArticle, String textAuthor) throws IOException {
        this.textArticle = textArticle;
        this.textAuthor = textAuthor;
    }

    public String sort() throws IOException {
        int size = 2;
        ArrayList<ArrayList<String>> articleList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> authorList = new ArrayList<ArrayList<String>>();
        ReadFile readArticle = new ReadFile(this.textArticle, articleList);
        ArrayList<ArrayList<String>> article = readArticle.author();
        ReadFile readAuthor = new ReadFile(this.textAuthor, authorList);
        ArrayList<ArrayList<String>> author = readAuthor.author();
        String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> numberArray = Arrays.asList(numbers);

        for (ArrayList<String> i : author) {
            size = 1;
            boolean steatement = true;
            if (i.size() > 2) {
                for (int elements = 2; elements < i.size(); elements++) {
                    size = elements;
                    String eleman;
                    eleman = (String) i.get(elements);
                    for (int character = 0; character < eleman.length(); character++) {
                        if (numberArray.contains(eleman.substring(character, character + 1)) && !eleman.contains("@")) {
                            steatement = false;
                            break;
                        }
                    }
                    if (!steatement) {
                        Collections.sort(i.subList(size + 1, i.size()));
                        break;
                    }
                }

            }
        }
        FileWriter writer = new FileWriter(this.textAuthor);
        for(ArrayList<String> i: author) {
            for (Object str : i) {
                writer.write((String) str + " ");
            }
            writer.write("\n");
        }
        writer.close();
        ReadLines readSort = new ReadLines(author, this.textArticle);
        String text = readSort.completeAll();
        return text;

    }
}