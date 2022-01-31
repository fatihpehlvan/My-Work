public abstract class Zordes extends Armies{
    public Zordes(String id) {
        super(id);
    }

    public abstract Integer getHeal();

    public abstract void setHeal(Integer heal);

    public abstract int getDefaultHeal();

    abstract void attack(String move, Armies champion) throws Exception;
}