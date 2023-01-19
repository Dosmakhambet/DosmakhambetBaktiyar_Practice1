package repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Status;
import repository.DeveloperRepository;


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

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {


    private final String filePath = "developers.json";

    @Override
    public void create(Developer developer) {

        List<Developer> developers = readFromJson(filePath);

        developers.add(developer);

        writeToJson(filePath, developers);
    }

    @Override
    public Developer get(Integer aLong) {

        return readFromJson(filePath).stream().filter((a) -> a.getId() == aLong && a.getStatus() == Status.ACTIVE).findFirst().orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return readFromJson(filePath).stream().filter((a) -> a.getStatus() == Status.ACTIVE).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer aLong) {
        List<Developer> developers = readFromJson(filePath);

        Optional<Developer> developer = developers.stream().filter((a) -> a.getId() == aLong && a.getStatus() == Status.ACTIVE).findAny();

        if (developer.isEmpty()){
            System.out.println("Нет данных с id " + aLong + " в классе Skill");
        }else {
            int index = developers.indexOf(developer.get());
            developers.get(index).setStatus(Status.DELETED);
            writeToJson(filePath,developers);
        }
    }

    @Override
    public int update(Developer object) {

        List<Developer> developers = readFromJson(filePath);

        Developer developer = developers.stream().filter((a) -> a.getId() == object.getId() && a.getStatus() == Status.ACTIVE).findAny().orElse(null);

        if (developer == null){
            System.out.println("Нет данных с id " + object.getId() + " в классе Developer");
        }else {
            int index = developers.indexOf(developer);
            developers.get(index).setId(object.getId());
            developers.get(index).setFirstName(object.getFirstName());
            developers.get(index).setLastName(object.getFirstName());
            developers.get(index).setSpecialty(object.getSpecialty());
            developers.get(index).setSkills(object.getSkills());

            writeToJson(filePath,developers);

            return index;
        }

        return -1;
    }

    public List<Developer> readFromJson(String filePath){
        List<Developer> list = null;
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath))){
            Type listType = new TypeToken<ArrayList<Developer>>(){}.getType();
            list = new Gson().fromJson(reader, listType);
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e);
        }

        if(list == null)
            list = new ArrayList<>();

        return list;
    }

    public void writeToJson(String filePath, List<Developer> list){
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))){

            new GsonBuilder().create().toJson(list,writer);
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
