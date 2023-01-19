package view;

import controller.SpecialtyController;
import model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView {

    public void menu(Scanner in, SpecialtyController controller){

        byte b;

        do{
            List<Specialty> list = controller.getAll();

            System.out.println("Мы в меню Specialty");
            System.out.println("1. Создать запись Specialty");
            System.out.println("2. Удалить запись");
            System.out.println("3. Все запись");
            System.out.println("4. Запись по id");
            System.out.println("5. Обновить запись");
            System.out.println("6. Выход");
            b = in.nextByte();

            switch ((int)b){
                case 1:
                    System.out.print("Напиши имя : ");
                    String name = in.next();
                    controller.create(new Specialty(list.size()+1, name));
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
                    Specialty specialty = controller.get(getId);

                    if(specialty == null)
                        System.out.println("Нету записи по данному id " + getId);
                    else
                        System.out.println(specialty.toString());
                    break;
                case 5:
                    System.out.print("Напиши id : ");
                    Integer iD = in.nextInt();
                    Specialty spec = controller.get(iD);

                    if(spec == null)
                        System.out.println("Нету записи по данному id " + iD);
                    else {
                        System.out.println(spec.toString());
                        System.out.print("Введите имя : ");
                        String str = in.next();
                        spec.setName(str);
                        controller.update(spec);
                    }
                    break;
                default:
                    System.out.println("Выход '6'");
                    break;
            }

        } while (((int) b != 6));
    }
}
