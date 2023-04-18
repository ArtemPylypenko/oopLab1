package org.example.model.user;

import lombok.SneakyThrows;
import org.example.GlobalSQLConnection;

import static org.junit.jupiter.api.Assertions.*;


class UserDaoTest {

    @SneakyThrows
    @org.junit.jupiter.api.Test
    void get() {
        UserDao dao = new UserDao(GlobalSQLConnection.get());
        assertEquals(dao.get("ortem").get(), new User("ortem", "123", "admin"));
    }


    @SneakyThrows
    @org.junit.jupiter.api.Test
    void add() {
        UserDao dao = new UserDao(GlobalSQLConnection.get());
        dao.add(new User("notOrtem", "123", "customer"));
        assertEquals(dao.get("notOrtem").get(), new User("notOrtem", "123", "customer"));
    }

    @SneakyThrows
    @org.junit.jupiter.api.Test
    void delete() {
        UserDao dao = new UserDao(GlobalSQLConnection.get());
        dao.delete(new User("notOrtem", "123", "customer"));
        assertFalse(dao.get("notOrtem").isPresent());
    }
}