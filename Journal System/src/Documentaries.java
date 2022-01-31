import java.util.ArrayList;

public class Documentaries extends Films {
    private String releaseDate;

    public Documentaries(String filmType, int uniqueFilmID, String title, String language, String[] directors, int runtime, String country, String[] cast, String releaseDate) {
        super(filmType, uniqueFilmID, title, language, directors, runtime, country, cast);
        this.releaseDate = releaseDate;
    }
    private String rating;
    private double count;
    public Documentaries(int id, String title, String releaseDate, String directors, String cast, String  rating, double count) {
        super(title, directors, cast , id);
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.count = count;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void viewFilm (){

        if (rating.endsWith("0")){
            rating = rating.substring(0,rating.length()-2);
        }

        Main.outputString += "VIEWFILM\t" + getUniqueFilmID() + "\n\n" + getTitle() + "\t" + "(" + getReleaseDate().substring(6) + ")\n\n" +
                "Directors:\t" + getDirectors1() + "\n" +
                "Stars:\t" + getCast1() + "\n";
        if (count != 0){
            Main.outputString += "Ratings:\t" + rating + "/10 from " + (int) count + " users\n\n";
        }
        else {
            Main.outputString += "Awaiting for votes\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
}
