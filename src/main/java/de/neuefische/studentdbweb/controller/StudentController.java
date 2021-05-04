package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.model.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

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
    public Student addStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        if (student.getId().equals(id)) {
            studentService.add(student);
            if (studentService.findById(id).isPresent()) {
                return studentService.findById(id).get();
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student id not correct");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
