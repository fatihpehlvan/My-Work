public class ChildActor extends Performer {
    private int age;

    public ChildActor(int uniqueID, String name, String surname, String country, int age) {
        super(uniqueID, name, surname, country);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

}
