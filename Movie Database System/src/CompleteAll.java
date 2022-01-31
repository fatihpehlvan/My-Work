
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompleteAll {
    private String textArticle;
    private String textAuthor;
    public CompleteAll (String textArticle, String textAuthor) throws IOException {
        this.textArticle = textArticle;
        this.textAuthor = textAuthor;
    }
    public String complete() throws IOException {
        ArrayList<ArrayList<String>> articleList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> authorList = new ArrayList<ArrayList<String>>();
        ReadFile readArticle = new ReadFile(this.textArticle, articleList);
        ArrayList<ArrayList<String>> article = readArticle.author();
        ReadFile readAuthor = new ReadFile(this.textAuthor, authorList);
        ArrayList<ArrayList<String>> author = readAuthor.author();
        String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> numberArray = Arrays.asList(numbers);
        for (ArrayList<String> authorElements : author){
            if (authorElements.size() < 2){
                continue;
            }
            int index = 7;
            if (authorElements.size()!=2){

                String index2 = (String) authorElements.get(2);
                for (int word = 0; word<index2.length();word++){
                    if (!numberArray.contains(index2.substring(word, word+1))){
                        index = 11;
                    }
                }

            }
            for (ArrayList<String> articleElements : article){
                if (articleElements.size() < 2){
                    continue;
                }
                String id = (String) articleElements.get(1);
                id = id.substring(0,3);
                if (authorElements.get(1).equals(id) & !(authorElements.contains(articleElements.get(1))) & authorElements.size()<index){
                    authorElements.add(articleElements.get(1));
                }
            }
        }
        FileWriter writer = new FileWriter(this.textAuthor);
        for(ArrayList i: author) {
            for (Object str : i) {
                writer.write((String) str + " ");
            }
            writer.write("\n");
        }
        writer.close();
        ReadLines readComplete = new ReadLines(author, textArticle);
        String text = readComplete.completeAll();
        return text;

    }
}
