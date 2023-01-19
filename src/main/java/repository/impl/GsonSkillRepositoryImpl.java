package repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Skill;
import model.Status;
import repository.SkillRepository;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final String filePath = "skills.json";

    @Override
    public void create(Skill skill) {

        List<Skill> skills = readFromJson(filePath);

        skills.add(skill);

        writeToJson(filePath, skills);
    }

    @Override
    public Skill get(Integer aLong) {

        return readFromJson(filePath).stream().filter((a) -> a.getId() == aLong && a.getStatus() == Status.ACTIVE).findFirst().orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return readFromJson(filePath).stream().filter((a) -> a.getStatus() == Status.ACTIVE).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer aLong) {
        List<Skill> skills = readFromJson(filePath);

        Optional<Skill> skill = skills.stream().filter((a) -> a.getId() == aLong && a.getStatus() == Status.ACTIVE).findAny();

        if (skill.isEmpty()){
            System.out.println("Нет данных с id " + aLong + " в классе Skill");
        }else {
            int index = skills.indexOf(skill.get());
            skills.get(index).setStatus(Status.DELETED);
            writeToJson(filePath,skills);
        }
    }

    @Override
    public int update(Skill object) {

        List<Skill> skills = readFromJson(filePath);

        Skill skill = skills.stream().filter((a) -> a.getId() == object.getId() && a.getStatus() == Status.ACTIVE).findAny().orElse(null);

        if (skill == null){
            System.out.println("Нет данных с id " + object.getId() + " в классе Skill");
        }else {
            int index = skills.indexOf(skill);
            skills.get(index).setId(object.getId());
            skills.get(index).setName(object.getName());
            writeToJson(filePath,skills);

            return index;
        }

        return -1;
    }

    public List<Skill> readFromJson(String filePath){
        List<Skill> list = null;
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath))){
            Type listType = new TypeToken<ArrayList<Skill>>(){}.getType();
            list = new Gson().fromJson(reader, listType);
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e);
        }

        if(list == null)
            list = new ArrayList<>();

        return list;
    }

    public void writeToJson(String filePath, List<Skill> list){
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))){

            new GsonBuilder().create().toJson(list,writer);
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
