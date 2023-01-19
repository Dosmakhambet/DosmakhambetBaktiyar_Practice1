package controller;

import model.Developer;
import model.Skill;
import repository.impl.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final GsonSkillRepositoryImpl repository;

    public SkillController(GsonSkillRepositoryImpl repository){ this.repository = repository;}

    public void create(Skill skill){ repository.create(skill); }

    public Skill get(Integer id){
        return repository.get(id);
    }

    public List<Skill> getAll(){
        return repository.getAll();
    }

    public void delete(Integer id){
        repository.delete(id);
    }

    public int update(Skill skill){
        return repository.update(skill);
    }
}
