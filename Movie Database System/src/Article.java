import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Article {
    private String paperid;
    private String name;
    private String publisherName;
    private String publisherYear;
    public Article(ArrayList<String> liste){
        paperid = liste.get(1);
        name = liste.get(2);
        publisherName = liste.get(3);
        publisherYear = liste.get(4);

    }

    public String getName() {
        return name;
    }

    public String getPaperid() {
        return paperid;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherYear() {
        return publisherYear;
    }

}
