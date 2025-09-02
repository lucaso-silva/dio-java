package domain;

import java.util.Objects;

public class ClientDomain implements GenericDomain<String> {
    private String id;
    private String name;
    private int age;

    public ClientDomain(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ClientDomain that = (ClientDomain) o;
        if(!(o instanceof ClientDomain that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "ClientDomain{" +
                "id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
