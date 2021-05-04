package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.model.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService = new StudentService(List.of(
            new Student("15", "Anna"),
            new Student("16", "Paul"),
            new Student("17", "John"),
            new Student("18", "Sarah")
    ));

    @GetMapping
    public List<Student> listStudents(@RequestParam Optional<String> search) {
        if(search.isEmpty()) {
            return studentService.list();
        }
        return studentService.search(search.get());
    }

    @GetMapping("{id}")
    public Student findStudent(@PathVariable String id) {
        Optional<Student> optionalStudent = studentService.findById(id);

        if(optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        return null;
    }

    @PutMapping("{id}")
    public Student addStudent(@PathVariable String id, @RequestBody Student student) {
        if (student.getId().equals(id)) {
            studentService.add(student);
            if (studentService.findById(id).isPresent()) {
                return studentService.findById(id).get();
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student id not correct");
    }


}
