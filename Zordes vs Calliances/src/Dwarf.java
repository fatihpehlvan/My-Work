import java.util.ArrayList;

public class Dwarf extends Calliances{
    private int AP = Constants.dwarfAP;
    private int dwarfMaxMove = Constants.dwarfMaxMove;
    private Integer Heal = Constants.dwarfHeal;
    public Dwarf(String id) {
        super(id);
    }

    public Integer getHeal() {
        return Heal;
    }

    public void setHeal(Integer heal) {
        Heal = heal;
    }

    public int getDefaultHeal(){
        return Constants.dwarfHeal;
    }

    public int getAP() {
        return AP;
    }

    public int getDwarfMaxMove() {
        return dwarfMaxMove;
    }

    @Override
    void attack(String move, Armies champion) throws Exception {

        String[] moveArray = move.split(";");
        if (moveArray.length != 2 * getDwarfMaxMove()){
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
                                    if (armies instanceof Zordes) {
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
                    if (getStop() || getFightToDeath()) break;
                }
                break;
            }
        }
    }
}