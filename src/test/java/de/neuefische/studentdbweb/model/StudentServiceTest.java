package de.neuefische.studentdbweb.model;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final StudentService studentService = new StudentService();

    @BeforeEach
    public void setUpData() {
        studentService.add(new Student("10", "John"));
        studentService.add(new Student("10", "John"));
        studentService.add(new Student("10", "John"));
    }



}