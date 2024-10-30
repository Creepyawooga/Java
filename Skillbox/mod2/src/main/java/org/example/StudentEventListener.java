package org.example;

import org.example.Student;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void onStudentCreated(Student student) {
        System.out.println("Студент создан: " + student);
    }

    @EventListener
    public void onStudentDeleted(Long id) {
        System.out.println("Студент удалён с ID: " + id);
    }
}
