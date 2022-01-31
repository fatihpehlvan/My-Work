public class Director extends Person {
    private String agent;

    public Director(int uniqueID, String name, String surname, String country, String agent) {
        super(uniqueID, name, surname, country);
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

}
