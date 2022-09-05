package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Vasya", "Pupkin", (byte) 25);
        userService.saveUser("Petya", "Ivanov", (byte) 30);
        userService.saveUser("Sasha", "Sidorov", (byte) 35);
        userService.saveUser("Masha", "Petrova", (byte) 40);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();


    }
}
