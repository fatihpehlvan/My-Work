import java.util.ArrayList;
import java.util.Arrays;


public class Armies extends Exception{
    private String id;

    private int AP = 0;

    private Integer heal = 0;

    private boolean fightToDeath;

    private boolean stop;

    public Armies(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public Integer getHeal(){
        return heal;
    }

    public int getAP() {
        return AP;
    }

    public int getDefaultHeal() {
        return 0;
    }

    public void setHeal(Integer heal){
    }
    public void movements(String move, Armies champion) throws Exception {
        fightToDeath = false;
        stop = false;
        ArrayList<String> zorde = new ArrayList<>(Arrays.asList("G", "T", "O"));
        ArrayList<String> calliance = new ArrayList<>(Arrays.asList("E","D","H"));
        String[] moveArray = move.split(";");
        String key = "";
        String previousKey = "";

        for (int i = 0; i < moveArray.length-1; i = i + 2){
            for (String keys : Main.board.keySet()) {
                if (Main.board.get(keys).equals(champion.getId())) {
                    String newXcoordinate = String.valueOf(Integer.parseInt(moveArray[i]) + Integer.parseInt(keys.substring(2)));
                    String newYcoordinate = String.valueOf(Integer.parseInt(moveArray[i + 1]) + Integer.parseInt(keys.substring(0,1)));
                    key = newYcoordinate + "," + newXcoordinate;
                    previousKey = keys;
                }
            }
            if (!Main.board.containsKey(key)){
                throw new Exception("Error : Game board boundaries are exceeded. Input line ignored.");
            }
            if(champion instanceof Zordes){
                if (Main.board.get(key) == null)continue;
                if (zorde.contains(Main.board.get(key).substring(0,1))){
                    stop = true;
                    break;
                }
                else if (calliance.contains(Main.board.get(key).substring(0,1))){
                    fightToDeath = true;
                    Armies championDefending = null;
                    for (Armies armies : Main.armiesArrayList){
                        if (armies.getId().equals(Main.board.get(key))) championDefending = armies;
                    }
                    assert championDefending != null;
                    makeFightToDeath(champion, championDefending, previousKey, key);
                    break;
                }
            }
            else if (champion instanceof Calliances) {
                if (Main.board.get(key) == null)continue;
                if (calliance.contains(Main.board.get(key).substring(0,1))){
                    stop = true;
                    break;
                }
                else if (zorde.contains(Main.board.get(key).substring(0,1))){
                    fightToDeath = true;
                    Armies championDefending = null;
                    for (Armies armies : Main.armiesArrayList){
                        if (armies.getId().equals(Main.board.get(key))) championDefending = armies;
                    }
                    assert championDefending != null;
                    makeFightToDeath(champion, championDefending,previousKey, key);
                    break;
                }
            }

            Main.board.put(previousKey, "  ");
            Main.board.put(key, champion.getId());
        }

    }
    public void makeFightToDeath(Armies championAttacking, Armies championDefending, String previousKey, String battleSquare){
        championDefending.setHeal(championDefending.getHeal()-championAttacking.getAP());
        if(championAttacking.getHeal().compareTo(championDefending.getHeal()) > 0){
            if (championDefending.getHeal() > 0) {
                championAttacking.setHeal(championAttacking.getHeal() - championDefending.getHeal());
            }
            Main.armiesArrayList.remove(championDefending);
            Main.board.put(previousKey, "  ");
            Main.board.put(battleSquare, championAttacking.getId());
        }
        else if (championAttacking.getHeal().compareTo(championDefending.getHeal()) < 0){
            Main.armiesArrayList.remove(championAttacking);
            championDefending.setHeal(championDefending.getHeal() - championAttacking.getHeal());
            Main.board.put(previousKey, "  ");
        }
        else {
            Main.armiesArrayList.remove(championDefending);
            Main.armiesArrayList.remove(championAttacking);
            Main.board.put(previousKey, "  ");
            Main.board.put(battleSquare, "  ");
        }
    }
    public boolean getFightToDeath(){
        return fightToDeath;
    }
    public boolean getStop(){
        return stop;
    }
}
