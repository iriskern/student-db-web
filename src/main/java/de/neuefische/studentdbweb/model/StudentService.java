package de.neuefische.studentdbweb.model;

import de.neuefische.studentdbweb.database.StudentDatabase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentDatabase studentDatabase;

    public StudentService(StudentDatabase studentDatabase) {
        this.studentDatabase = studentDatabase;
    }

    public List<Student> list() {
        return studentDatabase.list();
    }

    public Optional<Student> findById(String id) {
        return studentDatabase.findById(id);
    }

    public List<Student> search(String search) {
        List<Student> searchedStudents = new ArrayList<>();
        for (Student student : studentDatabase.list()) {
            if (student.getName().toLowerCase().contains(search.toLowerCase())) {
                searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public void add(Student student) {
        studentDatabase.add(student);
    }

}
