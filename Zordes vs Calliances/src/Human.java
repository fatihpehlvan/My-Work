public class Human extends Calliances{
    private Integer Heal = Constants.humanHeal;

    public Human(String id) {
        super(id);
    }

    public Integer getHeal() {
        return Heal;
    }

    public void setHeal(Integer heal) {
        Heal = heal;
    }

    public int getDefaultHeal(){
        return Constants.humanHeal;
    }

    public int getAP() {
        return Constants.humanAP;
    }

    public int getHumanMaxMove() {
        return Constants.humanMaxMove;
    }

    @Override
    void attack(String move, Armies champion) throws Exception {

        String[] moveArray = move.split(";");

        if (moveArray.length != 2 * getHumanMaxMove()){
            throw new Exception("Error : Move sequence contains wrong number of move steps. Input line ignored.");
        }

        int sumY = 0;
        int sumX = 0;
        for (int i = 0; i<moveArray.length; i++){
            if (i%2==0){
                sumX += Integer.parseInt(moveArray[i]);
            }
            else sumY+=Integer.parseInt(moveArray[i]);
        }

        for (String keys : Main.board.keySet()) {
            if (Main.board.get(keys).equals(champion.getId())) {
                movements(move, champion);
                // neighbor squares
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (!getStop() && !getFightToDeath()){
                            int sub = keys.indexOf(",");
                            String keysFinal = (Integer.parseInt(keys.substring(0, sub)) + i + sumY) + "," + (Integer.parseInt(keys.substring(sub+1)) + j + sumX);
                            for (Armies armies : Main.armiesArrayList){
                                if (armies instanceof Zordes){
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
