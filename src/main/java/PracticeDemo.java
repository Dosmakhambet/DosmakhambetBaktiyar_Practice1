import controller.DeveloperController;
import controller.SkillController;
import controller.SpecialtyController;
import model.Specialty;
import repository.impl.GsonDeveloperRepositoryImpl;
import repository.impl.GsonSkillRepositoryImpl;
import repository.impl.GsonSpecialtyRepositoryImpl;
import view.DeveloperView;
import view.SkillView;
import view.SpecialtyView;

import java.util.Scanner;

public class PracticeDemo {
    public static void main(String args[]){

        byte b;
        Scanner in = new Scanner(System.in);
        DeveloperView developerView = new DeveloperView(new GsonSkillRepositoryImpl(), new GsonSpecialtyRepositoryImpl());
        SkillView skillView = new SkillView();
        SpecialtyView specialtyView = new SpecialtyView();
        do{
            System.out.println("Добро пожаловать в меню");
            System.out.println("1. Developers");
            System.out.println("2. Specialty");
            System.out.println("3. Skills");
            System.out.println("4. Quit");

            b = in.nextByte();

            switch ((int) b) {
                case 1:
                    developerView.menu(in, new DeveloperController(new GsonDeveloperRepositoryImpl()));
                    break;
                case 2:
                    specialtyView.menu(in, new SpecialtyController(new GsonSpecialtyRepositoryImpl()));
                    break;
                case 3:
                    skillView.menu(in, new SkillController(new GsonSkillRepositoryImpl()));
                    break;
                default:
                    System.out.println("Для выхода нажмите 4");
                    break;
            }
        }while ((int)b != 4);

    }
}
