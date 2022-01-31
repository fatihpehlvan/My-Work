import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String outputString = "";

    public static void main(String[] args) throws IOException {
        //Read input files
	ReadFile read1 = new ReadFile(args[0]);
        ArrayList<ArrayList<String>> peopleList = read1.read();
        ReadFile read = new ReadFile(args[1]);
        ArrayList<ArrayList<String>> filmsList = read.read();
        //Add films objects their ArrayList type
        ArrayList<Films> filmsOBJ = new ArrayList<>();
        ArrayList<FeatureFilms> featureFilmsOBJ = new ArrayList<>();
        ArrayList<ShortFilm> shortFilmOBJ = new ArrayList<>();
        ArrayList<Documentaries> documentariesOBJ = new ArrayList<>();
        ArrayList<TVseries> tVseriesOBJ = new ArrayList<>();
        for (ArrayList<String> films : filmsList) {
            switch (films.get(0)) {
                case "FeatureFilm:":
                    FeatureFilms featureFilms = new FeatureFilms(films.get(0), Integer.parseInt(films.get(1)), films.get(2), films.get(3), films.get(4).split(","), Integer.parseInt(films.get(5)), films.get(6), films.get(7).split(","), films.get(8), films.get(9), films.get(10).split(","), Integer.parseInt(films.get(11)));
                    featureFilmsOBJ.add(featureFilms);
                    filmsOBJ.add(featureFilms);
                    break;
                case "ShortFilm:":
                    ShortFilm shortFilm = new ShortFilm(films.get(0), Integer.parseInt(films.get(1)), films.get(2), films.get(3), films.get(4).split(","), Integer.parseInt(films.get(5)), films.get(6), films.get(7).split(","), films.get(8), films.get(9), films.get(10).split(","));
                    if (shortFilm.getRuntime() > 40) {
                        System.out.println("Short film cannot be more than 40 mins");
                        break;
                    }
                    shortFilmOBJ.add(shortFilm);
                    filmsOBJ.add(shortFilm);
                    break;
                case "TVSeries:":
                    TVseries tVseries = new TVseries(films.get(0), Integer.parseInt(films.get(1)), films.get(2), films.get(3), films.get(4).split(","), Integer.parseInt(films.get(5)), films.get(6), films.get(7).split(","), films.get(8).split(","), films.get(9).split(","), films.get(10), films.get(11), Integer.parseInt(films.get(12)), Integer.parseInt(films.get(13)));
                    tVseriesOBJ.add(tVseries);
                    filmsOBJ.add(tVseries);
                    break;
                case "Documentary:":
                    Documentaries documentaries = new Documentaries(films.get(0), Integer.parseInt(films.get(1)), films.get(2), films.get(3), films.get(4).split(","), Integer.parseInt(films.get(5)), films.get(6), films.get(7).split(","), films.get(8));
                    documentariesOBJ.add(documentaries);
                    filmsOBJ.add(documentaries);
                    break;
            }
        }
        ////Add person objects their ArrayList type
        ArrayList<Person> personOBJ = new ArrayList<>();
        ArrayList<Director> directorOBJ = new ArrayList<>();
        ArrayList<Performer> performerOBJ = new ArrayList<>();
        ArrayList<Writer> writerOBJ = new ArrayList<>();
        ArrayList<Actor> actorOBJ = new ArrayList<>();
        ArrayList<ChildActor> childActorOBJ = new ArrayList<>();
        ArrayList<StuntPerformer> stuntPerformerOBJ = new ArrayList<>();
        ArrayList<User> userOBJ = new ArrayList<>();
        for (ArrayList<String> people : peopleList) {
            if (people.get(0).equals("Director:")) {
                Director director = new Director(Integer.parseInt(people.get(1)), people.get(2), people.get(3), people.get(4), people.get(5));
                directorOBJ.add(director);
                personOBJ.add(director);
            } else if (people.get(0).equals("Writer:")) {
                Writer writer = new Writer(Integer.parseInt(people.get(1)), people.get(2), people.get(3), people.get(4), people.get(5));
                writerOBJ.add(writer);
                personOBJ.add(writer);
            } else if (people.get(0).equals("Actor:")) {
                Actor actor = new Actor(Integer.parseInt(people.get(1)), people.get(2), people.get(3), people.get(4), Integer.parseInt(people.get(5)));
                actorOBJ.add(actor);
                personOBJ.add(actor);
                performerOBJ.add(actor);
            } else if (people.get(0).equals("ChildActor:")) {
                ChildActor childActor = new ChildActor(Integer.parseInt(people.get(1)), people.get(2), people.get(3), people.get(4), Integer.parseInt(people.get(5)));
                childActorOBJ.add(childActor);
                personOBJ.add(childActor);
                performerOBJ.add(childActor);
            } else if (people.get(0).equals("StuntPerformer:")) {
                StuntPerformer stuntPerformer = new StuntPerformer(Integer.parseInt(people.get(1)), people.get(2), people.get(3), people.get(4), Integer.parseInt(people.get(5)), people.get(6).split(","));
                stuntPerformerOBJ.add(stuntPerformer);
                personOBJ.add(stuntPerformer);
                performerOBJ.add(stuntPerformer);
            } else if (people.get(0).equals("User:")) {
                User user = new User(Integer.parseInt(people.get(1)), people.get(2), people.get(3), people.get(4));
                userOBJ.add(user);
            }
        }
        // IDs is necessary for commands so Add IDs it's properly list
        ArrayList<String> directorID = new ArrayList<>();
        ArrayList<String> performerID = new ArrayList<>();
        ArrayList<String> userID = new ArrayList<>();
        for (User user : userOBJ) {
            userID.add(Integer.toString(user.getUniqueID()));
        }
        for (Performer performer : performerOBJ) {
            performerID.add(Integer.toString(performer.getUniqueID()));
        }
        for (Director director : directorOBJ) {
            directorID.add(Integer.toString(director.getUniqueID()));
        }

        ArrayList<ArrayList<String>> rates = new ArrayList<>();
        ReadFile read2 = new ReadFile(args[2]);
        ArrayList<ArrayList<String>> commandList = read2.read();

        // Apply the commands
        for (int i = 0; i < commandList.size(); i++) {
            if (commandList.get(i).get(0).equals("RATE")) {
                boolean userExist = false;
                boolean filmExist = false;
                boolean repeat = false;
                String filmType = "";
                String filmTitle = "";
                for (User user : userOBJ) {
                    if (Integer.parseInt(commandList.get(i).get(1)) == user.getUniqueID()) {
                        userExist = true;
                        break;
                    }
                }
                for (Films films : filmsOBJ) {
                    if (Integer.parseInt(commandList.get(i).get(2)) == films.getUniqueFilmID()) {
                        filmType = films.getFilmType().substring(0, films.getFilmType().length() - 1);
                        filmTitle = films.getTitle();
                        filmExist = true;
                        break;
                    }
                }
                if (userExist && filmExist) {
                    // ratesData include Film title, film id, user id ,and rate
                    ArrayList<String> ratesData = new ArrayList<>();
                    for (Films films : filmsOBJ) {
                        if (films.getUniqueFilmID() == Integer.parseInt(commandList.get(i).get(2))) {
                            ratesData.add(films.getTitle());
                            break;
                        }
                    }
                    ratesData.add(commandList.get(i).get(2));
                    ratesData.add(commandList.get(i).get(1));
                    ratesData.add(commandList.get(i).get(3));
                    rates.add(ratesData);

                }
                for (int j = 0; j < rates.size(); j++) {
                    for (int k = 0; k < j; k++)
                        if (rates.get(j).equals(rates.get(k))) {
                            repeat = true;
                            rates.remove(rates.size()-1);
                            break;
                        }
                }
                User rate = new User(commandList.get(i).get(1), commandList.get(i).get(2), commandList.get(i).get(3), filmType, filmTitle, filmExist && userExist, repeat);
                rate.rate();

            } else if (commandList.get(i).get(0).equals("ADD")) {
                boolean statement = true;
                for (Films films : filmsOBJ) {
                    if (films.getUniqueFilmID() == Integer.parseInt(commandList.get(i).get(2))) {
                        statement = false;
                    }
                }
                String[] splitDirectors = commandList.get(i).get(5).split(",");
                String[] splitPerformers = commandList.get(i).get(8).split(",");
                for (String directors : splitDirectors) {
                    if (!directorID.contains(directors)) {
                        statement = false;
                        break;
                    }
                }
                for (String performers : splitPerformers) {
                    if (!performerID.contains(performers)) {
                        statement = false;
                        break;
                    }
                }

                FeatureFilms featureFilms1 = new FeatureFilms("FeatureFilm:", Integer.parseInt(commandList.get(i).get(2)), commandList.get(i).get(3), commandList.get(i).get(4), (commandList.get(i).get(5)).split(","), Integer.parseInt(commandList.get(i).get(6)), commandList.get(i).get(7), commandList.get(i).get(8).split(","), commandList.get(i).get(9), commandList.get(i).get(10), commandList.get(i).get(11).split(","), Integer.parseInt(commandList.get(i).get(12)), statement);
                if (statement) {
                    featureFilmsOBJ.add(featureFilms1);
                    filmsOBJ.add(featureFilms1);
                }
                featureFilms1.add();
            } else if (commandList.get(i).get(0).equals("VIEWFILM")) {
                boolean doesntExist = true;
                for (FeatureFilms featureFilms : featureFilmsOBJ) {
                    if (featureFilms.getUniqueFilmID() == Integer.parseInt(commandList.get(i).get(1))) {
                        String direc = "";
                        for (Director director : directorOBJ) {
                            if (Arrays.asList(featureFilms.getDirectors()).contains(Integer.toString(director.getUniqueID()))) {
                                direc += director.getName() + "\t" + director.getSurname() + ",\t";
                            }
                        }
                        direc = direc.substring(0, direc.length() - 2);
                        String stars = "";
                        for (Performer performer : performerOBJ) {
                            if (Arrays.asList(featureFilms.getCast()).contains(Integer.toString(performer.getUniqueID()))) {
                                stars += performer.getName() + "\t" + performer.getSurname() + ",\t";
                            }
                        }
                        stars = stars.substring(0, stars.length() - 2);
                        String writers = "";
                        for (Writer writer : writerOBJ) {
                            if (Arrays.asList(featureFilms.getWriters()).contains(Integer.toString(writer.getUniqueID()))) {
                                writers += writer.getName() + "\t" + writer.getSurname() + ",\t";
                            }
                        }
                        writers = writers.substring(0, writers.length() - 2);
                        double sum = 0;
                        double rating = 0;
                        double count = 0;
                        for (ArrayList<String> rate : rates) {
                            if (rate.contains(commandList.get(i).get(1))) {
                                sum += Integer.parseInt(rate.get(3));
                                count++;
                            }
                        }
                        if (count != 0) {
                            rating = sum / count;
                        }
                        FeatureFilms featureFilms1 = new FeatureFilms(Integer.parseInt(commandList.get(i).get(1)), featureFilms.getTitle(), featureFilms.getReleaseDate().substring(6), featureFilms.getFilmGenre(), writers, direc, stars, String.format("%.1f", rating), count);
                        featureFilms1.viewFilm();
                        doesntExist = false;
                    }
                }
                for (ShortFilm shortFilm : shortFilmOBJ) {
                    if (shortFilm.getUniqueFilmID() == Integer.parseInt(commandList.get(i).get(1))) {
                        String direc = "";
                        for (Director director : directorOBJ) {
                            if (Arrays.asList(shortFilm.getDirectors()).contains(Integer.toString(director.getUniqueID()))) {
                                direc += director.getName() + "\t" + director.getSurname() + ",\t";
                            }
                        }
                        direc = direc.substring(0, direc.length() - 2);
                        String stars = "";
                        for (Performer performer : performerOBJ) {
                            if (Arrays.asList(shortFilm.getCast()).contains(Integer.toString(performer.getUniqueID()))) {
                                stars += performer.getName() + "\t" + performer.getSurname() + ",\t";
                            }
                        }
                        stars = stars.substring(0, stars.length() - 2);
                        String writers = "";
                        for (Writer writer : writerOBJ) {
                            if (Arrays.asList(shortFilm.getWriters()).contains(Integer.toString(writer.getUniqueID()))) {
                                writers += writer.getName() + "\t" + writer.getSurname() + ",\t";
                            }
                        }
                        writers = writers.substring(0, writers.length() - 2);
                        double sum = 0;
                        double rating = 0;
                        double count = 0;
                        for (ArrayList<String> rate : rates) {
                            if (rate.contains(commandList.get(i).get(1))) {
                                sum += Integer.parseInt(rate.get(3));
                                count++;
                            }
                        }
                        if (count != 0) {
                            rating = sum / count;
                        }
                        ShortFilm shortFilm1 = new ShortFilm(Integer.parseInt(commandList.get(i).get(1)), shortFilm.getTitle(), shortFilm.getReleaseDate().substring(6), shortFilm.getFilmGenre(), writers, direc, stars, String.format("%.1f", rating), count);
                        shortFilm1.viewFilm();
                        doesntExist = false;
                    }

                }
                for (TVseries tVseries : tVseriesOBJ) {
                    if (tVseries.getUniqueFilmID() == Integer.parseInt(commandList.get(i).get(1))) {
                        String direc = "";
                        for (Director director : directorOBJ) {
                            if (Arrays.asList(tVseries.getDirectors()).contains(Integer.toString(director.getUniqueID()))) {
                                direc += director.getName() + "\t" + director.getSurname() + ",\t";
                            }
                        }
                        direc = direc.substring(0, direc.length() - 2);
                        String stars = "";
                        for (Performer performer : performerOBJ) {
                            if (Arrays.asList(tVseries.getCast()).contains(Integer.toString(performer.getUniqueID()))) {
                                stars += performer.getName() + "\t" + performer.getSurname() + ",\t";
                            }
                        }
                        stars = stars.substring(0, stars.length() - 2);
                        String writers = "";
                        for (Writer writer : writerOBJ) {
                            if (Arrays.asList(tVseries.getWriters()).contains(Integer.toString(writer.getUniqueID()))) {
                                writers += writer.getName() + "\t" + writer.getSurname() + ",\t";
                            }
                        }
                        writers = writers.substring(0, writers.length() - 2);
                        double sum = 0;
                        double rating = 0;
                        double count = 0;
                        for (ArrayList<String> rate : rates) {
                            if (rate.get(1).equals(commandList.get(i).get(1))) {
                                sum += Integer.parseInt(rate.get(3));
                                count++;
                            }
                        }
                        if (count != 0) {
                            rating = sum / count;
                        }
                        TVseries tVseries1 = new TVseries(Integer.parseInt(commandList.get(i).get(1)), tVseries.getTitle(), tVseries.getStartDate().substring(6), tVseries.getEndDate().substring(6), tVseries.getNumberOfSeason(), tVseries.getNumberOfEpisodes(), tVseries.getSeriesGenre(), writers, direc, stars, String.format("%.1f", rating), count);
                        tVseries1.viewFilm();
                        doesntExist = false;
                    }

                }
                for (Documentaries documentaries : documentariesOBJ) {
                    if (documentaries.getUniqueFilmID() == Integer.parseInt(commandList.get(i).get(1))) {
                        String direc = "";
                        for (Director director : directorOBJ) {
                            if (Arrays.asList(documentaries.getDirectors()).contains(Integer.toString(director.getUniqueID()))) {
                                direc += director.getName() + "\t" + director.getSurname() + ",\t";
                            }
                        }
                        direc = direc.substring(0, direc.length() - 2);
                        String stars = "";
                        for (Performer performer : performerOBJ) {
                            if (Arrays.asList(documentaries.getCast()).contains(Integer.toString(performer.getUniqueID()))) {
                                stars += performer.getName() + "\t" + performer.getSurname() + ",\t";
                            }
                        }
                        stars = stars.substring(0, stars.length() - 2);
                        double sum = 0;
                        double rating = 0;
                        double count = 0;
                        for (ArrayList<String> rate : rates) {
                            if (rate.contains(commandList.get(i).get(1))) {
                                sum += Integer.parseInt(rate.get(3));
                                count++;
                            }
                        }
                        if (count != 0) {
                            rating = sum / count;
                        }
                        Documentaries documentaries1 = new Documentaries(Integer.parseInt(commandList.get(i).get(1)), documentaries.getTitle(), documentaries.getReleaseDate(), direc, stars, String.format("%.1f", rating), count);
                        documentaries1.viewFilm();
                        doesntExist = false;
                    }

                }
                if (doesntExist) {
                    outputString += "Command Failed\nFilm ID:\t" + commandList.get(i).get(1) + "\n\n" +
                            "-----------------------------------------------------------------------------------------------------\n";
                }
            } else if (commandList.get(i).get(1).equals("USER")) {
                boolean voted = false;
                boolean userExist = false;
                for (User user : userOBJ) {
                    if (user.getUniqueID() == Integer.parseInt(commandList.get(i).get(2))) {
                        userExist = true;
                        break;
                    }
                }
                ArrayList<String> filmTitle = new ArrayList<>();
                ArrayList<String> rating = new ArrayList<>();
                for (ArrayList<String> rate : rates) {
                    if (rate.contains(commandList.get(i).get(2))) {
                        voted = true;
                        filmTitle.add(rate.get(0));
                        rating.add(rate.get(3));
                    }
                }
                User user = new User(commandList.get(i).get(2), filmTitle, rating, voted, userExist);
                user.showRates();
            } else if (commandList.get(i).get(0).equals("EDIT")) {
                boolean voted = false;
                String filmTitle = "";
                for (ArrayList<String> rate : rates) {
                    if (rate.contains(commandList.get(i).get(2)) && rate.contains(commandList.get(i).get(3))) {
                        voted = true;
                        rate.set(3, commandList.get(i).get(4));
                        filmTitle = rate.get(0);
                    }
                }
                User user = new User(commandList.get(i).get(2), commandList.get(i).get(3), filmTitle, commandList.get(i).get(4), voted);
                user.edit();
            } else if (commandList.get(i).get(0).equals("REMOVE")) {
                boolean voted = false;
                String filmTitle = "";
                int index = -1;
                for (ArrayList<String> rate : rates) {
                    index++;
                    if (rate.contains(commandList.get(i).get(2)) && rate.contains(commandList.get(i).get(3))) {
                        voted = true;
                        filmTitle = rate.get(0);
                        break;
                    }
                }
                if (voted) {
                    rates.remove(index);
                }
                User user = new User(commandList.get(i).get(2), commandList.get(i).get(3), filmTitle, voted);
                user.remove();

            } else if (commandList.get(i).get(2).equals("SERIES")) {
                boolean empty = false;
                ArrayList<String> title = new ArrayList<>();
                ArrayList<String> startEnd = new ArrayList<>();
                ArrayList<Integer> season = new ArrayList<>();
                ArrayList<Integer> episode = new ArrayList<>();
                if (tVseriesOBJ.isEmpty()) {
                    empty = true;
                } else {
                    for (TVseries tVseries : tVseriesOBJ) {
                        title.add(tVseries.getTitle());
                        String startWithEnd = "(" + tVseries.getStartDate().substring(6) + "-" + tVseries.getEndDate().substring(6) + ")";
                        startEnd.add(startWithEnd);
                        season.add(tVseries.getNumberOfSeason());
                        episode.add(tVseries.getNumberOfEpisodes());
                    }
                }
                TVseries tVseries = new TVseries(title, startEnd, season, episode, empty);
                tVseries.listTvSeries();
            } else if (commandList.get(i).get(2).equals("BEFORE")) {
                boolean exist = false;
                ArrayList<String> title = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();
                ArrayList<Integer> lenght = new ArrayList<>();
                ArrayList<String> language = new ArrayList<>();
                for (FeatureFilms featureFilms : featureFilmsOBJ) {
                    if (Integer.parseInt(featureFilms.getReleaseDate().substring(6)) < Integer.parseInt(commandList.get(i).get(3))) {
                        exist = true;
                        title.add(featureFilms.getTitle());
                        date.add(featureFilms.getReleaseDate().substring(6));
                        lenght.add(featureFilms.getRuntime());
                        language.add(featureFilms.getLanguage());
                    }
                }
                FeatureFilms featureFilms = new FeatureFilms(title, date, lenght, language, exist, commandList.get(i).get(3));
                featureFilms.before();
            } else if (commandList.get(i).get(2).equals("AFTER")) {
                boolean exist = false;
                ArrayList<String> title = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();
                ArrayList<Integer> lenght = new ArrayList<>();
                ArrayList<String> language = new ArrayList<>();
                for (FeatureFilms featureFilms : featureFilmsOBJ) {
                    if (Integer.parseInt(featureFilms.getReleaseDate().substring(6)) >= Integer.parseInt(commandList.get(i).get(3))) {
                        exist = true;
                        title.add(featureFilms.getTitle());
                        date.add(featureFilms.getReleaseDate().substring(6));
                        lenght.add(featureFilms.getRuntime());
                        language.add(featureFilms.getLanguage());
                    }
                }
                FeatureFilms featureFilms = new FeatureFilms(title, date, lenght, language, exist, commandList.get(i).get(3));
                featureFilms.after();
            } else if (commandList.get(i).get(1).equals("ARTISTS")) {
                ArrayList<Director> directors = new ArrayList<>();
                ArrayList<Writer> writers = new ArrayList<>();
                ArrayList<Actor> actors = new ArrayList<>();
                ArrayList<ChildActor> childActors = new ArrayList<>();
                ArrayList<StuntPerformer> stuntPerformers = new ArrayList<>();
                for (Director director : directorOBJ) {
                    if (director.getCountry().equals(commandList.get(i).get(3))) {
                        directors.add(director);
                    }
                }
                for (Writer writer : writerOBJ) {
                    if (writer.getCountry().equals(commandList.get(i).get(3))) {
                        writers.add(writer);
                    }
                }
                for (Actor actor : actorOBJ) {
                    if (actor.getCountry().equals(commandList.get(i).get(3))) {
                        actors.add(actor);
                    }
                }
                for (ChildActor childActor : childActorOBJ) {
                    if (childActor.getCountry().equals(commandList.get(i).get(3))) {
                        childActors.add(childActor);
                    }
                }
                for (StuntPerformer stuntPerformer : stuntPerformerOBJ) {
                    if (stuntPerformer.getCountry().equals(commandList.get(i).get(3))) {
                        stuntPerformers.add(stuntPerformer);
                    }
                }
                Films films = new Films(directors, writers, actors, childActors, stuntPerformers, commandList.get(i).get(3));
                films.countryPeople();
            } else if (commandList.get(i).get(3).equals("COUNTRY")) {
                ArrayList<Films> film = new ArrayList<>();
                for (Films films : filmsOBJ) {
                    if (films.getCountry().equals(commandList.get(i).get(4))) {
                        film.add(films);
                    }
                }
                Films films = new Films(film, commandList.get(i).get(4));
                films.countryFilm();
            } else if (commandList.get(i).get(4).equals("DEGREE")) {
                ArrayList<ArrayList<String>> featureFilmsArray = new ArrayList<>();
                for (FeatureFilms featureFilms : featureFilmsOBJ) {
                    ArrayList<String> featureFilmsArray1 = new ArrayList<>();
                    double sum = 0;
                    int count = 0;
                    for (ArrayList<String> rate : rates) {
                        if (rate.contains(Integer.toString(featureFilms.getUniqueFilmID()))) {
                            sum += Integer.parseInt(rate.get(3));
                            count++;
                        }
                    }
                    if (count != 0) {
                        sum = sum / count;
                    }
                    String rate = String.format("%.1f", sum);
                    if (rate.endsWith("0")) {
                        rate = rate.substring(0, rate.length() - 2);
                    }
                    featureFilmsArray1.add(featureFilms.getTitle());
                    featureFilmsArray1.add(featureFilms.getReleaseDate().substring(6));
                    featureFilmsArray1.add(rate);
                    featureFilmsArray1.add(Integer.toString(count));
                    featureFilmsArray.add(featureFilmsArray1);
                }

                ArrayList<ArrayList<String>> shortFilmArray = new ArrayList<>();
                for (ShortFilm shortFilm : shortFilmOBJ) {
                    ArrayList<String> shortFilmArray1 = new ArrayList<>();
                    double sum = 0;
                    int count = 0;
                    for (ArrayList<String> rate : rates) {
                        if (rate.contains(Integer.toString(shortFilm.getUniqueFilmID()))) {
                            sum += Integer.parseInt(rate.get(3));
                            count++;
                        }
                    }
                    if (count != 0) {
                        sum = sum / count;
                    }
                    String rate = String.format("%.1f", sum);
                    if (rate.endsWith("0")) {
                        rate = rate.substring(0, rate.length() - 2);
                    }
                    shortFilmArray1.add(shortFilm.getTitle());
                    shortFilmArray1.add(shortFilm.getReleaseDate().substring(6));
                    shortFilmArray1.add(rate);
                    shortFilmArray1.add(Integer.toString(count));
                    shortFilmArray.add(shortFilmArray1);
                }

                ArrayList<ArrayList<String>> documentaryArray = new ArrayList<>();
                for (Documentaries documentaries : documentariesOBJ) {
                    ArrayList<String> documentaryArray1 = new ArrayList<>();
                    double sum = 0;
                    int count = 0;
                    for (ArrayList<String> rate : rates) {
                        if (rate.contains(Integer.toString(documentaries.getUniqueFilmID()))) {
                            sum += Integer.parseInt(rate.get(3));
                            count++;
                        }

                    }
                    if (count != 0) {
                        sum = sum / count;
                    }
                    String rate = String.format("%.1f", sum);
                    if (rate.endsWith("0")) {
                        rate = rate.substring(0, rate.length() - 2);
                    }
                    documentaryArray1.add(documentaries.getTitle());
                    documentaryArray1.add(documentaries.getReleaseDate().substring(6));
                    documentaryArray1.add(rate);
                    documentaryArray1.add(Integer.toString(count));
                    documentaryArray.add(documentaryArray1);
                }
                ArrayList<ArrayList<String>> tVseriesArray = new ArrayList<>();
                for (TVseries tVseries : tVseriesOBJ) {
                    ArrayList<String> tVseriesArray1 = new ArrayList<>();
                    double sum = 0;
                    int count = 0;
                    for (ArrayList<String> rate : rates) {
                        if (rate.contains(Integer.toString(tVseries.getUniqueFilmID()))) {
                            sum += Integer.parseInt(rate.get(3));
                            count++;
                        }
                    }
                    if (count != 0) {
                        sum = sum / count;
                    }
                    String rate = String.format("%.1f", sum);
                    if (rate.endsWith("0")) {
                        rate = rate.substring(0, rate.length() - 2);
                    }
                    tVseriesArray1.add(tVseries.getTitle());
                    tVseriesArray1.add(tVseries.getStartDate().substring(6));
                    tVseriesArray1.add(tVseries.getEndDate().substring(6));
                    tVseriesArray1.add(rate);
                    tVseriesArray1.add(Integer.toString(count));
                    tVseriesArray.add(tVseriesArray1);
                }
                Films films = new Films(featureFilmsArray, shortFilmArray, documentaryArray, tVseriesArray);
                films.degree();
            }
        }
        //Make string to output file
        File output = new File(args[3]);
        FileWriter writer = new FileWriter(output);
        writer.write(outputString);
        writer.flush();
        writer.close();
    }
}