package view;

import controller.DeveloperController;
import controller.SkillController;
import model.Developer;
import model.Skill;
import model.Specialty;
import repository.impl.GsonSkillRepositoryImpl;
import repository.impl.GsonSpecialtyRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    private GsonSkillRepositoryImpl skillRepository;
    private GsonSpecialtyRepositoryImpl specialtyRepository;

    public DeveloperView(GsonSkillRepositoryImpl skillRepository,
                               GsonSpecialtyRepositoryImpl specialtyRepository){
        this.skillRepository = skillRepository;
        this.specialtyRepository = specialtyRepository;
    }

    public void menu(Scanner in, DeveloperController controller){

        byte b;

        do{
            List<Developer> list = controller.getAll();

            System.out.println("Мы в меню Skill");
            System.out.println("1. Создать запись Skill");
            System.out.println("2. Удалить запись");
            System.out.println("3. Все запись");
            System.out.println("4. Запись по id");
            System.out.println("5. Обновить запись");
            System.out.println("6. Выход ");
            b = in.nextByte();

            switch ((int)b){
                case 1:
                    System.out.print("Напиши имя : ");
                    String firstName = in.next();
                    System.out.print("Напиши фамилию : ");
                    String lastName = in.next();
                    List<Skill> skills = new ArrayList<>();
                    skillRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                    System.out.print("Сколько Skills: ");
                    int n = in.nextInt();
                    for(int i =0 ; i < n; i ++){
                        System.out.print("Введите id skill: ");
                        skills.add(skillRepository.get(in.nextInt()));
                    }
                    specialtyRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                    System.out.print("Введите id specialty: ");
                    int m = in.nextInt();
                    Specialty specialty = specialtyRepository.get(m);
                    controller.create(new Developer(list.size()+1, firstName,lastName,specialty,skills));
                    break;
                case 2:
                    System.out.print("Напиши id : ");
                    Integer id = in.nextInt();
                    controller.delete(id);
                    break;
                case 3:
                    System.out.println("Все записи  : ");
                    controller.getAll().forEach((a) -> System.out.println(a.toString()));
                    break;
                case 4:
                    System.out.print("Напиши id : ");
                    Integer getId = in.nextInt();
                    Developer developer = controller.get(getId);

                    if(developer == null)
                        System.out.println("Нету записи по данному id " + getId);
                    else
                        System.out.println(developer.toString());
                    break;
                case 5:
                    System.out.print("Напиши id : ");
                    Integer iD = in.nextInt();
                    Developer dev = controller.get(iD);

                    if(dev == null)
                        System.out.println("Нету записи по данному id " + iD);
                    else {
                        System.out.println(dev.toString());
                        System.out.print("Введите имя : ");
                        String str = in.next();
                        dev.setFirstName(str);
                        System.out.print("Введите фамилия : ");
                        String str2 = in.next();
                        dev.setLastName(str2);

                        List<Skill> skills1 = new ArrayList<>();
                        skillRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                        System.out.print("Сколько Skills: ");
                        int n1 = in.nextInt();
                        for(int i =0 ; i < n1; i ++){
                            System.out.print("Введите id skill: ");
                            skills1.add(skillRepository.get(in.nextInt()));
                        }
                        dev.setSkills(skills1);
                        specialtyRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                        System.out.print("Введите id specialty: ");
                        int m1 = in.nextInt();
                        Specialty specialty1 = specialtyRepository.get(m1);
                        dev.setSpecialty(specialty1);
                        controller.update(dev);
                    }
                    break;
                default:
                    System.out.println("Выход 6");
                    break;
            }

        } while (((int) b != 6));
    }
}
