public class Piwo extends przedmiot{
    private int lifetime;
    public Piwo(int lifetime,int mod_do_zad,int mod_do_przy) {
        this.lifetime = lifetime;
        this.mod_do_przy=mod_do_przy;
        this.mod_do_zad=mod_do_zad;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
