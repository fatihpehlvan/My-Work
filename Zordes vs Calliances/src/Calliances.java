public abstract class Calliances extends Armies{

    public Calliances(String id) {
        super(id);
    }

    public abstract Integer getHeal();

    public abstract void setHeal(Integer heal);

    abstract void attack(String move, Armies champion) throws Exception;
}
