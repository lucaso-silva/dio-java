package practiceIO;

import java.io.IOException;

public class IOTester {

    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println("----------------");
        System.out.println(persistence.write("Lucas;lucas@email.com;10/03/1986"));
        System.out.println("----------------");
        System.out.println(persistence.write("Davie;davie@email.com;20/10/1994"));
        System.out.println("----------------");
        System.out.println(persistence.write("Sarah;sarah@email.com;11/09/2002"));
        System.out.println("----------------");

        System.out.println(persistence.readAll());
        System.out.println("----------------");

        System.out.println(persistence.findBy("lucas"));
        System.out.println("----------------");
        System.out.println(persistence.findBy("sarah@"));
        System.out.println("----------------");
        System.out.println(persistence.findBy("94"));
        System.out.println("----------------");

        System.out.println(persistence.remove("Davie"));
        System.out.println("----------------");

        System.out.println(persistence.findBy("Davie"));
        System.out.println("----------------");

        System.out.println(persistence.readAll());
        System.out.println("----------------");

        System.out.println(persistence.replace("lucas@email.com", "Lucas;luc.s@email.com;10/03/1996"));
        System.out.println("----------------");

        System.out.println(persistence.readAll());
    }
}
