public class Actor extends Performer {
    private int height;

    public Actor(int uniqueID, String name, String surname, String country, int height) {
        super(uniqueID, name, surname, country);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

}
