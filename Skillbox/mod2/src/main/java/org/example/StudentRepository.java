package org.example;

import org.example.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private Long currentId = 1L;

    public List<Student> findAll() {
        return students;
    }

    public void save(Student student) {
        student.setId(currentId++);
        students.add(student);
    }

    public Optional<Student> delete(Long id) {
        return students.removeIf(student -> student.getId().equals(id)) ? Optional.of(new Student()) : Optional.empty();
    }

    public void clear() {
        students.clear();
    }
}
