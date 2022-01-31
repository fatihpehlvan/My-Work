public class Troll extends Zordes{
    private int AP = Constants.trollAP;
    private int trollMaxMove = Constants.trollMaxMove;
    private Integer Heal = Constants.trollHeal;

    public Troll(String id) {
        super(id);
    }

    public int getDefaultHeal() {
        return Constants.trollHeal;
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

    public void setAP(int trollAP) {
        this.AP = trollAP;
    }

    public int getTrollMaxMove() {
        return trollMaxMove;
    }

    public void setTrollMaxMove(int trollMaxMove) {
        this.trollMaxMove = trollMaxMove;
    }

    @Override
    void attack(String move, Armies champion) throws Exception {

        String[] moveArray = move.split(";");
        if (moveArray.length != 2 * getTrollMaxMove()){
            throw new Exception("Error : Move sequence contains wrong number of move steps. Input line ignored.");
        }
        for (String keys : Main.board.keySet()) {
            if (Main.board.get(keys).equals(champion.getId())) {
                // neighbor squares
                movements(move, champion);
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (!getStop() && !getFightToDeath()){
                            int sub = keys.indexOf(",");
                            String keysFinal = (Integer.parseInt(keys.substring(0, sub)) + i + Integer.parseInt(moveArray[1])) + "," + (Integer.parseInt(keys.substring(sub+1)) + j + Integer.parseInt(moveArray[0]));
                            for (Armies armies : Main.armiesArrayList){
                                if (armies instanceof Calliances){
                                    if (Main.board.get(keysFinal) == null)continue;
                                    if (Main.board.get(keysFinal).equals(armies.getId())){
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
                break;
            }
        }
    }
}
