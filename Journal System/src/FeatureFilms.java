import java.util.ArrayList;
import java.util.Arrays;

public class FeatureFilms extends Films {
    private String filmGenre;
    private String releaseDate;
    private String[] writers;
    private int budget;

    public FeatureFilms(String filmType, int uniqueFilmID, String title, String language, String[] directors, int runtime, String country, String[] cast, String filmGenre, String releaseDate, String[] writers, int budget) {
        super(filmType, uniqueFilmID, title, language, directors, runtime, country, cast);
        this.filmGenre = filmGenre;
        this.releaseDate = releaseDate;
        this.writers = writers;
        this.budget = budget;
    }

    private boolean statement;
    public FeatureFilms(String filmType, int uniqueFilmID, String title, String language, String[] directors, int runtime, String country,  String[] cast, String filmGenre, String releaseDate, String[] writers, int budget, boolean statement) {
        super(filmType, uniqueFilmID, title, language, directors, runtime, country, cast);
        this.filmGenre = filmGenre;
        this.releaseDate = releaseDate;
        this.writers = writers;
        this.budget = budget;
        this.statement = statement;
    }
    private String rating;
    private double count;
    private String writers1;
    public FeatureFilms(int id, String title, String date, String filmGenre, String writers1, String directors, String cast, String rating, double count) {
        super(title, directors, cast, id);
        this.releaseDate = date;
        this.filmGenre = filmGenre;
        this.writers1 = writers1;
        this.rating = rating;
        this.count = count;
    }
    private ArrayList<String> title;
    private ArrayList<String> date;
    private ArrayList<Integer> lenght;
    private ArrayList<String> language;
    private boolean exist;
    private String dates;
    public FeatureFilms(ArrayList<String> title, ArrayList<String> date, ArrayList<Integer> lenght, ArrayList<String> language, boolean exist, String dates) {
        this.title = title;
        this.date = date;
        this.lenght = lenght;
        this.language = language;
        this.exist = exist;
        this.dates = dates;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public void add(){
        Main.outputString += "ADD\tFEATUREFILM\t" + getUniqueFilmID() + "\t" + getTitle() + "\t" + getLanguage() + "\t" +
                getStringFromArray(getDirectors()) + "\t" + getRuntime() + "\t" + getCountry() + "\t" + getStringFromArray(getCast()) +
                "\t" + getFilmGenre() + "\t" + getReleaseDate() + "\t" + getStringFromArray(getWriters()) + "\t" + getBudget() + "\n\n";
        if (statement){
            Main.outputString += "FeatureFilm added successfully\n";
        }
        else {
            Main.outputString += "Command Failed\n";
        }
        Main.outputString += "Film ID:\t" + getUniqueFilmID() + "\n" +
                "Film title:\t" + getTitle() + "\n\n" +
                "-----------------------------------------------------------------------------------------------------\n";
    }
    public void viewFilm (){

        if (rating.endsWith("0")){
            rating = rating.substring(0,rating.length()-2);
        }
        Main.outputString += "VIEWFILM\t" + getUniqueFilmID() + "\n\n" + getTitle() + "\t" + "(" + getReleaseDate() + ")\n" +
                getFilmGenre() + "\n" + "Writers:\t" + writers1 + "\n" + "Directors:\t" + getDirectors1() + "\n" +
                "Stars:\t" + getCast1() + "\n";
        if (count != 0){
            Main.outputString += "Ratings:\t" + rating + "/10 from " + (int) count + " users\n\n";
        }
        else {
            Main.outputString += "Awaiting for votes\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void before(){
        Main.outputString += "LIST\tFEATUREFILMS\tBEFORE\t" + dates + "\n\n";
        if (exist) {
            for (int i = 0; i < title.size(); i++) {
                Main.outputString += "Film title:\t" + title.get(i)  + "\t(" + date.get(i) + ")\n" + lenght.get(i) + "\tmin\nLanguage:\t" + language.get(i) + "\n\n";
            }
        }
        else {
            Main.outputString += "No result\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }

    public void after(){
        Main.outputString += "LIST\tFEATUREFILMS\tAFTER\t" + dates + "\n\n";
        if (exist) {
            for (int i = 0; i < title.size(); i++) {
                Main.outputString += "Film title:\t" + title.get(i) + "\t(" + date.get(i) +  ")\n" + lenght.get(i) + "\tmin\nLanguage:\t" + language.get(i) + "\n\n";
            }
        }
        else {
            Main.outputString += "No result\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
}
