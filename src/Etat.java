public class Etat {
    private String nom ;
    private String a;
    private String b;
    private boolean f;

    public Etat(String nom,String a, String b, boolean f) {
        this.a = a;
        this.b = b;
        this.f = f;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }
}
