package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        //Создаем соединение
        Connection connection = Util.getConnection();
        //Создаем запрос
        String sql = "CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id))";
        //Выполняем запрос
        try {
            connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            //Закрываем подключение
        } finally {
            Util.closeConnection(connection);
        }

    }

    public void dropUsersTable() {
        //Создаем подключение к БД
        Connection connection = Util.getConnection();
        //Создаем запрос
        String sql = "DROP TABLE IF EXISTS User";
        //Выполняем запрос
        try {
            connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            //Закрываем подключение
        } finally {
            Util.closeConnection(connection);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        //Создаем подключение к БД
        Connection connection = Util.getConnection();
        //Создаем запрос
        String sql = "INSERT INTO User (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        //Выполняем запрос
        try {
            connection.createStatement().execute(sql);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
            //Закрываем подключение
        } finally {
            Util.closeConnection(connection);
        }

    }

    public void removeUserById(long id) {
        //Создаем подключение к БД
        Connection connection = Util.getConnection();
        //Создаем запрос
        String sql = "DELETE FROM User WHERE id = " + id;
        //Выполняем запрос
        try {
            connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            //Закрываем подключение
        } finally {
            Util.closeConnection(connection);
        }
    }

    public List<User> getAllUsers() {
        // Получение всех пользователей из таблицы (user)
        //
        // Пример SQL запроса:
        // SELECT * FROM `user`;
        //
        //Создаем подключение к БД
        Connection connection = Util.getConnection();
        //Создаем запрос
        String sql = "SELECT * FROM User";
        //Создаем список пользователей
        List<User> users = new ArrayList<>();
        //Выполняем запрос
        try {
            connection.createStatement().executeQuery(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Закрываем подключение
        } finally {
            Util.closeConnection(connection);
        }
        return users;
    }

    public void cleanUsersTable() {
        //Создаем подключение к БД
        Connection connection = Util.getConnection();
        //Создаем запрос
        String sql = "TRUNCATE TABLE User";
        //Выполняем запрос
        try {
            connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            //Закрываем подключение
        } finally {
            Util.closeConnection(connection);
        }
    }
}
