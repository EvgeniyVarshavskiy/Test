package warehouse_v10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operations_With_DB {
    private static final String url = "jdbc:mysql://localhost:3306/warehouse?useSSL=false";
    private static final String user = "root";
    private static final String password = "admin";

    // JDBC переменные для установления соединения.
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resoltSet;

    // Метод для вывода списка производителей.
    public static void showManufacturersList() {
        String query = "select id, name from manufacturer ORDER BY Name";
        try {
            // Открываем соединение и выполняем SELECT
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resoltSet = statement.executeQuery(query);
            int n = 1;
            while (resoltSet.next()) {
                int id = resoltSet.getInt(1);
                String name = resoltSet.getString(2);
                System.out.printf("%d. %s %n", n, name);
                n++;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закрываем соединение
            try {
                connection.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить connection.close().");
            }
            try {
                statement.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить statement.close().");
            }
            try {
                resoltSet.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить resoltSet.close().");
            }
        }
    }

    // Добавление нового производителя.
    public static void addManufacturerToList(String newManufacturer) {
        String query = "INSERT INTO warehouse.manufacturer (id, name) \n"
                + " VALUES (ID,'" + newManufacturer + "');";
        try {
            // Открываем соединение и выполняем SELECT
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закрываем соединение
            try {
                connection.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить connection.close().");
            }
            try {
                statement.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить statement.close().");
            }
        }
    }

    // Удаление производителя.
    public static void manufacturerToRemove(String manufacturerToRemove) {
        String query = "DELETE FROM warehouse.manufacturer WHERE Name='" + manufacturerToRemove + "'";
        try {
            // Открываем соединение и выполняем SELECT
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закрываем соединение
            try {
                connection.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить connection.close().");
            }
            try {
                statement.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить statement.close().");
            }
        }
    }

    // Получения кол-ва элементов в таблице.
    public static int numberOfElements() {
        int count = 0;
        String query = "select count(*) from manufacturer";
        try {
            // Открываем соединение и выполняем SELECT
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resoltSet = statement.executeQuery(query);
            while (resoltSet.next()) {
                count = resoltSet.getInt(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закрываем соединение
            try {
                connection.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить connection.close().");
            }
            try {
                statement.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить statement.close().");
            }
            try {
                resoltSet.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить resoltSet.close().");
            }
        }
        return count;
    }

    // Проверка на наличие элемента в таблице, перед тем, как его внести.
    public static boolean isItInTheDatabase(String newManufacturer) {
        boolean isItInTheDatabase = false;
        int count = 0;
        String query = "select count(*) from manufacturer where Name = '" + newManufacturer + "'";
        try {
            // Открываем соединение и выполняем SELECT
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resoltSet = statement.executeQuery(query);
            while (resoltSet.next()) {
                count = resoltSet.getInt(1);
                if (count > 0) {
                    isItInTheDatabase = true;
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закрываем соединение
            try {
                connection.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить connection.close().");
            }
            try {
                statement.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить statement.close().");
            }
            try {
                resoltSet.close();
            } catch (SQLException se) {
                System.out.println("Ошибка! Не удалось выполнить resoltSet.close().");
            }
        }
        return isItInTheDatabase;
    }
}