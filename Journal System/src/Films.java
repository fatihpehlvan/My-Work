import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Films {
    private String filmType;
    private int uniqueFilmID;
    private String title;
    private String language;
    private String[] directors;
    private int runtime;
    private String country;
    private String[] cast;

    public Films(String filmType, int uniqueFilmID, String title, String language, String[] directors, int runtime, String country, String[] cast) {
        this.filmType = filmType;
        this.uniqueFilmID = uniqueFilmID;
        this.title = title;
        this.language = language;
        this.directors = directors;
        this.runtime = runtime;
        this.country = country;
        this.cast = cast;
    }
    private String directors1;
    private String cast1;
    public Films(String title, String directors1, String cast1 , int uniqueFilmID) {
        this.title = title;
        this.directors1 = directors1;
        this.cast1 = cast1;
        this.uniqueFilmID = uniqueFilmID;
    }

    public Films() {

    }
    private ArrayList<Director> directorsArray;
    private ArrayList<Writer> writersArray;
    private ArrayList<Actor> actorsArray;
    private ArrayList<ChildActor> childActorsArray;
    private ArrayList<StuntPerformer> stuntPerformersArray;
    public Films(ArrayList<Director> directorsArray, ArrayList<Writer> writersArray, ArrayList<Actor> actorsArray, ArrayList<ChildActor> childActorsArray, ArrayList<StuntPerformer> stuntPerformersArray, String country) {
        this.directorsArray = directorsArray;
        this.writersArray = writersArray;
        this.actorsArray = actorsArray;
        this.childActorsArray = childActorsArray;
        this.stuntPerformersArray = stuntPerformersArray;
        this.country = country;
    }
    private ArrayList<Films> film;
    public Films(ArrayList<Films> film, String country) {
        this.film = film;
        this.country = country;
    }
    private ArrayList<ArrayList<String>> featureFilmsArray;
    private ArrayList<ArrayList<String>> shortFilmArray;
    private ArrayList<ArrayList<String>> documentaryArray;
    private ArrayList<ArrayList<String>> tVseriesArray;
    public Films(ArrayList<ArrayList<String>> featureFilmsArray, ArrayList<ArrayList<String>> shortFilmArray, ArrayList<ArrayList<String>> documentaryArray, ArrayList<ArrayList<String>> tVseriesArray) {
        this.featureFilmsArray = featureFilmsArray;
        this.shortFilmArray = shortFilmArray;
        this.documentaryArray= documentaryArray;
        this.tVseriesArray = tVseriesArray;
    }

    public String getFilmType() {
        return filmType;
    }

    public int getUniqueFilmID() {
        return uniqueFilmID;
    }

    public String getTitle() {
        return title;
    }

    public String getDirectors1() {
        return directors1;
    }

    public String getCast1() {
        return cast1;
    }

    public String getLanguage() {
        return language;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getDirectors() {
        return directors;
    }

    public String[] getCast() {
        return cast;
    }


    public String getStringFromArray(String[] str){
        String result = "";
        for (String i : str){
            result += i + ",";
        }
	result = result.substring(0,result.length()-1);
        return result;
    }
    public void countryPeople(){
        Main.outputString += "LIST\tARTISTS\tFROM\t" + country + "\n\n";
        if (directorsArray.isEmpty()){
            Main.outputString += "No result\n";
        }
        else {
            Main.outputString += "Directors:\n";
            for (Director director : directorsArray){
                Main.outputString += director.getName() + "\t" + director.getSurname() + "\t" + director.getAgent() + "\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "Writers:\n";
        if (writersArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (Writer writer : writersArray){
                Main.outputString += writer.getName() + "\t" + writer.getSurname() + "\t" + writer.getWriting() + "\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "Actors:\n";
        if (actorsArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (Actor actor : actorsArray){
                Main.outputString += actor.getName() + "\t" + actor.getSurname() + "\t" + actor.getHeight() + "\tcm" + "\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "ChildActors:\n";
        if (childActorsArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (ChildActor childActor : childActorsArray){
                Main.outputString += childActor.getName() + "\t" + childActor.getSurname() + "\t" + childActor.getAge() + "\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "StuntPerformers:\n";
        if (stuntPerformersArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (StuntPerformer stuntPerformer : stuntPerformersArray){
                Main.outputString += stuntPerformer.getName() + "\t" + stuntPerformer.getSurname() + "\t" + stuntPerformer.getHeight() + "\tcm" + "\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void countryFilm(){
        Main.outputString += "LIST\tFILMS\tBY\tCOUNTRY\t" + country + "\n\n";
        if (film.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (Films films : film) {
                Main.outputString += "Film title:\t" + films.getTitle() + "\n" + films.getRuntime() + "\tmin\n" + "Language:\t" + films.getLanguage() + "\n\n";
            }

        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
    public void degree(){
        Main.outputString += "LIST\tFILMS\tBY\tRATE\tDEGREE\n\n";
        Collections.sort(featureFilmsArray, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o2, ArrayList<String> o1) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });
        Collections.sort(shortFilmArray, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o2, ArrayList<String> o1) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });
        Collections.sort(documentaryArray, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o2, ArrayList<String> o1) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });
        Collections.sort(tVseriesArray, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o2, ArrayList<String> o1) {
                return o1.get(3).compareTo(o2.get(3));
            }
        });
        Main.outputString += "FeatureFilms:\n";
        if (featureFilmsArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (ArrayList<String> feturefilmsElements : featureFilmsArray){
                Main.outputString += feturefilmsElements.get(0) + "\t(" + feturefilmsElements.get(1) + ")\tRatings:\t" + feturefilmsElements.get(2) + "/10 from " + feturefilmsElements.get(3) + " users\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "ShortFilms:\n";
        if (shortFilmArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (ArrayList<String> shortfilmsElements : shortFilmArray){
                Main.outputString += shortfilmsElements.get(0) + "\t(" + shortfilmsElements.get(1) + ")\tRatings:\t" + shortfilmsElements.get(2) + "/10 from " + shortfilmsElements.get(3) + " users\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "Documentaries:\n";
        if (documentaryArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (ArrayList<String> documentaryElements : documentaryArray){
                Main.outputString += documentaryElements.get(0) + "\t(" + documentaryElements.get(1) + ")\tRatings:\t" + documentaryElements.get(2) + "/10 from " + documentaryElements.get(3) + " users\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "TVSeries:\n";
        if (tVseriesArray.isEmpty()){
            Main.outputString += "No result\n\n";
        }
        else {
            for (ArrayList<String> tVseriesElements : tVseriesArray){
                Main.outputString += tVseriesElements.get(0) + "\t(" + tVseriesElements.get(1) + "-" + tVseriesElements.get(2) + ")\tRatings:\t" + tVseriesElements.get(3) + "/10 from " + tVseriesElements.get(4) + " users\n";
            }
            Main.outputString += "\n";
        }
        Main.outputString += "-----------------------------------------------------------------------------------------------------\n";
    }
}
