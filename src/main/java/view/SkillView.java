package view;

import controller.SkillController;
import model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {

    public void menu(Scanner in, SkillController controller){

        byte b;

        do{
            List<Skill> list = controller.getAll();

            System.out.println("Мы в меню Skill");
            System.out.println("1. Создать запись Skill");
            System.out.println("2. Удалить запись");
            System.out.println("3. Все запись");
            System.out.println("4. Запись по id");
            System.out.println("5. Обновить запись");
            System.out.println("6. Выход");
            b = in.nextByte();
            switch((int)b){
                case 1:
                    System.out.print("Напиши имя : ");
                    String name = in.next();
                    controller.create(new Skill(list.size()+1, name));
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
                    Skill skill = controller.get(getId);

                    if(skill == null)
                        System.out.println("Нету записи по данному id " + getId);
                    else
                        System.out.println(skill.toString());
                    break;
                case 5:
                    System.out.print("Напиши id : ");
                    Integer iD = in.nextInt();
                    Skill skil = controller.get(iD);

                    if(skil == null)
                        System.out.println("Нету записи по данному id " + iD);
                    else {
                        System.out.println(skil.toString());
                        System.out.print("Введите имя : ");
                        String str = in.next();
                        skil.setName(str);
                        controller.update(skil);
                    }
                    break;
                default:
                    System.out.println("Выход 6");
                    break;
            }

        } while (((int) b != 6));
    }

}
