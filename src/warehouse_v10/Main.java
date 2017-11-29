package warehouse_v10;

import java.util.Scanner;

import static warehouse_v10.Operations_With_DB.*;

public class Main {
    // Константы с цветами.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int exitOrContinue;
        do {
            System.out.print("\nДоступные действия:"
                    + "\n1 - Показать внесенных производителей."
                    + "\n2 - Добавить нового производителя."
                    + "\n3 - Удалить производителя."
                    + ANSI_RED + "\nПри нажатии любой другой клавиши будет осуществлен выход!" + ANSI_RESET
                    + "\nВведите цифру, в зависимости от Вашего выбора: ");
            int action = s.nextInt();
            switch (action) {
                case 1:
                    System.out.println("Всего внесено: " + numberOfElements());
                    System.out.println("\nСписок производителей:");
                    showManufacturersList();
                    break;
                case 2:
                    int doYouWantToAddOneMore = 0;
                    boolean isItInTheDatabase = false;
                    String newManufacturer;
                    do {
                        do {
                            System.out.println("\nВведите название производителя, которого необходимо добавить:");
                            newManufacturer = s.next();
                            isItInTheDatabase = isItInTheDatabase(newManufacturer);
                            if (isItInTheDatabase == false) {
                                addManufacturerToList(newManufacturer);
                                System.out.println("\n" + ANSI_BLUE + "Производитель " + newManufacturer + " успешно добавлен!"
                                        + ANSI_RESET);
                                System.out.print("Желаете добавить еще одного производителя?"
                                        + "\n1 - Да."
                                        + "\n2 - Нет."
                                        + "\nВаш выбор: ");
                                doYouWantToAddOneMore = s.nextInt();
                            }
                            if (isItInTheDatabase == true) {
                                System.out.println("\n" + ANSI_RED + "Производитель " + newManufacturer
                                        + " уже внесен. Повторно внесен не будет." + ANSI_RESET);
                            }
                        } while (isItInTheDatabase == true);
                    } while (doYouWantToAddOneMore == 1);
                    System.out.println("\n\nСписок производителей:");
                    showManufacturersList();
                    break;
                case 3:
                    int doYouWantToRemoveOneMore;
                    // Переменные amountBeforeRemove и amountAfterRemove необходимы для сравнения кол-ва строк
                    // до и после удаления из БД. Таким образом можно понять, произоло ли удаление.
                    int amountBeforeRemove = numberOfElements();
                    int amountAfterRemove = 0;
                    String manufacturerToRemove;
                    do {
                        do {
                            System.out.println("\nВведите название производителя, которого необходимо удалить:");
                            manufacturerToRemove = s.next();
                            manufacturerToRemove(manufacturerToRemove);
                            amountAfterRemove = numberOfElements();
                            if (amountBeforeRemove == amountAfterRemove) {
                                System.out.println("\n" + ANSI_RED + "Производителя " + manufacturerToRemove
                                        + " нет в базе данных. Удалять его нет необходимости." + ANSI_RESET);
                            }
                        } while (amountBeforeRemove == amountAfterRemove);
                        System.out.println("\n" + ANSI_BLUE + "Производитель " + manufacturerToRemove + " успешно удален!"
                                + ANSI_RESET);
                        System.out.print("\nЖелаете удалить еще одного производителя?"
                                + "\n1 - Да."
                                + "\n2 - Нет."
                                + "\nВаш выбор: ");
                        doYouWantToRemoveOneMore = s.nextInt();
                    } while (doYouWantToRemoveOneMore == 1);
                    System.out.println("\nСписок производителей:");
                    showManufacturersList();
                    break;
                default:
                    System.out.println("\nВсего доброго!");
                    break;
            }
            System.out.print("\nЖелаете вернуться в главное меню?"
                    + "\n1 - Да."
                    + "\n2 - Нет."
                    + "\nВаш выбор: ");
            exitOrContinue = s.nextInt();
        } while (exitOrContinue == 1);
    }
}
