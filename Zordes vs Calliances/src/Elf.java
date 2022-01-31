import java.util.ArrayList;

public class Elf extends Calliances{
    private int AP = Constants.elfAP;
    private int elfRangedAP = Constants.elfRangedAP;
    private int elfMaxMove = Constants.elfMaxMove;
    private Integer Heal = Constants.elfHeal;
    public Elf(String id) {
        super(id);
    }

    public Integer getHeal() {
        return Heal;
    }

    public void setHeal(Integer heal) {
        Heal = heal;
    }

    public int getDefaultHeal(){
        return Constants.elfHeal;
    }

    public int getAP() {
        return AP;
    }

    public int getElfRangedAP() {
        return elfRangedAP;
    }

    public int getElfMaxMove() {
        return elfMaxMove;
    }

    @Override
    void attack(String move, Armies champion) throws Exception {
        int count = 0;
        String[] moveArray = move.split(";");
        if (moveArray.length != 2 * getElfMaxMove()){
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
                    count++;
                    int sub1 = keys.indexOf(",");
                    String moveString = moveElements[0] + ";" + moveElements[1];
                    keys = (Integer.parseInt(keys.substring(0, sub1)) + Integer.parseInt(moveElements[1])) + "," + (Integer.parseInt(keys.substring(sub1+1)) + Integer.parseInt(moveElements[0]));
                    movements(moveString, champion);
                    // neighbor squares
                    int bounds = 2;
                    int AP = getAP();
                    if (count == getElfMaxMove()) {
                        bounds = 3;
                        AP = getElfRangedAP();
                    }
                    for (int i = -bounds+1; i < bounds; i++) {
                        for (int j = -bounds+1; j < bounds; j++) {
                            if (!getStop() && !getFightToDeath()) {
                                int sub = keys.indexOf(",");
                                String keysFinal = (Integer.parseInt(keys.substring(0, sub)) + i) + "," + (Integer.parseInt(keys.substring(sub+1)) + j);
                                for (Armies armies : Main.armiesArrayList) {
                                    if (armies instanceof Zordes) {
                                        if (Main.board.get(keysFinal) == null) continue;
                                        if (Main.board.get(keysFinal).equals(armies.getId())) {
                                            armies.setHeal(armies.getHeal() - AP);
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
