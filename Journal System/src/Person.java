public class Person {
    private int uniqueID;
    private String name;
    private String surname;
    private String country;

    public Person(int uniqueID, String name, String surname, String country) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Person() {

    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
