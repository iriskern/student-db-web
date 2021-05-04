package de.neuefische.studentdbweb.model;

import de.neuefische.studentdbweb.database.StudentDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class StudentServiceTest {

    private final StudentDatabase studentDatabase = new StudentDatabase();
    private final StudentService studentService = new StudentService(studentDatabase);

    @BeforeEach
    public void setUpData() {
        studentService.add(new Student("10", "John"));
        studentService.add(new Student("11", "Anna"));
        studentService.add(new Student("12", "Max"));
    }

    @Test
    public void testAddShouldAddAStudentToTheList() {
        // When
        studentService.add(new Student("13", "Rahel"));

        // Then
        assertThat(studentService.list(), containsInAnyOrder(new Student("10", "John"),
                new Student("11", "Anna"), new Student("12", "Max"), new Student("13", "Rahel")));
    }

    @Test
    public void testAddShouldOverwriteStudentWhenIdIsAlreadyUsed() {
        // When
        studentService.add(new Student("12", "Peter"));

        // Then
        assertThat(studentService.list(), containsInAnyOrder(new Student("10", "John"),
                new Student("11", "Anna"), new Student("12", "Peter")));
    }




}