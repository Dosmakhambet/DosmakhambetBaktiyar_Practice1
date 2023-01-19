package repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Specialty;
import model.Status;
import repository.SpecialtyRepository;

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

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository{


    private final String filePath = "specialty.json";

    @Override
    public void create(Specialty specialty) {

        List<Specialty> specialties = readFromJson(filePath);

        specialties.add(specialty);

        writeToJson(filePath, specialties);
    }

    @Override
    public Specialty get(Integer aLong) {

        return readFromJson(filePath).stream().filter((a) -> a.getId() == aLong && a.getStatus() == Status.ACTIVE).findFirst().orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return readFromJson(filePath).stream().filter((a) -> a.getStatus() == Status.ACTIVE).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer aLong) {
        List<Specialty> specialties = (List<Specialty>) readFromJson(filePath);
        Optional<Specialty> specialty = specialties.stream().filter((a) -> a.getId() == aLong && a.getStatus() == Status.ACTIVE).findFirst();
        if (specialty.isEmpty()){
            System.out.println("Нет данных с id " + aLong + " в классе Specialty");
        }else {
            int index = specialties.indexOf(specialty.get());
            specialties.get(index).setStatus(Status.DELETED);
            writeToJson(filePath,specialties);
        }
    }

    @Override
    public int update(Specialty object) {

        List<Specialty> specialties = readFromJson(filePath);

        Specialty specialty = specialties.stream().filter((a) -> a.getId() == object.getId() && a.getStatus() == Status.ACTIVE).findAny().orElse(null);

        if (specialty == null){
            System.out.println("Нет данных с id " + object.getId() + " в классе Specialty");
        }else {
            int index = specialties.indexOf(specialty);
            specialties.get(index).setId(object.getId());
            specialties.get(index).setName(object.getName());
            writeToJson(filePath,specialties);

            return index;
        }

        return -1;
    }

    public List<Specialty> readFromJson(String filePath){
        List<Specialty> list = null;
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath))){
            Type listType = new TypeToken<ArrayList<Specialty>>(){}.getType();
            list = new Gson().fromJson(reader, listType);
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e);
        }

        if(list == null)
            list = new ArrayList<>();

        return list;
    }

    public void writeToJson(String filePath, List<Specialty> list){
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))){

            new GsonBuilder().create().toJson(list,writer);
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }

}
