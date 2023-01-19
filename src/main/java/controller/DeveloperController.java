package controller;

import model.Developer;
import repository.impl.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private final GsonDeveloperRepositoryImpl repository;

    public DeveloperController(GsonDeveloperRepositoryImpl repository) {
        this.repository = repository;
    }

    public void create(Developer developer){ repository.create(developer); }

    public Developer get(Integer id){
        return repository.get(id);
    }

    public List<Developer> getAll(){
        return repository.getAll();
    }

    public void delete(Integer id){
        repository.delete(id);
    }

    public int update(Developer developer){
        return repository.update(developer);
    }
}
