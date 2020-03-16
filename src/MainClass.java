import java.io.FileNotFoundException;

public class MainClass {

    public static void main(String[] args) throws FileNotFoundException {

        Afn a = new Afn();
        a.getNombreEtats();
        a.remplirEtats();
        System.out.println("-------------------------");
        a.printEtats();
        a.toAfd();
        //a.changeIndices();
        System.out.println("-------------------------");
        a.printSuperEtats();





        }
}
