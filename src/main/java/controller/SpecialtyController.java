package controller;

import model.Specialty;
import repository.impl.GsonSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {
    private final GsonSpecialtyRepositoryImpl repository;

    public SpecialtyController(GsonSpecialtyRepositoryImpl repository) {
        this.repository = repository;
    }

    public void create(Specialty specialty){
        repository.create(specialty);
    }

    public Specialty get(Integer id){
        return repository.get(id);
    }

    public List<Specialty> getAll(){
        return repository.getAll();
    }

    public void delete(Integer id){
        repository.delete(id);
    }

    public int update(Specialty specialty){
        return repository.update(specialty);
    }
}
