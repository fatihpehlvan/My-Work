public class Ork extends Zordes {
    private int AP = Constants.orkAP;
    private int orkHealPoints = Constants.orkHealPoints;
    private int orkMaxMove = Constants.orkMaxMove;
    private Integer Heal = Constants.orkHeal;

    public Ork(String id) {
        super(id);
    }

    public int getDefaultHeal() {
        return Constants.orkHeal;
    }

    public Integer getHeal() {
        return Heal;
    }

    @Override
    public void setHeal(Integer heal) {
        Heal = heal;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getOrkHealPoints() {
        return orkHealPoints;
    }

    public void setOrkHealPoints(int orkHealPoints) {
        this.orkHealPoints = orkHealPoints;
    }

    public int getOrkMaxMove() {
        return orkMaxMove;
    }

    public void setOrkMaxMove(int orkMaxMove) {
        this.orkMaxMove = orkMaxMove;
    }

    @Override
    void attack(String move, Armies champion) throws Exception {

        String[] moveArray = move.split(";");
        if (moveArray.length != 2 * getOrkMaxMove()){
            throw new Exception("Error : Move sequence contains wrong number of move steps. Input line ignored.");
        }
        for (String keys : Main.board.keySet()) {
            if (Main.board.get(keys).equals(champion.getId())) {
                // neighbor squares
                movements(move, champion);
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (!getFightToDeath()) {
                            int sub = keys.indexOf(",");
                            String neighborKeys = (Integer.parseInt(keys.substring(0, sub)) + i) + "," + (Integer.parseInt(keys.substring(sub+1)) + j);
                            for (Armies armies : Main.armiesArrayList) {
                                if (armies instanceof Zordes) {
                                    if (Main.board.get(neighborKeys) == null) continue;
                                    if (Main.board.get(neighborKeys).equals(armies.getId())) {
                                        armies.setHeal(armies.getHeal() + getOrkHealPoints());
                                        if (armies.getHeal() > armies.getDefaultHeal())
                                            armies.setHeal(armies.getDefaultHeal());
                                    }
                                }
                            }
                        }
                        if (!getStop() && !getFightToDeath()){
                            String keysFinal = (Integer.parseInt(keys.substring(0, 1)) + i + Integer.parseInt(moveArray[1])) + "," + (Integer.parseInt(keys.substring(2)) + j + Integer.parseInt(moveArray[0]));
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
