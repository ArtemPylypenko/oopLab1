package org.example.model;

import java.awt.*;
import java.util.ArrayList;

public interface DAO<E> {
    ArrayList<E> getAll();
    boolean add(E elem);
    boolean delete(E elem);

}
