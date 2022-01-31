import java.util.ArrayList;

public class TVseries extends Films {
    private String[] seriesGenre;
    private String[] writers;
    private String startDate;
    private String endDate;
    private int numberOfSeason;
    private int numberOfEpisodes;

    public TVseries(String filmType, int uniqueFilmID, String title, String language, String[] directors, int runtime, String country, String[] cast, String[] seriesGenre, String[] writers, String startDate, String endDate, int numberOfSeason, int numberOfEpisodes) {
        super(filmType, uniqueFilmID, title, language, directors, runtime, country, cast);
        this.seriesGenre = seriesGenre;
        this.writers = writers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisodes = numberOfEpisodes;
    }
    private String rating;
    private double count;
    private String writers1;
    public TVseries(int id , String title, String startDate, String endDate, int numberOfSeason, int numberOfEpisodes, String[] seriesGenre, String writers1, String directors, String cast, String rating, double count) {
        super(title, directors, cast, id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisodes = numberOfEpisodes;
        this.seriesGenre = seriesGenre;
        this.writers1 = writers1;
        this.rating = rating;
        this.count = count;
    }
    private ArrayList<String> title;
    private ArrayList<String> startEnd;
    private ArrayList<Integer> season;
    private ArrayList<Integer> episode;
    private boolean empty;
    public TVseries(ArrayList<String> title, ArrayList<String> startEnd, ArrayList<Integer> season, ArrayList<Integer> episode, boolean empty) {
        super();
        this.title = title;
        this.startEnd = startEnd;
        this.season = season;
        this.episode = episode;
        this.empty = empty;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public String[] getSeriesGenre() {
        return seriesGenre;
    }

    public String[] getWriters() {
        return writers;
    }

    public void viewFilm (){

        if (rating.endsWith("0")){
            rating = rating.substring(0,rating.length()-2);
        }

        Main.outputString += "VIEWFILM\t" + getUniqueFilmID() + "\n\n" + getTitle() + "\t" + "(" + getStartDate() + "-" + getEndDate() + ")\n" +
                getNumberOfSeason() + "\tseasons,\t" + getNumberOfEpisodes() + "\tepisodes\n" + getStringFromArray(getSeriesGenre()) + "\n" + "Writers:\t" + writers1 + "\n" + "Directors:\t" + getDirectors1() + "\n" +
                "Stars:\t" + getCast1() + "\n";
        if (count != 0){
            Main.outputString += "Ratings:\t" + rating + "/10 from " + (int) count + " users\n\n";
        }
        else {
            Main.outputString += "Awaiting for votes\n\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void listTvSeries(){
        Main.outputString += "LIST\tFILM\tSERIES\n\n";
        if (empty){
            Main.outputString += "No result";
        }
        else {
            for (int i = 0; i < title.size(); i++){
                Main.outputString += title.get(i) + "\t" + startEnd.get(i) + "\n" + season.get(i) + "\tseason\tand\t" + episode.get(i) + "\tepisodes\n\n";
            }
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }

}
