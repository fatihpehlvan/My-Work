import java.util.ArrayList;

public class Goblin extends Zordes{
    private int AP = Constants.goblinAP;
    private int goblinMaxMove = Constants.goblinMaxMove;
    private Integer Heal = Constants.goblinHeal;

    public Goblin(String id) {
        super(id);
    }

    public int getDefaultHeal() {
        return Constants.goblinHeal;
    }

    public Integer getHeal() {
        return Heal;
    }

    public void setHeal(Integer heal) {
        Heal = heal;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int goblinAP) {
        this.AP = goblinAP;
    }

    public int getGoblinMaxMove() {
        return goblinMaxMove;
    }

    public void setGoblinMaxMove(int goblinMaxMove) {
        this.goblinMaxMove = goblinMaxMove;
    }

    @Override
    void attack(String move, Armies champion) throws Exception {

        String[] moveArray = move.split(";");
        if (moveArray.length != 2 * getGoblinMaxMove()){
            throw new Exception("Error : Move sequence contains wrong number of move steps. Input line ignored.");
        }
        ArrayList<String[]> moveArrayList = new ArrayList<>();
        for (int i = 0; i < moveArray.length; i++){
            if (i%2==0){
                String[] moveArrayListElements = new String[]{moveArray[i], moveArray[i+1]};
                moveArrayList.add(moveArrayListElements);
            }
        }
        for (String keys : Main.board.keySet()) {
            if (Main.board.get(keys).equals(champion.getId())) {
                for (String[] moveElements : moveArrayList) {
                    String moveString = moveElements[0] + ";" + moveElements[1];
                    keys = (Integer.parseInt(keys.substring(0, 1)) + Integer.parseInt(moveElements[1])) + "," + (Integer.parseInt(keys.substring(2)) + Integer.parseInt(moveElements[0]));
                    movements(moveString, champion);
                    // neighbor squares
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (!getStop() && !getFightToDeath()) {
                                int sub = keys.indexOf(",");
                                String keysFinal = (Integer.parseInt(keys.substring(0, sub)) + i) + "," + (Integer.parseInt(keys.substring(sub+1)) + j);
                                for (Armies armies : Main.armiesArrayList) {
                                    if (armies instanceof Calliances) {
                                        if (Main.board.get(keysFinal) == null) continue;
                                        if (Main.board.get(keysFinal).equals(armies.getId())) {
                                            armies.setHeal(armies.getHeal() - getAP());
                                            if (armies.getHeal() <= 0) {
                                                Main.board.put(keysFinal, "  ");
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
    }
}