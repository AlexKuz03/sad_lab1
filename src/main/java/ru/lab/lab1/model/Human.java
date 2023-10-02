package ru.lab.lab1.model;

import java.util.Date;

public record Human(Long id, String name, Date birthday, double height, double weight) {
}
