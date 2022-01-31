import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadLines {
    private String str;
    private String articleFile;
    private String authorFile;
    private ArrayList<ArrayList<String>> author;
    public ReadLines(String str, String articleFile, String authorFile){
        this.str = str;
        this.articleFile = articleFile;
        this.authorFile = authorFile;
    }

    public ReadLines(ArrayList<ArrayList<String>> author, String articleFile) {
        this.author = author;
        this.articleFile = articleFile;
    }



    public String read() throws IOException {

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ReadFile readArticle = new ReadFile(this.articleFile, data);
        ArrayList<ArrayList<String>> article = readArticle.author();
        ArrayList<ArrayList<String>> data2 = new ArrayList<ArrayList<String>>();
        ReadFile readAuthor = new ReadFile(this.authorFile, data2);
        ArrayList<ArrayList<String>> author = readAuthor.author();
        String[] numbers = new String[] {"0","1","2","3","4","5","6","7","8","9"};
        List<String> numberArray = Arrays.asList(numbers);
        for (ArrayList<String> authorElements : author) {
            if (authorElements.size() < 2){
                continue;
            }
            int size = 2;
            boolean steatement = true;
            this.str += "Author:" + authorElements.get(1);
            if(authorElements.size()>2){
                for (int elements = 2; elements < authorElements.size(); elements++){
                    size = elements;
                    String eleman;
                    eleman = (String) authorElements.get(elements);
                    for (int character = 0 ; character < eleman.length(); character++){
                        if (numberArray.contains(eleman.substring(character,character+1)) && !eleman.contains("@")){
                            this.str += "\n";
                            steatement = false;
                            break;
                        }
                    }
                    if (!steatement){
                        for (int j = size; j < authorElements.size(); j++) {
                            for (ArrayList<String> i : article) {
                                if (i.contains(authorElements.get(j))) {
                                    Article articleInfo = new Article(i);
                                    this.str += "+" + articleInfo.getPaperid() + "\t" + articleInfo.getName() + "\t" + articleInfo.getPublisherName() + "\t" + articleInfo.getPublisherYear() + "\n";

                                }
                            }
                        }
                        str += "\n";
                        break;
                    }

                    else {
                        this.str += "\t" + authorElements.get(size);
                        if(size == authorElements.size()-1){
                            this.str += "\n";
                            this.str += "\n";
                        }
                    }



                }
            }
            else {
                this.str += "\n";
                this.str +="\n";
            }
        }
        return this.str;
    }




    public String completeAll () throws IOException {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ReadFile readArticle = new ReadFile(this.articleFile, data);
        ArrayList<ArrayList<String>> article = readArticle.author();
        String str = "";
        String[] numbers = new String[] {"0","1","2","3","4","5","6","7","8","9"};
        List<String> numberArray = Arrays.asList(numbers);
        for (ArrayList<String> authorElements : this.author) {
            if (authorElements.size() < 2){
                continue;
            }
            int size = 2;
            boolean steatement = true;
            str += "Author:" + authorElements.get(1);
            if(authorElements.size()>2){
                for (int elements = 2; elements < authorElements.size(); elements++){
                    size = elements;
                    String eleman;
                    eleman = (String) authorElements.get(elements);
                    for (int character = 0 ; character < eleman.length(); character++){
                        if (numberArray.contains(eleman.substring(character,character+1)) && !eleman.contains("@")){
                            str += "\n";
                            steatement = false;
                            break;
                        }

                    }

                    if (!steatement){
                        for (int j = size; j < authorElements.size(); j++) {
                            for (ArrayList<String> i : article) {

                                if (i.contains(authorElements.get(j))) {
                                    Article articleInfo = new Article(i);
                                    str += "+" + articleInfo.getPaperid() + "\t" + articleInfo.getName() + "\t" + articleInfo.getPublisherName() + "\t" + articleInfo.getPublisherYear() + "\n";

                                }
                            }
                        }
                        str += "\n";
                        break;
                    }

                    else {
                        str += "\t" + authorElements.get(size);
                        if(size == authorElements.size()-1){
                            str += "\n";
                            str += "\n";
                        }

                    }



                }
            }
            else {
                str +="\n";
                str +="\n";
            }
        }
        return str;
    }
}
