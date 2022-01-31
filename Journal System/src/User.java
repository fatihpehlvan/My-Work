import java.util.ArrayList;
import java.util.Collections;

public class User extends Person {
    public User(int uniqueID, String name, String surname, String country) {
        super(uniqueID, name, surname, country);
    }
    private String userID;
    private String filmID;
    private String rate;
    private String filmType;
    private String filmTitle;
    private boolean statement;
    private boolean repeat;
    public User(String userID, String filmID, String rate, String filmType, String filmTitle, boolean statement, boolean repeat) {
        super();
        this.userID = userID;
        this.filmID = filmID;
        this.rate = rate;
        this.filmType = filmType;
        this.filmTitle = filmTitle;
        this.statement = statement;
        this.repeat = repeat;
    }
    private ArrayList<String> filmTitleArray;
    private ArrayList<String> ratingArray;
    private boolean voted;
    private boolean userExist;
    public User(String userID, ArrayList<String> filmTitleArray, ArrayList<String> ratingArray, boolean voted, boolean userExist) {
        this.filmTitleArray = filmTitleArray;
        this.ratingArray = ratingArray;
        this.voted = voted;
        this.userExist = userExist;
        this.userID = userID;
    }

    public User(String userID, String filmID, String filmTitle, String rate, boolean voted) {
        this.userID = userID;
        this.filmID = filmID;
        this.filmTitle = filmTitle;
        this.rate = rate;
        this.voted = voted;
    }

    public User(String userID, String filmID, String filmTitle, boolean voted) {
        this.userID = userID;
        this.filmID = filmID;
        this.filmTitle = filmTitle;
        this.voted = voted;
    }

    public void rate(){
        Main.outputString += "RATE\t" + userID + "\t" + filmID + "\t" + rate + "\n\n";
        if (repeat){
            Main.outputString += "This film was earlier rated\n\n";
        }
        else if (!statement){
            Main.outputString += "Command Failed\n" +
                    "User ID:\t" + userID + "\n" +
                    "Film ID:\t" + filmID + "\n\n";
        }
        else {
            Main.outputString += "Film rated successfully\n" +
                    "Film type:\t" + filmType + "\n" +
                    "Film title:\t" + filmTitle + "\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void showRates(){
        Main.outputString += "LIST\tUSER\t" + userID + "\t" + "RATES\n\n";
        if (!userExist){
            Main.outputString += "Command Failed\nUser ID:\t" + userID + "\n\n";
        }
        else if (!voted){
            Main.outputString += "There is not any ratings so far\n\n";
        }
        else {
            for (int i = 0; i < filmTitleArray.size(); i++){
                Main.outputString += filmTitleArray.get(i) + ":\t" + ratingArray.get(i) + "\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void edit(){
        Main.outputString += "EDIT\tRATE\t" + userID +"\t" + filmID + "\t" + rate + "\n\n";
        if (!voted){
            Main.outputString += "Command Failed\nUser ID:\t" + userID + "\nFilm ID:\t" + filmID + "\n\n";
        }
        else {
            Main.outputString += "New ratings done successfully\nFilm title:\t" + filmTitle + "\n" + "Your Rating:\t" + rate +"\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void remove(){
        Main.outputString += "REMOVE\tRATE\t" + userID + "\t" + filmID + "\n\n";
        if (!voted){
            Main.outputString += "Command Failed\nUser ID:\t" + userID + "\nFilm ID:\t" + filmID + "\n\n";
        }
        else {
            Main.outputString += "Your film rating was removed successfully\nFilm title:\t" + filmTitle + "\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
}