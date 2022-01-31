import java.util.ArrayList;

public class Writer extends Person {
    private String writing;

    public Writer(int uniqueID, String name, String surname, String country, String writing) {
        super(uniqueID, name, surname, country);
        this.writing = writing;
    }

    public String getWriting() {
        return writing;
    }

}
