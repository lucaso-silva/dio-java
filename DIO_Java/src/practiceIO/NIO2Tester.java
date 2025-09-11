package practiceIO;

import java.io.IOException;

public class NIO2Tester {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new NIO2FilePersistence("users.csv");

        System.out.println(persistence.write("Lucas;lucas@email.com;10/03/1986;"));
        System.out.println("---------------");
        System.out.println(persistence.write("Elizabeth;beth@email.com;05/10/2001;"));
        System.out.println("---------------");
        System.out.println(persistence.write("Alisson;alisson@email.com;16/02/1993;"));
        System.out.println("---------------");
        System.out.println(persistence.readAll());
        System.out.println("---------------");
        System.out.println(persistence.findBy("2001"));
        System.out.println("---------------");
        System.out.println(persistence.findBy("Marcos"));
        System.out.println("---------------");
        System.out.println(persistence.remove("alisson@"));
        System.out.println("---------------");
        System.out.println(persistence.remove("mel@"));
        System.out.println("---------------");
        System.out.println(persistence.readAll());
        System.out.println("---------------");
        System.out.println(persistence.replace("Elizabeth", "Beth;beth@email.com;05/10/2001;"));
        System.out.println("---------------");
        System.out.println(persistence.replace("Elizabeth", "Beth;beth@email.com;05/10/2001;"));
        System.out.println("---------------");
        System.out.println(persistence.readAll());
    }
}
