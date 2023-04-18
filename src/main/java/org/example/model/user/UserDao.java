package org.example.model.user;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserDao implements DAO<User> {
    private final Connection conn;
    @SneakyThrows
    public Optional<User> get(String login){


        PreparedStatement statement = conn.prepareStatement
                ("select * from users where login = ?");
        statement.setString(1, login);
        ResultSet res = statement.executeQuery();

        String u_login;
        String password;
        String type;
        User u = null;
        if (res.next()) {
            u_login = res.getString("login");
            password = res.getString("password");
            type = res.getString("type");
            u = new User(u_login,password,type);
        }
        return Optional.ofNullable(u);
    }
    @SneakyThrows
    @Override
    public ArrayList<User> getAll() {
        PreparedStatement statement = conn.prepareStatement("select * from users");
        ResultSet res = statement.executeQuery();
        ArrayList<User> userList = new ArrayList<>();
        User tmp = null;
        while (res.next()) {
            String login = res.getString("login");
            String password = res.getString("password");
            String type = res.getString("type");
            tmp = new User(login, password, type);
            userList.add(tmp);
        }
        return userList;
    }

    @SneakyThrows
    @Override
    public boolean add(User elem) {
        PreparedStatement statement = conn.prepareStatement(
                "insert into users (login,password,type) values (?,?,?)");
        statement.setString(1, elem.getLogin());
        statement.setString(2, elem.getPassword());
        statement.setString(3, elem.getType().toString());

        return statement.execute();
    }

    @SneakyThrows
    @Override
    public boolean delete(User elem) {
        PreparedStatement statement = conn.prepareStatement(
                "delete from users where login = ?");
        statement.setString(1, elem.getLogin());
        return statement.execute();
    }
}
