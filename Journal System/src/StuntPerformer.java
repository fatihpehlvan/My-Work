public class StuntPerformer extends Performer {
    private int height;
    private String[] realActorsID;

    public StuntPerformer(int uniqueID, String name, String surname, String country, int height, String[] realActorsID) {
        super(uniqueID, name, surname, country);
        this.height = height;
        this.realActorsID = realActorsID;
    }

    public int getHeight() {
        return height;
    }

}
