package practiceIO;

import java.io.IOException;

public class NIOTester {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new NIOFilePersistence("user.csv");

        System.out.println(persistence.write("John;john@email.com;20/05/1988"));
        System.out.println("---------------");
        System.out.println(persistence.write("Alice;alice@email.com;15/06/2008"));
        System.out.println("---------------");
        System.out.println(persistence.write("Paul;paul@email.com;05/11/1995"));
        System.out.println("---------------");
        System.out.println(persistence.readAll());
        System.out.println("---------------");
        System.out.println(persistence.findBy("alice@"));
        System.out.println("---------------");
        System.out.println(persistence.findBy("88"));
        System.out.println("---------------");
        System.out.println(persistence.findBy("lucas"));
        System.out.println("---------------");
        System.out.println(persistence.remove("paul"));
        System.out.println("---------------");
        System.out.println(persistence.findBy("paul"));
        System.out.println("---------------");
        System.out.println(persistence.remove("ana"));
        System.out.println("---------------");
        System.out.println(persistence.replace("john", "John;jhonny@email.com;20/05/1988"));
        System.out.println("---------------");
        System.out.println(persistence.readAll());
        System.out.println("---------------");

    }
}
