package de.neuefische.studentdbweb.database;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDatabase {

    private final List<Student> students = new ArrayList<>();

    public List<Student> list() {
        return students;
    }

    public Optional<Student> findById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public Student add(Student newStudent) {
        for (Student student : students) {
            if (student.getId().equals(newStudent.getId())) {
                student.setName(newStudent.getName());
                return student;
            }
        }
        students.add(newStudent);
        return newStudent;
    }

}
