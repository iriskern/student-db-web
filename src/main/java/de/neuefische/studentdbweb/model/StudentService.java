package de.neuefische.studentdbweb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private final List<Student> students;

    public StudentService(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

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

    public List<Student> search(String search) {
        List<Student> searchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(search)) {
                searchedStudents.add(student);
            }
        }
        return searchedStudents;
    }

    public void add(Student student) {
        students.add(student);
    }

}
