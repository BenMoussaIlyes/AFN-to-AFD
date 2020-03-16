import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Afn {

    private int nEtats;
    private ArrayList<Etat> etats;
    private ArrayList<Etat> superEtats;


    public Afn() {
        etats = new ArrayList<>(10);
        superEtats = new ArrayList<>(10);
    }


    public void getNombreEtats() {
        Scanner sc = new Scanner(System.in);
        int nEtats;
        while (true) {
            System.out.print("Entrer nombre d'etats :");
            nEtats = sc.nextShort();
            System.out.println("");
            if (nEtats > 10 || nEtats < 2) {
                System.out.println("[-] input doit etre entre 2 et 10");
            } else {
                break;
            }
        }
        this.nEtats = nEtats;
        sc.nextLine();
    }


    public void remplirEtats() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < this.nEtats; i++) {
            System.out.println("etat " + i);
            String a;
            String b;
            String nom = this.CreateNameWithIndex(i);


            do {
                System.out.print("a :");
                a = sc.nextLine();
            }
            while (a.length() != nEtats);


            do {
                System.out.print("b :");
                b = sc.nextLine();
            }
            while (b.length() != nEtats);

            String f;
            do {
                System.out.print("est il final ? (t/f):");
                f = sc.nextLine();

            }
            while (!(f.equals("t") || f.equals("f")));


            boolean boo = f.equals("t") ? true : false;
            this.etats.add(new Etat(nom, a, b, boo));
        }
    }

    public void printEtats() {
        for (Etat e : this.etats) {
            System.out.print("Nom : " + e.getNom() + " ## ");
            System.out.print("A : " + e.getA() + " ## ");
            System.out.print("B : " + e.getB() + " ## ");
            System.out.println("final? : " + e.isF());
        }
    }

    public void toAfd() {
        superEtats.add(etats.get(0)); // l'etat 0 est ajoute au super etats

        int counter = 0;
        while (counter < superEtats.size()) {
            String newA = "0000000000".substring(0, nEtats);
            String newB = "0000000000".substring(0, nEtats);
            Etat currentEtat = superEtats.get(counter);
            char[] ce = currentEtat.getNom().toCharArray();

            for (int charIndexCounter = 0; charIndexCounter < ce.length; charIndexCounter++) {
                if (ce[charIndexCounter] == '1') {
                    Etat e = etats.get(charIndexCounter);
                    // operation or BEGIN
                    int intermidiaire = Integer.valueOf(newA, 2) | Integer.valueOf(e.getA(), 2);
                    newA = Integer.toBinaryString(intermidiaire);
                    int intermidiaire2 = Integer.valueOf(newB, 2) | Integer.valueOf(e.getB(), 2);
                    newB = Integer.toBinaryString(intermidiaire2);
                    // operation or END
                }

            }

            currentEtat.setA(newA);
            currentEtat.setB(newB);
            if (!this.TransitionIsSuperState(newA)) {
                superEtats.add(new Etat(newA, "", "", false));
            }

            if (!this.TransitionIsSuperState(newB)) {
                superEtats.add(new Etat(newB, "", "", false));
            }


            counter++;


        }
    }

    public void printSuperEtats() {
        for (Etat e : this.superEtats) {
            System.out.print("Nom : " + e.getNom() + " ## ");
            System.out.print("A : " + e.getA() + " ## ");
            System.out.print("B : " + e.getB() + " ## ");
            System.out.println("final? : " + e.isF());
        }
    }

    public boolean TransitionIsSuperState(String a) {
        String noTransition = "0000000000".substring(0, this.nEtats);
        if (noTransition.equals(a)) {
            return true; // ne doit pas etre ajoute dans les super-etats s'il n'a pas de transitions (tous sont a 0)
        }
        for (Etat e : superEtats) {
            if (e.getNom().equals(a)) {
                return true;
            }
        }
        return false;
    }

// c'est pour rendre un nom unique pour chaque etat , etat 0 par exemple recoi un 1 dans la position 0 donc : 1000000
    public String CreateNameWithIndex(int index) {
        String nom = "0000000000".substring(0, this.nEtats);
        char[] charsDeNom = nom.toCharArray();
        charsDeNom[index] = '1';
        return String.valueOf(charsDeNom);
    }

    public void changeIndices() {
        for (Etat e : this.superEtats) {
            for (int i = 0; i < this.superEtats.size(); i++) {
                if (e.getA().equals(this.superEtats.get(i).getNom())) {
                    e.setA(this.CreateNameWithIndex(i));
                }
                if (e.getB().equals(this.superEtats.get(i).getNom())) {
                    e.setB(this.CreateNameWithIndex(i));
                }
            }
        }
    }



}