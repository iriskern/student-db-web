package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @GetMapping
    public List<Student> listStudents(){
        return List.of(
                new Student("15", "Anna"),
                new Student("16", "Paul"),
                new Student("17", "John"),
                new Student("18", "Sarah")
        );
    }
}
