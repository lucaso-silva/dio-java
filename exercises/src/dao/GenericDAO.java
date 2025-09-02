package dao;
import domain.GenericDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class GenericDAO<ID, T extends GenericDomain<ID>> {

    private final List<T> db = new ArrayList<T>();

    private T save(T domain){
        db.add(domain);
        return domain;
    }

    @SafeVarargs
    public final boolean save(T... domains){
        return db.addAll(Arrays.stream(domains).toList());
    }

    public T update(ID id, T domain){
        var stored = find(d -> d.getId().equals(id)).orElseThrow();
        db.remove(stored);
        return save(domain);
    }

    public boolean delete(T domain){
        return db.remove(domain);
    }

    public Optional<T> find(Predicate<T> filterCallback){
        return db.stream().filter(filterCallback).findFirst();
    }

    public List<T> findAll(){
        return db;
    }

    public int count(){
        return db.size();
    }
}
