import dao.ClientDAO;
import dao.GenericDAO;
import dao.UserDAO;
import domain.ClientDomain;
import domain.UserDomain;
/*
** Generics
 */

public class Main {
    private final static GenericDAO<Integer, UserDomain> dao = new UserDAO();
    private final static GenericDAO<String, ClientDomain> clientDao = new ClientDAO();

    public static void main(String[] args) {
        var user = new UserDomain(1, "Joao", 36);
        System.out.println("===== Start UserDAO =====");
        System.out.println(dao.count());
        System.out.println(dao.save(new UserDomain(1, "Joao", 36), new UserDomain(2, "Oliver", 27)));
        System.out.println(dao.findAll());
        System.out.println(dao.find(d->d.getId().equals(1)));
        System.out.println(dao.find(d->d.getId().equals(4)));
        System.out.println(dao.count());
        System.out.println(dao.delete(new UserDomain(35, "Paulo", 16)));
        System.out.println(dao.delete(user));
        System.out.println(dao.findAll());
        System.out.println(dao.count());
        System.out.println("===== End UserDAO =====");
        System.out.println("===== Start ClientDAO =====");
        var client = new ClientDomain("A", "Maria", 19);
        System.out.println(clientDao.count());
        System.out.println(clientDao.save(client));
        System.out.println(clientDao.findAll());
        System.out.println(clientDao.find(d->d.getId().equals("A")));
        System.out.println(clientDao.find(d->d.getId().equals("B")));
        System.out.println(clientDao.count());
        System.out.println(clientDao.delete(new ClientDomain("AA", "Paulo", 16)));
        System.out.println(clientDao.delete(client));
        System.out.println(clientDao.findAll());
        System.out.println(clientDao.count());
        System.out.println("===== End ClientDAO =====");
    }
}
