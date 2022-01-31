import java.util.ArrayList;

public class ShortFilm extends Films {
    private String filmGenre;
    private String releaseDate;
    private String[] writers;


    public ShortFilm(String filmType, int uniqueFilmID, String title, String language, String[] directors, int runtime, String country, String[] cast, String filmGenre, String releaseDate, String[] writers) {
        super(filmType, uniqueFilmID, title, language, directors, runtime, country, cast);
        this.filmGenre = filmGenre;
        this.releaseDate = releaseDate;
        this.writers = writers;
    }

    private String rating;
    private double count;
    private String writers1;
    public ShortFilm(int id, String title, String date, String filmGenre, String writers1, String directors, String cast, String rating, double count) {
        super(title, directors, cast, id);
        this.releaseDate = date;
        this.filmGenre = filmGenre;
        this.writers1 = writers1;
        this.rating = rating;
        this.count = count;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String[] getWriters() {
        return writers;
    }

    public void viewFilm (){

        if (rating.substring(rating.length()-1).equals("0")){
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
}
