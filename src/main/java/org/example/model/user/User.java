package org.example.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private  String login;
    private  String password;
    private  String type;
}
